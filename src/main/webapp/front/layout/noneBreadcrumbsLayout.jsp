<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="en">

<!-- include  <html>  & <head></head> -->
<jsp:include page="./head.jsp" />



<body>

<!-- include  common JS-->
<jsp:include page="./commonJS.jsp" />

<!-- include  header-->
<jsp:include page="./header.jsp" />

<!-- main contain start -->

<!-- Defined Main Container -->
<%-- http://localhost:8085/cga101g2/front?page=dynamicAddPicture --%>

<jsp:include page="${pagePath}" />

<!-- main contain end -->

<!-- footer -->
<jsp:include page="footer.jsp" />

</body>

</html>
