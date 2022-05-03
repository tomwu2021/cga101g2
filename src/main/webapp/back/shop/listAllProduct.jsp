<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_img.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.sort2.model.*"%>
<%@ page import="com.sort_mix.model.*"%>


<%
ProductService pdSvc = new ProductService();
List<ProductVO> list = pdSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<title>商品管理</title>
<!-- 共用的CSS startr-->
<%@include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/hub/css/backproduct.css">
<!-- 額外添加的CSS -->
</head>
<body>
	<!-- 共用的header start-->
	<%@include file="/back/layout/header.jsp"%>
	<!-- 共用的header end-->

	<!-- 共用的leftnav start-->
	<%@include file="/back/layout/leftnav.jsp"%>
	<!-- 共用的leftnav end-->

	<!--! ========內容======== -->
	<div class="page-holder w-100 d-flex flex-wrap">
		<div class="container-fluid px-xl-5">
			<section>
				<div class="page-holder w-100 d-flex flex-wrap">
					<div class="container-fluid px-xl-5">
						<section class="py-5">
							<div class="row">
								<!--! Horizontal Form-->
								<div class="col-lg-12 mb-5">
									<div class="card">
										<div class="card-header">
											<h3 class="h6 text-uppercase mb-0">商品管理</h3>
										</div>
										<div class="card-body">
											<p>商品管理</p>
											<div class="row">
												<div class="col-lg-12 mb-5">
													<!-- <form class="form-horizontal"> -->
													<div class="form-group row">
														<div class="col-md-9"></div>
													</div>
													<div class="form-group row">
														<label class="col-sm-1 form-control-label">查詢日期 :</label>
														<div class="col-md-9">
															<input type="date" value="2020-04-20" min="2022-01-01"
																max="2050-01-01" step="1"> ~ <input type="date"
																value="2020-04-20" min="2022-01-01" max="2050-01-01"
																step="1"> &emsp; <input type="submit" value="確定"
																class="btn btn-primary">
														</div>
													</div>
													<div class="d-grid gap-2 d-md-flex justify-content-md-end">
														<button class="btn btn-primary me-md-2" type="button"
														 onclick="javascript:window.location='<%=request.getContextPath()%>/back/shop/addProduct.jsp'">
														 +新增商品</button>
														<button class="btn btn-primary" type="button">Button</button>
													</div>
												</div>
											</div>
											<!-- !分頁符號 css在style.default.css 4237 -->
											<!-- 											<div class="row justify-content-center align-items-center"> -->
											<!-- 												<ul class="pagination"> -->
											<!-- 													<li><a href="#">«</a></li> -->
											<!-- 													<li><a href="#">1</a></li> -->
											<!-- 													<li><a class="active" href="#">2</a></li> -->
											<!-- 													<li><a href="#">3</a></li> -->
											<!-- 													<li><a href="#">4</a></li> -->
											<!-- 													<li><a href="#">5</a></li> -->
											<!-- 													<li><a href="#">6</a></li> -->
											<!-- 													<li><a href="#">7</a></li> -->
											<!-- 													<li><a href="#">»</a></li> -->
											<!-- 												</ul> -->
											<!-- 											</div> -->
											<!-- !分頁符號 css在style.default.css 4237 -->
											<div class="form-group row">
												<div class="col-lg-12">
													<div class="card-body">
														<table
															class="table table-sm table-striped table-hover card-text">
															<thead>
																<tr class="text-center">
																	<th class="list">商品編號</th>
																	<th class="list">商品照片</th>
																	<th class="list2">商品名稱</th>
																	<th class="list">主分類</th>
																	<th class="list">子分類</th>
																	<th class="list">數量</th>
																	<th class="list">售價</th>
																	<th class="list1">上架狀態</th>
																	<th class="list1">推薦狀態</th>
																	<th class="list">編輯</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach var="productVO" items="${list}">
																	<tr class="text-center">
																		<td id ="productId">${productVO.productId}</td>
