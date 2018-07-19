<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spotCmnt.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	SpotCmntVO spotCmntVO = (SpotCmntVO) request.getAttribute("spotCmntVO");
%>
<html>
<head>
<title>景點評論資料清單</title>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
<link rel="stylesheet" type="text/css" href="stylesheets/premium.css">
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
<script src="lib/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="lib/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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
</head>

<body class=" theme-blue">
<!--    上方navbar及縮小後的navbar    -->
	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a href="index.html">
				<span class="navbar-brand">
					<span class="fa fa-paper-plane"></span> NowTryUrTrip
				</span>
			</a>
		</div>
		<div class="navbar-collapse collapse" style="height: 1px;">
			<ul id="main-menu" class="nav navbar-nav navbar-right">
				<li class="dropdown hidden-xs">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<span class="glyphicon glyphicon-user padding-right-small" style="position: relative; top: 3px;"></span>${managerVO.mgrName},你好!<i class="fa fa-caret-down"></i>
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
								<input type="hidden" name="action" value="logout">
							</form>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
<!-- 	側邊攔     -->
	<%@ include file="/backend/admin/Login/indexMenu.jsp" %>
<!-- --------------------navbar結束------------------------- -->
<!-- --------------------內容本體------------------------- -->
	<div class="content">
	<!-- 麵包屑 -->
		<div class="header">
			<h1 class="page-title">景點管理</h1>
			<ul class="breadcrumb">
				<li>
					<a href="spotCmntList.jsp">景點評論列表</a>
				</li>
				<li class="active">景點評論修改</li>
			</ul>
		</div>
	<!-- 錯誤回報區 -->
		<div>
			<c:if test="${not empty errorMsgs}">
				<font color='red'>請修正以下錯誤:
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</font>
			</c:if>
		</div>
		<FORM METHOD="post" ACTION="backSpotCmnt.do" name="form1" enctype="multipart/form-data">
			<table border="0">
				<tr>
					<td>景點評論編號:<font color=red><b>*</b></font></td>
					<td><%=spotCmntVO.getSpotCmntNo()%></td>
				</tr>
				<tr>
					<td>會員ID:</td>
					<td>
						<input type="TEXT" name="memId" value="<%=spotCmntVO.getMemId()%>" />
					</td>
				</tr>
				<tr>
					<td>景點編號:</td>
					<td>
						<input type="TEXT" name="spotNo" value="<%=spotCmntVO.getSpotNo()%>" />
					</td>
				</tr>
				<tr>
					<td>評論內容:</td>
					<td>
						<input type="TEXT" name="cmnt" value="<%=spotCmntVO.getCmnt()%>" />
					</td>
				</tr>
				<tr>
					<td>評論時間:</td>
					<td>
						<fmt:formatDate value="${spotCmntVO.cmntTime}" pattern="yyyy/MM/dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<td>地點給分:</td>
					<td>
						<input type="TEXT" name="rate" value="<%=spotCmntVO.getRate()%>" />
					</td>
				</tr>
				<tr>
					<td>是否檢查:</td>
					<td>
						<input type="text" name="isChecked" value="<%=spotCmntVO.getIsChecked()%>" />
					</td>
				</tr>
				<tr>
					<td>是否屏蔽:</td>
					<td>
						<input type="TEXT" name="isBlocked" value="<%=spotCmntVO.getIsBlocked()%>" />
					</td>
				</tr>
				<tr>
					<td>屏蔽原因:</td>
					<td>
						<input type="TEXT" name="blockedReason" value="<%=spotCmntVO.getBlockedReason()%>" />
					</td>
				</tr>
			</table>
			<br>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="spotCmntNo" value="<%=spotCmntVO.getSpotCmntNo()%>">
			<input type="submit" value="送出修改">
		</FORM>
	</div>
</body>
</html>
