const express = require('express');
const app = express();
const http = require('http');
const server = http.createServer(app);
const {Server} = require("socket.io");
const ioOption = {
    cors: {
        origin: "*",
        methods: ["GET", "POST"]
    }
}
const io = new Server(server, ioOption);
const port = 3000;
const DyDBService = require('./dynamoDBService.js');
const MemberService = require('./memberMySQLService');
const commentMySQLService = require('./commentMySQLService.js');
app.get('/', (req, res) => {
    res.send(`<h1>Websocket is listening on *:${port}</h1>`);
});

app.get('/getMessageByRoomId/:cid/:memeberId', async (req, res) => {
    let chatroomId = parseInt(req.params.cid);
    let memeberId = parseInt(req.params.memeberId);
    let result = await DyDBService.getMessageByRoomId(chatroomId, memeberId);
    res.header('Content-Type', 'application/json');
    res.header('Access-Control-Allow-Origin', '*');
    res.send({data: result, status: 200});
});

// socket.io
let onLineMap = new Map();
const publicIO = io.of('/public');
publicIO.on('connection', async (socket) => {
    let memberId = parseInt(socket.handshake.query.memberId);
    socket.memberId = memberId;
    if (memberId && !publicIO.adapter.rooms.has(memberId)) {
        //第一次登入
        //告訴好友已上線
        let onlineFriends = [];
        MemberService.getFriendsIdByMemberId(memberId).then(res => {
                res.forEach(item => {
                    publicIO.to(item.target_id).emit('on-on-line', memberId);
                    if (publicIO.adapter.rooms.has(item.target_id)) {
                        //預備取得上線好友列表
                        onlineFriends.push(item.target_id);
                    }
                })
                onLineMap.set(memberId, onlineFriends);
                broadcastUpdateOnlineFriends(memberId);
            }
        );
    } else if (memberId) {
        // 更新線上好友列表自前台
        broadcastUpdateOnlineFriends(memberId);
    }
    socket.join(memberId);

    socket.on('disconnect', (data) => {
        let memberId = socket.memberId;
        socket.leave(memberId);
        if (memberId && !publicIO.adapter.rooms.has(memberId)) {
            //該會員最後一條 session離開
            MemberService.getFriendsIdByMemberId(memberId).then(res => {
                    res.forEach(item => {
                        publicIO.to(item.target_id).emit('on-off-line', memberId);
                        // 取得好友的 onlineMap
                        if (onLineMap.get(item.target_id)){
                            // 取出好友的在線好友list
                            let onlineFriends = onLineMap.get(item.target_id) || [];

                            //將自己移除
                            onlineFriends.forEach((item, index) => {
                                if(item == memberId)
                                onlineFriends.splice(index, 1);
                            })
                            onLineMap.set(item.target_id, onlineFriends);
                        }
                    });

                }
            );
        }
    });
});

function broadcastUpdateOnlineFriends(memberId) {
    broadcast(publicIO, memberId, {
        type: 'update-online-friends',
        data: onLineMap.get(memberId) || []
    });
}


let chatRoomMap = new Map();
const privateIO = io.of('/private');
privateIO.on('connection', (socket) => {
    socket.joindRooms = [];
    socket.memberId = parseInt(socket.handshake.query.memberId);
    socket.on('join-room', async (roomId) => {
        console.log(`${socket.memberId} has join this room :` + roomId);
        console.log(socket.joindRooms);
        socket.joindRooms.push('' + roomId);
        socket.join(parseInt(roomId));
        await DyDBService.onReadHandler(parseInt(roomId), parseInt(socket.memberId));
        let room = chatRoomMap.get('' + roomId);
        console.log(room);
        if (!room) {
            //建立新的
            room = new Map();
        }
        if (!room.get('' + socket.memberId)) {
            console.log('new array')
            room.set('' + socket.memberId, []);
        }
        let connections = room.get('' + socket.memberId);
        console.log(connections);
        connections.push(socket.id);
        room.set('' + socket.memberId, connections);
        chatRoomMap.set('' + roomId, room);
    });

    socket.on('leave-room', (roomId) => {
        console.log(`${socket.id} has left this room: ${roomId}`);
        socket.leave(roomId);
        removeConnectionFromRoom(roomId, socket);
    });

    socket.on('send-message', (data) => {
        let readCount = aliveCount(data.message.chatroom_id);
        data.message.read_count = readCount;
        let members = data.members;
        //broadcast publicIO ->members
        //loop members -> lambda -> 取得未讀總數(memberId) & 取得個別聊天未讀總數(memberId,chatroom)
        //java -> dynamo -> 取得初始個別聊天室未讀數
        //
        broadcast(privateIO, data.message.chatroom_id, data.message);

        putRecord(data);
    });

    socket.on('on-read', async (message) => {
        setTimeout(async () => await DyDBService.updateOneUnread(message), 300);

    });

    socket.on('disconnect', (data) => {
        socket.joindRooms.forEach(id => {
            removeConnectionFromRoom(id, socket);
        })
    });
});

