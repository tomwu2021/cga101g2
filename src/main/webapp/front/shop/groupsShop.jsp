<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.product_img.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.product.model.*" %>

<%--<jsp:useBean id="listGroupProducts_Byfind" scope="request" type="java.util.List<ProductVO>"/>--%>
<!-- 於EL此行可省略 -->
<html>
<head>
    <title>團購商品總覽</title>
    <!-- 共用的CSS startr-->
    <%@include file="/front/layout/commonCSS.jsp" %>
    <!-- 共用的CSS end-->

    <!-- 額外添加的CSS -->
    <!-- 	路徑舉例 -->
    <%-- <link rel="stylesheet"href="<%=request.getContextPath()%>/assets/back/css/????.css"> --%>
    <!-- 額外添加的CSS -->
</head>
<body>


<!-- 共用的header start-->
<%@include file="/front/layout/header.jsp" %>
<!-- 共用的header end-->

<!-- 共用的CartAndWishlist-->
<%@include file="/front/layout/commonCartAndWishlist.jsp" %>
<!-- 共用的CartAndWishlist-->

<!--! ========內容======== -->
<!--breadcrumbs area start-->
<div class="breadcrumbs_area">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="breadcrumb_content">
                    <h3>團購專區</h3>
                    <ul>
                        <li><a href="index.html">寵物商城</a></li>
                        <li>團購專區</li>
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
                        <div class="widget_list widget_color">
                            <h3>
                                <a href="#">我要開團</a>
                            </h3>
                        </div>

                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/groupOrder.do" name="join">
                            <div class="widget_list widget_color">
                                <h3>
                                    <a href="javascript:document.join.submit();">我要跟團</a>
                                </h3>
                            </div>
                        </FORM>
                        <div class="widget_list widget_color"></div>
                        <div class="widget_list widget_color"></div>
                        <div class="widget_list widget_color">
                            <!-- 結束 -->
                        </div>
                        <div class="widget_list shopside_banner">
                            <div class="banner_thumb">
                                <a href="#"><img src=""
                                                 alt=""/></a>
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

                    <!--shop wrapper start 嚙賢�莎蕭嚙踝蕭嚙踝蕭獢�嚙賣�寞����嚙質�瘀蕭 content嚙質�穿蕭嚙踝蕭嚙賣�綽蕭��嚙踝蕭��嚙賣虜嚙踝蕭========================-->
                    <!--shop toolbar start-->
                    <div class="shop_toolbar_wrapper">
                        <div class="form-select" style="display: flex;">
                            <form action="#" style="display: block;">
                                <select id="pageSize">
                                    <option value=9>每頁9筆</option>
                                    <option value=18>每頁18筆</option>
                                    <option value=27>每頁27筆</option>
                                </select>
                            </form>
                        </div>
                        <div class="form-select" style="display: flex;">
                            <form action="#" style="display: block;">
                                <select id="sort">
                                    <option value="DESC">按上架時間排序</option>
                                    <option value="DESC">由新到舊</option>
                                    <option value="ASC">由舊到新</option>
                                </select>
                            </form>
                        </div>
                        <input placeholder="Search..." type="text" id="productName">
                        <div class="page_amount" style="align-self: center">
                            <p></p>
                        </div>
                    </div>
