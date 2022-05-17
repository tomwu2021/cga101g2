// fetch DOM elements
let myPage = document.getElementById("myPage");
let myPageForm = document.getElementById("myPageForm");
// do something. submit form
let product_id = document.getElementById("exampleDataListproduct_id");
let product_name = document.getElementById("exampleDataList2product_name");

var sort1_id = [];
$("[name ='sort1_id']").each(function() {
	sort1_id.push($(this).val());
});
//alert("您喜歡的水果 : " + sort1_id.join());

let sort2_id = document.getElementById("inlineFormCustomSelectSort2_id");
let status = document.getElementById("inlineFormCustomSelectStatus");
let top_status = document.getElementById("inlineFormCustomSelectTop_status");
let startTime = document.getElementById("f_date1");
let endTime = document.getElementById("f_date2");

if (startTime === null && endTime === null) {
	let startTime = document.getElementById("f_date3");
	let endTime = document.getElementById("f_date4");
}

let action = document.getElementById("action");

console.log(product_name);
console.log(sort1_id);
console.log(sort2_id);
console.log(top_status);
console.log(status);
console.log(startTime);
console.log(endTime);
console.log(action);
console.log(myPage);
console.log(myPageForm);

// add event listeners
myPage.addEventListener('change', function() {
	if (myPage.value === '-1') {
		alert('Please select a value!');
		// fetch DOM elements

	} else {
		// fetch DOM elements
		let myPage = document.getElementById("myPage");
		let myPageForm = document.getElementById("myPageForm");
		// do something. submit form
		let product_id = document.getElementById("exampleDataListproduct_id");
		let product_name = document.getElementById("exampleDataList2product_name");

		var sort1_id = [];
		$("[name ='sort1_id']").each(function() {
			sort1_id.push($(this).val());
		});
		//alert("您喜歡的水果 : " + sort1_id.join());

		let sort2_id = document.getElementById("inlineFormCustomSelectSort2_id");
		let status = document.getElementById("inlineFormCustomSelectStatus");
		let top_status = document.getElementById("inlineFormCustomSelectTop_status");
		let startTime = document.getElementById("f_date1");
		let endTime = document.getElementById("f_date2");

		if (startTime === null && endTime === null) {
			let startTime = document.getElementById("f_date3");
			let endTime = document.getElementById("f_date4");
		}

		let action = document.getElementById("action");
//		alert('現在的的myPage.value值' + myPage.value + '確認後提交');
		myPageForm.submit();
	}
});



//let selectProduct = document.getElementById("selectProduct");
//
//selectProduct.addEventListener('click', function() {
//	let myPage = document.getElementById("myPage");
//	if (myPage.value === '-1') {
//		alert('Please select a value!');
//	}
//
//	else if (parseInt(myPage.value) > 1) {
//
//		// do something. submit form
//		let product_id = document.getElementById("exampleDataListproduct_id");
//		let product_name = document.getElementById("exampleDataList2product_name");
//
//		var sort1_id = [];
//		$("[name ='sort1_id']").each(function() {
//			sort1_id.push($(this).val());
//		});
//		//alert("您喜歡的水果 : " + sort1_id.join());
//
//		let sort2_id = document.getElementById("inlineFormCustomSelectSort2_id");
//		let status = document.getElementById("inlineFormCustomSelectStatus");
//		let top_status = document.getElementById("inlineFormCustomSelectTop_status");
//		let startTime = document.getElementById("f_date1");
//		let endTime = document.getElementById("f_date2");
//
//		if (startTime === null && endTime === null) {
//			let startTime = document.getElementById("f_date3");
//			let endTime = document.getElementById("f_date4");
//		}
//
//		let action = document.getElementById("action");
//
//		let myPage = document.getElementById("myPage");
//		let myPageForm = document.getElementById("myPageForm");
//
//		console.log(myPage.value);
//		alert('現在的的myPage.value值' + myPage.value + '確認後提交');
//		myPage.value = "1";
//		alert('更換按鈕提交的myPage.value值' + myPage.value + '確認後提交');
//		myPageForm.submit();
//	}
//});

//selectProduct.addEventListener('click', function() {
//	if (parseInt(myPage.value) > 1) {
//		console.log(myPage.value);
//		alert('按鈕提交的頁數大於1!');
//	}
//	else {
//		// do something. submit form
//		myPageForm.submit();
//	}
//});
