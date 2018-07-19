<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>NowTryUrTrip</title>
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
<script src="<%=request.getContextPath()%>/backend/admin/Login/lib/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/backend/admin/Login/lib/sweetalert.min.js" type="text/javascript"></script>

</head>
<body class=" theme-blue">
	<script type="text/javascript">
		$(function() {
			var match = document.cookie.match(new RegExp('color=([^;]+)'));
			if (match)
				var color = match[1];
			if (color) {
				$('body').removeClass(function(index, css) {
					return (css.match(/\btheme-\S+/g) || []).join(' ')
				})
				$('body').addClass('theme-' + color);
			}

			$('[data-popover="true"]').popover({
				html : true
			});
		});
	</script>
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
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
					<script>swal("${message}", "", "error")</script>
					</c:forEach>
				</ul>
		</c:if>

	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a class="" href="index.html"><span class="navbar-brand">
			<span class="fa fa-paper-plane"></span> NowTryUrTrip</span></a>
		</div>
		<div class="navbar-collapse collapse" style="height: 1px;">
		</div>
	</div>

	<div class="dialog">
		<div class="panel panel-default">
			<p class="panel-heading no-collapse">管理員登入</p>
			<div class="panel-body">
				<form method="post" class="form-signin" role="form" action="<%=request.getContextPath()%>/Manager/mgr.do?action=login">
					<div class="form-group">
						<label>Username</label> <input class="form-control" type="text" name="mgrAccount" placeholder="Account" required autofocus>
					</div>
					<div class="form-group">
						<label>Password</label> <input class="form-control" type="password" name="mgrPsw" placeholder="Password" required>
					</div>
					<button class="btn btn-primary pull-right" type="submit">登入</button>
					<input type="hidden" name="action" value="login">
				</form>
			</div>
		</div>
	</div>

	<script src="<%=request.getContextPath()%>/backend/admin/Login/lib/bootstrap/js/bootstrap.js"></script>
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
