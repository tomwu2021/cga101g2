<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sort2.model.*"%>
<%@ page import="com.sort_mix.model.*"%>
<%@ page import="com.product.model.*"%>

<%
Sort2Service sort2Svc = new Sort2Service();
List<Sort2VO> sort2list = sort2Svc.getAll();
pageContext.setAttribute("sort2list", sort2list);
%>
<!DOCTYPE html>
<html>
<head>
<title>新增商品</title>
<!-- 共用的CSS startr-->
<%@include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<!-- 圖片上傳 -->
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/assets/shop/upimg/css/upimg.css"> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/shop/upimg2/upimg2.css">
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
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/shop/productInsert" name="form1" enctype="multipart/form-data">
										<div class="card">
											<div class="card-header">
												<h3 class="h6 text-uppercase mb-0">新增商品</h3>
											</div>
											<div class="card-body">
<!-- 												<p></p> -->
												<div class="row">
													<div class="col-lg-6 mb-5">
														<form class="form-horizontal">
															<div class="form-group row">
																<label class="col-md-3 form-control-label">商品名稱</label>
																<div class="col-md-6">
																	<input id="inputHorizontalSuccess" type="text"
																		value="${param.productName}" name="productName"
																		placeholder="輸入商品名稱"
																		class="form-control form-control-success"> <small
																		class="form-text text-muted ml-3">限制20個字</small> <small
																		class="form-text text-muted ml-3 text-danger">${errorMsgs.productName}</small>
																</div>
															</div>


															<div class="form-group row">
																<label class="col-md-3 form-control-label">商品價格</label>
																<div class="col-md-6">
																	<input id="inputHorizontalSuccess" type="number"
																		value="${param.price}" name="price" placeholder=""
																		step="1" min="1" max="9999"
																		class="form-control form-control-success"> <small
																		class="form-text text-muted ml-3 text-danger">${errorMsgs.price}</small>
																</div>
															</div>

															<div class="form-group row">
																<label class="col-md-3 form-control-label">商品數量</label>
																<div class="col-md-6">
																	<input id="inputHorizontalSuccess" type="number"
																		value="${param.amount}" name="amount" placeholder=""
																		step="1" min="1" max="999"
																		class="form-control form-control-success"> <small
																		class="form-text text-muted ml-3 text-danger">${errorMsgs.amount}</small>
																</div>
															</div>


															<div class="form-group row">
																<label class="col-md-3 form-control-label">商品子分類</label>
																<div class="col-md-6">
																	<select class="custom-select custom-select-sm"
																		id="inlineFormCustomSelect"
																		class="form-control form-control-success"
																		onchange="get_sort1index()" name="sort2Id">
																		<c:forEach var="sort2VO" items="${sort2list}">
																			<option value="${sort2VO.sort2Id}">${sort2VO.sort2Name}</option>
																		</c:forEach>
																	</select> <small class="form-text text-muted ml-3 text-danger">${errorMsgs.sort2Id}</small>
																</div>
															</div>
															<div class="form-group row">
																<label class="col-md-3 form-control-label">對應主分類</label>
																<div class="col-md-6 m-auto" id="sort2CheckBox">
																	<jsp:useBean id="smixSvc" scope="page"
																		class="com.sort_mix.model.SortMixService" />
																	<c:set var="sort1VOlist" scope="page"
																		value="${smixSvc.getSort1VOsBySort2Id(sort2list.get(0).sort2Id)}"></c:set>
																	<c:if test="${sort1VOlist.size() !=0}">
																		<c:forEach var="sort1VO" items="${sort1VOlist}">
																			<div class="form-check form-check-inline">
																				<input class="form-check-input" type="checkbox"
																					name="sort1Id" id="inlineCheckbox1"
																					value="${sort1VO.sort1Id}"> <label
																					class="form-check-label" for="inlineCheckbox1">${sort1VO.sort1Name}</label>
																			</div>
																		</c:forEach>
																	</c:if>
																</div>
															</div>
															<small
																class="form-text text-muted ml-3 text-danger justify-content-center ">${errorMsgs.sort1Id}</small>
														</form>
													</div>
													<!-- !右側團購欄位-->
													<div class="col-lg-6 mb-5">
														<div class="form-group row">
															<label class="col-md-3 form-control-label">團購起始價格</label>
															<div class="col-md-6">
																<input id="inputHorizontalSuccess" type="number"
																	value="${param.groupPrice1}" name="groupPrice1"
																	placeholder="" step="1" min="1" max="999"
																	class="form-control form-control-success"> <small
																	class="form-text text-muted ml-3 text-danger">${errorMsgs.groupPrice1}</small>
															</div>
														</div>

														<div class="form-group row">
															<label class="col-md-3 form-control-label">團購數量級距一</label>
															<div class="col-md-6">
																<input id="inputHorizontalSuccess" type="number"
																	value="${param.groupAmount1}" name="groupAmount1"
																	placeholder="" step="1" min="1" max="999"
																	class="form-control form-control-success"> <small
																	class="form-text text-muted ml-3 text-danger">${errorMsgs.groupAmount1}</small>
															</div>
														</div>

														<div class="form-group row">
															<label class="col-md-3 form-control-label">團購數量級距二</label>
															<div class="col-md-6">
																<input id="inputHorizontalSuccess" type="number"
																	value="${param.groupAmount2}" name="groupAmount2"
																	placeholder="" step="1" min="1" max="999"
																	class="form-control form-control-success"> <small
																	class="form-text text-muted ml-3 text-danger ">${errorMsgs.groupAmount2}</small>
															</div>
														</div>

														<div class="form-group row">
															<label class="col-md-3 form-control-label">團購數量級距三</label>
															<div class="col-md-6">
																<input id="inputHorizontalSuccess" type="number"
																	value="${param.groupAmount3}" name="groupAmount3"
																	placeholder="" step="1" min="1" max="999"
																	class="form-control form-control-success"> <small
																	class="form-text text-muted ml-3 text-danger">${errorMsgs.groupAmount3}</small>
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
																<div class="mb-3">
																	<textarea class="form-control"
																		id="exampleFormControlTextarea1" rows="5"
																		name="description" placeholder="輸入商品敘述">${param.description}</textarea>
																</div>
																<small class="form-text text-muted ml-3">限制至少20字以上至多300個字以下</small>
																<small class="form-text text-muted ml-3 text-danger">${errorMsgs.description}</small>
															</div>
														</div>
													</div>
												</div>
												<!--! 商品照片上傳start -->
												<div class="row">
													<div class="col-lg-12 mb-5">
														<div class="form-group row">
															<label class="col-md-3 form-control-label">商品照片 &emsp; (第一張為首圖)</label>
															<div class="col-md-6"></div>
														</div>
														<div class="form-group">
															<div class="col-md-12">
																<!--weui-uploader 照片上传功能-->
																<!--上傳圖片的按鈕-->
																<img src="<%=request.getContextPath()%>/assets/shop/backProduct/img/addimg.png"
																 class="ml-3" style="height:200px;width:200px;" onclick="add();" id="addimage">
