/**
 *
 */
let previousFileName = "";
let fileName = "";
let uploadTime = 1;
let pageSize = 12;
let sort = "DESC";
let thisPage = 1;
let total = 0;
let pageCount = 0;
let coverId;



function searchPicture() {
	albumId = $("#albumId").val();
	console.log(albumId);
	console.log('sort=' + sort + '&uploadTime=' + uploadTime);
	$.get({
		url: getContextPath() + "/photos?fileName=" + fileName + "&albumId=" + albumId + "&thisPage=" + thisPage + "&order=upload_time&pageSize="
			+ pageSize + "&sort=" + sort + "&uploadTime=" + uploadTime + "&action=search",
		success: function(result, status) {
			total = result.total;
			pageCount = result.pageCount;
			let html = "";
			for (let item of result.items) {
				html += "<div class='col-lg-4 col-md-6' style='padding:10px;overflow:hidden;'>";

				html += " <div class='services_thumb' style='width:400px;height:240px';margin:0px auto;overflow:hidden;>";
				html += `<ul class="services_thumb_ul">
							<li>Title: ${item.fileName}</li>
							<li>Size: ${item.size}</li>
							<li>Create Time: ${item.createTime}</li>`
				if (item.isCover === 1) {
					coverId = item.pictureId;
					html += `<li class='bi bi-journal cover-selected' 
							style='height:50px;width:50px;position:absolute;left:320px;top:210px;font-size:30px;' 
							title="cover" id="cover${item.pictureId}">
							</li>`
				} else {
					html += `<li class='bi bi-trash trash-bucket' 
							style='height:50px;color:white;width:50px;position:absolute;left:5px;top:210px;font-size:30px;' 
							title="delete" onclick="addToDeleteList(${item.pictureId})">
							</li>
							<li class='bi bi-journal cover' 
							style='height:50px;width:50px;position:absolute;left:320px;top:210px;font-size:30px;' 
							title="cover" onclick="changeCover(${item.pictureId},${albumId})" id="cover${item.pictureId}">
							</li>`
				}
				html += `<li id="createTime${item.pictureId}" style="display:none">${item.createTime}</li>
						  </ul>`
				html += "<a href=" + item.url + " data-toggle='lightbox' data-gallery=gallery class='col-md-4'>";
				html += "<img id='" + item.pictureId + "' class='photos img-fluid rounded' src='" + item.previewUrl + "' alt='" + item.fileName + "' style='min-height:300px;margin:auto;overflow:hidden;'>";
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
$(document).ready(() => searchPicture());
document.getElementById("fileName").onchange = getByFileName;
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

function changeCover(pictureId, albumId) {
	loading();
	$.get({
		url: getContextPath() + "/album?albumId=" + albumId + "&action=changeCover&pictureId=" + pictureId,
		processData: false,
		contentType: false,
		success: function(result, status) {
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: '變更封面成功',
				showConfirmButton: false,
				timer: 1500
			})
			coverId = pictureId;
			searchPicture();
			offLoading();
		}
	})
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

function getByFileName() {
	fileName = $("#fileName").val() || "";
	console.log(fileName);
	thisPage = 1;
	searchPicture();
}
function makePicturePages(pageCount) {
	let pageHtml = "";
	if (thisPage !== 1) {
		pageHtml += "<li class='pic_page' onclick='getPage(1)'>" + "<<" + "</li>";
		pageHtml += "<li class='pic_page' onclick='getPage(" + (thisPage - 1) + ")'> pre </li>";
	}
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




//燈箱效果
$(document).on('click', '[data-toggle="lightbox"]', function(event) {
	event.preventDefault();
	$(this).ekkoLightbox();
});



//刪除圖片

let atd = 0;
let deleteList = [];
let deleteListHtml = "";

function addToDeleteList(pictureId) {
	if (!deleteList.includes(pictureId)) {
		deleteList.push(pictureId);
		if (atd === 0) {
			$("table").show();
			deleteListHtml += makeTr(pictureId);
			$('tbody').html(deleteListHtml);
			$('#button-area2').show();
			deleteListHtml = makeTr(pictureId);
		} else {
			deleteListHtml += makeTr(pictureId);
			$("tbody").html(deleteListHtml);
		}
		atd += 1;
	} else {
		alert('此相片已在刪除列表中');
	}
}

function makeTr(pictureId) {
	let imageName = $('#' + pictureId).attr('alt');
	let imageCreateTime = $('#createTime' + pictureId).text();
	let imageSrc = $('#' + pictureId).attr('src')
	return `<tr id="delete-list${pictureId}">
							<td class="product_name">${imageName}</td>
							<td class="product_thumb"><img src="${imageSrc}" style="height:80px;width:120px;margin:auto"></td>
							<td class="product-price">${imageCreateTime}</td>
							<td class="product_total" class="recover" onclick='restore(${pictureId})'><a>Restore</a></td>
						</tr>`
}

function restore(pictureId) {
	deleteList = deleteList.filter(function(item) {
		return item !== pictureId;
	});
	atd -= 1;
	if (atd === 0) {
		$('tbody>tr').remove();
		$('table').hide();
		$('#button-area2').hide();
		deleteListHtml = '';
	} else {
		$("#delete-list" + pictureId).remove();
		deleteListHtml -= makeTr(pictureId);
	}
}
function deleteAll() {
	$('tbody>tr').remove();
	$('table').hide();
	$('#button-area2').hide();
	deleteList = [];
	deleteListHtml = '';
	atd = 0;
}

function commitDelete() {
	loading();
	console.log("delete start")
	let data = new FormData();
	data.append("picList", JSON.stringify(deleteList));
	//pictureIdList = {picList:};
	//pictureIdList["picList"]=deleteList;
	//console.log(pictureIdList);
	$.ajax({
		type: "DELETE",
		url: getContextPath() + "/photos",
		data: data,
		processData: false,
		contentType: false,
		success: function(res, error) {
			console.log("res");
			thisPage = 1;
			searchPicture();
			deleteAll();
			offLoading();
		}
	});
}
function toAddPicture() {
	console.log(getContextPath());
	location.href = getContextPath() + "/photos?memberId=" + $("#memberId").val() + "&albumId=" + $("#albumId").val() + "&action=addShow";
}
function backToAlbums() {
	console.log($("#memberId").val());
	location.href = getContextPath() + "/album?memberId=" + $("#memberId").val() + "&action=list";
}
//

