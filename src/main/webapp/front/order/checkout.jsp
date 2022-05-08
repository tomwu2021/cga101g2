<%@page import="java.util.Vector"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.members.model.*" %>
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
.f13{
	float:left;
	margin-left:5px;
	margin-right:5px;
	width:calc(65% - 10px)
}
</style>
<!-- 取得session內物品 -->
<%
Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shopingCart");
MembersVO membersVO=(MembersVO) session.getAttribute("membersVO");
%>>
<%
if (buylist != null && (buylist.size() > 0)) {
	int count=0;
%>


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
                        <Form action="/CGA101G2/member/cart.do" Method="Post" id="CHECKOUT">
                            <h3>Billing Details</h3>
                            <div class="row">
                                <div class="col-lg-6 mb-20">
                                    <label>姓名 <span>*</span></label>
                                    <input type="text" name="memberName">
                                </div>
                                <div class="col-12 mb-20">
                                    <label>電話<span>*</span></label>
                                    <input type="text" name="memberPhone">
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
                                    <label>使用紅利<span>*</span></label>
                                    	<input min="1" max="<%=membersVO.getBonusAmount() %>" type="number"></td>
                                </div>
						 		
						 		
						 		                              
                                <div class="col-lg-6 mb-20">
                                    <label>請輸入錢包密碼<span>*</span></label>
                                    <input type="password" name="password">
                                </div>                                                     
                            </div>
                            <input type="hidden" name="action" value="CHECKOUT">
                        </form>
                        
                          <Form action="/CGA101G2/member/cart.do" Method="Post">
                         <div class="col-lg-6 mb-20">
                                    <button type="submit">同會員資料</button>
                          </div>    
                          <input type="hidden" name="action" value="MEMBERDATA">
                          </Form>
                            
                    </div>
                    <div class="col-lg-6 col-md-6">
                   
                        
                            <h3>Your order</h3>
                            <div class="order_table table-responsive">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Product</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%
									for (int index = 0; index < buylist.size(); index++) {
										ProductVO productVO = buylist.get(index);
										count+=productVO.getCartAmount()*productVO.getPrice();
									%>
										 <tr>
                                            <td> <%= productVO.getProductName()%> <strong> × <%= productVO.getCartAmount()%></strong></td>
                                            <td> <%= productVO.getCartAmount()*productVO.getPrice()%></td>
                                        </tr>
									<%
									}
									%>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>Cart Subtotal</th>
                                            <td>$<%= count %></td>
                                        </tr>
                                        <tr>
                                            <th>Shipping</th>
                                            <td><strong>$5.00</strong></td>
                                        </tr>
                                         <tr>
                                            <th>Shipping</th>
                                            <td><strong>$5.00</strong></td>
                                        </tr>
                                        <tr class="order_total">
                                            <th>Order Total</th>
                                            <td><strong>$220.00</strong></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div class="payment_method">
                           
                               
                                <div class="order_button">
                                    <button type="submit" onclick="document.getElementById('CHECKOUT').submit()">確認付款</button>
                                </div>
                               
                            </div>
                       
                    </div>
                </div>
            </div>
            <%
			}
			%>
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
<script>
	$("#zipcode3").twzipcode({
		"zipcodeIntoDistrict" : true,
		"css" : [ "city form-control", "town form-control" ],
		countySel : "臺北市", // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
		districtSel : "大安區", // 地區預設值
	});
</script>
<!-- 額外添加JS end -->
<jsp:include page="/front/layout/showMessage.jsp" />
</body>
<!-- body end -->

</html>