<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<title>PCLUB 登入</title>
<!-- login 的 css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/login.css" />
<!-- register 註冊 css -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/register.css">
<%@ include file="/front/layout/commonCSS.jsp"%>
</head>

<body>
<%@ include file="/front/layout/commonJS.jsp"%>

	<!----------------------------- 登入視窗 ---------------------------------->

	<form method="post" action="member.do" class="login">
		<div>
			<h1>登 入 帳 號</h1>
			<img src="<%=request.getContextPath()%>/assets/img/logo/logo_icon.png">
		</div>
		<h2 style="font-size:28px;margin-top:20px;font-weight:bold">帳號<font color=red>　${messages.messagesAccount}</font></h2>
		<input type="text" name="loginAccount" placeholder="請輸入電子郵件" value="${messages.originalAccount}" />

		<h2 style="font-size:28px;margin-top:20px;font-weight:bold">密碼<font color=red>　${messages.messagesPassword}</font></h2>
		<input type="password" name="loginPassword" placeholder="請輸入密碼" maxlength="12" />
		
		<div class="btns">
			<input type="hidden" name="action" value="forLogin">
			<button id="memberlogin" class="btn1" style="vertical-align: middle" type="submit"><span>會員登入</span></button>
			<div>
				<div id="wrapper">
					<div id="popup" class="opoups_height_width">帳號註冊</div>
					<div id="popup22" class="opoups_height_width">忘記密碼</div>
					<div id="popup3" class="opoups_height_width"><a href="<%=request.getContextPath()%>/index.html">訪客進入</a></div>
				</div>
			</div>
		</div>
	</form>

	<!------------------------------ 註冊視窗 ------------------------------>
	<div id="box" style="height: 500px;font-size:28px;font-weight:bold">
		<h2 style="margin-top: 8px;margin-left:15px;font-weight: bold;">
			帳號
			<div id="viewRegisterAccount" style="display: inline; color: red;font-weight: bold;"></div>
		</h2>

		<input type="text" name="registerAccount" id="registerAccount" placeholder="請輸入電子郵件" /> 
		<input type="button" value="寄送驗證碼" onclick="checkAccount()" class="floating-button0" style="font-size: 16px;background-color: orange" />

		<h2 style="margin-top: 8px;margin-left:15px;font-weight: bold;">
			密碼
			<div id="viewpassword" style="display: inline; color: red;font-weight: bold;" ></div>
		</h2>
		<input type="password" name="registerpassword" id="registerpassword" placeholder="輸入 8 ~ 12 個字符，至少一個英文字母和一個數字" value="" maxlength="12"/>

		<h2 style="margin-top: 8px;margin-left:15px;font-weight: bold;">
			確認密碼
			<div id="viewcheckpassword" style="display: inline; color: red;font-weight: bold;"></div>
		</h2>
		<input type="password" name="registercheckpasswordr" id="registercheckpasswordr" placeholder="輸入 8 ~ 12 個字符，至少一個英文字母和一個數字" maxlength="12"/>

		<h2 style="margin-top: 8px;margin-left:15px;font-weight: bold;">
			輸入驗證碼
			<div id="viewVerificationCode" style="display: inline; color: red;font-weight: bold;"></div>
		</h2>
		<input type="text" name="verificationCode" id="verificationCode" placeholder="輸入驗證碼" /> 
		<input type="button" value="帳號註冊" onclick="registerVerification()" class="floating-button0" style="font-size: 16px;background-color: orange" /> 
		
		<span id="closeBtn">關閉</span>


	</div>


	<!------------------------------ 忘記密碼視窗視窗 ------------------------------>
	<div id="box2">
		<h2 style="margin-top: 8px;margin-left:15px;margin-top:20px;font-weight: bold;">
			帳號
			<div id="viewForgotPassword" style="display: inline; color: red;font-weight: bold;"></div>
		</h2>

		<input type="text" name="forgotPassword" id="forgotPassword" placeholder="請輸入電子郵件" /> 
		<input type="button" value="寄送新密碼" onclick="sendforgotMail()" class="floating-button0" style="font-size: 16px;background-color: orange"/>
		
		<span id="closeBtn2">關閉</span>
	</div>



	<!-- 會員註冊 -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- login.js -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/assets/js/login.js"></script>
	<!-- register.js -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/assets/js/register.js"></script>

<div class="loading" id="spinner">Loading&#8230;</div>

<jsp:include page="/front/layout/showMessage.jsp" />
</body>

</html>