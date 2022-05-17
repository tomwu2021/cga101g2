<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="col-lg-3 col-md-12">
    <div class="blog_sidebar_widget">
        <div class="widget_list widget_search">
            <div class="widget_title">
                <h3>Search</h3>
            </div>
            <form action="#">
                <input placeholder="Search..." type="text">
                <button type="submit">search</button>
            </form>
        </div>
        <!-- 最新貼文五則 -->
        <div className="widget_list widget_post" id="right-sidebar-post">

        </div>
        <!-- 最新貼文結束 -->
        <!-- 最新留言五則 -->
        <div class="widget_list comments" id="new-comment">

        </div>
        <!-- 最新留言結束 -->

        <!-- 最近互動好友-->
        <div class="widget_list widget_post">
            <div class="widget_title">
                <h3>Recent Posts</h3>
            </div>
            <div class="post_wrapper">
                <div class="post_thumb">
                    <a href="blog-details.html"><img
                            src="assets/img/blog/blog1.jpg" alt=""></a>
                </div>
                <div class="post_info">
                    <h4>
                        <a href="blog-details.html">Blog image post</a>
                    </h4>
                    <span>March 16, 2022 </span>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/assets/js/post/rightSideBar.js"></script>
