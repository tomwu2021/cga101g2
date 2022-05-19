<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<!-- 	筆記  class="sidebar-link text-white"  改變字體顏色 -->
 <div class="d-flex align-items-stretch">
      <div id="sidebar" class="sidebar py-3" style="background:#343a40">
        <div class="text-white text-uppercase px-3 px-lg-4 py-4 font-weight-bold small headings-font-family">&nbsp;</div>
        <ul class="sidebar-menu list-unstyled">
          <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/empSelect.jsp" class="sidebar-link text-white" onmouseover="hover_color(this);"  onmouseout="default_color(this);"><i class="o-user-details-1 mr-3 text-white"></i><span>會員管理</span></a>
          </li>
          
           <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#product-pages" aria-expanded="true" aria-controls="pages" class="sidebar-link text-white" onmouseover="hover_color(this);"  onmouseout="default_color(this);"><i class="o-stack-1 mr-3 text-white"></i><span>商品管理</span></a>
            <div id="product-pages" class="expanded">
              <ul class="sidebar-menu list-unstyled border-left border-primary border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/shop?action=listProducts_Byfind" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品總覽 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/shop/addProduct.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品新增 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/shop/sortMix?action=select" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">分類總覽</a></li>
              </ul>
            </div>
          </li>
          
          <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#order-pages" aria-expanded="true" aria-controls="pages" class="sidebar-link text-white" onmouseover="hover_color(this);"  onmouseout="default_color(this);"><i class="o-paper-stack-1 mr-3 text-white"></i><span>訂單管理</span></a>
          	<div id="order-pages" class="expanded">
              <ul class="sidebar-menu list-unstyled border-left border-primary border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/order/orderList.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">訂單列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/order/groupOrderList.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">團購單列表 </a></li>
              </ul>
            </div>
          </li>
          
           <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#service-pages" aria-expanded="true" aria-controls="pages" class="sidebar-link text-white" onmouseover="hover_color(this);"  onmouseout="default_color(this);"><i class="o-contact-card-1 mr-3 text-white"></i><span>客戶服務</span></a>
            <div id="service-pages" class="expanded">
              <ul class="sidebar-menu list-unstyled border-left border-primary border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/contact?action=all_Display" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">客戶問題列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/report/report.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">檢舉貼文管理 </a></li>
              </ul>
            </div>
          </li>
          
       <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#article-pages" aria-expanded="true" aria-controls="pages" class="sidebar-link text-white" onmouseover="hover_color(this);"  onmouseout="default_color(this);"><i class="o-news-article-1 mr-3 text-white"></i><span>最新消息管理</span></a>
            <div id="article-pages" class="expanded">
              <ul class="sidebar-menu list-unstyled border-left border-primary border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/article?action=all_Article" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">最新消息列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/article/add.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">發佈最新消息 </a></li>
              </ul>
            </div>
          </li>
 
      </div>
