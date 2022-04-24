<%@page contentType="text/html;charset=utf-8"%>
<%
  String account = request.getParameter("account");
  String boo="";
  
  if( account.equals("ooxxqaws@gmail.com"))
	  boo = "存在";
  else
	  boo = "查無此人";
  out.print(boo);
%>