<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spot.model.*"%>
<%
	SpotVO spotVO = (SpotVO) request.getAttribute("spotVO");
%>
<html>
<head>
<title>景點資料新增 - addSpot.jsp</title>
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
				<a href="<%=request.getContextPath() %>/backend/spotMgt/listAllSpot.jsp"">首頁</a>
			</li>
			<li class="active">新增景點</li>
		</ul>
	</div>
<!-- 內容區 -->
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>景點資料新增 - addSpot.jsp</h3>
			</td>
			<td>
				<a href="listAllSpot.jsp">
					<img src="../items/back.png" width="100" height="100" border="1">回首頁
				</a>
			</td>
		</tr>
	</table>
	<h3>新增景點資料:</h3>
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
	
	<FORM METHOD="post" ACTION="backSpot.do" name="form1" enctype="multipart/form-data">
		<table border="0">
			<tr>
				<td>景點名稱:</td>
				<td>
					<input type="TEXT" name="spotName" value="<%=(spotVO == null) ? "中壢火車站" : spotVO.getSpotName()%>" />
				</td>
			</tr>
			<tr>
				<td>景點地址:</td>
				<td>
					<input type="TEXT" name="spotAddr" value="<%=(spotVO == null) ? "桃園市中壢區石頭里中和路139號" : spotVO.getSpotAddr()%>" />
				</td>
			</tr>
			<tr>
				<td>景點類型:</td>
				<td>
					<select name="spotType">
						<option value="0" <%=(spotVO != null && spotVO.getSpotType()=="0" ) ? "selected" :""%>>景點
						<option value="1" <%=(spotVO != null && spotVO.getSpotType()=="0" ) ? "selected" :""%>>旅館
						<option value="2" <%=(spotVO != null && spotVO.getSpotType()=="0" ) ? "selected" :""%>>餐廳
					</select>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>景點經度:</td> -->
<!-- 				<td> -->
<%-- 					<input type="text" name="spotLng" value="<%=(spotVO == null) ? 123 : spotVO.getSpotLng()%>" /> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>景點緯度:</td> -->
<!-- 				<td> -->
<%-- 					<input type="TEXT" name="spotLat" value="<%=(spotVO == null) ? 23 : spotVO.getSpotLat()%>" /> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>景點評分:</td> -->
<!-- 				<td> -->
<%-- 					<input type="text" name="spotRate" value="<%=(spotVO == null) ? 8 : spotVO.getSpotRate()%>" /> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
			<tr>
				<td>景點簡介:</td>
				<td>
					<input type="text" name="spotOverview" value="<%=(spotVO == null) ? "阿勞集散地" : spotVO.getSpotOverview()%>" />
				</td>
			</tr>
			<tr>
				<td>景點照片:</td>
				<td>
					<input type="file" name="spotPhoto" id="imgInp" />
					<img id="blah" src="<%=request.getContextPath() %>/BackSpotImgReader.do?spotNo=${spotVO.spotNo }" alt="目前沒有圖片" width=250px />
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>景點擁有店家:</td> -->
<!-- 				<td> -->
<!-- 					<input type="TEXT" name="spotOwner" value="1" /> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>是否審核:</td> -->
<!-- 				<td> -->
<!-- 					<input type="TEXT" name="spotChk" value="0" /> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>是否處理:</td> -->
<!-- 				<td> -->
<!-- 					<input type="TEXT" name="spotHdl" value="0" /> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>是否被屏蔽:</td> -->
<!-- 				<td> -->
<!-- 					<input type="TEXT" name="spotIsBlocked" value="0" /> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>屏蔽原因:</td> -->
<!-- 				<td> -->
<!-- 					<input type="TEXT" name="spotBlockedReason" value="" /> -->
<!-- 				</td> -->
<!-- 			</tr> -->
		</table>
		<br>
		<input type="hidden" name="spotOwner" value="0" />
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
	</FORM>
<!-- --------------footer-------------- -->
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
</body>
</html>

<script>
// 上傳圖片會顯示圖片---------------
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#blah').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	$("#imgInp").change(function() {
		readURL(this);
	});
	
	
</script>

