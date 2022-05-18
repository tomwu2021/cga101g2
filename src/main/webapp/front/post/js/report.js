$("#report").click(function() {
	//fetch DOM elements

	let postId = $('#postId').val();
	let reportReason = $('#forgotPassword').val();

	Swal.fire({
		title: '確定檢舉?',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes!'
	}).then((result) => {
		if (result.isConfirmed) {
			if ($('#forgotPassword').val().length == 0 && $('#forgotPassword').val().trim() == "") {
				Swal.fire({
					position: 'center',
					icon: 'error',
					title: "請輸入檢舉內容",
					showConfirmButton: false,
					timer: 1500
				})
			} else {
				$.get({
					url: `${getContextPath()}/postReport.do?postId=${postId}&reportReason=${reportReason}`,
					success: function() {
						Swal.fire({
					position: 'center',
					icon: 'success',
					title: "檢舉成功",
					showConfirmButton: false,
					timer: 1500
				})
							window.location.href =getContextPath()+"/MainPost?action=selectChangePost";		
					},
				})
			}
		}
	})

});

$(function() {
	var screenwidth, screenheight, mytop, getPosLeft, getPosTop
	screenwidth = $(window).width();
	screenheight = $(window).height();
	//獲取滾動條距頂部的偏移
	mytop = $(document).scrollTop();
	//計算彈出層的left
	getPosLeft = screenwidth / 2 - 260;
	//計算彈出層的top
	getPosTop = screenheight / 2 - 150;
	//css定位彈出層
	$("#box2").css({ "left": getPosLeft, "top": getPosTop });
	//當瀏覽器視窗大小改變時...
	$(window).resize(function() {  //視窗被調整過時，運行此函數
		screenwidth = $(window).width();
		screenheight = $(window).height();
		mytop = $(document).scrollTop();
		getPosLeft = screenwidth / 2 - 260;
		getPosTop = screenheight / 2 - 150;
		$("#box2").css({ "left": getPosLeft, "top": getPosTop + mytop });
	});

	//當拉動滾動條時...
	$(window).scroll(function() {
		screenwidth = $(window).width();
		screenheight = $(window).height();
		mytop = $(document).scrollTop();
		getPosLeft = screenwidth / 2 - 260;
		getPosTop = screenheight / 2 - 150;
		$("#box2").css({ "left": getPosLeft, "top": getPosTop + mytop });
	});


	$("#popup22").click(function() {
		$("#box2").fadeIn("fast");  //淡入
		//獲取頁面文件的高度
		var docheight = $(document).height();
		//追加一個層，使背景變灰(此層z-index:100，介於彈出視窗和底層之間)
		$("body").append("<div id='greybackground2'></div>");
		$("#greybackground2").css({ "opacity": "0.5", "height": docheight });
		//return false;
	});

	//點選關閉按鈕
	$("#closeBtn2").click(function() {
		$("#box2").hide();
		//刪除變灰的層
		$("#greybackground2").remove();
		return false;
	});

});