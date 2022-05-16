<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>後台員工查詢</title>
<!-- 共用的CSS startr-->
<%@include file="/back/layout/commonCSS.jsp"%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/empSelect.css">

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

								<div class="form-group row" style="width: 400px">
									<label class="col-md-3 form-control-label">帳號查詢</label> <input
										type="text" id="account"> <input type="submit"
										value="確定" class="btn btn-primary" onclick="accountSelect()">
								</div>
								<br>
								<div class="form-group row" style="width: 400px">
									<label class="col-md-3 form-control-label">姓名查詢</label> <input
										type="text" id="name"> <input type="submit" value="確定"
										class="btn btn-primary" onclick="nameSelect()">
								</div>
								<div class="form-group row" style="width: 400px; float:right;">
									 <input
										type="submit" value="顯示所有會員" class="btn btn-primary"
										onclick="selectAll()" ">
								</div>

								<div id="show"></div>



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
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/assets/js/empSelect.js"></script>

</body>

</html>