<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 改變icon大小 fa-4x	 -->
<!-- 改變icon顏色 span	 -->

<!--! Cart svg設定  -->
<div style="position: fixed; bottom: 200px; right: 120px; z-index: 10;">
	<a href="<%=request.getContextPath()%>/??????.jsp" style="color:#CE0000	;"> 
	<span class="glyphicon glyphicon-usd text-danger"> 
	<i class="bi bi-cart4 fa-3x"></i>
	</span>
	</a>
</div>
<!-- !Cartsvg設定 end  -->
<!--! wishlis設定  -->
<div style="position: fixed; bottom: 120px; right: 120px; z-index: 10;">
	<span class="glyphicon glyphicon-usd text-danger"> 
	<a href="<%=request.getContextPath()%>/front/shop/cartAndWishlist.jsp" title="前往收藏清單" style="color:#CE0000;" >
	 <i class="bi bi-calendar2-heart-fill fa-3x"></i>
	</a>
	</span>
</div>
<!-- !Mwishlis設定 end  -->

