<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.customer.model.*"%>
<%@ page import="com.customer.service.*"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<!DOCTYPE html>
<html>
<head>
<title>後台中心-最新消息管理</title>
<!-- include <head></head>、共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 自訂的CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back/article/style.css">
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

		<!-- B Card -->
		<div class="col-lg-12">
			<div class="row">
				<!-- A Card -->
				<div class="col-md-12" style="width:1000px;">
<div class="row">
<div class="col-md-12">
	<a class="text-dark mb-1" href='<%=request.getContextPath()%>/article?action=all_Article' >
		<i class="fas fa-arrow-left"></i> 返回
	</a>
</div>
<div class="col-md-12">
<br/>
</div>
</div>
					<div class="mb-3" style="font-size:2em">上傳最新消息</div>
					<!-- Form Elements -->
					<form class="ml-5" method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/article">

						<div class="row mb-3">
							<label for="inputText" class="col-lg-1 col-form-label">標題</label>
							<div class="col-lg-11">
								<input name="title" type="text" class="form-control" style="width:100%" value='${param.title}'>
							<span style="color:#f33;">${errorMsgs.title}</span></div>
						</div>

						<div class="row mb-3">
							<label for="inputPassword"
								class="col-lg-1 col-form-label">類型</label>
							<div class="col-lg-11">
								<div class="form-check" style="display:inline-block">
									<input class="form-check-input" type="radio"
										name="type" id="gridRadios1" value="1" ${(param.type==1)? 'checked':''}
										> <label class="form-check-label"
										for="gridRadios1"> 商城優惠 </label>
								</div>
								<div class="form-check" style="display:inline-block">
									<input class="form-check-input" type="radio"
										name="type" id="gridRadios2" value="0" ${(param.type==0)? 'checked':''}>
									<label class="form-check-label" for="gridRadios2">
										最新公告 <span style="color:#f33;">(請注意！第一張為首圖)${errorMsgs.type}</span></label>
								</div>
							</div>
						</div>
						
						<div class="row mb-3">
							<label for="inputPassword"
								class="col-lg-1 col-form-label">內容</label>
							<div class="col-lg-11">
								<textarea name="content" class="form-control" style="height:30vh;width:100%;resize:none;">${param.content}</textarea>
								<span style="color:#f33;">${errorMsgs.content}</span>
							</div>
						</div>

						<div class="row mb-3">
							<label for="inputNumber"
								class="col-lg-1 col-form-label">圖片</label>
								<div class="col-lg-11">
								<img src="<%=request.getContextPath()%>/back/article/add-image.png" class="ml-3" style="height:200px;width:200px;" onclick="add()" id="addimage">
								<span style="color:#f33;">${errorMsgs.file}</span>
								</div>
						</div>
						
						<div class="row mb-3">
							<input type="hidden" name="empNo" value="<%=((com.emp.model.EmpVO)session.getAttribute("empVO")).getEmpNo()%>">
						</div>
						<div class="row mb-3">
							<div class="col-lg-10"></div>
							<div class="col-lg-2">
								<input type="hidden" name="action" value="insert" id="qq">
								<button type="submit" class="btn btn-success">發佈</button>
							</div>
						</div>

					</form>
					<!-- End Form Elements -->
				</div>
				<!-- End A Card -->
			</div>
		</div>
		<!-- End B Card -->
		
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
let count = 1;

function add(){
	$('#addimage').before('<div id="div'+count+'" style="float:left;position:relative;"><input name="file" accept="image/*" type="file" id="formFile'+count+'"></div>');
	$('#formFile'+count).hide();
		let Element = document.querySelector('#formFile'+count);
		Element.addEventListener('change', function() { 
		let url = URL.createObjectURL(Element.files[0]); 
		$(this).after('<img src="'+url+'" id="img'+ (count-1) +'" class="mb-3 ml-3" style="height:266px;width:400px;object-fit: cover;"><i class="far fa-window-close text-muted" id="btn'+ (count-1) +'" style="position:absolute;top:0;right:0;font-size:2em;" onclick="letItGo(this.id)"></i>');
	
		});
	document.querySelector('#formFile'+count).click();
		count++;
	}

function letItGo(btn){
	let select = btn.replace('btn','#div');
	$(select).remove();

}

</script>

</body>

</html>