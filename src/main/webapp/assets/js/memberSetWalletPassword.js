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
				
				if(exist === "true"){
					window.location.href='memberWalletPassword.jsp'; 
				}else{
					return;
				}

			},
		}
	);
});