<%--                    <%@ include file="pages/groupShop/page1_ByCompositeQuery.file" %>--%>
                </div>
                <!--shop toolbar end-->
                <div class="row shop_wrapper col-12" id="group-shop-product">
                    <!--s內容開始========================-->
                    <%--		<c:forEach var="productVO" items="${listGroupProducts_Byfind}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">--%>
                    <div class="col-lg-4 col-md-4 col-sm-6 col-12">
                        <!--單一商品開始 -->
                        <article class="single_product">
                            <figure>
                                <%--						<div class="product_thumb">--%>
                                <%--							<c:if test="${productVO.pictureVOList.size() != 0 }">--%>
                                <%--								<a class="primary_img" href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_GroupShop">--%>
                                <%--								<img src="${productVO.pictureVOList.get(0).previewUrl}" alt=""></a>--%>
                                <%--							</c:if>	--%>
                                <%--&lt;%&ndash; 							<c:if test="${productVO.pictureVO.size() >= 1  && productVO.pictureVO.size()!=0}"> &ndash;%&gt;--%>
                                <%--							<c:if test="${productVO.pictureVOList.size() >= 2  && productVO.pictureVOList.size()!=0}">--%>
                                <%--								<a class="secondary_img" href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_GroupShop">--%>
                                <%--								<img src="${productVO.pictureVOList.get(1).previewUrl}" alt=""></a>--%>
                                <%--							</c:if>	--%>
                                <%--							<div class="action_links">--%>
                                <%--								<ul>--%>
                                <%--									<li class="quick_button"><a href="#" data-toggle="modal"--%>
                                <%--										data-target="#modal_box" title="quick view"> <i--%>
                                <%--											class="icon icon-Eye"></i></a></li>--%>
                                <%--									<li class="wishlist"><a href="wishlist.html"--%>
                                <%--										title="Add to Wishlist"><i class="icon icon-Heart"></i></a></li>--%>
                                <%--								</ul>--%>
                                <%--							</div>--%>
                                <%--						</div>--%>
                                <%--						<div class="product_content grid_content">--%>
                                <%--							<h4 class="product_name">--%>
                                <%--								<a href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_GroupShop"> ${productVO.productName}</a>--%>
                                <%--							</h4>--%>
                                <%--							<div class="price_box">--%>
                                <%--								<span class="old_price">原價${productVO.price}元</span>--%>
                                <%--							</div>--%>
                                <%--							<div class="price_box">--%>
                                <%--								<span class="current_price">基本成團價${productVO.groupPrice1}元</span>--%>
                                <%--							</div>--%>
                                <%--							<div class="add_to_cart">--%>
                                <%--								<a href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_GroupShop" --%>
                                <%--								title="Add to cart"> 我要開團 </a>--%>
                                <%--							</div>--%>
                                <%--						</div>--%>
                                <%--						<div class="product_content list_content">--%>
                                <%--							<h4 class="product_name">--%>
                                <%--								<a href="product-details.html">quidem totam, voluptatem quae--%>
                                <%--									quasi possimus</a>--%>
                                <%--							</h4>--%>
                                <%--							<div class="product_desc">--%>
                                <%--								<p> ${productVO.description}</p>--%>
                                <%--							</div>--%>
                                <%--							<div class="price_box">--%>
                                <%--								<span class="current_price">$145.00</span> <span--%>
                                <%--									class="old_price">$178.00</span>--%>
                                <%--							</div>--%>
                                <%--							<div class="action_links list_action_right">--%>
                                <%--								<ul>--%>
                                <%--									<li class="quick_button"><a href="#" data-toggle="modal"--%>
                                <%--										data-target="#modal_box" title="quick view"> <i--%>
                                <%--											class="icon icon-Eye"></i></a></li>--%>
                                <%--<!-- 									<li class="wishlist"><a href="wishlist.html" -->--%>
                                <%--<!-- 										title="Add to Wishlist"><i class="icon icon-Heart"></i></a></li> -->--%>
                                <%--								</ul>--%>
                                <%--							</div>--%>
                                <%--						</div>--%>
                            </figure>
                        </article>
                    </div>
                    <!--單一商品結束 -->
                    <%--		</c:forEach>--%>
                </div>
                <%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/shop/ProductAll"></FORM>	--%>
                <%--<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->--%>
                <%--<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->--%>
                <%--<%@ include file="pages/groupShop/page2_ByCompositeQuery.file" %>--%>


                <%@include file="/front/layout/searchPage.jsp" %>
                <!--shop toolbar end-->
                <!--shop wrapper end-->
                <%--					</div>--%>
                <%--				</div>--%>
            </div>
        </div>
    </div>
</div>
<!--shop  area end-->
<!--! 內容 結束-->


<!-- 共通的footer start-->
<%@include file="/front/layout/footer.jsp" %>
<!-- 共通的footer end-->

<!-- 共用的JS -->
<%@include file="/front/layout/commonJS.jsp" %>
<!-- 共用的JS -->

<!-- 額外添加的JS -->
<!--leftnavlink.js -->
<script
        src="<%=request.getContextPath()%>/assets/shop/leftnav/leftnavlink.js">
</script>
<!-- leftnavlink.js -->
<script
        src="<%=request.getContextPath()%>/assets/js/groupShop/groupShop.js">
</script>
</body>

</html>