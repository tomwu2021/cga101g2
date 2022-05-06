<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>    
<!DOCTYPE html>
<html>
<head>

<title>社群個人頁面</title>
<!-- 共用的CSS startr-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- -- add -->
    <!-- Bootstrap CSS -->
     <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> -->  

    <!-- Main Style CSS -->
    <link rel="stylesheet" href="other/style copy.css">

</head>
<body>

	<!-- 共用的header start-->
	<%@include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->
	
	<!--! ========內容======== -->
	
	    <!--offcanvas menu area start-->
    <div class="off_canvars_overlay">

    </div>
    <div class="offcanvas_menu">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="canvas_open">
                        <a href="javascript:void(0)"><i class="ion-navicon"></i></a>
                    </div>
                    <div class="offcanvas_menu_wrapper">
                        <div class="canvas_close">
                            <a href="javascript:void(0)"><i class="ion-android-close"></i></a>
                        </div>
                        <div class="header_account_area">
                            <div class="header_account-list search_bar">
                                <a href="javascript:void(0)"><span class="icon icon-Search"></span></a>
                                <div class="dropdown_search">
                                    <div class="search_close_btn">
                                        <a href="#"><i class="ion-close-round"></i></a>
                                    </div>
                                    <form action="#">
                                        <input placeholder="Search entire store here ..." type="text">
                                        <button type="submit"><span class="icon icon-Search"></span></button>
                                    </form>
                                </div>
                            </div>
                            <div class="header_account-list  mini_cart_wrapper">
                                <a href="javascript:void(0)"><i class="icon icon-FullShoppingCart"></i><span
                                        class="item_count">2</span></a>
                                <!--mini cart-->
                                <div class="mini_cart">
                                    <div class="cart_gallery">
                                        <div class="cart_item">
                                            <div class="cart_img">
                                                <a href="#"><img src="assets/img/s-product/product.jpg" alt=""></a>
                                            </div>
                                            <div class="cart_info">
                                                <a href="#">Lorem ipsum dolor sit amet, consectetur</a>
                                                <p>1 x <span> $65.00 </span></p>
                                            </div>
                                            <div class="cart_remove">
                                                <a href="#"><i class="ion-ios-close-outline"></i></a>
                                            </div>
                                        </div>
                                        <div class="cart_item">
                                            <div class="cart_img">
                                                <a href="#"><img src="assets/img/s-product/product2.jpg" alt=""></a>
                                            </div>
                                            <div class="cart_info">
                                                <a href="#">impedit dolor sed soluta natus voluptas.</a>
                                                <p>1 x <span> $60.00 </span></p>
                                            </div>
                                            <div class="cart_remove">
                                                <a href="#"><i class="ion-ios-close-outline"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mini_cart_table">
                                        <div class="cart_table_border">
                                            <div class="cart_total">
                                                <span>Sub total:</span>
                                                <span class="price">$125.00</span>
                                            </div>
                                            <div class="cart_total mt-10">
                                                <span>total:</span>
                                                <span class="price">$125.00</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mini_cart_footer">
                                        <div class="cart_button">
                                            <a href="cart.html"><i class="fa fa-shopping-cart"></i> View cart</a>
                                        </div>
                                        <div class="cart_button">
                                            <a href="checkout.html"><i class="fa fa-sign-in"></i> Checkout</a>
                                        </div>

                                    </div>
                                </div>
                                <!--mini cart end-->
                            </div>
                            <div class="header_account-list top_links">
                                <a href="javascript:void(0)"><i class="icon icon-Settings"></i></a>
                                <div class="dropdown_links">
                                    <div class="dropdown_links_list">
                                        <h3>$Currency</h3>
                                        <ul>
                                            <li><a href="#">€ Euro</a></li>
                                            <li><a href="#">£ Pound Sterling</a></li>
                                            <li><a href="#">$ US Dollar</a></li>
                                        </ul>
                                    </div>
                                    <div class="dropdown_links_list">
                                        <h3><img src="assets/img/icon/language.png" alt=""> English</h3>
                                        <ul>
                                            <li><a href="#"><img src="assets/img/icon/language.png" alt=""> English</a>
                                            </li>
                                            <li><a href="#"><img src="assets/img/icon/language2.png" alt=""> Germany</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="dropdown_links_list">
                                        <h3>$Currency</h3>
                                        <ul>
                                            <li><a href="#">€ Euro</a></li>
                                            <li><a href="#">£ Pound Sterling</a></li>
                                            <li><a href="#">$ US Dollar</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="menu" class="text-left ">
                            <ul class="offcanvas_main_menu">
                                <li class="menu-item-has-children active">
                                    <a href="#">Home</a>
                                    <ul class="sub-menu">
                                        <li><a href="index.html">Home 1</a></li>
                                        <li><a href="index-2.html">Home 2</a></li>
                                        <li><a href="index-3.html">Home 3</a></li>
                                        <li><a href="index-4.html">Home 4</a></li>
                                    </ul>
                                </li>
                                <li class="menu-item-has-children">
                                    <a href="#">Shop</a>
                                    <ul class="sub-menu">
                                        <li class="menu-item-has-children">
                                            <a href="#">Shop Layouts</a>
                                            <ul class="sub-menu">
                                                <li><a href="shop.html">shop</a></li>
                                                <li><a href="shop-fullwidth.html">Full Width</a></li>
                                                <li><a href="shop-fullwidth-list.html">Full Width list</a></li>
                                                <li><a href="shop-right-sidebar.html">Right Sidebar </a></li>
                                                <li><a href="shop-right-sidebar-list.html"> Right Sidebar list</a></li>
                                                <li><a href="shop-list.html">List View</a></li>
                                            </ul>
                                        </li>
                                        <li class="menu-item-has-children">
                                            <a href="#">other Pages</a>
                                            <ul class="sub-menu">
                                                <li><a href="cart.html">cart</a></li>
                                                <li><a href="wishlist.html">Wishlist</a></li>
                                                <li><a href="checkout.html">Checkout</a></li>
                                                <li><a href="my-account.html">my account</a></li>
                                                <li><a href="404.html">Error 404</a></li>
                                            </ul>
                                        </li>
                                        <li class="menu-item-has-children">
                                            <a href="#">Product Types</a>
                                            <ul class="sub-menu">
                                                <li><a href="product-details.html">product details</a></li>
                                                <li><a href="product-sidebar.html">product sidebar</a></li>
                                                <li><a href="product-grouped.html">product grouped</a></li>
                                                <li><a href="variable-product.html">product variable</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                                <li class="menu-item-has-children">
                                    <a href="#">blog</a>
                                    <ul class="sub-menu">
                                        <li><a href="blog.html">blog</a></li>
                                        <li><a href="blog-details.html">blog details</a></li>
                                        <li><a href="blog-fullwidth.html">blog fullwidth</a></li>
                                        <li><a href="blog-sidebar.html">blog sidebar</a></li>
                                    </ul>

                                </li>
                                <li class="menu-item-has-children">
                                    <a href="#">pages </a>
                                    <ul class="sub-menu">
                                        <li><a href="about.html">About Us</a></li>
                                        <li><a href="services.html">services</a></li>
                                        <li><a href="faq.html">Frequently Questions</a></li>
                                        <li><a href="contact.html">contact</a></li>
                                        <li><a href="login.html">login</a></li>
                                        <li><a href="404.html">Error 404</a></li>
                                    </ul>
                                </li>
                                <li class="menu-item-has-children">
                                    <a href="contact.html"> Contact Us</a>
                                </li>
                                <li class="menu-item-has-children">
                                    <a href="shop.html"> Specials</a>
                                </li>
                            </ul>
                        </div>

                        <div class="offcanvas_footer">
                            <span><a href="#"><i class="fa fa-envelope-o"></i> info@yourdomain.com</a></span>
                            <ul>
                                <li class="facebook"><a href="#"><i class="fa fa-facebook"></i></a></li>
                                <li class="twitter"><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li class="pinterest"><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
                                <li class="google-plus"><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                <li class="linkedin"><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    	<!--offcanvas menu area end-->
    	
    	<!--breadcrumbs area start-->
    <div class="breadcrumbs_area">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="breadcrumb_content">
                        <h3>Blog</h3>
                        <ul>
                            <li><a href="index.html">home</a></li>
                            <li>blog</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    	<!--breadcrumbs area end-->
    	
    	
    	<!--blog area start-->
   <!--  <div class="blog_page_section mt-100"></div>
    <div class="container"> -->
										         <%-- <div class="row">
										        
										        	 <c:forEach var="postVO" items="${list}" > 	
										            <div class="col-12 col-md-4">
										                <div class="item h-100">
										                  
															<c:if test="${postVO.pictureList.size() != 0 }">
																<c:forEach var="postVO" items="${list}" >
																<img src="${postVO.pictureList.get(0).previewUrl}" class="w-100">
																</c:forEach>
										        			</c:if>
										        		
										        		</div>
									        	</div>
										      		 </c:forEach> 
										          
										        </div>  --%>
										        
    <!--shop  area start-->
    <div class="shop_area shop_reverse mt-100 mb-100">
        <div class="container">
            <div class="row">
                <!-- <div class="col-lg-3 col-md-12">
                    
                </div> -->
                <div class="col-lg-12 col-md-12">
                    <!--shop wrapper start-->
                    <!--shop toolbar start-->
                    <div class="shop_toolbar_wrapper">
                        <div class="shop_toolbar_btn">

                            <button data-role="grid_3" type="button" class="active btn-grid-3" data-toggle="tooltip"
                                title="3"></button>

                            <button data-role="grid_4" type="button" class=" btn-grid-4" data-toggle="tooltip"
                                title="4"></button>

                            <button data-role="grid_list" type="button" class="btn-list" data-toggle="tooltip"
                                title="List"></button>
                        </div>
                        <div class=" niceselect_option">
                            <form class="select_option" action="#">
                                <select name="orderby" id="short">

                                    <option selected value="1">Sort by average rating</option>
                                    <option value="2">Sort by popularity</option>
                                    <option value="3">Sort by newness</option>
                                    <option value="4">Sort by price: low to high</option>
                                    <option value="5">Sort by price: high to low</option>
                                    <option value="6">Product Name: Z</option>
                                </select>
                            </form>
                        </div>
                        <div class="page_amount">
                            <p>Showing 1–9 of 21 results</p>
                        </div>
                    </div>
                    <!--shop toolbar end-->
                    <div class="row shop_wrapper">
                     	<!-- 範圍開始 -->
                     	<c:forEach var="postVO" items="${list}" > 
                        <div class="col-lg-4 col-md-4 col-sm-6 col-12 ">
                        	
                            <article class="single_product">
                                <figure>
                                	<!-- 起始 -->
                                    <div class="product_thumb">
                                    
                                   		<!--  new \ 10%  -->
                                        <div class="label_product"> 
                                            <span class="label_new">new</span>
                                            <span class="label_sale">10%</span>
                                        </div> 
                                        
                                        <!-- 圖 -->
                                       <%--  <c:forEach var="postVO" items="${list}" >  --%>
                                        <c:if test="${postVO.pictureList.size() != 0 }">
                                        <a class="primary_img" href="product-details.html"><img
                                                src="${postVO.pictureList.get(0).previewUrl}" alt=""></a>
                                        </c:if>
                                          <%-- </c:forEach> --%>   
                                               
                                                <%-- <c:forEach var="postVO" items="${list}" > 
                                                
                                               	 <c:if test="${postVO.pictureList.size() != 0 }">
													<img src="${postVO.pictureList.get(0).previewUrl}" class="w-100">																
										         </c:if>
										        
										        </c:forEach>  --%>
										        
                                        <!-- 效果圖  -->      
                                        <a class="secondary_img" href="product-details.html"><img
                                                src="assets/img/product/product4.jpg" alt=""></a>
                                        <div class="action_links">
                                            <ul>
                                                <li class="quick_button"><a href="#" data-toggle="modal"
                                                        data-target="#modal_box" title="quick view"> <i
                                                            class="icon icon-Eye"></i></a></li>
                                                <li class="wishlist"><a href="wishlist.html" title="Add to Wishlist"><i
                                                            class="icon icon-Heart"></i></a></li>
                                                <li class="compare"><a href="#" title="Add to Compare"><i
                                                            class="icon icon-MusicMixer"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                   	<!--  整個文字框 -->
                                    <div class="product_content grid_content">
                                        <div class="product_rating">
                                            <ul>
                                                <li><a href="#"><i class="icon icon-Star"></i></a></li>
                                                <li><a href="#"><i class="icon icon-Star"></i></a></li>
                                                <li><a href="#"><i class="icon icon-Star"></i></a></li>
                                                <li><a href="#"><i class="icon icon-Star"></i></a></li>
                                                <li><a href="#"><i class="icon icon-Star"></i></a></li>
                                            </ul>
                                        </div>
                                        <h4 class="product_name"><a href="product-details.html"> quidem totam,
                                                voluptatem quae quasi possimus iusto!</a></h4>
                                        <div class="price_box">
                                            <span class="current_price">$145.00</span>
                                            <span class="old_price">$178.00</span>
                                        </div>
                                        <div class="add_to_cart">
                                            <a href="cart.html" title="Add to cart">Add to Cart</a>
                                        </div>
                                        <div class="swatches-colors">
                                            <ul>
                                                <li><a class="color1" href="javascript:void(0)"></a></li>
                                                <li><a class="color2" href="javascript:void(0)"></a></li>
                                                <li><a class="color3" href="javascript:void(0)"></a></li>
                                                <li><a class="color4" href="javascript:void(0)"></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product_content list_content">
                                        <div class="swatches-colors">
                                            <ul>
                                                <li><a class="color1" href="javascript:void(0)"></a></li>
                                                <li><a class="color2" href="javascript:void(0)"></a></li>
                                                <li><a class="color4" href="javascript:void(0)"></a></li>
                                                <li><a class="color3" href="javascript:void(0)"></a></li>
                                            </ul>
                                        </div>
                                        <div class="product_rating">
                                            <ul>
                                                <li><a href="#"><i class="icon icon-Star"></i></a></li>
                                                <li><a href="#"><i class="icon icon-Star"></i></a></li>
                                                <li><a href="#"><i class="icon icon-Star"></i></a></li>
                                                <li><a href="#"><i class="icon icon-Star"></i></a></li>
                                                <li><a href="#"><i class="icon icon-Star"></i></a></li>
                                            </ul>
                                        </div>
                                        <h4 class="product_name"><a href="product-details.html">quidem totam, voluptatem
                                                quae quasi possimus</a></h4>
                                        <div class="product_desc">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                                                veniam, quis nostrud exercitation ullamco…</p>
                                        </div>
                                        <div class="price_box">
                                            <span class="current_price">$145.00</span>
                                            <span class="old_price">$178.00</span>
                                        </div>

                                        <div class="action_links list_action_right">
                                            <ul>
                                                <li class="add_to_cart"><a href="cart.html" title="Add to cart">Add to
                                                        Cart</a></li>
                                                <li class="quick_button"><a href="#" data-toggle="modal"
                                                        data-target="#modal_box" title="quick view"> <i
                                                            class="icon icon-Eye"></i></a></li>
                                                <li class="wishlist"><a href="wishlist.html" title="Add to Wishlist"><i
                                                            class="icon icon-Heart"></i></a></li>
                                                <li class="compare"><a href="#" title="Add to Compare"><i
                                                            class="icon icon-MusicMixer"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </figure>
                            </article>
                        </div>
                        
                        <!-- 範圍結束 -->
                        </c:forEach>
                        
                        
                            
                        </div>
                    </div>
					  
					  
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
    <!--shop  area end-->
    
		 
	<!--! 內容 結束-->


		<!-- 共通的footer start-->
		<%@include file="/front/layout/footer.jsp"%>
		<!-- 共通的footer end-->


	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 共用的JS -->
</body>
</html>