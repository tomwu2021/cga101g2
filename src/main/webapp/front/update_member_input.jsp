<%@ page import="com.members.model.MembersService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.members.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�|����ƭק� - update_member_input.jsp</title>

<style>
body{
	text-align: center;
}

table#table-1 {
	width: 100%;
	background-color: #CCCCFF;
	border: 2px solid black;
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
				<h3>�޲z���ק�|�����A - update_member_input.jsp</h3>
				<h4>
					<a href="member_select.jsp">�^����</a>
				</h4>
			</td>
		</tr>
	</table>



	<FORM METHOD="post" ACTION="member.do" name="form1">
		<table>
			<tr>
				<td>�|���s��:<font color=red><b>*</b></font></td>
				<td>�|���b��:<font color=red><b>*</b></font></td>
				<td>�|���m�W:<font color=red><b>*</b></font></td>
				<td>�|���a�}:<font color=red><b>*</b></font></td>
				<td>�|�����:<font color=red><b>*</b></font></td>
				<td>�|������:<font color=red><b>*</b></font></td>
				<td>�|�����]�b����B:<font color=red><b>*</b></font></td>
				<td>�|�����Q:<font color=red><b>*</b></font></td>
				<td>�|�����A(0���v/1���`):</td>
				<td>�|���إߤ��:<font color=red><b>*</b></font></td>
			</tr>
			<tr>
				<td>${param.memberId}</td>
				<td>${param.account}</td>
				<td>${param.name}</td>
				<td>${param.address}</td>
				<td>${param.phone}</td>
				<td>${param.rankId}</td>
				<td>${param.eWalletAmount}</td>
				<td>${param.bonusAmount}</td>
				<td><input type="TEXT" name="status" size="45"
					value="${param.status}" /></td>
				<td>${param.createTime}</td>
			</tr>


		</table>
		<br><input type="hidden" name="action" value="update"> 
		    <input type="hidden" name="memberId" value="${param.memberId}"> 
			<input type="hidden" name="account" value="${param.account}"> 
			<input type="hidden" name="name" value="${param.name}"> 
			<input type="hidden" name="address" value="${param.address}"> 
			<input type="hidden" name="phone" value="${param.phone}"> 
			<input type="hidden" name="rankId" value="${param.rankId}"> 
			<input type="hidden" name="eWalletAmount" value="${param.eWalletAmount}"> 
			<input type="hidden" name="bonusAmount" value="${param.bonusAmount}"> 
			<input type="hidden" name="status" value="${param.status}"> 
			<input type="hidden" name="createTime" value="${param.createTime}"> 
			<input type="submit" value="�e�X�ק�">
	</FORM>
</body>




</html>