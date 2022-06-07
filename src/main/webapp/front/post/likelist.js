
function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
};
//貼文愛心icon新增&收回 該篇貼文單個讚
$("[id^='likelist']").on('click', myFunc);

function myFunc() {
	$(this).off('click');
	
	//獲取當前button value 索引值
	let id = $(this).attr("id");
//	console.log("當前元素id編號: " + id);
	let postId = id.substring(8);
//	console.log("當前貼文編號: " + postId);
	let val = $(this).prev().val();
//	console.log("上一個隱藏的input元素value: " + val);
	//儲存這個DOM點
	let thisIcon = $(this);

	//1代表已點讚,點擊後收回讚,改變icon與讚數 
	//0代表未點讚,點擊後給讚,改變icon與讚數 
	
		$.ajax({
			url:`${getContextPath()}/post/likelistServlet` ,
			type: "POST",
			data: {
				postId: postId, val: val
			},
			dataType: "json", // 返回格式為json
			success: function(data) {
				thisIcon.on('click', myFunc);//執行完後打開click 事件，並執行thisfunction
//				console.log(data);
//				console.log(data.msg);
				//確定有登入後再次發送請求
				if (data.msg === '1') {

					thisIcon.attr('class', 'bi bi-suit-heart-fill fa-2x').prev().val('1');
					thisIcon.parent().next().text(data.newlikeCount);
//					console.log("修改過後的讚數:"+thisIcon.parent().next().text());
				}
				else if (data.msg === '2') {

					thisIcon.attr('class', 'bi bi-suit-heart fa-2x').prev().val('0');
					thisIcon.parent().next().text(data.newlikeCount);
//					console.log("修改過後的讚數:"+thisIcon.parent().next().text());
				}
				else if (data.msg === '-1') {
					Swal.fire(
						'請求失敗...!',
						' ',
						'warning'
					)
				}
				else if (data.msg === '-2') {
					Swal.fire({
						icon: 'info',
						title: 'Oops...',
						text: '先登入才能點讚喔！',
						footer: '<a href=' + `${getContextPath()}/front/login.jsp` + '>前往登入頁面</a>'
					})
				}
			},
			error: function(data) {
				thisIcon.on('click', myFunc);//執行完後打開click 事件，並執行thisfunction
				alert("操作異常,回報錯誤給服務供應商" + data.responseText);
			}
		});
}

