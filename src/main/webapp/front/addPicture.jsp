<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/picture/addPicture.css" />


<%-- <% Integer albumId = 9; %> --%>




<!--services img area-->
<div class="services_gallery mt-100">
	<div class="container">
		<div id="button-area" style="display: block; text-align: right">
			<button class="col-sm-4 back-button" onclick="toGallery()"
				style="position: relative; right: 0; border-radius: 30px; height: 50px;">Back
				To Gallery</button>
		</div>
		<div class="shop_toolbar_wrapper" id="file-zone">
			<form method="POST"
				action="<%=request.getContextPath()%>/PictureController"
				enctype="multipart/form-data"
				style="font-size: 1.2em; letter-spacing: 0.4em; color: black; margin: auto;">
				<span id="upload-word">點擊上傳</span> <input style="display: none"
					type="file" id="file-btn" accept="image/*" multiple="multiple"
					name="upfile" id="upload-input">
				<button style="display: none" id="reset-button" type="reset"></button>
				<button style="display: none" id="save-button" type="submit"></button>
				<input type="hidden" name="action" value="upload"> 
				<input type="hidden" id="memberId" name="memberId" value="<%=request.getAttribute("memberId")%>">
				<input type="hidden" id="albumId" name="albumId" value="<%=request.getAttribute("albumId")%>">
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

<!-- 額外添加JS start -->
<script
	src="<%=request.getContextPath()%>/assets/js/picture/addPicture.js"></script>
<!-- 額外添加JS end -->
