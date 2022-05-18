<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product_img.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.group_order.model.*"%>


<html>

<head>
<title>團購商品總覽</title>
<!-- 共用的CSS startr-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<script>
        function getContextPath() {
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

	<!-- 共用的CartAndWishlist-->
	<%@include file="/front/layout/commonCartAndWishlist.jsp"%>
	<!-- 共用的CartAndWishlist-->

	<!--! ========內容======== -->
	<!--breadcrumbs area start-->
	<div class="breadcrumbs_area">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="breadcrumb_content">
						<h3>團購專區</h3>
						<ul>
							<li><a href="index.html">寵物商城</a></li>
							<li>團購專區</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--breadcrumbs area end-->
	<!--shop  area start-->
	<div class="shop_area shop_reverse mt-100 mb-100">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-12">
					<!--sidebar widget start ========================================= -->
					<aside class="sidebar_widget">
						<div class="widget_inner">

							<div class="widget_list widget_color">
								<h3>
									<a
										href="<%=request.getContextPath()%>/groupShop">我要開團</a>
								</h3>
							</div>

							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/member/groupOrder.do"
								name="join">
								<div class="widget_list widget_color">
									<h3>
										<a href="javascript:document.join.submit();">我要跟團</a>
									</h3>
								</div>
							</FORM>
							<div class="widget_list widget_color"></div>
							<div class="widget_list widget_color"></div>
							<div class="widget_list widget_color">
								<!-- 結束 -->
							</div>
							<div class="widget_list shopside_banner">
								<div class="banner_thumb">
									<a href="#"><img src="" alt="" /></a>
								</div>
							</div>
						</div>
					</aside>
					<!--sidebar widget end-->
				</div>

				<div class="col-lg-9 col-md-12">
					<!--shop wrapper start 右邊菜單內容展示 content是切換點不可更動========================-->
					<div id="content">
						<!--shop toolbar start-->
						<div class="shop_toolbar_wrapper">
							<div class="form-select" style="display: flex;">
								<form action="#" style="display: block;">
									<select id="pageSize">
										<option value=12>每頁9筆</option>
										<option value=24>每頁18筆</option>
										<option value=36>每頁27筆</option>
									</select>
								</form>
							</div>
							<div class="form-select" style="display: flex;">
								<form action="#" style="display: block;">
									<select id="sort">
										<option value="">按開團時間排序</option>
										<option value="DESC">由新到舊</option>
										<option value="ASC">由舊到新</option>
									</select>
								</form>
							</div>
	
							<div class="form-select" style="display: flex;">
								<form action="#" style="display: block;">
									<select id="status">
										<option value="">結單類型</option>
										<option value="1">時間</option>
										<option value="2">數量</option>
									</select>
								</form>								
							</div>
							<input placeholder="輸入關鍵字搜尋..." type="text" id="productName">
							<div class="page_amount" style="align-self: center">
								<p></p>
							</div>
						</div>
						<!--shop toolbar end-->
						<div class="row shop_wrapper" id="groupShopProduct">						
							<Form
									ACTION="<%=request.getContextPath()%>/member/groupOrder.do"
								Method="Post" name="GO${groupOrderVO.groupOrderId}">
									<input type="hidden" name="action" value="toJoinDetial">
									<input type="hidden" name="groupOrderId"
								value="${groupOrderVO.groupOrderId}">
								</Form>
						</div>
						<%@include file="/front/layout/searchPage.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--shop  area end-->

	<!-- 共通的footer start-->
	<%@include file="/front/layout/footer.jsp"%>
	<!-- 共通的footer end-->
	<!-- 額外添加的JS -->
	<script
		src="<%=request.getContextPath()%>/assets/shop/leftnav/leftnavlink.js">
	</script>
	<script
			src="<%=request.getContextPath()%>/assets/js/groupShop/joinGroupShop.js">
	</script>
</body>

</html>