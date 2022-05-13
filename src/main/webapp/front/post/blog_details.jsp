<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<title>每則貼文頁面</title>
<!-- 共用的CSS startr-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->
<meta charset="UTF-8">


<!-- Main Style CSS -->
<%--<link rel="stylesheet" href="other/style copy.css">--%>
<!-- comment css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/comment/comment.css">

<script>
	function getContextPath(){
		return "<%=request.getContextPath()%>";
	}
</script>
</head>

<body>

<!-- 共用的JS -->
<%@include file="/front/layout/commonJS.jsp"%>

	<!-- 共用的header start-->
	<%@include file="/front/layout/header.jsp"%>
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
											src="${postVO.pictureVO.url}" alt="頭像" /></span>
										<%-- <span><a href="#">${postVO.membersVO.name}</a></span> --%>
									</figure>
									
									<!-- 修改鈕 -->
									<%-- <form method="post" action="<%=request.getContextPath()%>/" enctype="multipart/form-data">
									<input type="submit" value="修改">
							     	<input type="hidden" name="postupdateVO"  value="${PostVO.content}">
							     	<input type="hidden" name="action"	value=""></FORM> --%>
							     	
									<h3 class="post_title">Aypi non habent claritatem insitam</h3>
									
									
									<!-- 刪除按鈕 -->
									<button onclick="confirmEvent()">Confirm</button>
									

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
								<div id="carouselExampleControls" class="carousel slide"
									data-ride="carousel">
									<div class="carousel-inner">
								
										<% int count =0;%>
										<c:forEach var="picture" items="${postVO.pictureList}">
										<% if(count==0){%>
											<div class="carousel-item active">
												<img src="${picture.url}" class="d-block w-100" alt="...">
											</div>
										<% 	} %>
										<% if(count>0){%>
											<div class="carousel-item">
												<img src="${picture.url}" class="d-block w-100" alt="...">
											</div>
											<% } %>
										<%count++;%>
										</c:forEach>
									</div>

								</div>

								
								<a class="carousel-control-prev" href="#carouselExampleControls"
									role="button" data-slide="prev"> <span
									class="carousel-control-prev-icon" aria-hidden="true"></span> <span
									class="sr-only">Previous</span>
								</a>
								<a class="carousel-control-next" href="#carouselExampleControls"
									role="button" data-slide="next"> <span
									class="carousel-control-next-icon" aria-hidden="true"></span> <span
									class="sr-only">Next</span>
								</a>
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

					<% if(loginId>0){ %>
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
					</div>
					<button class="button send-comment" onclick="sendComment()">發送</button>
					<% } %>
				</div>
				<!--blog grid area start-->
			</div>
			<!-- 中間欄結束 -->


			<!-- 側邊欄開始 -->
			<div class="col-lg-3 col-md-12">
				<div class="blog_sidebar_widget">
					<div class="widget_list widget_search">
						<div class="widget_title">
							<h3>Search</h3>
						</div>
						<form action="#">
							<input placeholder="Search..." type="text">
							<button type="submit">search</button>
						</form>
					</div>
					<div class="widget_list comments">
						<div class="widget_title">
							<h3>Recent Comments</h3>
						</div>
						<div class="post_wrapper">
							<div class="post_thumb">
								<a href="blog-details.html"><img
									src="assets/img/blog/comment2.png.jpg" alt=""></a>
							</div>
							<div class="post_info">
								<span> <a href="#">demo</a> says:
								</span> <a href="blog-details.html">Quisque semper nunc</a>
							</div>
						</div>
						<div class="post_wrapper">
							<div class="post_thumb">
								<a href="blog-details.html"><img
									src="assets/img/blog/comment2.png.jpg" alt=""></a>
							</div>
							<div class="post_info">
								<span><a href="#">admin</a> says:</span> <a
									href="blog-details.html">Quisque orci porta...</a>
							</div>
						</div>
						<div class="post_wrapper">
							<div class="post_thumb">
								<a href="blog-details.html"><img
									src="assets/img/blog/comment2.png.jpg" alt=""></a>
							</div>
							<div class="post_info">
								<span><a href="#">demo</a> says:</span> <a
									href="blog-details.html">Quisque semper nunc</a>
							</div>
						</div>
						<div class="post_wrapper">
							<div class="post_thumb">
								<a href="blog-details.html"><img
									src="assets/img/blog/comment2.png.jpg" alt=""></a>
							</div>
							<div class="post_info">
								<span><a href="#">admin</a> says:</span> <a
									href="blog-details.html">Quisque semper nunc</a>
							</div>
						</div>
					</div>
					<div class="widget_list widget_post">
						<div class="widget_title">
							<h3>Recent Posts</h3>
						</div>
						<div class="post_wrapper">
							<div class="post_thumb">
								<a href="blog-details.html"><img
									src="assets/img/blog/blog1.jpg" alt=""></a>
							</div>
							<div class="post_info">
								<h4>
									<a href="blog-details.html">Blog image post</a>
								</h4>
								<span>March 16, 2022 </span>
							</div>
						</div>
						<div class="post_wrapper">
							<div class="post_thumb">
								<a href="blog-details.html"><img
									src="assets/img/blog/blog2.jpg" alt=""></a>
							</div>
							<div class="post_info">
								<h4>
									<a href="blog-details.html">Post with Gallery</a>
								</h4>
								<span>March 16, 2022 </span>
							</div>
						</div>
						<div class="post_wrapper">
							<div class="post_thumb">
								<a href="blog-details.html"><img
									src="assets/img/blog/blog3.jpg" alt=""></a>
							</div>
							<div class="post_info">
								<h4>
									<a href="blog-details.html">Post with Audio</a>
								</h4>
								<span>March 16, 2022 </span>
							</div>
						</div>
						<div class="post_wrapper">
							<div class="post_thumb">
								<a href="blog-details.html"><img
									src="assets/img/blog/blog4.jpg" alt=""></a>
							</div>
							<div class="post_info">
								<h4>
									<a href="blog-details.html">Post with Video</a>
								</h4>
								<span>March 16, 2022 </span>
							</div>
						</div>
					</div>
					<div class="widget_list widget_categories">
						<div class="widget_title">
							<h3>Categories</h3>
						</div>
						<ul>
							<li><a href="#">Audio</a></li>
							<li><a href="#">Company</a></li>
							<li><a href="#">Gallery</a></li>
							<li><a href="#">Image</a></li>
							<li><a href="#">Other</a></li>
							<li><a href="#">Travel</a></li>
						</ul>
					</div>
					<div class="widget_list widget_tag">
						<div class="widget_title">
							<h3>Tag products</h3>
						</div>
						<div class="tag_widget">
							<ul>
								<li><a href="#">asian</a></li>
								<li><a href="#">brown</a></li>
								<li><a href="#">euro</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!--blog section area end-->
	<!-- 側邊欄結束 -->
<!-- 個別JS -->
<script src="<%=request.getContextPath()%>/assets/js/comment/comment.js"></script>
</body>

	<script>
		function confirmEvent() {
			if (confirm("確定刪除這篇貼文嗎")＝＝true) {
				alert("刪除!!!");
			}
			else{
				alter("取消");
			}
		}
		
	</script>
</html>