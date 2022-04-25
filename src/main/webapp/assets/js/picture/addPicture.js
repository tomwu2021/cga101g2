
let files;

$(document).ready(() => {
    $('#file-zone').click((event) => {
        let id = $(event.target).attr('id');
        if (id === 'file-zone') {
            $('#file-btn').click();
        }
        return
    })

    $('#file-btn').change((event) => {
        let el = event.target;
        console.log(el.getAttribute('accept'));
        if (el.getAttribute('accept') === 'image/*') {
            files = el.files;
            buildPreviewSection(files);
        }
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
    console.log(temp)
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
        $('#upload-word').text('點擊圖片以刪除');
    } else {
        $('#btn-container').hide()
        $('#upload-word').text('點擊上傳');
    }
}
function deleteAllPreview() {
    let temp = [];
    files = temp // Assign the updates list
    console.log(temp)
    buildPreviewSection(files);
}

function cancel() {
	console.log("cancel!")
	$("#reset-button").click();
	deleteAllPreview();
}

function save(){
	console.log("save!");
	$("#save-button").click();
//	location.href="/CGA101G2/front/gallery.jsp";
}

//function save() {
//    //模擬form表單
//    let myform = new FormData();
//    for (let i = 0; i < files.length; i++) {
//        myform.append(`file${i}`, files[i]);
//        myform.append(`albumId`, 9);
//    }
//    $.ajax({
//        url: "/CGA101G2/PictureController",
//        data: myform,
//        processData: false,
//        contentType: false,
//        type: 'POST',
//        success: function (dataofconfirm) {
//            location.href = "/CGA101G2/front/gallery.html"
//        }
//    });
//}



//<script>
//    let files;
//
//    $(document).ready(() => {
//        $('#file-zone').click((event) => {
//            let id = $(event.target).attr('id');
//            if (id === 'file-zone') {
//                $('#file-btn').click();
//            }
//            return
//        })
//
//        $('#file-btn').change((event) => {
//            let el = event.target;
//            // fileList to Array
//            files = [...el.files];
//            if (el.files.length >= 10) {
//                alert('同時只能上傳10筆');
//                return
//            }
//
//            if (!isFileImage(files)) {
//                alert('格式不正確')
//                return
//            }
//            buildPreviewSection(files);
//        })
//    })
//
//    function isFileImage(files) {
//        let flag = true
//        files.forEach(file => {
//            if (file && file['type'].split('/')[0] !== 'image') {
//                return flag = false
//            }
//        })
//        return flag;
//    }
//
//    function deletePreview(key) {
//        files.splice(key, 1);
//        buildPreviewSection(files);
//    }
//
//    function buildPreviewSection(files) {
//        let previewHtml = '';
//        files.forEach((file, key) => {
//            const url = URL.createObjectURL(file);
//            previewHtml +=
//                `
//            <div class="col-lg-4 col-md-6" style="padding:10px;overflow:hidden;" id="${key}">
//                <div class="services_thumb img-wraps photos" style="width:400px;height:240px;margin:0px; auto;overflow:hidden;" >
//                    <span class="closes" title="Delete" onclick="deletePreview(${key})">×</span>
//                    <img class="img-fluid rounded" src="${url}" style="min-height:300px;margin:auto;overflow:hidden;">
//                </div>
//            </div>
//            `
//        })
//        $('#picture-row').html(previewHtml);
//        if (files.length > 0) {
//            $('#btn-container').show();
//        } else {
//            $('#btn-container').hide()
//        }
//    }
//
//    function save() {
//        //模擬form表單
//        let myform = new FormData();
//        files.forEach((file, key) => {
//            myform.append(`file${key}`, file);
//        })
//        myform.append(`albumId`, 9);
//
//        $.ajax({
//            url: "/CGA101G2/PictureController",
//            data: myform,
//            processData: false,
//            contentType: false,
//            type: 'POST',
//            success: function (dataofconfirm) {
//                location.href = "/CGA101G2/front/gallery.html"
//            }
//        });
//    }
//
//    function cancel() {
//
//    }
//</script>