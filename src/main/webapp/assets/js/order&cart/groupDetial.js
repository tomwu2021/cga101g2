choose();
amountCount();

function choose(){
	if($("#choose").val()===2){
		$("#amount").attr("readonly","readonly");
	}else{
		$("#amount").removeAttr("readonly");
	}
	
}

function amountCount(){
	var count=$("#count").val();
	$("#amount").text(count);
	var payPrice=(Number($("#amount").text())*Number($("#price").text()));
}

