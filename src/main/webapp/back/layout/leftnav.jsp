<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<!-- 	筆記  class="sidebar-link text-white"  改變字體顏色 -->
 <div class="d-flex align-items-stretch">
      <div id="sidebar" class="sidebar py-3" style="background:#343a40">
        <div class="text-white text-uppercase px-3 px-lg-4 py-4 font-weight-bold small headings-font-family">MAIN</div>
        <ul class="sidebar-menu list-unstyled">
          <li class="sidebar-list-item"><a href="back_index.html" class="sidebar-link text-white" onmouseover="hover_color(this);"  onmouseout="default_color(this);"><i class="o-home-1 mr-3 text-white"></i><span>後台主頁</span></a></li>
          <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#pages" aria-expanded="false" aria-controls="pages" class="sidebar-link text-white" onmouseover="hover_color(this);"  onmouseout="default_color(this);"><i class="o-bookmark-archive-1 mr-3 text-white"></i><span>商品</span></a>
            <div id="pages" class="collapse">
              <ul class="sidebar-menu list-unstyled border-left border-primary border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/layout/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/TEST.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/layout/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/layout/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
              </ul>
            </div>
          </li>
          
           <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#pages" aria-expanded="false" aria-controls="pages" class="sidebar-link text-white" onmouseover="hover_color(this);"  onmouseout="default_color(this);"><i class="o-sales-up-1 mr-3 text-white"></i><span>商品</span></a>
            <div id="pages" class="collapse">
              <ul class="sidebar-menu list-unstyled border-left border-primary border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/layout/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/layout/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/layout/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/layout/footer.jspp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
              </ul>
            </div>
          </li>
          
             <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#pages" aria-expanded="false" aria-controls="pages" class="sidebar-link text-white" onmouseover="hover_color(this);"  onmouseout="default_color(this);"><i class="o-database-1 mr-3 text-white"></i><span>商品</span></a>
            <div id="pages" class="collapse">
              <ul class="sidebar-menu list-unstyled border-left border-primary border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/index/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/index/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/index/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/index/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
              </ul>
            </div>
          </li>
          
       <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#pages" aria-expanded="false" aria-controls="pages" class="sidebar-link text-white" onmouseover="hover_color(this);"  onmouseout="default_color(this);"><i class="o-table-content-1 mr-3 text-white"></i><span>商品</span></a>
            <div id="pages" class="collapse">
              <ul class="sidebar-menu list-unstyled border-left border-primary border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/index/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/index/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/index/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/index/footer.jsp" class="sidebar-link text-white pl-lg-5"onmouseover="hover_color(this);"  onmouseout="default_color(this);">商品列表 </a></li>
              </ul>
            </div>
          </li>
 
<!--               <li class="sidebar-list-item"><a href="charts.html" class="sidebar-link text-muted "><i class="o-sales-up-1 mr-3 text-white"></i><span>Charts</span></a></li> -->
<!--               <li class="sidebar-list-item"><a href="tables.html" class="sidebar-link text-muted"><i class="o-table-content-1 mr-3 text-white"></i><span>Tables</span></a></li> -->
<!--               <li class="sidebar-list-item"><a href="forms.html" class="sidebar-link text-muted"><i class="o-survey-1 mr-3 text-white"></i><span>Forms</span></a></li> -->
<!--           <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#pages" aria-expanded="false" aria-controls="pages" class="sidebar-link text-muted"><i class="o-wireframe-1 mr-3 text-white"></i><span>Pages</span></a> -->
<!--             <div id="pages" class="collapse"> -->
<!--               <ul class="sidebar-menu list-unstyled border-left border-primary border-thick"> -->
<!--                 <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted pl-lg-5">Page one</a></li> -->
<!--                 <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted pl-lg-5">Page two</a></li> -->
<!--                 <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted pl-lg-5">Page three</a></li> -->
<!--                 <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted pl-lg-5">Page four</a></li> -->
<!--               </ul> -->
<!--             </div> -->
<!--           </li> -->
<!--               <li class="sidebar-list-item"><a href="login.html" class="sidebar-link text-muted"><i class="o-exit-1 mr-3 text-white"></i><span>Login</span></a></li> -->
<!--         </ul> -->
<!--         <div class="text-gray-400 text-uppercase px-3 px-lg-4 py-4 font-weight-bold small headings-font-family">EXTRAS</div> -->
<!--         <ul class="sidebar-menu list-unstyled"> -->
<!--               <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted"><i class="o-database-1 mr-3 text-white"></i><span>Demo</span></a></li> -->
<!--               <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted"><i class="o-imac-screen-1 mr-3 text-white"></i><span>Demo</span></a></li> -->
<!--               <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted"><i class="o-paperwork-1 mr-3 text-white"></i><span>Demo</span></a></li> -->
<!--               <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted"><i class="o-wireframe-1 mr-3 text-white"></i><span>Demo</span></a></li> -->
<!--         </ul> -->
      </div>
