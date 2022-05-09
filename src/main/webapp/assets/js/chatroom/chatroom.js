function getLoginId() {
    let id = $("#loginId").val()
    if(id){
        return parseInt(id);
    }
    return null;
}


let socket_private;
$(document).ready(()=>{
    socket_private = io('ws://localhost:3000/private', {query:`memberId=${getLoginId()}`});

    socket_private.on('connection', function () {
    });

    socket_private.on('broadcast', (message) => {
        onMessage(JSON.parse(message));
    });

    getPrivateChatroom();

})




//個人聊天室


//自動scroll 到底部
function keepBottom(className) {
    document.getElementsByClassName(className)[0].scrollTop = document.getElementsByClassName(className)[0].scrollHeight;
}





const groupImg = "https://d148yrb2gzai3l.cloudfront.net/thumbs/a55af2c2-ce27-11ec-856d-d3c78e9023dc-pexels-alena-darmel-9040438.jpg?d=600x400";
let chatList = [];
let currentChat;

function getPrivateChatroom() {
    let chatMemberList = [];
    $.get({
        url: getContextPath() + "/chatroom?action=getPrivateChatroom", success: function (result, status) {
            chatList = result;
            let html = "";
            if (!result.message) {
                for (let chatroom of result) {
                    html += makeChatroomList(chatroom);
                }
            }
            $("#private-chatroom-list-ul").html(html);
        }
    });
}

function makeChatroomList(chatroom) {

    let html = '';
    if (chatroom.chatroomType == 0) {
        html += `<li class="indivi-chatroom" value="${chatroom.memberId}" id="${chatroom.chatroomId}" style="border:3px solid white;height:60px;margin:15px 0 " onclick="openChat(${chatroom.chatroomId},${chatroom.chatroomType})">
                <img id="img-${chatroom.chatroomId}"src="${chatroom.previewUrl}" style="width:40px;height:40px;border:5px rgba(0,0,0,0.5);border-radius:50%">
                <span style="font-size:20px;" id="crname${chatroom.chatroomId}">${chatroom.name}</span>
              </li>`;
    } else {
        html += `<li class="group-chatroom" id="${chatroom.chatroomId}" style="border:3px solid white;height:60px;margin:15px 0 " onclick="openChat(${chatroom.chatroomId},${chatroom.chatroomType})">
<!--                <img id="img-${chatroom.chatroomId}"src="${groupImg}" style="width:40px;height:40px;border:5px rgba(0,0,0,0.5);border-radius:50%"> -->
                <span><i style="width:40px;height:40px;border:5px rgba(0,0,0,0.5);border-radius:50%" class="bi bi-people-fill"></i></span>    
                <span style="font-size:20px;" id="crname${chatroom.chatroomId}">${chatroom.chatroomName}</span>
              </li>`;
    }
    return html;
}

function openChatList() {
    let el = $('#private-chatroom-list')
    let visibility = el.css('display');
    if (visibility === 'none') {
        el.show();
    } else {
        el.hide();
    }
}

function openChat(chatroomId, chatroomType) {
    currentChat = chatList.find(item => item.chatroomId === parseInt(chatroomId));
    let html = "";
    let crName = $(`#crname${chatroomId}`).text();
    if (chatroomType == 0) {
        html = `<h3 style="display: inline-block;">${crName}</h3>
                <span class="bi bi-x-lg chat-button" style="display: inline-block;float: right;" onclick="closeChatWindow()"></span>`;
    } else {
        html = `<h3 id="chatroom-name" style="display: inline-block;" onBlur="updateChatroomName()">${crName}</h3>
        <span class="bi bi-x-lg chat-button" style="display: inline-block;float: right;" onClick="closeChatWindow()"></span>
        <span class="bi bi-plus-lg chat-button" style="display: inline-block;float: right;" onClick="joinPeople()"></span>
        <span class="bi bi-pencil-fill chat-button" style="display: inline-block;float: right;" onClick="editName()"></span>`;
    }
    $("#chat-buttons").html(html);
    // 關閉列表
    $('.chatroom_list').hide();
    //開啟對話視窗
    $('#private-chat').show();
    $('#private-chatroom-name').val(`${chatroomId}`);
    getMessageByRoomId(`${chatroomId}`);
}

function closeChatWindow() {
    $('.chatroom_window').hide();
}


let lastRoomId;

