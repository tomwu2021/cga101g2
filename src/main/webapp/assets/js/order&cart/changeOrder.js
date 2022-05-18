
	
	
let address=$('#address').val();
analyzeAddress();
	

$("#zipcode3").twzipcode({
	"zipcodeIntoDistrict": true,
	"css": ["city form-control", "town form-control"],
	countySel: "臺北市", // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
	districtSel: "大安區", // 地區預設值
});

function analyzeAddress() {
	console.log(address);
	let space = address.indexOf(" ") + 1; // 空格位置
	console.log(address);
	console.log(address.slice(0, 3)); // 市
	console.log(address.slice(3, space)); // 區
	console.log(address.slice(space, address.length));// 空格後面的字
	$("#zipcode3").twzipcode({
		"zipcodeIntoDistrict": true,
		"css": ["city form-control", "town form-control"],
		countySel: address.slice(0, 3),
		districtSel: address.slice(3, space),
	});
	$("#exampleFormControlInput1").val(address.slice(space, address.length));
}