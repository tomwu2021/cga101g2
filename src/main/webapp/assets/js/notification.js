

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
					html += "<li  onclick='changeStatus(this)' id='" + objectJSON[i].notificationId + "'><a href='" + objectJSON[i].url + "'><i class='bi bi-envelope-fill red'></i>&emsp;" + viewStatus(objectJSON[i].status) + "　" + objectJSON[i].context + "  " + viewDate(objectJSON[i].time) + "</a></li>";
				}
				document.getElementById("viewNotification").innerHTML = html;
			},
		}
	);
}

// 年-月-日
function viewDate(date) {	//	"May 10, 2022, 2:29:42 AM"
	let someday = new Date(date);
	let dateFormate = someday.getFullYear() + "-" + (someday.getMonth() + 1) + "-" + someday.getDate();
	return dateFormate;
}


// 0未讀 1已讀
function viewStatus(date) {
	if (date === 0) {
		return '未讀';
	} else {
		return '已讀';
	}
}

function changeStatus(obj) {
	console.log(obj.id);
	let dataJSON = {
		action: "changeSataus",
		status: obj.id,
		memberId: getLoginId()
	}

	$.ajax(
		{
			url: getContextPath() + "/front/notification.do",
			type: "post",
			data: dataJSON,
			success: function(json) {
				let objectJSON = JSON.parse(json);
				let html = '';
				for (let i = 0; i < objectJSON.length; i++) {
					//通知 時間 狀態
					html += "<li  onclick='changeStatus(this)' id='" + objectJSON[i].notificationId + "'><a href='" + objectJSON[i].url + "'><i class='bi bi-envelope-fill red'></i>&emsp;" + viewStatus(objectJSON[i].status) + "　" + objectJSON[i].context + "  " + viewDate(objectJSON[i].time) + "</a></li>";
				}
				document.getElementById("viewNotification").innerHTML = html;
			},
		}
	);
}
