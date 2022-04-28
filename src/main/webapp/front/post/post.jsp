<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<head>
<title>Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<h3>個人頁面查詢</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<ul>
  <!-- <li><a href='listAllEmp.jsp'>List</a> all Emps.  <br><br></li> -->
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/uploadPost" >
        <b>輸入會員編號 (如1):</b>
        <input type="text" name="memberId" value="${param.memberId}"><font color=red>${errorMsgs.empno}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

<jsp:useBean id="ps" scope="page" class="com.post.model.PostService" /> 
   
  <%-- <li>
     <FORM METHOD="post" ACTION="emp.do" >
       <b>選擇員工編號:</b>
       <select size="1" name="empno">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.empno}">${empVO.empno}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="emp.do" >
       <b>選擇員工姓名:</b>
       <select size="1" name="empno">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.empno}">${empVO.ename}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li> --%>
</ul>


<h3>新增貼文</h3>

<ul>
  <li><a href='addPost.jsp'>Add</a> a new Post</li>
</ul>

</body>
</html>