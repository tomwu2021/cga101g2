      //!製作side nav sort1主分類
      //使用fetch訪問servlet獲得sort1
      //抓取想插入的節點
      URL = "http://localhost:8081/cga101g2/front/shop/getAllSortMix";
      fetch(URL) // 使用 fetch() 發出 request
        .then(function (response) {
          // 利用 then() 取得 response
          return response.json(); // 需透過 json()、text() 轉成可以使用的資訊
        })
        .then(function (myJson) {
          for (let i = 0; i < myJson[0].length; i++) {
            let sub_menu = document.getElementsByClassName(
              "widget_list widget_color"
            )[0];
            sort1VO = myJson[0][i];
            sub_menu.innerHTML += `<h3 style><a href="${getContextPath()}/front/shop.html">${
              sort1VO.sort1Name
            }${sort1VO.sort1Name}專區</a> </h3>`;
          }
          for (let i = 0; i < myJson[0].length; i++) {
            let str = "";
            let h3 = document.getElementsByTagName("h3")[1 + i];
            str += ` <ul> `;
            sort1VO = myJson[0][i];
            for (let a = 0; a < myJson[1].length; a++) {
              sortMixVO = myJson[1][a];
              if (sortMixVO.sort1Id === sort1VO.sort1Id) {
                str += ` <li>
                             <a href="#">${sortMixVO.sort2Name}</a> 
                        </li> `;
              } else {
              }
            }
            str += ` </ul><br> `;
            h3.insertAdjacentHTML("afterend",str);
          }
        })
        .catch((error) => console.error);
      //獲取當前專案路徑'/cga101g2'
      function getContextPath() {
        return window.location.pathname.substring(
          0,
          window.location.pathname.indexOf("/", 2)
        );
      }