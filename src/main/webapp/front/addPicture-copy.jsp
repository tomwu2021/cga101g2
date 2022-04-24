<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js" lang="zh-Hans-TW">

<!-- include <head></head> -->
<jsp:include page="./layout/head.jsp"/>

<!-- 額外添加CSS start -->
<link rel="stylesheet" href="../assets/css/pitcure/addPicture.css"/>

<!-- 額外添加CSS end -->

<!-- body start -->
<body>

<!-- include  common JS-->
<jsp:include page="./layout/commonJS.jsp"/>

<!-- include  header -->
<jsp:include page="./layout/header.jsp"/>


<!-- 主要內容 start -->
<!--breadcrumbs area start-->

<div class="breadcrumbs_area">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="breadcrumb_content">
                    <h3>Services</h3>
                    <ul>
                        <li><a href="index.html">home</a></li>
                        <li>our services</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!--breadcrumbs area end-->


<!--services img area-->
<div class="services_gallery mt-100">
    <div class="container">
        <div class="shop_toolbar_wrapper" id="file-zone">
            <span>點擊上傳</span>
            <input
                    type="file" id="file-btn" accept="image/*" multiple="multiple">
        </div>
        <div class="row" id="picture-row"></div>
    </div>
</div>

<!--services img end-->
<div class="container defined-btn" id="btn-container">
    <div class="product_tab_btn">
        <ul class="nav" role="tablist">
            <li>
                <a class="" data-toggle="tab" role="tab" aria-controls="classic" aria-selected="false">
                    Cancel
                </a>
            </li>
            <li>
                <a data-toggle="tab" role="tab" aria-controls="display" aria-selected="true" class="active show"
                   onclick="save()">
                    Save
                </a>
            </li>
        </ul>
    </div>
</div>

<!-- 主要內容 end -->

<!-- include  footer -->
<jsp:include page="./layout/footer.jsp"/>

<!-- 額外添加JS start -->
<script src="../assets/js/picture/addPicture.js"></script>

<!-- 額外添加JS end -->

</body><!-- body end -->

</html>
