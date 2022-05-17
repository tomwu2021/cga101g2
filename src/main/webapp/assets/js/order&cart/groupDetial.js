function chooseType(){
	let type=$("#choose").val();
	if(type==1){
		$("#min").attr("style", "display:none");
	}else if(type==2){
		$("#min").removeAttr("style");
	}
	
}
