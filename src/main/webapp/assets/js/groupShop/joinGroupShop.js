
let status = 0;
let createTime = $("#createTime").val();


function search() {
	$.get({
		url: getContextPath() + "/member/orders?status=" + status + "&thisPage=" + thisPage + "&order=create_time&pageSize="
			+ pageSize + "&sort=" + sort + "&product_name=" + productName + "&action=search",
		success: function(result, status) {
			
			html = "";
			for(let groupOrderVO of result.items){
				html+=buildProduct(groupOrderVO);
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
	html += `<Form
									ACTION="<%=request.getContextPath()%>/member/groupOrder.do"
									Method="Post" name="GO${groupOrderId}">
									<input type="hidden" name="action" value="toJoinDetial">
									<input type="hidden" name="groupOrderId"
										value="${groupOrderId}">
								</Form>
								<div class="col-lg-4 col-md-4 col-sm-6 col-12">
									<!--單一商品開始 -->

									<article class="single_product">
										<figure>`;
	html += buildProductThumb(pictureVOList,productId);
	`<div class="product_content grid_content">
												<h4 class="product_name">
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
												<div class="add_to_cart">

													<a
														href="javascript:document.GO${groupOrderId}.submit();"
														title="Add to cart"> 我要參團 </a>

												</div>
											</div>
											<div class="product_content list_content">
												<h4 class="product_name">
													<a href="product-details.html">quidem totam, voluptatem
														quae quasi possimus</a>
												</h4>
												<div class="product_desc">
													<p>${description}</p>
												</div>
												<div class="price_box">
													<span class="current_price">$145.00</span> <span
														class="old_price">$178.00</span>
												</div>
												<div class="action_links list_action_right">
													<ul>
														<li class="quick_button"><a href="#"
															data-toggle="modal" data-target="#modal_box"
															title="quick view"> <i class="icon icon-Eye"></i></a></li>
													</ul>
												</div>
											</div>
										</figure >
									</article >
								</div > `
}


function buildProductThumb(pictureVOList, productId) {
	let html = "";
	html += `< div class="product_thumb" > `;

	if (pictureVOList.length > 0) {
		html += `< a class="primary_img"
	href = "javascript:document.GO${groupOrderId}.submit();" >
		<img
			src="${pictureVOList.get[0].previewUrl}"
			alt="">
		</a>`
	}
	if (pictureVOList.length >= 2) {
		html += `<a class="secondary_img"
			href="javascript:document.GO${groupOrderId}.submit();">
			<img src="${pictureVOList.get[1].previewUrl}" alt=""></a>`
	}


	html += `<div class="action_links">
		<ul>
			<li class="quick_button"><a href="#"
				data-toggle="modal" data-target="#modal_box"
				title="quick view"> <i class="icon icon-Eye"></i></a></li>
			<li class="wishlist"><a href="wishlist.html"
				title="Add to Wishlist"><i class="icon icon-Heart"></i></a></li>
		</ul>
	</div>
											</div > `



}


$(document).ready(() => search());
document.getElementById("sort").onchange = getBySort;
document.getElementById("status").onchange = getByStatus;
document.getElementById("createTime").onchange = getByTime;
document.getElementById("pageSize").onchange = getByPageSize;



function getBySort() {
	sort = $("#sort").val();
	console.log(sort);
	search();
}
function getByTime() {
	createTime = $("#createTime").val();
	console.log(createTime);
	thisPage = 1;
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
