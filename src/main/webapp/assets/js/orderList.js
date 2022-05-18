let objectJSON = '';


// 狀態  0(未出貨) 1(出貨)
function viewStatus(status) {
	if (status === 0) {
		return "未出貨";
	} else if (status === 1) {
		return "已取消";
	} else {
		return "已完成";
	}
}
	
// 年-月-日
function viewDate(date) {	//	"May 10, 2022, 2:29:42 AM"
	let someday = new Date(date);
	let dateFormate = someday.getFullYear() + "-" + (someday.getMonth() + 1) + "-" + someday.getDate();
	return dateFormate;
}

// ------------------ 顯示全部 ------------------
$(function() {

	let dataJSON = {
		action: "orderList"
	}

	$.ajax(
		{
			url: getContextPath() + "/AllOrders",
			type: "post",
			data: dataJSON,
			success: function(json) {
				objectJSON = json;
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
	html += "<th>訂單編號</th>";
	html += "<th>會員編號</th>";
	html += "<th>訂單金額</th>";
	html += "<th>訂單狀態</th>";
	html += "<th>訂單詳情</th>";
	html += "<th>成立時間</th>";
	html += "<th>修改</th>";
	html += "</tr>";

	for (let i = 0; i < objectJSON.length; i++) {
		let order = objectJSON[i];
		html += "<tr>";
		html += "<td>" + order.orderId + "</td>";
		html += "<td>" + order.memberId + "</td>";
		html += "<td>" + order.payPrice + "</td>";
		html += "<td>" + viewStatus(order.status) + "</td>";
		html += "<td><a href=" + getContextPath() + "/member/order.do?memberId=" + order.memberId + "&orderId=" + order.orderId
			+ "&action=orderDeatil" + ">View</td>";
		html += "<td>" + viewDate(order.createTime) + "</td>";
		html += "<td class='viewhover'><button onclick='updateInfo(this)' id='" + order.orderId + "' >修改</button></td>";
		html += "</tr>";
	}
	html += "</table>";
	html += "</div>";

	return html;
}

// ------------------ 修改 ------------------
function updateInfo(obj) {
	console.log(obj.id);
	let html = viewUpdate(objectJSON, obj.id);
	document.getElementById("show").innerHTML = html;
}

// ------------------ 顯示修改畫面 ------------------
function viewUpdate(objectJSON, thisOrderId) {
	let html = '';
	html += "<div class='table-responsive'>";
	html += "<table class='table table-striped table-bordered table-hover' >";
	html += "<tr>";
	html += "<th>訂單編號</th>";
	html += "<th>會員編號</th>";
	html += "<th>訂單金額</th>";
	html += "<th>訂單狀態</th>";
	html += "<th>訂單詳情</th>";
	html += "<th>成立時間</th>";
	html += "<th>修改</th>";
	html += "</tr>";

	for (let i = 0; i < objectJSON.length; i++) {
		let order = objectJSON[i];
		html += "<tr>";
		html += "<td>" + order.orderId + "</td>";
		html += "<td>" + order.memberId + "</td>";
		html += "<td>" + order.payPrice + "</td>";
		if (thisOrderId == order.orderId) {
			html += "<td><select id='changeStatus'><option>" + viewStatus(order.status) + "</option><option>未出貨</option><option>已取消</option><option>已完成</option></select></td>";
			html += "<td><a href=" + getContextPath() + "/member/order.do?memberId=" + order.memberId + "&orderId=" + order.orderId
				+ "&action=orderDeatil" + ">View</td>";
			html += "<td>" + viewDate(order.createTime) + "</td>";
			html += "<td class='viewhover'><button onclick='sureInfo(this)' id='" + order.orderId + "' >確定</button></td>";
		} else {
			html += "<td>" + viewStatus(order.status) + "</td>";
			html += "<td><a href=" + getContextPath() + "/member/order.do?memberId=" + order.memberId + "&orderId=" + order.orderId
				+ "&action=orderDeatil" + ">View</td>";
			html += "<td>" + viewDate(order.createTime) + "</td>";
			html += "<td class='viewhover'><button id='" + order.orderId + "' >修改</button></td>";
		}


		html += "</tr>";
	}
	html += "</table>";
	html += "</div>";

	return html;
}

// ------------------ 確定按鈕 ------------------
function sureInfo(obj) { // obj.id：會員編號
	let status = $("#changeStatus").val();
	let newStatus = '';
	if (status === '未出貨') {
		newStatus = '0';
		console.log(newStatus);
	} else if (status === '已取消') {
		newStatus = '1';

		console.log(newStatus);

	} else {
		newStatus = '2';
		console.log(newStatus);
	}
	//	// 取得 changeStatus 值，呼叫 Servelt 修改資料庫值，並重新撈取資料
	let dataJSON = {
		action: "updateOrder",
		status: newStatus,
		orderId: obj.id,
	}
	$.ajax(
		{
			url: getContextPath() + "/AllOrders",
			type: "post",
			data: dataJSON,
			success: function(json) {
				objectJSON = json;
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
