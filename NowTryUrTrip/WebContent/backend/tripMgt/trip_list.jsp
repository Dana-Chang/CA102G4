<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	TripService tripSvc = new TripService();
	List<TripVO> list = tripSvc.getAll();
	pageContext.setAttribute("list", list);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	Map<String,String> typeMap = new HashMap<String,String>();
	Map<String,String> isPublicMap = new HashMap<String,String>();
	typeMap.put("0", "一般行程");
	typeMap.put("1", "店家行程");
	typeMap.put("2", "平台優選");
	isPublicMap.put("0", "私人");
	isPublicMap.put("1", "公開");
	pageContext.setAttribute("typeMap", typeMap);
	pageContext.setAttribute("isPublicMap", isPublicMap);
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
<script src="lib/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="lib/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="ckeditor/contents.css">

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
				<li class="active">行程列表</li>
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
			<div class="btn-toolbar list-toolbar">
				<a href='addTrip.jsp'>加入新行程</a>
				<div class="btn-group">
					<button class="btn btn-default">匯入行程</button>
					<button class="btn btn-default">上架行程</button>
				</div>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>選擇</th>
						<th>行程編號</th>
						<th>會員編號</th>
						<th>行程名稱</th>
						<th>出發時間</th>
						<th>行程類型</th>
						<th>行程上架時間</th>
						<th>行程下架時間</th>
<!-- 						<th>行程星等</th> -->
						<th>行程是否公開</th>
						<th>行程價格</th>
						<th>行程廣告照片</th>
						<th>行程介紹</th>
						<th>行程內容</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
				</thead>
				<tbody>
					<%@ include file="page1.file"%>
					<c:forEach var="tripVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr align='center' valign='middle'>
							<td style="text-align: center;"><input type="checkbox" /></td>
							<td>${tripVO.tripNo}</td>
							<td>${tripVO.memId}</td>
							<td>${tripVO.tripName}</td>
							<td>${tripVO.departTime}</td>
							<td>${typeMap.get(tripVO.tripType)}</td>
							<td><fmt:formatDate value="${tripVO.tripAddTime}" pattern="yyyy/MM/dd HH:mm" /></td>
							<td><fmt:formatDate value="${tripVO.tripRmvTime}" pattern="yyyy/MM/dd HH:mm" /></td>
<%-- 							<td>${tripVO.tripRate}</td> --%>
							<td>${isPublicMap.get(tripVO.tripIsPublic)}</td>
							<td>${tripVO.tripPrice}</td>
							<td>
								<img src="<%=request.getContextPath() %>/BackTripImgReader.do?tripNo=${tripVO.tripNo }" width=250px>
							</td>
							<td>${tripVO.tripDesc}</td>
							<td>${tripVO.tripContent}</td>
							<td>
								<FORM METHOD="post" ACTION="BackTrip.do">
									<button type="submit">
										<i class="fa fa-pencil"></i>
									</button>
									<input type="hidden" name="tripNo" value="${tripVO.tripNo}">
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<button data-target="#myModal" data-toggle="modal"
									onclick="deleteFunction(${tripVO.tripNo})">
									<i class="fa fa-trash-o"></i>
								</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<%@ include file="page2.file"%>
			<!-- 彈跳確認刪除視窗 -->
			<div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h3 id="myModalLabel">Delete Confirmation</h3>
						</div>
						<div class="modal-body">
							<p class="error-text">
								<i class="fa fa-warning modal-icon"></i>你確定要刪除行程嗎?刪除之後無法回復喔!
							</p>
						</div>
						<div class="modal-footer">
							<FORM METHOD="post" ACTION="BackTrip.do" style="float: right">
								<button class="btn btn-danger" type="submit">Delete</button>
								<input type="hidden" name="tripNo" value="">
								<input type="hidden" name="action" value="delete">
							</FORM>
							<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancel</button>
						</div>
					</div>
				</div>
			</div>
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
</body>
</html>
<script type="text/javascript">
	function deleteFunction(e){
		$(".modal-footer input[name='tripNo']").val(e);
	}
</script>

