<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="no-js" lang="en">

<!-- include  <html>  & <head></head> -->
<jsp:include page="/front/layout/head.jsp" />



<body>


<!-- include  common JS-->
<%@ include file="/front/layout/commonJS.jsp"%>

<!-- include  header-->
<jsp:include page="/front/layout/header.jsp" />


<!-- main contain start -->

<!--breadcrumbs area start-->
<div class="breadcrumbs_area">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="breadcrumb_content">
                    <h3>${title}</h3>
                    <ul>
                        <li><a href="<%=request.getContextPath()%>/index.html">home</a></li>
                        <li>${title}</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!--breadcrumbs area end-->

<!-- Defined Main Container -->
<%-- http://localhost:8085/cga101g2/front?page=addPicture&breadcrumb=1&title=Upload%20Picutre --%>

<jsp:include page="${pagePath}" />

<!-- main contain end -->
<!-- footer -->
<jsp:include page="footer.jsp" />

<jsp:include page="showMessage.jsp" />

</body>

</html>
