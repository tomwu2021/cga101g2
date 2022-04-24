<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/gallery.css">
<input type="hidden" name="albumId" value="${albumId}" id="albumId">
<!--services img area-->
<div class="services_gallery mt-100" >
  <div class="container">
    <div class="shop_toolbar_wrapper">

      <div class="form-select" style="display: flex;">
        <form action="#" style="display: block;">
          <select id="pageSize">
            <option value=12>每頁12筆</option>
            <option value=24>每頁24筆</option>
            <option value=36>每頁36筆</option>
          </select>
        </form>
      </div>
      <div class="form-select" style="display: flex;">
        <form action="#" style="display: block;">
          <select id="sort">
            <option value="">按時間排序</option>
            <option value="DESC">由新到舊</option>
            <option value="ASC">由舊到新</option>
          </select>
        </form>
      </div>
      <div class="form-select" style="display: flex;">
        <form action="#" style="display: block;">
          <select id="uploadTime">
            <option value="1">一天內</option>
            <option value="7">一週內</option>
            <option value="30">一個月內</option>
          </select>
        </form>
      </div>
      <input placeholder="Search..." type="text" id="fileName">
      <div class="page_amount" style="align-self: center">
        <p></p>
      </div>
    </div>
    <div class="row" id="picture-row"></div>
  </div>
</div>
<!--services img end-->
<div class="container" id="page-root">
  <div class="shop_toolbar t_bottom">
    <div class="pagination">
      <ul id="page-ul">
      </ul>
    </div>
  </div>
</div>

<!--our services area-->

<a class="upload-button" id="scrollUp" href="<%=request.getContextPath()%>/picture?action=add&albumId=${albumId}"
   style="position: fixed; z-index: 2147483647; display: inline; right: 4px; bottom: 300px;">
  <i class="fa fa-plus"></i>
</a>

<script src="<%=request.getContextPath()%>/assets/js/picture/gallery.js"></script>




