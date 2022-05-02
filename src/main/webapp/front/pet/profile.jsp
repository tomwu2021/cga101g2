<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.pet.*"%>
<%@ page import="com.pet_activity.*"%>
<%@ page import="com.pet_weight.*"%>

<!DOCTYPE html>
<html>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 自訂的CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/pet/style.css">
<body>
	<!-- 共用的header start -->
	<%@ include file="/front/layout/header.jsp"%>
	<!-- 共用的header end -->
	<%--breadcrumbs area ??--%>
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
			
			<!-- Left side columns -->
			<div class="col-lg-8">
				<div class="row">

					<!-- Pet Card -->
					<div class="col-xxl-4 col-xl-12">

						<div class="card info-card customers-card">

 							<div class="top_links filter">
                                <a class="icon" href="javascript:void(0)"><i class="bi bi-three-dots"></i></a>
                                <div class="dropdown_links filtermenu">
                                    <div class="dropdown_links_list">
                                        <ul>
                                            <li><a href="#" class="dropdown-item">新增紀錄</a></li>
                                            <li><a href="#" class="dropdown-item">查看更多</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

							<div class="card-body">
								<h5 class="card-title">
									寵物基本資訊 <span>| This Year</span>
								</h5>

								<div class="d-flex align-items-center">
									<div
										class="card-icon rounded-circle d-flex align-items-center justify-content-center">
										<i class="bi bi-people"></i>
									</div>
									<div class="ps-3">
										<h6>1244</h6>
										<span class="text-danger small pt-1 fw-bold">12%</span>
										<span class="text-muted small pt-2 ps-1">decrease</span>

									</div>
								</div>

							</div>
						</div>

					</div>
					<!-- End Pet Card -->
					<!-- Recent Weight Card -->
					<div class="col-xxl-4 col-md-6">
						<div class="card info-card sales-card">

							<div class="top_links filter">
                                <a class="icon" href="javascript:void(0)"><i class="bi bi-three-dots"></i></a>
                                <div class="dropdown_links filtermenu">
                                    <div class="dropdown_links_list">
                                        <ul>
                                            <li><a href="#" class="dropdown-item">新增紀錄</a></li>
                                            <li><a href="#" class="dropdown-item">查看更多</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

							<div class="card-body">
								<h5 class="card-title">
									最近一次體重 <span>| Today</span>
								</h5>

								<div class="d-flex align-items-center">
									<div
										class="card-icon rounded-circle d-flex align-items-center justify-content-center">
										<i class="bi bi-cart"></i>
									</div>
									<div class="ps-3">
										<h6>145</h6>
										<span class="text-success small pt-1 fw-bold">12%</span>
										<span class="text-muted small pt-2 ps-1">increase</span>

									</div>
								</div>
							</div>

						</div>
					</div>
					<!-- End Recent Weight Card -->

					<!-- Weight Average Card -->
					<div class="col-xxl-4 col-md-6">
						<div class="card info-card revenue-card">

							<div class="top_links filter">
                                <a class="icon" href="javascript:void(0)"><i class="bi bi-three-dots"></i></a>
                                <div class="dropdown_links filtermenu">
                                    <div class="dropdown_links_list">
                                        <ul>
                                            <li><a href="#" class="dropdown-item">新增紀錄</a></li>
                                            <li><a href="#" class="dropdown-item">查看更多</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

							<div class="card-body">
								<h5 class="card-title">
									最近一季平均 <span>| This Month</span>
								</h5>

								<div class="d-flex align-items-center">
									<div
										class="card-icon rounded-circle d-flex align-items-center justify-content-center">
										<i class="bi bi-currency-dollar"></i>
									</div>
									<div class="ps-3">
										<h6>$3,264</h6>
										<span class="text-success small pt-1 fw-bold">8%</span>
										<span class="text-muted small pt-2 ps-1">increase</span>

									</div>
								</div>
							</div>

						</div>
					</div>
					<!-- End Weight Average Card -->

					<!-- Weight Record Card -->
					<div class="col-xxl-4 col-xl-12">

						<div class="card info-card customers-card">

							<div class="top_links filter">
                                <a class="icon" href="javascript:void(0)"><i class="bi bi-three-dots"></i></a>
                                <div class="dropdown_links filtermenu">
                                    <div class="dropdown_links_list">
                                        <ul>
                                            <li><a href="#" class="dropdown-item">新增紀錄</a></li>
                                            <li><a href="#" class="dropdown-item">查看更多</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

							<div class="card-body">
								<h5 class="card-title">
									體重折線圖 <span>| This Year</span>
								</h5>

								<div class="d-flex align-items-center">
									<div
										class="card-icon rounded-circle d-flex align-items-center justify-content-center">
										<i class="bi bi-people"></i>
									</div>
									<div class="ps-3">
										<h6>1244</h6>
										<span class="text-danger small pt-1 fw-bold">12%</span>
										<span class="text-muted small pt-2 ps-1">decrease</span>

									</div>
								</div>

							</div>
						</div>

					</div>
					<!-- End Weight Record Card -->
					

				</div>
			</div>
			<!-- End Left side columns -->

			<!-- Right side columns -->
			<div class="col-lg-4">

				<%-- Recent Activity --%>
				<jsp:include page="/front/pet/activity/channel.jsp">
					<jsp:param value="2" name="petId"/>
				</jsp:include>
				<%-- End Recent Activity --%>
				<%-- Recent Remind --%>
				<jsp:include page="/front/pet/remind/channel.jsp">
					<jsp:param value="1" name="memberId"/>
				</jsp:include>
				<%-- End Recent Remind --%>

			</div>
			<!-- End Right side columns -->
			
			<!-- Banner -->
			<div class="col-lg-12">
				<div class="row">
					<!-- Albums Card -->
					<div class="col-xxl-4 col-xl-12">

						<div class="card info-card customers-card">

							<div class="filter">
								<a class="icon" href="#" data-toggle="dropdown"><i
									class="bi bi-three-dots"></i></a>
								<ul
									class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
									<li class="dropdown-header text-start">
										<h6>Filter</h6>
									</li>

									<li><a class="dropdown-item" href="#">Today</a></li>
									<li><a class="dropdown-item" href="#">This
											Month</a></li>
									<li><a class="dropdown-item" href="#">This
											Year</a></li>
								</ul>
							</div>

							<div class="card-body">
								<h5 class="card-title">
									相簿管理 <span>| This Year</span>
								</h5>

								<div class="d-flex align-items-center">
									<div
										class="card-icon rounded-circle d-flex align-items-center justify-content-center">
										<i class="bi bi-people"></i>
									</div>
									<div class="ps-3">
										<h6>1244</h6>
										<span class="text-danger small pt-1 fw-bold">12%</span>
										<span class="text-muted small pt-2 ps-1">decrease</span>

									</div>
								</div>

							</div>
						</div>
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
	
</body>

</html>