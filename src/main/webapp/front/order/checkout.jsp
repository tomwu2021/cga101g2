<%@page import="java.util.Vector"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.ranks.model.*" %>
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
                        <Form action="<%=request.getContextPath()%>/member/cart.do" Method="Post" id="CHECKOUT">
                            <h3>Billing Details</h3>
                            <div class="row">
                            
                                <div class="col-lg-6 mb-20" id="mname">
                                    <label>姓名 <span>*</span></label>
                                    <input type="text" name="memberName" id="memberName">
                                </div>
                                <div class="col-12 mb-20" id="mpho">
                                    <label>電話<span>*</span></label>
                                    <input type="text" name="memberPhone" id="memberPhone">
                                </div>
                       			<div class="col-12 mb-20" id=made>
                       			  <label>地址<span>*</span></label>
								 <div id="zipcode3">
									<div class="f3" data-role="county"></div>
									<div class="f4" data-role="district"></div>
									</div>
									<input type="text" name="address" class="form-control"
									id="exampleFormControlInput1">
						 		</div>

						 		
						 		<div class="col-12 mb-20">
                                    <input id="address" type="checkbox" data-target="createp_account" name="same" style="display:none"/>
                                    <label class="righ_0" for="address" data-toggle="collapse"
                                        data-target="#collapsetwo" aria-controls="collapseOne">等同會員資料</label>

                                    <div id="collapsetwo" class="collapse one" data-parent="#accordion">
                                        <div class="row">
                                                                                                                                                                        
                                           <div class="col-lg-6 mb-20">
			                                    <label>姓名 </label>
			                                    <input type="text" value="<%=membersVO.getName()%>" readonly="readonly">
			                                </div>
			                                <div class="col-12 mb-20">
			                                    <label>電話</label>
			                                    <input type="text" value="<%=membersVO.getPhone() %>" readonly="readonly">
			                                </div>
			                                 <div class="col-12 mb-20">
			                                    <label>地址</label>
			                                    <input type="text" value="<%=membersVO.getAddress() %>" readonly="readonly">
			                                </div>
			                                
                                        </div>
                                    </div>
                                </div>
						 		
						 		
						 		 <%
						 		 //防止紅利折扣比訂單金額高
									for (int index = 0; index < buylist.size(); index++) {
										ProductVO productVO = buylist.get(index);
										count+=productVO.getCartAmount()*productVO.getPrice();
									}
						 		 int maxBonus;
						 		 int discount=(Integer)request.getAttribute("discount");
						 		 if(membersVO.getBonusAmount()>count-discount){
						 		 	maxBonus=count-discount;
						 		 }else{
						 			 maxBonus=membersVO.getBonusAmount();
						 		 }
								%>
										
						 		<div class="col-12 mb-20">
                                    <label>使用紅利<span>*</span></label>
                                    	<input min="0" max="<%=maxBonus %>" value="<%=maxBonus %>" type="number" name="bonus" id="bonusCount" 
                                    	oninput="if(value><%=maxBonus %>)value=<%=maxBonus %>;if(value<0)value=0;" onchange="bonusChange()">
                                </div>
						 		
						 		 <input type="hidden" name="action" value="CHECKOUT">
                        </form>
						 		       	                       
                                <div class="col-lg-6 mb-20">
                                    <label>請輸入錢包密碼<span>*</span></label>
                                    <input type="password" name="password" id="password">
                                </div> 
                                
                                 <div class="order_button" id="check">
                                 <br>
                                    <button type="button" onclick="check()">密碼驗證</button>
                                </div>                                                    
                            </div>
                      		
                                    
                            
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
                                            <th>商品金額</th>
                                            <td id="total"><%= count %></td>
                                        </tr>
                                        <tr>
                                            <th>會員折扣</th>
                                            <td id="discount"><strong>-${discount}</strong></td>
                                        </tr>
                                         <tr>
                                            <th>紅利折扣</th>
                                            <td id="bonus"><strong>-<%=maxBonus %></strong></td>
                                        </tr>
                                        <tr class="order_total">
                                            <th>Order Total</th>
                                            <td id="payPrice"><strong>$220.00</strong></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div class="payment_method">
                           
                               
                                <div class="order_button" id="submit" style="display:none">
                                    <button type="submit" onclick="checking()">確認付款</button>
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
<script src="<%=request.getContextPath()%>/assets/js/jquery.twzipcode.min.js"></script>

<!-- 額外添加JS end -->
<script
		src="<%=request.getContextPath()%>/assets/js/order&cart/checkout.js">
</script>
<jsp:include page="/front/layout/showMessage.jsp" />
</body>
<!-- body end -->

</html>