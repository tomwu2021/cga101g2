let socket_post;
let postId = $("#postId").val();

$(document).ready(() => {
    let postInfo = {
        postId: postId,
        memberId: getLoginId()
    }
    socket_post = io('ws://localhost:3000/post', {query: `memberId=${getLoginId()}`});
    socket_post.emit('add-member', postInfo, (data) => {
        console.log(data);
    });
    socket_post.on('broadcast', (comments) => {
        console.log(comments);
        for (let comment of JSON.parse(comments)) {
            if (comment.target_id === 0) {
                onComment(comment);
            } else {
                onReply(comment);
            }
        }
    });
    socket_post.on('update-broadcast', (comment) => {
        console.log(comment);
        onUpdateComment(JSON.parse(comment));
    });
    socket_post.on('delete-broadcast', (comment) => {
            onDelete(JSON.parse(comment))
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
                let commentId = comment.commentId;
                html += buildComment(comment);
                html += `<div id="go-reply-${commentId}" style="display:none;margin-top:10px;margin-bottom: 30px;">
                    <span style="margin-left:100px;">
                        <input id="reply-input-${commentId}" type="text" placeholder="輸入回應..." style="width:80%">
                     </span>
                     <span className="comment_reply" onclick="sendReply(${commentId})">
                    <button type="submit" style="height: 35px;border: 0;background: #222222;
    font-weight: 500;color: white;width: 60px;letter-spacing: 0px;text-align: center;">回應</button>
                    </span>
                    </div>`
            }
            $("#comment-container").html(html);
        }
    });
}

function buildComment(comment) {
    let html = "";
    let isOwner = 0;
    if (parseInt(comment.memberId)===getLoginId()) {
        isOwner = 1;
    }
    html += `<div className="comment_list" class="post-comments" id="${comment.commentId}">
        <div className="comment_thumb" class="comment-headshot-container" >
            <img class="comment-headshot" src="${comment.previewUrl}">
        </div>
        <div className="comment_content" class="comment-info">
            <div className="comment_meta" class="comment-meta">
                <h5 style="margin-left: 10px;margin-top: 10px;">
                    <a style="margin-left: 10px;margin-top: 20px;font-size: 20px;font-weight: 500;font-family: 微軟正黑體;letter-spacing: 5px;">${comment.name}</a>
                </h5>
            </div>
            <span class="comment-button-area reply-button-area">`
        html += `<span className="comment_reply" onclick="showReplyInput(${comment.commentId})">
                    <i class="bi bi-chat-dots comment-button"></i>
                </span>`;
    if (isOwner === 1) {
        html += `<span className="comment_reply" onclick="editComment(${comment.commentId})">
                        <i class="bi bi-pencil comment-button"></i>
                    </span>
                <span className="comment_reply" onclick="deleteComment(${comment.commentId})">
                        <i class="bi bi-trash3 comment-button"></i>
                </span>`;
    }
    html += `</span>
    <p class="comment-content" id="content-${comment.commentId}"  onblur="startUpdateComment(${comment.commentId})">${comment.content}</p>
          <span class="comment-time">${comment.commentTime}</span>
        </div>
        <br>
    </div>
    <div className="comment_list" style="display: none;" id="reply-container-${comment.commentId}" >
    </div>`


    return html;
}

function showReplyInput(commentId) {
    getReply(commentId);
    if(getLoginId()>0) {
        $(`#go-reply-${commentId}`).show();
    }
}

function getReply(commentId) {
    if (!$(`#reply-container-${commentId}`).hasClass('show-reply')) {
        $(`#reply-container-${commentId}`).show();
        $(`#reply-container-${commentId}`).addClass("show-reply");
    } else {
        $(`#reply-container-${commentId}`).hide();
        $(`#go-reply-${commentId}`).hide();
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

            }
            $(`#reply-container-${commentId}`).html(html);
        }
    });
}

function buildReply(reply) {
    let html = "";
    let isOwner = 0;
    if (parseInt(reply.memberId) === getLoginId()) {
        isOwner = 1;
    }
    html += `<div className="comment_list" class="post-comments reply-comment target${reply.targetId}" id="${reply.commentId}">
        <div className="comment_thumb" class="comment-headshot-container" >
            <img class="comment-headshot" src="${reply.previewUrl}">
        </div>
        <div className="comment_content" class="comment-info">
            <div className="comment_meta" class="comment-meta">
                <h5 style="margin-left: 10px;margin-top: 10px;">
                    <a style="margin-left: 10px;margin-top: 20px;font-size: 20px;font-weight: 500;font-family: 微軟正黑體;letter-spacing: 5px;">${reply.name}</a>
                </h5>

            </div>
            <span class="comment-button-area reply-button-area">`
    if (isOwner === 1) {
        html += `  <span className="comment_reply" onclick="editComment(${reply.commentId})">
                        <i class="bi bi-pencil comment-button"></i>
                    </span>
                    <span className="comment_reply" onclick="deleteComment(${reply.commentId})">
                        <i class="bi bi-trash3 comment-button"></i>
                    </span>`;
    }
    html += `</span>
        <p class="comment-content" id="content-${reply.commentId}">${reply.content}</p>
            <span class="comment-time reply-time">${reply.commentTime}</span>
        </div>
        <div className="comment_list" style="display: none;" id="reply-container-${reply.commentId}">
        </div>
    </div>`;
    return html;
}

