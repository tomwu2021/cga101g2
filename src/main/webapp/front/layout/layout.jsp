<%@ page import="jdk.internal.access.JavaIOFileDescriptorAccess" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html class="no-js" lang="en">

<!-- include  <html>  & <head></head> -->
<jsp:include page="./head.jsp"/>


<body>

<!-- include  common JS-->
<jsp:include page="./commonJS.jsp"/>

<!-- include  header-->
<jsp:include page="./header.jsp"/>

<!-- main contain start -->
<!--breadcrumbs area start-->
<div class="breadcrumbs_area">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="breadcrumb_content">
                    <h3>${title}</h3>
                    <ul>
                        <c:forEach var="menu" items="${breadcrumbList}">
                            <li><a href='${menu.get("path")}'>${menu.get("name")}</a></li>
                        </c:forEach>
<%--                        <li>${title}</li>--%>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!--breadcrumbs area end-->

<!-- Defined Main Container -->
<%-- http://localhost:8085/cga101g2/front?page=addPicture&breadcrumb=1&title=Upload%20Picutre --%>

<jsp:include page="${pagePath}"/>

<!-- main contain end -->

<!-- footer -->
<jsp:include page="footer.jsp"/>

</body>

</html>
