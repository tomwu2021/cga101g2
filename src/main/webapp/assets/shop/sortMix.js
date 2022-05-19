function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
};

var sort1Date;
$.ajax({
	url: `${getContextPath()}/front/shop/getAllSort1`,
	type: "POST",
	dataType: "json", // 返回格式為json
	success: function(data) {
		sort1Date = data;
		console.log(sort1Date);
	},
	error: function(data) {
		alert("操作異常,回報錯誤給服務供應商" + data.responseText);
	}
});
var sort2Date;
$.ajax({
	url: `${getContextPath()}/front/shop/getSort2VO`,
	type: "POST",
	dataType: "json", // 返回格式為json
	success: function(data) {
		sort2Date = data;
		console.log(sort2Date);
	},
	error: function(data) {
		alert("操作異常,回報錯誤給服務供應商" + data.responseText);
	}
});

$("#checkSort1Name").bind('input porpertychange', function() {
	let sort1Name = $(this).val().trim();
//	console.log("抓到的"+sort1Name);
	let input = $(this);
	input.next().children().removeAttr("disabled");
	var strSort1Name = String(sort1Name);
	let length = strSort1Name.trim().length;
	if (length === 0) {
		input.next().children().attr("disabled", "disabled");
	}
	else {
		for (let i = 0; i < sort1Date.length; i++) {
//			console.log("迴圈的"+sort1Date[i].sort1Name);	
			if (strSort1Name === sort1Date[i].sort1Name) {//key所对应的value
				input.next().children().attr("disabled", "disabled");
				console.log("一樣的"+sort1Date[i].sort1Name);
				return;
			} 
		}
	}
});

$("#checkSort2Name").bind('input porpertychange', function() {
	let sort2Name = $(this).val().trim();
	let input = $(this);
	input.next().children().removeAttr("disabled");
	var strSort2Name = String(sort2Name);
	let length = strSort2Name.trim().length;
	if (length === 0) {
		input.next().children().attr("disabled", "disabled");
	}
	else {
		for (let i = 0; i < sort2Date.length; i++) {
			console.log("迴圈的"+sort2Date[i].sort2Name);	
			if (strSort2Name === sort2Date[i].sort2Name) {//key所对应的value
				input.next().children().attr("disabled", "disabled");
				console.log("一樣的"+sort2Date[i].sort2Name);
				return;
			} 
		}
	}
});