function sendComment() {
    let postId = parseInt($("#postId").val());
    let memberId = parseInt(getLoginId()) || null;
    let content = $("#review_comment").val().trim();

    console.log(content)
    if (memberId <= 0) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: '請先登入!'
        });
        $("#review_comment").val("");
        return;
    } else if (content === "") {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: '請輸入訊息!'
        });
        $("#review_comment").val("");
        return;
    } else {
        let data = {
            postId: postId,
            memberId: memberId,
            content: content
        }
        // JSON.stringify(data);
        socket_post.emit('send-comment', data);
    }
    $("#review_comment").val("");
}

function onComment(comment) {
    let html = "";
    let isOwner = 0;
    if (parseInt(comment.member_id) === getLoginId()) {
        isOwner = 1;
    }
    html += `<div className="comment_list" class="post-comments" id="${comment.comment_id}">
        <div className="comment_thumb" class="comment-headshot-container" >
            <img class="comment-headshot" src="${comment.preview_url}">
        </div>
        <div className="comment_content" class="comment-info">
            <div className="comment_meta" class="comment-meta">
                <h5 style="margin-left: 10px;margin-top: 10px;">
                    <a style="margin-left: 10px;margin-top: 20px;font-size: 20px;font-weight: 500;font-family: 微軟正黑體;letter-spacing: 5px;">${comment.name}</a>
                </h5>
            </div>
            <span class="comment-button-area reply-button-area">`
        html += `<span className="comment_reply" onclick="showReplyInput(${comment.comment_id})">
                        <i class="bi bi-chat-dots comment-button"></i>
                    </span>`;
    if (isOwner === 1) {
        html += `<span className="comment_reply" onclick="editComment(${comment.comment_id})">
                    <i class="bi bi-pencil comment-button"></i>
                </span>
                <span className="comment_reply" onclick="deleteComment(${comment.comment_id})">
                    <i class="bi bi-trash3 comment-button"></i>
                </span>`;
    }
    html += `</span>
            <p class="comment-content" id="content-${comment.comment_id}"  onblur="startUpdateComment(${comment.comment_id})">${comment.content}</p>
            <span class="comment-time">${comment.comment_time}</span>
        </div>
        <br>
    </div>
    <div className="comment_list" style="display: none;" id="reply-container-${comment.comment_id}">
    </div>`
    $("#comment-container").append(html);
}

function sendReply(commentId) {
    let postId = parseInt($("#postId").val());
    let memberId = parseInt(getLoginId()) || null;
    let content = $(`#reply-input-${commentId}`).val().trim();
    if (content && content !== "") {
        let data = {
            postId: postId,
            memberId: memberId,
            content: content,
            targetId: commentId
        }
        JSON.stringify(data);
        socket_post.emit('send-reply', data);
    }
    $(`#reply-input-${commentId}`).val("");
}

function onReply(reply) {
    let html = "";
    let isOwner = 0;
    if (parseInt(reply.member_id) == getLoginId()) {
        isOwner = 1;
    }
    html += `<div className="comment_list" class="post-comments reply-comment target${reply.target_id}" style="width: 90%;position: relative;left: 88px;" id="${reply.comment_id}">
        <div className="comment_thumb" class="comment-headshot-container" >
            <img class="comment-headshot" src="${reply.preview_url}">
        </div>
        <div className="comment_content" class="comment-info">
            <div className="comment_meta" class="comment-meta">
                <h5 style="margin-left: 10px;margin-top: 10px;">
                    <a style="margin-left: 10px;margin-top: 20px;font-size: 20px;font-weight: 500;font-family: 微軟正黑體;letter-spacing: 5px;">${reply.name}</a>
                </h5>
            </div>
            <span class="comment-button-area reply-button-area">`
    if (isOwner === 1) {
        html += `<span className="comment_reply" onclick="editComment(${reply.comment_id})">
                        <i class="bi bi-pencil comment-button"></i>
                    </span>
                  <span className="comment_reply" onclick="deleteComment(${reply.comment_id})">
                        <i class="bi bi-trash3 comment-button"></i>
                  </span>`;
    }
    html += ` </span>
            <p class="comment-content" id="content-${reply.comment_id}" onblur="startUpdateComment(${reply.comment_id})">${reply.content}</p>
            <span class="comment-time reply-time">${reply.comment_time}</span>
        </div>
    </div>`;
    $(`#reply-container-${reply.target_id}`).append(html);
}
let oldContent;
function editComment(commentId) {
    oldContent = $(`#content-${commentId}`).text();
    $(`#content-${commentId}`).attr('contenteditable', true);
    $(`#content-${commentId}`).focus();
    $(`#content-${commentId}`).css('color','red');

}

function startUpdateComment(commentId) {
    $(`#content-${commentId}`).css('color','black');
    $(`#content-${commentId}`).attr('contenteditable', false);
    let newContent = $(`#content-${commentId}`).text();
    if(newContent !== oldContent) {
        let data = {
            commentId: commentId,
            content: newContent,
            memberId: getLoginId(),
            postId: postId
        }
        JSON.stringify(data);
        socket_post.emit('update-comment', data);
    }
}

function onUpdateComment(comment) {
    console.log(comment.content);
    let updatedContent = comment.content;
    $(`#content-${comment.commentId}`).attr('contenteditable',true);
    $(`#content-${comment.commentId}`).text('');
    $(`#content-${comment.commentId}`).text(`${updatedContent}`);
}

function deleteComment(commentId) {
    let data = {
        commentId: commentId,
        postId: postId
    }
    JSON.stringify(data);
    socket_post.emit('delete-comment', data);
}

function onDelete(comment) {
    $(`#${comment.commentId}`).remove();
    $(`.target${comment.commentId}`).remove();
    $(`#go-reply-${comment.commentId}`).remove();
}
