/**
 * 
 */

let fileName = "";
let uploadTime = 7;
let albumId = 9;
let pageSize = 12;
let sort = "DESC";
let thisPage = 1;
let total = 0;
let pageCount = 0;
searchPicture();

function searchPicture() {
	fileName = $("#fileName").val();
	if (fileName !== '') {
		thisPage === 1;
	}
	console.log('sort=' + sort + '&uploadTime=' + uploadTime);
	$.get({
		url: "/CGA101G2/PictureController?fileName=" + fileName + "&albumId=" + albumId + "&thisPage=" + thisPage + "&order=upload_time&pageSize="
			+ pageSize + "&sort=" + sort + "&uploadTime=" + uploadTime,
		success: function (result, status) {
			total = result.total;
			pageCount = result.pageCount;
			let html = "";
			for (let item of result.items) {
				html += "<div class='col-lg-4 col-md-6' style='padding:10px;overflow:hidden;'>";
				html += " <div class='services_thumb' style='width:400px;height:240px';margin:0px auto;overflow:hidden;>";
				html += "<a href=" + item.url + " data-toggle='lightbox' data-gallery=gallery class='col-md-4'>";
				html += "<img class='photos img-fluid rounded' src='" + item.previewUrl + "' alt='" + item.fileName + "' style='min-height:300px;margin:auto;overflow:hidden;'>";
				html += "</a>";
				html += "</div></div>"
			}
			let pageResult = "Showing from " + result.start + " to " + result.end + " of " + result.total + " results";
			$(".page_amount p").text(pageResult);
			$("#picture-row").html(html);
			makePicturePages(result.pageCount);
		}
	});
}
document.getElementById("fileName").onchange = searchPicture;
document.getElementById("sort").onchange = getPictureBySort;
document.getElementById("uploadTime").onchange = getPictureByUploadTime;
document.getElementById("pageSize").onchange = getPictureByPageSize;

function addCurrent() {
	let picPages = document.getElementsByClassName(".pic_page");
	for (let pic of picPages) {
		if (pic.text() === thisPage)
			pic.addClass("current_page");
		else {
			pic.removeClass("current_page")
		}
	}
}


function getPage(page) {
	thisPage = page;
	searchPicture();
	addCurrent();
}
function getPictureBySort() {
	sort = $("#sort").val();
	console.log(sort);
	searchPicture();
}
function getPictureByUploadTime() {
	uploadTime = $("#uploadTime").val();
	console.log(uploadTime);
	thisPage = 1;
	searchPicture();
}
function getPictureByPageSize() {
	pageSize = $("#pageSize").val();
	console.log(pageSize);
	if (thisPage > total / pageSize) {
		thisPage = parseInt(total / pageSize) + 1;
	}
	searchPicture();
}
function makePicturePages(pageCount) {
	console.log(thisPage);
	console.log(pageCount);
	let pageHtml = "";
	if (thisPage !== 1) {
		pageHtml += "<li class='pic_page' onclick='getPage(1)'>" + "<<" + "</li>";
		pageHtml += "<li class='pic_page' onclick='getPage(" + (thisPage - 1) + ")'> pre </li>";
	}
	console.log(pageHtml);
	for (let i = 1; i <= pageCount; i++) {
		if (i === thisPage) {
			pageHtml += "<li class='pic_page current_page';' onclick='getPage(" + i + ")'" + ">" + i + "</li>";
		} else {
			pageHtml += "<li class='pic_page' onclick='getPage(" + i + ")'" + ">" + i + "</li>";
		}
	}
	if (thisPage !== pageCount) {
		pageHtml += "<li class='pic_page' onclick='getPage(" + (thisPage + 1) + ")'> next </li>";
		pageHtml += "<li class='pic_page' onclick='getPage(" + pageCount + ")'> >> </li>";
	}
	$("#page-ul").html(pageHtml);
}

$(document).on('click', '[data-toggle="lightbox"]', function (event) {
	event.preventDefault();
	$(this).ekkoLightbox();
});

$(".upload-button").on('click', function (e) {
	$(".box").show();
	$("#cover-all").show();
})


// <li class="current pic_page" onclick="getPage('1')">1</li>
//function searchPictureByfileName(){
//	fileName = $("#fileName").val();
//	console.log(fileName);
//	searchPicture();
//}
//function getPictureByASC() {
//	sort=$("#ASC").text();
//	console.log(sort);
//    searchPicture();
//}
//function getPictureByDESC() {
//	sort=$("#DESC").text();
//	console.log(sortp);
//    searchPicture();
//}
