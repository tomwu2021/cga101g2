
let status= 0;
let createTime=$("#createTime").val();
let productName = $("#productName").val();

function search() {
    productName = $("#productName").val();
    $.get({
        url: getContextPath() + "/groupShop?thisPage=" + thisPage + "&order=update_time&pageSize="
            + pageSize + "&sort=" + sort +"&productName=" + productName +"&action=search",
        success: function (result, status) {
            let html="";
            for(let product of result.items) {
                html += buildProduct(product);
            }
            $("#group-shop-product").html(html);
            makePicturePages(result.pageCount);
            let pageResult = "Showing from " + result.start + " to " + result.end + " of " + result.total + " results";
            $(".page_amount").text(pageResult);
        }
    })
}
// function buildProductThumb(picList,productId){
//     let html="";
//
//
//     return html;
// }
function buildProduct(productVO) {
    let html = "";
    let picList = productVO.pictureVOList;
    // let figure = $("#group-shop-product").find('figure').eq(i);
    console.log(picList);
    let productId = productVO.productId;
    let price = productVO.price;
    let groupPrice1 = productVO.groupPrice1;
    let productName = productVO.productName;
    let description = productVO.description;
    let contextPath = getContextPath();
    console.log(contextPath);
    html += `<div class="col-lg-4 col-md-4 col-sm-6">
                <article class="single_product">
                    <figure>
                    <div class="product_thumb">`;
    if(picList.length>0){
        html+= `<a class="primary_img" href="${contextPath}/shop/ProductGetOneServlet?productId=${productId}&action=getOne_For_GroupShop">
            <img src="${picList[0].previewUrl}" alt=""></a>`;
    }
    if(picList.length>=2){
        html+=  `<a class="secondary_img" href="${contextPath}/shop/ProductGetOneServlet?productId=${productId}&action=getOne_For_GroupShop">
                    <img src="${picList[1].previewUrl}" alt=""></a>`
    }
    html+=` </div>
            <div class="product_content grid_content">
                <h4 class="product_name" style="min-height: 50px;max-height: 50px;">
                    <a href="${contextPath}/shop/ProductGetOneServlet?productId=${productId}&action=getOne_For_GroupShop"> ${productName}</a>
                </h4>
                <div class="price_box">
                    <span class="old_price">原價${price}元</span>
                </div>
                <div class="price_box">
                    <span class="current_price">基本成團價${groupPrice1}元</span>
                </div>
                <div class="add_to_cart" style="bottom: 65px;">
                    <a href="${contextPath}/shop/ProductGetOneServlet?productId=${productId}&action=getOne_For_GroupShop" title="Add to cart" style="width: 138px;font-size: 16px;letter-spacing: 2px;"> 我要開團 </a>
                </div>
            </div>
            <div class="product_content list_content">
               <div class="product_desc">
                <p> ${description}</p>
             </div>
            </div>
        </figure>
    </article>
</div>`
    return html;
}

$(document).ready(() => search());
document.getElementById("sort").onchange = getBySort;
document.getElementById("pageSize").onchange = getByPageSize;
document.getElementById("productName").onchange = getByProductName;


function getBySort() {
    sort = $("#sort").val();
    console.log(sort);
    search();
}

function getByPageSize() {
    pageSize = $("#pageSize").val();
    console.log(pageSize);
    thisPage = 1;
    search();
}
function getByProductName() {
    productName = $("#productName").val();
    console.log(productName);
    thisPage = 1;
    search();
}