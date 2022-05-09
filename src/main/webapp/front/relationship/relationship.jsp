<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/relationship/relationship.css" />
<input type="hidden" id="memberId" value="<%=request.getAttribute("memberId")%>">

<input type="hidden" id="isOwner" value="<%=request.getAttribute("isOwner")%>">


    <!--shop wrapper start-->
<div class="shop_toolbar_wrapper" id="select-relation" style="margin: 20px auto;">
    <div class="col-12" id="relation-button" style="display: inline-block; text-align: left">
        <button class="col-lg-2 back-button col-md-4 col-sm-8" onclick="search(1)"
                style="position: relative; right: 0; border-radius: 30px; height: 50px;">好友</button>
        <button class="col-lg-2 back-button col-md-4 col-sm-8" onclick="search(2)"
                style="position: relative; right: 0; border-radius: 30px; height: 50px;">黑名單</button>
        <button class="col-lg-2 back-button col-md-4 col-sm-8" onclick="search(3)"
                style="position: relative; right: 0; border-radius: 30px; height: 50px;">邀請中</button>
        <button class="col-lg-2 back-button col-md-4 col-sm-8" onclick="search(4)"
                style="position: relative; right: 0; border-radius: 30px; height: 50px;">交友申請</button>
    </div>
</div>
<div class="comments_box col-12" id="relation-list">
<%--    <div class="comments_box" style="margin: 0 auto;max-width: 90%;text-align: left;">--%>
<%--        <div class="comment_list" style="margin: 20px auto;;;">--%>
<%--            <div class="comment_thumb" style="width: 100px;height: 100px;">--%>
<%--                <img src="assets/img/blog/comment3.png.jpg" alt="" style="height: 90%;width: 90%;margin-top: 8px;">--%>
<%--            </div>--%>
<%--            <div class="comment_content" style="width: calc(100% - 100px) !important;margin-left: 100px;">--%>
<%--                <div class="comment_meta">--%>
<%--                    <h5><a href="#">Admin</a></h5>--%>
<%--                    <span>October 16, 2022 at 1:38 am</span>--%>
<%--                </div>--%>
<%--                <p>But I must explain to you how all this mistaken idea of denouncing pleasure</p>--%>
<%--                <div class="comment_reply">--%>
<%--                    <a href="#">Reply</a>--%>
<%--                </div>--%>
<%--                <div class="comment_reply" style="top: 60px;">--%>
<%--                <a href="#">Reply</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
</div>
<script
        src="<%=request.getContextPath()%>/assets/js/relationship/relationship.js"></script>