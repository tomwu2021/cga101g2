<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link href="<%=request.getContextPath()%>/assets/css/relatioship/relationship.css">
<input type="hidden" id="memberId" value="<%=request.getAttribute("memberId")%>">

<input type="hidden" id="isOwner" value="<%=request.getAttribute("isOwner")%>">

<ul>
    <li class="add_to_cart" onclick="search(1)"><a href="" title="" data-original-title="Add to cart">Friend</a></li>
    <li class="add_to_cart" onclick="search(2)"><a href="" title="" data-original-title="Add to cart">Inviting</a></li>
    <li class="add_to_cart" onclick="search(3)"><a href="" title="" data-original-title="Add to cart">Invited</a></li>
    <li class="add_to_cart" onclick="search(4)"><a href="" title="" data-original-title="Add to cart">BlackList</a></li>
</ul>
<div class="row shop_wrapper grid_list" id="relation-list">
    <div class="col-12">
        <article class="single_product">
            <figure>
                <div class="product_thumb">
                    <a class="primary_img" href="product-details.html"><img src="assets/img/product/product3.jpg" alt=""></a>
                </div>
                <div class="product_content list_content">
                    <div class="product_rating">
                    </div>
                    <h4 class="product_name"><a href="product-details.html">quidem totam, voluptatem quae quasi possimus</a></h4>
                    <div class="product_desc">
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco…</p>
                    </div>
                    <div class="action_links list_action_right">
                        <ul>
                            <li class="add_to_cart"><a href="cart.html" title="" data-original-title="Add to cart">Add to Cart</a></li>
                            <li class="add_to_cart"><a href="cart.html" title="" data-original-title="Add to cart">Add to Cart</a></li>
                            <li class="quick_button"><a href="#" data-toggle="modal" data-target="#modal_box" title="" data-original-title="quick view"> <i class="icon icon-Eye"></i></a></li>
                            <li class="quick_button"><a href="#" data-toggle="modal" data-target="#modal_box" title="" data-original-title="quick view"> <i class="icon icon-Eye"></i></a></li>
                        </ul>
                    </div>
                </div>
            </figure>
        </article>
    </div>
</div>
<script
        src="<%=request.getContextPath()%>/assets/js/relationship/relationship.js"></script>