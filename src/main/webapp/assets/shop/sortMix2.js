function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
};

var sort1Date;
$.ajax({
	url: `${getContextPath()}/front/shop/getAllSort1`,
	type: "GET",
	dataType: "json", // 返回格式為json
	success: function(data) {
		sort1Date = data;
		console.log(sort1Date);
		//			if (data.msg === '0') {
		//				input.find("p").eq(3).text("與現有的主分類名稱重複")
		//				input.next().children().attr('disabled', 'disabled');
		//			}
		//			else if (data.msg === '1') {
		//				input.find("p").eq(2).text(" ")
		//				input.next().children().removeAttr("disabled");
		//			}
	},
	error: function(data) {
		alert("操作異常,回報錯誤給服務供應商" + data.responseText);
	}
});

$("#checkSort1Name").bind('input porpertychange', function() {
	let sort1Name = $(this).val().trim();
	let input = $(this);
	var strSort1Name = String(sort1Name);
	let length = strSort1Name.trim().length;
	if (length === 0) {
		input.find("p").eq(3).text("名稱不可空白")
	}
	else {
		for (var item in sort1Date) {
			if (item === 'sort1Name') {  //item 表示Json串中的属性，如'name'
				if (sort1Name = sort1Date[item]) {//key所对应的value
					console.log(sort1Name);
					input.find("p").eq(3).text("與現有的主分類名稱重複")
					input.next().children().attr('disabled', 'disabled');

				} else {
					input.find("p").eq(2).text(" ")
					input.next().children().removeAttr("disabled");
				}
			}
		}
	}
});

