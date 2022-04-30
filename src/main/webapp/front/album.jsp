<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/album/album.css" />

<%-- <% Integer memberId=9; %> --%>



<div class="shop_area shop_reverse mt-100 mb-100">
	<div class="container">
		<div class="row">
			<div class="col-12" style="margin: auto">
				<div id="button-area" style="display: block; text-align: right">
					<button id="create-button" class="col-sm-4 addAlbum-button" onclick="addAlbum()"
						style="position: relative; right: 0; border-radius: 30px; height: 50px;">Create
						New Album</button>
				</div>
				<div class="shop_toolbar_wrapper hideZone col-12" id="addZone">
					<div class="blog_thumb col-lg-9" id="cover-preview">
					 <img id="show-img" src="https://cga101-02.s3.ap-northeast-1.amazonaws.com/thumbs/newAlbum.png">
					</div>
					<form id="new-album-form" method="post" action="<%=request.getContextPath()%>/album"
						enctype="multipart/form-data" class="col-lg-3">
						<input type="file" accept="image/*" id="new-album-cover" style="display:none;" onchange="createCoverPreview(this.files)" name="upfile">
						<input type="text" id="new-album-name" placeholder="enter your new album name" name="name">
						<div class="container defined-btn" id="btn-container">
							<div class="product_tab_btn">
								<ul class="nav" role="tablist">
									<li onclick="uploadCover()"><a data-toggle="tab">Cover</a></li>
									<li onclick="commitAlbum()"><a data-toggle="tab">Save</a></li>
								</ul>
							</div>
						</div>
						<input type="hidden" name="action" value="addAlbum">
						<input type="hidden" id="memberId" name="memberId" value="<%=request.getAttribute("memberId")%>" >
						<button type="submit" style="display: none" id="cancel-new-album"></button>
						<button type="submit" style="display: none" id="commit-new-album"></button>
					</form>
				</div>
				<!--shop wrapper start-->
				<div class="shop_toolbar_wrapper">

					<div class="form-select" style="display: flex;">
						<form action="#" style="display: block;">
							<select id="pageSize">
								<option value=9>每頁9筆</option>
								<option value=16>每頁16筆</option>
								<option value=25>每頁25筆</option>
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
			</div>
			<!--shop toolbar end-->

			<div class="row shop_wrapper grid_3 col-12" style="margin-top: 80px"
				id="album-container"></div>



		</div>
	</div>
</div>
<div class="container">
	<div class="container" id="page-root">
		<div class="shop_toolbar t_bottom">
			<div class="pagination">
				<ul id="page-ul">
				</ul>
			</div>
		</div>
	</div>
</div>
<!--shop  area end-->

<script src="<%=request.getContextPath()%>/assets/js/album/album.js"></script>
