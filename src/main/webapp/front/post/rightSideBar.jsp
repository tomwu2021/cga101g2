<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="col-lg-3 col-md-12">
    <div class="blog_sidebar_widget">
        <!-- 搜尋區 -->
        <div class="widget_list widget_search">
            <div class="widget_title">
                <h3>Search</h3>
            </div>
            <form method="post" action="<%=request.getContextPath()%>/SelectKeyword" enctype="multipart/form-data">
                <input placeholder="Search..." type="text" name="content" >

                <input type="hidden" name="action" value="selectPost">

                <button type="submit">search</button>
            </form>
        </div>
        <!-- 最新貼文五則 -->
        <div className="widget_list widget_post" id="right-sidebar-post">

        </div>
        <!-- 最新貼文結束 -->
        <!-- 最新留言五則 -->
        <div class="widget_list comments" id="new-post-comment">

        </div>
        <!-- 最新留言結束 -->

        <!-- 最近互動好友-->
<%--        <div class="widget_list widget_post" id="recent-chat-friend">--%>


<%--        </div>--%>
    </div>
</div>
