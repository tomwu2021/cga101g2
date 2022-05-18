amountCount();

$("#address").click(function() {

	if ($("#address").is(':checked')) {
		$("#mname").attr("style", "display:none");
		$("#mpho").attr("style", "display:none");
		$("#made").attr("style", "display:none");


	} else {
		$("#mname").removeAttr("style");
		$("#mpho").removeAttr("style");
		$("#made").removeAttr("style");
	}
});

//地址預設
$("#zipcode3").twzipcode({
	"zipcodeIntoDistrict": true,
	"css": ["city form-control", "town form-control"],
	countySel: "臺北市", // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
	districtSel: "大安區", // 地區預設值
});

function amountCount() {
	var count = $("#count").val();
	$("#amount").text(count);
	var payPrice = (Number($("#amount").text()) * Number($("#price").text()));
	$("#payPrice").text(payPrice);
	$("#total").text(payPrice);
}

function checking() {
	let check = /^0\d{9}$/;
	if ($("#address").is(':checked')) {
		//有選
		document.getElementById('CHECKOUT').submit()

	} else {
		//沒選
		let name = $("#memberName").val();
		let phone = $("#memberPhone").val();
		let address = $("#exampleFormControlInput1").val();
		if (name == null || name.length == 0) {
			console.log("沒名字");
			Swal.fire({
				position: 'center',
				icon: 'error',
				title: "請輸入姓名",
				showConfirmButton: false,
				timer: 1500

			})
			return;
		} else if (!check.test(phone)) {
			console.log("沒電話");
			Swal.fire({
				position: 'center',
				icon: 'error',
				title: "請輸入正確電話",
				showConfirmButton: false,
				timer: 1500

			})
			return;
		} else if (address == null || address.length == 0) {
			console.log("沒地址");
			Swal.fire({
				position: 'center',
				icon: 'error',
				title: "請輸入地址",
				showConfirmButton: false,
				timer: 1500

			})
			return;
		} else {
			document.getElementById('CHECKOUT').submit()
		}



	}
}


//驗證密碼
function check() {

	let password = $("#password").val();
	let action = "CHECK";
	let data = {
		password: password,
		action: action
	}

	JSON.stringify(data);
	$.ajax({
		url: getContextPath() + "/member/cart.do",
		type: "post",
		data: data,
		success: function(result, status) {
			if (result == "錯") {
				console.log("錯誤")
				Swal.fire({
					position: 'center',
					icon: 'error',
					title: "驗證失敗",
					showConfirmButton: false,
					timer: 1500
				})
			} else {
				console.log("對");
				$("#password").attr("disabled", "disabled");
				$("#password").attr("style", "background:#CCCCCC");
				$("#submit").removeAttr("style");
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: "驗證成功",
					showConfirmButton: false,
					timer: 1500

				})
				$("#submit").removeAttr("style");
			}
		}
	})


}


