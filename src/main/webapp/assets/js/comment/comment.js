let socket_post;
let postId = $("#postId").val();

$(document).ready(() => {
    let postInfo = {
        postId:postId,
        memberId: getLoginId()
    }
    socket_post = io('ws://localhost:3000/post', {query:`memberId=${getLoginId()}`});
    socket_post.emit('add-member',postInfo,(data)=>{
        console.log(data);
    });
    socket_post.on('broadcast', (comment) => {
        console.log('broadcastComment');
        console.log(JSON.parse(comment));
        onComment(JSON.parse(comment));
    });
    getComment();
})


function getComment() {
    $.get({
        url: getContextPath() + "/comment?action=&postId=" + postId,
        success: function (result, status) {
            let html = "";
            html += `<h3 style="font-size: 25px;font-family:微軟正黑體, sans-serif;text-align: left;letter-spacing: 10px;margin: 20px 0;">回應</h3>`;
            for (let comment of result) {
                html += buildComment(comment);
            }
            $("#comment-container").html(html);
        }
    });
}

function buildComment(comment) {
    let html = "";
    html += `<div className="comment_list" class="post-comments" id="${comment.commentId}">
        <div className="comment_thumb" class="comment-headshot-container" >
            <img class="comment-headshot" src="${comment.previewUrl}">
        </div>
        <div className="comment_content" class="comment-info">
            <div className="comment_meta" class="comment-meta">
                <h5 style="margin-left: 10px;margin-top: 10px;">
                    <a style="margin-left: 10px;margin-top: 20px;font-size: 20px;font-weight: 500;font-family: 微軟正黑體;letter-spacing: 5px;">${comment.name}</a>
                </h5>

            </div>`

    if (getLoginId() > 0) {
        html += `<span class="comment-button-area">
                    <span className="comment_reply" onclick="showReplyInput(${comment.commentId})">
                        <i class="bi bi-chat-dots comment-button"></i>
                    </span>
                    <span className="comment_reply" onclick="getReply(${comment.commentId})">
                        <i class="bi bi-eye comment-button"></i>
                    </span>
                </span>`;
    }else{
        html += `<span class="comment-button-area" style="left: 453px;">
                    <span className="comment_reply" onclick="getReply(${comment.commentId})">
                        <i class="bi bi-eye comment-button"></i>
                    </span>
                </span>`;
    }
    html += `
            <p class="comment-content">${comment.content}</p>
            <span class="comment-time">${comment.commentTime}</span>
        </div>
        <br>
    </div>
    <div className="comment_list" style="display: none;" id="reply-container-${comment.commentId}">
    </div>`

    return html;
}

function showReplyInput(commentId) {
    $(`#reply-input-${commentId}`).show();

}

function getReply(commentId) {
    if (!$(`#reply-container-${commentId}`).hasClass('show-reply')) {
        $(`#reply-container-${commentId}`).show();
        $(`#reply-container-${commentId}`).addClass("show-reply");
    } else {
        $(`#reply-container-${commentId}`).hide();
        $(`#reply-container-${commentId}`).removeClass("show-reply");
        return;
    }
    $.get({
        url: getContextPath() + "/comment?commentId=" + commentId + "&action=getReplyByCommentId",
        success: function (result, status) {
            let html = "";
            let postId;
            for (let reply of result) {
                html += buildReply(reply);
                postId = reply.postId;
            }
            if (getLoginId() > 0) {
                html += `<div style="margin-top:10px;margin-bottom: 30px;">
                    <span style="margin-left:100px;">
                        <input id="reply-input-${commentId}" type="text" placeholder="輸入回應..." style="width:80%">
                     </span>
                     <span className="comment_reply" onclick="commitReply(${postId},${commentId})">
                    <button type="submit" style="height: 35px;border: 0;background: #222222;
    font-weight: 500;color: white;width: 60px;letter-spacing: 0px;text-align: center;">回應</button>
                    </span>
                    </div>`
            }
            $(`#reply-container-${commentId}`).html(html);
        }
    });
}

function buildReply(reply) {
    let html = "";
    html += `<div className="comment_list" class="post-comments reply-comment" id="${reply.commentId}">
        <div className="comment_thumb" class="comment-headshot-container" >
            <img class="comment-headshot" src="${reply.previewUrl}">
        </div>
        <div className="comment_content" class="comment-info">
            <div className="comment_meta" class="comment-meta">
                <h5 style="margin-left: 10px;margin-top: 10px;">
                    <a style="margin-left: 10px;margin-top: 20px;font-size: 20px;font-weight: 500;font-family: 微軟正黑體;letter-spacing: 5px;">${reply.name}</a>
                </h5>

            </div>`
    if (getLoginId() > 0) {
        html += `<span class="comment-button-area reply-button-area">
                <span className="comment_reply" onclick="showReplyInput(${reply.commentId})">
                    <i class="bi bi-chat-dots comment-button"></i>
                </span>
            </span>`;
    }
    html += `<p class="comment-content">${reply.content}</p>
            <span class="comment-time reply-time">${reply.commentTime}</span>
        </div>
        <div className="comment_list" style="display: none;" id="reply-container-${reply.commentId}">
        </div>
    </div>`;
    return html;
}

function sendComment(){
    let postId = parseInt($("#postId").val());
    let memberId = parseInt(getLoginId())||null;
    let content = $("#review_comment").val().trim();

    console.log(content)
    if(memberId===null){
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: '請先登入!'
        });
        $("#review_comment").val("");
        return;
    }else if(content!==""){
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: '請輸入訊息!'
        });
        $("#review_comment").val("");
        return;
    } else{
        let data = {
            postId : postId,
            memberId : memberId,
            content : content
        }
        // JSON.stringify(data);
        socket_post.emit('send-comment', data);
    }
    $("#review_comment").val("");
}

function onComment(comment){
    let html="";
    html += buildComment(comment);
    $("#comment-container").append(html);
}