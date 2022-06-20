
let previousFileName = "";
let fileName = "";
let uploadTime = $("#uploadTime").val() || 30;
let memberId = $("#memberId").val();
let order = "uploadTime";

//+ "&thisPage=" + thisPage + "&order=upload_time&pageSize="
//			+ pageSize + "&sort=" + sort + "&uploadTime=" + uploadTime
document.getElementById("fileName").onchange = getByFileName;
document.getElementById("sort").onchange = getBySort;
document.getElementById("uploadTime").onchange = getByUploadTime;
document.getElementById("pageSize").onchange = getByPageSize;

function getBySort() {
	sort = $("#sort").val();
	console.log(sort);
	search();
}
function getByUploadTime() {
	uploadTime = $("#uploadTime").val();
	console.log(uploadTime);
	thisPage = 1;
	search();
}
function getByPageSize() {
	pageSize = $("#pageSize").val();
	console.log(pageSize);
	if (thisPage > total / pageSize) {
		thisPage = parseInt(total / pageSize) + 1;
	}
	search();
}
function getByFileName() {
	fileName = $("#fileName").val() || "";
	console.log(fileName);
	thisPage = 1;
	search();
}

let previewSrc = "https://cga101-02.s3.ap-northeast-1.amazonaws.com/thumbs/newAlbum.png";

$(document).ready(()=>{
	
	search();
	
})

function search() {

	console.log(memberId);
	$.get({
		url: getContextPath() + "/album?memberId=" + memberId + "&action=getPersonAlbum" + "&fileName=" + fileName + "&thisPage=" + thisPage + "&order=create_time&pageSize="
			+ pageSize + "&sort=" + sort + "&uploadTime=" + uploadTime,
		success: function(result, status) {
			total = result.total;
			pageCount = result.pageCount;
			let html = "";
			for (let album of result.items) {
				html += makeAlbum(album);
			}
			let pageResult = "Showing from " + result.start + " to " + result.end + " of " + result.total + " results";
			$(".page_amount").text(pageResult);
			$("#album-container").html(html);
			makePicturePages(result.pageCount);
			offLoading();
		}
	})
}

function makeAlbum(album) {
	let html = '';
	let lockType = '';
	let lockTitle = '';
	let isOwner = parseInt($("#isOwner").val() || 0);
	if (album.authority === 1) {
		lockType = 'bi bi-lock';
		lockTitle = 'lock'
	} else {
		lockType = 'bi bi-unlock';
		lockTitle = 'unlock';
	}
	html += `<div class="col-lg-4 col-md-4 col-sm-6">
							<article class='single_product album_thumb'>
								<figure>
										<div class="product_thumb">
											<a id=${album.albumId} class="primary_img" href="${getContextPath()}/photos?albumId=${album.albumId}&action=list&memberId=${$("#memberId").val()}">
											<img src="${album.pictureVO.previewUrl}" class="album-cover" alt="${album.pictureVO.fileName}"></a>`
	if (isOwner === 1) {
		html += `<div class="action_links">
												<ul>
													<li class="quick_button album-button" onclick="editStart(${album.albumId})" id="editName" title="Rename Album">
													<i class="bi bi-pencil"></i></li>
													<li class="wishlist  album-button" onclick="deleteAlbum(${album.albumId})" id="deleteAlbum" title="Delete">
													<i class="bi bi-trash2"></i></li>											
													<li class="compare album-button"  onclick="changeAuthority(${album.albumId})" id="changeAuthority${album.albumId}" title=${lockTitle}>
													<i class="${lockType}" id="authority-button${album.albumId}"></i></li>
												</ul>
											</div>`
	}
	html += `</div>
										<div class="product_content grid_content" onclick="openAlbum(${album.albumId})">
											<h4 class="product_name" style="font-size: 1.5em"
												style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif">
												<a id="a${album.albumId}"><div class="album-name" onblur="edit(${album.albumId})" >${album.name}</div></a>
											</h4>
											<div class="price_box" style="text-align: right;">
												<span class="current_price" style="margin: 20px 0 0 0;">${album.createTime}</span>
											</div>
										</div>
									</figure>
								</article>
							</div>`;
	return html;
}

