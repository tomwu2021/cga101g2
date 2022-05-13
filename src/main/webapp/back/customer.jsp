<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.customer.model.*"%>
<%@ page import="com.customer.service.*"%>
<%
CustomerService custSvc = new CustomerService();
List<CustomerVO> list = custSvc.getAllCustomer();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<title>後台中心-客服回覆作業</title>
<!-- include <head></head>、共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 自訂的CSS -->
<style>
#dataTables-customer th, #dataTables-customer  td{
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
#dataTables-customer tr th{
position:sticky;
top:0;
background-color:#f8f9fb;
border:#f8f9fb;
}
</style>
</head>
<body>
	<!-- 共用的header start -->
	<%@ include file="/back/layout/header.jsp"%>
	<!-- 共用的header end -->
	<%--breadcrumbs area ??--%>
	<!-- 共用的side bar start -->
	<%@ include file="/back/layout/leftnav.jsp"%>
	<!-- 共用的side bar end -->

	<div class="page-holder w-100 d-flex flex-wrap">
		<div class="container-fluid px-xl-5">
			<section>
				<div class="page-holder w-100 d-flex flex-wrap">
					<div class="container-fluid">
						<section class="py-5">
							<div class="row">
								<div class="col-lg-12">
									<div class="row">
	<!-- ============================= Main ============================= -->
	<section class="section dashboard">
		<div class="row">
			
		
			<!-- Banner -->
			<div class="col-lg-12">
				<div class="row">
					<!-- Albums Card -->
								<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-heading">資料列表</div>
					<div class="panel-body">
						<div class="row">
<!-- 							<form action="/CGA101G2/contact" method="post"> -->
								<div class="col-lg-2">
									<div class="dataTables_filter" id="dataTables-example_length">
										<select name="action" id="select"
											aria-controls="dataTables-example"
											class="form-control input-sm">
											<option value="keyword_Display">回報內容</option>
											<option value="email_Display">電子信箱</option>
											<option value="status_Display">回覆狀態</option>
										</select>
									</div>
								</div>
								<div class="col-lg-2">
									<div id="type" class="dataTables_filter">
										<input name="searchBox" type="search" class="form-control input-sm">
									</div>
								</div>
								<div class="col-lg-2">
									<input type="submit" class="btn btn-default btn-sm" value="搜尋">
								</div>
							

						</div>
						<div class="row">
							<div class="col-sm-12">
								<br />
							</div>
						</div>
						<div class="table-responsive" style="max-height:30vh;">
							<table class="table table-striped table-bordered table-hover" style="table-layout:fixed;width:100%"	id="dataTables-customer">
								<thead>
									<tr>
										<th>案件編號</th>
										<th>電子郵件地址</th>
										<th>客戶暱稱</th>
										<th>回報內容</th>
										<th>發送時間</th>
										<th>回覆狀態</th>
										<th>回覆人員</th>
										<th>點選查看</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="custVO" items="${list}">
										<tr style='${(custVO.replyStatus==0)?"color:crimson;":""}'>
											<td>${custVO.caseId}</td>
											<td>${custVO.mailAddress}</td>
											<td>${custVO.nickname}</td>
											<td>${custVO.content}</td>
											<td><fmt:formatDate value="${custVO.sendTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${(custVO.replyStatus==1)?"已回覆":"未回覆"}</td>
											<td>${custVO.empVO.empName}</td>
											<td><form method="post" action="/CGA101G2/contact">
													<input type="submit" value="詳情" class="btn btn-info btn-sm"> <input
														type="hidden" name="caseId" value="${custVO.caseId}">
													<input type="hidden" name="action" value="one_Display">
												</form></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
					<!-- End Albums Card -->
				</div>
			</div>
			<!-- End Banner Card -->

		</div>
	</section>
	<!-- =========================== End Main =========================== -->
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</section>
		</div>
	</div>
	</div>
	<%@include file="/back/layout/footer.jsp"%>
	<!-- 共用的JS -->
	<%@include file="/back/layout/commonJS.jsp"%>
	<!-- 自訂的JS -->
	<script type="text/javascript">
        const type = document.getElementById('type');
        const select = document.getElementById('select');

        select.addEventListener('change', () => {
            const selectValue = select.value;
            if (selectValue === "status_Display") {
                type.innerHTML = "<input type='radio' name='radio' value='0' checked style='width:32px;'>未回覆<input type='radio' name='radio' value='1' style='width:32px;'>已回覆";
            } else {
                type.innerHTML = "<input type='search' class='form-control input-sm' aria-controls='dataTables-example' name='searchBox'>";
            }
        })
 </script>
</body>

</html>