<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<html>
<head>
<title>會員資料 - listOneEmp.jsp</title>

<style>
table{width: 100% }
th, td {
  border-bottom: 1px solid #ddd;
  text-align:center;
}
tr:hover {background-color: coral;}

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
				<h3>會員資料 - ListOneMember.jsp</h3>
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
			</tr>
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
			</tr>

		</table>
	</div>

</body>
</html>