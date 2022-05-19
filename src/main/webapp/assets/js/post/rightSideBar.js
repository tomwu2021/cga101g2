$(document).ready(() => {
    getRecentComment();
    getHottestPost();
    // getRecentFriend();
})

function getHottestPost() {
    console.log("getHottestPost");
    $.get({
        url: getContextPath() + "/detailPost?action=getHotPost",
        success: function (result, status) {


            let html = "";
            html += `<div className="widget_title">
                                <h3>熱門貼文</h3>
                            </div>`;
            let i = 1;
            console.log(result);
            for (let post of result) {
                html += buildRecentPost(post);
            }
            $("#right-sidebar-post").html(html);
        }
    });
}

function buildRecentPost(post) {
    let html = "";
    let postId = post.postId;
    let postUrl = post.pictureVO.url;
    let likeCount = post.likeCount;
    let content = post.content;
    let memberId = post.memberId;
 
    html += `<div className="post_wrapper">
                    <span className="post_thumb" style="display:inline-block;margin-right:5px">
                        <a href="${getContextPath()}/detailPost?memberId=${memberId}&postId=${postId}&action=selectdetail">
                        <img style="width: 50px;height:50px;border-radius: 5px;" src="${postUrl}" alt=""></a>
                    </span>
                    <span className="post_info" style="display:inline-block;position:relative;top:15px;">
                        <h4>
                             <a href="${getContextPath()}/detailPost?memberId=${memberId}&postId=${postId}&action=selectdetail" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">${content}</a>
                        </h4>
                        <span>${likeCount}個讚</span>
                    </span>
                </div>
            </div>`;
    return html;
}

function getRecentComment() {
    if (getLoginId() < 0) {
        return;
    }
    $.get({
        url: getContextPath() + "/comment?action=getRecentComment",
        success: function (result, status) {

            console.log(result);
            let html = "";
            html += `<div className="widget_title">
                    <h3 style="margin-top: 24px;font-size: 28px;">最新留言</h3>
                    </div>`;
            let i = 0;
            for (let comment of result) {
                console.log("getRecentComment");
                if (comment.memberId !== getLoginId()) {
                    html += buildRecentComment(comment);
                    i++;
                }
            }
            if (i === 0) {
                html += "<p>目前沒有新的留言</p>";
            }
            $("#new-post-comment").html(html);
        }
    });
}

function buildRecentComment(comment) {
    let html = "";
    let postId = comment.postId;
    let commentPreviewUrl = comment.previewUrl;
    let name = comment.name;
    let commentTime = comment.commentTime;
    let memberId = comment.memberId;
    let postMemberId = comment.postMemberId
    html += `<div className="post_wrapper" style="margin-bottom:10px;margin-top:10px">
                    <div className="post_thumb" style="margin-right:5px;display:block;">
                        <a href="${getContextPath()}/detailPost?memberId=${postMemberId}&postId=${postId}&action=selectdetail">
                        <img src="${commentPreviewUrl}" style="border-radius: 50%;width: 60px;height: 60px;" alt=""></a>
                    </div>
                    <div classname="post_info" style="margin-top:5px;display:block;position: relative;top: 10px;">
                        <h5 style="max-width: 200px;text-overflow: ellipsis;;white-space: nowrap;">
                             <a href="${getContextPath()}/detailPost?memberId=${postMemberId}&postId=${postId}&action=selectdetail"
                             style="overflow: hidden;font-size:14px;position:relative;top:5px">
                              <i>${name}</i> 在你的貼文中留言了</a>
                        </h5>
                        <span>${commentTime}</span>
                    </div>
                </div>
            </div>`
    return html;
}

// function getRecentFriend() {
//     if (getLoginId() < 0) {
//         return;
//     }
//     $.get({
//         url: getContextPath() + "/relationship?action=findRecentFriend&memberId=" + getLoginId(),
//         success: function (result, status) {
//             let html = "";
//             html += `<div className="widget_title">
//                         <h3 style="margin-top: 24px;font-size: 28px;">最新留言</h3>
//                     </div>`;
//             for (let friend of result) {
//                 html += buildRecentFriend(friend);
//             }
//             $("#recent-chat-friend").html(html);
//         }
//     });
// }
//
// function buildRecentFriend(friend) {
//     let html = "";
//     let memberId = friend.memberId;
//     let previewUrl = friend.previewUrl;
//     let name = friend.name;
//     let rankName = friend.rankName;
//     html += ` <div className="post_wrapper" onclick="openNewFriendChatroom(${memberId})">
//                     <span className="post_thumb" style="display:inline-block;margin-right:5px">
//                         <a>
//                         <img src="${previewUrl}" style="border-radius: 50%;width: 60px;height: 60px;" alt=""></a>
//                     </span>
//                     <span className="post_info" style="margin-top:5px;display:inline-block;">
//                         <h4>
//                              <a style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;position:relative;top:5px">
//                               ${name}</a>
//                         </h4>
//                         <span>${rankName}</span>
//                     </span>
//                 </div>
//             </div>`
//     return html;
// }
//
// function openNewFriendChatroom(targetId) {
//     let crs = document.getElementsByClassName('indivi-chatroom');
//     for (let cr of crs) {
//         if (cr.value == targetId) {
//             cr.click();
//             return;
//         }
//     }
// }