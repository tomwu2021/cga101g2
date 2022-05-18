<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.math.BigDecimal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.pet_weight.model.*"%>
<%
Integer petId = session.getAttribute("membersVO")==null ? -999:((MembersVO)session.getAttribute("membersVO")).getPetVO().getPetId();
%>
<%
PetWeightVO recentWgt = (PetWeightVO) request.getAttribute("recentWgt");
BigDecimal averageWgt = (BigDecimal) request.getAttribute("averageWgt");
%>
<%
PetWeightVO pwVO = (PetWeightVO)request.getAttribute("recentWgt");
Date recent = pwVO.getRecordTime();
String startTime = "";
Calendar cal = Calendar.getInstance();
if(recent != null){
cal.setTime(recent);
cal.add(Calendar.MONTH, -3);
cal.add(Calendar.DATE, 1);
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
startTime = sdf.format(cal.getTime())+" ~ ";
}
%>
<!-- Recent Weight -->
<div class="col-xxl-4 col-xl-12">

	<div class="card info-card customers-card border-bottom-primary">

		<div class="top_links filter">
			<a class="icon" href="javascript:void(0)"><i
				class="bi bi-three-dots"></i></a>
			<div class="dropdown_links filtermenu">
				<div class="dropdown_links_list">
					<ul>
						<li><a href="<%=request.getContextPath()%>/weight?action=goToInsert&petId=<%=petId%>" class="dropdown-item">新增紀錄</a></li>
						<c:if test="${recentWgt.weightRecord != null}">
						<li><a href="<%=request.getContextPath()%>/weight?action=all_Display&petId=<%=petId%>" class="dropdown-item">查看更多</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>

		<div class="card-body">
			<h5 class="card-title">
				體重紀錄 
			</h5>
			<c:if test="${recentWgt.weightRecord == null}">
			<div class='text-secondary'>(無紀錄)</div>
			</c:if>
			<div class="row weight-card">
                <div class="col-lg-6">
                  <div class="card bg-white text-black">
						<div class="card-body">
							<div class='row'>
							<div class="col-lg-4 align-self-center">
						  		<div class="card-icon rounded-circle d-flex align-items-center justify-content-center" style="height:100px;width:100px;filter:hue-rotate(90deg);">
						    		<i class="fas fa-history" style='font-size:1.4em'></i>
						  		</div>
						  	</div>
						  	<div class="col-lg-8">
						  	<h5 class="card-title font-weight-bold" style='text-align:center'>最近一次體重</h5>
						    	<h6 style='text-align:center'>${recentWgt.weightRecord} kg</h6>
						    	<br/>
								<p style='text-align:center' class="text-muted small pt-2 ps-1">${recentWgt.recordTime}</p>
						  </div>
						  </div>
						</div>
                    </div>
                </div>
                
                <div class="col-lg-6">
                    <div class="card bg-white text-black">
						<div class="card-body">
							
							<div class='row'>
							<div class="col-lg-4 align-self-center">
						  		<div class="card-icon rounded-circle d-flex align-items-center justify-content-center" style="height:100px;width:100px;filter:hue-rotate(210deg);">
						    		<i class="fas fa-chart-bar" style='font-size:1.4em'></i>
						  		</div>
						  	</div>
						  	<div class="col-lg-8">
						  	<h5 class="card-title font-weight-bold" style='text-align:center'>本季平均體重</h5>
						    	<h6 style='text-align:center'><%=averageWgt%> kg</h6>
						    	<br/>
								<p style='text-align:center' class="text-muted small pt-2 ps-1"><%=startTime%>${recentWgt.recordTime}</p>
						  </div>
						  </div>
						</div>
                    </div>
                </div>
			</div>
			<div class="row weight-card">
				<div class="col-lg-12">
				 <div class="panel-body">
                     <div id="morris-line-chart"></div>
                 </div>
				</div>
			</div>
		</div>

	</div>
</div>
<!-- End Recent Weight -->