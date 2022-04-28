<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<div class="main_header sticky-header">
	<div class="container-fluid">
		<div class="header_container">
			<div class="row align-items-center">
				<div class="col-lg-2">
					<div class="logo">
						<a href="<%=request.getContextPath()%>+index.html"><img
							src="../assets/shop/img/logo/logo.png" alt=""></a>
					</div>
				</div>
				<div class="col-lg-7">
					<!--main menu start-->
					<div class="main_menu menu_position">
						<nav>
							<ul>
								<li><a href="blog.html">最新消息<i class="fa fa-angle-down"></i></a>
									<ul class="sub_menu pages">
										<li><a
											href="<%=request.getContextPath()%>/blog-details.html">分類?</a></li>
										<li><a
											href="<%=request.getContextPath()%>/blog-fullwidth.html">狗狗專區</a></li>
										<li><a
											href="<%=request.getContextPath()%>/blog-sidebar.html">貓狗通用</a></li>
									</ul></li>
								<li><a href="<%=request.getContextPath()%>/front/shop.html">全部商品<i
										class="fa fa-angle-down"></i></a>
									<ul class="sub_menu pages">
									</ul></li>
								<li><a
									href="<%=request.getContextPath()%>/front/groupshop.html">團購專區<i
										class="fa fa-angle-down"></i></a>
									<ul class="sub_menu pages">
										<li><a
											href="<%=request.getContextPath()%>/groupshop.html">我要開團</a></li>
										<li><a
											href="<%=request.getContextPath()%>/groupshop.html">我要跟團</a></li>
									</ul></li>
								<li><a href="<%=request.getContextPath()%>/">寵物社群</a>
									<ul class="sub_menu pages">
									</ul></li>
								<li><a href="<%=request.getContextPath()%>/contact.html">
										找客服</a></li>
							</ul>
						</nav>
					</div>
					<!--!main menu end-->
					<!--! 導覽列右側icon -->
				</div>
				<div class="col-lg-3">
					<div class="header_account_area">
						<div class="header_account-list"
							style="padding: 10px 0px 0px 0px;">
							<a id="notifications" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"
								class="nav-link dropdown-toggle text-gray-400 px-1"><i
								class="fa fa-bell"></i><span class="notification-icon"></span></a>
							<div aria-labelledby="notifications " class="dropdown-menu ">
								<a href="#" class="dropdown-item ">
									<div class="d-flex align-items-center">
										<i class="icon icon-Mail" style="color: #dc3545;"></i>
										<div class="text ml-2">
											<p class="mb-0">系統通知系統通知系統通知</p>
										</div>
									</div>
								</a><a href="#" class="dropdown-item">
									<div class="d-flex align-items-center">
										<i class="icon icon-Message" style="color: #007bff;"></i>
										<div class="text ml-2">
											<p class="mb-0">商城通知</p>
										</div>
									</div>
								</a><a href="#" class="dropdown-item">
									<div class="d-flex align-items-center">
										<i class="icon icon-Mail" style="color: #dc3545;"></i>
										<div class="text ml-2">
											<p class="mb-0">系統通知</p>
										</div>
									</div>
								</a><a href="#" class="dropdown-item">
									<div class="d-flex align-items-center">
										<i class="icon icon-Message" style="color: #007bff;"></i>
										<div class="text ml-2">
											<p class="mb-0">商城通知</p>
										</div>
									</div>
								</a>
								<div class="dropdown-divider"></div>
								<a href="#" class="dropdown-item text-center"><small
									class="font-weight-bold headings-font-family text-uppercase">查看所有通知</small></a>
							</div>
							</li>
						</div>
						<!--! 導覽列右側icon會員 style.css 905 -->
						<!-- <div class="header_account-list" style="padding: 10px 0px 0px 0px; margin-right: 70px; >
                            <a href="javascript:void(0)"><svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                              </svg></span></a>
                         </div> -->
						<div class="header_account-list" style="margin-right: 70px;">
							<ul class="ml-auto d-flex align-items-center list-unstyled mb-0">
								<li class="nav-item dropdown ml-auto"><a id="userInfo"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="nav-link dropdown-toggle"><img
										src="https://picsum.photos/200/300" alt="Jason Doe"
										style="max-width: 2.5rem;"
										class="img-fluid rounded-circle shadow"></a>
									<div aria-labelledby="userInfo" class="dropdown-menu">
										<a href="#" class="dropdown-item"><strong
											class="d-block text-uppercase headings-font-family">Mark
												Stephen</strong><small>Web Developer</small></a>
										<div class="dropdown-divider"></div>
										<a href="login.html" class="dropdown-item">Logout</a>
									</div></li>
							</ul>
							<!--! 導覽列右側icon會員 end-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--! Cart svg設定  -->
<div class="cart">
	<a href="#"><svg xmlns="http://www.w3.org/2000/svg" width="40"
			height="40" fill="currentColor" class="bi bi-cart4"
			viewBox="0 0 16 16">
    <path
				d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z" />
  </svg></a>
</div>
<!-- !Cartsvg設定 end  -->
<!--! Messenger svg設定  -->
<div class="wishlist_link">
	<a href="#"><svg xmlns="http://www.w3.org/2000/svg" width="40"
			height="40" fill="currentColor" class="bi bi-clipboard-heart"
			viewBox="0 0 16 16">
    <path fill-rule="evenodd"
				d="M5 1.5A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5v1A1.5 1.5 0 0 1 9.5 4h-3A1.5 1.5 0 0 1 5 2.5v-1Zm5 0a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-1Z" />
    <path
				d="M3 1.5h1v1H3a1 1 0 0 0-1 1V14a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V3.5a1 1 0 0 0-1-1h-1v-1h1a2 2 0 0 1 2 2V14a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V3.5a2 2 0 0 1 2-2Z" />
    <path
				d="M8 6.982C9.664 5.309 13.825 8.236 8 12 2.175 8.236 6.336 5.31 8 6.982Z" />
  </svg></a>
</div>
<!-- !Messenger svg設定 end  -->