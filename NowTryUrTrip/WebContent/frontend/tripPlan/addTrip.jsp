<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%
	Date now = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	TripVO tripVO = (TripVO) request.getAttribute("tripVO");
	MemberVO memberVo = (MemberVO)session.getAttribute("memberVO");
	Map<String, String> memTypeMap = new HashMap<String, String>();
	memTypeMap.put("0", "一般會員");
	memTypeMap.put("1", "店家");
	pageContext.setAttribute("memTypeMap", memTypeMap);
%>
<head>
<title>新增行程</title>
<meta charset="utf8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="jquery.datetimepicker.css"/>
<script src="jquery.datetimepicker.full.js"></script>
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
<!-- --------------------內容本體------------------------- -->
	<div class="content">
		<!-- 麵包屑 -->
<!-- 		<div class="header"> -->
<!-- 			<h1 class="page-title">行程管理</h1> -->
<!-- 			<ul class="breadcrumb"> -->
<!-- 				<li> -->
<%-- 					<a href="<%=request.getContextPath()%>/backend/index.jsp">首頁</a> --%>
<!-- 				</li> -->
<!-- 				<li class="active">新增行程</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
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
						<!-- 第一頁內容 -->
						<div class="tab-pane active in" id="tripData">
							<FORM METHOD="post" ACTION="BackTrip.do" id="tab" name="tripData" enctype="multipart/form-data">
								<table style="border:1px solid black; width: 100%">
									<tr>
										<td>團長編號:</td>
										<td><input type="TEXT" name="memId" size="4" value="<%=(tripVO == null) ? "1234" : tripVO.getMemId()%>" /></td>
									</tr>
									<tr>
										<td>行程名稱:</td>
										<td><input type="TEXT" name="tripName" value="<%=(tripVO == null) ? "中央大學露營趣" : tripVO.getTripName()%>" /></td>
									</tr>
									<tr>
										<td>出發時間:</td>
										<td><input type="text" class="dateTime" value="<%=tripVO == null ? sdf.format(now) : sdf.format(tripVO.getDepartTime())%>" id="departTime" name="departTime" /></td>
									</tr>
									<tr>
										<td>行程類型:</td>
										<td><input type="text" name="tripType" size="1" value="<%=(tripVO == null) ? "1" : tripVO.getTripType()%>" /></td>
									</tr>
									<tr>
										<td>上架時間</td>
										<td><input type="text" class="dateTime" value="<%=tripVO == null ? sdf.format(now) : sdf.format(tripVO.getTripAddTime())%>" id="tripAddTime" name="tripAddTime" /></td>
									</tr>
									<tr>
										<td>下架時間</td>
										<td><input type="text" class="dateTime" value="<%=tripVO == null ? sdf.format(now) : sdf.format(tripVO.getTripRmvTime())%>" id="tripRmvTime" name="tripRmvTime" /></td>
									</tr>
									<tr>
										<td>行程評分:</td>
										<td><input type="text" name="tripRate" size="1" value="<%=(tripVO == null) ? "9" : tripVO.getTripRate()%>" /></td>
									</tr>
									<tr>
										<td>行程是否公開:</td>
										<td><input type="text" name="tripIsPublic" size="1" value="<%=(tripVO == null) ? "1" : tripVO.getTripIsPublic()%>" /></td>
									</tr>
									<tr>
										<td>行程價位:</td>
										<td><input type="text" name="tripPrice" value="<%=(tripVO == null) ? "10000" : tripVO.getTripPrice()%>" /></td>
									</tr>
									<tr>
										<td>行程圖片:</td>
										<td><input type="file" name="tripAdImg" id="imgInp" value="<%=(tripVO == null) ? null : tripVO.getTripAdImg()%>" />
											<img id="blah" src="../items/nopic.gif" alt="目前沒有圖片" width=250px /></td>
									</tr>
									<tr>
										<td>行程描述:</td>
										<td><input type="TEXT" name="tripDesc" size="45" value="<%=(tripVO == null) ? "中央大學很好玩低" : tripVO.getTripDesc()%>" /></td>
									</tr>
									<tr>
										<td>行程內容:</td>
										<td><input type="TEXT" name="tripContent" value="<%=(tripVO == null) ? null : tripVO.getTripContent()%>" /></td>
									</tr>
								</table>
								<br>
								<input type="hidden" name="action" value="insert">
								<input type="submit" value="送出新增">
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
</body>
</html>
<script>
	// 挑選時間的api
	$('.dateTime').datetimepicker();
	//顯示圖片
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#blah').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	//顯示圖片
	$("#imgInp").change(function() {
		readURL(this);
	});
</script>