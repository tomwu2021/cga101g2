<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pet_weight.model.*"%>
<%
PetWeightVO recentWgt = (PetWeightVO) request.getAttribute("recentWgt");
BigDecimal averageWgt = (BigDecimal) request.getAttribute("averageWgt");
%>
<!-- Recent Weight -->
<div class="col-xxl-4 col-xl-12">

	<div class="card info-card customers-card">

		<div class="top_links filter">
			<a class="icon" href="javascript:void(0)"><i
				class="bi bi-three-dots"></i></a>
			<div class="dropdown_links filtermenu">
				<div class="dropdown_links_list">
					<ul>
						<li><a href="#" class="dropdown-item">新增紀錄</a></li>
						<li><a href="#" class="dropdown-item">查看更多</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="card-body">
			<h5 class="card-title">
				體重資訊 
			</h5>
			<div class="row">
				<div class="col-lg-6">
                    <div class="card bg-warning text-black shadow">
                    	最近一筆
                                <div class="card-body">
                            ${recentWgt.weightRecord}
                            <div class="text-white-50 small">${recentWgt.recordTime}</div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="card bg-warning text-black shadow">
	                平均體重
                        <div class="card-body">
                            <%=averageWgt%>
                            <%
                            PetWeightVO pwVO = (PetWeightVO)request.getAttribute("recentWgt");
                            Date recent = pwVO.getRecordTime();
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(recent);
                            cal.add(Calendar.MONTH, -3);
                            cal.add(Calendar.DATE, 1);
                            recent = cal.getTime();
                            %>
                            <div class="text-white-50 small"><%=recent%></div>
                        </div>
                    </div>
                </div>
			</div>
			<div class="row">
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