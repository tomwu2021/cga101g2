const AWS = require("aws-sdk");
const Config = require("./config") ;
AWS.config.update(Config.AWS);
const docClient = new AWS.DynamoDB.DocumentClient();
const tableName = 'Messages';

getMessageByRoomId = async (chatroomId, memberId) => {
    console.log("Scanning messages table.");
	let params = {
    TableName: tableName,
    ProjectionExpression: "message_id, chatroom_id,member_id, message, create_time,sender, read_count",
    ScanIndexForward: true,
    FilterExpression: "chatroom_id = :chatroom_id  and member_id = :member_id",
    ExpressionAttributeValues: {
         ":chatroom_id": chatroomId,
         ":member_id":memberId
		}
	};
    let messages = await docClient.scan(params, onScan).promise();
    messages = messages.Items.sort(function (a, b) {return a.create_time - b.create_time;});
    // TODO implement
    console.log(messages);
    return messages;
};

async function onScan(err, data) {
    if (err) {
        console.error("Unable to scan the table. Error JSON:", JSON.stringify(err, null, 2));
    } else {
        // print all the movies
        console.log("Scan succeeded.");
        data.Items.forEach(function(message) {
            console.log(JSON.stringify(message));
        });

        // continue scanning if we have more movies, because
        // scan can retrieve a maximum of 1MB of data
        if (typeof data.LastEvaluatedKey != "undefined") {
            console.log("Scanning for more...");
            params.ExclusiveStartKey = data.LastEvaluatedKey;
            await docClient.scan(params, onScan);
        }
    }
}

function putMessage(data){
    console.log(data)
    let params = {
        TableName:tableName,
        Item:data
    }
    docClient.put(params, function(err, data) {
        if (err) {
            console.error("Unable to add item. Error JSON:", JSON.stringify(err, null, 2));
        }
    });
}
function updateOneUnread(data){
    updateUnreadToRead(data).then();
}

onReadHandler = async (chatroomId, userId) => {
    // Step1 取得未讀訊息
    let unreadResult = await getUnreadMessge(chatroomId, userId);

    // Step2 更新未讀為已讀
    await onUpdateUnread(unreadResult.Items);
};

// Step1
async function getUnreadMessge(chatroomId, userId){
    let nowTime = Date.now();
    console.log('Debug:: nowTime: ' , nowTime);
    let params = {
        TableName: tableName,
        ProjectionExpression: "chatroom_id, message_id, sender , member_id, create_time, is_read",
        ScanIndexForward: false,
        FilterExpression: "chatroom_id = :chatroom_id  and member_id = :member_id and create_time <=:now and is_read = :unRead",
        ExpressionAttributeValues: {
            ":chatroom_id": chatroomId,
            ":member_id": userId,
            ":now": nowTime,
            ":unRead":0
        }
    };
    return await docClient.scan(params, onScan).promise();

    async function onScan(err, data) {
        if (err) {
            console.error("Unable to scan the table. Error JSON:", JSON.stringify(err, null, 2));
        } else {
            //data.Items.forEach(function(message) {
            //console.log(JSON.stringify(message));
            //});

            // continue scanning if we have more date, because
            // scan can retrieve a maximum of 1MB of data
            if (typeof data.LastEvaluatedKey != "undefined") {
                console.log("Scanning for more...");
                params.ExclusiveStartKey = data.LastEvaluatedKey;
                await docClient.scan(params, onScan);
            }
        }
    }
}

// Step 2
async function onUpdateUnread(unreads){
    let prepareReadCounts = [];
    for (let index = 0; index < unreads.length; index++) {
        // Step 2-1 更新當前user對話紀錄中未讀訊息為已讀
        await updateUnreadToRead(unreads[index]);
        // Step 2-2-1 取得被當前user已讀的對話紀錄中的已讀數+1
        let res = await getReadCount(unreads[index]);
        prepareReadCounts.push(res.Items[0])
    }
    console.log('prepareReadCounts:' + prepareReadCounts);
    for (let index = 0; index < prepareReadCounts.length; index++) {
        // Step 2-2-2 更新被當前user已讀的對話紀錄中的已讀數+1
        await updateReadCount(prepareReadCounts[index]);
    }

}

// Step 2-1
async function updateUnreadToRead(msg){

    let params = {
        TableName: tableName,
        Key:{
            "message_id": msg.message_id,
            "create_time": msg.create_time
        },
        UpdateExpression: "set is_read = :r",
        ExpressionAttributeValues:{
            ":r":1
        },
        ReturnValues:"UPDATED_NEW"
    };
    return await docClient.update(params, function(err, data) {
        if (err) {
            console.error("Unable to update Unread To Read. Error JSON:", JSON.stringify(err, null, 2));
        } else {
            console.log("Update Unread To Read succeeded:", JSON.stringify(data, null, 2));
        }
    })
}


// Step 2-2-1
async function getReadCount(msg){
    // Step 2-2-1 由當前user的對話紀錄中的sender, 找出要修改ReadCount 的message record
    let params = {
        TableName: tableName,
        ProjectionExpression: "chatroom_id, message_id, sender , member_id, create_time, read_count",
        ScanIndexForward: false,
        FilterExpression: "chatroom_id = :chatroom_id  and member_id = :sender and create_time = :create_time",
        ExpressionAttributeValues: {
            ":chatroom_id": msg.chatroom_id,
            ":sender": msg.sender,
            ":create_time": msg.create_time,
        }
    };

    return await docClient.scan(params,((err, data) => {
            if (err) {
                console.error("Unable to scan the table. Error JSON:", JSON.stringify(err, null, 2));
            } else {
                data.Items.forEach(function(message) {
                    console.log('Prepare Update Read Count Msg:' + JSON.stringify(message));
                });
            }
        })
    ).promise();
}

// Step 2-2-2
async function updateReadCount(msg){
    let updateCountparams = {
        TableName: tableName,
        Key:{
            "message_id": msg.message_id,
            "create_time": msg.create_time
        },
        UpdateExpression: "set read_count = :r",
        ExpressionAttributeValues:{
            ":r" : (msg.read_count + 1)
        },
        ReturnValues:"UPDATED_NEW"
    };
    return await docClient.update(updateCountparams, function(err, data) {
        if (err) {
            console.error("Unable to update Read Count +1. Error JSON:", JSON.stringify(err, null, 2));
        } else {
            console.log("Update Read Count +1 succeeded:", JSON.stringify(data, null, 2));
        }
    }).promise();
}




module.exports = { getMessageByRoomId ,putMessage, updateOneUnread, onReadHandler}