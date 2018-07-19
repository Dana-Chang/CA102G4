<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="managerSvc" scope="page"
	class="com.manager.model.ManagerService" />
<%
	ManagerVO mgrVO = (ManagerVO) session.getAttribute("managerVO");
	ManagerVO loginManagerVO = (ManagerVO) session.getAttribute("loginManagerVO");
%>
<%
	ManagerService mgrSvc = new ManagerService();
	List<ManagerVO> list = mgrSvc.getAll();
	pageContext.setAttribute("list", list);
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

<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backend/admin/Login/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/backend/admin/listAllManagerIdentity.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backend/admin/Login/lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backend/admin/Login/stylesheets/theme.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backend/admin/Login/stylesheets/premium.css">
<script
	src="<%=request.getContextPath()%>/backend/admin/Login/lib/jquery-1.11.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/backend/admin/Login/lib/jQuery-Knob/js/jquery.knob.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$(".knob").knob();
	});
	
// $( document ).ready(function() {
// 	var manager = $('#manager').val();
// 	var purchasing = $('#purchasing').val();
// 	var stroke = $('stroke').val();
	
	
// 	$("#mgrIdentity option[value=1]").attr("selected",true);
	
// }
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
						<li><form role="form"
								action="<%=request.getContextPath()%>/Manager/mgr.do?action=logout">
								<button class="btn btn-primary pull-right" type="submit">登出</button>
								<input type="hidden" name="action" value="logout">
							</form>
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
		<div class="main-content">

			<table border='1' cellpadding='5' cellspacing='0' width='800'>
				<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
					<td>
						<h3>所有管理員資料</h3>
					</td>
				</tr>
			</table>


			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font color='red'>請修正以下錯誤:
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</font>
			</c:if>

			<table border='1' bordercolor='#CCCCFF' width='800'>
				<tr>
					<th>管理員編號</th>
					<th>管理員姓名</th>
					<th>管理員帳號</th>
					<th>管理員權限</th>
					<th>管理員帳號狀態</th>
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="mgrVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Manager/mgr.do">
					<tr align='center' valign='middle'>
						<td>${mgrVO.mgrId}</td>
						<td>${mgrVO.mgrName}</td>
						<td>${mgrVO.mgrAccount}</td>
						<td><select name="mgrIdentity" class="form-control span12" id="mgrIdentity">
						<c:if test="${mgrVO.mgrIdentity == 1}">
									<option value="${mgrVO.mgrIdentity}">請選擇</option>									
									<option id="manager" value="1" selected>總管理員</option>
									<option id="purchasing" value="2">代購管理員</option>
									<option id="stroke" value="3">行程規劃管理員</option>
						</c:if>
						<c:if test="${mgrVO.mgrIdentity == 2}">
									<option value="${mgrVO.mgrIdentity}">請選擇</option>									
									<option id="manager" value="1">總管理員</option>
									<option id="purchasing" value="2" selected>代購管理員</option>
									<option id="stroke" value="3">行程規劃管理員</option>
						</c:if>
						<c:if test="${mgrVO.mgrIdentity == 3}">
									<option value="${mgrVO.mgrIdentity}">請選擇</option>									
									<option id="manager" value="1">總管理員</option>
									<option id="purchasing" value="2">代購管理員</option>
									<option id="stroke" value="3" selected>行程規劃管理員</option>
						</c:if>
								</select></td>
						<td>${mgrVO.mgrStatus}</td>
						<td><label class="custom-control custom-checkbox"> 
						<input type="checkbox" class="custom-control-input"> 
						<input type="hidden" name="mgrStatus" value="${mgrVO.mgrStatus}" class="custom-control-input"> 
						<span class="custom-control-indicator"></span>
						</label></td>
						<td>
								<input type="submit" value="修改">
								<input type="hidden" name="mgrId" value="${mgrVO.mgrId}">
								<input name="mgrName" type="hidden" value="${mgrVO.mgrName}" />
								<input name="mgrAccount" type="hidden" value="${mgrVO.mgrAccount}" />
								<input name="mgrPsw" type="hidden" value="${mgrVO.mgrPsw}" />
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
								<input type="hidden" name="action" value="update">
						</td>
					</tr>
							</FORM>
				</c:forEach>
			</table>
			<%@ include file="page2.file"%>



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
