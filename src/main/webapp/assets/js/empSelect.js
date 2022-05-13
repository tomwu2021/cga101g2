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

// ------------------ 顯示全部 ------------------
$(function() {

	let dataJSON = {
		action: "empSelect"
	}

	$.ajax(
		{
			url: "/CGA101G2/front/emp.do",
			type: "post",
			data: dataJSON,
			success: function(json) {
				let objectJSON = JSON.parse(json);

				let html = '';
				html += "<div class='main' >";
				html += "<table width='100%'; style='table-layout:fixed' >";
				html += "<tr>";
				html += "<th>會員帳號</th>";
				html += "<th>會員姓名</th>";
				html += "<th width=30%>會員地址</th>";
				html += "<th>會員手機</th>";
				html += "<th>會員等級</th>";
				html += "<th>會員紅利</th>";
				html += "<th>會員狀態</th>";
				html += "<th>創建日期</th>";
				html += "<th>修改</th>";
				html += "</tr>";

				for (let i = 0; i < objectJSON.length; i++) {
					let memberVO = objectJSON[i];
					html += "<tr>";
					html += "<td>" + removeGmail(memberVO.account) + "</td>";
					html += "<td>" + removeGmail(memberVO.name) + "</td>";
					html += removeUndefined(memberVO.address);
					html += removeUndefined(memberVO.phone);
					html += "<td>" + viewRanks(memberVO.rankId) + "</td>";
					html += "<td>" + memberVO.bonusAmount + "</td>";
					html += viewStatus(memberVO.status);
					html += "<td>" + memberVO.createTimeString + "</td>";
					html += "<td><button onclick='updateInfo'>修改</button></td>";
					html += "</tr>";
				}

				html += "</table>";

				document.getElementById("show").innerHTML = html;
			},
		}
	);

});

// ------------------ 查詢 ------------------
