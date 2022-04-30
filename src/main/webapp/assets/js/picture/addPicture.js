
let files;
$(document).ready(() => {
	$('#file-zone').click((event) => {
		let id = $(event.target).attr('id');
		if (id === 'file-zone' || id === 'upload-word') {
			$('#file-btn').click();
		}
		return
	})

	$('#file-btn').change((event) => {
		let el = event.target;
		console.log("event.target:" + el);
		// fileList to Array
		files = [...el.files];
		if (el.files.length > 10) {
			alert('同時只能上傳10筆');
			return
		}

		if (!isFileImage(files)) {
			alert('格式不正確')
			return
		}
		buildPreviewSection(files);
	})
})

function deletePreview(key) {
	let temp = [];
	for (let i = 0; i < files.length; i++) {
		const file = files[i]
		if (key !== i)
			temp.push(file) // here you exclude the file. thus removing it.
	}

	files = temp // Assign the updates list
	console.log("files:" + files);
	buildPreviewSection(files);
}

function buildPreviewSection(files) {
	let previewHtml = '';
	for (let key = 0; key < files.length; key++) {
		const url = URL.createObjectURL(files[key]);
		previewHtml +=
			` <div class="col-lg-4 col-md-6" style="padding:10px;overflow:hidden;" id="${key}">
         <div class="services_thumb img-wraps photos" style="width:400px;height:240px;margin:0px; auto;overflow:hidden;" onclick="deletePreview(${key})">
             <span class="closes" title="Delete">×</span>
             <img class="img-fluid rounded" src="${url}" style="min-height:300px;margin:auto;overflow:hidden;">
         </div>
     </div>
     `
	}
	$('#picture-row').html(previewHtml);
	if (files.length > 0) {
		$('#btn-container').show();
		$('#upload-word').text('點擊圖片取消上傳')
		$('.shop_toolbar_wrapper>form').css("color", "white");
		$('.shop_toolbar_wrapper>form').css("font-weight", "700");
		$('.shop_toolbar_wrapper').css("background", "red");
	} else {
		$('#btn-container').hide()
		$('#upload-word').text('點擊上傳');
		$('.shop_toolbar_wrapper>form').css("color", "black");
		$('.shop_toolbar_wrapper>form').css("font-weight", "550");
		$('.shop_toolbar_wrapper').css("background", "white");
	}
}
function deleteAllPreview() {
	let temp = [];
	files = temp // Assign the updates list
	console.log(temp)
	buildPreviewSection(files);
}


function getFileListItems(files) {
	let fileList = new DataTransfer();
	files.forEach(file => {
		fileList.items.add(file);
		console.log("file:" + file);
	})
	console.log(fileList);

	return fileList.files
}

function save() {
	// arraylist to FilesList
	$('#upload-input').files = getFileListItems(files);
	console.log(files);
	$("#save-button").click();
}

//function cancel() {
//	location.href = getContextPath() + "/PictureController?action=list&albumId=" + $('#albumId').val() + "&memberId=" + $("memberId").val();
//}
function cancel() {
	deleteAllPreview();
}

function isFileImage(files) {
	let flag = true
	files.forEach(file => {
		if (file && file['type'].split('/')[0] !== 'image') {
			return flag = false
		}
	})
	return flag;
}
function toGallery() {
	console.log(getContextPath());

	location.href = getContextPath() + "/PictureController?action=list&albumId=" + $('#albumId').val() + "&memberId=" + $("#memberId").val();
}
console.log($('#albumId').attr('value'));
//取消新增預覽Bug 消除
//let files;
//
//$(document).ready(() => {
//    $('#file-zone').click((event) => {
//        let id = $(event.target).attr('id');
//        if (id === 'file-zone') {
//            $('#file-btn').click();
//        }
//        return
//    })
//
//    $('#file-btn').change((event) => {
//        let el = event.target;
//        // fileList to Array
//        files = [...el.files];
//        if (el.files.length >= 10) {
//            alert('同時只能上傳10筆');
//            return
//        }
//
//        if (!isFileImage(files)) {
//            alert('格式不正確')
//            return
//        }
//        buildPreviewSection(files);
//    })
//})
//
//function isFileImage(files) {
//    let flag = true
//    files.forEach(file => {
//        if (file && file['type'].split('/')[0] !== 'image') {
//            return flag = false
//        }
//    })
//    return flag;
//}
//
//function deletePreview(key) {
//    files.splice(key, 1);
//    buildPreviewSection(files);
//}
//
//function buildPreviewSection(files) {
//    let previewHtml = '';
//    files.forEach((file, key) => {
//        const url = URL.createObjectURL(file);
//        previewHtml +=
//            `
//            <div class="col-lg-4 col-md-6" style="padding:10px;overflow:hidden;" id="${key}">
//                <div class="services_thumb img-wraps photos" style="width:400px;height:240px;margin:0px; auto;overflow:hidden;" >
//                    <span class="closes" title="Delete" onclick="deletePreview(${key})">×</span>
//                    <img class="img-fluid rounded" src="${url}" style="min-height:300px;margin:auto;overflow:hidden;">
//                </div>
//            </div>
//            `
//    })
//    $('#picture-row').html(previewHtml);
//    if (files.length > 0) {
//        $('#btn-container').show();
//    } else {
//        $('#btn-container').hide()
//    }
//}
//
//function save() {
//    // arraylist to FilesList
//    $('#upload-input')[0].files = getFileListItems(files);
//    $('#main-form').submit();
//}
//
//function cancel() {
//    location.href = "/cga101g2/picture?albumId=" + $('#albumId').val();
//}
//
///**
// * array to fileList
// */
//function getFileListItems (files) {
//    let fileList = new DataTransfer();
//    files.forEach(file=>{
//        fileList.items.add(file)
//    })
//    return fileList.files
//}
