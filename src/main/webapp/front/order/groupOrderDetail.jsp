<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ page import="com.group_buyer.model.*"%>

<%
  //接傳回的VO
  GroupBuyerVO groupBuyerVO = (GroupBuyerVO) request.getAttribute("groupBuyerVO");
%>
<style>
table{
  width: 100%;
  border-collapse: collapse;
}

table tr{
  border-bottom: solid 2px white;
}

table tr:last-child{
  border-bottom: none;
}

table th{
  position: relative;
  width: 30%;
  background-color: #7d7d7d;
  color: white;
  text-align: center;
  padding: 10px 0;
}

table th:after{
  display: block;
  content: "";
  width: 0px;
  height: 0px;
  position: absolute;
  top:calc(50% - 10px);
  right:-10px;
  border-left: 10px solid #7d7d7d;
  border-top: 10px solid transparent;
  border-bottom: 10px solid transparent;
}

table td{
  text-align: left;
  width: 70%;
  text-align: center;
  background-color: #eee;
  padding: 10px 0;
}
</style>
<!-- include <head></head> -->

<!--services img area-->
<%@ include file="/front/layout/head.jsp"%>
<!-- include  common JS-->

<!-- include  header -->
<%@ include file="/front/layout/header.jsp"%>

<!-- 主要內容 start -->
<!--breadcrumbs area start-->
<FORM METHOD="post" ACTION="/CGA101G2/front/order/myAccount.jsp">
	<div class="main">
	<label>訂單寄送詳情</label><br>
		<table>
			<tr>
				<th>Recipients</th>
				<td><%=groupBuyerVO.getRecipients()%></td>
			</tr>
			<tr>
				<th>Phone</th>
				<td><%=groupBuyerVO.getPhone()%></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><%=groupBuyerVO.getAddress()%></td>
			</tr>
		</table>
	</div>
	<br>
	<input type="submit" class="btn btn-primary" value="返回查看訂單">
</FORM>


<!-- 主要內容 end -->
<%@ include file="/front/layout/commonJS.jsp"%>
<%@ include file="/front/layout/commonCSS.jsp"%>
<!-- include  footer -->
<%@include file="/front/layout/footer.jsp"%>
<!-- 額外添加JS start -->

<!-- 額外添加JS end -->

</body>
<!-- body end -->

</html>