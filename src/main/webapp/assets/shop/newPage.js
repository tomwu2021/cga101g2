
// fetch DOM elements
let myPage = document.getElementById("myPage");
let myPageForm = document.getElementById("myPageForm");
// add event listeners
myPage.addEventListener('change', function() {
	if (myPage.value === '-1') {
		alert('Please select a value!');
	} else {
		// do something. submit form
		myPageForm.submit();
	}
});

