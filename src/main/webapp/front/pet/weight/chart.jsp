<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet_weight.model.*"%>
<%@ page import="com.pet_weight.service.*"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<!-- Morris Chart Styles-->
<link href="assets/css/morris-0.4.3.min.css" rel="stylesheet" />
<!-- Weight Chart -->

				<div class="col-lg-12">
				 <div class="panel-body">
                     <div id="morris-line-chart"  style='height:30vh;'></div>
                 </div>
				</div>
			
<!-- End Weight Chart -->