function cartChangeCount(sum){

	let count = $(`#pamout-${sum}`).val();
	let action ="ADDINCART";

let data={
		count:count,
		sum:sum,
		action:action
}
	
	JSON.stringify(data);
	$.ajax({
		url:getContextPath()+"/member/cart.do",
		type:"post",
		data:data,
		success:function(result,status){
			    $(`#ptotal-${sum}`).text(result);
			}
		})
			
}

function deleteOne(del){

	let action ="DELETE";

let data={		
		del:del,
		action:action
}
	
	JSON.stringify(data);
	$.ajax({
		url:getContextPath()+"/member/cart.do",
		type:"post",
		data:data,
		success:function(result,status){
			var remove = document.getElementById(del);
			  remove.remove();
			}
		})
			
}