<!-- 																<label class="btn btn-info"> -->
<!-- 																 <input type="file" name="img" accept="image/*" multiple="multiple" -->
<!-- 																		id="showimg" style="display: none;" multiple /> 上傳圖片 -->
<!-- 																</label> -->
<!-- 																<div class='row'> -->
<!-- 																	<div id='previewMultiple'></div> -->
<!-- 																</div> -->
															</div>
																<small class="form-text text-muted ml-3 text-danger">${errorMsgs.img}</small>
														</div>
													</div>
												</div>	
												<!--! 商品照片上傳end -->
												<!--! 提交按鈕start -->
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<input type="submit" value="送出新增" class="btn btn-primary">
															<input type="hidden" name="action" value="insert">
														</div>
													</div>
												</div>
												<!--! 提交按鈕end -->
													</div>
													
												</div>
					</FORM>
											</div>
										</div>
								</div>
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
	<!-- 	圖片上傳 -->
	<script
		src="<%=request.getContextPath()%>/assets/shop/upimg/js/upimg.js">
		
	</script>
	<script
		src="<%=request.getContextPath()%>/assets/shop/upimg2/upimg2.js">
	</script> 
	<script
		src="<%=request.getContextPath()%>/assets/shop/addproduct/js/sort1VOCheckBox.js">
	</script>
	
	<script
		src="<%=request.getContextPath()%>/assets/shop/backProduct/img/addimg.js">
	</script>  

	<!-- 額外添加的JS -->
</body>

</html>