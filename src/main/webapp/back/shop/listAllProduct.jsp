<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ page import="java.util.*"%>
<%@ page import="com.product_img.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.sort2.model.*"%>
<%@ page import="com.sort_mix.model.*"%>

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
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/shop/datetimepicker/jquery.datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/shop/datetimepicker/time.css" />
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
											<p>查詢條件</p>
											
											<div class="row">
												<div class="col-lg-6 mb-5">
													<!-- <form class="form-horizontal"> -->
<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/back/shop" id="myPageForm">
													<div class="form-group row">
															<label class="col-md-3 form-control-label">商品編號 :</label>
															<div class="col-md-6">
															<input class="form-control" value="${userMsgs.product_id}" list="datalistOptions" id="exampleDataListproduct_id" name="product_id"  placeholder="輸入商品編號">
																<datalist id="datalistOptions">
																	<c:forEach var="productVO" items="${forSelectList}">
  																		<option value="${productVO.productId}">
 																	</c:forEach>
																</datalist> 
															</div>
													</div>	
									
													<div class="form-group row">
																<label class="col-md-3 form-control-label">商品名稱 :</label>
																<div class="col-md-6">
																<input class="form-control" list="datalistOptions2" value="${userMsgs.product_name}" id="exampleDataList2product_name" name="product_name"  placeholder="輸入商品名稱">
																<datalist id="datalistOptions2">
																	<c:forEach var="productVO" items="${forSelectList}">
  																		<option value="${productVO.productName}">
 																	</c:forEach>
																</datalist> 
															</div>
													</div>	
													
													<div class="form-group row ">
															<label class="col-md-3 form-control-label">商品主分類 :</label>
																<div class="col-md-6 m-auto" id="sort2CheckBox">
																		<div class="form-check form-check-inline">
																			
																			<c:if test="${userMsgsSort1.sort1_id == null}">
																			<c:forEach var="sort1VO" items="${sort1VOList}" >
																				<input class="form-check-input" type="checkbox" 
																					name="noChoose" id="inlineCheckbox1noChoose${sort1VO.sort1Id}"
																					value="${sort1VO.sort1Id}"> 
																					<label class="form-check-label" for="inlineCheckbox1"style="padding-right: 20px;">${sort1VO.sort1Name}</label>
																			</c:forEach>	
																			</c:if>	
																			
																			<c:if test="${userMsgsSort1.sort1_id != null}">
