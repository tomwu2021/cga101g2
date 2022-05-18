<%@page import="java.util.Vector"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>

<!-- include  header -->
<%@ include file="/front/layout/header.jsp"%>

<!-- 取得session內物品 -->
<%
Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shopingCart");
%>
<%
if (buylist != null && (buylist.size() > 0)) {
%>


<!-- 主要內容 start -->
<!--breadcrumbs area start-->
<!-- <FORM METHOD="post" ACTION="/CGA101G2/front/order/myAccount.jsp"> -->
<div class="breadcrumbs_area">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="breadcrumb_content">
					<h3>Cart</h3>
					<ul>
						<li><a href="index.html">home</a></li>
						<li>Shopping Cart</li>
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
			<div class="row">
				<div class="col-12">
					<div class="table_desc">
						<div class="cart_page table-responsive">
							<table>
								<thead>
									<tr>
										<th class="product_remove">Delete</th>
										<th class="product_thumb">Image</th>
										<th class="product_name">Product</th>
										<th class="product-price">Price</th>
										<th class="product_quantity">Quantity</th>
										<th class="product_total">Total</th>
									</tr>
								</thead>
								
								
																<tbody>
									<%
									for (int index = 0; index < buylist.size(); index++) {
										ProductVO productVO = buylist.get(index);
									%>
									
									<tr id="<%= productVO.getProductId() %>">
										<td class="product_remove">
											<a onclick="deleteOne(<%= productVO.getProductId() %>)">
											<i class="fa fa-trash-o"></i>
										</a>
										<!--刪除商品 -->
										<input type="hidden" name="action" value="DELETE">
										<input type="hidden" name="del" value="<%= productVO.getProductId() %>"> 

										</td>
										
										<td class="product_thumb"><a href="#"><img
												src="<%= productVO.getPictureVO().getPreviewUrl()%>" alt=""></a></td>
										<td class="product_name"><a href="#"><%= productVO.getProductName()%></a></td>
										<td class="product-price"><%= productVO.getPrice()%></td>
									
										<td class="product_quantity"><label></label>
										<!--更改購物車數量 -->										
											<input id="pamout-<%= productVO.getProductId() %>" name="count" min="1" max="100" 
											value="<%= productVO.getCartAmount()%>" type="number" onchange="cartChangeCount(<%= productVO.getProductId() %>)">										
										</td>										
										<td class="product_total" id="ptotal-<%= productVO.getProductId() %>"><%= productVO.getCartAmount()*productVO.getPrice()%></td>
										
										
									</tr>
									
									<%
									}
									%>
								</tbody>

							</table>
						</div>
						<Form action="/CGA101G2/member/cart.do" Method="Post">
						<div class="cart_submit">
							<button type="submit">結帳</button>
							 <input type="hidden" name="action" value="TOCHECKOUT">
						</div>
						</Form>
					</div>
				</div>
			</div>
			<%
			}else{
			%>
			<div class="error_section">
	        <div class="container">   
	            <div class="row">
	                <div class="col-12">
	                    <div class="error_form">
	                        <h1>Opps</h1>
	                        <h2>您的購物車還沒有商品</h2>
	                        <p>趕快去商城幫毛小孩買些東西吧</p>
	                        <form action="#">
<!-- 	                         <a href="#" onclick="document.getElementById('backToShop').submit()">回商城</a> -->
 								 <a href="<%=request.getContextPath()%>/shop?action=listProducts_Byfind&sort1_id=1">回商城</a>
	                        </form>
	                       
	                    </div>
	                </div>
	            </div>
	        </div>    
	    </div>
			<%
			}
			%>
			<!-- 			</form> -->
	</div>
</div>

<!-- 主要內容 end -->
<%@ include file="/front/layout/commonJS.jsp"%>
<%@ include file="/front/layout/commonCSS.jsp"%>
<!-- include  footer -->
<%@include file="/front/layout/footer.jsp"%>
<!-- 額外添加JS start -->
<script
		src="<%=request.getContextPath()%>/assets/js/order&cart/cartChangeCount.js">
	</script>
<!-- 額外添加JS end -->
<jsp:include page="/front/layout/showMessage.jsp" />
</body>
<!-- body end -->

</html>