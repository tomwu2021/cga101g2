let rankId = document.getElementById("rankId").innerHTML;

function executeAfterloadedBody() {
	let dataJSON = {
		rankId: rankId,
		action: "getRankName"
	}
	$.ajax(
		{
			url: "member.do", // 請求的url地址，相對位址
			type: "post", // 請求的方式，通常用 POST
			data: dataJSON,
			success: function(json) {
				let objectJSON = JSON.parse(json);
				document.getElementById("rankId").innerHTML = objectJSON.msgRankName + objectJSON.msgDiscount;
			},
		}
	);

}

