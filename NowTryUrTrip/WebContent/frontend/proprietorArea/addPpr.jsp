<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.proprietorArea.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
	java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	PprVO pprVO = (PprVO) request.getAttribute("pprVO");
%>

<html>
<head>
<title>廣告資料新增- addPpr.jsp</title>
<script src="ckeditor/ckeditor.js"></script>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script type="text/javascript" src="js/calendarcode.js"></script>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<script src="lib/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/frontend/proprietorArea/lib/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/frontend/proprietorArea/lib/jquery.datetimepicker.full.js"></script>

<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>商家廣告新增 - addPpr.jsp</h3>
			</td>
			<td><a href="#"><img
					src="<%=request.getContextPath()%>/frontend/proprietorArea/images/111.jpg"
					width="100" height="100" border="1">回首頁</a></td>
		</tr>
	</table>
	
	<div style="center">
		<h3>廣告資料:</h3>
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
		<%=request.getContextPath()%>
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/proprietorArea/ppr.do"
			name="form1" enctype="multipart/form-data">

			<%-- enctype="multipart/form-data"   記得圖片成功FORM表單上要加此行--%>
			<table border="0">

				<tr>
<%-- 					<td>廣告內容:</td>
						<td><input type="text" name="pprMsgeCtx" size="45"
						value="<%=(pprVO == null) ? "廣告內容" : pprVO.getPprMsgeCtx()%>" /></td>--%>
					<td>廣告內容:</td>
					<td>
						<textarea name="pprMsgeCtx"
							value="<%=(pprVO == null) ? "目前沒有內容" : pprVO.getPprMsgeCtx()%>">
							<p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
						</textarea>						
						<script>
							CKEDITOR.replace('pprMsgeCtx');
						</script>						
					</td>
				</tr>
				<%-- showCalendar('form1','CheckIn','BTN_date')'CheckIn'為當前時間,設定手動會有問題先用當前pass掉'pprCheckIn'才是正解 --%>
				<tr>
				<tr>
					<td>廣告上架日期:</td>
					<td><input type="text" class="dateTime"
						value="<%=pprVO == null ? sdf.format(now) : sdf.format(pprVO.getPprCheckIn())%>"
						name="pprCheckIn" /></td>
				</tr>
				<tr>
					<td>廣告下架日期:</td>
					<td><input type="text" class="dateTime"
						value="<%=pprVO == null ? sdf.format(now) : sdf.format(pprVO.getPprCheckOut())%>"
						name="pprCheckOut" /></td>
				</tr>

				<jsp:useBean id="spotSvc" scope="page"
					class="com.spot.model.SpotService" />
				<tr>
					<td>選擇廣告地點:<font color=red><b>*</b></font></td>
					<td><select size="1" name="pprSpotNo">
							<c:forEach var="spotVO" items="${spotSvc.all}">
								<option value="${spotVO.spotNo}"
									${(param.spotNo==spotVO.spotNo)? 'selected':'' }>${spotVO.spotName}
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>廣告圖片:<font color=red><b>*</b></font></td>
					<td><input type="FILE" class="upl" name="pprMsgeImg" size="45" /></td>
					<td><img class="preview"
						style="max-width: 150px; max-height: 150px;"
						src="<%=request.getContextPath()%>/proprietorArea/pprImg.do?pprMsgeNo=${pprVO.getPprMsgeNo()}" /></td>
				</tr>
			</table>
			<br> <input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增">
		</FORM>


	</div>
</body>
<script type="text/javascript">
	$('.dateTime').datetimepicker();
	$(function() {

		function format_float(num, pos) {
			var size = Math.pow(10, pos);
			return Math.round(num * size) / size;
		}

		function preview(input) {

			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('.preview').attr('src', e.target.result);
					var KB = format_float(e.total / 1024, 2);
					$('.size').text("檔案大小：" + KB + " KB");
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$("body").on("change", ".upl", function() {
			preview(this);
		})

	})
</script>
</ html>