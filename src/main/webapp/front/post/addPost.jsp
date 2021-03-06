<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.post.model.*"%>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	發文頁面 </title>

<!-- 共用的CSS start-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->
	 <!-- Main Style CSS -->
    <link rel="stylesheet" href="other/style copy.css">

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
                    <!-- <div class="col-12" style="margin: auto">  -->
                    
                	<!-- <div class="col-md-3">
                	放入一個buttom
					<br/>
					</div> -->
					
	               
	               
	                <div class="col-md-6">
	                	 <form method="post" action="<%=request.getContextPath()%>/addPost" enctype="multipart/form-data">
	                	 
	                	 <input type="hidden" id="memberId" value="<%=request.getAttribute("memberId")%>">
	                	 
	                	<!-- <input type="file" id="file-btn" accept="image/*" multiple="multiple" name="upfile"> -->
						<label for="inputNumber"
							class="col-md-6 col-form-label">圖片</label>
							<div class="col-lg-12">
							<img src="<%=request.getContextPath()%>/front/post/image/add-image.png" class="ml-3" style="height:300px;width:300px;" onclick="add()" id="addimage">
							
							<span style="color:#f33; position: relative;top: 180px;right: 300px;">${errorMsgs.parts}</span>
							</div>						
					</div>		
					
					<div class="col-md-6">
					 
						<label for="inputPassword"
					 		class="col-lg-6 col-form-label">內容</label>
							<div class="col-lg-12">
							<textarea name="content" class="form-control" style="height:30vh;width:100%;resize:none;">${param.content}</textarea>
							<div style="color:#f33; transform: translateX(10px) translateY(30px); ">${errorMsgs.content}</div>
							<%-- <input type="TEXT" name="content" size="90" value="${param.content}"/>${errorMsgs.content} --%>
					   	</div>
					   	
					   	<div class="col-lg-6 ">
							<input type="hidden" name="action" value="insert">									
							<input type="submit" value="發文" class="btn btn-primary" style="position: absolute;top: 140px; right: -265px;">
								
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
	$('#addimage').before('<div id="div'+count+'" style="float:left;position:relative;"><input name="parts" accept="image/*" type="file" id="formFile'+count+'"></div>');
	$('#formFile'+count).hide();
		let Element = document.querySelector('#formFile'+count);
		Element.addEventListener('change', function() { 
		let url = URL.createObjectURL(Element.files[0]); 
		$(this).after('<img src="'+url+'" id="img'+ (count-1) +'" class="mb-3 ml-3" style="height:266px;width:400px;object-fit: cover;"><i class="bi bi-x" id="btn'+ (count-1) +'" style="position:absolute;top:0;right:0;font-size:2em;" onclick="letItGo(this.id)"></i>');
	
		});
	document.querySelector('#formFile'+count).click();
		count++;
	}

function letItGo(btn){
	let select = btn.replace('btn','#div');
	$(select).remove();

}

</script>


</body>
</html>