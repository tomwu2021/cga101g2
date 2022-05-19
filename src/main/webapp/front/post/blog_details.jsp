<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>每則貼文頁面</title>
<!-- 共用的CSS startr-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/register.css">
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- Favicon -->
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/assets/img/favicon.ico">

<!-- Main Style CSS -->
<%--<link rel="stylesheet" href="other/style copy.css">--%>
<!-- comment css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/comment/comment.css">

<!-- 照片輪流撥放 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/flexslider/2.7.0/flexslider.css">

<script>
        function getContextPath() {
            return "<%=request.getContextPath()%>
	";
	}
</script>

<style>
.opoups_height_width {
	display: inline-block;
	padding: 6px 12px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: 400;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	-ms-touch-action: manipulation;
	touch-action: manipulation;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
	text-decoration: none;
	color: #333;
	background-color: #ffcccb;
	border-color: #ccc;
}

#demo_border {
	position: relative;
	width: 200px;
	height: 0px;
}

#image_photo {
	　　position: absolute;
	background-image: url("");
	background-repeat: no-repeat;
	border-radius: 50%;
	overflow: hidden;
	width: 75px;
	height: 75px;
	transform: translateY(5px);
}

img {
	max-width: 100%;
	height: 100%;
}

.blog_details .blog_meta {
	margin-bottom: 0;
	transform: translateY(-40px) translateX(120px);
}

a, button {
	color: #836c71;
	line-height: inherit;
	text-decoration: none;
	cursor: pointer;
	font-size: 16px;
}
</style>
</head>

