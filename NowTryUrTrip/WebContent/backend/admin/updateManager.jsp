<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="managerSvc" scope="page"
	class="com.manager.model.ManagerService" />
<%
	ManagerVO managerVO = (ManagerVO) session.getAttribute("managerVO");
	ManagerVO loginManagerVO = (ManagerVO) session.getAttribute("loginManagerVO");
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Bootstrap Admin</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/admin/Login/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/admin/Login/lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/admin/Login/stylesheets/theme.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/admin/Login/stylesheets/premium.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/backend/admin/sweetalert.css">
<script src="<%=request.getContextPath()%>/backend/admin/Login/lib/sweetalert.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/backend/admin/Login/lib/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/backend/admin/Login/lib/jQuery-Knob/js/jquery.knob.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$(".knob").knob();
	});
</script>

</head>
<body class=" theme-blue">

	<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.navbar-default .navbar-brand, .navbar-default .navbar-brand:hover {
	color: #fff;
}
</style>
	<script type="text/javascript">
		$(function() {
			var uls = $('.sidebar-nav > ul > *').clone();
			uls.addClass('visible-xs');
			$('#main-menu').append(uls.clone());
		});
	</script>

	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="" href="index.html"><span class="navbar-brand"><span
					class="fa fa-paper-plane"></span> NowTryUrTrip</span></a>
		</div>

		<div class="navbar-collapse collapse" style="height: 1px;">
			<ul id="main-menu" class="nav navbar-nav navbar-right">
				<li class="dropdown hidden-xs"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <span
						class="glyphicon glyphicon-user padding-right-small"
						style="position: relative; top: 3px;"></span>
						${loginManagerVO.mgrName},你好!<i class="fa fa-caret-down"></i>
				</a>

					<ul class="dropdown-menu">
						<li><a href="./">My Account</a></li>
						<li class="divider"></li>
						<li><a href="./">Users</a></li>
						<li><a href="./">修改管理員資訊</a></li>
						<li><a tabindex="-1" href="./">Payments</a></li>
						<li class="divider"></li>
						<li><form role="form" action="<%=request.getContextPath()%>/Manager/mgr.do?action=logout">
							<button class="btn btn-primary pull-right" type="submit">登出</button>
							<input type="hidden" name="action" value="logout"></form>
					</ul></li>
			</ul>

		</div>
	</div>

	<%@ include file="/backend/admin/Login/indexMenu.jsp" %>

	<div class="content">
		<div class="header">
			<div class="stats"></div>

			<h1 class="page-title">Dashboard</h1>
			<ul class="breadcrumb">
				<li><a href="index.html">Home</a></li>
				<li class="active">Dashboard</li>
			</ul>

		</div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
					<script>swal("${message}", "", "error")</script>
					</c:forEach>
				</ul>
		</c:if>
		<div class="main-content">

			<div class="dialog">
				<div class="panel panel-default">
					<p class="panel-heading no-collapse">修改管理員帳號</p>
					<div class="panel-body">
						<form method="post" class="form-signin" role="form"
							action="<%=request.getContextPath()%>/Manager/mgr.do?action=update">
							<div class="form-group">
								<label>管理員編號:</label> <br>
									<a><%=loginManagerVO.getMgrId()%></a>
							</div>
							<div class="form-group">
								<label>管理員姓名</label> <input name="mgrName" type="text" class="form-control span12"
									value="<%=loginManagerVO.getMgrName()%>" />
							</div>
							<div class="form-group">
								<label>管理員帳號</label> <input name="mgrAccount" type="text" class="form-control span12"
									value="<%=loginManagerVO.getMgrAccount()%>" />
							</div>
							<div class="form-group">
								<label>管理員密碼</label> <input name="mgrPsw" type="text" class="form-control span12"
									value="<%=loginManagerVO.getMgrPsw()%>" />
							</div>
							<div class="form-group">
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="mgrId"  value="<%=loginManagerVO.getMgrId()%>">
								<input name="mgrIdentity" type="hidden" value="<%=loginManagerVO.getMgrIdentity()%>" />
								<input name="mgrStatus" type="hidden" value="<%=loginManagerVO.getMgrStatus()%>" />
								<button class="btn btn-primary pull-right" type="submit">送出修改</button>
							</div>
						</form>
					</div>
				</div>
				<p>
					<a href="privacy-policy.html"
						style="font-size: .75em; margin-top: .25em;">Privacy Policy</a>
				</p>
			</div>


			<footer>
				<hr>

				<p class="pull-right">
					A <a href="http://www.portnine.com/bootstrap-themes"
						target="_blank">Free Bootstrap Theme</a> by <a
						href="http://www.portnine.com" target="_blank">Portnine</a>
				</p>
				<p>
					© 2014 <a href="http://www.portnine.com" target="_blank">Portnine</a>
				</p>
			</footer>
		</div>
	</div>


	<script
		src="<%=request.getContextPath()%>/backend/admin/Login/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
	</script>
	</body>
</html>
