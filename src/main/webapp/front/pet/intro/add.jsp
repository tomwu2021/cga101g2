<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<%
Integer petId = (Integer)(session.getAttribute("petId"));
%>
<%--舊的petId --%>
<% 
	java.util.Calendar cal = Calendar.getInstance();
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND,0);
	cal.set(Calendar.HOUR, 0);
	java.sql.Date defaultDate = new java.sql.Date(cal.getTime().getTime());
%>
<!DOCTYPE html>
<html>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 自訂的CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/pet/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back/article/style.css">
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
				<div class="card">
					<form method="post" action="<%=request.getContextPath()%>/pet" id="editForm">
						<div class="card-body">
							<div class="row">
								<div class="card-title col-lg-11">寵物資訊</div>
								<div class="card-title col-lg-1">
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
										<div class='col-lg-6'>
											<div class="row mb-3">
												<label for="inputText" class="col-lg-2 col-form-label">暱稱</label>
												<div class="col-lg-8">
													<input name="title" type="text" class="form-control" style="width:100%">
													<span style="color:#f33;">${errorMsgs.petName}</span>
												</div>
											</div>
											<div class="row mb-3">
												<label for="inputPassword"
													class="col-lg-2 col-form-label">品種</label>
												<div class="col-lg-8">
													<div class="form-check" style="display:inline-block">
														<input class="form-check-input" type="radio"
														name="type" id="gridRadios1" value="2">
														<label class="form-check-label"
														for="gridRadios1"> 狗 </label>
													</div>
													<div class="form-check" style="display:inline-block">
													<input class="form-check-input" type="radio"
														name="type" id="gridRadios2" value="1">
													<label class="form-check-label" for="gridRadios2"> 貓 <span style="color:#f33;">${errorMsgs.sort1Id}</span></label>
													</div>
												</div>
											</div>
											<div class="row mb-3">
												<label for="inputNumber"
												class="col-lg-2 col-form-label">頭貼</label>
												<div class="col-lg-8">
												              <!-- Large Modal -->
              <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#largeModal">
                Large Modal
              </button>

              <div class="modal fade" id="largeModal" tabindex="-1">
                <div class="modal-dialog modal-lg">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title">Large Modal</h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      Non omnis incidunt qui sed occaecati magni asperiores est mollitia. Soluta at et reprehenderit.
                      Placeat autem numquam et fuga numquam. Tempora in facere consequatur sit dolor ipsum. Consequatur
                      nemo amet incidunt est facilis. Dolorem neque recusandae quo sit molestias sint dignissimos.
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                      <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                  </div>
                </div>
              </div><!-- End Large Modal-->
													<label class="btn btn-info"><input id="upload_img" style="display:none;" type="file" accept="image/*"><i
												            class="fa fa-photo"></i> 上傳圖片</label>
												    <div id="oldImg" style="display:none;"></div>
												    <button id="crop_img" class="btn btn-info"><i class="fa fa-scissors"></i> 裁剪圖片</button>
												    <div id="newImgInfo"></div>
												    <div id="newImg"></div>
												    
												</div>
											</div>
										</div>
										<div class='col-lg-6'>
											<div class="row mb-3">
												<label for="inputPassword"
													class="col-lg-2 col-form-label">簡介</label>
												<div class="col-lg-8">
													<textarea name="content" class="form-control" style="height:30vh;width:100%;resize:none;"></textarea>
													<span style="color:#f33;">${errorMsgs.introduction}</span>
												</div>
											</div>
											<div class="row mb-3">
												<label class="col-lg-1 col-form-label">生日</label>
												<input type='text' id='r_date1'>
											</div>
											<div class="row mb-3">
												<label class="col-lg-1 col-form-label">性別</label>
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
<script src="/assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/croppie/2.6.5/croppie.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/croppie/2.6.5/croppie.min.js"></script>
<script	src="croppieImage.js"></script>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/assets/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/assets/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh');
	$('#r_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value: new Date(),
		maxDate: '<%=defaultDate%>',
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
</html>