<body>

	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 共用的header start-->
	<%@include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->
	<input type="hidden" id="postId" value="${postVO.postId}">
	<input type="hidden" id="memberId" value="${postVO.memberId}">
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

							<!-- 圖上方標題 放頭像 姓名 讚數 創貼文時間-->
							<div class="post_header">
								<figure style="width: 100px; height: 100px;">
									<div id="demo_border" style="position: relative;">
										<div id="image_photo">
											<img style="height: 100%;" src="${postVO.pictureVO.url}">
										</div>
										<div class="blog_meta" style="width: 650px;">

											<a
												href="<%=request.getContextPath()%>/PersonPost?memberId=${postVO.memberId}&action=getOne_For_Display">${postVO.membersVO.name}</a>
											<!-- 檢舉功能 -->
											<c:if test="${memberId != isOwner}">
												<%if (loginId>0){%>>
													<div id="popup22" class="opoups_height_width"
														style="float: right;">檢舉</div>
												<%} %>>
											</c:if>

										</div>
									</div>

								</figure>

								<div id="box2">
									<h2
										style="margin-top: 8px; margin-left: 15px; margin-top: 20px; font-weight: bold;">
										檢舉內容
										<div id="viewForgotPassword"
											style="display: inline; color: red; font-weight: bold;"></div>
									</h2>

									<textarea type="text" name="forgotPassword" id="forgotPassword"
										placeholder="請輸入檢舉內容" rows="8" cols="50" /></textarea>
									<input type="button" value="確認送出" id="report"
										class="floating-button0"
										style="font-size: 16px; background-color: orange" /> <span
										id="closeBtn2">關閉</span>
								</div>

								<c:if test="${memberId == isOwner}">
									<!-- 修改按鈕 -->
									<button id="updatePost${postVO.postId}"
										class="btn btn btn-secondary btn-sm"
										style="transform: translateX(710px) translateY(-80px);">修改</button>

								</c:if>



							</div>
							<!-- 放圖 -->
							<%-- <div class="blog_thumb">
                               <a href="#"><img src="${postVO.pictureVO.url}" alt=""></a>
                           </div> --%>
							<!--                             <div id="carouselExampleControls" class="carousel slide" data-ride="carousel" style="transform: translateY(-40px);"> -->

							<!--                                 <div class="carousel-inner"> -->

							<%--                                     <% int count = 0;%> --%>
							<%--                                     <c:forEach var="picture" items="${postVO.pictureList}"> --%>
							<%--                                         <% if (count == 0) {%> --%>
							<!--                                         <div class="carousel-item active"> -->
							<%--                                             <img src="${picture.url}" class="d-block w-100" alt="..."> --%>
							<!--                                         </div> -->
							<%--                                         <% } %> --%>
							<%--                                         <% if (count > 0) {%> --%>
							<!--                                         <div class="carousel-item"> -->
							<%--                                             <img src="${picture.url}" class="d-block w-100" alt="..."> --%>
							<!--                                         </div> -->
							<%--                                         <% } %> --%>
							<%--                                         <%count++;%> --%>

							<%--                                     </c:forEach> --%>

							<!--                                 </div> -->

							<!--                                 <button class="carousel-control-prev" type="button" -->
							<!--                                         data-bs-target="#carouselExampleControls" data-bs-slide="prev"> -->
							<!--                                     <span class="carousel-control-prev-icon" aria-hidden="true"></span> -->
							<!--                                     <span class="visually-hidden">Previous</span> -->
							<!--                                 </button> -->

							<!--                                 <button class="carousel-control-next" type="button" -->
							<!--                                         data-bs-target="#carouselExampleControls" data-bs-slide="next"> -->
							<!--                                     <span class="carousel-control-next-icon" aria-hidden="true"></span> -->
							<!--                                     <span class="visually-hidden">Next</span> -->
							<!--                                 </button> -->
							<!--                             </div> -->

							<!-- 靜宜新版的放圖 開始-->
							<c:if test="${postVO.pictureList.size() != 0 }">
								<div class="flexslider" style="width: 700px; margin-left: 80px;">
									<ul class="slides">
										<c:forEach var="pictureVO" items="${postVO.pictureList}">
											<li><img src="${pictureVO.url}" alt="" width="350"
												height="250"></li>
										</c:forEach>
									</ul>
								</div>
							</c:if>
							<!-- 靜宜新版的放圖 結束-->
							<figcaption class="blog_content">

								<div class="post_content">

									<!-- 貼文內容 -->

									<p style="color: #836c71;">
										<a>${postVO.likeCount}</a>個讚
									</p>
									<blockquote>
										<p style="overflow: hidden; word-break: break-all">${postVO.content}</p>

									</blockquote>

								</div>
								<div class="entry_content">
									<div class="post_meta">
										<!-- <span>Tags: </span> -->
										<span><a>${postVO.createTime}</a></span>
										<!-- <span><a href="#">, t-shirt</a></span>
                                                    <span><a href="#">, white</a></span> -->
									</div>
								</div>
							</figcaption>
							</figure>
						</article>

						<!-- 已刪除最近貼文區塊 -->
						<!-- 留言區開始 -->
						<div class="comments_box" id="comment-container" class="col-12">

						</div>

						<%
						if (loginId > 0) {
						%>
						<div class="comments_form">
							<h3 id="comment-for">
								給 <i>${postVO.membersVO.name}</i> 留個言吧！
							</h3>
							<form>
								<div class="row">
									<div class="col-12">
										<label for="review_comment">留言：</label>
										<textarea name="comment" id="review_comment"></textarea>
									</div>
								</div>
							</form>
							<div class="button send-comment" onclick="sendComment()"
								style="margin-left: 604px; background-color: black; margin-top: 15px;">發送
							</div>
						</div>

						<%
						}
						%>
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

	<script
		src="<%=request.getContextPath()%>/assets/js/comment/comment.js"></script>
	<script src="<%=request.getContextPath()%>/front/post/js/update.js"></script>
	<script src="<%=request.getContextPath()%>/front/post/js/report.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/post/rightSideBar.js"></script>


	<!-- 照片輪流撥放 -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/flexslider/2.7.0/jquery.flexslider.js"></script>

	<script type="text/javascript">
		$(function() {
			$(".flexslider").flexslider({
				slideshowSpeed : 5000, //展示时间间隔ms
				animationSpeed : 500, //滚动时间ms
				touch : true
			//是否支持触屏滑动
			});
		});
	</script>
</body>


</html>