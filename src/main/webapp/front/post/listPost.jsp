<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人頁面資料 - listPost.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 1200px;
	background-color: white;
	margin-top: 6px;
	margin-bottom: 6px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 10px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>個人頁面資料 - ListPost.jsp</h3>
		 <h4><a href="post.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
<c:forEach var="postVO" items="${list}" >
	<tr>
		<th>會員編號</th>
		<th>貼文編號</th>
		<th>內容</th>
		<th>按讚數</th>
		<th>status</th>
		<th>authority</th>
		<th>發文時間</th>
		<th>更新時間</th>
		
		<th>圖</th>
		
		
	</tr>
	<tr>
		<td>${postVO.memberId}</td>
		<td>${postVO.postId}</td>
		<td>${postVO.content}</td>
		<td>${postVO.likeCount}</td>
		<td>${postVO.status}</td>
		<td>${postVO.authority}</td>
		<td>${postVO.createTime}</td>
		<td>${postVO.updateTime}</td>
		
		
		<c:if test="${postVO.pictureList.size() != 0 }">
                   <td style="width: 6%;"><img
                    src="${postVO.pictureList.get(0).url}"
                    alt="..." class="img-thumbnail"></td>
        </c:if>
                  <!-- 此段是防止沒有照片所以跑版的判斷 開始-->
                  <c:if test="${postVO.pictureList.size() == 0 }">
                   <td style="width: 6%;"><img
                    src="https://fakeimg.pl/350x200/ff0000/000"
                    alt="..." class="img-thumbnail"></td>
        </c:if>
	</tr>
</c:forEach>
	
</table>
</html>