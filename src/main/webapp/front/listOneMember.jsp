<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<html>
<head>
<title>�|����� - listOneEmp.jsp</title>

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
				<h3>�|����� - ListOneMember.jsp</h3>
				<h4>
					<a href="member_select.jsp">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<div class="main">
		<table>
			<tr>
				<th>�|���s��</th>
				<th>�|���b��</th>
				<th>�|���m�W</th>
				<th>�|���a�}</th>
				<th>�|�����</th>
				<th>�|������</th>
				<th>�|�����]�b����B</th>
				<th>�|�����Q</th>
				<th>�|�����A</th>
				<th>�|���إߤ��</th>
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