<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<!-- 	筆記  class="sidebar-link text-white"  改變字體顏色 -->
 <div class="d-flex align-items-stretch">
      <div id="sidebar" class="sidebar py-3" >
        <div class="-400 text-uppercase px-3 px-lg-4 py-4 font-weight-bold small headings-font-family">&nbsp;</div>
        <ul class="sidebar-menu list-unstyled">
          <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#member" aria-expanded="false" aria-controls="pages" class="sidebar-link " ><i class="bi bi-house-door-fill mr-3 "></i><span>會員中心</span></a>
            <div id="member" class="collapse">
              <ul class="sidebar-menu list-unstyled border-left border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/front/member/member.jsp" class="sidebar-link  pl-lg-5">基本資料</a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/layout/footer.jsp" class="sidebar-link  pl-lg-5">Ｐ幣錢包 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/back/layout/footer.jsp" class="sidebar-link  pl-lg-5">我的訂單 </a></li>
              </ul>
            </div>
          </li>
          
           <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#pet" aria-expanded="false" aria-controls="pages" class="sidebar-link " ><i class="bi bi-clipboard-heart mr-3 "></i><span>寵物日記</span></a>
            <div id="pet" class="collapse">
              <ul class="sidebar-menu list-unstyled border-left border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/pet?memberId=${membersVO.memberId}&action=profile" class="sidebar-link  pl-lg-5">寵物主頁 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/weight?action=all_Display&petId=${membersVO.petVO.petId}" class="sidebar-link  pl-lg-5">體重紀錄 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/activity?action=all_Display&petId=${membersVO.petVO.petId}" class="sidebar-link  pl-lg-5">活動紀錄 </a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/remind?action=all_Display&memberId=${membersVO.memberId}" class="sidebar-link  pl-lg-5">提醒事項 </a></li>
              </ul>
            </div>
          </li>
          
      </div>
