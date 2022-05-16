let objectJSON = '';

// ------------------ 顯示全部 ------------------
$(function() {

	let dataJSON = {
		action: "orderList"
	}

	$.ajax(
		{
			url: "/CGA101G2/front/xxx",
			type: "post",
			data: dataJSON,
			success: function(json) {
				objectJSON = JSON.parse(json);
				let html = viewBody(objectJSON);
				document.getElementById("show").innerHTML = html;
			},
		}
	);

});

// ------------------ 正常顯示 ------------------
function viewBody(objectJSON) {
	let html = '';
	html += "<div class='main' >";
	html += "<table>";
	html += "<tr>";
	html += "<th>訂單編號</th>";
	html += "<th>價格</th>";
	html += "<th>狀態</th>";
	html += "<th>購買時間</th>";
	html += "<th>查看詳情</th>";
	html += "</tr>";

	for (let i = 0; i < objectJSON.length; i++) {
		let xxxVO = objectJSON[i];
		html += "<tr>";
		html += "<td>" + xxxVO.訂單編號 + "</td>";
		html += "<td>" + xxxVO.價格 + "</td>";
		html += "<td>" + xxxVO.狀態 + "</td>";
		html += "<td>" + xxxVO.購買時間 + "</td>";
		html += "<td><td><button onclick='viewDetail(this)' id='" + xxx.memberId + "'>View</button></td>";
		html += "</tr>";
	}

	html += "</table>";

	return html;
}


// 狀態  0(未出貨) 1(出貨)
function viewStatus(status) {
	if (status === 0) {
		return "未出貨";
	} else {
		return "已出貨";
	}
}

// viewDetial，訂單詳情
function viewDetail(obj){
	let id = obj.id; //按下 button 按鈕的 id
}