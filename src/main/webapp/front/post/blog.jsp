<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>
<%@ page import="java.util.List"%>
<jsp:useBean id="postlist" scope="request" type="java.util.List<PostVO>" />
<!-- 於EL此行可省略 -->

<!DOCTYPE html>
<html>
<head>

<title>社群主頁</title>
<!-- 共用的CSS startr-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<meta charset="UTF-8">

<!-- 更改的CSS start-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/post/css/blog.css">
<!-- 更改的CSS end-->

<script>
    function getContextPath(){
     return "<%=request.getContextPath()%>";
 }
</script>


</head>
<body>

	<!-- 共用的header start-->
	<%@include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->

	<!--! ========內容======== -->

	<!--offcanvas menu area start-->
	<div class="off_canvars_overlay"></div>

	<!--blog area start-->
	<div class="blog_page_section blog_fullwidth mt-100">
		<div class="container">
			<div class="row">

				<div class="col-lg-9 col-md-12">
					<div class="blog_wrapper">
						<%@ include file="page/page1.file"%>
						<c:forEach var="postVO" items="${postlist}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<!-- 範圍 -->
							<article class="single_blog">

								<!-- 從這改 -->
								<!-- <figure>每則整個貼文 -->

								<figure>
									<!-- 圖 -->

									<!-------------------------------- 頭像跟名字 -------------------------------->

									<div id="demo_border" style="position: relative;">

										<div id="image_photo">

											<a
												href="<%=request.getContextPath()%>/PersonPost?memberId=${postVO.memberId}&action=getOne_For_Display"><img
												src="${postVO.pictureVO.url}"></a>

										</div>

										<a
											href="<%=request.getContextPath()%>/PersonPost?memberId=${postVO.memberId}&action=getOne_For_Display"><div
												id="img_text">${postVO.membersVO.name}</div></a>

									</div>

									<div class="blog_meta"></div>

									<!-------------------------------- 貼文圖片 -------------------------------->

									<div class="blog_thumb">

										<a
											href="<%=request.getContextPath()%>/detailPost?postId=${postVO.postId}&memberId=${postVO.memberId}&action=selectdetail"><img
											src="${postVO.pictureVO2.url}" alt=""></a>

									</div>

									<!-- 整個文 -->
									<figcaption class="blog_content">


										<!-- 貼文發布時間 -->
										<div class="blog_meta">
											<p>
												<!--                                     會員是否有點讚該文章的判斷 -->
												<span class="glyphicon glyphicon-usd text-danger"> <c:if
														test="${sessionMemberId == null || postVO.likelistVO.memberId != sessionMemberId }">
														<input type="hidden" name="likelistStatus" value="0">
														<i id="likelist${postVO.postId}"
															class="bi bi-suit-heart fa-2x" style="cursor: pointer;"></i>
													</c:if> <c:if
														test="${sessionMemberId != null && postVO.likelistVO.memberId == sessionMemberId }">
														<input type="hidden" name="likelistStatus" value="1">
														<i id="likelist${postVO.postId}"
															class="bi bi-suit-heart-fill fa-2x"
															style="cursor: pointer;"></i>
													</c:if>
												</span> <a href="#">&ensp;${postVO.likeCount}</a> <span
													style="font-size: 16px; color: #eca2a2;">個讚</span> &ensp; <a
													href="#">${postVO.createTime}</a>
												<!-- 									會員是否有點讚該文章的判斷 -->
										</div>

										<!-- 貼文內容 -->
										<p
											style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"
											class="post_desc">${postVO.content}</p>


										<!-- 按鈕 -->
										<footer class="btn_more">
											<a
												href="<%=request.getContextPath()%>/detailPost?postId=${postVO.postId}&memberId=${postVO.memberId}&action=selectdetail">
												Read more</a>
										</footer>


									</figcaption>
								</figure>

							</article>
						</c:forEach>
						<%@ include file="page/page2.file"%>
					</div>
				</div>


				<!-- 側邊欄範圍 -->

				<!-- 側邊欄開始 -->
				<%@include file="/front/post/rightSideBar.jsp"%>
				<!-- 側邊欄結束 -->

			</div>
		</div>
	</div>
	<!--blog area end-->




	<!-- 共通的footer start-->
	<%@include file="/front/layout/footer.jsp"%>
	<!-- 共通的footer end-->


	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 共用的JS -->


	<!-- 點讚用的JS 完成後記得移入commonJS-->
	<script src="<%=request.getContextPath()%>/front/post/likelist.js"> 
	</script>
	<!-- 點讚用的JS -->
	<script
		src="<%=request.getContextPath()%>/assets/js/post/rightSideBar.js">
		</script>
</body>
</html>