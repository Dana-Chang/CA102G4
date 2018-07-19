<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.proprietorArea.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    PprService pprSvc = new PprService();
    List<PprVO> list = pprSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>廣告資料清單</title>
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
			<h1 class="page-title">優惠訊息</h1>
			<ul class="breadcrumb">
				<li>
					<a href="index.html">首頁</a>
				</li>
				<li class="active">優惠訊息列表</li>
			</ul>
		</div>
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
		<th>廣告編號</th>
		<th>景點</th>
		<th>上架時間</th>
		<th>下架時間</th>
		<th>廣告內容</th>
		<th>廣告圖片</th>
		<!-- <th>修改</th> -->
		<th>刪除</th>				
	</tr>
	<%@ include file="pages/page1.file" %> 

	<jsp:useBean id="spotSvc" scope="page" class="com.spot.model.SpotService" />
	
		
			<c:forEach var="spotVO" items="${spotSvc.all}">
				<c:if test="${spotVO.spotNo==pprVO.pprSpotNo}">
					pprVO.pprSpotNo-${spotVO.spotName}
				</c:if>			
			</c:forEach>
		
	
		
	<c:forEach var="pprVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'}>
			<td>${pprVO.pprMsgeNo}</td>
			<td><c:forEach var="spotVO" items="${spotSvc.all}">
					<c:if test="${spotVO.spotNo==pprVO.pprSpotNo}">
					${spotVO.spotName}
					</c:if>			
				</c:forEach>
			</td>
			<td>${pprVO.pprCheckIn}</td>
			<td>${pprVO.pprCheckOut}</td>
			<td>${pprVO.pprMsgeCtx}</td>
			<td><img style="max-width: 200px; max-height:200px;" src="<%=request.getContextPath()%>/proprietorArea/pprImg.do?pprMsgeNo=${pprVO.pprMsgeNo}"/></td>			
<%-- 			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/proprietorArea/ppr.do">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="pprMsgeNo" value="${pprVO.pprMsgeNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td> --%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/proprietorArea/ppr.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="pprMsgeNo" value="${pprVO.pprMsgeNo}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>			
		</tr>
	</c:forEach>	
</table>
<%@ include file="pages/page2.file" %>

<%--  <br>本網頁的路徑:<br><b>
   <font color=blue>request.getContextPath():</font> <%=request.getContextPath()%><br>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>--%>
</div>   
</body>
</html>
