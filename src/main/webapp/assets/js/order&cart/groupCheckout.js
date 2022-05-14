amountCount();

function amountCount(){
	var count=$("#count").val();
	$("#amount").text(count);
	var payPrice=(Number($("#amount").text())*Number($("#price").text()));
	$("#payPrice").text(payPrice);
	$("#total").text(payPrice);
}

