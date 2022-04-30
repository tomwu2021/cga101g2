



//+ "&thisPage=" + thisPage + "&order=upload_time&pageSize="
//			+ pageSize + "&sort=" + sort + "&uploadTime=" + uploadTime

let previewSrc = "https://cga101-02.s3.ap-northeast-1.amazonaws.com/thumbs/newAlbum.png";
getPersonAlbum();
function getPersonAlbum() {
	let memberId = $("#memberId").val();
	console.log(memberId);
	let html = '';
	$.get({
		url: getContextPath() + "/album?memberId=" + memberId + "&action=getPersonAlbum",
		success: function(result, status) {
			for (let album of result) {
				html += makeAlbum(album);
				$("#album-container").html(html);
			}
		}
	})
}

function makeAlbum(album) {
	let html = '';
	let lockType = '';
	let lockTitle = ''
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
											<a id=${album.albumId} class="primary_img" href="${getContextPath()}/album?albumId=${album.albumId}&action=list&memberId=${$("#memberId").val()}&coverId=${album.coverId}">
											<img src="${album.pictureVO.previewUrl}" class="album-cover" alt="${album.pictureVO.fileName}"></a>
											<div class="action_links">
												<ul>
													<li class="quick_button album-button" onclick="editStart(${album.albumId})" id="editName" title="Rename Album">
													<i class="bi bi-pencil"></i></li>
													<li class="wishlist  album-button" onclick="deleteAlbum(${album.albumId})" id="deleteAlbum" title="Delete">
													<i class="bi bi-trash2"></i></li>											
													<li class="compare album-button"  onclick="changeAuthority(${album.albumId})" id="changeAuthority${album.albumId}" title=${lockTitle}>
													<i class="${lockType}" id="authority-button${album.albumId}"></i></li>
												</ul>
											</div>
										</div>
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
			url: getContextPath() + "/album?albumId=" + albumId + "&action=updateName&name=" + name2,
			processData: false,
			contentType: false,
			success: function(result, status) {
				console.log("相簿名稱修改成功");
			}
		})
	}
}

function deleteAlbum(albumId) {
	if (confirm("Do you want to delete " + $("#a" + albumId).text().trim() + " ? ")) {
		$.get({
			url: getContextPath() + "/album?albumId=" + albumId + "&action=deleteAlbum&memberId=" + $("#memberId").val(),
			processData: false,
			contentType: false,
			success: function(result, status) {
				console.log("相簿刪除成功");
				getPersonAlbum();
			}
		})
	}
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
		console.log("commit");
		$("#commit-new-album").click();
	} else {
		alert("請輸入相簿名稱並上傳封面");
	}
}

function changeAuthority(albumId) {
	let authHtml;
	if ($("#authority-button" + albumId).hasClass("bi bi-lock")) {
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
		url: getContextPath() + "/album?albumId=" + albumId + "&action=updateAuthority&authority=" + authority,
		processData: false,
		contentType: false,
		success: function(result, status) {
			console.log("相簿權限修改成功");
		}
	})
}
//<input type="file" accept="image/*" id="new-album-cover" style="display:none;">
//						<input type="text" id="new-album-name">
//						<div class="container defined-btn" id="btn-container">
//							<div class="product_tab_btn">
//								<ul class="nav" role="tablist">
//									<li onclick="uploadCover()"><a data-toggle="tab">Cover</a></li>
//									<li onclick="commitAlbum()"><a data-toggle="tab">Save</a></li>
//								</ul>
//							</div>
//						</div>