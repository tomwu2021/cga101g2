<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>會員錢包儲值</title>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/pet/style.css">
<!-- 額外添加的CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/memberWallet.css">

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

							<div class="container">
								<!-- 								<div class="row"> -->

								<div class="col-xs-12 col-md-4">


									<!-- CREDIT CARD FORM STARTS HERE -->
									<div class="panel panel-default credit-card-box"
										style="width: 500px;">
										<div class="panel-heading display-table"
											style="margin-bottom: 20px;">
											<div class="row display-tr">
												<h3 class="panel-title display-td"
													style="padding-left: 26px">付款資訊</h3>
												<div class="display-td"></div>
											</div>
										</div>
										<div class="panel-body" style="margin: auto">
											<!-- 											<form role="form" id="payment-form"> -->
											<form method="post"
												action="<%=request.getContextPath()%>/front/member.do"
												id="payment-form">




												<div class="row">
													<div class="col-xs-12">
														<div class="form-group">
															<label for="cardNumber"
																style="padding-left: 15px; margin-top: 20px; font-weight: bold; font-size: 20px">儲值金額<font
																color=red> ${messages.inputError}</font></label>
															<div class="input-group"
																style="width: 350px; padding-left: 15px">
																<input type="text" class="form-control"
																	name="storedValueAmount"
																	placeholder="Stored value amount"
																	autocomplete="cc-number" required autofocus value="${messages.resStoredValueAmount}"/>
															</div>
														</div>
													</div>
												</div>




												<div class="row">
													<div class="col-xs-12" style="margin-top: 15px;">
														<div class="form-group" style="display: inline-block">
															<!-- 															<label for="cardNumber" style="padding-left: 15px"></label> -->
															<span class="input-group-addon"
																style="padding-left: 15px; margin-top: 15px; font-weight: bold; font-size: 20px"">卡號<i
																class="fa fa-credit-card" style="padding-left: 15px"></i></span>
															<div class="input-group"
																style="width: 300px; padding-left: 15px">
																<input type="tel" class="form-control" name="cardNumber"
																	placeholder="Valid Card Number"
																	autocomplete="cc-number" required autofocus
																	value="${messages.resCardNumber}" />
															</div>

														</div>
													</div>
												</div>



												<div class="row">
													<div class="col-xs-7 col-md-7" style="margin-top: 15px;">
														<div class="form-group">
															<label for="cardExpiry"><span class="hidden-xs"
																style="padding-left: 5px; margin-top: 15px; font-weight: bold; font-size: 20px">有效日期</span><span
																class="visible-xs-inline"></span> </label> <input type="tel"
																class="form-control" name="cardExpiry"
																placeholder="MM / YY" autocomplete="cc-exp" required />
														</div>
													</div>
													<div class="col-xs-5 col-md-5 pull-right">
														<div class="form-group">
															<label for="cardCVC"
																style="padding-left: 5px; margin-top: 15px; font-weight: bold; font-size: 20px">安全碼</label>
															<input type="tel" class="form-control" name="cardCVC"
																placeholder="CVC" autocomplete="cc-csc" required />
														</div>
													</div>
												</div>



												<div class="row">
													<div class="col-xs-12">
														<div class="form-group">
															<label for="cardNumber"
																style="padding-left: 15px; margin-top: 20px; font-weight: bold; font-size: 20px">錢包密碼<font
																color=red>
																	${messages.inputErrorpasswordWallet}</font></label>
															<div class="input-group"
																style="width: 350px; padding-left: 15px">
																<input  maxlength="6" type="password" class="form-control"
																	name="passwordWallet"
																	placeholder="Password Wallet" autocomplete="cc-number"
																	required autofocus value="${messages.resPasswordWallet}"  />
															</div>
														</div>
													</div>
												</div>


												<div class="row">
													<div class="col-xs-12">
														<div class="form-group"></div>
													</div>
												</div>
												<div class="row">
													<div class="col-xs-12">
														<!-- 															<button class="btn btn-success btn-lg btn-block" -->
														<!-- 																type="submit" >送出</button> -->

														<button
															class="btn btn-success btn-lg btn-block floating-button0"
															type="submit"
															style="font-size: 16px; background-color: orange; line-height: 35px">送出</button>
														<input type="hidden" name="action" value="walletAddMoney">
														<% session.setAttribute("submit","OK"); %>
														

													</div>
												</div>
												<div class="row" style="display: none;">
													<div class="col-xs-12">
														<p class="payment-errors"></p>
													</div>
												</div>
											</form>
										</div>
									</div>
									<!-- CREDIT CARD FORM ENDS HERE -->


								</div>



								<!-- 								</div> -->
							</div>

							<!-- If you're using Stripe for payments -->
							<script type="text/javascript" src="https://js.stripe.com/v2/"></script>


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
		src="<%=request.getContextPath()%>/assets/js/memberWallet.js"></script>
		<jsp:include page="/front/layout/showMessage.jsp" />
</body>
	
</html>