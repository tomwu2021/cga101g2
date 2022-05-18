<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>每則貼文頁面</title>
    <!-- 共用的CSS startr-->
    <%@include file="/front/layout/commonCSS.jsp" %>
    <!-- 共用的CSS end-->

  <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon"
          href="<%=request.getContextPath()%>/assets/img/favicon.ico">

    <!-- Main Style CSS -->
    <%--<link rel="stylesheet" href="other/style copy.css">--%>
    <!-- comment css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/comment/comment.css">

    <script>
        function getContextPath() {
            return "<%=request.getContextPath()%>";
        }
    </script>
</head>

<body>

<!-- 共用的JS -->
<%@include file="/front/layout/commonJS.jsp" %>
<!-- 共用的header start-->
<%@include file="/front/layout/header.jsp" %>
<!-- 共用的header end-->
<input type="hidden" id="postId" value="${postVO.postId}">
<!--! ========內容======== -->
<!--blog body area start-->
<div class="blog_details">
    <div class="container">
        <div class="row">

            <!-- 中間欄開始 -->
            <div class="col-lg-9 col-md-12">
                <!--blog grid area start-->


                <div class="blog_wrapper blog_wrapper_details">
                    <article class="single_blog">
                        <figure>
                            <!-- 圖上方標題 放頭像 姓名 讚數 創貼文時間-->
                            <div class="post_header">
                                <figure style="width: 60px; height: 60px;">
										<span><img class="img-responsive "
                                                   src="${postVO.pictureVO.url}" alt="頭像"/></span>
                                    <%-- <span><a href="#">${postVO.membersVO.name}</a></span> --%>
                                </figure>

                                <h3 class="post_title">Aypi non habent claritatem insitam</h3>
                                
								<c:if test="${memberId == isOwner}">
                                <!-- 修改按鈕 -->
                                <button id="updatePost${postVO.postId}">Confirm</button>
								</c:if>

                                <div class="blog_meta">
                                    <p>
                                        Posted by : <a href="#">${postVO.membersVO.name}</a>
                                    </p>
                                </div>

                            </div>
                            <!-- 放圖 -->
                            <%-- <div class="blog_thumb">
                               <a href="#"><img src="${postVO.pictureVO.url}" alt=""></a>
                           </div> --%>
                            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">

                                <div class="carousel-inner">

                                    <% int count = 0;%>
                                    <c:forEach var="picture" items="${postVO.pictureList}">
                                        <% if (count == 0) {%>
                                        <div class="carousel-item active">
                                            <img src="${picture.url}" class="d-block w-100" alt="...">
                                        </div>
                                        <% } %>
                                        <% if (count > 0) {%>
                                        <div class="carousel-item">
                                            <img src="${picture.url}" class="d-block w-100" alt="...">
                                        </div>
                                        <% } %>
                                        <%count++;%>

                                    </c:forEach>

                                </div>

                                <button class="carousel-control-prev" type="button"
                                        data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>

                                <button class="carousel-control-next" type="button"
                                        data-bs-target="#carouselExampleControls" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>


                            <figcaption class="blog_content">

                                <div class="post_content">

                                    <!-- 貼文內容 -->

                                    <p>
                                        <a href="#">${postVO.likeCount}</a>個讚
                                    </p>
                                    <blockquote>
                                        <p style="overflow: hidden; word-break: break-all ">${postVO.content}</p>

                                    </blockquote>

                                </div>
                                <div class="entry_content">
                                    <div class="post_meta">
                                        <!-- <span>Tags: </span> -->
                                        <span><a href="#">${postVO.createTime}</a></span>
                                        <!-- <span><a href="#">, t-shirt</a></span>
                                                    <span><a href="#">, white</a></span> -->
                                    </div>

                                    <div class="social_sharing">
                                        <p>share this post:</p>
                                        <ul>
                                            <li><a href="#" title="facebook"><i
                                                    class="fa fa-facebook"></i></a></li>
                                            <li><a href="#" title="twitter"><i
                                                    class="fa fa-twitter"></i></a></li>
                                            <li><a href="#" title="pinterest"><i
                                                    class="fa fa-pinterest"></i></a></li>
                                            <li><a href="#" title="google+"><i
                                                    class="fa fa-google-plus"></i></a></li>
                                            <li><a href="#" title="linkedin"><i
                                                    class="fa fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </figcaption>
                        </figure>
                    </article>

                    <!-- 已刪除最近貼文區塊 -->
                    <!-- 留言區開始 -->
                    <div class="comments_box" id="comment-container" class="col-12">

                    </div>

                    <% if (loginId > 0) { %>
                    <div class="comments_form">
                        <h3 id="comment-for">給　<i>${postVO.membersVO.name}</i>　留個言吧！</h3>
                        <form>
                            <div class="row">
                                <div class="col-12">
                                    <label for="review_comment">留言：</label>
                                    <textarea name="comment" id="review_comment"></textarea>
                                </div>
                            </div>
                        </form>
                        <div class="button send-comment" onclick="sendComment()"
                             style="margin-left: 604px;background-color: black;margin-top: 15px;">發送
                        </div>
                    </div>

                    <% } %>
                </div>
                <!--blog grid area start-->
            </div>
            <!-- 中間欄結束 -->


            <!-- 側邊欄開始 -->
            <%@include file="/front/post/rightSideBar.jsp"%>
        </div>
    </div>
</div>

<!--blog section area end-->
<!-- 側邊欄結束 -->
<!-- 個別JS -->

<script src="<%=request.getContextPath()%>/assets/js/comment/comment.js"></script>
<script src="<%=request.getContextPath()%>/front/post/update.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/post/rightSideBar.js"></script>
</body>


</html>