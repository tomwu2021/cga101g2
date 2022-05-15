function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
};
//用愛心icon前往收藏清單頁面 
$("#goWishlist").click(function() {
	let id = $(this).attr("id");
	console.log(id);
	$.ajax({
		url: `${getContextPath()}/shop/wishlist/getSessionMemberId`,
		type: "POST",
		dataType: "json", // 返回格式為json
		success: function(data) {
			console.log(data);
			console.log(data.msg);
			//確定有登入後再次發送請求
			if (data.msg === '1') {
				$(location).attr('href', `${getContextPath()}/shop/wishlist`);
			}
			else {
				Swal.fire({
					icon: 'info',
					title: 'Oops...',
					text: '先登入才能收藏喔！',
					footer: '<a href=' + `${getContextPath()}/front/login.jsp` + '>前往登入頁面</a>'
				})
			}
		},
		error: function(data) {
			alert("操作異常,回報錯誤給服務供應商" + data.responseText);
		}
	});
});

//wlishlist.jsp的垃圾桶icon
$("[id^='deleteList']").click(function() {
	//fetch DOM elements
	let id = $(this).attr("id");
	let productId = id.substring(10);
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
				url: "deleteWishlist",
				type: "POST",
				data: JSON.stringify({
					productId
				}),
				dataType: "json", // 返回格式為json
				success: function(data) {
					console.log(data);
					console.log(data.msg);
					//確定有登入後再次發送請求
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
					else if (data.msg === '-2') {
						let timerInterval
						Swal.fire({
							title: '您尚未登入!',
							html: '倒數 <b></b> 秒跳轉至商城首頁.',
							timer: 2500,
							timerProgressBar: true,
							didOpen: () => {
								Swal.showLoading()
								const b = Swal.getHtmlContainer().querySelector('b')
								timerInterval = setInterval(() => {
									b.textContent = Swal.getTimerLeft()
								}, 100)
							},
							willClose: () => {
								clearInterval(timerInterval)
							}
						}).then((result) => {
							/* Read more about handling dismissals below */
							if (result.dismiss === Swal.DismissReason.timer) {
								console.log('I was closed by the timer')
								location.href = `${getContextPath()}/shop?action=listProducts_Byfind`
							}
						})
					}
				},
				error: function(data) {
					alert("操作異常,回報錯誤給服務供應商" + data.responseText);
				}
			})
		}
	})

});

//商品細節愛心icon新增&刪除單個收藏商品
$("[id^='Wlishlist']").click(function thisfunction() {
	//fetch DOM elements
	//獲取當前button value 索引值
	let id = $(this).attr("id");
	console.log("當前元素id編號: " + id);
	let productId = id.substring(9);
	console.log("當前商品編號: " + productId);
	let val = $(this).val();
	console.log("當前元素狀態: " + val);
	//儲存這個DOM點
	let thisButton = $(this);

	//1代表已加入收藏清單,點擊後刪除 
	//0代表未收藏清單,點擊後新增
	if (val === '0') {
		$.ajax({
			url: `insertWishlist`,
			type: "POST",
			data: JSON.stringify({
				productId
			}),
			dataType: "json", // 返回格式為json
			success: function(data) {
				console.log(data);
				console.log(data.msg);
				//確定有登入後再次發送請求
				if (data.msg === '1') {
					Swal.fire(
						'加入成功!',
						'已新增至收藏清單',
						'success'
					)
					console.log($(this));
					thisButton.attr('class', 'btn btn-danger')
					.val('1')
					.children().text('已加入收藏');
					console.log(thisButton.val());
					thisButton.off('click');//先關閉click事件。
					thisButton.on('click', thisfunction);//執行完後打開click 事件，並執行thisfunction
				}
				else if (data.msg === '-1') {
					Swal.fire(
						'Oops...',
						'加入失敗...',
						'warning'
					)
				}
				else if (data.msg === '-2') {
					Swal.fire({
						icon: 'info',
						title: 'Oops...',
						text: '先登入才能收藏喔！',
						footer: '<a href=' + `${getContextPath()}/front/login.jsp` + '>前往登入頁面</a>'
					})
				}
			},
			error: function(data) {
				alert("操作異常,回報錯誤給服務供應商" + data.responseText);
			}
		});


	}
	else if (val === '1') {
		$.ajax({
			url: "deleteWishlist",
			type: "POST",
			data: JSON.stringify({
				productId
			}),
			dataType: "json", // 返回格式為json
			success: function(data) {
				console.log(data);
				console.log(data.msg);
				//確定有登入後再次發送請求
				if (data.msg === '1') {
					Swal.fire(
						'移除成功!',
						'已將該商品移除收藏清單',
						'success'
					)
					console.log($(this));
					thisButton.attr('class', 'btn btn-outline-danger')
					.val('0')
					.children().text('已加入收藏');
					console.log(thisButton.val());
					thisButton.off('click');//先關閉click事件。
					thisButton.on('click', thisfunction);//執行完後打開click 事件，並執行thisfunction
				}
				else if (data.msg === '-1') {
					Swal.fire(
						'Oops...',
						'移除失敗...',
						'warning'
					)
				}
				else if (data.msg === '-2') {
					Swal.fire(
						'Oops...',
						'移除失敗...請先登入!',
						'warning'
					)
				}
			},
			error: function(data) {
				alert("操作異常,回報錯誤給服務供應商" + data.responseText);
			}
		})
	}

});





