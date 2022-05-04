
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
	$("#popup2").click(function() {
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

	let dataJSON = {
		registerAccount: $("#registerAccount").val(),
		action: "checkAccount"
	}
	$.ajax(
		{
			url: "member.do", // 請求的url地址，相對位址
			type: "post", // 請求的方式，通常用 POST
			data: dataJSON,
			success: function(data) {
				console.log(data);
			},
		}
	);
}


//// 帳號註冊
//function registerVerification() {
//
//	let re = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/gi;
//	// 密碼
//	let passwordRegister = document.getElementById("passwordRegister");
//	let ps1 = passwordRegister.value;
//	// 確認密碼
//	let checkpasswordRegister = document.getElementById("checkpasswordRegister");
//	let ps2 = checkpasswordRegister.value;
//
//	// 驗證碼
//	let verification = document.getElementById("verificationCode").innerHTML;
//
//	if (ps1 !== null && ps1.length !== 0) {
//		if (re.test(ps1)) {
//			document.getElementById("viewpassword").innerHTML = "✔";
//			if (ps1 === ps2) {
//				document.getElementById("viewcheckpassword").innerHTML = "✔";
//				console.log(verification);
//				if (verification !== null && verification.length !== 0) {
//					document.getElementById("verificationCode").innerHTML = "判斷驗證碼中，請稍後";
//					// 呼叫 servlet
//					CreateAccount();
//
//				} else {
//					document.getElementById("verificationCode").innerHTML = "請確認驗證碼";
//					return;
//				}
//			} else {
//				document.getElementById("viewcheckpassword").innerHTML = "兩次輸入的密碼不一致";
//				return;
//			}
//		} else {
//			document.getElementById("viewpassword").innerHTML = "密碼格式不正確";
//			document.getElementById("viewcheckpassword").innerHTML = "";
//			return;
//		}
//	} else {
//		document.getElementById("viewpassword").innerHTML = "請輸入密碼";
//		return;
//	}
//
//}

//function CreateAccount() {
//	let xhr = null;
//	function createXHR() {
//		if (window.XMLHttpRequest) {
//			xhr = new XMLHttpRequest();
//		} else if (window.ActiveXObject) {
//			xhr = new ActiveXObject("Microsoft.XMLHTTP");
//		}
//		return xhr;
//	}
//	createXHR();
//
//	xhr.onload = (e) => {
//		if (xhr.status == 200) {
//			//	document.getElementById("viewAccount").innerHTML = xhr.responseText;
////			let errorJson = xhr.responseText;
////			showError(errorJson);
//		} else {
//			alert(xhr.status);
//		}
//	};
//
//	xhr.open("post", "/CGA101G2/front/member.do");
//	xhr.setRequestHeader(
//		"content-type",
//		"application/x-www-form-urlencoded"
//	);
//	
//	
//	
//	let datasInfo = "passwordRegister=" + document.getElementById("passwordRegister").value&"verificationCode="+document.getElementById("verificationCode").value;
//	console.log(datasInfo);
//	xhr.send(datasInfo);
//}




