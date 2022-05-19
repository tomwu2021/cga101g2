<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.picture.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<style>
.product_variant:nth-of-type(1){
    width: fit-content !important;
    display: block !important;
    letter-spacing: 1px;
    color: #333;
    font-family: 'Microsoft JhengHei';
    font-weight: bold;
    padding: 8px;
}
</style>
<head>
<title>商品細節</title>
<!-- 共用的CSS startr-->
<%@include file="/front/layout/commonCSS.jsp"%>

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

	<!--breadcrumbs area start-->
	<div class="breadcrumbs_area">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="breadcrumb_content">
						<h3>團購商品專區</h3>
						<ul>
							<li><a href="index.html">寵物商城</a></li>
							<li>我要參團商品細節</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--breadcrumbs area end-->

	<!--! ========內容======== -->
	<!--product details start-->
	<div class="product_details mt-100 mb-100">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6">
					<div class="product-details-tab">
						<div id="img-1" class="zoomWrapper single-zoom">
							<!-- 				<pictureVOList 首圖一張開始>				 -->
							<!-- 				防止超出索引值判斷/有圖放圖/開始			 -->
							<c:if test="${groupOrderVO.productVO.pictureVOList.size() != 0 }">
								<a href="#"> <img id="zoom1"
									src="${groupOrderVO.productVO.pictureVOList.get(0).url}"
									data-zoom-image="${groupOrderVO.productVO.pictureVOList.get(0).url}"
									alt="big-1" />
								</a>
							</c:if>
							<!-- 				防止超出索引值判斷/有圖放圖/開始		 -->
							<!-- 				防止超出索引值判斷/沒圖放預設圖/開始			 -->
							<c:if test="${groupOrderVO.productVO.pictureVOList.size() == 0 }">
								<a href="#"> <img id="zoom1"
									src="https://d148yrb2gzai3l.cloudfront.net/thumbs/132bbcf5-c503-11ec-99b3-a77a059327a2-pexels-alotrobo-1562983.jpg?d=600x400"
									data-zoom-image="https://d148yrb2gzai3l.cloudfront.net/thumbs/132bbcf5-c503-11ec-99b3-a77a059327a2-pexels-alotrobo-1562983.jpg?d=600x400"
									alt="big-1" />
								</a>
							</c:if>
							<!-- 				防止超出索引值判斷/沒圖放預設圖/開始			 -->
							<!-- 				<pictureVOList 首圖一結束>	-->
						</div>
						<div class="single-zoom-thumb">
							<ul class="s-tab-zoom owl-carousel single-product-active"
								id="gallery_01">
								<!-- 				<pictureVOList 如果有第二張循環開始>				 -->
								<!-- 				防止超出索引值判斷/有圖放圖/開始			 -->
								<c:if
									test="${groupOrderVO.productVO.pictureVOList.size() >= 2 }">
									<c:forEach var="pictureVO"
										items="${groupOrderVO.productVO.pictureVOList}">
										<li><a href="#" class="elevatezoom-gallery active"
											data-update="" data-image="${pictureVO.url}"
											data-zoom-image="${pictureVO.url}"> <img
												src="${pictureVO.url}" alt="zo-th-1" />
										</a></li>
									</c:forEach>
								</c:if>
								<!-- 				防止超出索引值判斷/有圖放圖/結束			 -->
								<!-- 				<pictureVOList 如果有第二張循環結束>				 -->
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6">
					<div class="product_d_right">
						<h1>
							<a href="#">${groupOrderVO.productVO.productId}</a>
						</h1>
						<div class="price_box">
							<span class="current_price">原價${groupOrderVO.productVO.price}元</span>
						</div>
						<div class="price_box">
							<span class="current_price">基本成團價${groupOrderVO.productVO.groupPrice1}元</span>
						</div>

						<div class="price_box">
							<p>PCLUB品質保證</p>
						</div>

						<table class="table" style="width: 65%">
							<thead>
								<tr>
									<th scope="col">折扣表</th>
									<th scope="col">數量</th>
									<th scope="col">折數</th>
									<th scope="col">折扣價</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">最低開團數量</th>
									<td>${groupOrderVO.productVO.groupAmount1}</td>
									<td>團購價</td>
									<td>${groupOrderVO.productVO.groupPrice1}</td>
								</tr>
								<tr>
									<th scope="row">開團數量二</th>
									<td>${groupOrderVO.productVO.groupAmount2}</td>
									<td>團購價8折</td>
									<td>${Math.round(groupOrderVO.productVO.groupPrice1*(0.8))}</td>
								</tr>
								<tr>
									<th scope="row">開團數量三</th>
									<td>${groupOrderVO.productVO.groupAmount3}</td>
									<td>團購價7折</td>
									<td>${Math.round(groupOrderVO.productVO.groupPrice1*(0.7))}</td>
								</tr>
							</tbody>
						</table>

						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/groupOrder.do">

							<div class="product_variant quantity">
								<!-- 							數量至少min於&起始於最低開團級距一 -->
								<span>截單方式：</span>
								<c:if test="${groupOrderVO.endType == 1 }">
								<span>限時截單</span><br>
								<span>於<fmt:formatDate value="${groupOrderVO.endTime}" pattern="yyyy-MM-dd HH:mm" />截單</span>
								<br>
								<span>目前份數： </span><span style='color:#d80000;'>${established}</span><span>份</span>
								</c:if>
								<c:if test="${groupOrderVO.endType == 2 }">
									<span>依份數截單(${groupOrderVO.minAmount}份)</span>
									<br>
									<span>目前份數： </span><span style='color:#d80000;'>${established}</span><span>份</span>
									<br>
									<span>最終時間： </span><span style='color:#3B5999;'><fmt:formatDate value="${groupOrderVO.endTime}" pattern="yyyy-MM-dd HH:mm" /></span>
								</c:if>

							</div>
							<input type="hidden" name="action" value="toJoinGroupOrder">
							<input type="hidden" name="groupOrderId"
								value="${groupOrderVO.groupOrderId}">

						</form>
						<div class="checkout_btn"
							onclick="javascript:document.join.submit();">
							<input type="hidden" name="action" value="joinGroupOrder">
							<a href="<%=request.getContextPath()%>/front/order/myAccount.jsp">回訂單</a>
						</div>

<!-- 						<div class="priduct_social"> -->
<!-- 							<ul> -->
<!-- 								<li><a class="facebook" href="#" title="facebook"> <i -->
<!-- 										class="bi bi-share-fill"></i>分享PCLUB社群 -->
<!-- 								</a></li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--product details end-->
	<!--product info start-->
	<div class="product_d_info mb-90">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="product_d_inner">
						<div class="product_info_button">
							<ul class="nav" role="tablist">
								<li><a class="active" data-toggle="tab" href="#info"
									role="tab" aria-controls="info" aria-selected="false">商品敘述</a>
								</li>
							</ul>
						</div>
						<div class="tab-content">
							<div class="tab-pane fade show active" id="info" role="tabpanel">
								<div class="product_info_content">
									<p>${groupOrderVO.productVO.description}</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 共通的footer start-->
	<%@include file="/front/layout/footer.jsp"%>

	<script
		src="<%=request.getContextPath()%>/assets/js/order&cart/groupDetial.js"></script>


</body>


</html>