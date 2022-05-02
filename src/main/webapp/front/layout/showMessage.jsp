<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	
<%if (request.getAttribute("errorMsg") != null) {
	String alert = "Swal.fire({" + " icon:'error'," + " title: 'Oops...'," + " text:'"
			+ request.getAttribute("errorMsg") + "'," + " })";
	out.write(alert);
	request.setAttribute("errorMsg", null);
}%>
	
<%if (request.getAttribute("msg") != null) {
	String alert = "Swal.fire({" + " icon:'success'," + " title: '" + request.getAttribute("msg") + "'" + " })";
	out.write(alert);
	request.setAttribute("msg", null);
}%>
	
</script>