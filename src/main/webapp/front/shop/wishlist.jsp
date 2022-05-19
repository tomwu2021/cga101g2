<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>收藏清單</title>

<!-- 共用的CSS startr-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<!-- 	路徑舉例 -->
<%-- <link rel="stylesheet"href="<%=request.getContextPath()%>/assets/back/css/????.css"> --%>
<!-- 額外添加的CSS -->

<script>
    function getContextPath(){
     return "<%=request.getContextPath()%>";
	}
</script>

</head>
<body>
		<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<script
		src="<%=request.getContextPath()%>/assets/js/order&cart/addToCart.js">
	</script>
	<!-- 共用的header start-->
	<%@include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->

	<!-- 共用的CartAndWishlist-->
	<%@include file="/front/layout/commonCartAndWishlist.jsp"%>
	<!-- 共用的CartAndWishlist-->


	<!--! ========內容======== -->
	  <!--如果沒有任何收藏商品 開始-->
	   <!--error section area start-->
	<c:if test="${wishList.size() == 0}">   
    <div class="error_section">
        <div class="container">   
            <div class="row">
                <div class="col-12">
                    <div class="error_form">
                        <h1>Opps!</h1>
                         <p>您尚未有任何收藏商品!<br>
                        <h2>快來看看這些推薦商品吧!</h2>
<!--                         <form action="#"> -->
<!--                             <input placeholder="Search..." type="text"> -->
<!--                             <button type="submit"><i class="icon-search"></i></button> -->
<!--                         </form> -->
                       <a href="<%=request.getContextPath()%>/shop?action=listProducts_Byfind" style="width: 300px;height: 50px;"> <font size="5">點擊前往商城頁面</font></a>
                    </div>
                </div>
            </div>
        </div>    
    </div>
     <!--error section area end-->
      <!--如果沒有任何收藏商品 結束-->
	<!--! ========內容 結束========-->
    </c:if>	
	
	
	<c:if test="${wishList.size() != 0}"> 
	<!--! ========內容======== -->
	   <!--如果有收藏清單 開始-->
	<!--breadcrumbs area start-->
    <div class="breadcrumbs_area">
        <div class="container">   
            <div class="row">
                <div class="col-12">
                    <div class="breadcrumb_content">
                       <h3>收藏清單</h3>
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/shop?action=listProducts_Byfind">商品專區</a></li>
                            <li>收藏清單</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>         
    </div>
    <!--breadcrumbs area end-->
    
    <!--wishlist area start -->
    <div class="wishlist_area mt-100">
        <div class="container">   
            <form action="#"> 
                <div class="row">
                    <div class="col-12">
                        <div class="table_desc wishlist">
                            <div class="cart_page table-responsive">
                                <table>
                                    <thead>
                                        <tr>
                                            <th class="product_remove">刪除</th>
                                            <th class="product_thumb">商品圖片</th>
                                            <th class="product_name">商品名稱</th>
                                            <th class="product-price">價格</th>
                                            <th class="product_total">商城</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="wishListVO" items="${wishList}" >
<%--                                     ===${wishListVO.pictureVOList}==== --%>
                                        <tr>
                                         	<td class="product_remove" style ="width:15%" ><i id="deleteList${wishListVO.productId}" class="bi bi-trash3-fill fa-2x"></i></td>
                                         	<c:if test="${wishListVO.pictureVOList.size() >= 1}">
                                            <td class="product_thumb" style ="width:8%"><img src="${wishListVO.pictureVOList.get(0).previewUrl}"></td>
                                            </c:if>	
                                            <td class="product_name" style ="width:50%"><font size="4">${wishListVO.productName}</font></td>
                                            <td class="product-price" style ="width:10%" >${wishListVO.price}元</td>
                                            <td class="product_total" style ="width:8%"><a href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${wishListVO.productId}&action=getOne_For_Shop" target="_blank">
                                            <font size="3"><span style="color:white; font-size:1.2em;">前往購買</span></font></a></td>
                                        </tr>
                                    </c:forEach>   
                                    </tbody>
                                </table>   
                            </div>  

                        </div>
                     </div>
                 </div>
            </form> 
        </div>
    </div>
    <!--wishlist area end -->
   <!--如果有收藏清單 結束-->
	<!--! ========內容 結束========-->
	</c:if>	
    
     <!--推薦商品 start-->
    <!--product area start-->
    <div class="product_area  mb-100">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section_title">
                       <h2><span>推薦</span>商城主打</h2>
                       <p>寵物商品熱門推薦</p>
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
<!--                                 商品照片裡的span標籤 start -->
                                 <div class="label_product">
                                       <span class="label_new">TOP</span>
                                 </div>
<!--                                 商品照片裡的span標籤 end  -->
<!--                                  商品照片點 start -->
                                <c:if test="${productVO.pictureVOList.size() >= 1  }">
                                    <a class="primary_img" href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop">
									<img src="${productVO.pictureVOList.get(0).previewUrl}" alt=""></a>
                                </c:if>	    
                                <c:if test="${productVO.pictureVOList.size() >= 2 }">
									<a class="secondary_img" href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop">
									<img src="${productVO.pictureVOList.get(1).previewUrl}" alt=""></a>
								</c:if>	
<!--                                  商品照片點 end    -->
                                </div>
                                <figcaption class="product_content">
                                    <h4 class="product_name">
                                    	<a href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop"> 
                                    	${productVO.productName}</a>
                                    </h4>
                                    <c:if test="${productVO.totalView != null}">
											<div class="price_box">
													<i class="bi bi-eye-fill fa-1x"></i>累積觀看${productVO.totalView}次
											</div>
									</c:if>
                                    <div class="price_box"> 
                                        <span class="current_price">${productVO.price}元</span>
                                    </div>
                                    <div class="add_to_cart">
                                        <a href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop" 
                                        title="前往購買" style="font-size:1.2em;">前往購買</a>
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
 
	
			<!-- 共通的footer start-->
			<%@include file="/front/layout/footer.jsp"%>
			<!-- 共通的footer end-->


	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 共用的JS -->

	<!-- 額外添加的JS -->
	<!-- 	路徑舉例 -->
	<script 
		  src="<%=request.getContextPath()%>/assets/js/order&cart/addToCart.js">
	</script>
	<!-- 額外添加的JS -->

</body>

</html>