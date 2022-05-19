let objectJSON = '';

// 會員帳號和姓名消除 @後面的英文字
function removeGmail(account) {
	let rg = account.indexOf('@');
	if (rg == -1) {
		return account;
	} else {
		let newAccount = account.slice(0, rg);
		return newAccount;
	}
}

// 會員等級 顯示 1(一般會員) 2(黃金會員) 3(白金會員) 4(鑽石會員)
function viewRanks(rank) {

	let rankStr = String(rank);
	switch (rankStr) {
		case "1":
			return '一般會員';
		case "2":
			return '黃金會員';
		case "3":
			return '白金會員';
		case "4":
			return '鑽石會員';
	}
}

// undefined 改成 尚無資訊
function removeUndefined(str) {
	if (typeof (str) === "undefined") {
		return "<td style='color:red'>" + "尚無資料" + "</td>";
	} else {
		return "<td >" + str + "</td>";
	}
}

// 狀態  0(停權) 1(正常)
function viewStatus(status) {
	if (status === 1) {
		return "<td>" + "正常" + "</td>";
	} else {
		return "<td style='color:red'>" + "停權" + "</td>";
	}

}

// 狀態  0(停權) 1(正常)
function viewStatusNo(status) {
	if (status === 1) {
		return "正常";
	} else {
		return "停權";
	}

}

// 顯示正常畫面
function viewBody(objectJSON) {

	let html = '';
	html += "<div class='table-responsive'>";
	html += "<table class='table table-striped table-bordered table-hover' >";
	html += "<tr>";
	html += "<th style='display: none;'>會員編號</th>"; // 隱藏的 MemberId
	html += "<th style='width:165px'>會員帳號</th>";
	html += "<th style='width:165px'>會員姓名</th>";
	html += "<th style='width:200px'>會員地址</th>";
	html += "<th style='width:120px'>會員手機</th>";
	html += "<th style='width:120px'>會員等級</th>";
	html += "<th>會員錢包</th>";
	html += "<th>會員狀態</th>";
	html += "<th  style='width:120px'>創建日期</th>";
	html += "<th style='width:80px'>修改</th>";
	html += "</tr>";

	for (let i = 0; i < objectJSON.length; i++) {
		let memberVO = objectJSON[i];
		html += "<tr>";
		html += "<td style='display: none;'>" + memberVO.memberId + "</td>"; // 隱藏的 MemberId
		html += "<td>" + removeGmail(memberVO.account) + "</td>";
		html += "<td>" + removeGmail(memberVO.name) + "</td>";
		html += removeUndefined(memberVO.address);
		html += removeUndefined(memberVO.phone);
		html += "<td>" + viewRanks(memberVO.rankId) + "</td>";
		html += "<td class = 'viewhover' onclick='viewRecord(this)' id='" + memberVO.account + "'>" + memberVO.eWalletAmount + "</td>";
		html += viewStatus(memberVO.status);
		html += "<td>" + memberVO.createTimeString + "</td>";
		html += "<td class='viewhover'><button onclick='updateInfo(this)' id='" + memberVO.memberId + "' >修改</button></td>";
		html += "</tr>";
	}
	html += "</table>";
	html += "</div>";

	return html;
}


// ------------------ 顯示全部 ------------------
$(function() {

	let dataJSON = {
		action: "empSelect"
	}

	$.ajax(
		{
			url: getContextPath()+"/back/emp.do",
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


// ------------------ 修改 ------------------
function updateInfo(obj) { // obj.id：會員編號
	let html = viewUpdate(objectJSON, obj.id);
	document.getElementById("show").innerHTML = html;
}

// ------------------ 顯示修改畫面 ------------------
function viewUpdate(objectJSON, objMemberId) {
	//	console.log(objectJSON);
	let html = '';
	html += "<div class='table-responsive'>";
	html += "<table class='table table-striped table-bordered table-hover' >";
	html += "<tr>";
	html += "<th style='display: none;'>會員編號</th>"; // 隱藏的 MemberId
	html += "<th style='width:165px'>會員帳號</th>";
	html += "<th style='width:165px'>會員姓名</th>";
	html += "<th style='width:200px'>會員地址</th>";
	html += "<th style='width:120px'>會員手機</th>";
	html += "<th style='width:120px'>會員等級</th>";
	html += "<th>會員錢包</th>";
	html += "<th>會員狀態</th>";
	html += "<th  style='width:120px'>創建日期</th>";
	html += "<th style='width:80px'>修改</th>";
	html += "</tr>";

	for (let i = 0; i < objectJSON.length; i++) {
		let memberVO = objectJSON[i];
		html += "<tr>";
		html += "<td style='display: none;'>" + memberVO.memberId + "</td>"; // 隱藏的 MemberId
		html += "<td>" + removeGmail(memberVO.account) + "</td>";
		html += "<td>" + removeGmail(memberVO.name) + "</td>";
		html += removeUndefined(memberVO.address);
		html += removeUndefined(memberVO.phone);
		html += "<td>" + viewRanks(memberVO.rankId) + "</td>";
html += "<td class = 'viewhover' onclick='viewRecord(this)' id='" + memberVO.account + "'>" + memberVO.eWalletAmount + "</td>";
		if (objMemberId == memberVO.memberId) {
			html += "<td><select id='changeSelect'><option>" + viewStatusNo(memberVO.status) + "</option><option>停權</option><option>正常</option></select></td>";
			html += "<td>" + memberVO.createTimeString + "</td>";
			html += "<td><button onclick='sureInfo(this)' id='" + memberVO.memberId + "'>確定</button></td>";
		} else {
			html += viewStatus(memberVO.status);
			html += "<td>" + memberVO.createTimeString + "</td>";
			html += "<td><button id='" + memberVO.memberId + "'>修改</button></td>";
		}


		html += "</tr>";
	}

	html += "</table>";
	html += "</div>";
	return html;
}

//// ------------------ 按下確定取得 select 值 ------------------
function sureInfo(obj) { // obj.id：會員編號
	let status = $("#changeSelect").val();
	let newStatus = '';
	//	console.log(status);
	if (status === '停權') {
		newStatus = '0';
		//		console.log(newStatus);
	} else {
		newStatus = '1';
		//		console.log(newStatus);
	}
	// 取得 select 值，呼叫 Servelt 修改資料庫值，並重新撈取資料
	let dataJSON = {
		action: "empChangeStatus",
		newStatus: newStatus,
		memberId: obj.id,
	}
	$.ajax(
		{
			url: getContextPath() + "/back/emp.do",
			type: "post",
			data: dataJSON,
			success: function(json) {
				objectJSON = JSON.parse(json);
				let html = viewBody(objectJSON);
				document.getElementById("show").innerHTML = html;
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: "修改會員權限成功",
					showConfirmButton: false,
					timer: 1800

				})
			},
		}
	);

}


// ------------------ 帳號查詢 ------------------
function accountSelect() {
	let memberAccount = $("#account").val();
	console.log(memberAccount);
	let dataJSON = {
		action: "accountSelect",
		memberAccount: memberAccount,
	}

	$.ajax(
		{
			url: getContextPath() + "/back/emp.do",
			type: "post",
			data: dataJSON,
			success: function(json) {
				objectJSON = JSON.parse(json);
				let html = viewBody(objectJSON);
				document.getElementById("show").innerHTML = html;
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: "帳號查詢成功",
					showConfirmButton: false,
					timer: 1800

				})
			},
		}
	);
}

