let isOwner = parseInt($("#isOwner").val());
let memberId = parseInt($("#memberId").val());
let type;

function search(e){
    loading();
    type = e;
    let action;
    if(e===1){
        action = "getFriends";
    }else if(e===2){
        if(isOwner===1) {
            action = "getBlocks";
        }else{
            offLoading();
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: '無資料!'
            });
            return;
        }
    }else if(e===3){
        if(isOwner===1) {
            action = "getInviting";
        }else{
            offLoading();
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: '無資料!'
            });
            return;
        }
    }else if(e===4){
        if(isOwner===1) {
            action = "getInvited";
        }else{
            offLoading();
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: '無資料!'
            });
            return;
        }
    }else{
        offLoading();
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: '無資料!'
        });
        return;
    }
    let html='';
    $.get({
        url: getContextPath() + "/relationship?action=" + action +"&memberId=" + memberId +"",
        processData: false,
        contentType: false,
        success: function(result, status) {
            for(let relation of result) {
                html += makeList(e, relation);
            }
            $("#relation-list").html(html);
            offLoading();

        }
    });
}


function makeList(e,relation){
    let html="";
    html+=`<div class="comments_box col-lg-6" style="margin: 0 auto;text-align:left;">
        <div class="comment_list" style="margin: 20px auto;">
            <div class="comment_thumb" style="width: 100px;height: 100px;">
                <img src="${relation.previewUrl}" alt="" style="height:64%;width: 96%;margin-top: 16px;">
            </div>
            <div class="comment_content" style="width: calc(100% - 100px) !important;margin-left: 100px;height:100px">
                <div class="comment_meta">
                    <h5><a href="#">${relation.name}</a></h5>
                </div>
                <p>${relation.account}</p>`;
    switch (e) {
        case 1:
            let crName = relation.name;
            html+=`<div class="comment_reply" onclick="deleteFriend(${relation.memberId})">
                    <a style="color:white">刪除好友</a>
                </div>
                <div class="comment_reply" style="top: 60px;" onclick="makeChatroom(${relation.memberId},'${crName}')">
                <a style="color:white">開始聊天</a>
                </div>`
            break;
        case 2:
            html+=`<div class="comment_reply" onclick="deleteBlock(${relation.memberId})">
                    <a style="color:white">移除黑名單</a>
                </div>`
            break;
        case 3:
            html+=`<div class="comment_reply" onclick="cancelInvite(${relation.memberId})">
                    <a style="color:white">取消邀請</a>
                </div>`
            break;
        case 4:
            html+=`<div class="comment_reply" onclick="accpetInvite(${relation.memberId})">
                    <a style="color:white">接受申請</a>
                </div>
                <div class="comment_reply" style="top: 60px;" onclick="cancelInvite(${relation.memberId})">
                <a style="color:white">狠心拒絕</a>
                </div>`
            break;
        default:
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: '無此分類!'
            });
            break;

    }
    html+=            `</div>
                    </div>
                </figure>
            </article>
        </div>`
    return html;
}
search(1);

function deleteFriend(i){
    loading();
    $.get({
        url: getContextPath() + "/relationship?action=deleteFriend&targetId=" + i +"&memberId="+memberId ,
        success: function(result, status) {
            search(1);
            offLoading();
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: '刪除朋友成功',
                showConfirmButton: false,
                timer: 1500
            })
        }
    });
}
function makeChatroom(targetId,crName){
    loading();
    let crs = document.getElementsByClassName('indivi-chatroom');
    for(let cr of crs) {
        if (cr.value == targetId) {
            cr.click();
            offLoading();
            return;
        }
    }
    $.get({
        url: getContextPath() + "/chatroom?action=makePrivateChatroom&targetId=" + targetId +"&name="+ crName + "&memberId="+memberId,
        success: function(result, status) {
            offLoading();
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: '已成功建立聊天室',
                showConfirmButton: false,
                timer: 1500
            })
            getPrivateChatroom();
            let crs2 = document.getElementsByClassName('indivi-chatroom');
            crs2[0].click();
        }
    });
    offLoading();
}
function cancelInvite(i){
    loading();
    $.get({
        url: getContextPath() + "/relationship?action=cancelInvite&targetId=" + i +"&memberId="+memberId,
        success: function(result, status) {
            search(2);
            offLoading();
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: '已取消邀請',
                showConfirmButton: false,
                timer: 1500
            })
        }
    });
}
function accpetInvite(i){
    loading();
    $.get({
        url: getContextPath() + "/relationship?action=addFriend&targetId=" + i +"&memberId="+memberId,
        success: function(result) {
            if(parseInt(result.status) === 0) {
                console.log(result.status);
                search(4);
                offLoading();
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: '你們當不成朋友',
                    showConfirmButton: false,
                    timer: 1500
                })
            }else{
                search(1);
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
function deleteBlock(i){
    loading();
    $.get({
        url: getContextPath() + "/relationship?action=deleteBlock&targetId=" + i +"&memberId="+memberId,
        success: function(result, status) {
            search(2);
            offLoading();
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: '已移除黑名單',
                showConfirmButton: false,
                timer: 1500
            })
        }
    });
}