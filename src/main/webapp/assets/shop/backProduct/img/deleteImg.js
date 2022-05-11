/**
 * 監聽img button的 click事件 
 * 改變name & 增加特效
 */
 $("[id^='deleteImg']").click(function thisfunction() {
	console.log("選擇刪除的照片 name值期望為deleteImg");
	let id = $(this).attr("id");
	console.log("被選中的button元素的id值 :" + id)
	
	$(this).prev().attr('name','deleteImg');
	let name = $(this).prev().attr("name");
	console.log("被改變的的input元素的name值 :" + name)
	
	console.log($(this).prev().prev());
//	console.log($(this).parent());
	
//	用attr()方法设置class属性,是一个覆盖的过程;而addClass()则是一个追加的过程.
	$(this).prev().prev().attr('style','-webkit-filter:brightness(30%);');
});
 
 $("[id^='cancelDeleteImg']").click(function thisfunction() {
	console.log("取消剛剛選擇刪除的照片 name值期望為空");
	let id = $(this).attr("id");
	console.log("被選中的button元素的id值 :" + id)
	
	$(this).prev().prev().attr('name',' ');
	let name = $(this).prev().attr("name");
	console.log("被改變的的button元素的name值 :" + name)
	
	console.log($(this).prev().prev().prev());
	$(this).prev().prev().prev().attr('style',' ');
}); 