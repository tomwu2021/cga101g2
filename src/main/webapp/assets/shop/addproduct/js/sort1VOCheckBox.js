// 前台傳值方法
// let url ="GetJsonWithJava.jsp?empno=" + document.getElementById("empno").value;
// $("#sort1option").click(function() {
//   $.ajax({
//     url : "getSort1VOsBySort2Id?Sort2Id=2",
//     type : "GET",
//     //do yourself
//     success : function(data) {
//       // $("#sort1option").html(data);
//       console.log(data);
//     }
//   });
// });

//針對 select tag 設定onchange="get_sort1index()"
function get_sort1index() {
  //清空sort2CheckBox上次的選項 移除子節點
  const sort2CheckBox = document.getElementById('sort2CheckBox');
  while (sort2CheckBox.hasChildNodes()) {
    sort2CheckBox.removeChild(sort2CheckBox.firstChild);
  }

   //獲取當前option索引值
  let selectname = $('#inlineFormCustomSelect').val();

  //發送請求帶入變數
  $.ajax({
    url: "getSort1VOsBySort2Id?sort2Id=" + selectname,
    type: "GET",
    //do yourself
    success: function (data) {
      // $("#sort1option").html(data);
      console.log(data);
      console.log(data[0]);
      sort2CheckBox.innerHTML = "";
      let sort1VO;
      let str = " ";
      for (let i = 0; i < data.length; i++) {
        sort1VO = data[i];
        str += `<div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="sort1Id" id="inlineCheckbox1" value="${sort1VO.sort1Id}"> 
            <label class="form-check-label" for="inlineCheckbox1">${sort1VO.sort1Name}</label>
             </div>`;
      }
      sort2CheckBox.innerHTML += str;
    }
  });
}