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
io.of('/public').on('connection', (socket) => {
    socket.on('add-member', (memberId) => {
        socket.join(roomId);
        broadcast(memberId, '已經加入Public :' + memberId);
    });
});

io.of('/post').on('connection', (socket) => {
    socket.on('add-member', (postId) => {
        socket.join(postId);
        broadcast(postId, '已經加入POST:' + postId);
    });
});

let chatRoomMap = new Map();
const privateIO = io.of('/private');
privateIO.on('connection', (socket) => {
    console.log('a user connected private');
    socket.joindRooms = [];
    socket.memberId = parseInt(socket.handshake.query.memberId);
    socket.on('join-room', async (roomId) => {
        console.log(`${socket.memberId} has join this room :` + roomId);
        console.log(socket.joindRooms);
        socket.joindRooms.push(''+roomId);
        socket.join(parseInt(roomId));
        await DyDBService.onReadHandler(parseInt(roomId), parseInt(socket.memberId));
        let room = chatRoomMap.get(''+roomId);
        console.log(room);
        if (!room) {
            //建立新的
            room = new Map();
        }
        if(!room.get(''+socket.memberId)){
            console.log('new array')
            room.set(''+socket.memberId, []);
            privateIO.emit('on-line', socket.memberId);
        }
        let connections = room.get(''+socket.memberId);
        console.log(connections);
        connections.push(socket.id);
        room.set(''+socket.memberId, connections);
        chatRoomMap.set(''+roomId,room);
    });

    socket.on('leave-room', (roomId) => {
        console.log(`${socket.id} has left this room: ${roomId}`);
        socket.leave(roomId);
        removeConnectionFromRoom(roomId, socket);
    });

    socket.on('send-message', (data) => {
        broadcast(privateIO, data.message.chatroom_id, data.message);
        putRecord(data);
    });

    socket.on('on-read', async (message) => {
        setTimeout(async () => await DyDBService.updateOneUnread(message), 200);
    });

    socket.on('disconnect', (data) => {
        socket.joindRooms.forEach(id=>{
            removeConnectionFromRoom(id, socket);
        })
    });
});

function removeConnectionFromRoom(roomId, socket){
    let room = chatRoomMap.get(''+roomId);
    if(room){
        let connections = room.get(''+socket.memberId);
        if(connections){
            let key;
            connections.forEach((item,index)=>{
                if(item === socket.id){
                    key = index;
                }
            });
            connections.splice(key,1);
        }
        if(!connections || connections.length == 0){
            room.delete(''+socket.memberId);
            privateIO.emit('off-line', socket.memberId);

        }else{
            room.set(''+socket.memberId, connections);
        }
        console.log('removeConnectionFromRoom=======================')
        console.log(room.get(''+socket.memberId));
    }

}

function broadcast(io, id, data) {
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
    let room = chatRoomMap.get(''+roomId);
    console.log(room);
    console.log('===========================keys');
    return room.has(''+id);
}

function aliveCount(roomId) {
    if(chatRoomMap.get(''+roomId)){
        return chatRoomMap.get(''+roomId).size - 1;
    }
    return 0;
}