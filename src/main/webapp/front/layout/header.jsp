<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!--offcanvas menu area end-->
<%@ page import="com.members.model.*" %>
<header>

    <input id="loginId" value="<%=session.getAttribute("membersVO")==null?"":((MembersVO)session.getAttribute("membersVO")).getMemberId() %>">

    <!--     筆記 fa-2x控制i的大小 -->
    <div class="main_header sticky-header">
        <div class="container-fluid">
            <div class="header_container">
                <div class="row align-items-center">
                    <div class="col-lg-2">
                        <div class="logo">
                            <a href="index.html"><img src=""></a>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <!--main menu start-->
                        <div class="main_menu menu_position">
                            <nav>
                                <ul>
                                    <!-- <li><a class="active"  href="index.html">寵物商城</a>
                                    </li> -->
                                    <li><a href="<%=request.getContextPath()%>blog.html">最新消息<i
                                            class="fa fa-angle-down"></i></a>
                                        <ul class="sub_menu pages">
                                            <li><a href="<%=request.getContextPath()%>blog-details.html">分類?</a></li>
                                            <li><a href="<%=request.getContextPath()%>blog-fullwidth.html">狗狗專區</a></li>
                                            <li><a href="<%=request.getContextPath()%>blog-sidebar.html">貓狗通用</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="<%=request.getContextPath()%>blog.html">商品專區<i
                                            class="fa fa-angle-down"></i></a>
                                        <ul class="sub_menu pages">
                                            <li><a href="<%=request.getContextPath()%>blog-details.html">貓貓專區</a></li>
                                            <li><a href="<%=request.getContextPath()%>blog-fullwidth.html">狗狗專區</a></li>
                                            <li><a href="<%=request.getContextPath()%>blog-sidebar.html">貓狗通用</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="<%=request.getContextPath()%>groupshop.html">團購專區<i
                                            class="fa fa-angle-down"></i></a>
                                        <ul class="sub_menu pages">
                                            <li><a href="<%=request.getContextPath()%>groupshop.html">我要開團</a></li>
                                            <li><a href="<%=request.getContextPath()%>groupshop.html">我要跟團</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="<%=request.getContextPath()%>">寵物社群</a>
                                        <ul class="sub_menu pages">
                                            <li><a href="404.html">Error 404</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="<%=request.getContextPath()%>contact.html"> 找客服</a></li>
                                </ul>
                            </nav>
                        </div>
                        <!--main menu end-->
                    </div>
                    <div class="col-lg-2">
                        <!--                         justify-content-start 往左靠齊 -->
                        <div class="header_account_area  justify-content-start">
                            <!--mini cart end-->
                            <div class="header_account-list top_links" onclick="openChatList()">
                                <a><i class="bi bi-chat-fill"></i></a>
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
                                <a href=""><i class="bi bi-person-square"></i></a>
                                <div class="dropdown_links">
                                    <div class="dropdown_links_list">
                                        <h3>會員名稱</h3>
                                        <ul>
                                            <li><a href="<%=request.getContextPath()%>">前往會員中心</a></li>
                                            <li><a href="#">歡迎來到P.club</a></li>
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
        <div class="dropdown_links chatroom_list" id="private-chatroom-list">
            <div class="dropdown_links_list">
                <h2>聊天室</h2>
            </div>
            <div class="dropdown_links_list">
                <ul id="private-chatroom-list-ul">
                </ul>
            </div>
        </div>
    </div>
</header>

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
