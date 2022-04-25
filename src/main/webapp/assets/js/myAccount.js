/**
 * 
 */
let xhr = null;
getGroupOrder();

function createXHR() {
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xhr;
}
function getGroupOrder() {
    /*
      創建 xhr 物件
      解決瀏覽器相容性的問題
    */
    xhr = createXHR();

    xhr.onload = function () {
        if (xhr.status == 200) {
            /*
                GetHsonWithJava.jsp 的 out 資料
                在 xhr.responseText 接住回應
                並丟到 showEmployee 做剖析
            */
            showGroupOrder(xhr.responseText);
        } else {
            alert(xhr.status);
        }
    };

    /*
        剖析 json 字串，轉成 js 物件
    */
    function showGroupOrder(json) {
        /*
            JSON.parse 將一個 JSON 字串轉換成 JavaScript 的數值或是物件
        */
        let groupOrder = JSON.parse(json);
        console.log(groupOrder); // emp 經過 JSON.parse 變成物件
        let html = "";
        console.log(groupOrder);

        /*
            先檢查是否有抓到資料
            接下來要在網頁上顯示
        */

        // let str = "<tbody><tr><td>" + groupOrder[0].groupOrderId + "</td></tr></tbody>";
        let str = "<tbody>";
        for (let order of groupOrder) {
            str += "<tr><td>" + order.productVO.productName + "</td>";
            str += "<td>" + order.productAmount + "</td>";
            str += "<td>" + order.groupOrderVO.finalPrice + "</td>";
            if (order.groupOrderVO.status === 0) {
                str += "<td>進行中</td>";
            } else if (order.groupOrderVO.status === 1) {
                str += "<td>未成團</td>";
            } else {
                str += "<td>已成團</td>";
            }


            str += "<td><a href=" + "cart.html" + "class=" + "view" + ">view</a></td>";
        }
        str += "</tr></tbody>";
        $("#memberOrder").append(str);
        // str += "<tr><td class='mainTitle'>姓名</td><td class='mainData'>" + emp.ename + "</td></tr>";
        // str += "<tr><td class='mainTitle'>電話</td><td class='mainData'><table class='phoneTable' align='left'>";
        // str += "<tr><th>(O)</th><td>" + emp.phone.O + "</td></tr>";
        // str += "<tr><th>(H)</th><td>" + emp.phone.H + "</td></tr>";
        // str += "<tr><th>(M)</th><td>" + emp.phone.M + "</td></tr>";
        // str += "</table></td></tr>";
        // str += "</table>";
        // document.getElementById("showPanel").innerHTML = str;
    }

    let url = "/CGA101G2/member/orders?memberId=1";
    xhr.open("Get", url, true);
    xhr.send(null);
}

