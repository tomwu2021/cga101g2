function getLoginId() {
	let id = $("#loginId").val()
	if (id) {
		return parseInt(id);
	}
	return null;
}


let socket_private;
$(document).ready(() => {

	socket_private = io('ws://localhost:3000/private', { query: `memberId=${getLoginId()}` });

	socket_private.on('connection', function() {
	});

	socket_private.on('broadcast', (message) => {
		onMessage(JSON.parse(message));
	});

	socket_private.on('broadcast-read', (message) => {
		onRead(message);
	})

	getPrivateChatroom();
	getTotalUnread();
	getTotalCartCount();

})

// $("#header-icon-area").on('click','#chat-icon',function(){
//     openChatList();
// })
// $("header").on('click','#close-window',function(){
//     closePrivateChatroom();
// })


//個人聊天室


//自動scroll 到底部
function keepBottom(className) {
	document.getElementsByClassName(className)[0].scrollTop = document.getElementsByClassName(className)[0].scrollHeight;
}




function getTotalUnread() {
	let loginId = getLoginId();
	console.log(getLoginId());
	console.log(loginId);
	$.get({
		url: "https://7v6jnv62e737iwiobergb4lm4m0qerua.lambda-url.ap-northeast-1.on.aws/" + loginId,
		success: function(result, status) {
			let html = "";
			console.log(result.data.Count);
			html = result.data.Count;
			$("#unread-count").html(html);
		}
	});
}


const groupImg = "https://d148yrb2gzai3l.cloudfront.net/thumbs/a55af2c2-ce27-11ec-856d-d3c78e9023dc-pexels-alena-darmel-9040438.jpg?d=600x400";
let chatList = [];
let currentChat;

function getPrivateChatroom() {
	let chatMemberList = [];
	$.get({
		url: getContextPath() + "/chatroom?action=getPrivateChatroom",
		success: function(result, status) {
			chatList = result;
			let html = "";
			if (!result.message) {
				for (let chatroom of result) {
					html += makeChatroomList(chatroom);
				}
				html += `<li id="make-group-chatroom" style="border:3px solid white;height:60px;margin:15px 0 " onclick="showGroupChatroom()">
                <span><i style="width:40px;height:40px;border:5px rgba(0,0,0,0.5);border-radius:50%" class="bi bi-patch-plus"></i></span>    
                <span style="font-size:20px;">創建團體聊天室</span>
              </li>`;
			}
			$("#private-chatroom-list-ul").html(html);
		}
	});
}




function makeChatroomList(chatroom) {
	let html = '';
	if (chatroom.chatroomType == 0) {
		html += `<li class="indivi-chatroom" value="${chatroom.memberId}" id="${chatroom.chatroomId}" style="border:3px solid white;height:60px;margin:15px 0 " onclick="openChat(${chatroom.chatroomId},${chatroom.chatroomType})">
                <i class="bi bi-circle-fill ${isOnline(chatroom.memberId) ? 'online' : 'offline'} ${'chatroom-list-i' + chatroom.memberId}"></i>
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

function searchFriend() {
	loading();
	let html = '';
	let action = "getFriends";
	$.get({
		url: getContextPath() + "/relationship?action=" + action + "&memberId=" + getLoginId(),
		processData: false,
		contentType: false,
		success: function(result, status) {
			for (let friend of result) {
				html += makeFriendList(friend);
			}
			html += `<input style="margin: 20px 25%;width: 50%;height: 40px;" type="text" id="group-chatroom-name" placeholder="輸入新聊天室名稱">
                   <button id="invite-button" onmouseleave="blurInviteButton()" onmouseover="hoverInviteButton()" onclick="makeGroupChatroom()" className="comment_reply" style="display:block;color: white;background-color: black;text-align: center;border-radius: 10px;height: 45px;width: 150px;letter-spacing: 5px;font-size: 22px;position: relative;margin: 20px auto;">邀請好友</button>`;
			$("#friend-list").html(html);
			offLoading();

		}
	});
}

function blurInviteButton() {
	$("#invite-button").css("background-color", "black");
}
function hoverInviteButton() {
	$("#invite-button").css("background-color", "#bd2130");
	$("#invite-button").css("border", "2px grey");
}

function showGroupChatroom() {
	$("#friend-list-container").toggle().show();
	searchFriend();
	$('#private-chatroom-list').hide();
}
function makeFriendList(friend) {
	let html = "";
	html += `<div class="comments_box col-lg-6" style="margin: 0 auto;text-align:left;">
        <div class="comment_list" style="margin: 20px auto;">
            <div class="comment_thumb" style="width: 100px;height: 100px;">
                <i class="bi bi-circle-fill ${isOnline(friend.memberId) ? 'online' : 'offline'}"></i>
                <img src="${friend.previewUrl}" alt="" style="height:64%;width: 96%;margin-top: 16px;">
            </div>
            <div class="comment_content" style="width: calc(100% - 100px) !important;margin-left: 100px;height:100px;min-width: 220px;">
                <div class="comment_meta">
                    <h5 style="font-size: 20px;letter-spacing: 5px;margin-top: 10px;margin-left: 0px;font-weight: 700;"><a>${friend.name}</a></h5>
                </div>
                <p style:"margin-top: 20px;">${friend.rankName}</p>
                <div id="friend${friend.memberId}" class="comment_reply" style="top: 60px;" onclick="addToInviteList(${friend.memberId})">
                <a id="friend-chat-${friend.memberId}" style="color:white">一起聊天</a>
                </div>
                        </div>
                    </div>
                </figure>
            </article>
        </div>`
	return html;
}

let friendIds = [];
function addToInviteList(friendId) {
	if ($(`#friend-chat-${friendId}`).hasClass("joined")) {
		$(`#friend-chat-${friendId}`).css('background-color', 'black');
		friendIds.filter(joinedId => friendId != joinedId);
		$(`#friend-chat-${friendId}`).text('一起聊天');
	} else {
		$(`#friend-chat-${friendId}`).css('background-color', 'lightcoral');
		$(`#friend${friendId}`).addClass("joined");
		friendIds.push(friendId);
		$(`#friend-chat-${friendId}`).text('已邀請');
	}
}

