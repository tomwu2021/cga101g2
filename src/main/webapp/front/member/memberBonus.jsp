<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS startr-->
<%@include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<!-- 	路徑舉例 -->
<%-- <link rel="stylesheet"href="<%=request.getContextPath()%>/assets/?????待討論"> --%>
<!-- 額外添加的CSS -->
<body>
	<!-- 共用的header start-->
	<%@ include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->

	<!-- 共用的leftnav start-->
	<%@include file="/front/member/layout/leftnav.jsp"%>
	<!-- 共用的leftnav end-->

	<!--! ========內容======== -->
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
                      <div class="card-body">
                        <p>Lorem ipsum dolor sit amet consectetur.</p>
                        <div class="row">
                        <div class="col-lg-9 mb-5">
                        <!-- <form class="form-horizontal"> -->
                          <div class="form-group row">
                            <label class="col-md-3 form-control-label">您的會員等級 :</label>
                            <div class="col-md-9">
                              <label class="col-md-12 form-control-label"><span class="highlight">黃金</span>(等級期限 2022/08/31 止)</label>
                            </div>
                          </div>
                          <div class="form-group row">
                            <label class="col-md-3 form-control-label">累積消費金額 :</label>
                            <div class="col-md-9">
                              <label class="col-md-10 form-control-label"><span class="highlight">100000 </span>元</label>
                            </div>
                          </div>
                          <div class="form-group row">
                            <label class="col-sm-3 form-control-label">會員等級說明 :</label>
                            <div class="col-md-12">
                              <table class="table card-text">
                                <thead>
                                  <tr>
                                    <th>#</th>
                                    <th>黃金會員</th>
                                    <th>黃金會員</th>
                                    <th>黃金會員</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <tr>
                                    <th scope="row">折數</th>
                                    <td>95折</td>
                                    <td>98折</td>
                                    <td>56折</td>
                                  </tr>
                                  <tr>
                                    <th scope="row">累積消費</th>
                                    <td>0~5000元</td>
                                    <td>5000~8000元</td>
                                    <td>8000元以上</td>
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


			<!-- 共通的footer start-->
			<%@include file="/back/layout/footer.jsp"%>
			<!-- 共通的footer end-->
		</div>
	</div>


	<!-- 共用的JS -->
	<%@include file="/back/layout/commonJS.jsp"%>
	<!-- 共用的JS -->

	<!-- 額外添加的JS -->
	<!-- 	路徑舉例 -->
	<%-- <link rel="stylesheet"href="<%=request.getContextPath()%>/assets/?????待討論"> --%>
	<!-- 額外添加的JS -->

</body>

</html>