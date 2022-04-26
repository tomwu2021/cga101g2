<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_img.model.*"%>
<%@ page import="com.product.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ProductService pdSvc = new ProductService();
List<ProductVO> pdlist = pdSvc.getAll();
pageContext.setAttribute("pdlist", pdlist);

ProductImgService pdimgSvc = new ProductImgService();
List<ProductImgVO> pdImglist = pdimgSvc.getAll();
pageContext.setAttribute("pdImglist", pdImglist); //蠢!!!!
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--shop wrapper start 右邊菜單內容展示 content是切換點不可更動========================-->
	<!--shop toolbar start-->

	<div class="shop_toolbar_wrapper">
		<div class="shop_toolbar_btn">
			<button data-role="grid_3" type="button" class="active btn-grid-3"
				data-toggle="tooltip" title="3"></button>

			<button data-role="grid_4" type="button" class="btn-grid-4"
				data-toggle="tooltip" title="4"></button>

			<button data-role="grid_list" type="button" class="btn-list"
				data-toggle="tooltip" title="List"></button>
		</div>
		<div class="niceselect_option">
			<form class="select_option" action="#">
				<select name="orderby" id="short">
					<option selected value="1">價格由高到低</option>
					<option value="2">Sort by popularity</option>
					<option value="3">Sort by newness</option>
					<option value="4">Sort by price: low to high</option>
					<option value="5">Sort by price: high to low</option>
					<option value="6">Product Name: Z</option>
				</select>
			</form>
		</div>
		<div class="page_amount">
			<p>Showing 1to9 of 21 results</p>
		</div>
	</div>
	<!--shop toolbar end-->
	<div class="row shop_wrapper">
		<jsp:useBean id="imgSvc" scope="page"
			class="com.product_img.model.ProductImgService" />

		<!--宣告foreach起始點========================-->
<%-- 	<%@ include file="page1.file"%>  --%>
		<c:forEach var="productVO" items="${pdlist}" >
			<div class="col-lg-4 col-md-4 col-sm-6 col-12">
				<!--單一商品開始點 -->
				<article class="single_product">
					<figure>
						<div class="product_thumb">
							<c:set var="imgList" scope="page"
								value="${imgSvc.getImgsByProductId(productVO.productId)}"></c:set>
							<%-- 												<c:forEach var="productImgVO" items="${pdImglist}" > --%>
							<%-- 													<c:if test="${productImgVO.productId == productVO.productId}"> --%>
							<c:if test="${imgList.size() != 0 }">
								<a class="primary_img" href="product-details.html"><img
									src="${imgList.get(0).productImgUrl}" alt=""></a>
								<a class="secondary_img" href="product-details.html"><img
									src="${imgList.get(1).productImgUrl}" alt=""></a>
							</c:if>
							<%-- 													</c:if> --%>
							<%-- 												</c:forEach> --%>
							<div class="action_links">
								<ul>
									<li class="quick_button"><a href="#" data-toggle="modal"
										data-target="#modal_box" title="quick view"> <i
											class="icon icon-Eye"></i></a></li>
									<li class="wishlist"><a href="wishlist.html"
										title="Add to Wishlist"><i class="icon icon-Heart"></i></a></li>
								</ul>
							</div>
						</div>
						<div class="product_content grid_content">
							<h4 class="product_name">
								<a href="product-details.html"> ${productVO.productName}</a>
							</h4>
							<div class="price_box">
								<span class="current_price">${productVO.price}</span>
							</div>
							<div class="add_to_cart">
								<a href="cart.html" title="Add to cart">Add to Cart</a>
							</div>
						</div>
						<div class="product_content list_content">
							<h4 class="product_name">
								<a href="product-details.html">quidem totam, voluptatem quae
									quasi possimus</a>
							</h4>
							<div class="product_desc">
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
									sed do eiusmod tempor incididunt ut labore et dolore magna
									aliqua. Ut enim ad minim veniam, quis nostrud exercitation
									ullamcoâ¦</p>
							</div>
							<div class="price_box">
								<span class="current_price">$145.00</span> <span
									class="old_price">$178.00</span>
							</div>
							<div class="action_links list_action_right">
								<ul>
									<li class="add_to_cart"><a href="cart.html"
										title="Add to cart">Add to Cart</a></li>
									<li class="quick_button"><a href="#" data-toggle="modal"
										data-target="#modal_box" title="quick view"> <i
											class="icon icon-Eye"></i></a></li>
									<li class="wishlist"><a href="wishlist.html"
										title="Add to Wishlist"><i class="icon icon-Heart"></i></a></li>
								</ul>
							</div>
						</div>
					</figure>
				</article>
			</div>
		</c:forEach>
	</div>
