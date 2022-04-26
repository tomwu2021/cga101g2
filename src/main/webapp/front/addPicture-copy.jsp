<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html class="no-js" lang="zh-Hans-TW">

<!-- include <head></head> -->

<jsp:include page="./layout/head.jsp" />
<!-- 額外添加CSS start -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/picture/addPicture.css" />

<!-- 額外添加CSS end -->

<!-- body start -->
<body>
	<!-- 主要內容 start -->
	<!--breadcrumbs area start-->
	<jsp:include page="./layout/header.jsp" />
	
	<div class="breadcrumbs_area">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="breadcrumb_content">
						<h3>Services</h3>
						<ul>
							<li><a href="index.html">home</a></li>
							<li>our services</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--breadcrumbs area end-->


	<!--services img area-->
	<div class="services_gallery mt-100">
		<div class="container">
			<div class="shop_toolbar_wrapper" id="file-zone">
				<form method="POST" action="/CGA101G2/PictureController"
					enctype="multipart/form-data"
					style="font-size: 1.2em; letter-spacing: 0.4em; color: black; margin: auto;">
					<span id="upload-word">點擊上傳</span> <input style="display: none"
						type="file" id="file-btn" accept="image/*" multiple="multiple"
						name="upfile">
					<button style="display: none" id="reset-button" type="reset"></button>
					<button style="display: none" id="save-button" type="submit"></button>
					<input name="albumId" value="9" style="display: none">
				</form>
			</div>
			<div class="row" id="picture-row"></div>
		</div>
	</div>

	<!--services img end-->
	<div class="container defined-btn" id="btn-container">
		<div class="product_tab_btn">
			<ul class="nav" role="tablist">
				<li onclick="cancel()"><a data-toggle="tab"> Cancel </a></li>
				<li onclick="save()"><a data-toggle="tab"> Save </a></li>
			</ul>
		</div>
	</div>

	<!-- 主要內容 end -->

	<!-- include  footer -->
	<jsp:include page="./layout/footer.jsp" />

	<!-- include  common JS-->
	<jsp:include page="./layout/commonJS.jsp" />

	<!-- 額外添加JS start -->
	<script
		src="<%=request.getContextPath()%>/assets/js/picture/addPicture.js"></script>
	<!-- 額外添加JS end -->

</body>
<!-- body end -->

</html>
