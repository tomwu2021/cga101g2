let socket_public;
let onlineFriends;
$(document).ready(() => {
    socket_public = io('ws://localhost:3000/public', {query: `memberId=${getLoginId()}`});

    socket_public.on('connection', function () {
        console.log('public connected!');
    });

    socket_public.on('broadcast', (data) => {
        onPublicBroadcastHandler(JSON.parse(data)).then();
    });

    socket_public.on('on-on-line', data => {
        // alert('MemberId[' + data + '] 已上線!');
        $('.chatroom-list-i' + data).removeClass('offline');
        $('.chatroom-list-i' + data).addClass('online');
        onlineFriends.push(data);
    });

    socket_public.on('on-off-line', data => {
        // alert('MemberId[' + data + '] 已離線!');
        $('.chatroom-list-i' + data).removeClass('online');
        $('.chatroom-list-i' + data).addClass('offline');
        onlineFriends.forEach((item, key) => {
            if(item == data)
                onlineFriends.splice(key, 1);
        })
    });
})

async function onPublicMessage(message) {

}

async function onPublicBroadcastHandler(res) {
    switch (res.type) {
        case 'update-online-friends':
            onlineFriends = res.data;
            break;
    }
}

function isOnline(friendId){
    if(!onlineFriends) return false;
    return onlineFriends.find(item=>item == friendId);
}