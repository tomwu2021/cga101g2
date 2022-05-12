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
				html += "<div class='main'>";
				html += "<table width='100%'; style='table-layout:fixed' >";
				html += "<tr>";
				html += "<th>會員帳號</th>";
				html += "<th>會員姓名</th>";
				html += "<th>會員地址</th>";
				html += "<th style='width:90px'>會員手機</th>";
				html += "<th style='width:80px'>會員等級</th>";
				html += "<th style='width:80px'>會員紅利</th>";
				html += "<th style='width:80px'>會員狀態</th>";
				html += "<th style='width:100px'>會員建立日期</th>";
				html += "<th>修改</th>";
				html += "</tr>";

				for (let i = 0; i < objectJSON.length; i++) {
					let memberVO = objectJSON[i];
					html += "<tr>";
					html += "<td>"+memberVO.account+"</td>";
					html += "<td>"+memberVO.name+"</td>";
					html += "<td>"+memberVO.address+"</td>";
					html += "<td>"+memberVO.phone+"</td>";
					html += "<td>"+memberVO.rankId+"</td>";
					html += "<td>"+memberVO.bonusAmount+"</td>";
					html += "<td>"+memberVO.status+"</td>";
					html += "<td>"+memberVO.createTimeString+"</td>";
					html += "<td><button onclick='updateInfo'>修改</button></td>";
					html += "</tr>";
				}



				html += "</table>";




				document.getElementById("show").innerHTML = html;
			},
		}
	);

});
