// $(document).ready(()=>{
//
//
// });
getHottestPost();
getRecentComment();
function getHottestPost() {
    console.log("getHottestPost");
    $.get({
        url: getContextPath() + "/detailPost?action=getHotPost",
        success: function (result, status) {
            let html = "";
            html += `<div className="widget_title">
                    <h3>熱門貼文</h3>
                </div>` ;
            for (let post of result) {
                html += buildRecentPost(post);
            }
            $("#right-sidebar-post").html(html);
        }
    });
}

function buildRecentPost(post) {
    let html = "";
    html += `<div className="post_wrapper">
                    <div className="post_thumb">
                        <a href="http://localhost:8081/cga101g2/detailPost?postId=${post.postId}&action=selectdetail">
                        <img src="${post.pictureVO.previewUrl}" alt=""></a>
                    </div>
                    <div className="post_info">
                        <h4>
                             <a href="http://localhost:8081/cga101g2/detailPost?postId=${post.postId}&action=selectdetail" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">${post.content}</a>
                        </h4>
                        <span>${post.like_count}個讚</span>
                    </div>
                </div>
            </div>`
    return html;
}

function getRecentComment() {
    $.get({
        url: getContextPath() + "/comment?action=getRecentComment",
        success: function (result, status) {
            let html = "";
            html += `<div className="widget_title">
                    <h3>最新留言</h3>
                </div>` ;
            for (let comment of result) {
                html += buildRecentComment(comment);
            }
            $("#new-comment").html(html);
        }
    });
}

function buildRecentComment(comment) {
    let html = "";
    html += `<div className="post_wrapper">
                    <div className="post_thumb">
                        <a href="http://localhost:8081/cga101g2/detailPost?postId=${comment.postId}&action=selectdetail">
                        <img src="${comment.previewUrl}" alt=""></a>
                    </div>
                    <div className="post_info">
                        <h4>
                             <a href="http://localhost:8081/cga101g2/detailPost?postId=${comment.postId}&action=selectdetail" 
                             style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
                              <i>${comment.name}</i> 在你的貼文中留言了</a>
                        </h4>
                        <span>${comment.commentTime}</span>
                    </div>
                </div>
            </div>`
    return html;
}