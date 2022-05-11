<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product_img.model.*"%>
<%@ page import="java.util.List" %>
<%@ page import="com.product.model.*"%>

<jsp:useBean id="listProducts_Byfind" scope="request" type="java.util.List<ProductVO>" /> <!-- 於EL此行可省略 -->
<html>
<head>
<title>商品總覽</title>
<!-- 共用的CSS startr-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<!-- 	路徑舉例 -->
<%-- <link rel="stylesheet"href="<%=request.getContextPath()%>/assets/back/css/????.css"> --%>

<script>
    function getContextPath(){
     return "<%=request.getContextPath()%>";
	}
</script>


</head>
<body>
	<!-- 共用的JS -->
	<%@include file="/front/layout/commonShopJS.jsp"%>
	<!-- 共用的JS -->
	
<!-- 	<script -->
<%-- 		src="<%=request.getContextPath()%>/assets/js/addToCart.js"> --%>
<!-- 	</script> -->
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
							<li><a href="<%=request.getContextPath()%>/shop?action=listProducts_Byfind">寵物商城</a></li>
							<li>商品專區</li>
						</ul>
<!-- 						放分類的地方 -->
						<c:if test="${not empty thisSort1VO.sort1Name || not empty thisSort1VO.sort1Name }">
						<ul>
							<li>${thisSort1VO.sort1Name}${thisSort1VO.sort1Name}專區</li>
							<li>${thisSort2VO.sort2Name}</li>
						</ul>
						</c:if>
<!-- 						放分類的地方 -->						
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
							<c:set var="sort1VOList"  scope="page" value="${sort1VOListIncludesort2VOList}" />
							
							<div class="widget_list widget_color">
									<h3>
										<a href="<%=request.getContextPath()%>/shop?action=listProducts_Byfind">全部商品</a>
									</h3>
								</div>
							<c:forEach var="sort1VO" items="${sort1VOList}" >
							<div class="widget_list widget_color ">
										<h3>
											<a href="<%=request.getContextPath()%>/shop?action=listProducts_Byfind&sort1_id=${sort1VO.sort1Id}">
											${sort1VO.sort1Name}${sort1VO.sort1Name}專區</a>
										</h3>
								<ul>
							<c:forEach var="sort2VO" items="${sort1VO.sort2VOList}" >
								<li>
									<a href="<%=request.getContextPath()%>/shop?action=listProducts_Byfind&sort1_id=${sort1VO.sort1Id}&sort2_id=${sort2VO.sort2Id}" >${sort2VO.sort2Name} </a>
								</li>
							</c:forEach>
							    </ul>
							</div>
							</c:forEach>
							<!-- 結束 -->
							<div class="widget_list shopside_banner">
								<div class="banner_thumb">
									<a href="#"><img src=""
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
						
							<!--shop wrapper start ========================-->
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
					<option selected value="1">價格由高到低</option>
					<option value="2">Sort by popularity</option>
					<option value="3">Sort by newness</option>
					<option value="4">Sort by price: low to high</option>
					<option value="5">Sort by price: high to low</option>
					<option value="6">Product Name: Z</option>
				</select>
			</form>
		</div>
<%@ include file="pages/shop2/page1_ByCompositeQuery.file" %> 
	</div>
	<!--shop toolbar end-->
	<div class="row shop_wrapper">
		<!--內容開始========================-->
		<c:forEach var="productVO" items="${listProducts_Byfind}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<div class="col-lg-4 col-md-4 col-sm-6 col-12">
				<!--單一商品開始 -->
				<Form id="${productVO.productId}" action="/CGA101G2/member/cart.do" Method="Post">
				<article class="single_product">
					<figure>
						<div class="product_thumb">
							<c:if test="${productVO.pictureVOList.size() != 0 }">
								<a class="primary_img" href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop">
								<img src="${productVO.pictureVOList.get(0).previewUrl}" alt=""></a>
							</c:if>	
