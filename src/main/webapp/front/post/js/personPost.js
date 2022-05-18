$(document).ready(() => {
    makeButtonArea();
})

let isFriend;
let isBlock;
let isInviting;
let isInvited;
let isBlocked;
let targetId = parseInt($("#memberId").val());
let isOwner = parseInt($("#isOwner").val());

function makeButtonArea() {
    console.log("/////////lid:"+getLoginId()+"////isOwner:"+isOwner+"////target:"+targetId)
    if(getLoginId() <= 0 ||getLoginId() === targetId ){
        return;
    }
    $.get({
        url: `${getContextPath()}/relationship?memberId=${getLoginId()}&action=getRelation&targetId=${targetId}`,
        success: function (result, status) {
            let html = "";
            $("#button-area").html(html);
            console.log(result);
            isFriend = parseInt(result.isFriend);
            isBlock = parseInt(result.isBlock);
            isInviting = parseInt(result.isInviting);
            isInvited = parseInt(result.isInvited);
            isBlocked = parseInt(result.isInvited);
            let contextPath = getContextPath();
            if (isFriend === 1) {
                html += `<div class="button1" style="margin-bottom:10px;"><i class="bi bi-check-lg" style="color:white;"></i> 朋友 </div>`
            } else if (isInviting === 1) {
                html += `<div class="button1" id="cancelInvite" style="margin-bottom: 10px;" onclick="cancelInvite()">取消邀請</div>`;
            } else if (isInvited === 0) {
                html += `<div class="button1" id="inviteFriend" style="margin-bottom: 10px;" onclick="inviteFriend()">加為朋友</div>`;
            } else if (isInvited === 1){
                html += `<div class="button1" id="inviteFriend" style="margin-bottom: 10px;" onclick="accpetInvite()">接受邀請</div>`;
            }
            if (isBlock === 0) {
                html += `<div class="button2" id="addBlock" style="margin-bottom: 10px;position: relative;right: 10px;" onclick="addBlock()" >封鎖</div>`
                html += `<a href="${contextPath}/album?memberId=${targetId}">
                        <div class="button1 buttons">觀看相簿</div>
                    </a>`;
            }else {
                html += `<div class="button2" id="deleteBlock"  style="margin-bottom: 10px;position: relative;right: 10px;" onclick="deleteBlock()" >取消封鎖</div>`
                html += `<a href="${contextPath}/album?memberId=${targetId}">
                        <div class="button1 buttons">觀看相簿</div>
                    </a>`;
            }
            $("#button-area").html(html);
        }
    });
}

function inviteFriend() {
    if(isBlocked===1){
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: '發生不明錯誤',
            showConfirmButton: false,
            timer: 1500
        })
        return;
    }
    if(isBlock===1){
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: '請先移除黑名單',
            showConfirmButton: false,
            timer: 1500
        })
        return;
    }
    $.get({
        url: `${getContextPath()}/relationship?targetId=${targetId}&action=inviteFreind&memberId=${getLoginId()}`,
        success: function (result, status) {
            if(parseInt(result.status) === 0) {
                console.log(result.status);
                offLoading();
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: '邀請失敗',
                    showConfirmButton: false,
                    timer: 1500
                })
            }else{
                makeButtonArea();
                offLoading();
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: '邀請發出成功',
                    showConfirmButton: false,
                    timer: 1500
                })
            }
        }
    })
}

function cancelInvite() {

    $.get({
        url: `${getContextPath()}/relationship?targetId=${targetId}&action=cancelInvite&memberId=${getLoginId()}`,
        success: function (result, status) {
            if(parseInt(result.status) === 0) {
                console.log(result.status);
                offLoading();
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: '取消失敗',
                    showConfirmButton: false,
                    timer: 1500
                })
            }else{
                makeButtonArea();
                offLoading();
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: '成功取消',
                    showConfirmButton: false,
                    timer: 1500
                })
            }
        }
    })
}

function addBlock() {
    if(isFriend===1){
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: '請先移除好友',
            showConfirmButton: false,
            timer: 1500
        })
        return;
    }

    $.get({
        url: `${getContextPath()}/relationship?targetId=${targetId}&action=addBlock&memberId=${getLoginId()}`,
        success: function (result, status) {
            if(parseInt(result.status) === 0) {
                console.log(result.status);
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: '不能加入黑名單',
                    showConfirmButton: false,
                    timer: 1500
                })
            }else{
                makeButtonArea();
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: '成功加入黑名單',
                    showConfirmButton: false,
                    timer: 1500
                })
            }
        }
    })
}

function deleteBlock() {
    $.get({
        url: `${getContextPath()}/relationship?targetId=${targetId}&action=deleteBlock&memberId=${getLoginId()}`,
        success: function (result, status) {
            if(parseInt(result.status) === 0) {
                console.log(result.status);
                offLoading();
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: '移除失敗',
                    showConfirmButton: false,
                    timer: 1500
                })
            }else{
                makeButtonArea();
                offLoading();
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: '成功自黑名單中移除',
                    showConfirmButton: false,
                    timer: 1500
                })
            }
        }
    })
}
function accpetInvite(){
    $.get({
        url: `${getContextPath()}/relationship?action=addFriend&targetId=${targetId}&memberId=${getLoginId()}`,
        success: function(result) {
            if(parseInt(result.status) === 0) {
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: '你們當不成朋友',
                    showConfirmButton: false,
                    timer: 1500
                })
            }else{
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: '成功變成朋友了',
                    showConfirmButton: false,
                    timer: 1500
                })
            }
        }
    });
}