<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	TripVO tripVO = (TripVO) request.getAttribute("tripVO");
%>
<head>
<title>景點資料清單</title>
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
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" />
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<script src="ckeditor/ckeditor.js"></script>
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
			<h1 class="page-title">行程管理</h1>
			<ul class="breadcrumb">
				<li>
					<a href="index.html">首頁</a>
				</li>
				<li>
					<a href="trip_list.jsp">行程列表</a>
				</li>
				<li class="active">行程修改</li>
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
		<div class="main-content">
			<ul class="nav nav-tabs">
                <li class="active"><a href="#tripData" data-toggle="tab">行程資訊</a></li>
                <li><a href="#tripPlan" data-toggle="tab">行程規劃</a></li>
            </ul>
            <div class="row">
                <div class="col-md-8">
                	<br>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="#tripData">
							<FORM METHOD="post" ACTION="BackTrip.do" id="tab1" name="tripData" enctype="multipart/form-data">
								<table style="border:1px solid black; width: 100%">
									<tr>
										<td>行程編號:<font color=red><b>*</b></font></td>
										<td><%=tripVO.getTripNo()%></td>
									</tr>
									<tr>
										<td>會員編號:</td>
										<td><input type="text" name="memId" size="4" value="<%=tripVO.getMemId()%>" /></td>
									</tr>
									<tr>
										<td>行程名稱:</td>
										<td><input type="text" name="tripName" value="<%=tripVO.getTripName()%>" /></td>
									</tr>
									<tr>
										<td>出發日期:</td>
										<td>
											<div class="input-group">
												<input type='text' class="form-control" id='departTime' name="departTime" value="${sdf.format(tripVO.departTime)}"/>
												<span class="input-group-addon" id="clearDepart">
												<i class="fa fa-times-circle" aria-hidden="true"></i>
												</span>
											</div>
										</td>
									</tr>
									<tr>
										<td>行程類型:</td>
										<td><input type="text" name="tripType" size="1" value="<%=tripVO.getTripType()%>" /></td>
									</tr>
									<tr>
										<td>上架時間</td>
										<td>
											<div class="input-group">
												<input type='text' class="form-control" id='startTime' name="tripAddTime" value="${sdf.format(tripVO.tripAddTime)}" />
<%-- 												<input type='text' class="form-control" id='startTime' name="tripAddTime" value="<%= sdf.format(tripVO.getTripAddTime())%>" /> --%>
												<span class="input-group-addon" id="clearStart">
												<i class="fa fa-times-circle" aria-hidden="true"></i>
												</span>
											</div>
										</td>
									</tr>
									<tr>
										<td>下架時間</td>
										<td>
											<div class="input-group">
												<input type='text' class="form-control" id='endTime' name="tripRmvTime" value="${sdf.format(tripVO.tripRmvTime)}" />
<%-- 												<input type='text' class="form-control" id='endTime' name="tripRmvTime" value="<%= sdf.format(tripVO.getTripRmvTime())%>" /> --%>
												<span class="input-group-addon" id="clearEnd">
													<i class="fa fa-times-circle" aria-hidden="true"></i>
												</span>
											</div>
										</td>
									</tr>
									<tr>
										<td>行程評分:</td>
										<td><input type="text" name="tripRate" size="1" value="<%=tripVO.getTripRate()%>" /></td>
									</tr>
									<tr>
										<td>是否公開:</td>
										<td><input type="text" name="tripIsPublic" size="1" value="<%=tripVO.getTripIsPublic()%>" /></td>
									</tr>
									<tr>
										<td>行程價格:</td>
										<td><input type="text" name="tripPrice"  value="<%=tripVO.getTripPrice()%>" /></td>
									</tr>
									<tr>
										<td>行程廣告圖片:</td>
										<td>
											<input type="file" name="tripAdImg" id="imgInp" value="<%=tripVO.getTripAdImg()%>">
											<img id="blah" src="<%=request.getContextPath() %>/BackTripImgReader.do?tripNo=${tripVO.tripNo }" alt="目前沒有圖片" width=250px />
										</td>
									</tr>
									<tr>
										<td>行程描述:</td>
										<td>
											<textarea name="tripDesc" >
												<%=tripVO.getTripDesc()%>
											</textarea>
										</td>
									</tr>
									<tr>
										<td>行程內容:</td>
										<td><input type="text" name="tripContent" value="<%=tripVO.getTripContent()%>" /></td>
									</tr>
								</table>
								<br>
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="tripNo" value="<%=tripVO.getTripNo()%>">
								<button class="btn btn-primary" type="submit">
									<i class="fa fa-save"></i> 送出修改
								</button>
							</form>
							<form method="get" action="trip_list.jsp">
								<button class="btn btn-primary">
									<i class="fa fa-times"></i> 取消
								</button>
							</form>
						</div>
						<!-- 第二頁內容 -->
						<div class="tab-pane fade" id="tripPlan">
							<form id="tab2">
								這裡放排行程規畫的東東...所以會有張地圖給你選地點及排路線
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<hr>
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
</body>
</html>
<script>
$(function() {
	$('#departTime').datetimepicker({
		minDate: moment(),
		format : "YYYY/MM/DD HH:mm"
	});
	$('#startTime').datetimepicker({
		minDate: moment(),
		format : "YYYY/MM/DD HH:mm"
	});
	$('#endTime').datetimepicker({
		useCurrent: false, //Important! See issue #1075
		format : "YYYY/MM/DD HH:mm"
	});
	$("#departTime").on("dp.change", function(e) {
		$('#startTime').data("DateTimePicker").maxDate(e.date);
		$('#endTime').data("DateTimePicker").minDate(e.date);
	});
	$("#startTime").on("dp.change", function(e) {
		$('#endTime').data("DateTimePicker").minDate(e.date);
	});
	$("#endTime").on("dp.change", function(e) {
		$('#startTime').data("DateTimePicker").maxDate(e.date);
	});
	$("#clearDepart").click(function(){
	    $('#departTime').val("");
	});
	$("#clearStart").click(function(){
	    $('#startTime').val("");
	    $('#endTime').val("");
	});
	$("#clearEnd").click(function(){
	    $('#endTime').val("");
	});
	$("#endTime").blur(function(){
		if($('#startTime').val()!=''){
			$('#endTime').prop('required',true);
		}
	});
});
	$("#imgInp").change(function() {
		readURL(this);
	});
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#blah').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	CKEDITOR.replace('tripDesc');
</script>