<%-- 																			===本次選擇:=== ===${userMsgsSort1.sort1_id}=== --%>
<%-- 																			===本次選擇:=== ===${fn:length(sort1_id)}=== --%>
<%-- 																			===本次選擇:=== ===${sort1VOList}=== --%>
																			<c:set var="userMsgsSort1_idArray" value="${userMsgsSort1.sort1_id}"/>
																			
																			<c:if test="${fn:length(userMsgsSort1_idArray) != fn:length(sort1VOList)}">
																			
																			<c:forEach var="sort1VO" items="${sort1VOList}" >
																			<c:forEach var="i" begin="0" end="${fn:length(userMsgsSort1_idArray) -1}" >
																						<c:if test="${userMsgsSort1_idArray[i]  ==  sort1VO.sort1Id}">
																						<input class="form-check-input" type="checkbox" 
																							name="sort1_id" id="inlineCheckbox1noChoose${sort1VO.sort1Id}" 
																							checked="checked"
																							value="${sort1VO.sort1Id}" >
																						<label class="form-check-label" for="inlineCheckbox1"style="padding-right: 20px;">${sort1VO.sort1Name}</label>
																						</c:if> 
																						<c:if test="${userMsgsSort1_idArray[i] != sort1VO.sort1Id }">
																						<input class="form-check-input" type="checkbox" 
																							name="noChoose" id="inlineCheckbox1noChoose${sort1VO.sort1Id}" 
																							value="${sort1VO.sort1Id}" >
																						<label class="form-check-label" for="inlineCheckbox1"style="padding-right: 20px;">${sort1VO.sort1Name}</label>
																						</c:if> 
																				</c:forEach>
																			</c:forEach>
																			</c:if>	
																			
																			<c:if test="${fn:length(userMsgsSort1_idArray) == fn:length(sort1VOList)}">
																				<c:forEach var="sort1VO" items="${sort1VOList}" >
																						<input class="form-check-input" type="checkbox" 
																							name="sort1_id" id="inlineCheckbox1noChoose${sort1VO.sort1Id}" 
																							checked="checked"
																							value="${sort1VO.sort1Id}" >
																						<label class="form-check-label" for="inlineCheckbox1"style="padding-right: 20px;">${sort1VO.sort1Name}</label>
																				</c:forEach>	
																			</c:if>
																			
																			</c:if>	
																		</div>
																</div>
													</div>
													
												<div class="form-group row">
														<label class="col-md-3 form-control-label">商品子分類 :</label>
														<div class="col-md-6">
																<select class="custom-select custom-select-sm"
																	id="inlineFormCustomSelectSort2_id" name="sort2_id"
																	class="form-control form-control-success">
																		<c:if test="${userMsgs.sort2_id == null}">
																			<option value="" >請選擇</option>
																			<c:forEach var="sort2VO" items="${sort2VOList}" >
																				<option value="${sort2VO.sort2Id}">${sort2VO.sort2Name}</option>
																			</c:forEach>
																		</c:if>
																		<c:if test="${userMsgs.sort2_id !=null}">
																			<option value="${userMsgs.sort2_id}" >${userMsgs.sort2_name}</option>
																			<option value="" >請選擇</option>
																			<c:forEach var="sort2VO" items="${sort2VOList}" >
																				<option value="${sort2VO.sort2Id}">${sort2VO.sort2Name}</option>
																			</c:forEach>
																		</c:if>
																</select>
														</div>
												</div>
													
												
												
												</div>
										<div class="col-lg-6 mb-5">
												
												<div class="form-group row">
														<label class="col-md-3 form-control-label">推薦狀態 :</label>
														<div class="col-md-6">
<%-- 														=====${userMsgs.top_status}==== --%>
																<select class="custom-select custom-select-sm"
																	id="inlineFormCustomSelectTop_status" name="top_status"
																	class="form-control form-control-success">
																	<c:if test="${userMsgs.top_status == null}">
																		<option value=" " >請選擇</option>
																		<option value="0">未推薦</option>
																		<option value="1">已推薦</option>
																	</c:if>
																	
																	<c:if test="${userMsgs.top_status == '0'}">
																		<option value="0" >未推薦</option>
																		<option value="1">已推薦</option>
																		<option value=" ">請選擇</option>
																	</c:if>
																	<c:if test="${userMsgs.top_status == '1'}">
																		<option value="1" >已推薦</option>
																		<option value="0">未推薦</option>
																		<option value=" ">請選擇</option>
																	</c:if>
																
															</select> 
														</div>
												</div>
												
												<div class="form-group row">
														<label class="col-md-3 form-control-label">上架狀態 :</label>
														<div class="col-md-6">
