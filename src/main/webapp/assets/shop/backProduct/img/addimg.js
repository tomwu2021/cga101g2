let count = 1;

function add(){
	$('#addimage').before('<div id="div'+count+'" style="float:left;position:relative;"><input name="img" accept="image/*" type="file" id="formFile'+count+'"></div>');
	$('#formFile'+count).hide();
		let Element = document.querySelector('#formFile'+count);
		Element.addEventListener("change", function() { 
		let url = URL.createObjectURL(Element.files[0]); 
		$(this).after('<img src="'+url+'" id="img'+ (count-1) +'" class="mb-3 ml-3" style="height:266px;width:400px;object-fit: cover;"><i class="far fa-window-close text-muted" id="btn'+ (count-1) +'" style="position:absolute;top:0;right:0;font-size:2em;" onclick="letItGo(this.id)"></i>');
	
		});
	document.querySelector('#formFile'+count).click();
		count++;
	}

function letItGo(btn){
	let select = btn.replace('btn','#div');
	$(select).remove();

}