<c:set var="pictureVOList" scope="page" value="${productVO.pictureVOList}"></c:set>
																		<c:if test="${pictureVOList.size() != 0 }">
																			<td style="width: 6%;"><img
																				src="${productVO.pictureVOList.get(0).previewUrl}"
																				alt="..." class="img-thumbnail"></td>
																		</c:if>
																		<!-- 此段是防止沒有照片所以跑版的判斷 開始-->
																		<c:if test="${pictureVOList.size() == 0 }">
																			<td style="width: 6%;"><img
																				src="https://fakeimg.pl/350x200/ff0000/000"
																				alt="..." class="img-thumbnail"></td>
																		</c:if>
																		<!-- 此段是防止沒有照片所以跑版的判斷	 結束-->
																		<td>${productVO.productName}</td>


																		<c:set var="sort1VOList" scope="page"
																			value="${productVO.sort1VOList}" />
																		<td>
																			<!-- 3.看不到實體不知道抓去哪裡? 改寫VO的Tostring方法,慢慢嘗試,了解輸出的格式(類似JSON)-->
																			<c:if test="${sort1VOList.size() !=0 }">
																				<c:forEach var="sort1VO" items="${sort1VOList}">
																					<span>${sort1VO.sort1Name}&nbsp;&nbsp;</span>
																				</c:forEach>
																			</c:if>
																		</td>
																		<c:set var="sort2VO" scope="page"
																			value="${productVO.sort2VO}" />
																		<td>${sort2VO.sort2Name}</td>
																		<td>${productVO.amount}</td>
																		<td>${productVO.price}元</td>

																		<c:choose>
																			<c:when test="${productVO.status == 0}">
																				<td>未上架
																			</c:when>
																			<c:when test="${productVO.status == 1}">
																				<td class="table-primary">一般商品上架
																			</c:when>
																			<c:when test="${productVO.status == 2}">
																				<td class="table-warning">一般與團購上架
																			</c:when>
																		</c:choose>
																		<form>
																			<select class="custom-select custom-select-sm"
																				style="width: 70%" id="inlineFormCustomSelect" name ="status">
																				<!-- 															<option selected> Choose...</option> -->
																				<option value="0">未上架</option>
																				<option value="1">一般商品上架</option>
																				<option value="2">一般與團購上架</option>
																			</select>
																			<div class="col-auto my-1">
																				<button type="button" class="btn btn-primary btn-sm update" >提交</button>
																			</div>
																		</form>
																		</td>
																		<c:choose>
																			<c:when test="${productVO.topStatus == 0}">
																				<td>未推薦
																			</c:when>
																			<c:when test="${productVO.topStatus == 1}">
																				<td class="table-primary">推薦商品
																			</c:when>
																		</c:choose>
																		<form>
																			<select class="custom-select custom-select-sm"
																				style="width: 70%" id="inlineFormCustomSelect">
																				<!-- 																	<option selected>Choose...</option> -->
																				<option value="1">未推薦</option>
																				<option value="2">上架推薦</option>
																			</select>
																			<div class="col-auto my-1">
																				<button type="button" class="btn btn-primary btn-sm">提交</button>
																			</div>
																		</form>
																		</td>
																		<td>
																			<FORM METHOD="get"
																				ACTION="<%=request.getContextPath()%>/back/shop/productUpdateServlet">
																				<button type="submit" class="btn btn-danger btn-sm">修改</button>
																				<input type="hidden" name="productId"
																					value="${productVO.productId}"> <input
																					type="hidden" name="action"
																					value="getOne_For_Update">
																			</FORM>
																		</td>
																</c:forEach>

															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
					</div>
					<!--! Horizontal Form結束-->
					<!-- !modal area start-->
					<div class="modal fade bd-example-modal-lg" tabindex="-1"
						role="dialog" aria-labelledby="myLargeModalLabel"
						aria-hidden="true">
						<div class="modal-dialog modal-lg" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">訂單明細</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<h4>訂單編號:&nbsp;2022143532</h4>
									<table class="table table-striped table-hover card-text">
										<thead>
											<tr>
												<th>商品編號?</th>
												<th>商品名稱</th>
												<th>價格</th>
												<th>數量</th>
												<th>小計</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>20223453</td>
												<td>貓咪貓咪罐頭</td>
												<td>20 元</td>
												<td>10</td>
												<td>270 元</td>
												<input name="status" type="checkbox" data-size="small">
											</tr>
									</table>
								</div>
								<div class="modal-footer"></div>
							</div>
						</div>
					</div>
			</section>
			<!--! modal area end-->


			<!-- 共通的footer start-->
			<%@include file="/back/layout/footer.jsp"%>
			<!-- 共通的footer end-->
		</div>
	</div>


	<!-- 共用的JS -->
	<%@include file="/back/layout/commonJS.jsp"%>
	<!-- 共用的JS -->

	<!-- 額外添加的JS -->
	<script src="<%=request.getContextPath()%>/assets/shop/backProduct/productUpdateStatus.js"> </script>
	<!-- 額外添加的JS -->

</body>

</html>