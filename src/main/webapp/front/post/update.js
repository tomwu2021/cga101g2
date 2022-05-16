/**
 * update post content
 */
 
 $("[id^='updatePost']").click(function() {
	   let id = $(this).attr("id");
	   let postId = id.substring(10);
	
        Swal.fire({
            title: 'Are you sure?',
            text: "確定修改這篇貼文嗎 ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: 'darkgrey',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {      	
        	
            //點擊修改後跳修改頁面

            if (result.isConfirmed) {
	        location.href = `${getContextPath()}/detailPost?action=changecontent&postId=`+postId
            }
        });
    })