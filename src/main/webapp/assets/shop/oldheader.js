(() => {
  //獲取當前專案路徑'/cga101g2'
  function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
  }
  //使用fetch訪問servlet獲得sort1 JSON動態製作下拉式選單
  let sub_menu = document.getElementsByClassName('sub_menu pages');
  let list = sub_menu[1];
  URL = "http://localhost:8081/cga101g2/front/shop/getAllSort1";
  fetch(URL) // 使用 fetch() 發出 request
    .then(function (response) { // 利用 then() 取得 response
      return response.json(); // 需透過 json()、text() 轉成可以使用的資訊
    })
    .then(function (myJson) {
      // console.log(myJson); // 得到的 json
      list.innerHTML = "";
      let sort1VO;
      for (let i = 0; i < myJson.length; i++) {
        sort1VO = myJson[i];
        list.innerHTML +=
          ` <li><a href="${getContextPath()}/shop.html">${sort1VO.sort1Name}${sort1VO.sort1Name}專區</a></li>`;
      }
    });

})
();