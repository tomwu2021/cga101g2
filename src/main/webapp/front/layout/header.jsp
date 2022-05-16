<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!--offcanvas menu area end-->
<%@ page import="com.members.model.*" %>
<header>

    <%
    Integer loginId = session.getAttribute("membersVO")==null ? -999:((MembersVO)session.getAttribute("membersVO")).getMemberId();
    %>
    <input id="loginId" value="<%=loginId%>">

    <!--     筆記 fa-2x控制i的大小 -->
    <div class="main_header sticky-header">
        <div class="container-fluid">
            <div class="header_container">
                <div class="row align-items-center">
                    <div class="col-lg-2">
                        <div class="logo">
                            <a href="<%=request.getContextPath()%>/index.html"><img src="<%=request.getContextPath()%>/assets/img/logo/logo_icon.png"></a>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <!--main menu start-->
                        <div class="main_menu menu_position">
                            <nav>
                                <ul>
                                    <!-- <li><a class="active"  href="index.html">寵物商城</a>
                                    </li> -->
                                    <li><a href="<%=request.getContextPath()%>/article?action=all_Display">最新消息</a></li>
                                    <li><a href="<%=request.getContextPath()%>/shop?action=listProducts_Byfind">商品專區<i
                                            class="fa fa-angle-down"></i></a>
                                        <ul class="sub_menu pages">
                                            <li><a href="<%=request.getContextPath()%>/shop?action=listProducts_Byfind&sort1_id=1">貓貓專區</a></li>
                                            <li><a href="<%=request.getContextPath()%>/shop?action=listProducts_Byfind&sort1_id=2">狗狗專區</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="<%=request.getContextPath()%>/groupShop?action=listGroupProducts_Byfind">團購專區<i
                                            class="fa fa-angle-down"></i></a>
                                        <ul class="sub_menu pages">
                                            <li><a href="<%=request.getContextPath()%>/groupShop?action=listGroupProducts_Byfind">我要開團</a></li>
                                            <li><a href="<%=request.getContextPath()%>/groupShop?action=listGroupProducts_Byfind">我要跟團</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="<%=request.getContextPath()%>/MainPost?action=selectChangePost">寵物社群<i
                                            class="fa fa-angle-down"></i></a>
                                        <ul class="sub_menu pages">
                                            <li><a href="<%=request.getContextPath()%>/PersonPost?action=getOne_For_Display&memberId=<%=loginId%>">個人主頁</a></li>
                                            <li><a href="<%=request.getContextPath()%>/relationship?memberId=<%=loginId%>">好友管理</a></li>
                                            <li><a href="<%=request.getContextPath()%>/pet?memberId=<%=loginId%>&action=profile">寵物主頁</a></li>
                                            <li><a href="<%=request.getContextPath()%>/album?memberId=<%=loginId%>">相簿管理</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="<%=request.getContextPath()%>/front/contact.jsp"> 找客服</a></li>
                                </ul>
                            </nav>
                        </div>
                        <!--main menu end-->
                    </div>
                    <div class="col-lg-2">
                        <!--                         justify-content-start 往左靠齊 -->
                        <div class="header_account_area  justify-content-start">
                            <!--mini cart end-->
                            <% if(loginId>0){ %>
                            <div class="header_account-list top_links" id="chat-icon" onclick="openChatList()">
                                <div id="unread-count" style="text-align:center;border-radius:50%;width: 20px;height: 20px;font-weight:600;position:absolute;background-color: red;font-size: 10px;z-index: 1;top: -10px;left: 10px;border: 2px white solid;line-height: 20px;color: white;">

                                </div>
                                <a><i class="bi bi-chat-fill"></i></a>
                            </div>
                            <% } %>
                            <div class="header_account-list top_links">
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/shop/shoppingCart.jsp" id="cartForm">
                                    <a onclick="document.getElementById('cartForm').submit()">
                                        <i class="bi bi-cart-fill" style="font-size: 20px"></i></a>
                                    </a>
                                </FORM>
                            </div>
                            <div class="header_account-list top_links">
                                <a href=""><i class="bi bi-bell-fill"></i></a>
                                <div class="dropdown_links">
                                    <div class="dropdown_links_list">
                                        <h3>通知列表</h3>
                                        <ul>
                                            <li><a href="#"><i class="bi bi-envelope-fill red"></i>&emsp;系統通知</a></li>
                                            <li><a href="#"><i class="bi bi-envelope-paper-fill blue"></i>&emsp;社群通知</a>
                                            </li>
                                            <li><a href="#"><i class="bi bi-envelope-fill red"></i>&emsp;系統通知</a></li>
                                        </ul>
                                    </div>
                                    <div class="dropdown_links_list">
                                        <h3><img src="" alt=""> 查看通知總覽</h3>
                                    </div>
                                </div>
                            </div>

                            <div class="header_account-list top_links">
                                <a href=""><i class="bi bi-person-circle"></i></a>
                                <div class="dropdown_links">
                                    <div class="dropdown_links_list">
                                        <h3>${membersVO.name}<small>&nbsp;您好！</small></h3>
                                        <ul>
                                            <li><a href="<%=request.getContextPath()%>/front/account.jsp">前往會員中心</a></li>
                                        </ul>
                                    </div>
                                    <div class="dropdown_links_list">
                                        <h3><img src="" alt="">Logout</h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
<%--        聊天室列表--%>

        <% if(loginId>0){ %>
        <div class="dropdown_links chatroom_list" id="private-chatroom-list">
            <div class="dropdown_links_list">
                <span style="margin-right:0px;font-size: 25px;font-family: '微軟正黑體', sans-serif;letter-spacing: 2px">聊天室</span>
                <span class="bi bi-x-lg chat-button" style="display: inline-block;float: right;" onclick="closePrivateChatroom()"></span>
            </div>
            <div class="dropdown_links_list">
                <ul id="private-chatroom-list-ul">
                </ul>
            </div>
        </div>
        <% } %>
    </div>
</header>
<style>

    #private-chatroom-list-ul .bi.bi-circle-fill {
        position: relative;
        z-index: 9;
        left: 17px;
        bottom: 13px;
    }

    #private-chatroom-list-ul .online {
        color: green;
    }

    #private-chatroom-list-ul .offline {
        color: darkorange;
    }

</style>
<% if(loginId>0){ %>
<div class="dropdown_links chatroom_list" id="friend-list-container"
     style="max-height: 520px !important; width: 750px; display: none;padding: 0;position: absolute;left: calc(50% - 375px);top: calc(50% - 250px);min-height: 0vh;">
    <div class="dropdown_links_list" style="text-align:center;padding: 20px 0 0;">
        <span style="margin-left:28px;margin-right:0px;font-size: 25px;font-family: '微軟正黑體', sans-serif;letter-spacing: 2px">邀請想一起聊天的朋友</span>
        <span class="bi bi-x-lg chat-button" style="display: inline-block;float: right;" onClick="closeFriendContainer()"></span>
    </div>
    <div class="dropdown_links_list">
        <ul id="friend-list">
        </ul>

    </div>
</div>



<div class="chatroom_window" id="private-chat">
    <div class="dropdown_links_list" id="chat-buttons">

    </div>
    <div class="dropdown_links_list chat_container">
        <ul id="private-message-list-ul">
        </ul>
    </div>
    <div class="dropdown_links_list">
        <div>
            <input type="text" id="private-new-message" >
            <input type="button" value="傳送" onclick="putMessage()">
        </div>
    </div>
</div>
<% } %>