function removeConnectionFromRoom(roomId, socket) {
    let room = chatRoomMap.get('' + roomId);
    if (room) {
        let connections = room.get('' + socket.memberId);
        if (connections) {
            let key;
            connections.forEach((item, index) => {
                if (item === socket.id) {
                    key = index;
                }
            });
            connections.splice(key, 1);
        }
        if (!connections || connections.length == 0) {
            room.delete('' + socket.memberId);
        } else {
            room.set('' + socket.memberId, connections);
        }
    }
}

function broadcast(io, id, data) {
    console.log("ressssssssss:"+JSON.stringify(data)+"///id:"+id);
    io.to(parseInt(id)).emit('broadcast', JSON.stringify(data));
}

server.listen(port, () => {
    console.log(`websocket listening on *:${port}`);
});

function putRecord(data) {
    let members = data.members;
    let message = data.message;
    // console.log(`${message.chatroom_id}: ${message.message}`);
    for (let item of members) {
        console.log(`${item.name} isOnline:` + isOnline(message.chatroom_id, item.memberId))
        let newMsg = JSON.parse(JSON.stringify(message));
        newMsg.message_id = parseInt('' + message.chatroom_id + item.memberId + message.create_time);
        if (parseInt(item.memberId) === newMsg.sender) {
            newMsg.member_id = item.memberId
            newMsg.read_count = aliveCount(message.chatroom_id);
        } else {
            newMsg.member_id = item.memberId
            newMsg.is_read = 0;
            // message.is_read = isOnline(room.members, item.memeberId;
        }
        DyDBService.putMessage(newMsg);
    }
}

function isOnline(roomId, id) {
    let room = chatRoomMap.get('' + roomId);
    console.log(room);
    console.log('===========================keys');
    if(room) {
        return room.has('' + id);
    }
}

function aliveCount(roomId) {
    if (chatRoomMap.get('' + roomId)) {
        return chatRoomMap.get('' + roomId).size - 1;
    }
    return 0;
}

const postIO = io.of('/post');
postIO.on('connection', (socket) => {
    socket.on('add-member', (data) => {
        socket.join(parseInt(data.postId));
        console.log("add!");
        // broadcast(postIO,data.postId,data.memberId+"成功加入post"+data.postId);
    });

    socket.on('send-comment', async (data) => {
        console.log("send-comment!");
        await commentMySQLService.insertComment(data).then((res)=>{
            broadcast(postIO,data.postId,res);
        });
    });
    socket.on('send-reply', async (data) => {
        console.log("send-reply!");
        await commentMySQLService.insertReply(data).then((res)=>{
            broadcast(postIO,parseInt(data.postId),res);
        });
    });
    socket.on('update-comment', async(data)=>{
        console.log("update");
        updateBroadcast(postIO,parseInt(data.postId),data);
        await commentMySQLService.updateComment(data);
    });
    socket.on('delete-comment', async(data)=>{
        console.log(data);
        deleteBroadcast(postIO,parseInt(data.postId), data)
        await commentMySQLService.deleteComment(data);
    });

});
function updateBroadcast(io, id, data) {
    console.log("updateressssssssss:"+JSON.stringify(data)+"///id:"+id);
    io.to(parseInt(id)).emit('update-broadcast', JSON.stringify(data));
}
function deleteBroadcast(io, id, data) {
    console.log("deleteressssssssss:"+JSON.stringify(data)+"///id:"+id);
    io.to(parseInt(id)).emit('delete-broadcast', JSON.stringify(data));
}