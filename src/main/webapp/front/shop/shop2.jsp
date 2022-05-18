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
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 共用的JS -->
	
	
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
		</div>
		<div class="niceselect_option">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop" id="myPageForm">   
		       <select size="1" name="whichPage" id="myPage">
		      	  <option value="-1">請選擇
		         <%
		          // get attributes
		          int whichPage = 1;
		          String currPage = (String) request.getAttribute("currPage");
		          if(currPage != null) {
		        	  whichPage = Integer.parseInt(currPage);
		          }
		          int total = (Integer) request.getAttribute("total");
		          int pageTotal; 
		          if(total % 9 == 0) {
					  pageTotal = total / 9;
		          } else {
					  pageTotal = total / 9 + 1;
		          }
		          for (int i=1; i<=pageTotal; i++){
		         %>
		         	<c:if test="<%= whichPage == i%>">
		         		<option value="<%=i%>" selected>跳至第<%=i%>頁
		         	</c:if>
		         	<c:if test="<%= whichPage != i%>">
		         		<option value="<%=i%>">跳至第<%=i%>頁
		         	</c:if>
		         <%}%> 
		       </select>
<!-- 		       <input type="submit" value="確定" > -->
		       <input type="hidden" name="action" value="listProducts_Byfind">  
		    </FORM>
		    
	    </div>
	    
	    <span class="text-danger" >一頁9筆</span>
	    <span class="text-danger" >總共<%=pageTotal%>頁</span>
	    <span class="text-danger" >總共<%=total %>筆</span>
	    
	</div>
	
	<!--shop toolbar end-->
	<div class="row shop_wrapper">
		<!--內容開始========================-->
		<c:forEach var="productVO" items="${listProducts_Byfind}" >
			<div class="col-lg-4 col-md-4 col-sm-6 col-12">
				<!--單一商品開始 -->
				<Form id="${productVO.productId}" action="/CGA101G2/member/cart.do" Method="Post">
				<article class="single_product">
					<figure>
						<div class="product_thumb">
							<c:if test="${productVO.pictureVOList.size() >= 1}">
								<a class="primary_img" href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop">
								<img src="${productVO.pictureVOList.get(0).previewUrl}" alt=""></a>
							</c:if>	
							<c:if test="${productVO.pictureVOList.size() >= 2}">
								<a class="secondary_img" href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop">
								<img src="${productVO.pictureVOList.get(1).previewUrl}" alt=""></a>
							</c:if>	
						</div>
						<div class="product_content grid_content">
							<h4 class="product_name">
								<a href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop"> ${productVO.productName}</a>
							</h4>
							
							<c:if test="${productVO.totalView!=null}">
							<div class="price_box">
								<i class="bi bi-eye-fill"></i>累積觀看${productVO.totalView}次
							</div>
							</c:if>
							
							<div class="price_box">
								<span class="current_price">${productVO.price}元</span>
							</div>
							<div class="add_to_cart">								
								<a title="加入購物車" onclick="addToCart(${productVO.productId})">
								<span style="color:white; font-size:1.2em;">加入購物車</span></a>
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
	<!--單一商品結束 -->
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
										<i class="bi bi-eye-fill"></i>累積觀看${productVO.totalView}次
									</div>
                                    </c:if>
									
                                    <div class="price_box"> 
                                        <span class="current_price">${productVO.price}元</span>
                                    </div>
                                    
                                    <div class="add_to_cart">
                                        <a href="<%=request.getContextPath()%>/shop/ProductGetOneServlet?productId=${productVO.productId}&action=getOne_For_Shop" 
                                        title="前往購買"><span style="color:white; font-size:1.2em;">前往購買</span></a>
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
		src="<%=request.getContextPath()%>/assets/js/order&cart/addToCart.js">
	</script> 
	<script
		src="<%=request.getContextPath()%>/assets/shop/leftnav/leftnavlink.js">
	</script>
	
	<script 
		src="<%=request.getContextPath()%>/assets/shop/wishlist.js"> 
	</script>
	
	<script 
		src="<%=request.getContextPath()%>/assets/shop/newPage.js"> 
	</script>
	
</body>

</html>