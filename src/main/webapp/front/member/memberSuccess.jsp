<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS startr-->
<%@include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<!-- 	路徑舉例 -->
<%-- <link rel="stylesheet"href="<%=request.getContextPath()%>/assets/?????待討論"> --%>
<!-- 額外添加的CSS -->
<body>
	<!-- 共用的header start-->
	<%@ include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->

	<!-- 共用的leftnav start-->
	<%@include file="/front/member/layout/leftnav.jsp"%>
	<!-- 共用的leftnav end-->

	<!--! ========內容======== -->
	<div class="page-holder w-100 d-flex flex-wrap">
		<div class="container-fluid px-xl-5">
			<section>
				<div class="page-holder w-100 d-flex flex-wrap">
					<div class="container-fluid px-xl-5">
						<section class="py-5">
							<div class="row">
								<div class="col-lg-10 mb-5">
								<img src="https://imageio.forbes.com/specials-images/dam/imageserve/1133672681/960x0.jpg?format=jpg&amp;width=960" style="width:80%" alt="成功！">

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


</body>

</html>