function makeGroupChatroom() {
	friendIds.push(getLoginId());
	let name = $("#group-chatroom-name").val().trim();
	$("#friend-list-container").hide();
	if (name && name !== "") {
		let data = {
			inviteIds: JSON.stringify(friendIds),
			name: name,
			action: "makeGroupChatroom",
			memberId: getLoginId()
		};
		JSON.stringify(data);
		$.ajax({
			url: getContextPath() + "/chatroom",
			type: "post",
			data: data,
			dataType: "text",
			success: function(result, status) {
				console.log(result);
				getPrivateChatroom();
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: '聊天室建立成功',
					showConfirmButton: false,
					timer: 1500
				});
			}
		});
		$("#group-chatroom-name").val("");
	} else {
		Swal.fire({
			position: 'center',
			icon: 'error',
			title: '請輸入聊天室名稱',
			showConfirmButton: false, timer: 1500
		})
	}
}

function openChatList() {
	let el = $('#private-chatroom-list');
	let visibility = el.css('display');
	if (visibility === 'none') {
		el.show();

	} else {
		el.hide();
	}
}
function closePrivateChatroom() {
	$('#private-chatroom-list').hide();
}
function closeFriendContainer() {
	$('#friend-list-container').hide();
}

function openChat(chatroomId, chatroomType) {
	currentChat = chatList.find(item => item.chatroomId === parseInt(chatroomId));
	let html = "";
	let crName = $(`#crname${chatroomId}`).text();
	if (chatroomType == 0) {
		html = `<h3 style="display: inline-block;">${crName}</h3>
                <span class="bi bi-x-lg chat-button" style="display: inline-block;float: right;" onclick="closeChatWindow()"></span>`;
	} else {
		html = `<h3 id="chatroom-name" style="display: inline-block;" onBlur="updateChatroomName(${chatroomId})">${crName}</h3>
        <span class="bi bi-x-lg chat-button" style="display: inline-block;float: right;" onclick="closeChatWindow()"></span>
        <span class="bi bi-plus-lg chat-button" style="display: inline-block;float: right;" onclick="joinPeople()"></span>
        <span class="bi bi-pencil-fill chat-button" style="display: inline-block;float: right;" onclick="editName()"></span>`;
	}
	$("#chat-buttons").html(html);
	// 關閉列表
	$('.chatroom_list').hide();
	//開啟對話視窗
	$('#private-chat').show();
	$('#private-chatroom-name').val(`${chatroomId}`);
	getMessageByRoomId(`${chatroomId}`, `${chatroomType}`);
}

function closeChatWindow() {
	socket_private.emit('leave-room', lastRoomId);
	$('.chatroom_window').hide();
}


let lastRoomId;

