function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
};


$("#inputHorizontalSuccessCheckProductName").on('blur', CheckProductName);

function CheckProductName() {
	let productName = $(this).val().trim();
	let input = $(this);
	$(this).off('blur');
	
	var strProductName = String(productName);
	let length = strProductName.trim().length;
	if (length <= 4) {
		input.next().next().text("商品名稱不可小於5個字")
	}
	else if (length > 40) {
		input.next().next().text("商品名稱不可大於40個字")
	} else {
		//-1商品名稱空白,不可以送出
		//0代表有重複,不可以送出
		//1代表沒重複,可以送出
		$.ajax({
			url: `${getContextPath()}/shop/CheckProductName`,
			type: "POST",
			data: {
				productName: productName
			},
			dataType: "json", // 返回格式為json
			success: function(data) {
				console.log(data);
				input.on('blur', CheckProductName);
				if (data.msg === '-1') {
					input.next().next().text("不可空白")
				}
				else if (data.msg === '0') {
					input.next().next().text("與現有的商品名稱重複")
				}
				else if (data.msg === '1') {
					input.next().next().text(" ")
					$("#upTo").removeAttr("disabled");
					$("#upToShop").removeAttr("disabled");
					$("#upToShopAndGropShop").removeAttr("disabled");
				}
			},
			error: function(data) {
				input.on('blur', CheckProductName);
				alert("操作異常,回報錯誤給服務供應商" + data.responseText);
			}
		});
	}
}

let productInsertFrom = document.getElementById("productInsertFrom");

$("#upToShop").click(function() {
	$(this).next().val('upToShop');
	productInsertFrom.submit();
});

$("#upToShopAndGropShop").click(function() {
	$(this).next().val('upToShopAndGropShop');
	productInsertFrom.submit();
});
