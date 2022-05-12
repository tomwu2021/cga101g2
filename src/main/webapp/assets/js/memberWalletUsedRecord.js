

function firstChar(str) {
	let number = parseInt(str);
	//	console.log(number);
	if (number < 0) {
		return "<td style='color:red;'>" + str + "</td>"
	} else {
		return "<td>" + str + "</td>"
	}
}


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
					html += "<td>" + objectJSON[i].recordTime + "</td>";
					//					console.log(objectJSON[i].recordTime);
					//					html += "<td>" + objectJSON[i].chargeAmount + "</td>"
					html += firstChar(objectJSON[i].chargeAmount);
					html += "</tr>"

				}


				html += "</tbody>";

				html += "</table>";


				document.getElementById("show").innerHTML = html;

			},
		}
	);
});


$(function() {
	// 今天日期
	let Today = new Date();
	let month = Today.getMonth() + 1;
	if (month < 10) {
		month = '0' + month;
	}
	let today = Today.getFullYear() + "-" + month + "-" + Today.getDate();

	// second Date Value 今天
	$("#inputDateTwo").val(today);

	// first Date Value 這個月份1號
	let inputDateOneValue = Today.getFullYear() + "-" + month + "-01";
	$("#inputDateOne").val(inputDateOneValue);

	// second Date:今天之後日期不能選擇
	document.getElementById('inputDateTwo').setAttribute('max', today);


});

function choseDate() {
	// first Date：抓到 second Date 日期，在 Second Date 日期前都不能選擇
	let inputDateTwo = $("#inputDateTwo").val();
	document.getElementById('inputDateOne').setAttribute('max', inputDateTwo);

}

function changeDate() {

	let inputDateOne = $("#inputDateOne").val();
	let inputDateTwo = $("#inputDateTwo").val();

	let firstDate = inputDateOne.slice(0, 4) + inputDateOne.slice(5, 7) + inputDateOne.slice(8, 10);
	//	console.log(firstDate);
	let secondDate = inputDateTwo.slice(0, 4) + inputDateTwo.slice(5, 7) + inputDateTwo.slice(8, 10);
	//	console.log(secondDate);

	if (secondDate < firstDate) {
		// 讓 first Date 在 second Date 前一天
		let yearMonthDay = String(secondDate - 1);
		let newInputDateOne = yearMonthDay.slice(0, 4) + '-' + yearMonthDay.slice(4, 6) + '-' + yearMonthDay.slice(6, 9);
		//console.log(yearMonthDay.slice(0, 4));
		//console.log(yearMonthDay.slice(4, 6));
		//console.log(yearMonthDay.slice(6, 9));
		//		console.log(newInputDateOne);

		$("#inputDateOne").val(newInputDateOne);
	}
}




// 確定按鈕
function selectByDate() {

	let count = 0;

	let inputDateOne = $("#inputDateOne").val();
	let inputDateTwo = $("#inputDateTwo").val();

	let firstDate = inputDateOne.slice(0, 4) + inputDateOne.slice(5, 7) + inputDateOne.slice(8, 10);
	//	console.log(firstDate);
	let secondDate = inputDateTwo.slice(0, 4) + inputDateTwo.slice(5, 7) + inputDateTwo.slice(8, 10);
	//	console.log(secondDate);

	// 判斷
	function select(str) {

		let newStr = str.slice(0, 4) + str.slice(5, 7) + str.slice(8, 10);
		//		console.log(newStr);
		if (newStr >= firstDate && newStr <= secondDate) {
			let newString = String(newStr);
			let newView = newString.slice(0, 4) + '-' + newString.slice(4, 6) + '-' + newString.slice(6, 9) + ' ' + str.slice(11, 13) + ':' + str.slice(14, 16);
			return "<td>" + newView + "</td>";
		}


	}


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
				for (let i = 0; i < objectJSON.length; i++) {
					let str = select(objectJSON[i].recordTime);
					if (typeof str === "undefined") {
						continue;
					}
					count++;
					html += "<tr class='text-center'>";
					html += select(objectJSON[i].recordTime);
					html += firstChar(objectJSON[i].chargeAmount);
					html += "</tr>";

				}

				if (count == '0') {

					html += "<tr class='text-center'>";
					html += "<td style='color:red;'>" + "尚無此筆紀錄" + "</td>";
					html += "<td>" + "" + "</td>";
					html += "</tr>";

				}


				html += "</tbody>";
				html += "</table>";

				document.getElementById("show").innerHTML = html;

			},
		}
	);

}

