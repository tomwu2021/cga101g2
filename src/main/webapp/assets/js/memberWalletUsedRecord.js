$(function() {

	let dataJSON = {
		action: "memberWalletUsedRecord"
	}

	$.ajax(
		{
			url: "/CGA101G2/front/member.do",
			type: "post",
			data: dataJSON,
			success: function(json) {
				let objectJSON = JSON.parse(json);

				let html = '';
				html += "<table class='table table-striped table-hover card-text'>";
				html += "<thead>";
				html += "<tr class='text-center'>";
				html += "<th style='width: 20%;'>日期</th>";
				html += "<th>消費/儲值</th>";
				html += "</tr>";
				html += "</thead>";

				html += "<tbody>";
				//				html += "<tr class='text-center'>";
				//				html += "<td>2022-04-09</td>";
				//				html += "<td>1 點</td>";
				//				html += "</tr>";

				for (let i = 0; i < objectJSON.length; i++) {

					html += "<tr class='text-center'>";
					html += "<td>" + objectJSON[i].recordTime + "</td>"
					html += "<td>" + objectJSON[i].chargeAmount + "</td>"
					html += "</tr>"

				}


				html += "</tbody>";

				html += "</table>";


				document.getElementById("show").innerHTML = html;

			},
		}
	);
});