<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<!-- include <head></head> -->

<!--services img area-->
<%@ include file="/front/layout/head.jsp"%>
<!-- include  common JS-->

<!-- include  header -->
<%@ include file="/front/layout/header.jsp"%>

<!-- 主要內容 start -->
<!--breadcrumbs area start-->

<section class="main_content_area">
	<div class="container">

		<div class="shop_toolbar_wrapper">
			<div class="form-select" style="display: flex;">
				<form action="#" style="display: block;">
					<select id="pageSize">
						<option value=12>每頁12筆</option>
						<option value=24>每頁24筆</option>
						<option value=36>每頁36筆</option>
					</select>
				</form>
			</div>
			<div class="form-select" style="display: flex;">
				<form action="#" style="display: block;">
					<select id="sort">
						<option value="">按時間排序</option>
						<option value="DESC">由新到舊</option>
						<option value="ASC">由舊到新</option>
					</select>
				</form>
			</div>
<!-- 			<div class="form-select" style="display: flex;"> -->
<!-- 				<form action="#" style="display: block;"> -->
<!-- 					<select id="endTime"> -->
<!-- 						<option value="30">請選擇區間</option> -->
<!-- 						<option value="1">一天內</option> -->
<!-- 						<option value="7">一週內</option> -->
<!-- 						<option value="30">一個月內</option> -->
<!-- 						<option value="90">三個月內</option> -->
<!-- 					</select> -->
<!-- 				</form> -->
<!-- 			</div> -->
			<div class="form-select" style="display: flex;">
				<form action="#" style="display: block;">
					<select id="status">
						<option value="0">請選擇區間</option>
						<option value="0">進行中</option>
						<option value="1">未成團</option>
						<option value="2">已成團</option>
					</select>
				</form>
				<div class="page_amount" style="align-self: center"></div>
			</div>
		</div>

		<div class="account_dashboard">
			<div class="row">
				<div class="col-sm-12 col-md-2 col-lg-2">
					<!-- Nav tabs -->
					<div class="dashboard_tab_button">
						<ul role="tablist" class="nav flex-column dashboard-list">
							<li onclick="go2Order()"><a href="#orders" data-toggle="tab"
								class="nav-link" style="text-align: center;">Orders</a></li>
							<li onclick="go2GroupOrder()"><a href="#groupOrder"
								data-toggle="tab" class="nav-link active"
								style="text-align: center;">Group Orders</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-12 col-md-10 col-lg-10">
					<!-- Tab panes -->
					<div class="tab-content dashboard_content">
						<div class="tab-pane fade show active" id="orders">
							<h3>Group Order</h3>
							<div class="table-responsive">
								<table class="table" >
									<thead>
										<tr>
											<th>商品名稱</th>
											<th>訂購數量</th>
											<th>價錢</th>
											<th>狀態</th>
											<th>修改訂單</th>
											<th>查看詳情</th>
										</tr>
									</thead>
									<tbody id="memberGroupOrder">
									</tbody>

								</table>
							</div>
						</div>



						<!-- 						<div class="tab-pane fade" id="groupOrder"> -->
						<!-- 							<h3>Group Order</h3> -->
						<!-- 							<div class="table-responsive"> -->
						<!-- 								<table class="table" id="memberGroupOrder"> -->
						<!-- 									<thead> -->
						<!-- 										<tr> -->
						<!-- 											<th>商品名稱</th> -->
						<!-- 											<th>訂購數量</th> -->
						<!-- 											<th>價錢</th> -->
						<!-- 											<th>狀態</th> -->
						<!-- 											<th>修改訂單</th> -->
						<!-- 											<th>查看詳情</th> -->
						<!-- 										</tr> -->
						<!-- 									</thead> -->

						<!-- 								</table> -->
						<!-- 							</div> -->
						<!-- 						</div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- 主要內容 end -->
<%@ include file="/front/layout/commonJS.jsp"%>
<%@ include file="/front/layout/commonCSS.jsp"%>
<!-- include  footer -->

<%@include file="/front/layout/searchPage.jsp"%>
<%@include file="/front/layout/footer.jsp"%>

<!-- 額外添加JS start -->
<script
	src="<%=request.getContextPath()%>/assets/js/order&cart/myAccountGroup.js"></script>


<!-- 額外添加JS end -->

</body>
<!-- body end -->

</html>