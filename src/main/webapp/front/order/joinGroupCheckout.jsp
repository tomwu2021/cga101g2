<%@page import="java.util.Vector"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.members.model.*"%>

<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>

<!-- include  header -->
<%@ include file="/front/layout/header.jsp"%>
<style>
.f3 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(15% - 10px)
}

.f4 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(20% - 10px)
}

.f13 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(65% - 10px)
}
</style>
<!-- 取得session內物品 -->
<%
MembersVO membersVO = (MembersVO) session.getAttribute("membersVO");
%>>


<!-- 主要內容 start -->
<!--breadcrumbs area start-->
<div class="breadcrumbs_area">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="breadcrumb_content">
					<h3>Checkout</h3>
					<ul>
						<li><a href="index.html">home</a></li>
						<li>Checkout</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<!--breadcrumbs area end-->

<!--Checkout page section-->
<div class="Checkout_section mt-100">
	<div class="container">
		<div class="checkout_form">
			<div class="row">
				<div class="col-lg-6 col-md-6">
					<Form action="<%=request.getContextPath()%>/member/groupOrder.do"
						Method="Post" id="CHECKOUT">
						<h3>Billing Details</h3>
						<div class="row">

							<div class="col-lg-6 mb-20">
								<label>姓名 <span>*</span></label> <input type="text"
									name="memberName">
							</div>
							<div class="col-12 mb-20">
								<label>電話<span>*</span></label> <input type="text"
									name="memberPhone">
							</div>
							<div class="col-12 mb-20">
								<label>地址<span>*</span></label>
								<div id="zipcode3">
									<div class="f3" data-role="county"></div>
									<div class="f4" data-role="district"></div>
								</div>
								<input type="text" name="address" class="form-control"
									id="exampleFormControlInput1">
							</div>


							<div class="col-12 mb-20">
								<input id="address" type="checkbox"
									data-target="createp_account" name="same" style="display:none"/> <label
									class="righ_0" for="address" data-toggle="collapse"
									data-target="#collapsetwo" aria-controls="collapseOne">等同會員資料</label>

								<div id="collapsetwo" class="collapse one"
									data-parent="#accordion">
									<div class="row">

										<div class="col-lg-6 mb-20">
											<label>姓名</label> <input type="text"
												value="<%=membersVO.getName()%>" readonly="readonly">
										</div>
										<div class="col-12 mb-20">
											<label>電話</label> <input type="text"
												value="<%=membersVO.getPhone()%>" readonly="readonly">
										</div>
										<div class="col-12 mb-20">
											<label>地址</label> <input type="text"
												value="<%=membersVO.getAddress()%>" readonly="readonly">
										</div>

									</div>
								</div>
							</div>


							<div class="col-12 mb-20">
								<label>購買份數<span>*</span></label> <input min="1" max="999"
									value="1" type="number" id="count" name="amount"
									onchange="amountCount();"
									oninput="if(value>999)value=999;if(value<0)value=0;">
							</div>



							<div class="col-lg-6 mb-20">
								<label>請輸入錢包密碼<span>*</span></label> <input type="password"
									name="password" id="password">
							</div>
							<div class="order_button" id="check">
								<br>
								<button type="button" onclick="check()">密碼驗證</button>
							</div>
						</div>
						<input type="hidden" name="groupOrderId"
							value="${groupOrderVO.groupOrderId}"> <input
							type="hidden" name="endType" value="${groupOrderVO.endType}">
						<input type="hidden" name="price"
							value="${groupOrderVO.productVO.groupPrice1}"> <input
							type="hidden" name="minAmount" value=" ${groupOrderVO.minAmount}">
						<input type="hidden" name="action" value="joinCheckout">
					</form>


				</div>
				<div class="col-lg-6 col-md-6">


					<h3>Your order</h3>
					<div class="order_table table-responsive">
						<table>
							<thead>
								<tr>
									<th>Product</th>
									<th>Price</th>

								</tr>
							</thead>
							<tbody>

								<tr>
									<td><strong>
											${groupOrderVO.productVO.productName} </strong></td>
									<td id="price"><strong>${groupOrderVO.productVO.groupPrice1}</strong></td>

								</tr>

							</tbody>
							<tfoot>

								<tr>
									<th>購買數量</th>
									<td id="amount"><strong></strong></td>
								</tr>
								<tr class="order_total">
									<th>總價</th>
									<td id="payPrice"><strong></strong></td>
								</tr>
							</tfoot>
						</table>
					</div>
					<div class="payment_method">


						<div class="order_button" style="display:none">
							<button type="submit" onclick="checking()">確認付款</button>
						</div>

					</div>

				</div>
			</div>
		</div>

	</div>
</div>

<!-- 主要內容 end -->
<%@ include file="/front/layout/commonJS.jsp"%>
<%@ include file="/front/layout/commonCSS.jsp"%>
<!-- include  footer -->
<%@include file="/front/layout/footer.jsp"%>
<!-- 額外添加JS start -->
<script
	src="<%=request.getContextPath()%>/assets/js/jquery.twzipcode.min.js"></script>

<!-- 額外添加JS end -->
<script
	src="<%=request.getContextPath()%>/assets/js/order&cart/groupCheckout.js">
	
</script>
<jsp:include page="/front/layout/showMessage.jsp" />
</body>
<!-- body end -->

</html>