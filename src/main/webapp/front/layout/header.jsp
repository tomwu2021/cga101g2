<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!--offcanvas menu area end-->
<header>
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
                            <script>
                                function openChatList() {
                                    let el = $('.chatroom_list')
                                    let visibility = el.css('display');
                                    if (visibility === 'none') {
                                        el.show()
                                    } else {
                                        el.hide();
                                    }
                                }
                            </script>

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
        <%--聊天室列表--%>
        <div class="dropdown_links chatroom_list">
            <div class="dropdown_links_list">
                <h3>聊天室</h3>
            </div>
            <style>
                .chatroom_list {
                    visibility: visible;
                    width: 360px;
                    min-height: 90vh;
                    max-height: 90vh;
                    padding: 20px;
                    right: 20px;
                    top: 7vh;
                    display: none;
                    overflow: auto;
                }

            </style>
            <div class="dropdown_links_list">
                <ul>
                    <li>
                        <span onclick="openChat('9')">假朋友</span>
                    </li>
                </ul>
                <script>
                    //自動scroll 到底部
                    function keepBottom(className) {
                        document.getElementsByClassName(className)[0].scrollTop = document.getElementsByClassName(className)[0].scrollHeight;
                    }

                    function openChat() {
                        // 關閉列表
                        $('.chatroom_list').hide();
                        //開啟對話視窗
                        $('.chatroom_window').show();
                        //自動滾自最底部
                        keepBottom('chat_container');
                    }
                </script>
            </div>
        </div>
    </div>
</header>


<%--對話視窗--%>
<div class="chatroom_window">
    <div class="dropdown_links_list">
        <h3 style="display: inline-block;">聊天室</h3>
        <span class="bi bi-x-lg" style="display: inline-block;float: right;" onclick="closeChatWindow()"></span>
    </div>

    <script>
        function closeChatWindow() {
            $('.chatroom_window').hide();
        }
    </script>
    <div class="dropdown_links_list chat_container">
        <ul>
            <li>對方: 安安</li>
            <li class="sender">我方: 好好好</li>
            <li class="sender">我方: 好好好</li>
            <li>對方: 安安</li>
            <li class="sender">我方: 好好好</li>
            <li class="sender">我方: 好好好</li>
            <li>對方: 安安</li>
            <li>對方: 安安</li>
            <li>對方: 安安</li>
            <li>對方: 安安</li>
            <li class="sender">我方: 好好好</li>
            <li>對方: 安安</li>
            <li class="sender">我方: 好好好</li>
            <li class="sender">我方: 好好好</li>
            <li>對方: 安安</li>
            <li>對方: 安安</li>
            <li class="sender">我方: 好好好</li>
            <li>對方: 安安</li>
            <li class="sender">我方: 好好好</li>
            <li class="sender">我方: 好好好</li>
            <li>對方: 安安</li>
            <li>對方: 安安</li>
            <li class="sender">我方: 好好好</li>
            <li>對方: 安安</li>
            <li class="sender">我方: 好好好</li>
            <li class="sender">我方: 好好好</li>
            <li>對方: 安安</li>
            <li>對方: 安安</li>
        </ul>
    </div>
    <div class="dropdown_links_list">
        <div>
            <input type="text">
            <input type="button" value="傳送">
        </div>
    </div>
</div>
<style>
    .chatroom_window {
        background: #fff;
        box-shadow: 0 3px 11px 0 rgb(0 0 0 / 10%);
        text-align: left;
        transition: all .5s ease-out;
        overflow: hidden;
        z-index: 1;
        border-radius: 3px;
        max-height: 0;
        visibility: visible;
        width: 360px;
        min-height: 50vh;
        padding: 20px;
        right: 75px;
        position: fixed;
        bottom: 0;
        display: none;
    }

    .chat_container {
        height: 310px;
        overflow: auto;
        margin-bottom: 10px;
    }

    .chat_container .sender {
        text-align: right;
        padding-right: 20px;
    }
</style>
