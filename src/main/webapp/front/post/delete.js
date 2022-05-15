function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
};
//wlishlist.jsp的垃圾桶icon
$("[id^='deletePost']").click(function() {
	//fetch DOM elements
	let id = $(this).attr("id");
	let postId = id.substring(10);
	Swal.fire({
		title: '確定刪除?',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes!'
	}).then((result) => {
		if (result.isConfirmed) {

			$.ajax({
				url: `${getContextPath()}/deletePost`,
				type: "POST",
				data: JSON.stringify({
					postId
				}),
				dataType: "json", // 返回格式為json
				success: function(data) {
					console.log(data);
					console.log(data.msg);
					if (data.msg === '1') {
						Swal.fire(
							'刪除成功',
							' ',
							'success'
						)
						location.reload();
					}
					else if (data.msg === '-1') {
						Swal.fire(
							'Oops...',
							'刪除失敗',
							'warning'
						)
					}
				},
				error: function(data) {
					alert("操作異常,回報錯誤給服務供應商" + data.responseText);
				}
			})
		}
	})

});