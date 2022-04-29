<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>新增商品</title>
<!-- 共用的CSS startr-->
<%@include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<!-- 	路徑舉例 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/back/css/addproduct.css">
<!-- 額外添加的CSS -->
</head>
<body>
	<!-- 共用的header start-->
	<%@include file="/back/layout/header.jsp"%>
	<!-- 共用的header end-->

	<!-- 共用的leftnav start-->
	<%@include file="/back/layout/leftnav.jsp"%>
	<!-- 共用的leftnav end-->

	<!--! ========內容開始======== -->
	<!-- 	筆記 小提示 -->
	<!-- 	<small class="form-text text-muted ml-3">限制20個字</small>
       新增時用的預設值placeholder="拿取資料value"
 -->


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
											<h3 class="h6 text-uppercase mb-0">新增商品</h3>
										</div>
										<div class="card-body">
											<p>商品編號??? 最後更新時間:????</p>
											<div class="row">
												<div class="col-lg-6 mb-5">
													<form class="form-horizontal">

														<div class="form-group row">
															<label class="col-md-3 form-control-label">商品名稱</label>
															<div class="col-md-6">
																<input id="inputHorizontalSuccess" type="text"
																	value="美國短毛貓" name="productName"
																	placeholder="拿取資料value"
																	class="form-control form-control-success"> <small
																	class="form-text text-muted ml-3">限制20個字</small>
															</div>
														</div>


														<div class="form-group row">
															<label class="col-md-3 form-control-label">商品價格</label>
															<div class="col-md-6">
																<input id="inputHorizontalSuccess" type="number"
																	value="" name="amount" placeholder="" step="1" min="1"
																	max="999" class="form-control form-control-success">
															</div>
														</div>

														<div class="form-group row">
															<label class="col-md-3 form-control-label">商品數量</label>
															<div class="col-md-6">
																<input id="inputHorizontalSuccess" type="number"
																	value="" name="amount" placeholder="" step="1" min="1"
																	max="999" class="form-control form-control-success">
															</div>
														</div>


														<div class="form-group row">
															<label class="col-md-3 form-control-label">商品子分類</label>
															<div class="col-md-6">
																<select class="custom-select custom-select-sm"
																	id="inlineFormCustomSelect"
																	class="form-control form-control-success" onchange="getSort1()">
<!-- 																用JS寫 -->
																
																</select>
															</div>
														</div>

														<div class="form-group row">
															<label class="col-md-3 form-control-label">對應主分類</label>
															<div class="col-md-6 m-auto">
																<div class="form-check form-check-inline">
																	<input class="form-check-input" type="checkbox"
																		id="inlineCheckbox1" value="option1"> <label
																		class="form-check-label" for="inlineCheckbox1">貓</label>
																</div>
																<div class="form-check form-check-inline">
																	<input class="form-check-input" type="checkbox"
																		id="inlineCheckbox2" value="option2"> <label
																		class="form-check-label" for="inlineCheckbox2">狗</label>
																</div>
															</div>
														</div>
														<!--! 提交按鈕 -->
														<!--                           <div class="form-group row">        -->
														<!--                             <div class="col-md-9 ml-auto"> -->
														<!--                               <input type="submit" value="儲存" class="btn btn-primary"> -->
														<!--                             </div> -->
														<!--                           </div> -->
													</form>
													<!--! 提交按鈕結束 -->
												</div>
												<!-- !右側團購欄位-->
												<div class="col-lg-6 mb-5">
													<div class="form-group row">
														<label class="col-md-3 form-control-label">團購起始價格</label>
														<div class="col-md-6">
															<input id="inputHorizontalSuccess" type="number"
																name="groupPrice1" placeholder="" step="1" min="1"
																max="999" class="form-control form-control-success">
														</div>
													</div>

													<div class="form-group row">
														<label class="col-md-3 form-control-label">團購數量級距一</label>
														<div class="col-md-6">
															<input id="inputHorizontalSuccess" type="number"
																name="groupAmount1" placeholder="" step="1" min="1"
																max="999" class="form-control form-control-success">
														</div>
													</div>

													<div class="form-group row">
														<label class="col-md-3 form-control-label">團購數量級距二</label>
														<div class="col-md-6">
															<input id="inputHorizontalSuccess" type="number"
																name="groupAmount2" placeholder="" step="1" min="1"
																max="999" class="form-control form-control-success">
														</div>
													</div>

													<div class="form-group row">
														<label class="col-md-3 form-control-label">團購數量級距三</label>
														<div class="col-md-6">
															<input id="inputHorizontalSuccess" type="number"
																name="groupAmount3" placeholder="" step="1" min="1"
																max="999" class="form-control form-control-success">
														</div>
													</div>


													<div class="form-group row">
														<label class="col-md-3 form-control-label">團購數量級距三</label>
														<div class="col-md-6">
															<input id="inputHorizontalSuccess" type="number"
																name="groupAmount3" placeholder="" step="1" min="1"
																max="999" class="form-control form-control-success">
														</div>
													</div>
												</div>
												<!--                     !右側團購欄位結束 -->
												<!--! Horizontal Form結束-->
											</div>
											<div class="row">
												<div class="col-lg-12 mb-5">
													<div class="form-group row">
														<label class="col-md-1 form-control-label">商品敘述</label>
														<div class="col-md-8">
															<textarea id="inputHorizontalSuccess" name="description"
																rows="5" cols="90" placeholder="輸入商品內容"
																class="form-control form-control-success">
														                            </textarea>
															<small class="form-text text-muted ml-3">限制200個字</small>
														</div>
													</div>
												</div>
											</div>


											<div class="row">
												<div class="col-lg-12 mb-5">
													<div class="form-group">
														<div class="col-md-8">
															<!--weui-uploader 照片上传功能-->
															<div class="picDiv">
																<div class="addImages">
																	<!--multiple属性可选择多个图片上传-->
																	<input type="file" class="file" id="fileInput" multiple accept="image/png, image/jpeg, image/gif, image/jpg" />
																	<div class="text-detail">
																		<span>+</span>
																		<p>點擊上傳</p>
																	</div>
																</div>
															</div>
															<!--weui-uploader 照片上传功能 END-->
														</div>
													</div>
												</div>
											</div>
						</section>
					</div>
				</div>
			</section>
			<!--! ========內容結束========-->


			<!-- 共通的footer start-->
			<%@include file="/back/layout/footer.jsp"%>
			<!-- 共通的footer end-->
		</div>
	</div>


	<!-- 共用的JS -->
	<%@include file="/back/layout/commonJS.jsp"%>
	<!-- 共用的JS -->

	<!-- 額外添加的JS -->
	<!-- 	路徑舉例 -->
	<script src="<%=request.getContextPath()%>/assets/back/js/uploadimg.js">	</script>
	<script src="<%=request.getContextPath()%>/assets/back/product/js/sortOption.js">	</script>
	<!-- 額外添加的JS -->

</body>

</html>