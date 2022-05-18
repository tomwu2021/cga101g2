function selectNotification() {

	let dataJSON = {
		action: "selectNotification",
		memberId: getLoginId()
	}
	$.ajax(
		{
			url: getContextPath() + "/front/notification.do", // 請求的url地址，相對位址
			type: "post", // 請求的方式，通常用 POST
			data: dataJSON,
			success: function(json) {
				let objectJSON = JSON.parse(json);

				let html = '';
				for (let i = 0; i < objectJSON.length; i++) {
					//通知 時間 狀態

					html += "<li><a href='#'><i class='bi bi-envelope-fill red'></i>&emsp;" + objectJSON[i].context + "--" + objectJSON[i].time + "--" + objectJSON[i].status + "</a></li>";
				}
				document.getElementById("viewNotification").innerHTML = html;
			},
		}
	);
}