<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet_activity.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Integer petId = session.getAttribute("membersVO")==null ? -999:((MembersVO)session.getAttribute("membersVO")).getPetVO().getPetId();
%>
<!DOCTYPE html>
<html>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 自訂的CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/sb-admin-2.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/account.css">
<body>
	<!-- 共用的header start -->
	<%@ include file="/front/layout/header.jsp"%>
	<!-- 共用的header end -->
	<!--breadcrumbs area start-->
	<div class="breadcrumbs_area">
	    <div class="container">
	        <div class="row">
	            <div class="col-12">
	                <div class="breadcrumb_content">
	                    <h3>${title}會員中心</h3>
	                    <ul>
	                        <li><a href="<%=request.getContextPath()%>/index.html">home</a></li>
	                        <li>${title}會員中心</li>
	                    </ul>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<!--breadcrumbs area end-->
		<!-- ============================= Main ============================= -->
<div class="shop_area shop_reverse mb-5">
	<div class="container">
		<div class="row">
    <!--背景容器-->
    <div class="container-bg" style="">
            <div class="mod-002">
            <h3 class="panel-title">
                <b><i class="bi bi-person"></i>個人專區</b>
            </h3>
            <div class="card-wrap" id="member_wrap">
                <div class="item">
                    <div class="card">
                        <div class="info"><strong class="title"><a
                                    href="<%=request.getContextPath()%>/front/member/member.jsp">個人資料管理</a></strong><strong
                                class="title"><a
                                    href="<%=request.getContextPath()%>/front/member/memberPassword.jsp">修改密碼</a></strong>
                        </div>
                        <ul class="note">
                            <li>修改姓名、地址、登入密碼...等</li>
                        </ul>
                    </div>
                </div>
                <div class="item">
                    <div class="card">
                        <div class="info">
                            <strong class="title" style="">會員等級</strong>
                        </div>
                        <ul class="note">
                            <li id="rankId">${membersVO.rankId}</li>
                        </ul>
                    </div>
                </div>
                <div class="item">
                    <div class="card">
                        <div class="info">
                            <strong class="title">紅利回饋</strong>
                        </div>
                        <ul class="note">
                            <li><strong>累積<b>${membersVO.bonusAmount}</b>點</strong></li>
                        </ul>
                    </div>
                </div>
                
            </div>

        </div>
        <div class="mod-002" style="">
            <h3 class="panel-title" style="">
                <b><i class="bi bi-journal-text"></i>交易記錄</b>
            </h3>
            <div class="card-wrap">
	            <div class="item">
	                    <div class="card">
	                        <div class="info">
	                            <strong class="title"><a
	                                    href="#">訂單查詢</a></strong>
	                        </div>
	                        <ul class="note">
	                            <div class="row">
	                                <div class="cell">
	                                    <ul>
	                                        <li><strong>已出貨<b>0</b></strong></li>
	                                    </ul>
	                                </div>
	                                <div class="cell">
	                                    <ul>
	                                        <li><strong>未出貨<b>0</b></strong></li>
	                                    </ul>
	                                </div>
	                            </div>
	                        </ul>
	                    </div>
	                </div>
                <div class="item" style="">
                    <div class="card" style="">
                        <div class="info">
                            <strong class="title" style=""><a
                                    href="#">團購查詢</a></strong>
                        </div>
                        <ul class="note" style="">
                            <li style="">結團時間因應團主設定</li>
                        </ul>
                    </div>
                </div>
                <div class="item">

                </div>
                <div class="item">

                </div>
            </div>
        </div>
        
        <div class="mod-002">
            <h3 class="panel-title">
                <b><i class="bi bi-piggy-bank"></i>我的錢包</b>
            </h3>
            <div class="card-wrap">
            	<div class="item">
                    <div class="card">
                        <div class="info"><strong class="title"><a
                                    href="<%=request.getContextPath()%>/front/member/memberWalletUsedRecord.jsp">錢包管理</a></strong><strong
                                class="title"><a
                                    href="<%=request.getContextPath()%>/front/member/memberWalletPassword.jsp">修改密碼</a></strong>
                        </div>
                        <ul class="note">
                            <li>修改錢包使用密碼</li>
                        </ul>
                    </div>
                </div>
                
                <div class="item">
                    <div class="card">
                        <div class="info"><strong class="title"><a
                                    href="<%=request.getContextPath()%>/front/member/memberWallet.jsp">刷卡儲值</a></strong>
                        </div>
                        <ul class="note">
                            <li>消費需使用信用卡儲值</li>
                        </ul>
                    </div>
                </div>
                <div class="item">
                    <div class="card">
                        <div class="info">
                            <strong class="title"><a
                                    href="<%=request.getContextPath()%>/front/member/memberWalletUsedRecord.jsp">可用餘額</a></strong>
                         </div>
                        <ul class="note">
                            <li><strong>尚有<b>${membersVO.eWalletAmount}</b>元</strong></li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
        
        <div class="mod-002">
            <h3 class="panel-title">
                <b><i class="bi bi-clipboard-heart"></i>寵物日記</b>
            </h3>
            <div class="card-wrap">
                <div class="item">
                    <div class="card">
                        <div class="info">
                            <strong class="title"><a
                                    href="<%=request.getContextPath()%>/pet?memberId=${membersVO.memberId}&action=profile">寵物基本資訊</a></strong>
                        </div>
                        <ul class="note">
                            <li>寵物檔案與生活點滴一覽</li>
                        </ul>
                    </div>
                </div>
                <div class="item">
                    <div class="card">
                        <div class="info">
                            <strong class="title"><a
                                    href="<%=request.getContextPath()%>/weight?action=all_Display&petId=${membersVO.petVO.petId}">寵物體重紀錄</a></strong>
                        </div>
                        <ul class="note">
                            <li>提供記錄寵物體重之服務</li>
                        </ul>
                    </div>
                </div>
                <div class="item">
                    <div class="card">
                        <div class="info">
                            <strong class="title"><a
                                    href="<%=request.getContextPath()%>/activity?action=all_Display&petId=${membersVO.petVO.petId}">寵物活動紀錄</a></strong>
                        </div>
                        <ul class="note">
                            <li>提供記錄寵物活動之服務</li>
                        </ul>
                    </div>
                </div>
                <div class="item">
                    <div class="card">
                        <div class="info">
                            <strong class="title"><a
                                    href="<%=request.getContextPath()%>/remind?action=all_Display&memberId=${membersVO.memberId}">提醒事項管理</a></strong>
                        </div>
                        <ul class="note">
                            <li>管理寵物相關事項之提醒</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        
        <!--mod-002 end-->

    </div>
    <!--背景容器 end-->
    </div>
			</div>

		</div>
	<!-- =========================== End Main =========================== -->
	</div>
	<%@include file="/front/pet/footer.jsp"%>
	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 自訂的JS -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/assets/js/member.js"></script>
</body>
</html>