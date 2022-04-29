<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>新增商品</title>
<!-- 共用的CSS startr-->
<%@include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<!-- 	路徑舉例 -->
<%-- <link rel="stylesheet"href="<%=request.getContextPath()%>/assets/back/css/????.css"> --%>
<!-- 額外添加的CSS -->
</head>
<body>
	<!-- 共用的header start-->
	<%@include file="/back/layout/header.jsp"%>
	<!-- 共用的header end-->

	<!-- 共用的leftnav start-->
	<%@include file="/back/layout/leftnav.jsp"%>
	<!-- 共用的leftnav end-->

	<!--! ========內容開始======== -->
<!-- 	筆記 小提示 -->
<!-- 	<small class="form-text text-muted ml-3">限制20個字</small>
       新增時用的預設值placeholder="拿取資料value"
 -->
	
	
	<div class="page-holder w-100 d-flex flex-wrap">
		<div class="container-fluid px-xl-5">
			<section>
				<div class="page-holder w-100 d-flex flex-wrap">
					<div class="container-fluid px-xl-5">
						<section class="py-5">
							<div class="row">
							
							 <!--! Horizontal Form-->
                  <div class="col-lg-12 mb-5">
                    <div class="card">
                      <div class="card-header">
                        <h3 class="h6 text-uppercase mb-0">新增商品</h3>
                      </div>
                      <div class="card-body">
                        <p>商品編號???  最後更新時間:????</p>
                        <div class="row">
                        <div class="col-lg-6 mb-5">
                        <form class="form-horizontal">
                        
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label">商品名稱</label>
                            <div class="col-md-6">
                              <input id="inputHorizontalSuccess" type="text"  value="美國短毛貓" name="productName" placeholder="拿取資料value"  class="form-control form-control-success"> <small class="form-text text-muted ml-3">限制20個字</small>
                            </div>
                          </div>
                        
                          
                          <div class="form-group row">
                            <label class="col-md-3 form-control-label">商品價格</label>
                            <div class="col-md-6">
                              <input id="inputHorizontalSuccess" type="number" value=""  name="amount" placeholder="1" step="1" min="1" max="999"  class="form-control form-control-success">
                           </div>
                          </div>
                          
                             <div class="form-group row">
                            <label class="col-md-3 form-control-label">商品數量</label>
                            <div class="col-md-6">
                              <input id="inputHorizontalSuccess" type="number" value="" name="amount" placeholder="1" step="1" min="1" max="999"  class="form-control form-control-success">
                           </div>
                          </div>
                          
                           <div class="form-group row">
                            <label class="col-md-3 form-control-label">商品敘述</label>
                            <div class="col-md-6">
                            <textarea id="inputHorizontalSuccess" name="description"  rows="5" cols="90" placeholder="輸入商品內容"  class="form-control form-control-success">
                            </textarea>
							<small class="form-text text-muted ml-3">限制200個字</small>
                            </div>
                          </div>
                          
                           <div class="form-group row">
                            <label class="col-md-3 form-control-label">團購起始價格</label>
                            <div class="col-md-6">
                              <input id="inputHorizontalSuccess" type="number" name="groupPrice1" placeholder="1" step="1" min="1" max="999"  class="form-control form-control-success">
                           </div>
                          </div>
                          
                           <div class="form-group row">
                            <label class="col-md-3 form-control-label">團購數量級距一</label>
                            <div class="col-md-6">
                              <input id="inputHorizontalSuccess" type="number" name="groupAmount1" placeholder="1" step="1" min="1" max="999"  class="form-control form-control-success">
                           </div>
                          </div>
                          
                            <div class="form-group row">
                            <label class="col-md-3 form-control-label">團購數量級距二</label>
                            <div class="col-md-6">
                              <input id="inputHorizontalSuccess" type="number" name="groupAmount2" placeholder="1" step="1" min="1" max="999"  class="form-control form-control-success">
                           </div>
                          </div>
                          
                             <div class="form-group row">
                            <label class="col-md-3 form-control-label">團購數量級距三</label>
                            <div class="col-md-6">
                              <input id="inputHorizontalSuccess" type="number" name="groupAmount3" placeholder="1" step="1" min="1" max="999"  class="form-control form-control-success">
                           </div>
                          </div>
                          
                          
                          <div class="form-group row">
                            <label class="col-sm-3 form-control-label">Password</label>
                            <div class="col-md-9">
                              <input id="inputHorizontalWarning" type="password" placeholder="Pasword" class="form-control form-control-warning"><small class="form-text text-muted ml-3">Example help text that remains unchanged.</small>
                            </div>
                          </div>
                          <div class="form-group row">
                            <label class="col-sm-3 form-control-label">密碼</label>
                            <div class="col-md-9">
                              <label class="col-sm-9 form-control-label">*****777 <a href="#">變更</a></label>
                              <!-- !看會員提交圖片的css css/style.default.css 4195-->
                            </div>
                          </div>
                            <!--! 提交按鈕 -->
                          <div class="form-group row">       
                            <div class="col-md-9 ml-auto">
                              <input type="submit" value="儲存" class="btn btn-primary">
                            </div>
                          </div>
                        </form>  
                            <!--! 提交按鈕結束 -->
                      </div>
                       <!-- !看會員提交圖片的css css/style.default.css 4195-->
                      <div class="col-lg-6 mb-5"> 
                       <div class="head_div">
                         <div class="head_div imgdiv">
                           <img src="https://picsum.photos/id/237/200/300">
                         </div>
                         <label class="btn btn-primary">
                          <input id="upload_img" style="display:none;" type="file">
                          <i class="fa fa-photo"></i> 上傳圖片
                          </label>
                         <small class="form-text text-muted ml-3">檔案大小:最大 1 MB</small>
                         <small class="form-text text-muted ml-3">檔案限制: .JPEG, .PNG</small>
                       </div>
                      </div>
                    </div>
                  </div>
                       <!--! Horizontal Form結束-->
							
							
							
							</div>
						</section>
					</div>
				</div>
			</section>
			<!--! ========內容結束========-->


			<!-- 共通的footer start-->
			<%@include file="/back/layout/footer.jsp"%>
			<!-- 共通的footer end-->
		</div>
	</div>


	<!-- 共用的JS -->
	<%@include file="/back/layout/commonJS.jsp"%>
	<!-- 共用的JS -->

	<!-- 額外添加的JS -->
	<!-- 	路徑舉例 -->
	<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/back/js/?????.js"> --%>
	<!-- 額外添加的JS -->

</body>

</html>