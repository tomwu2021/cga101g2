<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ page import="com.group_buyer.model.*"%>

<%
//接傳回的VO
GroupBuyerVO groupBuyerVO = (GroupBuyerVO) request.getAttribute("groupBuyerVO");
%>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>

<!-- include  header -->
<%@ include file="/front/layout/header.jsp"%>

<!-- 主要內容 start -->
<!--breadcrumbs area start-->
	<div class="breadcrumbs_area">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="breadcrumb_content">
						<h3>Cart</h3>
						<ul>
							<li>訂單</li>
							<li>訂單明細</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="shopping_cart_area mt-100">
		<div class="container">
				<div class="row">
					<div class="col-12">
						<div class="table_desc">
							<div class="cart_page table-responsive">
								<table>
									<thead>
										<tr>
											<th class="product_thumb">Image</th>
											<th class="product_name">Product</th>
											<th class="product-price">Price</th>
											<th class="product_quantity">Quantity</th>
											<th class="product_total">Total</th>
										</tr>
									</thead>
									<tbody>

										<tr>
											<td class="product_thumb"><a href="#"><img
													src="${groupBuyerVO.pictureVO.previewUrl}" alt=""></a></td>
											<td class="product_name">${groupBuyerVO.productVO.productName}</td>
											<td class="product-price">${groupBuyerVO.groupOrderVO.finalPrice}</td>
											<td class="product_quantity">${groupBuyerVO.productAmount}</td>
											<td class="product_total">${groupBuyerVO.groupOrderVO.finalPrice*groupBuyerVO.productAmount}</td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!--coupon code area start-->
				<div class="coupon_area">
					<div class="row">
						<div class="col-lg-6 col-md-6">
							<div class="coupon_code left">
								<h3>收件資訊</h3>
								<div class="cart_subtotal">
									<p>收件人姓名: ${groupBuyerVO.recipients}</p>
									<br>
								</div>
								<br>
								<div class="cart_subtotal">
									<p>收件人電話: ${groupBuyerVO.phone}</p>
									<br>
								</div>
								<br>
								<div class="cart_subtotal">
									<p>收件人地址: ${groupBuyerVO.address}</p>
									<br>
								</div>
							</div>
						</div>
						
						<div class="col-lg-6 col-md-6">
							<div class="coupon_code right">
								<h3>Cart Totals</h3>
								<div class="coupon_inner">
									<div class="cart_subtotal">
										<p>總金額</p>
										<p class="cart_amount">${groupBuyerVO.groupOrderVO.finalPrice*groupBuyerVO.productAmount}</p>
									</div>	
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/groupOrder.do" name="seeMore">
									<div class="checkout_btn" onclick="javascript:document.seeMore.submit();">
									<input type="hidden" name="action" value="seeMore">
									<input type="hidden" name="groupOrderId" value="${groupBuyerVO.groupOrderId}">
										<a href="#">看詳情</a>
									</div>
									</FORM>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/groupOrder.do" name="join">
									<div class="checkout_btn" onclick="javascript:document.join.submit();">
									<input type="hidden" name="action" value="joinGroupOrder">
										<a href="#">到商城</a>
									</div>
									</FORM>
								
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--coupon code area end-->
		</div>
	</div>

<!-- 主要內容 end -->
<%@ include file="/front/layout/commonJS.jsp"%>
<%@ include file="/front/layout/commonCSS.jsp"%>
<!-- include  footer -->
<%@include file="/front/layout/footer.jsp"%>
<!-- 額外添加JS start -->

<!-- 額外添加JS end -->
<jsp:include page="/front/layout/showMessage.jsp" />
</body>
<!-- body end -->

</html>