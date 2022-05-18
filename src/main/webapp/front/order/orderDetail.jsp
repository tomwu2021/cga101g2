<%@page import="com.orders.model.OrdersVO"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orders.model.*"%>

<%
//接傳回的VO
OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO");
%>
<!-- include <head></head> -->

<!--services img area-->
<%@ include file="/front/layout/head.jsp"%>
<!-- include  common JS-->

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
                            <li onclick="history.back()">訂單</li>
                            <li>訂單明細</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>         
    </div>
    <!--breadcrumbs area end-->
<!--shopping cart area start -->
<div class="shopping_cart_area mt-100">
	<div class="container">
		<form action="#">
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
									<c:forEach var="ordersVO" items="${productDetail}">
										<tr>
											<td class="product_thumb"><a href="#"><img src="${ordersVO.pictureVO.previewUrl}" alt=""></a></td>
											<td class="product_name">${ordersVO.productVO.productName}</td>
											<td class="product-price">${ordersVO.productVO.price}</td>
											<td class="product_quantity">${ordersVO.productAmount}</td>
											<td class="product_total">${ordersVO.orderPrice}</td>
										</tr>
									</c:forEach>
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
									<p>收件人姓名: ${orderDetail.recipient}</p><br>
								</div>
								<br>
								<div class="cart_subtotal">
									<p>收件人電話: ${orderDetail.phone}</p><br>
								</div>
								<br>
								<div class="cart_subtotal">
									<p>收件人地址: ${orderDetail.address}</p><br>
								</div>
                            </div>
                        </div>
					<div class="col-lg-6 col-md-6">
						<div class="coupon_code right">
							<h3>Cart Totals</h3>
							<div class="coupon_inner">
								<div class="cart_subtotal">
									<p>總金額</p>
									<p class="cart_amount">${orderDetail.sumPrice}</p>
								</div>
								<div class="cart_subtotal ">
									<p>紅利折扣</p>
									<p class="cart_amount">-${orderDetail.bonus}</p>
								</div>
								<div class="cart_subtotal ">
									<p>會員等級折扣</p>
									<p class="cart_amount">-${orderDetail.discount}</p>
								</div>
								<hr>					
								<div class="cart_subtotal">
									<p>總計</p>
									<p class="cart_amount">${orderDetail.payPrice}</p>
								</div>
								<div class="checkout_btn" onclick="history.back()">
									<a href="<%=request.getContextPath()%>/shop?action=listProducts_Byfind&sort1_id=1">到商城</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--coupon code area end-->
		</form>
	</div>
</div>
<!--shopping cart area end -->

<!-- 主要內容 end -->
<%@ include file="/front/layout/commonJS.jsp"%>
<%@ include file="/front/layout/commonCSS.jsp"%>
<!-- include  footer -->
<%@include file="/front/layout/footer.jsp"%>
<!-- 額外添加JS start -->
<jsp:include page="/front/layout/showMessage.jsp" />
<!-- 額外添加JS end -->

</body>
<!-- body end -->

</html>