<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 額外添加CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/picture/addPicture.css"/>

<section class="main-container">
        <!--services img area-->
        <div class="services_gallery mt-100">
            <div class="container">
                <div class="shop_toolbar_wrapper" id="file-zone">
                    <span>點擊上傳</span>
                    <input type="file" name="file" id="file-btn" accept="image/*" multiple="multiple">
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
    <form METHOD="post" action="PictureController" id="main-form" enctype="multipart/form-data">
        <input type="hidden" name="albumId" value="${albumId}" id="albumId">
        <input type="hidden" name="action" value="upload">
        <input type="file" name="file" id="upload-input" accept="image/*" multiple="multiple" style="display: none">
    </form>
</section>

<!-- 額外添加JS -->
<script src="<%=request.getContextPath()%>/assets/js/picture/addPicture.js"></script>
