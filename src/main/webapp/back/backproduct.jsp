<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/hub/css/backproduct.css">
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
																step="1"> &emsp;
																<input type="submit" value="確定" class="btn btn-primary">
														</div>
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
														<table class="table table-sm table-striped table-hover card-text">
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
															<td>${productVO.productId}</td>
															<td style="width: 6%;"><img
																src="https://dummyimage.com/600x400/000000/202a99&text=第1-1張"
																alt="..." class="img-thumbnail"></td>
															<td>${productVO.productName}</td>
<%-- 															<td><p>${sort2VO.sort1VOList}<p></td> --%>
<!-- 1.使用jsp:useBean創建Service實體 -->
<jsp:useBean id="pSort1Svc" scope="page" class="com.p_sort1.model.PSort1Service" />
<!-- 2.使用Service實體的方法計算 返回的實體自己的JDBC方法-->
<c:set var="sort1VOList" scope="page" value="${pSort1Svc.findSort1VOByproductId(productVO.sort2Id)}" />
															<td>
<!-- 3.看不到實體不知道抓去哪裡? 改寫VO的Tostring方法,慢慢嘗試,了解輸出的格式(類似JSON)-->
<c:if test="${sort1VOList.size() !=0 }">
<c:forEach var="sort1VO" items="${sort1VOList}">
															
<%-- <c:if test="${sort2VO.sort1VOList.get(0).sort1Name != null}"> --%>
															<span>${sort1VO.sort1Name}&nbsp;&nbsp;</span>
</c:forEach>															
<%-- </c:if>		 --%>
<%-- <c:if test="${sort2VO.sort1VOList.get(1)  != null}"> --%>
<%-- 															<p>${sort2VO.sort1VOList.get(1).sort1Name}<p>	 --%>
<%-- </c:if>	 --%>
</c:if> 															
															</td>
															<td>${sort2VO.sort2Name}</td>
															<td>${productVO.amount}</td>
															<td>${productVO.price}元</td>
														
															<td>
<c:choose>
<c:when test="${productVO.status == 0}">																
															未上架
</c:when>
<c:when test="${productVO.status == 1}">																
															一般商品上架
</c:when>	
<c:when test="${productVO.status == 2}">																
															一般與團購上架
</c:when>														
</c:choose>															
															<form>
																<select class="custom-select custom-select-sm"
																		style="width: 70%" id="inlineFormCustomSelect">
<!-- 															<option selected> Choose...</option> -->
																<option value="0">未上架</option>
																<option value="1">一般商品上架</option>
																<option value="3">一般與團購上架</option>
																</select>
																	<div class="col-auto my-1">
																		<button type="submit" class="btn btn-primary btn-sm">提交</button>
																	</div>
															</form>
															</td>
															<td>未推薦
																<form >
																	<select class="custom-select custom-select-sm"
																		style="width: 70%" id="inlineFormCustomSelect">
<!-- 																	<option selected>Choose...</option> -->
																		<option value="1">未推薦</option>
																		<option value="2">上架推薦</option>
																	</select>
																	<div class="col-auto my-1">
																		<button type="submit" class="btn btn-primary btn-sm">提交</button>
																	</div>
																</form>
															</td>
															<td><button type="submit" class="btn btn-danger btn-sm">修改</button></td>
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
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/back/js/?????.js"> --%>
<!-- 額外添加的JS -->
	
</body>

</html>