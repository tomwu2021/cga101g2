<%@ page import="com.members.model.MembersService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.members.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
MembersService memberSvc = new MembersService();
List<MembersVO> list = memberSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>會員資料 - listOneEmp.jsp</title>

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
				<h3>所有會員資料 - ListAllMember.jsp</h3>
				<h4>
					<a href="member_select.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>



	<div class="main">
		<table>
			<tr>
				<th>會員編號</th>
				<th>會員帳號</th>
				<th>會員姓名</th>
				<th>會員地址</th>
				<th>會員手機</th>
				<th>會員等級</th>
				<th>會員錢包帳戶金額</th>
				<th>會員紅利</th>
				<th>會員狀態</th>
				<th>會員建立日期</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="membersVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${membersVO.memberId}</td>
					<td>${membersVO.account}</td>
					<td>${membersVO.name}</td>
					<td>${membersVO.address}</td>
					<td>${membersVO.phone}</td>
					<td>${membersVO.rankId}</td>
					<td>${membersVO.eWalletAmount}</td>
					<td>${membersVO.bonusAmount}</td>
					<td>${membersVO.status}</td>
					<td>${membersVO.createTime}</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/front/member.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="memberId" value="${membersVO.memberId}"> <input
								type="hidden" name=status value="${membersVO.status}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/front/member.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> <input type="hidden"
								name="memberId" value="${membersVO.memberId}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>


</body>
</html>