$(document).ready(() => {
    makeButtonArea();
})

let isFriend;
let isBlock;
let isInviting;
let isInvited;
let isBlocked;
let targetId = $("#memberId").val();
function makeButtonArea() {
    console.log(targetId);
    isOwner = $("#isOwner").val();
    if(targetId <= 0 ||isOwner === 1 ){
        return;
    }
    $.get({
        url: `${getContextPath()}/relationship?memberId=${targetId}&action=getRelation`,
        success: function (result, status) {
            let html = "";
            console.log(result);
            isFriend = parseInt(result.isFriend);
            isBlock = parseInt(result.isBlock);
            isInviting = parseInt(result.isInviting);
            isInvited = parseInt(result.isInvited);
            isBlocked = parseInt(result.isInvited);
            if(isBlock===1||getLoginId()<0){
                return;
            }
            let contextPath = getContextPath();
            if (isFriend === 1) {
                html += `<div class="button1 buttons" id="inviteFreind"><i class="bi bi-check-lg" style="color:green;"></i>朋友</div>`
            } else if (isInvited === 0 && isInviting === 0 && isFriend ===0) {
                html += `<div class="button1 buttons" id="inviteFriend" onclick="inviteFriend()">加為朋友</div>`;
            } else if (isInviting === 1) {
                html += `<div class="button1 buttons" id="acceptInvite" style="border:green 2px solid" onclick="accpetInvite()">接受邀請</div>`;
            } else if (isInvited === 1) {
                html += `<div class="button1 buttons" id="cancelInvite" onclick="cancelInvite()">取消邀請</div>`;
            }
            if (isBlock === 0) {
                html += `<div class="button2 buttons" id="addBlock" onclick="addBlock()" >封鎖</div>`
                html += `<a href="${contextPath}/album?memberId=${targetId}">
                        <div class="button1 buttons">觀看相簿</div>
                    </a>`;
            }if (isBlock === 1) {
                html += `<div class="button2 buttons" id="deleteBlock" onclick="deleteBlock()">取消封鎖</div>`
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
    loading();
    $.get({
        url: getContextPath() + `/relationship?targetId=addFriend&targetId${targetId}`,
        success: function(result) {
            if(parseInt(result.status) === 0) {
                console.log(result.status);
                offLoading();
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: '你們當不成朋友',
                    showConfirmButton: false,
                    timer: 1500
                })
            }else{
                makeButtonArea();
                offLoading();
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
