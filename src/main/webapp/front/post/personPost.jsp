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

<!-- 更改的CSS start-->
<link rel="stylesheet"
href="<%=request.getContextPath()%>/front/post/css/personPost.css">
<!-- 更改的CSS end-->

<meta charset="UTF-8">
<style>
	
	#demo_border {
	position: relative;
	width:280px;
	height:0px;
	}
	
	    #image_photo {
　　position: absolute;
    background-image: url("");
 
    background-repeat: no-repeat;
    border-radius: 50%;
    overflow: hidden;

    width: 120px;
    height: 120px;
    transform: translateY(-50px);
	}
	
	+	#img_text {
	position: absolute;
	bottom: 0px;
	right: 0px;
 	font-size: 16px;

	color: #926161;t
	border-radius: 50%;
	/* background-color: #F00; */
	width: 150px;
	height:50px;
	text-align: center;
	line-height: 30px;
	transform: translateX(8px) translateY(1px);
	}
	
    
    /* 追蹤 */
	.button1 {
	/* background: url(../img/icon/bkg_grid4.png) no-repeat scroll center center; */
	background-color: #7f1912;
	border: none;
	color: white;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	border-radius: 8px;
	transform: translateY(50px) translateX(-120px);
	} 
  	
  	/* 封鎖 */
	.button2 {
	/* background: url(../img/icon/bkg_grid4.png) no-repeat scroll center center; */
	background-color: #504c4c;
	border: none;
	color: white;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	border-radius: 8px;
	transform: translateY(50px) translateX(-80px);
	}
	
	
</style>

    
</head>

<body>
<input type="hidden" id="memberId"
 value="<%=request.getAttribute("memberId")%>">

<input type="hidden" id="isOwner" value="<%=request.getAttribute("isOwner")%>">

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

							
                            <div id="demo_border" style="position: relative;">

								<div id="image_photo"><img src="${membersVO.url}"></div>										
								<div id="img_text">${membersVO.name}</div>
							</div>
							
														 

                            <!-- <button data-role="grid_4" type="button" id=" btn-grid-4" data-toggle="tooltip"
                                title="4"></button>

                            <button data-role="grid_list" type="button" class="btn-list" data-toggle="tooltip"
                                title="List"></button> --> 
                        </div>
                        <!-- 新增的追蹤跟封鎖按鈕 -->
                        <div class="container">
                        <div class="button1">追蹤</div>
                        <div class="button2">封鎖</div>
                        </div>
                        
                        
                        
                        <!-- sort by average rating按鈕 -->
                        <!-- <div class=" niceselect_option">
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
                        </div> -->
                        
                        <!-- showing 1-9 0f 21 results按鈕 -->
                        <!-- <div class="page_amount">
                            <p>Showing 1–9 of 21 results</p>
                        </div> -->
                        
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
                                       
                                        <c:if test="${postVO.pictureList.size() != 0 }">
                                        <a class="primary_img" href="<%=request.getContextPath()%>/detailPost?postId=${postVO.postId}&memberId=${postVO.memberId}&action=selectdetail"><img
                                                src="${postVO.pictureVO2.previewUrl}" alt=""></a>
                                        </c:if>
                                        
                                        <c:if test="${memberId == isOwner}">
                                        <!-- 刪除按鈕 -->
                                        <button id="deletePost${postVO.postId}">Confirm</button>
                                                                                                       
                                		
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
					  
					  
                    <!-- <div class="shop_toolbar t_bottom">
                        <div class="pagination">
                            <ul>
                                <li class="current">1</li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li class="next"><a href="#">next</a></li>
                                <li><a href="#">>></a></li>
                            </ul>
                        </div>
                    </div> -->
                    </div>
                    
                    <%-- <c:if test="${isOwner==1}"> --%>
                    
                    <c:if test="${memberId == isOwner}">
                    
                    <div>
                    <img src="front/post/image/addpost.png" class="ml-3" style="height:40px;width:40px; position: fixed; top: 80px; right: 14px;" onclick="window.location='http://localhost:8081/CGA101G2/front/post/addPost.jsp'" id="addpost">
                    </div>
                    </c:if>
                    
            </div>
        </div>
        
    <!--shop  area end-->
    
		 
	<!--! 內容 結束-->


		<!-- 共通的footer start-->
		<%@include file="/front/layout/footer.jsp"%>
		<!-- 共通的footer end-->


	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 刪除鈕的JS -->
	<script 
		  src="<%=request.getContextPath()%>/front/post/js/delete.js">
	</script>
</body>

<!-- <script>
    function confirmEvent() {
        Swal.fire({
            title: 'Are you sure?',
            text: "Do you want to delete 確定刪除這篇貼文嗎 ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: 'darkgrey',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {

            //刪除方法

            if (result.isConfirmed) {
                Swal.fire(
                    'Deleted!',
                    'Your post has been deleted.',
                    'success'
                )
            }
        });
    }

</script> -->

</html>