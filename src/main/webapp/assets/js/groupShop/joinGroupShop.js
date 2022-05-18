
let status = 0;
let createTime = $("#createTime").val();


function search() {
productName = $("#productName").val()||"";
	$.get({
		url: getContextPath() + "/member/groupOrder.do?status=" + status + "&thisPage=" + thisPage + "&order=create_time&pageSize="
			+ pageSize + "&sort=" + sort + "&productName=" + productName + "&action=toJoinDetial",
		success: function(result, status) {
			let html = "";
			for(let groupOrderVO of result.items){
				html += buildProduct(groupOrderVO);
			}
			$("#groupShopProduct").html(html);
			makePicturePages(result.pageCount);
			let pageResult = "Showing from " + result.start + " to " + result.end + " of " + result.total + " results";
			$(".page_amount").text(pageResult);
		}
	})
}

function buildProduct(groupOrderVO) {
	let html = "";
	let groupOrderId = groupOrderVO.groupOrderId;
	let productName = groupOrderVO.productVO.productName;
	let price = groupOrderVO.productVO.price;
	let groupPrice1 = groupOrderVO.productVO.groupPrice1;
	let description = groupOrderVO.productVO.description;
	let pictureVOList = groupOrderVO.productVO.pictureVOList;
	let productId=groupOrderVO.productVO.productId;
	let contextPath = getContextPath();
	html += `<Form
				ACTION="${contextPath}/member/groupOrder.do"
				Method="Post" name="GO${groupOrderId}">
				<input type="hidden" name="action" value="joinGroupOrder">
				<input type="hidden" name="groupOrderId"
				value="${groupOrderId}">
				</Form>
				<div class="col-lg-4 col-md-4 col-sm-6 col-12">
						<article class="single_product">
							<figure>
							<div class="product_thumb" > `;
	if (pictureVOList.length > 0) {
		html += `<a class="primary_img" href = "javascript:document.GO${groupOrderId}.submit();" >
					<img src="${pictureVOList[0].previewUrl}" alt="">
				</a>`;
	}
	if (pictureVOList.length >= 2) {
		html += `<a class="secondary_img" href="javascript:document.GO${groupOrderId}.submit();">
					<img src="${pictureVOList[1].previewUrl}" alt="">
				</a>`;
	}
		html += `<div class="product_content grid_content">
            			<h4 class="product_name" style="min-height: 50px;max-height: 50px;">
							<a
								href="javascript:document.GO${groupOrderId}.submit();">
								${productName}</a>
						</h4>
						<div class="price_box">
							<span class="old_price">原價${price}元</span>
						</div>
						<div class="price_box">
							<span class="current_price">基本成團價${groupPrice1}元</span>
						</div>
	                <div class="add_to_cart" style="bottom: 65px;">
							<a href="javascript:document.GO${groupOrderId}.submit();"
								title="Add to cart"  style="width: 138px;font-size: 16px;letter-spacing: 2px;"> 我要參團 </a>
						</div>
					</div>
					<div class="product_content list_content">
						<div class="product_desc">
							<p>${description}</p>
						</div>
					</div>
				</figure >
			</article >
		</div > `;
	return html;
}


// function buildProductThumb(pictureVOList, groupOrderId) {
// 	let html = "";
//
// }
//

$(document).ready(() => search());
document.getElementById("sort").onchange = getBySort;
document.getElementById("status").onchange = getByStatus;
document.getElementById("pageSize").onchange = getByPageSize;
document.getElementById("productName").onchange = getByProductName;


function getBySort() {
	sort = $("#sort").val();
	console.log(sort);
	search();
}

function getByStatus() {
	status = $("#status").val();
	console.log(status);
	thisPage = 1;
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