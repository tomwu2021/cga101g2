<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!-- include <head></head> -->

<style>
.f3 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(15% - 10px)
}

.f4 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(20% - 10px)
}
</style>
<!--services img area-->
<%@ include file="/front/layout/head.jsp"%>
<!-- include  common JS-->

<!-- include  header -->
<%@ include file="/front/layout/header.jsp"%>

<!-- 主要內容 start -->
<!--breadcrumbs area start-->


<div class="contact_area">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-12">
				<div class="contact_message form">
					<h3>更改寄送資訊</h3>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/order.do">
						<p>
							<label> 收件人姓名</label> <input type="text" name="recipient"
								class="form-control" id="exampleFormControlInput1"
								placeholder="${param.recipient}" value="${param.recipient}">
						</p>
						<p>
							<label> 收件人電話(市話請加上區碼)</label> <input type="tel" name="phone" size="10"
								minlength="10" maxlength="10" pattern="0\d{1}\d{2}\d{6}"
								class="form-control" id="exampleFormControlInput1"
								placeholder="${param.phone}" value="${param.phone}">
						</p>
						<p>
							<label> 收件人地址</label>
						<div id="zipcode3">
							<div class="f3" data-role="county"></div>
							<div class="f4" data-role="district"></div>
							</div>
							<input type="text" name="address" class="form-control"
							id="exampleFormControlInput1" placeholder="${param.address}"
							value="${param.address}">
						</p>
						

						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="memberId" value="${param.memberId}">
						<input type="hidden" name="groupOrderId"
							value="${param.groupOrderId}"> <input type="submit"
							class="btn btn-primary" value="送出修改"> <input
							type="button" class="btn btn-primary" onclick="history.back()"
							value="回到上一頁"></input>
					</FORM>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 主要內容 end -->
<%@ include file="/front/layout/commonJS.jsp"%>
<%@ include file="/front/layout/commonCSS.jsp"%>
<!-- include  footer -->
<%@ include file="/front/layout/footer.jsp"%>

<!-- 額外添加JS start -->
<script
	src="<%=request.getContextPath()%>/assets/js/jquery.twzipcode.min.js"></script>
<script>
	$("#zipcode3").twzipcode({
		"zipcodeIntoDistrict" : true,
		"css" : [ "city form-control", "town form-control" ],
		countySel : "臺北市", // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
		districtSel : "大安區", // 地區預設值
	});
</script>

<!-- 額外添加JS end -->

</body>
<!-- body end -->

</html>