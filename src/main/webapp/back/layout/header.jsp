<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
    function getContextPath(){
     return "<%=request.getContextPath()%>";
	}
</script>

    <!-- header-->
    <header class="header">
         <!-- !nav将默认的bg-light navbar-light改为bg-dark navbar-dark 
        a将默认的text-black改为text-light -->
      <nav class="navbar navbar-expand-lg px-4 py-2 bg-dark shadow" >
      <a href="#" class="sidebar-toggler text-gray-500 mr-4 mr-lg-5 lead">
      <i class="fas fa-align-left"></i></a>
      <a href="back_index.html" class="navbar-brand font-weight-bold text-uppercase text-white">後  臺  系  統 </a>
        <ul class="ml-auto d-flex align-items-center list-unstyled mb-0">
          <li class="nav-item dropdown ml-auto btn btn-secondary"><a id="userInfo" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle text-white">${(empVO.empName==null?"尚未登入":"使用者： ")}${empVO.empName}</a>
            <div aria-labelledby="userInfo" class="dropdown-menu"><a href="#" class="dropdown-item"><strong class="d-block text-uppercase headings-font-family">${empVO.empName}</strong><small>管理員</small></a>
              <div class="dropdown-divider"></div><a href="login.html" class="dropdown-item">Logout</a>
            </div>
          </li>
        </ul>
      </nav>
    </header>
<!-- header-->
</body>
