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
					<div class="mb-3" style="font-size:2em">
						<div class="row">
							<div class="col-md-9">
							最新消息詳情
							</div>
							<div class="col-md-3">
								<button class="btn btn-warning form-btn-circle btn-sm text-dark" id="updateBtn" onclick="updateBtn()">
									<i class="fas fa-edit"></i> 編輯
								</button>
														<!-- Basic Modal -->
					              <button  class="btn btn-danger form-btn-circle btn-sm" data-toggle="modal" data-target="#basicModal" id="deleteBtn">
					                <i class="fas fa-trash-alt"></i> 刪除
					              </button>
					                      <form method="post" action="/CGA101G2/article">
					              <input type="hidden" name="articleId" value="${artiVO.articleId}">
					              <div class="modal fade" id="basicModal" tabindex="-1">
					                <div class="modal-dialog">
					                  <div class="modal-content">
					                    <div class="modal-header">
					                      <h5 class="modal-title">提示訊息</h5>
					                    </div>
					                    <div class="modal-body">
					                      是否確定刪除此項公告？
					                    </div>
					                    <div class="modal-footer">
					                      <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
					                      <input type="hidden" name="action" value="delete">
					                      
					                      <button type="submit" class="btn btn-danger" id="confirmDelete">確認</button>
					                    </div>
					                  </div>
					                </div>
					              </div>
					                      </form>
									<!-- End Basic Modal-->
				              </div>
			              </div>
		              </div>
					<!-- Form Elements -->
					<form class="ml-5" method="post" enctype="multipart/form-data" action="/CGA101G2/article">

						<div class="row mb-3">
							<label for="inputText" class="col-lg-1 col-form-label">標題</label>
							<div class="col-lg-11">
								<input name="title" type="text" class="form-control" style="width:100%;background:none;border:none;" value="${artiVO.title}" disabled id='titleInput'>
							<span style="color:#f33;">${errorMsgs.title}</span></div>
						</div>

						<div class="row mb-3">
							<label for="inputPassword"
								class="col-lg-1 col-form-label">類型</label>
							<div class="col-lg-11">
								<div class="form-check" style="display:inline-block">
									<input class="form-check-input" type="radio" ${(artiVO.type==1)?"checked":""}
										name="type" id="gridRadios1" value="1"
										disabled><label class="form-check-label"
										for="gridRadios1"> 商城優惠 </label>
								</div>
								<div class="form-check" style="display:inline-block">
									<input class="form-check-input" type="radio" ${(artiVO.type==0)?"checked":""}
										name="type" id="gridRadios2" value="0" disabled >
									<label class="form-check-label" for="gridRadios2">
										最新公告 <span style="color:#f33;">(請注意！第一張為首圖)</span></label>
								</div>
							</div>
						</div>
						
						<div class="row mb-3">
							<label for="inputText" class="col-lg-1 col-form-label">建檔於</label>
							<div class="col-lg-11">
								<input type="text" class="form-control" style="width:100%;background:none;border:none;" value="<fmt:formatDate value="${artiVO.createTime}" pattern="yyyy-MM-dd HH:mm"/>" disabled>
</div>
						</div>
						
						<div class="row mb-3">
							<label for="inputPassword"
								class="col-lg-1 col-form-label">內容</label>
							<div class="col-lg-11">
								<textarea name="content" class="form-control" style="width:100%;resize:none;background:none;border:none;" disabled id='contentArea'>${artiVO.content}</textarea>
								<span style="color:#f33;">${errorMsgs.content}</span>
							</div>
						</div>

						<div class="row mb-3">
							<label for="inputNumber"
								class="col-lg-1 col-form-label">圖片</label>
								<div class="col-lg-11">
								<c:forEach var="picVO" items="${picList}">
								<div id="preDiv${picVO.pictureId}" style="float:left;position:relative;">
									<input name="file" accept="image/*" type="file" style="display: none;">
									<img src="${picVO.url}" onerror="this.src='${picVO.url}'"
									class="mb-3 ml-3" style="height:266px;width:400px;object-fit: cover;"><i
									class="far fa-window-close text-muted preBtns" id="preBtn${picVO.pictureId}" style="position:absolute;top:0;right:0;font-size:2em;"
									onclick="remove(this.id)"></i>
								</div>
								</c:forEach> 
								<img src="back/article/add-image.png" class="ml-3" style="height:200px;width:200px;" onclick="add()" id="addimage">
								<span style="color:#f33;">${errorMsgs.parts}</span>
								</div>
						</div>
						
						<div class="row mb-3">
							<label class="col-lg-1 col-form-label">管理員</label>
							<jsp:useBean id="empSvc" scope="page"
											class="com.emp.model.EmpService" />
							<div class="col-lg-3">
								<select class="form-select" name="empNo" id='empInput' style="background:none;border:none;"
									aria-label="Default select example" disabled>
								<c:forEach var="empVO" items="${empSvc.all}">
									<option value="${empVO.empNo}" ${(artiVO.empVO.empNo==empVO.empNo)? 'selected':''}>${empVO.empName}
								</c:forEach>
								</select>
								<span style="color:#f33;">${errorMsgs.empNo}</span>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-lg-10"></div>
							<div class="col-lg-2">
								<input type="hidden" name="action" value="update">
								<button type="submit" class="btn btn-success">修改</button>
							</div>
						</div>
						<input type="hidden" name='articleId' value="${artiVO.articleId}">
						<input type="hidden" name='picIdArray' id="deletePics">
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
<script src="/assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
$('#addimage').hide();
$('.btn-success').hide();
$('.preBtns').hide();

let removeIds = [];
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

function updateBtn(){
	$('#titleInput').removeAttr('disabled').attr('style','background:white;border:1px #CED4DA solid;');
	$('#gridRadios1').removeAttr('disabled');
	$('#gridRadios2').removeAttr('disabled');
	$('#contentArea').removeAttr('disabled').attr('style','background:white;height:30vh;border:1px #CED4DA solid;');
	$('#empInput').removeAttr('disabled').css('background','white').css('border','1px #CED4DA solid');
	$('.preBtns').show();
	$('#addimage').show();
	$('.btn-success').show();
}

function remove(preBtn) {
	let select = preBtn.replace('preBtn','#preDiv');
	let id = preBtn.replace('preBtn','');
	removeIds.push(id);
	console.log(removeIds);
	$(select).remove();
	$('#deletePics').val(removeIds);
	}

</script>

</body>

</html>