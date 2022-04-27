<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<html>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- body start -->
<!-- 主要內容 start -->

<body>
<!--breadcrumbs area start-->
<%@ include file="/front/layout/header.jsp"%>
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
<!--footer area start-->
<%@ include file="/front/layout/footer.jsp"%>
<!--footer area end-->
<!--! <li class="quick_button"><a href="#" data-toggle="modal" data-target="#modal_box"  title="quick view"> <i class="icon icon-Eye"></i></a></li> -->

<!-- 共用js 開始-->
<%@ include file="/front/layout/commonJS.jsp"%>
<!--! 共用js 結束-->

<!-- 額外添加js開始 -->
<script src="../index/js/footer.js"></script>
<!--! 菜單點集切換用的js-->
<script src="assets/js/leftnavlink.js"></script>
<!-- 額外添加js結束 -->

</body>
</html>