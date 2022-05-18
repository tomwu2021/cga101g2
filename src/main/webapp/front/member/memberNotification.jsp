<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>會員等級說</title>
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

	<!--! ========內容======== -->
	<div class="page-holder w-100 d-flex flex-wrap">
		<div class="container-fluid px-xl-5">
			<section>
				<div class="page-holder w-100 d-flex flex-wrap">
					<div class="container-fluid px-xl-5">
						<section class="py-5">
							<div class="row">
							
							    <!--! Horizontal Form-->
                  <div class="col-lg-12 mb-5">
                    <div class="card">
                      <div class="card-header">
                        <h3 class="h6 text-uppercase mb-0">會員紅利</h3>
                      </div>
                      <div class="card-body">
                        <p>Lorem ipsum dolor sit amet consectetur.</p>
                        <div class="row">
                        <div class="col-lg-12 mb-5">
                        <!-- <form class="form-horizontal"> -->
                          <div class="form-group row">
                            <label class="col-md-3 form-control-label">您累計的紅利共  :</label>
                            <div class="col-md-9">
                              <span class="highlight">10</span>點(等級期限 2022/08/31 止)
                            </div>
                          </div>
                          <div class="form-group row">
                            <label class="col-md-3 form-control-label">查詢日期 :</label>
                            <div class="col-md-9">
                              <input type="date" value="2020-04-20" min="2022-01-01" max="2050-01-01" step="1"> ~
                              <input type="date" value="2020-04-20" min="2022-01-01" max="2050-01-01" step="1">
                              &emsp;<input type="submit" value="確定" class="btn btn-primary">
                            </div>
                          </div>
                        </div>
                      </div>
                       <!-- !分頁符號 css在style.default.css 4237 -->
                          <div class="row justify-content-center align-items-center">
                               <ul class="pagination">
                                <li><a href="#">«</a></li>
                                <li><a href="#">1</a></li>
                                <li><a class="active" href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#">6</a></li>
                                <li><a href="#">7</a></li>
                                <li><a href="#">»</a></li>
                              </ul>
                          </div>      
                              <!-- !分頁符號 css在style.default.css 4237 -->    
                              <div class="form-group row">
                                <div class="col-lg-12">
                              <div class="card-body"> 
                                <table class="table table-striped table-hover card-text">
                                  <thead>
                                    <tr class="text-center">
                                      <th style="width: 20%;">日期</th>
                                      <th>新增點數</th>
                                      <th>扣除點數</th>
                                      <th>訂單編號</th>
                                      <th>備註</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    <tr class="text-center">
                                      <td>2022-04-09</td>
                                      <td>1 點</td>
                                      <td>1 點</td>
                                      <td>02201291818745</td>
                                      <td class="text-left">賣家友善計劃賣家友善計劃賣家友善計劃賣家友善計劃</td>
                                    </tr>
                                    <tr>
                                      <td>Jacob</td>
                                      <td>Thornton</td>
                                      <td>@fat</td>
                                    </tr>
                                    <tr>
                                      <td>Larry</td>
                                      <td>the Bird</td>
                                      <td>@twitter</td>
                                    </tr>
                                    <tr>
                                      <td>Sam</td>
                                      <td>Nevoresky</td>
                                      <td>@facebook</td>
                                    </tr>
                                  </tbody>
                                </table>
                              </div>
                            </div>
                          </div>   
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
			<!--! Horizontal Form結束-->



		</div>
	</div>


</div>
	<%@include file="/front/pet/footer.jsp"%>
	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 自訂的JS -->

</body>

</html>