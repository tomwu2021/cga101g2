//choose();

function choose(){
	if($("#choose").val()===1){
		$("#amount").attr("display","none");
	}else{
		$("#amount").removeAttr("display");
	}
	
}
