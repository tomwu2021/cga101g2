<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>會員中心</title>

<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/pet/style.css">

</head>
<body>
	<!-- 共用的header start-->
	<%@ include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->

	<!-- 共用的leftnav start-->
	<%@include file="/front/member/layout/leftnav.jsp"%>
	<!-- 共用的leftnav end-->
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
	<div class="page-holder w-100 d-flex flex-wrap">
		<div class="container-fluid px-xl-5">
			<section>
				<div class="page-holder w-100 d-flex flex-wrap">
					<div class="container-fluid px-xl-5">
						<section class="py-5">
							<div class="row">
							
						 <!--! 會員中心首頁資料 -->
                  <!--! Horizontal Form-->
                  <div class="col-lg-10 mb-5">
                    <div class="card">
                      <div class="card-header">
                        <h3 class="h6 text-uppercase mb-0">會員等級</h3>
                      </div>
                      <div class="card-body" style="padding-top:30px">
                        <div class="row">
                        <div class="col-lg-9 mb-5">
                        <!-- <form class="form-horizontal"> -->
                          <div class="form-group row">
                            <label class="col-md-3 form-control-label">您的會員等級 :</label>
                            <div class="col-md-9">
                              <label class="col-md-12 form-control-label"><span class="highlight" id="rankId">${membersVO.rankId}</span></label>
                            </div>
                          </div>
                          <div class="form-group row">
                            <label class="col-md-3 form-control-label">累積消費金額 :</label>
                            <div class="col-md-9">
                              <label class="col-md-10 form-control-label"><span class="highlight" id="totalMoney"> </span>元</label>
                            </div>
                          </div>
                          <div class="form-group row">
                            <label class="col-sm-3 form-control-label">會員等級說明 :</label>
                            <div class="col-md-12">
                              <table class="table card-text">
                                <thead>
                                  <tr>
                                    <th>#</th>
                                    <th>一般會員</th>
                                    <th>黃金會員</th>
                                    <th>白金會員</th>
                                    <th>鑽石會員</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <tr>
                                    <th scope="row">折數</th>
                                    <td>無</td>
                                    <td>95折</td>
                                    <td>9折</td>
                                    <td>85折</td>
                                  </tr>
                                  <tr>
                                    <th scope="row">累積消費</th>
                                    <td>0 ~ 2000 元</td>
                                    <td>2001 ~ 5000 元</td>
                                    <td>5001 ~ 10000 元</td>
                                    <td>10001 元以上</td>
                                  </tr>
                                </tbody>
                              </table>
                            </div>
                          </div>
                            <!-- ! 提交按鈕 -->
                          <!-- <div class="form-group row">       
                            <div class="col-md-9 ml-auto">
                              <input type="submit" value="儲存" class="btn btn-primary">
                            </div>
                          </div> -->
                            <!--! 提交按鈕結束 -->
                      </div>
                    <!-- </form> -->
                    </div>
                  </div>
                       <!--! Horizontal Form結束-->
							
							
							</div>
						</section>
					</div>
				</div>
			</section>
			<!--! Horizontal Form結束-->



		</div>
	</div>
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
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/assets/js/memberBonus.js"></script>
		
</body>



		
</html>