// ------------------ 姓名查詢 ------------------

function nameSelect() {
	let memberName = $("#name").val();
	console.log(memberName);
	let dataJSON = {
		action: "nameSelect",
		memberName: memberName,
	}

	$.ajax(
		{
			url: getContextPath() + "/back/emp.do",
			type: "post",
			data: dataJSON,
			success: function(json) {
				objectJSON = JSON.parse(json);
				let html = viewBody(objectJSON);
				document.getElementById("show").innerHTML = html;
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: "姓名查詢成功",
					showConfirmButton: false,
					timer: 1800

				})
			},
		}
	);
}

// 顯示所有資料
function selectAll() {
	let dataJSON = {
		action: "empSelect"
	}

	$.ajax(
		{
			url: getContextPath() + "/back/emp.do",
			type: "post",
			data: dataJSON,
			success: function(json) {
				objectJSON = JSON.parse(json);
				let html = viewBody(objectJSON);
				document.getElementById("show").innerHTML = html;
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: "查詢成功",
					showConfirmButton: false,
					timer: 1800

				})
			},
		}
	);
}


// 顯示儲值消費紀錄 ---------------- 
function viewRecord(obj) {
	let dataJSON = {
		action: "empSelectMemberRecord",
		memberAccount: obj.id,
	}
	console.log(obj.id); // 信箱
	$.ajax(
		{
			url: getContextPath() + "/back/emp.do",
			type: "post",
			data: dataJSON,
			success: function(json) {
				objectJSON = JSON.parse(json);
				let html = '';
				html += "<div class='table-responsive'>";
				html += "<table class='table table-striped table-bordered table-hover'>";
				html += "<thead>";
				html += "<tr class='text-center'>";
				html += "<th>交易編號</th>";
				html += "<th>姓名</th>";
				html += "<th>消費/儲值</th>";
				html += "<th>日期</th>";
				html += "</tr>";
				html += "</thead>";
				html += "<tbody>";
				for (let i = 0; i < objectJSON.length; i++) {
					html += "<tr class='text-center'>";
					html += "<td>" + objectJSON[i].recordId + "</td>";
					html += "<td class='getName'>" + getNameByMemberId(objectJSON[i].memberId) + "</td>";
					html += firstChar(objectJSON[i].chargeAmount);
					html += "<td>" + objectJSON[i].recordTimeString + "</td>";
					html += "</tr>"
				}
				html += "</tbody>";
				html += "</table>";
				html += "</div>";
				document.getElementById("show").innerHTML = html;
			},
		}
	);
}



function firstChar(str) {
	let number = parseInt(str);
	if (number < 0) {
		return "<td style='color:red;'>" + str + "</td>"
	} else {
		return "<td>" + str + "</td>"
	}
}


function getNameByMemberId(memberId) {
	let memberName = '';

	let dataJSON = {
		action: "getNameByMemberId",
		memberId: memberId,
	}

	$.ajax(
		{
			url: getContextPath() + "/back/emp.do",
			type: "post",
			data: dataJSON,
			success: function(json) {

				responseName = JSON.parse(json);
				const nodeList = document.querySelectorAll(".getName");
				for (let i = 0; i < nodeList.length; i++) {
					nodeList[i].innerHTML = responseName.memberName;
					memberName = responseName.memberName;
				}

			},
		}
	);
	Swal.fire({
		position: 'center',
		icon: 'success',
		title: "查詢 " + memberName + " 錢包紀錄",
		showConfirmButton: false,
		timer: 1800

	})
}

