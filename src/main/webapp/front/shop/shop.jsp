<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>商品總覽</title>
<!-- 共用的CSS startr-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<!-- 	路徑舉例 -->
<%-- <link rel="stylesheet"href="<%=request.getContextPath()%>/assets/back/css/????.css"> --%>
<!-- 額外添加的CSS -->
</head>
<body>
	<!-- 共用的header start-->
	<%@include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->

	<!-- 共用的CartAndWishlist-->
	<%@include file="/front/layout/commonCartAndWishlist.jsp"%>
	<!-- 共用的CartAndWishlist-->

	<!--! ========內容======== -->
	<!--breadcrumbs area start-->
<div class="breadcrumbs_area">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="breadcrumb_content">
					<h3>商品專區</h3>
					<ul>
						<li><a href="index.html">寵物商城</a></li>
						<li>商品專區</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<!--breadcrumbs area end-->
<!--shop  area start-->
<div class="shop_area shop_reverse mt-100 mb-100">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-12">
				<!--sidebar widget start ========================================= -->
				<aside class="sidebar_widget">
					<div class="widget_inner">
						<!--                 <div class="widget_list widget_filter">
<!--                                 <h3>Filter by price</h3> -->
						<!--                                 <form action="#">  -->
						<!--                                     <div id="slider-range"></div>    -->
						<!--                                     <button type="submit">Filter</button> -->
						<!--                                     <input type="text" name="text" id="amount" />    -->

						<!--                                 </form>  -->
						<!--                             </div> -->
						<div id="NewFile.jsp" class="widget_list widget_color">
							<h3>
								<a href="#" onclick="showAtRight('NewFile.jsp')">全部商品</a>
							</h3>
						</div>
						<div class="widget_list widget_color"></div>
						<div class="widget_list widget_color"></div>
						<div class="widget_list widget_color"></div>
						<div class="widget_list widget_color">
							<!-- 結束 -->
						</div>
						<div class="widget_list shopside_banner">
							<div class="banner_thumb">
								<a href="#"><img src="../assets/shop/img/bg/banner12.jpg"
									alt="" /></a>
							</div>
						</div>
					</div>
				</aside>
				<!--sidebar widget end-->
			</div>

			<div class="col-lg-9 col-md-12">
				<!--shop wrapper start 右邊菜單內容展示 content是切換點不可更動========================-->
				<div id="content">
					<!--shop toolbar start-->
					<%@ include file="allproduct.jsp"%>
					<!--shop toolbar end-->
					<!--shop wrapper end-->
				</div>
			</div>
		</div>
	</div>
