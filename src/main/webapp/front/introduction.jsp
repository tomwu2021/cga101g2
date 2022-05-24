<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<% String videoURL = (String)request.getAttribute("url"); %>


<video src="<%=videoURL %>" controls style="width: 800px;height: 450px;box-shadow:5px 5px 3px 0 lightgrey,-5px -5px 3px 0 lightgrey;position: relative;left: calc(50% - 400px);margin: 100px 0;"></video>