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
<title>社群主頁</title>

	<!-- -- add -->
    <!-- Bootstrap CSS -->
    <!-- <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
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
    	
    	
    <!--blog area start-->
    <div class="blog_page_section blog_fullwidth mt-100">
        <div class="container">
            <div class="row">
                <div class="col-lg-9 col-md-12">
                    <div class="blog_wrapper">
                    	
                    	<c:forEach var="postVO" items="${postlist}" >
                    	<!-- 範圍 -->
                        <article class="single_blog">
                        
                        <!-- 從這改 -->
                         <!-- <figure>每則整個貼文 -->
                         	
                         	
                            <figure>
                               	<!-- 圖 -->

								<!--  <div class="blog_thumb">
								
								<a href="blog-details.html"><img src="assets/img/blog/blog-big1.jpg" alt=""></a>
								
								</div>  -->
								
								
								 	<figure style="width: 60px;height: 60px;"> 
									<!-- <figcaption>responsive(100*100)</figcaption>  -->
									<img class="img-responsive " src="${postVO.urlList.get(0) }" alt="頭像"/> 
									</figure>
								                   
								                    
						                    <div class="blog_thumb">
                                    		<a href="blog-details.html"><img src="${postVO.pictureVO.url}" alt=""></a>
                                			</div>	
                                			
                                							        
						                  
                                <!-- 整個文 -->
                                <figcaption class="blog_content">
                                   
                                   
                                   <!-- 貼文發布時間 -->
                                    <div class="blog_meta">
                                        <p> <a href="#">${postVO.likeCount}</a> 個讚 &ensp; <a href="#">${postVO.createTime}</a> 
                                    </div>
                                    
                                    <!-- 貼文內容 -->
                                    <p class="post_desc">${postVO.content}</p>
                                    
                                    xxxx ${postVO.membersVO.name}
                                    ${postVO.urlList.get(0) }
                                    
                                    
									
                                    <!-- 按鈕 -->
                                    <footer class="btn_more">
                                        <a href="blog-details.html"> Read more</a>
                                    </footer>
                                    
                                    
                                </figcaption>
                            </figure>
                            
                        </article>
                        </c:forEach>
