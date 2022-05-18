let objectJSON = '';

//0:未處理 1:已處理
function viewStatus(data) {
	if (data === 0) {
		return "未處理";
	} else {
		return "已處理";
	}
}

// ------------------ 顯示全部 ------------------
$(function() {

	let dataJSON = {
		action: "selectAll"
	}

	$.ajax(
		{
			url: getContextPath() + "/backReport.do",
			type: "post",
			data: dataJSON,
			success: function(json) {

				objectJSON = JSON.parse(json);

				console.log(objectJSON);
				let html = viewBody(objectJSON);
				document.getElementById("show").innerHTML = html;

			},
		}
	);

});

// ------------------ 正常顯示 ------------------
function viewBody(objectJSON) {
	let html = '';
	html += "<div class='table-responsive'>";
	html += "<table class='table table-striped table-bordered table-hover' >";
	html += "<tr>";
	html += "<th>檢舉編號</th>";
	html += "<th>貼文編號</th>";
	html += "<th>檢舉理由</th>";
	html += "<th>檢舉時間</th>";
	html += "<th>貼文狀態</th>";
	html += "<th>查看詳情</th>";
	html += "<th>修改</th>";
	html += "</tr>";

	for (let i = 0; i < objectJSON.length; i++) {
		let report = objectJSON[i];
		html += "<tr>";
		html += "<td>" + report.reportId + "</td>";
		html += "<td>" + report.postId + "</td>";
		html += "<td>" + report.reportReason + "</td>";
		html += "<td>" + report.reportTime + "</td>";
		html += "<td>" + viewStatus(report.status) + "</td>";
		html += "<td><a href='" + getContextPath() + report.url + "'>" + "View" + "</a></td>";
		html += "<td class='viewhover'><button onclick='updateInfo(this)' id='" + report.reportId + "' >修改</button></td>";
		html += "</tr>";
	}
	html += "</table>";
	html += "</div>";

	return html;
}

// ------------------ 修改 ------------------
updateInfo
