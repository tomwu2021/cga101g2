<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改</title>

<style>
table {
	width: 100%
}

th, td {
	border-bottom: 1px solid #ddd;
	text-align: center;
}

tr:hover {
	background-color: coral;
}

th {
	background-color: #04AA6D;
	color: white;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>會員資料新增</h3>
				<h4>
					<a href="member_select.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>



<FORM METHOD="post" ACTION="member.do" name="form1">
<table>
	<tr>
		<td>會員信箱:</td>
		<td><input type="TEXT" name="account" size="45" 
			 value="${param.account}"/></td><td>${errorMsgs.account}</td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="password" size="45"
			 value="${param.password}"/></td><td>${errorMsgs.password}</td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

        
</html>