<!--                         下一則貼文
                        <article class="single_blog">
                            <figure>
                                <div class="blog_thumb">
                                    <a href="blog-details.html"><img src="assets/img/blog/blog-big2.jpg" alt=""></a>
                                </div>
                                <figcaption class="blog_content">
                                   <h4 class="post_title"><a href="blog-details.html"><i class="fa fa-paper-plane"></i>Libero lorem</a></h4>
                                    <div class="blog_meta">
                                        <p>By <a href="#">admin</a> / Date <a href="#">July 16, 2022</a> / Category: <a href="#">eCommerce</a></p>
                                    </div>
                                    <p class="post_desc">Donec vitae hendrerit arcu, sit amet faucibus nisl. Cras pretium arcu ex. Aenean posuere libero eu augue condimentum rhoncus praesent ornare.</p>
                                    <footer class="btn_more">
                                        <a href="blog-details.html"> Read more</a>
                                    </footer>
                                </figcaption>
                            </figure>
                        </article>
                        <article class="single_blog">
                            <figure>
                                <div class="blog_thumb blog_thumb_active owl-carousel">
                                    <div class="single_blog_thumb">
                                        <a href="#"><img src="assets/img/blog/blog-big3.jpg" alt=""></a>
                                    </div>
                                    <div class="single_blog_thumb">
                                        <a href="#"><img src="assets/img/blog/blog-big1.jpg" alt=""></a>
                                    </div>
                                    <div class="single_blog_thumb">
                                        <a href="#"><img src="assets/img/blog/blog-big2.jpg" alt=""></a>
                                    </div>
                                    <div class="single_blog_thumb">
                                        <a href="#"><img src="assets/img/blog/blog-big4.jpg" alt=""></a>
                                    </div>
                                </div>
                                <figcaption class="blog_content">
                                   <h4 class="post_title"><a href="blog-details.html"><i class="fa fa-paper-plane"></i> Post with Gallery</a></h4>
                                    <div class="blog_meta">
                                        <p>By <a href="#">admin</a> / Date <a href="#">July 16, 2022</a> / Category: <a href="#">eCommerce</a></p>
                                    </div>
                                    <p class="post_desc">Donec vitae hendrerit arcu, sit amet faucibus nisl. Cras pretium arcu ex. Aenean posuere libero eu augue condimentum rhoncus praesent ornare.</p>
                                    <footer class="btn_more">
                                        <a href="blog-details.html"> Read more</a>
                                    </footer>
                                </figcaption>
                            </figure>
                        </article>
                        <article class="single_blog">
                            <figure>
                                <div class="blog_thumb">
                                    <a href="blog-details.html"><img src="assets/img/blog/blog-big4.jpg" alt=""></a>
                                </div>
                                <figcaption class="blog_content">
                                   <h4 class="post_title"><a href="blog-details.html"><i class="fa fa-paper-plane"></i> Post with Audio</a></h4>
                                    <div class="blog_meta">
                                        <p>By <a href="#">admin</a> / Date <a href="#">July 16, 2022</a> / Category: <a href="#">eCommerce</a></p>
                                    </div>
                                    <div class="blog_aduio_icone">
                                        <audio controls>
                                          <source src="http://www.jplayer.org/audio/mp3/TSP-01-Cro_magnon_man.mp3?1" type="audio/mp3">
                                        </audio>
                                    </div>
                                    <p class="post_desc">Donec vitae hendrerit arcu, sit amet faucibus nisl. Cras pretium arcu ex. Aenean posuere libero eu augue condimentum rhoncus praesent ornare.</p>
                                    <footer class="btn_more">
                                        <a href="blog-details.html"> Read more</a>
                                    </footer>
                                </figcaption>
                            </figure>
                        </article>
                        <article class="single_blog">
                            <figure>
                                 <div class="blog_thumb">
                                    <iframe src="https://www.youtube.com/embed/2Zt8va_6HRk"  allow="autoplay; encrypted-media" allowfullscreen></iframe>
                                </div>
                                <figcaption class="blog_content">
                                   <h4 class="post_title"><a href="blog-details.html"><i class="fa fa-paper-plane"></i>Post with Video</a></h4>
                                    <div class="blog_meta">
                                        <p>By <a href="#">admin</a> / Date <a href="#">July 16, 2022</a> / Category: <a href="#">eCommerce</a></p>
                                    </div>
                                    <p class="post_desc">Donec vitae hendrerit arcu, sit amet faucibus nisl. Cras pretium arcu ex. Aenean posuere libero eu augue condimentum rhoncus praesent ornare.</p>
                                    <footer class="btn_more">
                                        <a href="blog-details.html"> Read more</a>
                                    </footer>
                                </figcaption>
                            </figure>
                        </article>
                        <article class="single_blog">
                            <figure>
                                <div class="blog_thumb">
                                    <a href="blog-details.html"><img src="assets/img/blog/blog-big5.jpg" alt=""></a>
                                </div>
                                <figcaption class="blog_content">
                                   <h4 class="post_title"><a href="blog-details.html"><i class="fa fa-paper-plane"></i> Maecenas ultricies</a></h4>
                                    <div class="blog_meta">
                                        <p>By <a href="#">admin</a> / Date <a href="#">July 16, 2022</a> / Category: <a href="#">eCommerce</a></p>
                                    </div>
                                    <p class="post_desc">Donec vitae hendrerit arcu, sit amet faucibus nisl. Cras pretium arcu ex. Aenean posuere libero eu augue condimentum rhoncus praesent ornare.</p>
                                    <footer class="btn_more">
                                        <a href="blog-details.html"> Read more</a>
                                    </footer>
                                </figcaption>
                            </figure>
                        </article> -->
                    </div>
                </div>  

                
                <!-- 側邊欄範圍 -->
                
                <div class="col-lg-3 col-md-12">
                    <div class="blog_sidebar_widget">
                    
               <!-- 搜尋區 -->   
                        <div class="widget_list widget_search">
                            <div class="widget_title">
                                <h3>Search</h3>
                            </div>
                            <form action="#">
                                <input placeholder="Search..." type="text">
                                <button type="submit">search</button>
                            </form>
                        </div>
                        
               <!-- recent comments區 -->
                        
                        <div class="widget_list comments">
                           <div class="widget_title">
                                <h3>Recent Comments</h3>
                            </div>
                            <div class="post_wrapper">
                                <div class="post_thumb">
                                    <a href="blog-details.html"><img src="assets/img/blog/comment2.png.jpg" alt=""></a>
                                </div>
                                <div class="post_info">
                                    <span> <a href="#">demo</a> says:</span>
                                    <a href="blog-details.html">Quisque semper nunc</a>
                                </div>
                            </div>
                             <div class="post_wrapper">
                                <div class="post_thumb">
                                    <a href="blog-details.html"><img src="assets/img/blog/comment2.png.jpg" alt=""></a>
                                </div>
                                <div class="post_info">
                                    <span><a href="#">admin</a> says:</span>
                                    <a href="blog-details.html">Quisque orci porta...</a>
                                </div>
                            </div>
                            <div class="post_wrapper">
                                <div class="post_thumb">
                                    <a href="blog-details.html"><img src="assets/img/blog/comment2.png.jpg" alt=""></a>
                                </div>
                                <div class="post_info">
                                    <span><a href="#">demo</a> says:</span>
                                    <a href="blog-details.html">Quisque semper nunc</a>
                                </div>
                            </div>
                            <div class="post_wrapper">
                                <div class="post_thumb">
                                    <a href="blog-details.html"><img src="assets/img/blog/comment2.png.jpg" alt=""></a>
                                </div>
                                <div class="post_info">
                                    <span><a href="#">admin</a> says:</span>
                                    <a href="blog-details.html">Quisque semper nunc</a>
                                </div>
                            </div>
                        </div>
                        
                    <!-- recent posts區 -->   
                        
                        <div class="widget_list widget_post">
                            <div class="widget_title">
                                <h3>Recent Posts</h3>
                            </div>
                            <div class="post_wrapper">
                                <div class="post_thumb">
                                    <a href="blog-details.html"><img src="assets/img/blog/blog1.jpg" alt=""></a>
                                </div>
                                <div class="post_info">
                                    <h4><a href="blog-details.html">Blog image post</a></h4>
                                    <span>March 16, 2022 </span>
                                </div>
                            </div>
                             <div class="post_wrapper">
                                <div class="post_thumb">
                                    <a href="blog-details.html"><img src="assets/img/blog/blog2.jpg" alt=""></a>
                                </div>
                                <div class="post_info">
                                    <h4><a href="blog-details.html">Post with Gallery</a></h4>
                                    <span>March 16, 2022 </span>
                                </div>
                            </div>
                             <div class="post_wrapper">
                                <div class="post_thumb">
                                    <a href="blog-details.html"><img src="assets/img/blog/blog3.jpg" alt=""></a>
                                </div>
                                <div class="post_info">
                                    <h4><a href="blog-details.html">Post with Audio</a></h4>
                                    <span>March 16, 2022 </span>
                                </div>
                            </div>
                             <div class="post_wrapper">
                                <div class="post_thumb">
                                    <a href="blog-details.html"><img src="assets/img/blog/blog4.jpg" alt=""></a>
                                </div>
                                <div class="post_info">
                                    <h4><a href="blog-details.html">Post with Video</a></h4>
                                    <span>March 16, 2022 </span>
                                </div>
                            </div>
                        </div>
                        <div class="widget_list widget_categories">
                            <div class="widget_title">
                                <h3>Categories</h3>
                            </div>
                            <ul>
                                <li><a href="#">Audio</a></li>
                                <li><a href="#">Company</a></li>
                                <li><a href="#">Gallery</a></li>
                                <li><a href="#">Image</a></li>
                                <li><a href="#">Other</a></li>
                                <li><a href="#">Travel</a></li>
                            </ul>
                        </div>
                        <div class="widget_list widget_tag">
                            <div class="widget_title">
                                <h3>Tag products</h3>
                            </div>
                            <div class="tag_widget">
                                <ul>
                                    <li><a href="#">asian</a></li>
                                    <li><a href="#">brown</a></li>
                                    <li><a href="#">euro</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--blog area end-->
    
    <!--blog pagination area start-->
    <div class="blog_pagination">
        <div class="container">
            <div class="row">
                <div class="col-12">
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
            </div>
        </div>
    </div>
    <!--blog pagination area end-->    	



	<!-- 共通的footer start-->
	<%@include file="/front/layout/footer.jsp"%>
			<!-- 共通的footer end-->


	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 共用的JS -->
</body>
</html>