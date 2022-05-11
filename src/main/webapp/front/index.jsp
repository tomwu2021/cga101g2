<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.picture.model.*"%>
<%@ page import="java.util.*"%>
<%-- <% --%>
<!-- // PictureVO pictureVOList = (PictureVO) request.getAttribute("pictureVOList");  -->
<!-- // // EmpVO empVO = (EmpVO) request.getAttribute("empVO"); -->
<!-- // //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件) -->
<%-- %> --%>
<html>
<head>
<title>PCLUB-寵物們的快樂天堂</title>
<!-- 共用的CSS startr-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
</head>
<body>

	<!-- 共用的header start-->
	<%@include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->

    <!--slider area start-->
    <section class="slider_section slider_s_two color_two  mb-95">
        <div class="slider_area owl-carousel">
    <%int count = 0;%>
    <c:forEach var="artiVO" items="${aList0}">
            <div class="single_slider d-flex align-items-center" data-bgimg='${artiVO.picVO.url}'>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <div class="slider_content slider_c_three">
                                <div>
                                <h1 style="font-size:72px;">${artiVO.title}</h1>
                                </div>
                                <%count++;%>
                                <a class="button" href="/CGA101G2/article?action=all_Display#<%=count%>">了解更多</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </c:forEach>
        </div>
    </section>
    <!--slider area end-->

<!--product area start-->
    <div class="product_area product_style2 color_two  mb-95">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="section_title">
                        <h2><span>超殺的</span>商品優惠</h2>
                        <p>寵物商品優惠不漏接</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--product area end-->

    <!--banner area start-->
    <div class="banner_area home-banner2 mb-95">
        <div class="container-fluid">
                    <div class="row">
                <div class="blog_column3 owl-carousel">
                <c:forEach var="artiVO" items="${aList1}">
					<div class="col-lg-12 col-md-12 col-sm-12">
					    <figure class="single_banner">
					        <div class="banner_thumb">
					            <a href="shop.html"><img src='${artiVO.picVO.url}' alt="" style="max-height:100%;width:100%"></a>
					        </div>
					    </figure>
					</div>
                </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <!--banner area end-->

    <!--testimonial area start-->
    <div class="testimonial_area testimonial_three color_two mb-95">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="section_title">
                        <h2><span>最新的 </span>熱門貼文</h2>
                        <p>隨時追蹤毛孩的最新動態</p>
                    </div>
                </div>
            </div>
            <div class="testimonial_container2">
                <div class="row">
                    <div class="testimonial_carousel testimonial_column5 owl-carousel">
                        <c:forEach var="postVO" items="${postList}">
                        <div class="col-lg-4">
                            <div class="single-testimonial">
                                <div class="testimonial_author">
                                    <a href="#">${postVO.memberId}</a>
                                </div>
                                <div class="testimonial_thumb">
                                    <a href="#"><img src="${postVO.pictureVO.url}" alt=""></a>
                                </div>
                                <div class="testimonial_content">
                                    <div class="testimonial-rating">
                                        <ul>
                                            <li><a href="#">${postVO.createTime} 發文</a></li>
                                            <li><a href="#"> ${postVO.likeCount} 個讚</a></li>
                                        </ul>
                                    </div>
                                    <p style='height:7vh;overflow:hidden;display: -webkit-box;-webkit-box-orient:vertical;-webkit-line-clamp:2;text-overflow: ellipsis;'>${postVO.content}</p>
                                </div>
                            </div>
                        </div>
						</c:forEach>


                    </div>
                </div>
            </div>
            <div class='row mt-5'>
            <div class='col-lg-5'></div>
            <div class='col-lg-2'>
            <a href='#'>
            <img src='<%=request.getContextPath()%>/assets/img/login-image.png'>
            </a>
            </div>
            <div class='col-lg-5'></div>
            </div>
        </div>
    </div>
    <!--testimonial area end-->












	<!-- 共通的footer start-->
	<%@include file="/front/layout/footer.jsp"%>
	<!-- 共通的footer end-->
	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 共用的JS -->

	<!-- 額外添加的JS -->
<script>
$(".owl-prev").css('background','none');
$(".owl-next").css('background','none');
$('.slider_area i').css('font-size','50px');

</script>
	<!-- 額外添加的JS -->

</body>

</html>