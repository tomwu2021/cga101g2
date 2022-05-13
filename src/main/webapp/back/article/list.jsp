<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.article.service.*"%>
<!DOCTYPE html>
<html>
<head>
<title>後台中心-最新消息一覽</title>
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
					<div class="panel-heading">最新消息列表</div>
					<div class="panel-body">
						<div class="row">
						</div>
						<div class="row">
							<div class="col-sm-10">
							<br/>
							</div>
							<div class="col-sm-2">
							<a href="<%=request.getContextPath()%>/back/article/add.jsp">
								<button class="btn btn-primary form-btn-circle btn-sm" id="insertBtn">
									<i class="fas fa-plus"></i> 新增
								</button>
							</a>
							</div>
						</div>
						<div class="table-responsive" style="max-height:70vh;">
							<table class="table table-striped table-bordered table-hover" style="table-layout:fixed;width:100%"	id="dataTables-customer">
								<thead>
									<tr>
										<th>編號</th>
										<th>標題</th>
										<th>類型</th>
										<th>內容</th>
										<th>建立時間</th>
										<th>最後編輯者</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="artiVO" items="${aList}">
									
										<tr>
											<td>${artiVO.articleId}</td>
											<td><a class="text-primary" href="<%=request.getContextPath()%>/article?action=one_Display&articleId=${artiVO.articleId}">${artiVO.title}</a></td>
											<td>${(artiVO.type==1)?"商城優惠":"公告訊息"}</td>
											<td>${artiVO.content}</td>
											<td><fmt:formatDate value="${artiVO.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
											<td>${artiVO.empVO.empName}</td>
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
 </script>
</body>

</html>