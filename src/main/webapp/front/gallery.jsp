<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!-- include <head></head> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/gallery.css">

<!--services img area-->
<jsp:include page="./layout/head.jsp" />
<!-- include  common JS-->
<jsp:include page="./layout/commonJS.jsp" />

<!-- include  header -->
<jsp:include page="./layout/header.jsp" />

<div class="services_gallery mt-100">
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
			<div class="form-select" style="display: flex;">
				<form action="#" style="display: block;">
					<select id="uploadTime">
						<option value="1">一天內</option>
						<option value="7">一週內</option>
						<option value="30">一個月內</option>
					</select>
				</form>
			</div>
			<input placeholder="Search..." type="text" id="fileName">
			<div class="page_amount" style="align-self: center">
				<p></p>
			</div>
		</div>
		<div class="row" id="picture-row"></div>
	</div>
</div>
<!--services img end-->
<div class="container" id="page-root">
	<div class="shop_toolbar t_bottom">
		<div class="pagination">
			<ul id="page-ul">
			</ul>
		</div>
	</div>
</div>
<div class="wishlist_area mt-100">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="table_desc wishlist">
					<div class="cart_page table-responsive">
						<table style="display: none">
							<thead>
								<tr>
									<th class="product_remove">FileName</th>
									<th class="product_thumb">Image</th>
									<th class="product_name">Create Time</th>
									<th class="product_total"><a>Restore<a></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="button-area" style="display:none">
		 <button class="col-sm-1.5" onclick="commitDelete()">Delete</button>
		 <span class="col-sm-3"></span>
		 <button class="col-sm-1.5" onclick="deleteAll()">Cancel</button>
		</div>
	</div>
</div>

<!--our services area-->

<a class="upload-button" id="scrollUp" href="<%=request.getContextPath()%>/front/addPicture-copy.jsp"
	style="position: fixed; z-index: 2147483647; display: inline; right: 4px; bottom: 300px;">
	<i class="fa fa-plus"></i>
</a>

<jsp:include page="./layout/footer.jsp" />
<script src="<%=request.getContextPath()%>/assets/js/picture/gallery.js"></script>
