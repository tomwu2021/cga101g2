/**



function search() {

	$.get({
		
		url: "/CGA101G2/PictureController?fileName=" + fileName + "&thisPage=" + thisPage + "&order=" + order + "&pageSize="
			+ pageSize + "&sort=" + sort + "&time=" + time + "&action=search",  

		//url後面為分頁查詢必須的條件 後面可再加屬於自己方法的條件
		
		success: function(result, status) {

			//接回查詢的結果
		}
		
		let pageResult = "Showing from " + result.start + " to " + result.end + " of " + result.total + " results";
		//顯示當前頁面為第幾筆到第幾筆,並顯示總筆數
		
		$("#picture-row").html(html); 
		
		// 把接回的結果塞到頁面上
		
		makePicturePages(result.pageCount);
		//利用搜到的筆數製造對應數量分頁
}
	});
}
 * 
 */
// function search(){
	//發出查詢請求的方法 jquery ajax參考上面實作
	
// }
//預設進頁面時的查詢條件


let pageSize = $("#pageSize").val()||12;
let sort = "DESC";
let thisPage = 1;
let total = 0;
let pageCount = 0;

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
	search();
	addCurrent();
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