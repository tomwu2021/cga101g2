<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<%
Integer memberId = session.getAttribute("membersVO")==null ? -999:((MembersVO)session.getAttribute("membersVO")).getMemberId();
Integer petId = session.getAttribute("membersVO")==null ? -999:((MembersVO)session.getAttribute("membersVO")).getPetVO().getPetId();
%>
<%--舊的petId --%>
<!DOCTYPE html>
<html>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 自訂的CSS -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/croppie/2.6.5/croppie.css"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/pet/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/sb-admin-2.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/datetimepicker/jquery.datetimepicker.css" />


<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>
<body>
	<!-- 共用的header start -->
	<%@ include file="/front/layout/header.jsp"%>
	<!-- 共用的header end -->
	<%--bread crumb area ?? title ?? --%>
	<!-- 共用的side bar start -->
	<%@ include file="/front/member/layout/leftnav.jsp"%>
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
										<div class="col-lg-2 col-md-12"></div>
											<div class="col-lg-10 col-md-12">
	<!-- ============================= Main ============================= -->
	<section class="section dashboard">
		<div class="row">

			<div class="col-lg-12">
				<!-- ALL introduction -->
				<div class="card border-bottom-warning">
					<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/pet" id="editForm">
						<div class="card-body">
							<div class="row">
								<div class="card-title col-lg-11">新建寵物資料</div>
								<div class="card-title col-lg-1">
									<input type="hidden" name="memberId" value="<%=memberId%>">
									<input type="hidden" name="petId" value="<%=petId%>">
									<input type="hidden" name="action" value="insert">
									<button class="btn btn-success form-btn-circle" type="submit">
									送出 <i class="fas fa-arrow-right"></i>
									</button>
								</div>
							</div>
							<div class="row"></div>
							<div class="row">
								<div class="col-lg-12 mb-5" id="editBox">
									<!-- Form Elements -->
									<div class="row">
										<div class='col-lg-5'>
											<div class="row mb-3">
												<label for="inputText" class="col-lg-2 col-form-label">暱稱<span style='color:#f33;'> *</span></label>
												<div class="col-lg-8">
													<input name="petName" type="text" class="form-control" style="width:100%" value='${param.petName}'>
													<span style="color:#f33;">${errorMsgs.petName}</span>
												</div>
											</div>
											<div class="row mb-3">
												<label for="inputPassword"
													class="col-lg-2 col-form-label">品種<span style="color:#f33;"> *</span></label>
												<div class="col-lg-8">
													<div class="form-check" style="display:inline-block">
														<input class="form-check-input" type="radio"
														name="sort1Id" id="gridRadios1" value="2" ${(param.sort1Id==2)?'checked':''}>
														<label class="form-check-label"
														for="gridRadios1"> 狗 </label>
													</div>
													<div class="form-check" style="display:inline-block">
													<input class="form-check-input" type="radio"
														name="sort1Id" id="gridRadios2" value="1" ${(param.sort1Id==1)?'checked':''}>
													<label class="form-check-label" for="gridRadios2"> 貓 <span style="color:#f33;">${errorMsgs.sort1Id}</span></label>
													</div>
												</div>
											</div>
											<div class="row mb-3">
												<label for="inputNumber"
												class="col-lg-2 col-form-label">頭貼</label>
												<div class="col-lg-8" id='headshotBox'>
													<label class="btn btn-primary mb-3"><input id="upload_img" style="display:none;" name='file' type="file" accept="image/*"><i class="fa fa-photo"></i> 上傳相片</label>
												</div>
											</div>
										</div>
										<div class='col-lg-7'>
										
											<div class="row mb-3">
												<label class="col-lg-2 col-form-label">性別</label>
												<div class="col-lg-9">
													<div class="form-check" style="display:inline-block">
														<input class="form-check-input" type="radio"
														name="gender" id="gridRadios1" value="1" ${(param.gender==1)?'checked':''}>
														<label class="form-check-label"
														for="gridRadios1"> 男孩 </label>
													</div>
													<div class="form-check" style="display:inline-block">
													<input class="form-check-input" type="radio"
														name="gender" id="gridRadios2" value="2" ${(param.gender==2)?'checked':''}>
													<label class="form-check-label" for="gridRadios2"> 女孩 </label>
													</div>
												</div>
											</div>
											<div class="row mb-3">
												<label class="col-lg-2 col-form-label">生日</label>
												<div class="col-lg-9">
												<input name="birthday" id='r_date1' type="text" class="form-control" style="width:30%" value='${param.birthday}'>
												<span style="color:#f33;">${errorMsgs.birthday}</span>
												</div>
											</div>
											<div class="row mb-3">
												<label for="inputPassword"
													class="col-lg-2 col-form-label">簡介</label>
												<div class="col-lg-9">
													<textarea name="introduction" class="form-control" style="height:30vh;width:100%;resize:none;">${param.introduction}</textarea>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
					<!-- End Form Elements -->
				</div>
				<!-- End All introduction -->
			</div>
		</div>
	</section>
	<!-- =========================== End Main =========================== -->
										</div>
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
	<%@include file="/front/pet/footer.jsp"%>
	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 自訂的JS -->
<script src="<%=request.getContextPath()%>/assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
let Element = document.querySelector('#upload_img');
Element.addEventListener('change', function() { 
$('#headshot').remove();
let url = URL.createObjectURL(Element.files[0]);
$('#headshotBox').append('<img src="'+url+'" id="headshot" class="mb-3 ml-3" style="height:250px;width:250px;object-fit: cover;">');

});
</script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/croppie/2.6.5/croppie.min.js"></script> -->
<!-- <script	src="croppieImage.js"></script> -->
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<script src="<%=request.getContextPath()%>/assets/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/assets/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
	$.datetimepicker.setLocale('zh');
	$('#r_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
// 		value: new Date(),
		maxDate: new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
</html>