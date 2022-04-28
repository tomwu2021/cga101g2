<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.post.model.*"%>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	貼文新增 - addPost.jsp</title>

<style>
  table {
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>

<body>
<table id="table-1">
			 
<h4><a href="post.jsp">回首頁</a></h4>
	
</table>



	
<form method="post" action="<%=request.getContextPath()%>/uploadPost" enctype="multipart/form-data">
	
<!--            <input type="hidden" name="memberId" value="1"> -->
            <div class="shop_toolbar_wrapper" id="file-zone">
                <span>點擊上傳</span>
                <input type="file" id="file-btn" accept="image/*" multiple="multiple" name="upfile">
                <input type="TEXT" name="content" size="45" value="${param.content}"/>${errorMsgs.content}
            </div>
	
	<%-- <jsp:useBean id="deptSvc" scope="page" class="com.post.model.PostService" /> --%>


<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">

</form>
</body>
</html>