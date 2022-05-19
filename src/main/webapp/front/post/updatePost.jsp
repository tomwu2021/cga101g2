<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.post.model.*"%>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	修改貼文頁面 </title>

<!-- 共用的CSS start-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->
	 <!-- Main Style CSS -->
    <link rel="stylesheet" href="other/style copy.css">
  
<!-- 照片區 -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/shop/backProduct/updateProduct.css">
</head>

<body>
	<!-- 共用的header start-->
	<%@include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->
	
	    <!--offcanvas menu area start-->
    <div class="off_canvars_overlay">

    </div>
										   									        
    <!--shop  area start-->
	<div class="shop_area shop_reverse mt-100 mb-100">
        <div class="container">
            <div class="row">
                
                <!-- Form Elements -->                    
                    
						           	          
	              <form method="post" action="<%=request.getContextPath()%>/updatePost" enctype="multipart/form-data">
<!-- 	               我是照片區 開始 -->
						<div class="bob-container "> 
 								<div class="bob-row ">
 								<span style="color:#f33;">${errorMsgs.img}</span>
<c:if test="${postVO.pictureList.size() != 0 }">
<c:forEach var="pictureVO" items="${postVO.pictureList}"> 								
									<div class="bob-3item">
    									<img src="${pictureVO.url}"> 
      									<input type="hidden"  value="${pictureVO.pictureId}">
      									<button id ="deleteImg${pictureVO.pictureId}" type="button" class="btn btn-primary btn-sm" >
      									<i class="bi bi-x-lg"></i>刪除</button>
      									<button id ="cancelDeleteImg${pictureVO.pictureId}" value="${pictureVO.pictureId}"
      									type="button" class="btn btn-secondary btn-sm" >
      									<i class="bi bi-check-lg"></i>取消</button>
    								</div>
</c:forEach>
</c:if>    								
							</div>
						</div>
<!-- 	               我是照片區 結束 -->
	               
	               
	               
	                <div class="col-md-6">
	                	<!-- <input type="file" id="file-btn" accept="image/*" multiple="multiple" name="upfile"> -->
						<label for="inputNumber"
							class="col-md-6 col-form-label">圖片</label>
							<div class="col-lg-12">
							<img src="<%=request.getContextPath()%>/front/post/image/add-image.png" class="ml-3" style="height:300px;width:300px;" onclick="add()" id="addimage">
							<span style="color:#f33;">${errorMsgs.parts}</span>
							</div>						
					</div>		
					
					<div class="col-md-6">
					 
						<label for="inputPassword"
					 		class="col-lg-6 col-form-label">內容</label>
							<div class="col-lg-12">
							
							<textarea name="content" class="form-control" style="height:30vh;width:100%;resize:none;">${postVO.content}</textarea>
							
							<span style="color:#f33;">${errorMsgs.content}</span>
							
					   	</div>
					   	
					   	
					   	<div class="col-lg-6 ">
							<input type="hidden" name="action" value="updatepostcontent">	
							<input type="hidden" name="postId" value="${postVO.postId}">									
							<!-- <input type="submit" value ="修改" class="btn btn-success" style="position: fixed; bottom: 120px; right: 150px;"> -->
							<input type="submit" value="修改" class="btn btn-primary" style="position: absolute;bottom: 600px; left: 1000px;">		
						</div>
						</form>
						
				  </div>	
			</div>
		</div>
	</div>
		
	<!-- 共通的footer start-->
	<%@include file="/front/layout/footer.jsp"%>
	<!-- 共通的footer end-->


	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 共用的JS -->
	
<script type="text/javascript">
let count = 1;

function add(){
	$('#addimage').before('<div id="div'+count+'" style="float:left;position:relative;"><input name="img" accept="image/*" type="file" id="formFile'+count+'"></div>');
	$('#formFile'+count).hide();
		let Element = document.querySelector('#formFile'+count);
		Element.addEventListener('change', function() { 
		let url = URL.createObjectURL(Element.files[0]); 
		$(this).after('<img src="'+url+'" id="img'+ (count-1) +'" class="mb-3 ml-3" style="height:266px;width:400px;object-fit: cover;"><i class="far fa-window-close text-muted" id="btn'+ (count-1) +'" style="position:absolute;top:0;right:0;font-size:2em;" onclick="letItGo(this.id)"></i>');
	
		});
	document.querySelector('#formFile'+count).click();
		count++;
	}

function letItGo(btn){
	let select = btn.replace('btn','#div');
	$(select).remove();

}

</script>
<script
	src="<%=request.getContextPath()%>/assets/shop/backProduct/img/deleteImg.js">
</script>

</body>
</html>