let name = '';
function editStart(albumId) {
	name = $("#a" + albumId).text().trim();
	$("#a" + albumId + ">div").attr("contenteditable", true);
	$("#a" + albumId + ">div").focus();
}
function edit(albumId) {
	let name2 = $("#a" + albumId).text().trim();
	if (name2 !== name) {
		console.log("update start")
		$.get({
			url: getContextPath() + "/album?albumId=" + albumId + "&action=updateName&name=" + name2 + "&memberId=" + memberId,
			processData: false,
			contentType: false,
			success: function(result, status) {
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: '變更名稱成功',
					showConfirmButton: false,
					timer: 1500
				})
			}
		})
	}
}

function deleteAlbum(albumId) {

	Swal.fire({
		title: 'Are you sure?',
		text: "Do you want to delete " + $("#a" + albumId).text().trim() + " ? ",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#d33',
		cancelButtonColor: 'darkgrey',
		confirmButtonText: 'Yes, delete it!'
	}).then((result) => {
		if (result.isConfirmed) {
			loading();
			$.get({
				url: getContextPath() + "/album?albumId=" + albumId + "&action=deleteAlbum&memberId=" + $("#memberId").val(),
				processData: false,
				contentType: false,
				success: function(result, status) {
					Swal.fire(
						'Deleted!',
						'Your album has been deleted.',
						'success'
					)
					search();
					offLoading();
				}
			})
		}
	})
}


function openAlbum(albumId) {
	$("#" + albumId).click();
}
function addAlbum() {
	if ($("#addZone").hasClass('hideZone')) {
		$("#create-button").text("Cancel");
		$("#addZone").removeClass("hideZone");
		$("#addZone").addClass("showZone");
		$("#new-album-form").show();
	} else {
		$("#create-button").text("Create New Album");
		$("#addZone").removeClass("showZone");
		$("#addZone").addClass("hideZone");
		$("#new-album-form").hide();
		cancelCreate();
	}
}

function cancelCreate() {
	inputFiles = [];
	let previewHtml = "<img src=" + previewSrc + ">"
	$("#cover-preview").html(previewHtml);
	$("#new-album-name").val('');
	$("#new-album-name").text('');
}
function uploadCover() {
	$("#new-album-cover").click();
}
let inputFiles = [];
function createCoverPreview(files) {
	inputFiles = [...files];
	let file = files[0];
	let previewHtml = ''
	if (inputFiles.length > 0) {
		const url = URL.createObjectURL(file);
		previewHtml = "<img src=" + url + ">";
	} else {
		previewHtml = "<img src=" + previewSrc + ">";
	}
	$("#cover-preview").html(previewHtml);
}

function commitAlbum() {
	let albumName = $("#new-album-name").val();
	if (inputFiles.length === 1 && albumName !== null && albumName.trim() !== "") {
		loading();
		console.log("commit");
		$("#commit-new-album").click();
	} else {
			Swal.fire({
				icon: 'error',
				title: 'Oops...',
				text: '請輸入相簿名稱並上傳封面!'
			});
	}
}

function changeAuthority(albumId) {
	let authHtml;
	loading();
	if ($("#authority-button" + albumId).hasClass("bi-lock")) {
		authority = 0;
		console.log("unlock");
		authHtml = "<i class='bi bi-unlock' id='authority-button'  title='unlock'></i>";
	} else {
		authority = 1;
		console.log("lock");
		authHtml = "<i class='bi bi-lock' id='authority-button' title='lock'></i>";
	}
	$("#changeAuthority" + albumId).html(authHtml);
	$.get({
		url: getContextPath() + "/album?albumId=" + albumId + "&action=updateAuthority&authority=" + authority + "&memberId=" + memberId,
		processData: false,
		contentType: false,
		success: function(result, status) {
			offLoading();
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: '變更權限成功',
				showConfirmButton: false,
				timer: 1500
			})
		}
	})
}