<%-- 														===${userMsgs.status}=== --%>
																<select class="custom-select custom-select-sm"
																	id="inlineFormCustomSelectStatus" name="status"
																	class="form-control form-control-success">
																	<c:if test="${userMsgs.status == null}">
																	<option value=" ">請選擇</option>
																	<option value="0">未上架</option>
																	<option value="1">一般商品上架</option>
																	<option value="2">一般與團購上架</option>
																	</c:if>
																	
																	<c:if test="${userMsgs.status == '0'}">
																		<option value="${userMsgs.status}" >未上架</option>
																		<option value=" ">請選擇</option>
																		<option value="1">一般商品上架</option>
																		<option value="2">一般與團購上架</option>
																	</c:if>
																	
																	<c:if test="${userMsgs.status == '1'}">
																		<option value="${userMsgs.status}" >一般商品上架</option>
																		<option value=" ">請選擇</option>
																		<option value="0">未上架</option>
																		<option value="2">一般與團購上架</option>
																	</c:if>
																	
																	<c:if test="${userMsgs.status == '2'}">
																		<option value="${userMsgs.status}">一般與團購上架</option>
																		<option value=" ">請選擇</option>
																		<option value="0">未上架</option>
																		<option value="1">一般商品上架</option>
																	</c:if>
																	
															</select> 
														</div>
												</div>
												
												
													<div class="from-group row">
														<label class="col-md-3 form-control-label">更新日期 :</label>
														<div class="col-md-9">
														<c:if test="${userMsgs.startTime == null && userMsgs.endTime == null}">
															<input name="startTime" id="f_date1" type="text" >
															～　
															<input name="endTime" id="f_date2" type="text" >
														</c:if>
														<c:if test="${userMsgs.startTime != null && userMsgs.endTime != null}">
															<input name="startTime" id="f_date3" type="text" value="${userMsgs.startTime}">
															～　
															<input name="endTime" id="f_date4" type="text" value="${userMsgs.endTime}">
														</c:if>
														</div>
													</div>
												</div>
											<div class="col-lg-12 mb-5">		
													<div class="d-grid gap-2 d-md-flex justify-content-md-end">
														<button class="btn btn-success me-md-2" type="button" style="margin-right: 100px"
														 onclick="javascript:window.location='<%=request.getContextPath()%>/back/shop/addProduct.jsp'">
														 +新增商品</button>
														<input id ="selectProduct" type="submit" value="送出查詢" class="btn btn btn-primary"  style="margin-right: 40px;">
														<input type="hidden" name="action" value="listProducts_Byfind">
													</div>
											</div>
<div class="col-lg-12 mb-5">
<div class="d-grid gap-2 d-md-flex justify-content-md-end">									
<div class="niceselect_option">
<select size="1" name="whichPage" id="myPage">
		      	  <option value="-1">請選擇
		         <%
		          // get attributes
		          int whichPage = 1;
		          String currPage = (String) request.getAttribute("currPage");
		          if(currPage != null) {
		        	  whichPage = Integer.parseInt(currPage);
		          }
		          int total = (Integer) request.getAttribute("total");
		          int pageTotal; 
		          if(total % 9 == 0) {
					  pageTotal = total / 9;
		          } else {
					  pageTotal = total / 9 + 1;
		          }
		          for (int i=1; i<=pageTotal; i++){
		         %>
		         	<c:if test="<%= whichPage == i%>">
		         		<option value="<%=i%>" selected>跳至第<%=i%>頁
		         	</c:if>
		         	<c:if test="<%= whichPage != i%>">
		         		<option value="<%=i%>">跳至第<%=i%>頁
		         	</c:if>
		         <%}%> 
		       </select>
	  
	   	<span class="text-primary" >一頁9筆</span>
	    <span class="text-primary" >總共<%=pageTotal%>頁</span>
	    <span class="text-primary" >總共<%=total %>筆</span>
	      </div>
	    </div>
	  </div>  
</FORM>	  
			
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
																	<th class="list1">推薦狀態</th>
																	<th class="list1">上架狀態</th>
																	<th class="list">編輯</th>
																</tr>
															</thead>
															<tbody>
<%-- <%@ include file="pages/page1_ByCompositeQuery.file" %>  --%>

																<c:forEach var="productVO" items="${listProducts_Byfind}" >
																	<tr class="text-center">
																		<td >${productVO.productId}</td>
<c:set var="pictureVOList" scope="page" value="${productVO.pictureVOList}"></c:set>
																		<c:if test="${pictureVOList.size() != 0 }">
																			<td style="width: 6%;"><img
																				src="${productVO.pictureVOList.get(0).previewUrl}"
																				alt="..." class="img-thumbnail"></td>
																		</c:if>
																		<td>${productVO.productName}</td>


																		<c:set var="PSort1VOList" scope="page"
																			value="${productVO.PSort1VOList}" />
																		<td>
																			<!-- 3.看不到實體不知道抓去哪裡? 改寫VO的Tostring方法,慢慢嘗試,了解輸出的格式(類似JSON)-->
																			<c:if test="${PSort1VOList.size() !=0 }">
																				<c:forEach var="sort1VO" items="${PSort1VOList}">
																					<span>${sort1VO.sort1Name}&nbsp;&nbsp;</span>
																				</c:forEach>
																			</c:if>
																		</td>
