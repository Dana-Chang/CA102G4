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
	String nowTime = sdf.format(now);
	pageContext.setAttribute("nowTime", nowTime);
	MemberVO memberVo = (MemberVO)session.getAttribute("memberVO");
	Map<String, String> memTypeMap = new HashMap<String, String>();
	memTypeMap.put("0", "一般會員");
	memTypeMap.put("1", "店家");
	pageContext.setAttribute("memTypeMap", memTypeMap);
%>
<head>
<title>新增行程</title>
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
<!-- <script src="lib/jquery-1.11.1.min.js" type="text/javascript"></script> -->
<!-- <script src="lib/bootstrap/js/bootstrap.js" type="text/javascript"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" href="lib/jquery.datetimepicker.css"/> -->
<!-- <script src="lib/jquery.datetimepicker.full.js"></script> -->
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
					<a href="<%=request.getContextPath()%>/backend/index.jsp">首頁</a>
				</li>
				<li class="active">新增行程</li>
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
						<!-- 第一頁內容 -->
						<div class="tab-pane active in" id="tripData">
							<FORM METHOD="post" ACTION="BackTrip.do" id="tab" name="tripData" enctype="multipart/form-data">
								<table style="border:1px solid black; width: 100%">
									<tr>
										<td>團長編號:</td>
										<td><input type="TEXT" name="memId" size="4" value="<%=(tripVO == null) ? "1" : tripVO.getMemId()%>" /></td>
									</tr>
									<tr>
										<td>行程名稱:</td>
										<td><input type="TEXT" name="tripName" value="<%=(tripVO == null) ? "中央大學露營趣" : tripVO.getTripName()%>" /></td>
									</tr>
									<tr>
										<td>出發時間:</td>
										<td><div class="input-group">
												<input type='text' class="form-control" id='departTime' name="departTime" required/>
												<span class="input-group-addon" id="clearDepart">
												<i class="fa fa-times-circle" aria-hidden="true"></i>
												</span>
											</div>
										</td>
									</tr>
