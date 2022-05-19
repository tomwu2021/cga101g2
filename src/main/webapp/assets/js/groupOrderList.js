let objectJSON = '';


// 1:時間到結單  2:數量到結單	
function viewType(status) {
	if (status === 1) {
		return "時間到結單";
	} else {
		return "數量到結單";
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
		action: "groupOrderList"
	}

	$.ajax(
		{
			url: getContextPath() + "/AllOrders", //http://localhost:8081/CGA101G2/AllOrders
			type: "post",
			data: dataJSON,
			success: function(json) {
				//				console.log(json);
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
	html += "<th>團購訂單編號</th>";
	html += "<th>商品編號</th>";
	html += "<th>團購開始時間</th>";
	html += "<th>團購結束時間</th>";
	html += "<th>團購結單方式</th>";
	html += "<th>團購最後價格</th>";
	html += "<th>最低份數</th>";
	html += "</tr>";

	for (let i = 0; i < objectJSON.length; i++) {
		let groupOrder = objectJSON[i];
		html += "<tr>";
		html += "<td class='viewhover' onclick='viewRecord("+groupOrder.groupOrderId+")' id='" + groupOrder.productId + "'>" + groupOrder.groupOrderId + "</td>";
		html += "<td>" + groupOrder.productId + "</td>";
		html += "<td>" + viewDate(groupOrder.createTime) + "</td>";
		html += "<td>" + viewDate(groupOrder.endTime) + "</td>";
		html += "<td>" + viewType(groupOrder.endType) + "</td>";
		html += "<td>" + groupOrder.finalPrice + "</td>";

		if (groupOrder.status === 1) {
			html += "<td>" + "❌" + "</td>";
		} else {
			html += "<td>" + groupOrder.minAmount + "</td>";
		}
		html += "</tr>";
	}
	html += "</table>";
	html += "</div>";

	return html;
}

// ------------------ 點擊團購訂單編號查看詳情 ------------------
function viewRecord(groupOrderId) {
	//	console.log(obj.id);

	let dataJSON = {
		action: "GroupOredrDetail",
		orderId: groupOrderId
	}

	$.ajax(
		{
			url: getContextPath() + "/AllOrders",
			type: "post",
			data: dataJSON,
			success: function(json) {
				//				console.log(json);
				objectJSON = json;
				let html = viewGroupOredrDetial(objectJSON);
				document.getElementById("show").innerHTML = html;
			},
		}
	);
}

// ------------------ 顯示詳情畫面 ------------------
function viewGroupOredrDetial() {
	let html = '';
	html += "<div class='table-responsive'>";
	html += "<table class='table table-striped table-bordered table-hover' >";
	html += "<tr>";
	html += "<th>團購商品數量</th>";
	html += "<th>收件人姓名</th>";
	html += "<th>電話</th>";
	html += "<th>地址</th>";
	html += "</tr>";

	for (let i = 0; i < objectJSON.length; i++) {
		let groupOrderDetial = objectJSON[i];
		html += "<tr>";
		html += "<td>" + groupOrderDetial.productAmount + "</td>";
		html += "<td>" + groupOrderDetial.recipients + "</td>";
		html += "<td>" + groupOrderDetial.phone + "</td>";
		html += "<td>" + groupOrderDetial.address + "</td>";
		html += "</tr>";
	}
	html += "</table>";
	html += "</div>";

	return html;
}

function selectAll() {
	
	let dataJSON = {
		action: "groupOrderList"
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
}