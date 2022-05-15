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
<title>PCLUB-最新消息</title>
<!-- 共用的CSS startr-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
</head>
<body>

	<!-- 共用的header start-->
	<%@include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->


    <!--blog body area start-->
    <div class="blog_details">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12">
    <%int count = 0; %>
	<c:forEach var="artiVO" items="${aList}">
                    <!--blog grid area start-->
                    <div class="blog_wrapper blog_wrapper_details">
                        <article class="single_blog">
                            <figure>
                            <%count++;%>
                                <div class="post_header" id="<%=count%>">
                                    <h3 class="post_title">${artiVO.title}</h3>
                                    <div class="blog_meta">
                                        <p>建立時間 : <fmt:formatDate value="${artiVO.createTime}" pattern="yyyy-MM-dd HH:mm"/></p>
                                    </div>
                                </div>
							    <!--slider area start-->
							        <div class="slider_area owl-carousel">
							        <c:forEach var="picVO" items="${artiVO.picList}">
							            <div class="single_slider d-flex align-items-center" data-bgimg='${picVO.url}'></div>
							        </c:forEach>
							        </div>
							    <!--slider area end-->
                                <figcaption class="blog_content">
                                    <div class="post_content">
                                        <p>${artiVO.content}</p>
                                    </div>
                                </figcaption>
                            </figure>
                        </article>
                        <hr/>
                    </div>
                    <!--blog grid area start-->
	</c:forEach>
                </div>
            </div>
        </div>
    </div>
    <!--blog section area end-->











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