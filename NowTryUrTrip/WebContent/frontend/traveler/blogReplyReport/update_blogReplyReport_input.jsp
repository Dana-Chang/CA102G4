<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blogReplyReport.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
BlogReplyReportVO blogReplyReportVO = (BlogReplyReportVO) request.getAttribute("blogReplyReportVO");
	Map<String,String> typeMap = new HashMap<String,String>();
	typeMap.put("0", "未審閱");
	typeMap.put("1", "已審閱");
	pageContext.setAttribute("typeMap", typeMap);
%>
<head>
<title>部落格文章檢舉處理</title>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/admin/Login/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/admin/Login/lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/admin/Login/stylesheets/theme.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/admin/Login/stylesheets/premium.css">
<script src="<%=request.getContextPath()%>/backend/admin/Login/lib/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/backend/admin/Login/lib/jQuery-Knob/js/jquery.knob.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

th {
	text-align:center;
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
	<%@ include file="/backend/blog/indexMenu.jsp" %>
<!-- --------------------navbar結束------------------------- -->
<!-- --------------------內容本體------------------------- -->
	<div class="content">
		<!-- 麵包屑 -->
		<div class="header">
			<h1 class="page-title">文章檢舉管理</h1>
			<ul class="breadcrumb">
				<li>
					<a href="index.html">首頁</a>
				</li>
				<li class="active">文章檢舉管理</li>
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
<FORM METHOD="post" ACTION="blogReplyReport.do" name="form1">
		<div class="main-content">
			<table class="table">
				<thead>
					<tr>
						<th>選擇</th>
						<th>檢舉編號</th>
						<th>回覆編號</th>
						<th>檢舉會員編號</th>
						<th>檢舉原因</th>
						<th>檢舉時間</th>
						<th>管理員審閱</th>
						<th>不屏蔽原因</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					
						<tr align='center' valign='middle'>
							<td style="text-align: center;"><input type="checkbox" /></td>
							<td>${blogReplyReportVO.replyReportNo}</td>
							<td>${blogReplyReportVO.replyNo}</td>
							<td>${blogReplyReportVO.memId}</td>
							<td>${blogReplyReportVO.rpReason}</td>
							<td><fmt:formatDate value="${blogReplyReportVO.rpTime}" pattern="yyyy/MM/dd HH:mm" /></td>
							<td><input type="TEXT" name="adminChecked" size="1"	value="${blogReplyReportVO.adminChecked}" /></td>
							<td><input type="TEXT" name="noRpReason" size="45" value="${blogReplyReportVO.noRpReason}" /></td>
							<td><input type="hidden" name="action" value="update">
							<input type="hidden" name="replyReportNo" value="<%=blogReplyReportVO.getReplyReportNo()%>">
							<input class="btn btn-default" type="submit" value="送出修改">
							</td>
							</FORM>
						</tr>
				</tbody>
			</table>
		</div>
		<footer>
			<hr>
			<!-- Purchase a site license to remove this link from the footer: http://www.portnine.com/bootstrap-themes -->
			<p class="pull-right">
				A 
				<a href="http://www.portnine.com/bootstrap-themes" target="_blank">something footer here</a> by 
				<a href="http://www.portnine.com" target="_blank">something footer here</a>
			</p>
			<p>
				© something footer here
				<a href="http://www.portnine.com" target="_blank">
					something footer here
				</a>
			</p>
		</footer>
	</div>
		
		<script
		src="<%=request.getContextPath()%>/backend/admin/Login/lib/bootstrap/js/bootstrap.js"></script>
</body>
</html>
<script type="text/javascript">
	function deleteFunction(e){
		$(".modal-footer input[name='tripNo']").val(e);
	}
</script>

