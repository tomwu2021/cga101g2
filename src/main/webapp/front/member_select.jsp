<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- Object account = session.getAttribute("account"); -->
<!-- if (account == null) { -->
<!-- 	session.setAttribute("location", request.getRequestURL()); -->
<!-- 	response.sendRedirect(request.getContextPath() + "/front/login.jsp"); -->
<!-- 	return; -->
<!-- } -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member</title>
<style>
ul li {
	background: #cce5ff;
	color: darkblue;
	margin: 5px;
	font-size: 30px;
}
</style>

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
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>會員資料 Home (增、刪、修、查)</h3>

			</td>
		</tr>
	</table>
	<ul>
		<li><a href='listAllMember.jsp'>List</a> all Member.</li>
		<li>
			<FORM METHOD="post" ACTION="member.do">
				<b>輸入會員編號：</b> <input type="text" name="memberId"> <font
					color=red>${errorMsgs.memberId}</font> <input type="hidden"
					name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="memberSvc" scope="page"
			class="com.members.model.MembersService" />

		<li>
			<FORM METHOD="post" ACTION="member.do">
				<b>選擇會員編號:</b> <select size="1" name="memberId">
					<c:forEach var="membersVO" items="${memberSvc.all}">
						<option value="${membersVO.memberId}">${membersVO.memberId}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>


		<li>
			<FORM METHOD="post" ACTION="member.do">
				<b>選擇會員編號:</b> <select size="1" name="memberId">
					<c:forEach var="membersVO" items="${memberSvc.all}">
						<option value="${membersVO.memberId}">${membersVO.name}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>


		<li><a href='addMember.jsp'>Add 新增一筆會員資料</a>
	</ul>
	<ul>
		<li>JSP EL 取值</li>
		<li>memberId：${membersVO.memberId}</li>
		<li>account：${membersVO.account}</li>
		<li>password：${membersVO.password}</li>
		<li>name：${membersVO.name}</li>
		<li>address：${membersVO.address}</li>
		<li>phone：${membersVO.phone}</li>
		<li>rank_id：${membersVO.rankId}</li>
		<li>eWalletAmount：${membersVO.eWalletAmount}</li>
		<li>eWalletPassword：${membersVO.eWalletPassword}</li>
		<li>eWalletPassword：${membersVO.eWalletPassword}</li>
		<li>bonusAmount：${membersVO.bonusAmount}</li>
		<li>status：${membersVO.status}</li>
		<li>createTime：${membersVO.createTime}</li>
	</ul>
	
</body>
</html>