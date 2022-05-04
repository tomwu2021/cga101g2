<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<title>PCLUB</title>
<!-- login 的 css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/login.css" />
<!-- register 註冊 css -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/register.css">

</head>

<body>

	<!----------------------------- 登入視窗 ---------------------------------->

	<form method="post" action="member.do" class="login">
		<div>
			<h1>登 入 帳 號</h1>
			<img
				src="<%=request.getContextPath()%>/assets/img/logo/logo_icon.png">
		</div>
		<h2>
			帳號<font color=red> ${errorMsgs.account}</font>
		</h2>

		<input type="text" name="account" placeholder="請輸入電子郵件"
			value="${errorMsgs.errorAccount}" />

		<h2>
			密碼<font color=red> ${errorMsgs.passowrd}</font>
		</h2>
		<input type="password" name="passowrd" placeholder="請輸入密碼" />
		<div class="btns">

			<input type="hidden" name="action" value="forLogin">
			<button id="memberlogin" class="btn1" style="vertical-align: middle"
				type="submit">
				<span>會員登入</span>
			</button>
			<div>
				<div id="wrapper">
					<!-- 會員註冊視窗 -->
					<div id="popup" class="opoups_height_width">帳號註冊</div>
					<!-- 忘記密碼視窗 -->
					<div id="popup2" class="opoups_height_width">忘記密碼</div>
					<div id="popup3" class="opoups_height_width">訪客進入</div>
				</div>
			</div>
		</div>
	</form>

	<!------------------------------ 註冊視窗 ------------------------------>
	<div id="box" style="height: 500px;">
		<h2>
			帳號
			<div id="viewAccount" style="display: inline; color: red;"></div>
		</h2>

		<input type="text" name="accountRegister" id="accountRegister" placeholder="請輸入電子郵件" /> <input
			type="button" value="帳號驗證" onclick="checkAccount()" />

		<h2>
			密碼
			<div id="viewpassword" style="display: inline; color: red;"></div>
		</h2>

		<input type="password" name="passwordRegister" id="passwordRegister" placeholder="至少八個字符，至少一個字母和一個數字" value="" />

		<h2>
			再次確認密碼
			<div id="viewcheckpassword" style="display: inline; color: red;"></div>
		</h2>
		<input type="password" name="checkpasswordRegister"
			id="checkpasswordRegister" placeholder="至少八個字符，至少一個字母和一個數字" />

		<h2>
			輸入驗證碼
			<div id="verificationCode" style="display: inline; color: red;"></div>
		</h2>
		<input type="text" name="verificationCode" id="verificationCode" /> <input
			type="button" value="帳號註冊" onclick="registerVerification()" /> <span id="closeBtn">關閉</span>


	</div>


	<!------------------------------ 忘記密碼視窗視窗 ------------------------------>
	<div id="box2">
		<form action="">
			<h2>帳號</h2>
			<input type="text" placeholder="請輸入電子郵件" />
		</form>
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



</body>

</html>