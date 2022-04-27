<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<!-- 共用的CSS startr-->
<%@include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/back/css/backproduct.css">
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
														<label class="col-md-3 form-control-label">查詢日期 :</label>
														<div class="col-md-9">
															<input type="date" value="2020-04-20" min="2022-01-01"
																max="2050-01-01" step="1"> ~ <input type="date"
																value="2020-04-20" min="2022-01-01" max="2050-01-01"
																step="1"> &emsp;<input type="submit" value="確定"
																class="btn btn-primary">
														</div>
													</div>
												</div>
											</div>
											<!-- !分頁符號 css在style.default.css 4237 -->
											<div class="row justify-content-center align-items-center">
												<ul class="pagination">
													<li><a href="#">«</a></li>
													<li><a href="#">1</a></li>
													<li><a class="active" href="#">2</a></li>
													<li><a href="#">3</a></li>
													<li><a href="#">4</a></li>
													<li><a href="#">5</a></li>
													<li><a href="#">6</a></li>
													<li><a href="#">7</a></li>
													<li><a href="#">»</a></li>
												</ul>
											</div>
											<!-- !分頁符號 css在style.default.css 4237 -->
											<div class="form-group row">
												<div class="col-lg-12">
													<div class="card-body">
														<table
															class="table table-sm table-striped table-hover card-text">
																<thead>
														<tr>
															<th class="list">商品編號</th>
															<th class="list">商品照片</th>
															<th class="list">商品名稱</th>
															<th class="list">主分類</th>
															<th class="list">子分類</th>
															<th class="list">數量</th>
															<th class="list">售價</th>
															<th class="list">上架狀態</th>
															<th class="list">推薦狀態</th>
															<th class="list">編輯</th>
														</tr>
													</thead>
															<tr class="">
															<td>1</td>
															<td style="width: 6%;"><img
																src="https://dummyimage.com/600x400/000000/202a99&text=第1-1張"
																alt="..." class="img-thumbnail"></td>
															<td>好吃罐頭</td>
															<td><p>貓
																<p></td>
															<td>貓砂</td>
															<td>20</td>
															<td>$100</td>
															<td class="list2">一般商品上架
																<form >
																	<select class="custom-select custom-select-sm"
																		style="width: 80%" id="inlineFormCustomSelect">
																		<option selected>Choose...</option>
																		<option value="1">One</option>
																		<option value="2">Two</option>
																		<option value="3">Three</option>
																	</select>
																	<div class="col-auto my-1">
																		<button type="submit" class="btn btn-primary btn-sm">Submit</button>
																	</div>
																</form>
															</td>
															<td class="list2">未推薦
																<form >
																	<select class="custom-select custom-select-sm"
																		style="width: 80%" id="inlineFormCustomSelect">
																		<option selected>Choose...</option>
																		<option value="1">One</option>
																		<option value="2">Two</option>
																		<option value="3">Three</option>
																	</select>
																	<div class="col-auto my-1">
																		<button type="submit" class="btn btn-primary btn-sm">Submit</button>
																	</div>
																</form>
															</td>
														</tr>
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