<%-- 																		<c:set var="sort2VO" scope="page" value="${productVO.sort2VO}" /> --%>
																		<td>${productVO.sort2Name}</td>
																		<td>${productVO.amount}</td>
																		<td>${productVO.price}元</td>


												
																		<c:choose>
																			<c:when test="${productVO.topStatus == 0}">
																				<td >
																					<input id="updateTopStatus${productVO.productId}" value="${productVO.topStatus}"
																					type="checkbox"  data-toggle="toggle"  data-width="80" data-height="30"  data-onstyle="primary" data-offstyle="secondary" >
																				</td>
																			</c:when>
																			<c:when test="${productVO.topStatus == 1}">
																				<td class="table-primary">
																					<input id="updateTopStatus${productVO.productId}"  value="${productVO.topStatus}"
																					type="checkbox" data-toggle="toggle"  data-width="80" data-height="30"  data-onstyle="primary" data-offstyle="secondary" checked>
																				</td>
																			</c:when>
																		</c:choose>


																		<c:choose>
																			<c:when test="${productVO.status == 0}">
																				<td class=""> 
																					<span>未上架&nbsp;&nbsp;&nbsp;&nbsp;</span>
																					<select class="custom-select custom-select-sm" style="width: 70%" id="inlineFormCustomSelect" name ="status">
																						<option value="1">一般商品上架</option>
																						<option value="2">一般與團購上架</option>
																					</select>
																					<div class="col-auto my-1">
																						<button id="updateStatus${productVO.productId}" value="${productVO.productId}" name="status" type="button" class="btn btn-primary btn-sm" >提交</button>
																					</div>
																				</td>
																			</c:when>
																			
																			<c:when test="${productVO.status == 1}">
																				<td class="table-primary">
																						<span>一般商品上架</span>
																						<select class="custom-select custom-select-sm" style="width: 70%" id="inlineFormCustomSelect" name ="status">
																						<option value="0">未上架</option>
																						<option value="2">一般與團購上架</option>
																					</select>
																					<div class="col-auto my-1">
																						<button id="updateStatus${productVO.productId}" value="${productVO.productId}" name="status" type="button" class="btn btn-primary btn-sm" >提交</button>
																					</div>
																				</td>
																			</c:when>
																			<c:when test="${productVO.status == 2}">
																				<td class="table-warning">
																						<span>一般與團購上架</span>
																						<select class="custom-select custom-select-sm" style="width: 70%" id="inlineFormCustomSelect" name ="status">
																						<option value="0">未上架</option>
																						<option value="1">一般商品上架</option>
																					</select>
																					<div class="col-auto my-1">
																						<button id="updateStatus${productVO.productId}" value="${productVO.productId}" name="status" type="button" class="btn btn-primary btn-sm" >提交</button>
																					</div>
																				</td>
																			</c:when>
																		</c:choose>
																		
																		
																		<td>
																			<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/shop/ProductGetOneServlet">
																				<button type="submit" class="btn btn-danger btn-sm">修改</button>
																				<input type="hidden" name="productId" value="${productVO.productId}"> 
																				<input type="hidden" name="action" value="getOne_For_Update">
																			</FORM>
																		</td>
																</c:forEach>
															</tbody>
														</table>
													</div>
<%-- <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
<%-- <%@ include file="pages/page2_ByCompositeQuery.file" %> --%>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
					</div>
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
	<script src="<%=request.getContextPath()%>/assets/shop/backProduct/productUpdateStatus.js"> </script>
	<script src="<%=request.getContextPath()%>/assets/shop/backProduct/productUpdateTopStatus.js"> </script>
	
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.all.min.js"></script>
	<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/assets/shop/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script src="<%=request.getContextPath()%>/assets/shop/datetimepicker/time.js"></script>
	
	<script src="<%=request.getContextPath()%>/assets/shop/backProduct/selectSort1Id.js"> </script>
	<script src="<%=request.getContextPath()%>/assets/shop/backNewPage.js"> </script>
	
	
	<!-- 額外添加的JS -->

</body>


</html>