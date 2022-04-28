<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html class="no-js" lang="zh-Hans-TW">

<!-- include <head></head> -->
<jsp:include page="./layout/head.jsp" />

<!-- body start -->
<body>

	<!-- include  common JS-->
	<jsp:include page="./layout/commonJS.jsp" />

	<!-- include  header -->
	<jsp:include page="./layout/header.jsp" />


	<!-- 主要內容 start -->
	<!--breadcrumbs area start-->

	<section class="main_content_area">
        <div class="container">
            <div class="account_dashboard">
                <div class="row">
                    <div class="col-sm-12 col-md-3 col-lg-3">
                        <!-- Nav tabs -->
                        <div class="dashboard_tab_button">
                            <ul role="tablist" class="nav flex-column dashboard-list">
                                <li><a href="#orders" data-toggle="tab" class="nav-link active">Orders</a></li>
                                <li> <a href="#groupOrder" data-toggle="tab" class="nav-link">Group Orders</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-9 col-lg-9">
                        <!-- Tab panes -->
                        <div class="tab-content dashboard_content">
                            <div class="tab-pane fade show active" id="orders">
                                <h3>Orders</h3>
                                <div class="table-responsive">
                                    <table class="table" id="memberOrder">
                                        <thead>
                                            <tr>
                                                <th>訂單編號</th>
                                                <th>價錢</th>
                                                <th>狀態</th>
                                                <th>購買時間</th>
                                                <th>查看詳情</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="groupOrder">
                                <h3>Group Order</h3>
                                <div class="table-responsive">
                                    <table class="table" id="memberGroupOrder">
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
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

	<!-- 主要內容 end -->

	<!-- include  footer -->
	<jsp:include page="./layout/footer.jsp" />

	<!-- 額外添加JS start -->
	<script src="../assets/js/myAccount.js"></script>

	<!-- 額外添加JS end -->

</body>
<!-- body end -->

</html>