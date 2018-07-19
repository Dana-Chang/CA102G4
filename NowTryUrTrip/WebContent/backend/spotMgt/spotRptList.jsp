<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.spotRpt.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	SpotRptService spotRptSvc = new SpotRptService();
	List<SpotRptVO> list = spotRptSvc.getAllSorted();
	pageContext.setAttribute("list", list);
	List<String> blockedReasonList = new ArrayList<String>();
	blockedReasonList.add("內容與實際情況不符");
	blockedReasonList.add("地點位置不符");
	blockedReasonList.add("已歇業");
	pageContext.setAttribute("blockedReasonList", blockedReasonList);
%>
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
					<a href="listAllSpot.jsp">景點列表</a>
				</li>
				<li class="active">景點檢舉列表</li>
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
		
		<table border='1' bordercolor='#CCCCFF' width='800'>
			<tr>
				<th>檢舉編號-檢舉</th>
				<th>會員編號</th>
				<th>會員名稱</th>
				<th>景點編號</th>
				<th>景點名稱</th>
				<th>檢舉原因</th>
				<th>檢舉時間</th>
				<th>是否屏蔽-景點</th>
				<th>是否處理過-景點</th>
				<th>屏蔽原因</th>
				<th>處理</th>
			</tr>
			<%@ include file="page1.file"%>
			<jsp:useBean id="spotSvc" scope="page" class="com.spot.model.SpotService"/>
			<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
			<c:forEach var="spotRptVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr align='center' valign='middle'>
					<td>${spotRptVO.rptNo}</td>
					<td>${spotRptVO.memId}</td>
					<td>${memberSvc.getOneMember(spotRptVO.memId).memName}</td>
					<td>${spotRptVO.spotNo}</td>
					<td>${spotSvc.getOneSpot(spotRptVO.spotNo).spotName}</td>
					<td>${spotRptVO.rptReason}</td>
					<td><fmt:formatDate value="${spotRptVO.rptTime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
					<td>${spotSvc.getOneSpot(spotRptVO.spotNo).spotIsBlocked}</td>
					<td>${spotSvc.getOneSpot(spotRptVO.spotNo).spotHdl}</td>
					<td>${spotSvc.getOneSpot(spotRptVO.spotNo).spotBlockedReason}</td>
					<td style="width:250px">
                    <!-- **********屏蔽********** -->
						<form class="form-inline" style="margin-top: 0px;float:left" action="backSpotRpt.do">
							<select name="spotBlockedReason">
								<c:forEach var = "blockedReason" items="${blockedReasonList}">
									<option value="${blockedReason}">${blockedReason}
								</c:forEach>
							</select>
							<button type="submit" class="btn btn-default">
								<font color="red"><i class="fa fa-ban" aria-hidden="true"></i>送出屏蔽</font>
							</button>
							<input type="hidden" name="action" value="set_To_Block">
							<input type="hidden" name="rptNo" value="${spotRptVO.rptNo}">
							<input type="hidden" name="spotIsBlocked" value="1">
							<input type="hidden" name="spotHdl" value="1">
						</form>
					 <!-- **********駁回********** -->
						<form class="form-inline" style="margin-top: 0px;float:left" action="backSpotRpt.do">
							<button type="submit" class="btn btn-default">
								<font color="loghtgreen"><i class="fa fa-check-circle" aria-hidden="true"></i>駁回</font>
							</button>
							<input type="hidden" name="action" value="set_To_Block">
							<input type="hidden" name="rptNo" value="${spotRptVO.rptNo}">
							<input type="hidden" name="spotIsBlocked" value="0">
							<input type="hidden" name="spotHdl" value="1">
							<input type="hidden" name="spotBlockedReason" value="">
						</form>
						<div style="clear:both"></div>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
<!-- 		<div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> -->
<!-- 			<div class="modal-dialog"> -->
<!-- 				<div class="modal-content"> -->
<!-- 					<div class="modal-header"> -->
<!-- 						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
<!-- 						<h3 id="myModalLabel">警告</h3> -->
<!-- 					</div> -->
<!-- 					<div class="modal-body"> -->
<!-- 						<p class="error-text"> -->
<!-- 							<i class="fa fa-warning modal-icon"></i> -->
<!-- 							您確定要刪除這筆資料嗎? <br> -->
<!-- 							刪除後便無法回復了 -->
<!-- 						</p> -->
<!-- 					</div> -->
<!-- 					<div class="modal-footer"> -->
<!-- 						<FORM METHOD="post" ACTION="backSpotCmnt.do" style="float: right"> -->
<!-- 							<button class="btn btn-danger" type="submit">Delete</button> -->
<!-- 							<input type="hidden" name="spotCmntNo" value=""> -->
<!-- 							<input type="hidden" name="action" value="delete"> -->
<!-- 						</FORM> -->
<!-- 						<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancel</button> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<footer>
			<hr>
			<!-- Purchase a site license to remove this link from the footer: http://www.portnine.com/bootstrap-themes -->
			<p class="pull-right">
				 A 
				<a href="http://www.portnine.com/bootstrap-themes" target="_blank">
					something footer here
				</a> by <a href="http://www.portnine.com" target="_blank">
					something footer here
				</a>
			</p>
			<p>
				© something footer here
				<a href="http://www.portnine.com" target="_blank">
					something footer here
				</a>
			</p>
		</footer>
	</div>
</body>
</html>
<!-- <script type="text/javascript"> -->
// 	function deleteFunction(e){
// 		$(".modal-footer input[name='spotCmntNo']").val(e);
// 	}
<!-- </script> -->