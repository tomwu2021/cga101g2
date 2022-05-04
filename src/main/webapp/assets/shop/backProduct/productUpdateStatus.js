  /* <button id="update${productVO.productId}" name="status" type="button" class="btn btn-primary btn-sm update" >提交</button> */
//按下選單，所有選單都會執行active
$("[id^='update']").click(function () {
      //獲取當前option status 索引值
      let status = $(this).parents().prev().val();
      console.log(status);

      let statusName = "";
      if (parseInt(status) === 0) {
        statusName = "未上架";
      } else if (parseInt(status) === 1) {
        statusName = "一般商品上架";
      } else if (parseInt(status) === 1) {
        statusName = "一般與團購上架";
      }

      let productId = $(this).val();
      console.log(productId) 
        /* <button id="update${productVO.productId}" name="status" type="button" class="btn btn-primary btn-sm update" >提交</button> */
      
      //按下選單，所有選單都會執行active
      $("[id^='update']").click(function () {
          //獲取當前option status 索引值
          let status = $(this).parents().prev().val();
          console.log(status);

          let statusName = "";
          if (parseInt(status) === 0) {
            statusName = "未上架";
          } else if (parseInt(status) === 1) {
            statusName = "一般商品上架";
          } else if (parseInt(status) === 1) {
            statusName = "一般與團購上架";
          }

          let productId = $(this).val();
          console.log(productId);

          Swal.fire({
            title: "更改商品狀態為" + statusName + "?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: '確定',
            cancelButtonText: '取消',
          }).then(function (isConfirm) {
            if (isConfirm.value != true) {
              return false;
            } else {
              $.ajax({
                url: "productUpdateStatusServlet?productId=" + productId + "&status=" + status,
                type: "GET",
                dataType: "json", // 返回格式為json
                success: function (data) {
                  console.log(data);
                  if (data.msg > 0) {
                    Swal.fire("更新成功", "跳轉當前頁面", "success")
                      .then(function () {
                        location.reload();
                      })

                  } else {
                    Swal.fire("查詢成功", "商品狀態已是" + statusName, "error")
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

});