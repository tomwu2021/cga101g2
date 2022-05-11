// $(function() {
//    $("[id^='updateStatus']").bootstrapToggle({
//      on: '推薦',
//      off: 'off'
//    });
//  })
//按下選單，所有選單都會執行active
$("[id^='updateTopStatus']").change(function thisfunction() {
	//獲取當前option status 索引值
	let id = $(this).attr("id");
	console.log("被選中的input元素的id值 :" + id);
	let productId = id.substring(15);
	console.log("productId : " + productId);
	let oldTopStatus = $(this).val();
	console.log("oldTopStatus : " + oldTopStatus);

	let newTopStatus;
	if (parseInt(oldTopStatus) === 0) {
		newTopStatus = 1;
	} else if (parseInt(oldTopStatus) === 1) {
		newTopStatus = 0;
	}
	console.log("newTopStatus : " + newTopStatus);

	//	當前提交input往上的"td"點	<td> 
	let newStatusTd = $(this).parent().parent();
	console.log(newStatusTd);

	$.ajax({
		url: "shop/productUpdateTopStatusServlet?product_id=" + productId + "&top_status=" + newTopStatus,
		type: "GET",
		dataType: "json", // 返回格式為json
		success: function(data) {
			console.log(data);
			console.log(data.msg);
			//此處重點data.msg的type是string 
			//			使用string比較if(data.msg === '-2')或是轉成int (parseInt(data.msg) === 1)
			if (data.msg === '1') {
				Swal.fire("更新成功", "更新成功", "success")
					.then(function() {
						if (parseInt(oldTopStatus) === 0) {
							$("[id ='" + id + "']").val("1");
							//$(this).prop("checked", true).change();
							$(newStatusTd).attr('class', 'table-primary')
							$("[id ='" + id + "']").off('change');//先關閉Change事件。
							$("[id ='" + id + "']").bootstrapToggle('on');
							$("[id ='" + id + "']").on('change', thisfunction);//執行完後打開Change 事件，並執行thisfunction

						}
						if (parseInt(oldTopStatus) === 1) {
							$("[id ='" + id + "']").val("0");
							$(newStatusTd).attr('class', ' ')
							$("[id ='" + id + "']").off('change');
							$("[id ='" + id + "']").bootstrapToggle('off');
							$("[id ='" + id + "']").on('change', thisfunction);
						}
					})
			}

			else if (data.msg === '-2') {
				Swal.fire("推薦失敗", "已有六筆已上市且已推薦商品，請取消現有推薦商品", "error")
					.then(function() {
						$("[id ='" + id + "']").off('change');//先關閉Change事件。
						$("[id ='" + id + "']").bootstrapToggle('off');
						$("[id ='" + id + "']").on('change', thisfunction);//執行完後打開Change 事件，並執行thisfunction

					})
			}
			else if (data.msg === '-3') {
				Swal.fire("推薦失敗", "請先上市該商品", "error")
					.then(function() {
						$("[id ='" + id + "']").off('change');
						$("[id ='" + id + "']").bootstrapToggle('off');
						$("[id ='" + id + "']").on('change', thisfunction);
					})
			}
			else if (data.msg === '-1') {
				Swal.fire("更新失敗", "請洽管理員", "error")
			}
		},
		error: function(data) {
			alert("操作異常,回報錯誤給服務供應商" + data.responseText);
		}
	});
});

