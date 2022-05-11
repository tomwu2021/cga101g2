<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>    



<!DOCTYPE html>
<html>
<head>

<title>社群個人頁面</title>
<!-- 共用的CSS start-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<meta charset="UTF-8">

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
	

	<%-- <% Integer memberId=9; %> --%>
	<%-- <input type="hidden" id="memberId" value="<%=request.getAttribute("memberId")%>"> --%>
	
                           
                                          
  	
	
	    <!--offcanvas menu area start-->
    <div class="off_canvars_overlay">

    </div>
    
										   									        
    <!--shop  area start-->
    <div class="shop_area shop_reverse mt-100 mb-100">
        <div class="container">

				  
                
                <div class="row">
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
                     	<c:forEach var="postVO" items="${personList}" > 
                        <div class="col-lg-4 col-md-4 col-sm-6 col-12 ">
                        	
                            <article class="single_product">
                                <figure>
                                	<!-- 起始 -->
                                    <div class="product_thumb">
                                    
                                        
                                        <!-- 圖 -->
                                       <%--  <c:forEach var="postVO" items="${list}" >  --%>
                                        <c:if test="${postVO.pictureList.size() != 0 }">
                                        <a class="primary_img" href="http://localhost:8081/CGA101G2/detailPost?postId=${postVO.postId}&action=selectdetail"><img
                                                src="${postVO.pictureList.get(0).previewUrl}" alt=""></a>
                                        </c:if>

                                    </div>
                                   	<!--  整個文字框 -->
                                    <div class="product_content grid_content">
                                        
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