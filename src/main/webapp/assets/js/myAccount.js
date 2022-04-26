/**
 * 
 */

let xhr2 = null;
getMemberOder();
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

        /*
            先檢查是否有抓到資料
            接下來要在網頁上顯示
        */

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
            str += "<td><a href=" + "cart.html" + "class=" + "revise" + ">Revise</a></td>";
            str += "<td><a href=" + "cart.html" + "class=" + "view" + ">View</a></td>";
        }
        str += "</tr></tbody>";
        $("#memberGroupOrder").append(str);

    }

    let url = "/CGA101G2/member/groupOrders?memberId=1";
    xhr.open("Get", url, true);
    xhr.send(null);
}



function createXHR2() {
    if (window.XMLHttpRequest) {
        xhr2 = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr2 = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xhr2;
}


function getMemberOder() {
    /*
      創建 xhr 物件
      解決瀏覽器相容性的問題
    */
    xhr2 = createXHR2();

    xhr2.onload = function () {
        if (xhr2.status == 200) {
            /*
                GetHsonWithJava.jsp 的 out 資料
                在 xhr.responseText 接住回應
                並丟到 showEmployee 做剖析
            */
            showMemberOder(xhr2.responseText);
        } else {
            alert(xhr2.status);
        }
    };

    /*
        剖析 json 字串，轉成 js 物件
    */
    function showMemberOder(json) {
        /*
            JSON.parse 將一個 JSON 字串轉換成 JavaScript 的數值或是物件
        */
        let orders = JSON.parse(json);
        console.log(orders); // emp 經過 JSON.parse 變成物件
        let html = "";

        /*
            先檢查是否有抓到資料
            接下來要在網頁上顯示
        */

        let str = "<tbody>";
        for (let order of orders) {
            str += "<tr><td>" + order.orderId + "</td>";
            str += "<td>" + order.payPrice + "</td>";
            if (order.status === 0) {
                str += "<td>處理中</td>";
            } else if (order.status === 1) {
                str += "<td>取消</td>";
            } else {
                str += "<td>已完成</td>";
            }
            str += "<td>" + order.createTime + "</td>";
            str += "<td><a href=" + "cart.html" + "class=" + "view" + ">View</a></td>";
        }
        str += "</tr></tbody>";
        $("#memberOrder").append(str);

    }

    let url = "/CGA101G2/member/orders?memberId=1";
    xhr2.open("Get", url, true);
    xhr2.send(null);
}