function getMessageByRoomId(chatroomId) {
    socket_private.emit('leave-room', lastRoomId);
    lastRoomId = chatroomId;
    socket_private.emit('join-room', chatroomId);
    $.get({
        url: `https://zpnzzdoix666snohgytcn3aawq0fsmzw.lambda-url.ap-northeast-1.on.aws/${chatroomId}/` + getLoginId(),
        success: function (result, status) {
            let html = "";
            if (result.status == 200) {
                for (let message of result.data) {
                    if (message.sender == getLoginId()) {
                        html += makeSenderMessage(message);
                    } else {
                        html += makeMessage(message);
                    }
                }
            }
            $("#private-message-list-ul").html(html);
            keepBottom("chat_container");
            onRead();
        }
    });
}

function onMessage(message) {
    let html = "";
    if (message.sender !== getLoginId()) {
        html = makeMessage(message);
    } else {
        html = makeSenderMessage(message);
    }
    $("#private-message-list-ul").append(html);
    keepBottom('chat_container');
    if(message.sender !=  getLoginId()){
        onRead();
        message.member_id = getLoginId();
        message.message_id = parseInt('' + message.chatroom_id + getLoginId() + message.create_time);
        console.log(message);
        socket_private.emit('on-read', message);
    }
}

function onRead() {
    $('.is-read').text('已讀');
}

function getFormDate(millisecond) {
    var date = new Date(millisecond);
    const formatDate = (date) => {
        let formatted_date = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
        return formatted_date;
    }
    return formatDate(date);
}

function makeSenderMessage(message) {
    let time = getFormDate(message.create_time);
    return    `<li class="sender" style="height:40px;margin:20px 0 ;">
                <span style="max-width:70px;display: inline-block;margin-right: 10px">
                        <div style="height:12px;font-size:8px;margin: 0 0 0 2px;">${time}</div>
            <div class="is-read" id="is-read-${message.message_id}" style="height:12px;font-size:8px;text-align: right;margin: 0 0 0 2px;"">${message.is_read != 1?'':'已讀'} </div>
        </span>
                <span style="font-size:22px;display: inline-block"> ${message.message} </span>
            </li>`;
}

function makeMessage(message) {
    let time = getFormDate(message.create_time);
    // let img = $(`#img-${message.chatroom_id}`).attr('src');
    let img = getCurrentMemberPic(message.sender);
    return `<li style="margin:20px 0 ;">
            <span><img src="${img}" style="width:30px;height:30px;border:5px rgba(20,20,20,0.5);border-radius:50%"></span>
             <span style="font-size:22px;display: inline-block"> ${message.message} </span>
            <span style="height:12px;font-size:8px;margin: 0 0 0 2px;">${time}</span>
            </li>`;
}

function getCurrentMemberPic(id){
    let obj =  currentChat.crms.find(item=>item.memberId == id);
    if(obj){
        return obj.previewUrl
    }
    return null;
}

function putMessage() {
    let createTime = Date.now();
    let message = {
        sender: getLoginId(),
        chatroom_id: currentChat.chatroomId,
        message: null,
        create_time: createTime,
    };
    message["message"] = $("#private-new-message").val();
    $("#private-new-message").val('');
    // if(currentChat.chatroomType == 0 && !currentChat.crms.find(item=>item.memberId == getLoginId())){
    //     let member={ memberId: getLoginId()};
    //     currentChat.crms.push(member);
    // }
    let data = {
        message:message,
        members: currentChat.crms,
    }
    socket_private.emit('send-message', data);


}


// function joinPeople(){
//     $.get({
//         url:getContextPath()+"/chatroom?action=getPersonalChatroom&targetId="+targetId,
//         success: function(result, status) {
//             let html="";
//             if(result.status == 200) {
//                 for(let message of result.data) {
//                     if(message.sender==getLoginId()) {
//                         html += makeSenderMessage(message);
//                     }else{
//                         html += makeMessage(message);
//                     }
//                 }
//             }
//             $("#message-list-ul").html(html);
//         }
//     })
// }
let roomName;

function editName() {
    roomName = $("#chatroom-name").text().trim();
    $("#chatroom-name").attr('contenteditable', true);
    $("#chatroom-name").focus();
}

function updateChatroomName() {
    let newName = $("#chatroom-name").text().trim();
    if (newName == roomName || newName == '' || newName == null) {
        $("#chatroom-name").text(roomName);
        return;
    }
    $("#chatroom-name").attr('contenteditable', false);
    let roomId = $("#chatroom-name").val();
    $.get({
        url: getContextPath() + "/chatroom?action=updateChatroomName&newName=" + newName + "&chatroomId=" + roomId,
        success: function (result, status) {
            Swal.fire({
                position: 'center', icon: 'success', title: '變更聊天室名稱成功', showConfirmButton: false, timer: 1500
            })
            getPersonalChatroom();
        }
    });
}
