<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.product_img.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.product.model.*" %>
<%@ page import="com.group_order.model.*" %>


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
    <script>
        function getContextPath() {
            return "<%=request.getContextPath()%>";
        }
    </script>
</head>


<body>

<!-- 共用的JS -->
<%@include file="/front/layout/commonJS.jsp" %>


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
                        <!--                 <div class="widget_list widget_filter">
                                <h3>Filter by price</h3> -->
                        <!--                                 <form action="#">  -->
                        <!--                                     <div id="slider-range"></div>    -->
                        <!--                                     <button type="submit">Filter</button> -->
                        <!--                                     <input type="text" name="text" id="amount" />    -->

                        <!--                                 </form>  -->
                        <!--                             </div> -->

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
                            <input type="hidden" name="action" value="joinGroupOrder">
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
                    </div>
                    <!--shop toolbar end-->
                    <div class="row shop_wrapper">
                        <!--s內容開始========================-->
<%--						<% List groupOrderVOList=(List)request.getAttribute("groupList");--%>
<%--						    request.setAttribute("groupOrderVOList",groupOrderVOList);--%>
<%--                            for(Object groupOrderVO:groupOrderVOList){--%>
<%--                                groupOrderVO = (GroupOrderVO)groupOrderVO;%>--%>

                        <c:forEach var="groupOrderVO" items="${groupList}">

                            <div class="col-lg-4 col-md-4 col-sm-6 col-12">
                                <!--單一商品開始 -->

                                <article class="single_product">
                                    <figure>
                                        <div class="product_thumb">
                                            <c:if test="${groupOrderVO.productVO.pictureVOList.size() != 0 }">
                                                <a class="primary_img"
                                                   href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${groupOrderVO.productId}&action=getOne_For_GroupShop">
                                                    <img src="${groupOrderVO.productVO.pictureVOList.get(0).previewUrl}"
                                                         alt=""></a>
                                            </c:if>
                                            <c:if test="${groupOrderVO.productVO.pictureVOList.size()>= 2}">
                                                <a class="secondary_img"
                                                   href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${groupOrderVO.productId}&action=getOne_For_GroupShop">
                                                    <img src="${groupOrderVO.productVO.pictureVOList.get(1).previewUrl}"
                                                         alt=""></a>
                                            </c:if>
                                            <div class="action_links">
                                                <ul>
                                                    <li class="quick_button"><a href="#" data-toggle="modal"
                                                                                data-target="#modal_box"
                                                                                title="quick view"> <i
                                                            class="icon icon-Eye"></i></a></li>
                                                    <li class="wishlist"><a href="wishlist.html"
                                                                            title="Add to Wishlist"><i
                                                            class="icon icon-Heart"></i></a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="product_content grid_content">
                                            <h4 class="product_name">
                                                <a href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${groupOrderVO.productId}&action=getOne_For_GroupShop"> ${groupOrderVO.productVO.productName}</a>
                                            </h4>
                                            <div class="price_box">
                                                <span class="old_price">原價${groupOrderVO.productVO.price}元</span>
                                            </div>
                                            <div class="price_box">
                                                <span class="current_price">基本成團價${groupOrderVO.productVO.groupPrice1}元</span>
                                            </div>
                                            <div class="add_to_cart">
                                                <a href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${groupOrderVO.productVO.productId}&action=getOne_For_GroupShop"
                                                   title="Add to cart"> 我要參團 </a>
                                            </div>
                                        </div>
                                        <div class="product_content list_content">
                                            <h4 class="product_name">
                                                <a href="product-details.html">quidem totam, voluptatem quae
                                                    quasi possimus</a>
                                            </h4>
                                            <div class="product_desc">
                                                <p> ${groupOrderVO.productVO.description}</p>
                                            </div>
                                            <div class="price_box">
                                                <span class="current_price">$145.00</span> <span
                                                    class="old_price">$178.00</span>
                                            </div>
                                            <div class="action_links list_action_right">
                                                <ul>
                                                    <li class="quick_button"><a href="#" data-toggle="modal"
                                                                                data-target="#modal_box"
                                                                                title="quick view"> <i
                                                            class="icon icon-Eye"></i></a></li>
                                                    <!-- 									<li class="wishlist"><a href="wishlist.html" -->
                                                    <!-- 										title="Add to Wishlist"><i class="icon icon-Heart"></i></a></li> -->
                                                </ul>
                                            </div>
                                        </div>
                                    </figure>
                                </article>
                            </div><!--單一商品結束 -->
                        </c:forEach>
<%--                        <% } %>--%>
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
    </div>
    <!--shop  area end-->
    <!--product area start-->
<!--product area end-->
<!--! 內容 結束-->
<!-- 共通的footer start-->
<%@include file="/front/layout/footer.jsp" %>
<!-- 共通的footer end-->
<!-- 額外添加的JS -->
<!--leftnavlink.js -->
<script
        src="<%=request.getContextPath()%>/assets/shop/leftnav/leftnavlink.js">
</script>
</body>

</html>