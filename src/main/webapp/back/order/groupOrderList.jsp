<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>後台團購訂單列表</title>
<!-- 共用的CSS startr-->
<%@include file="/back/layout/commonCSS.jsp"%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/groupOrderList.css">

</head>
<body>
	<!-- 共用的JS -->
	<%@include file="/back/layout/commonJS.jsp"%>
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


								<!-- 								<div class="form-group row" style="width: 400px"> -->
								<!-- 									<label class="col-md-3 form-control-label">帳號查詢</label> <input -->
								<!-- 										type="text" id="account"> <input type="submit" -->
								<!-- 										value="確定" class="btn btn-primary" onclick="accountSelect()"> -->
								<!-- 								</div> -->

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

<jsp:include page="/front/layout/showMessage.jsp" />

	<!-- 共用的JS -->

	<!-- 額外添加的JS -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/assets/js/groupOrderList.js"></script>
	
</body>

</html>