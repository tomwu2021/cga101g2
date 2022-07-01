
function loginButton() {

	const account = document.querySelector('#username');
	const password = document.querySelector('#password');

	let dataJSON = {
		account: account.value,
		password: password.value
	}
	$.ajax(
		{
			url: "http://localhost:8081/cga101g2/member/login",
			type: "post",
			data: dataJSON,
			success: function(json) {
				let objectJSON = JSON.parse(json);
				console.log(objectJSON.message);
			}
		}
	);
}