///***初版參考**///
//商品細節愛心icon新增單個收藏商品
$("[id^='insertWlishlist']").click(function() {
	//fetch DOM elements
	let id = $(this).attr("id");
	let productId = id.substring(15);
	console.log("原本的的id值: " + id);
	let thisButton = $(this);

	$.ajax({
		url: `insertWishlist`,
		type: "POST",
		data: JSON.stringify({
			productId
		}),
		dataType: "json", // 返回格式為json
		success: function(data) {
			console.log(data);
			console.log(data.msg);
			//確定有登入後再次發送請求
			if (data.msg === '1') {
				Swal.fire(
					'加入成功!',
					'已新增至收藏清單',
					'success'
				)
				console.log($(this));
				thisButton.attr('id', 'deleteWlishlist' + productId);
				thisButton.attr('class', 'btn btn-danger');
				thisButton.children().text('已加入收藏');
				let id = thisButton.attr("id");
				console.log("新增後的id值: " + id);
			}
			else if (data.msg === '-1') {
				Swal.fire(
					'Oops...',
					'加入失敗...',
					'warning'
				)
			}
			else if (data.msg === '-2') {
				Swal.fire({
					icon: 'info',
					title: 'Oops...',
					text: '先登入才能收藏喔！',
					footer: '<a href=' + `${getContextPath()}/front/login.jsp` + '>前往登入頁面</a>'
				})
			}
		},
		error: function(data) {
			alert("操作異常,回報錯誤給服務供應商" + data.responseText);
		}
	})
});

//商品細節頁面  刪除單個收藏商品
$("[id^='deleteWlishlist']").click(function() {
	//fetch DOM elements
	let id = $(this).attr("id");
	let productId = id.substring(15);
	console.log("原本的的id值: " + id);
	let thisButton = $(this);

	$.ajax({
		url: "deleteOneWishlist",
		type: "POST",
		data: JSON.stringify({
			productId
		}),
		dataType: "json", // 返回格式為json
		success: function(data) {
			console.log(data);
			console.log(data.msg);
			//確定有登入後再次發送請求
			if (data.msg === '1') {
				Swal.fire(
					'移除成功!',
					'已將該商品移除收藏清單',
					'success'
				)
				console.log($(this));
				thisButton.attr('id', 'insertWlishlist' + productId);
				thisButton.attr('class', 'btn btn-outline-danger');
				thisButton.children().text('加入收藏');
				let id = thisButton.attr("id");
				console.log("刪除後的id值: " + id);
			}
			else if (data.msg === '-1') {
				Swal.fire(
					'Oops...',
					'移除失敗...',
					'warning'
				)
			}
			else if (data.msg === '-2') {
				Swal.fire(
					'Oops...',
					'移除失敗...請先登入!',
					'warning'
				)
			}
		},
		error: function(data) {
			alert("操作異常,回報錯誤給服務供應商" + data.responseText);
		}
	})
});
