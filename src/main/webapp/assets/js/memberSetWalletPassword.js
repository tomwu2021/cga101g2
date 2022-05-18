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
				
				if(exist === "true"){
					function historyZero() {
						window.location.href = 'memberSetWalletPassword.jsp';
					}
					setTimeout(historyZero, 1500);
					window.location.href='memberWalletPassword.jsp'; 
				}else{
					return;
				}

			},
		}
	);
});