<%-- 							<c:if test="${productVO.pictureVO.size() >= 1  && productVO.pictureVO.size()!=0}"> --%>
							<c:if test="${productVO.pictureVOList.size() >= 2  && productVO.pictureVOList.size()!=0}">
								<a class="secondary_img" href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop">
								<img src="${productVO.pictureVOList.get(1).previewUrl}" alt=""></a>
							</c:if>	
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
								<a href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop"> ${productVO.productName}</a>
							</h4>
							<div class="price_box">
								<span class="current_price">${productVO.price}元</span>
							</div>
							<div class="add_to_cart">								
								<a title="Add to cart" onclick="addToCart(${productVO.productId})">Add to Cart</a>
							</div>
						</div>
						<div class="product_content list_content">
							<h4 class="product_name">
								<a href="product-details.html">quidem totam, voluptatem quae
									quasi possimus</a>
							</h4>
							<div class="product_desc">
								<p> ${productVO.description}</p>
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
				 <input id="pid-${productVO.productId}" type="hidden" name="productId" value="${productVO.productId}">
				 <input id="pname-${productVO.productId}" type="hidden" name="productName" value="${productVO.productName}">
				 <input id="pprice-${productVO.productId}" type="hidden" name="productPrice" value="${productVO.price}">
				 <input id="purl-${productVO.productId}" type="hidden" name="productPictureUrl" value="${productVO.pictureVOList.get(0).previewUrl}">
				 <input id="pamout-${productVO.productId}" type="hidden" name="productAmout" value="1">
				</Form>
			</div>
		</c:forEach>
	</div>
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop"></FORM>	
<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
<%@ include file="pages/shop2/page2_ByCompositeQuery.file" %>
	<!--單一商品結束 -->
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
													src="" alt="" /></a>
											</div>
										</div>
										<div class="tab-pane fade" id="tab2" role="tabpanel">
											<div class="modal_tab_img">
												<a href="#"><img
													src="" alt="" /></a>
											</div>
										</div>
										<div class="tab-pane fade" id="tab3" role="tabpanel">
											<div class="modal_tab_img">
												<a href="#"><img
													src=" alt="" /></a>
											</div>
										</div>
										<div class="tab-pane fade" id="tab4" role="tabpanel">
											<div class="modal_tab_img">
												<a href="#"><img
													src="" alt="" /></a>
											</div>
										</div>
									</div>
									<div class="modal_tab_button">
										<ul class="nav product_navactive owl-carousel" role="tablist">
											<li><a class="nav-link active" data-toggle="tab"
												href="#tab1" role="tab" aria-controls="tab1"
												aria-selected="false"><img
													src="" alt="" /></a></li>
											<li><a class="nav-link" data-toggle="tab" href="#tab2"
												role="tab" aria-controls="tab2" aria-selected="false"><img
													src="" alt="" /></a></li>
											<li><a class="nav-link button_three" data-toggle="tab"
												href="#tab3" role="tab" aria-controls="tab3"
												aria-selected="false"><img
													src="" alt="" /></a></li>
											<li><a class="nav-link" data-toggle="tab" href="#tab4"
												role="tab" aria-controls="tab4" aria-selected="false"><img
													src="" alt="" /></a></li>
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
										<span class="new_price">${productVO.price}元</span>
									</div>
									<div class="modal_description mb-15">
										<p> >${productVO.description}</p>
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
						<!--shop wrapper end-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--shop  area end-->
    <!--product area start-->
    <div class="product_area  mb-100">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section_title">
                       <h2><span>推薦</span>商城主打</h2>
                       <p>寵物熱門推薦</p>
                    </div>
                </div>
            </div> 
            <div class="row">
                <div class="product_carousel product_column4 owl-carousel">
                  <!--單一商品循環 start-->
                  <c:forEach var="productVO" items="${topProdcutList}">
                    <div class="col-lg-3">
                        <article class="single_product">
                            <figure>
                                <div class="product_thumb">
                                <!--商品照片裡的span標籤 start-->
                                 <div class="label_product">
                                       <span class="label_new">TOP</span>
                                 </div>
                                <!--商品照片裡的span標籤 end--> 
                                 <!--商品照片點 start-->
                                <c:if test="${productVO.pictureVOList.size() != 0 }">
                                    <a class="primary_img" href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop">
									<img src="${productVO.pictureVOList.get(0).previewUrl}" alt=""></a>
                                </c:if>	    
                                 <c:if test="${productVO.pictureVOList.size() >= 2  && productVO.pictureVOList.size()!=0}">
									<a class="secondary_img" href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop">
									<img src="${productVO.pictureVOList.get(1).previewUrl}" alt=""></a>
								</c:if>	
                                 <!--商品照片點 end-->   
                                    <div class="action_links">
                                        <ul>
                                            <li class="quick_button">
                                            <a href="#" data-toggle="modal" data-target="#modal_box"  title="quick view"> 
                                            <i class="icon icon-Eye"></i></a></li>
                                            <li class="wishlist">
                                            <a href="wishlist.html" title="Add to Wishlist">
                                            <i class="icon icon-Heart"></i></a></li>  
                                        </ul>
                                    </div>
                                </div>
                                <figcaption class="product_content">
                                    <h4 class="product_name">
                                    	<a href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop"> 
                                    	${productVO.productName}</a>
                                    </h4>
                                    <div class="price_box"> 
                                        <span class="current_price">${productVO.price}元</span>
                                    </div>
                                    <div class="add_to_cart">
                                        <a href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop" 
                                        title="Look More">Look More</a>
                                    </div>
                                </figcaption>
                            </figure>
                        </article>
                    </div> 
                    </c:forEach>
                    <!--單一商品循環 end-->
                </div> 
            </div>
        </div> 
    </div>
    <!--product area end-->
	<!--! 內容 結束-->


	<!-- 共通的footer start-->
	<%@include file="/front/layout/footer.jsp"%>
	<!-- 共通的footer end-->

	<!-- 額外添加的JS -->
	<!--leftnavlink.js -->
	
	<%@include file="/front/layout/templateJS.jsp"%>
	
	<script
		src="<%=request.getContextPath()%>/assets/shop/leftnav/leftnavlink.js">
	</script>
	
	<script 
		src="<%=request.getContextPath()%>/assets/shop/wishlist.js"> 
	</script>
	<!-- leftnavlink.js -->

</body>

</html>