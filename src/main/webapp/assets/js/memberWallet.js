$(function() {

	let dataJSON = {
		action: "checkWalletPassword"
	}

	$.ajax(
		{
			url: "/CGA101G2/front/member.do",
			type: "post",
			data: dataJSON,
			success: function(json) {
				let objectJSON = JSON.parse(json);

				let exist = objectJSON.exist;
				console.log(exist);

				if (exist === "false") {
					Swal.fire({
						position: 'center',
						icon: 'error',
						title: "請設定錢包密碼",
						showConfirmButton: true,
						timer: 2500

					})
					window.location.href = 'memberSetWalletPassword.jsp';
				} else {
					return;
				}

			},
		}
	);
});