<%-- 	<%@ include file="page2.file" %> --%>
	<!--單一商品結束點 -->
<!-- modal area start-->
	<div class="modal fade" id="modal_box" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true"><i class="ion-android-close"></i></span>
				</button>
				<div class="modal_body">
					<div class="container">
						<div class="row">
							<div class="col-lg-5 col-md-5 col-sm-12">
								<div class="modal_tab">
									<div class="tab-content product-details-large">
										<div class="tab-pane fade show active" id="tab1"
											role="tabpanel">
											<div class="modal_tab_img">
												<a href="#"><img
													src="../assets/shop/img/product/productbig1.jpg" alt="" /></a>
											</div>
										</div>
										<div class="tab-pane fade" id="tab2" role="tabpanel">
											<div class="modal_tab_img">
												<a href="#"><img
													src="../assets/shop/img/product/productbig2.jpg" alt="" /></a>
											</div>
										</div>
										<div class="tab-pane fade" id="tab3" role="tabpanel">
											<div class="modal_tab_img">
												<a href="#"><img
													src="../assets/shop/img/product/productbig3.jpg" alt="" /></a>
											</div>
										</div>
										<div class="tab-pane fade" id="tab4" role="tabpanel">
											<div class="modal_tab_img">
												<a href="#"><img
													src="../assets/shop/img/product/productbig4.jpg" alt="" /></a>
											</div>
										</div>
									</div>
									<div class="modal_tab_button">
										<ul class="nav product_navactive owl-carousel" role="tablist">
											<li><a class="nav-link active" data-toggle="tab"
												href="#tab1" role="tab" aria-controls="tab1"
												aria-selected="false"><img
													src="../assets/shop/img/product/product1.jpg" alt="" /></a></li>
											<li><a class="nav-link" data-toggle="tab" href="#tab2"
												role="tab" aria-controls="tab2" aria-selected="false"><img
													src="../assets/shop/img/product/product2.jpg" alt="" /></a></li>
											<li><a class="nav-link button_three" data-toggle="tab"
												href="#tab3" role="tab" aria-controls="tab3"
												aria-selected="false"><img
													src="../assets/shop/img/product/product3.jpg" alt="" /></a></li>
											<li><a class="nav-link" data-toggle="tab" href="#tab4"
												role="tab" aria-controls="tab4" aria-selected="false"><img
													src="../assets/shop/img/product/product8.jpg" alt="" /></a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-lg-7 col-md-7 col-sm-12">
								<div class="modal_right">
									<div class="modal_title mb-10">
										<h2>}</h2>
									</div>
									<div class="modal_price mb-10">
										<span class="new_price"></span> <span class="old_price">$78.99</span>
									</div>
									<div class="modal_description mb-15">
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing
											elit. Mollitia iste laborum ad impedit pariatur esse optio
											tempora sint ullam autem deleniti nam in quos qui nemo ipsum
											numquam, reiciendis maiores quidem aperiam, rerum vel
											recusandae</p>
									</div>
									<div class="variants_selects">
										<div class="modal_add_to_cart">
											<form action="#">
												<input min="1" max="100" step="2" value="1" type="number" />
												<button type="submit">add to cart</button>
											</form>
										</div>
									</div>
									<div class="modal_social">
										<h2>Share this product</h2>
										<ul>
											<li class="facebook"><a href="#"><i
													class="fa fa-facebook"></i></a></li>
											<li class="twitter"><a href="#"><i
													class="fa fa-twitter"></i></a></li>
											<li class="pinterest"><a href="#"><i
													class="fa fa-pinterest"></i></a></li>
											<li class="google-plus"><a href="#"><i
													class="fa fa-google-plus"></i></a></li>
											<li class="linkedin"><a href="#"><i
													class="fa fa-linkedin"></i></a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- modal area end-->

	<div class="shop_toolbar t_bottom">
		<div class="pagination">
			<ul>
				<li class="current">1</li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li class="next"><a href="#">next</a></li>
				<li><a href="#">>></a></li>
			</ul>
		</div>
	</div>
	<!--shop toolbar end-->

</body>
</html>