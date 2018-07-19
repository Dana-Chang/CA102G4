<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spot.model.*"%>
<%
	SpotVO spotVO = (SpotVO) request.getAttribute("spotVO");
%>
<html>
<head>
<title>景點資料修改</title>
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
				<li>
					<a href="listAllSpot.jsp">景點列表</a>
				</li>
				<li class="active">景點修改</li>
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

		<FORM METHOD="post" ACTION="backSpot.do" name="form1" enctype="multipart/form-data">
			<table border="0">
				<tr>
					<td>景點編號:<font color=red><b>*</b></font></td>
					<td><%=spotVO.getSpotNo()%></td>
				</tr>
				<tr>
					<td>景點名稱:</td>
					<td>
						<input type="TEXT" name="spotName" value="<%=spotVO.getSpotName()%>" />
					</td>
				</tr>
				<tr>
					<td>景點地址:</td>
					<td>
						<input type="TEXT" name="spotAddr" value="<%=spotVO.getSpotAddr()%>" />
					</td>
				</tr>
				<tr>
					<td>景點類型:</td>
					<td>
						<select name="spotType">
							<option value="0" <%= spotVO.getSpotType().equals("0")?"selected":""%>>餐廳
							<option value="1" <%= spotVO.getSpotType().equals("1")?"selected":""%>>旅館
							<option value="2" <%= spotVO.getSpotType().equals("2")?"selected":""%>>景點
						</select>
					</td>
				</tr>
				<tr>
					<td>景點經度:</td>
					<td>
						<input type="text" name="spotLng" value="<%=spotVO.getSpotLng()%>" />
					</td>
				</tr>
				<tr>
					<td>景點緯度:</td>
					<td>
						<input type="TEXT" name="spotLat" value="<%=spotVO.getSpotLat()%>" />
					</td>
				</tr>
				<tr>
					<td>景點評分:</td>
					<td>
						<input type="text" name="spotRate" value="<%=spotVO.getSpotRate()%>"  />
					</td>
				</tr>
				<tr>
					<td>景點簡介:</td>
					<td>
						<input type="text" name="spotOverview" value="<%=spotVO.getSpotOverview()%>" />
					</td>
				</tr>
				<tr>
					<td>景點照片:</td>
					<td>
						<input type="file" name="spotPhoto" id="imgInp" />
						<img id="blah" src="<%=request.getContextPath() %>/BackSpotImgReader.do?spotNo=${spotVO.spotNo}" alt="目前沒有圖片" width=250px />
					</td>
				</tr>
				<tr>
					<td>景點擁有店家:</td>
					<td>
						<input type="TEXT" name="spotOwner" value="<%=spotVO.getSpotOwner()==null?0:spotVO.getSpotOwner()%>" />
					</td>
				</tr>
				<tr>
					<td>是否檢查:</td>
					<td>
						<select name="spotChk">
							<option value="0" <%= spotVO.getSpotChk().equals("0")?"selected":""%>>未審核
							<option value="1" <%= spotVO.getSpotType().equals("1")?"selected":""%>>已審核
						</select>
					</td>
				</tr>
				<tr>
					<td>是否處理:</td>
					<td>
						<select name="spotHdl">
							<option value="0" <%= spotVO.getSpotHdl().equals("0")?"selected":""%>>未處理
							<option value="1" <%= spotVO.getSpotHdl().equals("1")?"selected":""%>>已處理
						</select>
					</td>
				</tr>
				<tr>
					<td>是否被屏蔽:</td>
					<td>
						<select name="spotIsBlocked">
							<option value="0" <%= spotVO.getSpotIsBlocked().equals("0")?"selected":""%>>可見
							<option value="1" <%= spotVO.getSpotIsBlocked().equals("1")?"selected":""%>>已屏蔽
						</select>
					</td>
				</tr>
				<tr>
					<td>屏蔽原因:</td>
					<td>
						<input type="TEXT" name="spotBlockedReason" value="<%=spotVO.getSpotBlockedReason()%>" />
					</td>
				</tr>
			</table>
			<br>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="spotNo" value="<%=spotVO.getSpotNo()%>">
			<input type="submit" value="送出修改">
		</FORM>
	</div>
</body>
</html>
<script>
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

