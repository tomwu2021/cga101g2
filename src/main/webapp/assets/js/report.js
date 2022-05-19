let objectJSON = '';

// 0:未處理  1:已處理
function viewReportIdStatus(data) {
	if (data === 0) {
		return "未處理";
	} else {
		return "已處理";
	}
}


// 0:正常 1:待審核 2:刪除
function viewStatus(data) {
	if (data === 0) {
		return "正常";
	} else if (data === 1) {
		return "待審核";
	} else {
		return "刪除";
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

				//				console.log(objectJSON);
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
		html += "<td>" + viewReportIdStatus(report.status) + "</td>";
		html += "<td><a href='" + getContextPath() + report.url + "'>" + "View" + "</a></td>";
		html += "<td class='viewhover'><button onclick='updateInfo(this)' id='" + report.postId + "-" + report.status + "-" + report.reportId + "' >修改</button></td>";
		html += "</tr>";
	}
	html += "</table>";
	html += "</div>";

	return html;
}

// ------------------ 修改 ------------------
function updateInfo(obj) {
	//	console.log(obj.id);
	//	console.log('111');
	let html = viewUpdate(objectJSON, obj.id);
	document.getElementById("show").innerHTML = html;

}


// ------------------ 修改畫面 ------------------
function viewUpdate(objectJSON, obj) {
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
	html += "<th>確定</th>";
	html += "</tr>";

	for (let i = 0; i < objectJSON.length; i++) {
		let report = objectJSON[i];
		html += "<tr>";
		console.log(report.postId + "-" + report.status + "-" + report.reportId);
		console.log(obj);
		if ((report.postId + "-" + report.status + "-" + report.reportId) === obj) {
			html += "<td>" + report.reportId + "</td>";
			html += "<td>" + report.postId + "</td>";
			html += "<td>" + report.reportReason + "</td>";
			html += "<td>" + report.reportTime + "</td>";
			//			html += "<td>" + viewStatus(report.status) + "</td>";  // // reportId 0:未處理 1:已處理  0:正常 1:待審核 2:刪除
			html += "<td><select id='changeSelect'><option>" +viewReportIdStatus(report.status)  + "<option>正常</option><option>待審核</option><option>刪除</option></option></select></td>";
			html += "<td><a href='" + getContextPath() + report.url + "'>" + "View" + "</a></td>";
			html += "<td class='viewhover'><button onclick='updateSure(this)' id='" + report.postId + "-" + report.status + "-" + report.reportId + "' >確定</button></td>";
		} else {
			html += "<td>" + report.reportId + "</td>";
			html += "<td>" + report.postId + "</td>";
			html += "<td>" + report.reportReason + "</td>";
			html += "<td>" + report.reportTime + "</td>";
			html += "<td>" + viewStatus(report.status) + "</td>";
			html += "<td><a href='" + getContextPath() + report.url + "'>" + "View" + "</a></td>";
			html += "<td class='viewhover'><button  id='" + report.postId + "-" + report.status + "-" + report.reportId + "' >修改</button></td>";
		}
		html += "</tr>";
	}
	html += "</table>";
	html += "</div>";

	return html;
}

function updateSure(obj) {

	let postStatus = $("#changeSelect").val();
	//	console.log($("changeSelect").val());
	//	console.log(obj.id);	// postId status reportId
	//	console.log((obj.id).slice(0, 2)); // postId
	//	console.log((obj.id).slice(3, 4)); // status
	//	console.log((obj.id).slice(5, 8)); // reportId

	let postId = (obj.id).slice(0, 2);
//	let postStatus = (obj.id).slice(3, 4);
	let reportId = (obj.id).slice(5, 8);

	// 解析 狀態 塞回 db  ， 0:正常 1:待審核 2:刪除
	if ($("#changeSelect").val() === '正常') {
		postStatus = 0;
	} else if ($("#changeSelect").val() === '待審核') {
		postStatus = 1;
	} else if ($("#changeSelect").val() === '刪除') {
		postStatus = 2;
	}

	let dataJSON = {
		postId: postId,
		status: postStatus,
		reportId: reportId,
		action: "updateStatus",
	}

	$.ajax(
		{
			url: getContextPath() + "/backReport.do",
			type: "post",
			data: dataJSON,
			success: function(json) {
				console.log('111');
				objectJSON = JSON.parse(json);
				let html = viewBody(objectJSON);
				document.getElementById("show").innerHTML = html;
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: "修改成功",
					showConfirmButton: false,
					timer: 1500

				});
			},
		}
	);
}
