$("#address").click(function() {

	if ($("#address").is(':checked')) {
		$("#mname").attr("style", "display:none");
		$("#mpho").attr("style", "display:none");
		$("#made").attr("style", "display:none");


	} else {
		$("#mname").removeAttr("style");
		$("#mpho").removeAttr("style");
		$("#made").removeAttr("style");
	}

});

	//地址預設
	$("#zipcode3").twzipcode({
		"zipcodeIntoDistrict" : true,
		"css" : [ "city form-control", "town form-control" ],
		countySel : "臺北市", // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
		districtSel : "大安區", // 地區預設值
	});
bonusChange();
function bonusChange(){
	var bonus=$("#bonusCount").val();
	$("#bonus").text("-"+bonus);
	var payPrice=(Number($("#total").text()))+(Number($("#discount").text()))+(Number($("#bonus").text()));
	$("#payPrice").text(payPrice);
}

