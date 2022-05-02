<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/gallery.css">

<%-- <%=request.getAttribute("memberId")%> --%>

<input type="hidden" id="albumId" name="albumId" value="<%=request.getAttribute("albumId")%>">
<input type="hidden" id="memberId" name="memberId" value="<%=request.getAttribute("memberId")%>">

<div class="services_gallery mt-100">
	<div class="container">
		<div id="button-area" style="display: block; text-align: right">
			<button class="col-sm-4 upload-button" onclick="toAddPicture()"
				style="position: relative; right: 0; border-radius: 30px; height: 50px;">Upload
				Picture</button>
			<div class="col-sm-4"></div>
			<button class="col-sm-4 upload-button" onclick="backToAlbums()"
				style="position: relative; right: 0; border-radius: 30px; height: 50px;">Back
				To My Albums</button>
		</div>
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
		<div id="button-area2" style="display: none">
			<button class="col-sm-1.5" onclick="commitDelete()">Delete</button>
			<span class="col-sm-3"></span>
			<button class="col-sm-1.5" onclick="deleteAll()">Cancel</button>
		</div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/assets/js/picture/gallery.js"></script>

