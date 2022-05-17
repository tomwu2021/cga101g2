<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
#sidebar::-webkit-scrollbar-thumb {
  background: #ececec;
}
</style>	
<!-- 	筆記  class="sidebar-link text-white"  改變字體顏色 -->
 <div class="d-flex align-items-stretch">
      <div id="sidebar" class="sidebar py-3" >
        <div class="-400 text-uppercase px-3 px-lg-4 py-4 font-weight-bold small headings-font-family">&nbsp;</div>
        <ul class="sidebar-menu list-unstyled">
        
          <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#pet-pages" aria-expanded="true" aria-controls="pages" class="sidebar-link " ><i class="bi bi-clipboard-heart mr-3 "></i><span>寵物日記</span></a>
            <div id="pet-pages" class="expanded">
              <ul class="sidebar-menu list-unstyled border-left border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/pet?memberId=${membersVO.memberId}&action=profile" class="sidebar-link  pl-lg-5">寵物主頁</a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/weight?action=all_Display&petId=${membersVO.petVO.petId}" class="sidebar-link  pl-lg-5">體重紀錄</a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/activity?action=all_Display&petId=${membersVO.petVO.petId}" class="sidebar-link  pl-lg-5">活動紀錄</a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/remind?action=all_Display&memberId=${membersVO.memberId}" class="sidebar-link  pl-lg-5">提醒事項</a></li>
              </ul>
            </div>
          </li>
          
          <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#member-pages" aria-expanded="true" aria-controls="pages" class="sidebar-link " ><i class="bi bi-person mr-3 "></i><span>個人專區</span></a>
            <div id="member-pages" class="expanded">
              <ul class="sidebar-menu list-unstyled border-left border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/front/member/member.jsp" class="sidebar-link  pl-lg-5">我的檔案</a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/front/member/memberPassword.jsp" class="sidebar-link  pl-lg-5">修改登入密碼</a></li>
              </ul>
            </div>
          </li>
                    
          <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#wallet-pages" aria-expanded="true" aria-controls="pages" class="sidebar-link " ><i class="bi bi-piggy-bank mr-3 "></i><span>我的錢包</span></a>
            <div id="wallet-pages" class="expanded">
              <ul class="sidebar-menu list-unstyled border-left border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/front/member/memberWalletUsedRecord.jsp" class="sidebar-link  pl-lg-5">消費紀錄</a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/front/member/memberWallet.jsp" class="sidebar-link  pl-lg-5">刷卡儲值</a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/front/member/memberWalletPassword.jsp" class="sidebar-link  pl-lg-5">修改錢包密碼</a></li>
              </ul>
            </div>
          </li>
          
           <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#order-pages" aria-expanded="false" aria-controls="pages" class="sidebar-link " ><i class="bi bi-file-text mr-3 "></i><span>交易紀錄</span></a>
            <div id="order-pages" class="collapse">
              <ul class="sidebar-menu list-unstyled border-left border-thick">
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/front/order/myAccount.jsp" class="sidebar-link  pl-lg-5">訂單查詢</a></li>
                <li class="sidebar-list-item"><a href="<%=request.getContextPath()%>/front/order/myAccountGroup.jsp" class="sidebar-link  pl-lg-5">團購查詢</a></li>
              </ul>
            </div>
          </li>
          
      </div>