<!-- 									<tr><td> -->
<!-- 										<td>行程類型:</td> -->
<!-- 									</td></tr> -->
									<tr>
										<td>上架時間</td>
										<td>
											<div class="input-group">
												<input type='text' class="form-control" id='startTime' name="tripAddTime"/>
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
												<input type='text' class="form-control" id='endTime' name="tripRmvTime"/>
												<span class="input-group-addon" id="clearEnd">
												<i class="fa fa-times-circle" aria-hidden="true"></i>
												</span>
											</div>
										</td>
									</tr>
									<tr>
										<td>行程是否公開:</td>
										<td>
											<select name="tripIsPublic">
												<option value="0" selected>私人
												<option value="1">公開
											</select>
										</td>
									</tr>
									<tr>
										<td>行程價位:</td>
										<td><input type="text" name="tripPrice" value="<%=(tripVO == null) ? "10000" : tripVO.getTripPrice()%>" /></td>
									</tr>
									<tr>
										<td>行程圖片:</td>
										<td>
											<input type="file" name="tripAdImg" id="imgInp" value="<%=(tripVO == null) ? null : tripVO.getTripAdImg()%>" />
											<img id="blah" src="../items/nopic.gif" alt="目前沒有圖片" width=250px /></td>
									</tr>
									<tr>
										<td>行程描述:</td>
										<td>
											<textarea type="TEXT" name="tripDesc" id="tripDesc" rows="10" cols="80" >
												"<div class="maxl"><big> <font color="#009900"><b><big>台北ＦＵＮ縱任你遊<br>●玩園區－ 北投泡溫泉、國父紀念館、中正紀念堂、故宮博物院、龍山寺、行天宮、星空草原-擁恆文創園區、國立海洋科技博物館、和平島公園 ．．．<br>●玩享吃－基隆廟口夜市、饒河夜市、士林夜市、臨江夜市．．．<br>●玩童樂－平溪天燈．．．<br>●玩古味－四四南村、十分老街、九份老街、猴硐貓街、深坑老街、金山老街、淡水老街．．．<br>●玩瘋趣－陽明山、擎天崗大草原．．．<br>●玩血拚－ 西門町、台北１０１、．．．</big></b></font><br></big>   <br><font color="blue"><strong>《<a href="http://www.liontravel.com/webft/webftse01.asp?sTcountry=TW&amp;sFreegroup=16TPH041" target="_blank">☆其他各地出發旅客，請由此處點選參考～</a>》</strong></font><br><font color="red"><u><strong>提醒您：產品訂價依出發日不同，參考售價請指定出發日期！</strong></u></font><br><br><center><br><img src="http://www.liontravel.com/Comm/2TRS/HotSale/photo/51/101.jpg" align="center" border="0"><img src="http://www.liontravel.com/Comm/2TRS/HotSale/photo/51/228.jpg" align="center" border="0"><br></center><br> <br><center>＜以上圖片僅供參考＞<br></center><br><br><br>提醒您：<br>１．訂購精省高鐵系列行程請最慢於出發日前三天（不含入住日、六日與國定假日）訂購付費完成。<br>　　若於三天內出發之訂單，將依客服實際作業狀況回覆為主！<br>　（備註：出發地除台北外之縣市，因高鐵票實體券需物流時間，故需增加一日以上工作天，敬請注意。）<br>２．訂購時請提供所有旅客之完整資料（姓名、身分證字號、出生日期），並請給予去回程各2-3個優先搭乘之高鐵班次<a href="http://www.thsrc.com.tw/tw/TimeTable/SearchResult" target="_blank">《友善連結：高鐵時刻表》</a>。<br>　<br>　　如未能立即提供全數旅客資料，仍可付款預先訂房作業，但實際可搭乘之班次須等資料補足後，始能完成高鐵車票訂位。<br><br><img src="http://www.liontravel.com/comm/2dom/hotsale/photo/a3.png" align="center" border="0"><br>【交通】台灣高速鐵路優惠指定案往返車票乙套。 <br>【住宿】HomeHotel大安館 住宿１晚（各飯店搭配房型參考詳見計價欄位說明，實際依飯店房況回覆為準）（此專案僅限本國人，非國人請另詢報價）。<br>【餐食】依房型含早餐。》》其他依各飯店各住宿區間搭配的專案內容不同提供(詳見計價欄位說明)<br>【保險】200萬旅遊綜合責任保險+10萬意外醫療險 （建議旅客可依需求自行購買旅行平安保險）<br>【備註】年滿0-2歲可購買嬰兒價【僅含保險】。<br>　　　　兒童不佔床僅含車佔位費及保險，其他費用不含。<br>　　　　報名兒童佔床及有敬老、愛心身份者團費與成人佔床同價。<br>　　　　─【兒童如報名不佔床，現場如產生餐食等其他費用需請依各飯店收費標準，現場自行付費飯店】 <br>　　　　若實際出發後，嬰兒年紀超過上述限制規定，需請家長於現場補足實際產生之費用。<br><br><img src="http://www.liontravel.com/comm/2dom/hotsale/photo/a4.png" align="center" border="0"><br>※個人因素所產生之消費，如飲料、酒類、私人購物費…等。<br>※個人旅遊平安保險，依規定旅客若有個別需求，得自行投保旅行平安保險。<br>※本行程表上未註明之各項開銷，建議、自費或自由行程所衍生之任何費用。<br></div>"
											</textarea></td>
									</tr>
									<tr>
										<td>行程內容:</td>
										<td><input type="TEXT" name="tripContent" value="<%=(tripVO == null) ? null : tripVO.getTripContent()%>" /></td>
									</tr>
								</table>
								<br>
								<input type="hidden" name="tripType" size="1" value="2">
								<input type="hidden" name="tripRate" value="0.0">
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
		$("#clearDepart").click(function(){
		    $('#departTime').val("");
		});
	});
	//顯示圖片
	$("#imgInp").change(function() {
		readURL(this);
	});
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
	
	CKEDITOR.replace('tripDesc');
	
</script>