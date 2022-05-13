$("[id^='inlineCheckbox1noChoose']").change(function() {
	console.log("被選擇的主分類 name值期望為inlineCheckbox1noChoose");
	let id = $(this).attr("id");
	console.log("被選中的button元素的id值 :" + id)
	
	let oldNname = $(this).attr("name");
	console.log("原本的name值為:" + oldNname)
	
	if(oldNname =='noChoose'){
		$(this).attr('name','sort1_id');
		let newName = $(this).attr('name');
		console.log("被改變後的的name值為(代表已勾選):" + newName)
	}
	
	if(oldNname =='sort1_id'){
		 $(this).attr('name','noChoose');
		let newName = $(this).attr('name');
		console.log("被改變後的的name值為(代表沒勾選):" + newName)
	}
	
	console.log($(this));
	});
	
	