<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.picture.model.*"%>
<%@ page import="java.util.*"%>
<%-- <% --%>
<!-- // PictureVO pictureVOList = (PictureVO) request.getAttribute("pictureVOList");  -->
<!-- // // EmpVO empVO = (EmpVO) request.getAttribute("empVO"); -->
<!-- // //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件) -->
<%-- %> --%>
<html>
<head>
<title>商品細節</title>
<!-- 共用的CSS startr-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<!-- 	路徑舉例 -->
<!-- 額外添加的CSS -->
</head>
<body>

	<!-- 共用的header start-->
	<%@include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->
	
	<!-- 共用的CartAndWishlist-->
	<%@include file="/front/layout/commonCartAndWishlist.jsp"%>
	<!-- 共用的CartAndWishlist-->
	
	<!--breadcrumbs area start-->
	<div class="breadcrumbs_area">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="breadcrumb_content">
						<h3>商品專區</h3>
						<ul>
							<li><a href="index.html">寵物商城</a></li>
							<li>商品細節</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--breadcrumbs area end-->

	<!--! ========內容======== -->
	<!--product details start-->
	<div class="product_details mt-100 mb-100">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6">
					<div class="product-details-tab">
						<div id="img-1" class="zoomWrapper single-zoom">
<!-- 				<pictureVOList 首圖一開始>	-->
<c:if test="${pictureVOList.size() >= 1 }">
							<a href="#"> <img id="zoom1"
								src="${pictureVOList.get(0).url}"
								data-zoom-image="${pictureVOList.get(0).url}" alt="big-1" />
							</a>
</c:if>		
<!-- 				<pictureVOList 首圖二開始>	-->
						</div>
						<div class="single-zoom-thumb">
							<ul class="s-tab-zoom owl-carousel single-product-active"
								id="gallery_01">
<c:if test="${pictureVOList.size() >= 2}">
<c:forEach var="pictureVO" items="${pictureVOList}">
								<li><a href="#" class="elevatezoom-gallery active"
									data-update="" data-image="${pictureVO.previewUrl}"
									data-zoom-image="${pictureVO.previewUrl}">
										<img src="${pictureVO.previewUrl}" alt="zo-th-1" />
								</a></li>
</c:forEach>								
</c:if>		
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6">
					<div class="product_d_right">
						<Form id="addForm" action="<%=request.getContextPath()%>/member/cart.do" Method="Post">
							<h1>
								<a href="#">${param.productName}</a>
							</h1>
							<div class="price_box">
								<span class="current_price">${param.price}元</span>
							</div>
							<div class="product_desc">
								<p>PCLUB品質保證</p> 
								<c:if test="${productVO.totalView!=null}">
								<p><i class="bi bi-eye-fill fa-1x"></i>累積觀看&ensp; ${param.totalView}&ensp; 次</p> 
								</c:if>
							</div>
							<div class="product_variant quantity">
								<label>數量</label>
								 <input name="productAmout" min="1" max="999" step="1" value="1" type="number" />
							</div>
							<div class="product_variant quantity">
								<button class="button" type="submit" style="position: relative; right:20px;" onclick="document.getElementById('addForm').submit()">加入購物車</button>
							</div>
							<input type="hidden" name="productId" value="${param.productId}">
							<input type="hidden" name="productName" value="${param.productName}">
							<input type="hidden" name="productPrice" value="${param.price}">
							<input type="hidden" name="productPictureUrl" value="${pictureVOList.get(0).previewUrl}">
							<input type="hidden" name="action" value="ADDONE">
							</form>
							
							<div class="product_d_action">
								<ul>
<!-- 									會員使否有收藏該商品的判斷 -->
									<c:if test="${wishlistVO.productId == null}">
									<li><button id="Wlishlist${param.productId}" value="0" type="button" class="btn btn-outline-danger"><i class="bi bi-heart">加入收藏</i></button></li>
									</c:if> 
									<c:if test="${wishlistVO.productId != null}">
									<li><button id="Wlishlist${param.productId}" value="1" type="button" class="btn btn-danger"><i class="bi bi-heart">已加入收藏</i></button></li>
									</c:if> 
<!-- 									會員使否有收藏該商品的判斷 -->									
								</ul>
							</div>
							<div class="product_meta">
								<span>子分類:${param.sort2Name}</span>
							</div>
						
<!-- 						<div class="priduct_social"> -->
<!-- 							<ul> -->
<!-- 								<li><a class="facebook" href="#" title="facebook"> -->
<!-- 								<i class="bi bi-share-fill"></i>分享PCLUB社群</a></li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--product details end-->
 <!--product info start-->
    <div class="product_d_info mb-90">
      <div class="container">
        <div class="row">
          <div class="col-12">
            <div class="product_d_inner">
              <div class="product_info_button">
                <ul class="nav" role="tablist">
                  <li>
                    <a
                      class="active"
                      data-toggle="tab"
                      href="#info"
                      role="tab"
                      aria-controls="info"
                      aria-selected="false"
                      >商品敘述</a
                    >
                  </li>
                </ul>
              </div>
              <div class="tab-content">
                <div
                  class="tab-pane fade show active"
                  id="info"
                  role="tabpanel"
                >
                  <div class="product_info_content">
                    <p>${param.description}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--product info end-->
    
    
        <!--product info start-->
    
<!--     預留include推薦商品區 -->
    
    <!--product info end-->
    
	<!--! 內容 結束-->


	<!-- 共通的footer start-->
	<%@include file="/front/layout/footer.jsp"%>
	<!-- 共通的footer end-->
	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 共用的JS -->

	<!-- 額外添加的JS -->
	<jsp:include page="/front/layout/showMessage.jsp" />
	<!-- 額外添加的JS -->

</body>

</html>