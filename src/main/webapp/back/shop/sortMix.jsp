<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>商品分類</title>
<!-- 共用的CSS startr-->
<%@include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<!-- 	路徑舉例 -->
<%-- <link rel="stylesheet"href="<%=request.getContextPath()%>/assets/back/css/????.css"> --%>
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
								<!-- 					主分類分界線開始		 -->
								<div class="col-lg-6 mb-4">
									<div class="card">
										<div class="card-header">
											<h6 class="text-uppercase mb-0"
												style="font-size: 1.5em; font-weight: bold;">主分類</h6>
										</div>
										<div class="card-body">

												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/shop/sortMix">
											<div class="form-group row">
												<label class="col-md-3 form-control-label">新增主分類</label>
												<div class="input-group col-md-6">
 												 <input id="checkSort1Name" type="text" class="form-control" name="sort1Name" placeholder="輸入主分類名稱" >
 													 <div class="input-group-append">
  													  <button id="insertSort1" class="btn btn-primary" type="submit" disabled="disabled">新增</button>
  													</div>
												</div>
											</div>
														<input type="hidden" name="action" value="insertSort1VO">
  												</FORM>	
											
											<p class="form-text text-muted ml-3 ">不可與現有名稱重複</p>
											<p class="form-text text-muted ml-3 text-danger">刪除主分類會連帶刪除相關分類組合</p>
											<p class="form-text text-muted ml-3 text-danger"></p>

											<table class="table table-sm table-striped table-hover card-text">
												<thead>
													<tr class="text-center">
														<th>主分類編號</th>
														<th>主分類名稱</th>
														<th>編輯</th>
														<th>刪除</th>
														<th>不可刪除的原因</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="sort1VO" items="${sort1VOList}">
													<tr class="text-center">
														<th scope="row">${sort1VO.sort1Id}</th>
														<td>${sort1VO.sort1Name}</td>
														<td><button  class="btn btn-danger btn-sm">修改</button></td>
														<c:if test="${sort1VO.productVOList.size() != 0}">
														<td>不可刪除</td>
														<td>尚有歸類在此商品分類的商品</td>
														</c:if>
														<c:if test="${sort1VO.productVOList.size() == 0}">

														<td>
														<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/shop/sortMix">
														<input type="submit" value="刪除" class="btn btn-secondary btn-sm">														
														<input type="hidden" name="sort1Id" value="${sort1VO.sort1Id}">
														<input type="hidden" name="action" value="deleteSort1VO">
														</FORM>
														</td>
														<td class="btn-danger">無歸類商品，可刪除！</td>
														
														</c:if>	
													</tr>
													</c:forEach>	
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!-- 					主分類分界線開始			 -->
								<!-- 					子分類分界線開始		 -->
											<div class="col-lg-6 mb-4">
									<div class="card">
										<div class="card-header">
											<h6 class="text-uppercase mb-0"
												style="font-size: 1.5em; font-weight: bold;">子分類</h6>
										</div>
										<div class="card-body">

											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/shop/sortMix">
											<div class="form-group row">
												<label class="col-md-3 form-control-label">新增子分類</label>
													<div class="input-group col-md-6">
 												 <input id="checkSort2Name" type="text" class="form-control" name="sort2Name" placeholder="輸入子分類名稱" >
 													 <div class="input-group-append">
  													  <button id="insertSort2" class="btn btn-primary" type="submit" disabled="disabled">新增</button>
  													</div>
												</div>
											</div>
												<input type="hidden" name="action" value="insertSort2VO">
											</FORM>		
											<p class="form-text text-muted ml-3 ">不可與現有名稱重複</p>
											<p class="form-text text-muted ml-3 text-danger">刪除子分類會連帶刪除相關分類組合</p>
											<p class="form-text text-muted ml-3 text-danger"></p>

											<table class="table table-sm table-striped table-hover card-text">
												<thead>
													<tr class="text-center">
														<th>子分類編號</th>
														<th>子分類名稱</th>
														<th>編輯</th>
														<th>刪除</th>
														<th>尚不可刪除的原因</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="Sort2VO" items="${sort2VOList}">
													<tr class="text-center">
														<th scope="row">${Sort2VO.sort2Id}</th>
														<td>${Sort2VO.sort2Name}</td>
														<td><button  class="btn btn-danger btn-sm">修改</button></td>
														<c:if test="${Sort2VO.productVOList.size() != 0}">
														<td>不可刪除</td>
														<td>尚有歸類在此商品分類的商品</td>
														</c:if>
														<c:if test="${Sort2VO.productVOList.size() == 0}">
														<td>
														<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/shop/sortMix">
														<input type="submit" value="刪除" class="btn btn-secondary btn-sm">														
														<input type="hidden" name="sort2Id" value="${sort2VO.sort2Id}">
														<input type="hidden" name="action" value="deleteSort2VO">
														</FORM>
														</td>
														<td class="btn-danger">無歸類商品，可刪除！</td>
														</c:if>		
													</tr>
													</c:forEach>	
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!-- 					子分類分界線開始			 -->
								<!-- 					分類組合分界線開始			 -->
								<div class="col-lg-7 mb-4">
									<div class="card">
										<div class="card-header">
											<h6 class="text-uppercase mb-0"
												style="font-size: 1.5em; font-weight: bold;">分類組合</h6>
										</div>
										<div class="card-body">
										
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/shop/sortMix">
											<div class="form-group row">	
												<label class="col-md-12 form-control-label">新增分類組合</label>
												<label class="col-md-3 form-control-label">主分類選擇</label>
													<select class="col-md-3 custom-select custom-select" style="width: 50%" id="inlineFormCustomSelect" name ="sort1Id">
															<c:forEach var="sort1VO" items="${sort1VOList}">
															<option value="${sort1VO.sort1Id}">${sort1VO.sort1Name}</option>
															</c:forEach>
													</select>
											</div>		
											<div class="form-group row">	
												<label class="col-md-3 form-control-label">子分類選擇</label>
													<select class=" col-md-3 custom-select custom-select" style="width: 50%" id="inlineFormCustomSelect" name ="sort2Id">
														<c:forEach var="Sort2VO" items="${sort2VOList}">
														<option value="${Sort2VO.sort2Id}">${Sort2VO.sort2Name}</option>
														</c:forEach>
												</select>	
											</div>
											<input id="insertSort1" value="新增" type="submit" class="btn btn-primary">
											<input type="hidden" name="action" value="insertSortMixVO">
										</FORM>			
											<p class="form-text text-muted ml-3 ">不可與現有分類組合重複</p>
											<p class="form-text text-muted ml-3 text-danger"></p>

											<table class="table table-sm table-striped table-hover card-text">
												<thead>
													<tr class="text-center">
														<th>主分類編號</th>
														<th>主分類名稱</th>
														<th>子分類編號</th>
														<th>子分類名稱</th>
														<th>刪除</th>
														<th>不可刪除的原因</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="sortMixVO" items="${sortMixVOList}">
													<tr class="text-center">
														<th scope="row">${sortMixVO.sort1Id}</th>
														<td>${sortMixVO.sort1Name}</td>
														<td scope="row">${sortMixVO.sort2Id}</td>
														<td>${sortMixVO.sort2Name}</td>
														<c:if test="${sortMixVO.productVOList.size() != 0}">
														<td>不可</td>
														<td>有歸類在此商品分類的商品</td>
														</c:if>
														<c:if test="${sortMixVO.productVOList.size() == 0}">
														<td>
														<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/shop/sortMix">
														<input type="submit" value="刪除" class="btn btn-secondary btn-sm">														
														<input type="hidden" name="sort1Id" value="${sortMixVO.sort1Id}">
														<input type="hidden" name="sort2Id" value="${sortMixVO.sort2Id}">
														<input type="hidden" name="action" value="deleteSortMixVO">
														</FORM>
														</td>
														<td class="btn-danger">無歸類商品，可刪除！</td>
														</c:if>
													</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!-- 					分類組合分界線開始			 -->


							</div>
						</section>
					</div>
				</div>
			</section>
			<!--! Horizontal Form結束-->


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
	<script
		src="<%=request.getContextPath()%>/assets/shop/sortMix.js">
	</script>
	<!-- 額外添加的JS -->

	<jsp:include page="/front/layout/showMessage.jsp" />

</body>

</html>