  /* <button id="update${productVO.productId}" name="status" type="button" class="btn btn-primary btn-sm update" >提交</button> */
//按下選單，所有選單都會執行active
      
      //按下選單，所有選單都會執行active
      $("[id^='updateStatus']").click(function () {
          //獲取當前option status 索引值
          let status = $(this).parents().prev().val();
          console.log(status);

          let statusName = "";
          if (parseInt(status) === 0) {
            statusName = "未上架";
          } else if (parseInt(status) === 1) {
            statusName = "一般商品上架";
          } else if (parseInt(status) === 2) {
            statusName = "一般與團購上架";
          }

          let productId = $(this).val();
          console.log(productId);
			
//		當前提交button往上的"名稱"點 <span>
		  let nowStatusSpan = $(this).parent().prev().prev();
		  console.log(nowStatusSpan);
//		當前提交button往上的"狀態選項"點	<select> 
		   let nowStatusSelect = $(this).parent().prev();
		  console.log(nowStatusSelect); 
//		當前提交button往上的"td"點	<td> 
		   let nowStatusTd = $(this).parent().parent();		
		   console.log(nowStatusTd);   
		   
          Swal.fire({
            title: "更改商品狀態為\n\n" +statusName + "?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: '確定',
            cancelButtonText: '取消',
          }).then(function (isConfirm) {
            if (isConfirm.value != true) {
              return false;
            } else {
              $.ajax({
                url: "shop/productUpdateStatusServlet?productId=" + productId + "&status=" + status,
                type: "GET",
                dataType: "json", // 返回格式為json
                success: function (data) {
                  console.log(data);
                  if (data.msg > 0) {
                    Swal.fire("更新成功", "商品狀態已改為"+statusName, "success")
                      .then(function () {
						//	如果成功更改成未上架
						if(parseInt(status) === 0){
//							"\xa0\xa0" 增加空白 重要							
//							1.更換nowStatusSpan
							$(nowStatusSpan).text(statusName+"\xa0\xa0\xa0\xa0");
//							2.更換td 內的class
							$(nowStatusTd).attr('class',' ')
//							3.更換 select內的選項
							$(nowStatusSelect).html('<option value="1">一般商品上架</option> <option value="2">一般與團購上架</option>');
						}
						if(parseInt(status) === 1){
							$(nowStatusSpan).text(statusName);
							$(nowStatusTd).attr('class','table-primary')
							$(nowStatusSelect).html('<option value="0">未上架</option> <option value="2">一般與團購上架</option>');
						}
						if(parseInt(status) === 2){
							$(nowStatusSpan).text(statusName);
							$(nowStatusTd).attr('class','table-warning')
							$(nowStatusSelect).html('<option value="0">未上架</option><option value="1">一般商品上架</option>');
						}
						
                      })

                  } else {
                    Swal.fire("查詢完畢", "商品狀態已是" + statusName +"error")
                  }
                },
                error: function (data) {
                  alert("操作異常,回報錯誤給服務供應商" + data.responseText);
                }
              });
            }

          })
        }
      );