</div>
<!--shop  area end-->
<!--product area start-->
<div class="product_area mb-100">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="section_title">
					<h2>
						<span>推薦商品</span>
					</h2>
					<p>Lorem ipsum dolor sit amet, consectetur elit.</p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="product_carousel product_column4 owl-carousel">
				<div class="col-lg-3">
					<article class="single_product">
						<figure>
							<div class="product_thumb">
								<a class="primary_img" href="product-details.html"><img
									src="../assets/shop/img/product/product5.jpg" alt="" /></a> <a
									class="secondary_img" href="product-details.html"><img
									src="../assets/shop/img/product/product6.jpg" alt="" /></a>
								<div class="action_links">
									<ul>
										<li class="quick_button"><a href="#" data-toggle="modal"
											data-target="#modal_box" title="quick view"> <i
												class="icon icon-Eye"></i>
										</a></li>
										<li class="wishlist"><a href="wishlist.html"
											title="Add to Wishlist"><i class="icon icon-Heart"></i></a></li>
									</ul>
								</div>
							</div>
							<figcaption class="product_content">
								<h4 class="product_name">
									<a href="product-details.html"> suscipit tempora delectus
										officia a, possimus at ipsam.</a>
								</h4>
								<div class="price_box">
									<span class="current_price">$142.00</span> <span
										class="old_price">$173.00</span>
								</div>
								<div class="add_to_cart">
									<a href="cart.html" title="Add to cart">Add to Cart</a>
								</div>
							</figcaption>
						</figure>
					</article>
				</div>
				<div class="col-lg-3">
					<article class="single_product">
						<figure>
							<div class="product_thumb">
								<a class="primary_img" href="product-details.html"><img
									src="../assets/shop/img/product/product10.jpg" alt="" /></a> <a
									class="secondary_img" href="product-details.html"><img
									src="../assets/shop/img/product/product9.jpg" alt="" /></a>
								<div class="action_links">
									<ul>
										<li class="quick_button"><a href="#" data-toggle="modal"
											data-target="#modal_box" title="quick view"> <i
												class="icon icon-Eye"></i>
										</a></li>
										<li class="wishlist"><a href="wishlist.html"
											title="Add to Wishlist"><i class="icon icon-Heart"></i></a></li>
									</ul>
								</div>
							</div>
							<figcaption class="product_content">
								<h4 class="product_name">
									<a href="product-details.html"> modi nisi cum officia error
										possimus, unde ipsam is.!</a>
								</h4>
								<div class="price_box">
									<span class="current_price">$142.00</span> <span
										class="old_price">$173.00</span>
								</div>
								<div class="add_to_cart">
									<a href="cart.html" title="Add to cart">Add to Cart</a>
								</div>
							</figcaption>
						</figure>
					</article>
				</div>
				<div class="col-lg-3">
					<article class="single_product">
						<figure>
							<div class="product_thumb">
								<a class="primary_img" href="product-details.html"><img
									src="../assets/shop/img/product/product1.jpg" alt="" /></a> <a
									class="secondary_img" href="product-details.html"><img
									src="../assets/shop/img/product/product2.jpg" alt="" /></a>
								<div class="action_links">
									<ul>
										<li class="quick_button"><a href="#" data-toggle="modal"
											data-target="#modal_box" title="quick view"> <i
												class="icon icon-Eye"></i>
										</a></li>
										<li class="wishlist"><a href="wishlist.html"
											title="Add to Wishlist"><i class="icon icon-Heart"></i></a></li>
									</ul>
								</div>
							</div>
							<figcaption class="product_content">
								<h4 class="product_name">
									<a href="product-details.html">Lorem ipsum dolor sit amet,
										elit, the display aliquid!</a>
								</h4>
								<div class="price_box">
									<span class="current_price">$142.00</span> <span
										class="old_price">$173.00</span>
								</div>
								<div class="add_to_cart">
									<a href="cart.html" title="Add to cart">Add to Cart</a>
								</div>
							</figcaption>
						</figure>
					</article>
				</div>
				<div class="col-lg-3">
					<article class="single_product">
						<figure>
							<div class="product_thumb">
								<a class="primary_img" href="product-details.html"><img
									src="../assets/shop/img/product/product3.jpg" alt="" /></a> <a
									class="secondary_img" href="product-details.html"><img
									src="../assets/shop/img/product/product4.jpg" alt="" /></a>
								<div class="action_links">
									<ul>
										<li class="quick_button"><a href="#" data-toggle="modal"
											data-target="#modal_box" title="quick view"> <i
												class="icon icon-Eye"></i>
										</a></li>
										<li class="wishlist"><a href="wishlist.html"
											title="Add to Wishlist"><i class="icon icon-Heart"></i></a></li>
									</ul>
								</div>
							</div>
							<figcaption class="product_content">
								<h4 class="product_name">
									<a href="product-details.html"> quidem totam, voluptatem
										quae quasi possimus iusto!</a>
								</h4>
								<div class="price_box">
									<span class="current_price">$142.00</span> <span
										class="old_price">$173.00</span>
								</div>
								<div class="add_to_cart">
									<a href="cart.html" title="Add to cart">Add to Cart</a>
								</div>
							</figcaption>
						</figure>
					</article>
				</div>
				<div class="col-lg-3">
					<article class="single_product">
						<figure>
							<div class="product_thumb">
								<a class="primary_img" href="product-details.html"><img
									src="../assets/shop/img/product/product5.jpg" alt="" /></a> <a
									class="secondary_img" href="product-details.html"><img
									src="../assets/shop/img/product/product6.jpg" alt="" /></a>
								<div class="action_links">
									<ul>
										<li class="quick_button"><a href="#" data-toggle="modal"
											data-target="#modal_box" title="quick view"> <i
												class="icon icon-Eye"></i>
										</a></li>
										<li class="wishlist"><a href="wishlist.html"
											title="Add to Wishlist"><i class="icon icon-Heart"></i></a></li>
									</ul>
								</div>
							</div>
							<figcaption class="product_content">
								<h4 class="product_name">
									<a href="product-details.html">iusto dignissimos illum.
										Quis provident ratione maiores.</a>
								</h4>
								<div class="price_box">
									<span class="current_price">$142.00</span> <span
										class="old_price">$173.00</span>
								</div>
								<div class="add_to_cart">
									<a href="cart.html" title="Add to cart">Add to Cart</a>
								</div>
							</figcaption>
						</figure>
					</article>
				</div>
			</div>
		</div>
	</div>
</div>
<!--product area end-->
	<!--! 內容 結束-->


	<!-- 共通的footer start-->
	<%@include file="/front/layout/footer.jsp"%>
	<!-- 共通的footer end-->


	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 共用的JS -->

	<!-- 額外添加的JS -->
	<!-- 	路徑舉例 -->
	<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/back/js/?????.js"> --%>
	<!-- 額外添加的JS -->

</body>

</html>