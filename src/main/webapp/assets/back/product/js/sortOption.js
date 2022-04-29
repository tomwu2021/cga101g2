(() => {
    //獲取當前專案路徑'/cga101g2'
    //使用fetch訪問servlet獲得sort1 JSON動態製作下拉式選單
    let optionStart = document.getElementById('inlineFormCustomSelect');
    URL = "http://localhost:8081/cga101g2/shop/getSort2VOsAndSort1VOList";
    fetch(URL) // 使用 fetch() 發出 request
      .then(function (response) { // 利用 then() 取得 response
        return response.json(); // 需透過 json()、text() 轉成可以使用的資訊
      })
      .then(function (myJson) {
        console.log(myJson); // 得到的 json
        optionStart.innerHTML =" ";
        let sort2VO;
        let str = "";
        for (let i = 0; i < myJson.length; i++) {
          sort2VO = myJson[i];
          str +=`<option value="${sort2VO.sort2Id}">${sort2VO.sort2Name}</option> `;
        }
        optionStart.innerHTML += str;


        
      });



  })
  ();