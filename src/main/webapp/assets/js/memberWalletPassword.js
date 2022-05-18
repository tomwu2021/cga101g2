$(function() {

	let dataJSON = {
		action: "checkWalletPassword"
	}

	$.ajax(
		{
			url: getContextPath() + "/front/member.do",
			type: "post",
			data: dataJSON,
			success: function(json) {
				let objectJSON = JSON.parse(json);

				let exist = objectJSON.exist;
				console.log(exist);

				if (exist === "false") {

					Swal.fire({
						position: 'center',
						icon: 'info',
						title: "請設定錢包密碼",
						showConfirmButton: true,
						timer: 2500
					})

					function historyZero() {
						window.location.href = 'memberSetWalletPassword.jsp';
					}


					setTimeout(historyZero, 1950);

				} else {
					return;
				}

			},
		}
	);
});