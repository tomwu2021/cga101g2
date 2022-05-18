
// 開啟 register 視窗
function registerView() {
	$("#box").fadeIn("fast");  //淡入
	//獲取頁面文件的高度
	var docheight = $(document).height();
	//追加一個層，使背景變灰(此層z-index:100，介於彈出視窗和底層之間)
	$("body").append("<div id='greybackground'></div>");
	$("#greybackground").css({ "opacity": "0.5", "height": docheight });
	//return false;
}

// 會員註冊 --------------
$(function() {
	var screenwidth, screenheight, mytop, getPosLeft, getPosTop
	screenwidth = $(window).width();
	screenheight = $(window).height();
	//獲取滾動條距頂部的偏移
	mytop = $(document).scrollTop();
	//計算彈出層的left
	getPosLeft = screenwidth / 2 - 260;
	//計算彈出層的top
	getPosTop = screenheight / 2 - 300;
	//css定位彈出層
	$("#box").css({ "left": getPosLeft, "top": getPosTop });
	//當瀏覽器視窗大小改變時...
	$(window).resize(function() {  //視窗被調整過時，運行此函數
		screenwidth = $(window).width();
		screenheight = $(window).height();
		mytop = $(document).scrollTop();
		getPosLeft = screenwidth / 2 - 260;
		getPosTop = screenheight / 2 - 150;
		$("#box").css({ "left": getPosLeft, "top": getPosTop + mytop });
	});

	//當拉動滾動條時...
	$(window).scroll(function() {
		screenwidth = $(window).width();
		screenheight = $(window).height();
		mytop = $(document).scrollTop();
		getPosLeft = screenwidth / 2 - 260;
		getPosTop = screenheight / 2 - 150;
		$("#box").css({ "left": getPosLeft, "top": getPosTop + mytop });
	});

	$("#popup").click(function() {
		$("#box").fadeIn("fast");  //淡入
		//獲取頁面文件的高度
		var docheight = $(document).height();
		//追加一個層，使背景變灰(此層z-index:100，介於彈出視窗和底層之間)
		$("body").append("<div id='greybackground'></div>");
		$("#greybackground").css({ "opacity": "0.5", "height": docheight });
		//return false;
	});

	//點選關閉按鈕
	$("#closeBtn").click(function() {
		$("#box").hide();
		//刪除變灰的層
		$("#greybackground").remove();
		return false;
	});

});


// 忘記密碼
$(function() {
	var screenwidth, screenheight, mytop, getPosLeft, getPosTop
	screenwidth = $(window).width();
	screenheight = $(window).height();
	//獲取滾動條距頂部的偏移
	mytop = $(document).scrollTop();
	//計算彈出層的left
	getPosLeft = screenwidth / 2 - 260;
	//計算彈出層的top
	getPosTop = screenheight / 2 - 150;
	//css定位彈出層
	$("#box2").css({ "left": getPosLeft, "top": getPosTop });
	//當瀏覽器視窗大小改變時...
	$(window).resize(function() {  //視窗被調整過時，運行此函數
		screenwidth = $(window).width();
		screenheight = $(window).height();
		mytop = $(document).scrollTop();
		getPosLeft = screenwidth / 2 - 260;
		getPosTop = screenheight / 2 - 150;
		$("#box2").css({ "left": getPosLeft, "top": getPosTop + mytop });
	});

	//當拉動滾動條時...
	$(window).scroll(function() {
		screenwidth = $(window).width();
		screenheight = $(window).height();
		mytop = $(document).scrollTop();
		getPosLeft = screenwidth / 2 - 260;
		getPosTop = screenheight / 2 - 150;
		$("#box2").css({ "left": getPosLeft, "top": getPosTop + mytop });
	});

	//點選連結彈出視窗 會員註冊
	$("#popup22").click(function() {
		$("#box2").fadeIn("fast");  //淡入
		//獲取頁面文件的高度
		var docheight = $(document).height();
		//追加一個層，使背景變灰(此層z-index:100，介於彈出視窗和底層之間)
		$("body").append("<div id='greybackground2'></div>");
		$("#greybackground2").css({ "opacity": "0.5", "height": docheight });
		//return false;
	});

	//點選關閉按鈕
	$("#closeBtn2").click(function() {
		$("#box2").hide();
		//刪除變灰的層
		$("#greybackground2").remove();
		return false;
	});

});


