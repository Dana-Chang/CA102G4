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
<meta charset="utf8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Title Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" />
	<link rel="stylesheet" type="text/css" href="ckeditor/contents.css">

<style type="text/css">
	table tr td{
		border:solid black 1px;
		table-layout: fixed;
	}
		
	table tr th{
		border:solid black 1px;
		table-layout: fixed;
		background-color:#ddd
	}
	/***************** 顯示星星********************/

	span.stars, span.stars span {
	    display: block;
	    background: url(stars.png) 0 -16px repeat-x;
	    width: 80px;
	    height: 16px;
	}
	
	span.stars span {
	    background-position: 0 0;
	}
</style>
</head>
<body>
<!-- --------------------內容本體------------------------- -->
	<div class="content">
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
				<a href="<%=request.getContextPath()%>/frontend/tripPlan/addTrip.jsp">加入新行程</a>
				<div class="btn-group">
					<button class="btn btn-default">匯入行程</button>
					<button class="btn btn-default">上架行程</button>
				</div>
			</div>
			<table class="table table-responsive" >
					<caption>平台優選</caption>
					
					<%@ include file="page1.file"%>
					<c:forEach var="tripVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<c:if test="${memberVO.memId==tripVO.memId}">
					
					<tr class="trip-title">
        				<th >${tripVO.tripName }</th>
				        <th style="width:240px">
				        	<span class="stars">${tripVO.tripRate/2}</span>
				        	<span style="float:right">
				        		<button onclick="selectTrip(${tripVO.tripNo});">查看詳情</button>
			        		</span>
			        		<span>
			        			<button id="tripDelBtn" data-target="#myModal" data-toggle="modal" onclick="deleteTrip(${tripVO.tripNo});">刪除行程</button>
   					 		</span>
					 	</th>
   					 </tr>
				    <tr style="display:none;">
				        <td colspan="2">${tripVO.tripDesc }</td>
					</tr>
						</c:if>
					</c:forEach>

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
	$(function() {
		$(".trip-title").click(function(){
	        $(this).next().toggle('slow');
	    });
		$('span.stars').stars();
	});
	function selectTrip(e){
		window.location.href = "/AA107G5/frontend/tripPlan/list_one_trip.jsp?tripNo="+e;
	}
	$("#tripDelBtn").click(function(e) {
	    e.preventDefault();
	    e.stopPropagation();
	});
	function deleteTrip(e){
		$(".modal-footer input[name='tripNo']").val(e);
	}
	$.fn.stars = function() {
	    return $(this).each(function() {
	        // Get the value
	        var val = parseFloat($(this).html());
	        // Make sure that the value is in 0 - 5 range, multiply to get width
	        var size = Math.max(0, (Math.min(5, val))) * 16;
	        // Create stars holder
	        var $span = $('<span />').width(size);
	        // Replace the numerical value with stars
	        $(this).html($span);
	    });
	}
</script>