function getMessageByRoomId(chatroomId, chatroomType) {
	socket_private.emit('leave-room', lastRoomId);
	lastRoomId = chatroomId;
	socket_private.emit('join-room', chatroomId);
	$.get({
		url: `https://zpnzzdoix666snohgytcn3aawq0fsmzw.lambda-url.ap-northeast-1.on.aws/${chatroomId}/` + getLoginId(),
		success: function(result, status) {
			if (result.status == 200) {
				for (let message of result.data) {
					if (parseInt(message.is_read) !== 1 && parseInt(message.sender) !== getLoginId()) {
						socket_private.emit('on-read', message);
						getTotalUnread();
					}
				}

				$.get({
					url: `https://zpnzzdoix666snohgytcn3aawq0fsmzw.lambda-url.ap-northeast-1.on.aws/${chatroomId}/` + getLoginId(),
					success: function(result, status) {
						let html = "";
						for (let message of result.data) {
							if (message.sender == getLoginId()) {
								console.log("sender");
								html += makeSenderMessage(message, chatroomType);
							} else {
								console.log("message");
								html += makeMessage(message, chatroomType);
							}
						}
						$("#private-message-list-ul").html(html);
						keepBottom("chat_container");
					}
				});
			}
		}
	});
}

function onMessage(message) {
	console.log(message);
	let html = "";
	let chatroomType = currentChat.chatroomType;
	getTotalUnread();
	if (message.sender !== getLoginId()) {
		html = makeMessage(message, chatroomType);
	} else {
		html = makeSenderMessage(message, chatroomType);
	}
	$("#private-message-list-ul").append(html);
	keepBottom('chat_container');
	if (parseInt(message.sender) !== getLoginId()) {
		message.member_id = getLoginId();
		message.message_id = parseInt('' + message.chatroom_id + getLoginId() + message.create_time);
		socket_private.emit('on-read', message);
		onRead(message);
	}
}

function onRead(message) {
	console.log("READ");
	let readCount = message.read_count;
	if (currentChat.chatroomType === 0) {
		$(".isRead").text('已讀');
	} else {
		if (readCount) {
			$(".isRead").text('已讀' + readCount);
		}
	}
	getTotalUnread();
}

function getFormDate(millisecond) {
	var date = new Date(millisecond);
	const formatDate = (date) => {
		let formatted_date = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
		return formatted_date;
	}
	return formatDate(date);
}

function makeSenderMessage(message, chatroomType) {
	let time = getFormDate(message.create_time);
	let html = "";
	let readCount;
	let rc = parseInt(message.read_count);
	// let orRead;
	if (chatroomType == 0) {
		if (rc > 0) {
			readCount = '已讀';
			//         orRead = 'read-p'
		} else {
			readCount = '';
			//         orRead = 'unread'
		}
	} else {
		if (rc > 0) {
			readCount = '已讀' + rc;
			//         orRead = 'read-g'
		} else {
			readCount = '';
			//         orRead = 'unread'
		}
	}
	html += `<li class="sender" style="height:40px;margin:20px 0 ;">
                <span style="max-width:70px;display: inline-block;margin-right: 10px">
                        <div style="height:12px;font-size:8px;margin: 0 0 0 2px;">${time}</div>
                        <div class="isRead" id="is-read-${message.messageId}" style="height:12px;font-size:8px;text-align: right;margin: 0 0 0 2px;width:30px;">${readCount}</div>
                  </span>
                <span style="font-size:22px;display: inline-block"> ${message.message} </span>
            </li>`;
	return html;
}

function makeMessage(message, chatroomType) {
	let time = getFormDate(message.create_time);
	// let img = $(`#img-${message.chatroom_id}`).attr('src');
	let img = getCurrentMemberPic(message.sender);
	return `<li style="margin:20px 0 ;">
            <span><img src="${img}" style="width:30px;height:30px;border:5px rgba(20,20,20,0.5);border-radius:50%"></span>
             <span style="font-size:22px;display: inline-block"> ${message.message} </span>
            <span style="height:12px;font-size:8px;margin: 0 0 0 2px;">${time}</span>
            </li>`;
}

function getCurrentMemberPic(id) {
	let obj = currentChat.crms.find(item => item.memberId == id);
	if (obj) {
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
		message: message,
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

function updateChatroomName(chatroomId) {
	let newName = $("#chatroom-name").text().trim();
	if (newName == roomName || newName == '' || newName == null) {
		$("#chatroom-name").text(roomName);
		return;
	}
	$("#chatroom-name").attr('contenteditable', false);
	let roomId = $("#chatroom-name").val();
	$.get({
		url: getContextPath() + "/chatroom?action=updateChatroomName&newName=" + newName + "&chatroomId=" + chatroomId,
		success: function(result, status) {
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: '變更聊天室名稱成功',
				showConfirmButton: false,
				timer: 1500
			})
			getPrivateChatroom();
		}
	});
}

function getTotalCartCount() {
	$.get({
		url: getContextPath() + "/member/cart.do?action=getCartCount",
		success: function(result, status) {
			let html = "";
			console.log(result);
			$("#cart-count").html(result);
		}
	});
}