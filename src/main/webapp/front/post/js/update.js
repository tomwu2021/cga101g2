/**
 * update post content
 */
 
 $("[id^='updatePost']").click(function() {
	   let id = $(this).attr("id");
	   let postId = id.substring(10);
	
        Swal.fire({
            title: '我要修改貼文',
            text: " ",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: 'darkgrey',
            confirmButtonText: '確定',
            cancelButtonText: '取消'
        }).then((result) => {      	
        	
            //點擊修改後跳修改頁面

            if (result.isConfirmed) {
	        location.href = `${getContextPath()}/detailPost?action=changecontent&postId=`+postId
            }
        });
    })