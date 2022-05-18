<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.customer.model.*"%>
<%@ page import="com.customer.service.*"%>
<!DOCTYPE html>
<html>
<head>
<title>後台中心-客服回覆作業</title>
<!-- include <head></head>、共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 自訂的CSS -->
<link rel='stylesheet' href='<%=request.getContextPath()%>/back/customer/customer.css'>
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
					<div class="panel-heading">客戶問題列表</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-sm-12">
								<br />
							</div>
						</div>
							<form action="<%=request.getContextPath()%>/contact" method="post">
						<div class="row">
								<div class="col-lg-2 ml-3">
									<div id="type" class="dataTables_filter">
										<input name="searchBox" type="search" class="form-control input-sm" value="${searchBox}">
										<input type='hidden' name='action' value='keyword_Display'>
									</div>
								</div>
								<div class="col-lg-1">
									<input type="submit" class="btn btn-primary" value="內容搜尋">
								</div>
								<div class="col-lg-7">
								</div>
								<div class="col-lg-1">
								<a class='btn btn-warning btn-sm' href='<%=request.getContextPath()%>/contact?action=all_Display'>全部顯示</a>
								</div>
						</div>
							</form>
						<div class="row">
							<div class="col-sm-12">
								<br />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-9">
							</div>
						</div>
						<div class="table-responsive" style="max-height:213px;">
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
										<tr>
											<td>${custVO.caseId}</td>
											<td><a href='<%=request.getContextPath()%>/contact?action=email_Display&email=${custVO.mailAddress}'>${custVO.mailAddress}</a></td>
											<td>${custVO.nickname}</td>
											<td>${custVO.content}</td>
											<td><fmt:formatDate value="${custVO.sendTime}"
 													pattern="yyyy-MM-dd HH:mm:ss" /></td>
 											<td>${(custVO.replyStatus==1)?"已回覆":"未回覆"}${(custVO.replyStatus==0)?"&emsp;<i class='text-warning fas fa-exclamation-triangle'></i>":""}</td>
 											<td>${custVO.empVO.empName}</td>
 											<td>
											<form method='post' action='<%=request.getContextPath()%>/contact'>
											<input type='hidden' name='action' value='one_Display'>
											<input type='hidden' name='email' value='${custVO.mailAddress}'>
											<input type='hidden' name='caseId' value='${custVO.caseId}'>
											<button type='submit' class='btn btn-info btn-sm'>詳情</button>
											</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<br />
							</div>
						</div>
						
			
				<div class="col-xxl-4 col-xl-12">
	<div class="card info-card customers-card border-bottom-warning">
		<div class="card-body">
			<h5 class="card-title"><span class='text-secondary font-weight-bold'>客戶暱稱： </span>${custVO.nickname}</h5>
			<div class='row'>
				<div class='col-lg-12'>
					<div class='text-secondary font-weight-bold' style='font-size:2em;'>${pVO.petName}</div>
					<div><span class='text-secondary font-weight-bold'>電子郵件地址： </span>${custVO.mailAddress}</div>
					<div><span class='text-secondary font-weight-bold'>發送時間： </span><fmt:formatDate value="${custVO.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
					<div><span class='text-secondary font-weight-bold'>回報內容：</span></div>
					<div>${custVO.content}</div>
					<div><span class='text-secondary font-weight-bold'>回覆人員： </span>${custVO.empVO.empName}</div>
				</div>
			</div>
			<div class='row'>
			<div class='col-lg-10'>
			</div>
			<div class='col-lg-2'>
			<c:if test="${custVO.empNo == 0}">
					<!-- Large Modal -->
              <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#largeModal" id='replyBtn'>
                回覆問題
              </button>

              <div class="modal fade" id="largeModal" tabindex="-1">
                <div class="modal-dialog modal-lg">
                <form method='post' action=''>
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title">回覆內容</h5>
                      <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
							<textarea name="replyContent" class="form-control" style="height:30vh;width:100%;resize:none;"></textarea>
                    </div>
                    <div class="modal-footer">
                    <input type='hidden' name='caseId' value='${custVO.caseId}'>
                    <input type='hidden' name='mailAddress' value='${custVO.mailAddress}'>
                    <input type='hidden' name='nickname' value='${custVO.nickname}'>
                    <input type='hidden' name='empNo' value='<%=((com.emp.model.EmpVO)session.getAttribute("empVO")).getEmpNo()%>'>
                    <input type='hidden' name='action' value='update'>
                      <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
                      <button type="submit" class="btn btn-danger">寄送</button>
                    </div>
                  </div>
                  </form>
                </div>
              </div><!-- End Large Modal-->	
              </c:if>
			</div>
			</div>
		
		</div>
	</div>
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
	<script>
// 	if('${custVO.empVO.empName}' !== ''){
// 		$('#replyBtn').hide();
// 	}
	</script>
	</body>

</html>