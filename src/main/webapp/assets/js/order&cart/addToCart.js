function addToCart(productId){

	let productName = $(`#pname-${productId}`).val();
	let productPrice = $(`#pprice-${productId}`).val();
	let productPictureUrl = $(`#purl-${productId}`).val();
	let productAmout = $(`#pamout-${productId}`).val();
	let action ="ADD";

let data={
		productId:productId,
		productName:productName,
		productPrice:productPrice,
		productPictureUrl:productPictureUrl,
		productAmout:productAmout,
		action:action
}
	
	JSON.stringify(data);
	$.ajax({
		url:getContextPath()+"/member/cart.do",
		type:"post",
		data:data,
		success:function(result,status){
			JSON.parse(result);
			   	Swal.fire({
                position: 'center',
                icon: 'success',
                title:"成功加入購物車",
                showConfirmButton: false,
                timer: 1500
            })
		
			
			}
		})
		
	
}

