<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<%--
String success = (String)request.getAttribute("success");
--%>
<!DOCTYPE html>
<html>
<head>
<title>找客服</title>
<!-- 共用的CSS startr-->
<%@include file="/front/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<!-- 	路徑舉例 -->
<!-- Phyllis's CSS Start-->
    <style>
        .contact_box {
            border: 1px solid rgb(229, 229, 229);
        }

        #captcha_area>* {
            display: inline-block;
            margin-left:10px;
        }

        #captchaTextBox {
            width: 126px;
        }

        .contact_box span {
            color: #F53737;
        }
        
        .contact_box input {
            background-color: #FAFAFF;
        }
        
        .contact_box textarea {
            background-color: #FAFAFF;
        }

        #captcha {
            pointer-events: none;
        }
    </style>
    <!-- Phyllis's CSS End-->
<!-- 額外添加的CSS -->
</head>
<body onload="createCaptcha()">
	<!-- 共用的header start-->
	<%@include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->
	<!--breadcrumbs area start-->
	<div class="breadcrumbs_area">
	    <div class="container">
	        <div class="row">
	            <div class="col-12">
	                <div class="breadcrumb_content">
	                    <h3>客服信箱</h3>
	                    <ul>
	                        <li><a href="<%=request.getContextPath()%>/index">Home</a></li>
	                        <li>找客服</li>
	                    </ul>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<!--breadcrumbs area end-->

	<!--! ========內容======== -->
	    <!--contact area start-->
    <div class="contact_area">
        <div class="container">
            <div class="row justify-content-center">

                <div class="col-lg-8 col-md-12 mt-50 contact_box">
                <c:if test="${success != null}">
                <h3 class='success'><b>感謝您的提問</b></h3>
                <p class='success'>${success} 您好，我們將於24小時內回覆至您指定的信箱！</p>
                </c:if>
                <c:if test="${success == null}">
                    <div class="contact_message form mt-30 mb-100">
                        <h3><b>讓我們了解您的問題</b></h3>
                        <form id="contact_form" method='post' action='/CGA101G2/contact'>
                            <p>
                                <label> 暱稱<span> *</span></label><span>${errorMsgs.nickname}</span>
                                <input id="name" name="nickname" type="text" value="${param.nickname}">
                            </p>
                            <p>
                                <label> 電子信箱<span> *</span></label><span>${errorMsgs.mailAddress}</span>
                                <input id="email" name="mailAddress" type="email" value="${param.mailAddress}">
                            </p>
                            <div class="contact_textarea">
                                <label> 問題描述<span> *</span> </label><span>${errorMsgs.content}</span>
                                <textarea id="message" name="content" class="form-control2">${param.content}</textarea><!-- 更換字符 -->
                            </div>
                            <label> 請輸入驗證碼<span> *</span></label><span>${errorMsgs.captcha}</span>
                            <div id="captcha_area">
                                <input type="text" id="captchaTextBox" placeholder="不分大小寫" />
                                <canvas id="captcha"></canvas>
                                <i onclick="createCaptcha()" class="fa fa-refresh fa-2x"></i>
                            </div>
                            <input id="errorCaptcha" name='captcha' type='hidden'>
                            <br />
                            <input type="hidden" name="action" value="insert">
                          
                            <button type="submit" onclick="confirmCaptcha()"> 送出</button>
                        </form>

                    </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <!--contact area end-->
	<!--! 內容 結束-->


			<!-- 共通的footer start-->
			<%@include file="/front/layout/footer.jsp"%>
			<!-- 共通的footer end-->


	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 共用的JS -->

	<!-- 額外添加的JS -->
	<!-- 	路徑舉例 -->
	<!-- 額外添加的JS -->
<!-- Phyllis's JS Start-->

    <script>
        let code;
        function createCaptcha() {
            document.getElementById('captcha').remove();
            let charsArray =
                "123456789abcdefghijkmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            let captcha = [];
            for (let i = 0; i < 6; i++) {
                let index = parseInt(Math.random() * charsArray.length + 1);
                if (captcha.indexOf(charsArray[index]) == -1)
                    captcha.push(charsArray[index]);
                else i--;
            }
            const canv = document.createElement("canvas");
            canv.id = "captcha";
            canv.width = 108;
            canv.height = 42;
            canv.setAttribute("style", "position: absolute;")
            let ctx = canv.getContext("2d");
            ctx.font = "24px Comic Sans MS";
            ctx.strokeStyle = 'darkblue';
            ctx.strokeText(captcha.join(""), 0, 30);
            code = captcha.join("");
            document.getElementById("captchaTextBox").after(canv);
            document.querySelector(".fa-refresh").setAttribute("style", "position: relative; left:108px;color:#666;");
        }
        function confirmCaptcha() {
            if (document.getElementById("captchaTextBox").value.toUpperCase() == code.toUpperCase()) {
                submitClick();
            } else {
                const span = document.getElementById("errorCaptcha");
                span.value = "(驗證碼錯誤)";
                createCaptcha();
            }
        }
    </script>
    <!-- Phyllis's JS End-->
</body>

</html>