// 傳送資料到 member.do
function checkAccount() {
	//	loading();
	let dataJSON = {
		registerAccount: $("#registerAccount").val(),
		action: "checkAccount"
	}
	$.ajax(
		{
			url: "member.do", // 請求的url地址，相對位址
			type: "post", // 請求的方式，通常用 POST
			data: dataJSON,
			success: function(json) {
				let objectJSON = JSON.parse(json);
				document.getElementById("viewRegisterAccount").innerHTML = objectJSON.exist;
				//				offLoading();
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: "寄送成功",
					showConfirmButton: true,
					timer: 2500

				})
			},
		}
	);
}


//// 帳號註冊
function registerVerification() {

	let rAccount = $("#registerAccount").val();
	let rPassword = $("#registerpassword").val();
	let rCheckPassword = $("#registercheckpasswordr").val();
	let rVerificationCode = $("#verificationCode").val();

	if (rAccount !== null && rAccount.length !== 0) {
		document.getElementById("viewRegisterAccount").innerHTML = "";
	} else {
		document.getElementById("viewRegisterAccount").innerHTML = "請輸入電子郵件";
		return;
	}

	let re = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/gi;

	if (rPassword !== null && rPassword.length !== 0) {

		if (re.test(rPassword)) {
			document.getElementById("viewpassword").innerHTML = "✔";
		} else {
			document.getElementById("viewpassword").innerHTML = "密碼格式不正確";
			return;
		}
	} else {
		document.getElementById("viewpassword").innerHTML = "請輸入密碼";
		return;
	}


	if (rCheckPassword !== null && rCheckPassword.length !== 0) {
		if (rPassword === rCheckPassword) {
			document.getElementById("viewcheckpassword").innerHTML = "✔";
		} else {
			document.getElementById("viewcheckpassword").innerHTML = "與前次輸入不相符";
			return;
		}
	} else {
		document.getElementById("viewcheckpassword").innerHTML = "請輸入密碼";
		return;
	}


	if (rVerificationCode !== null && rVerificationCode.length !== 0) {
		document.getElementById("viewVerificationCode").innerHTML = "";
	} else {
		document.getElementById("viewVerificationCode").innerHTML = "請輸入驗證碼";
		return;
	}




	//	loading();
	let dataJSON = {
		registerAccount: rAccount,
		registerpassword: rPassword,
		registercheckpasswordr: rCheckPassword,
		verificationCode: rVerificationCode,
		action: "registerVerification"
	}
	$.ajax(
		{
			url: "member.do", // 請求的url地址，相對位址
			type: "post", // 請求的方式，通常用 POST
			data: dataJSON,
			success: function(json) {
				let objectJSON = JSON.parse(json);
				document.getElementById("viewRegisterAccount").innerHTML = objectJSON.msgError;
				if (objectJSON.msgError === "") {
					document.getElementById("viewVerificationCode").innerHTML = objectJSON.msgErrorVerificationCode;
				}

				//				offLoading();
				if (objectJSON.msgErrorVerificationCode === "" && objectJSON.msgError === "") {
					history.go(0);
					//					alert(objectJSON.registerSuccessful);
					Swal.fire({
						position: 'center',
						icon: 'success',
						title: "註冊成功",
						showConfirmButton: true,
						timer: 2500

					})

				}
			},
		}
	);

}

// 忘記密碼
function sendforgotMail() {

	let forgotPassword = $("#forgotPassword").val();
	if (forgotPassword !== null && forgotPassword.length !== 0) {
		//		loading();
		let dataJSON = {
			forgotPassword: $("#forgotPassword").val(),
			action: "sendforgotMail"
		}

		$.ajax(
			{
				url: "member.do",
				type: "post",
				data: dataJSON,
				success: function(json) {
					let objectJSON = JSON.parse(json);
					document.getElementById("viewForgotPassword").innerHTML = objectJSON.msgError;
					Swal.fire({
						position: 'center',
						icon: 'success',
						title: "寄送成功",
						showConfirmButton: true,
						timer: 2500

					})
				},
			}
		);
		//		offLoading();
	} else {
		document.getElementById("viewForgotPassword").innerHTML = "請輸入電子郵件";
		return;
	}


}

