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
<title>�s�i��Ʒs�W- addPpr.jsp</title>
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
				<h3>�Ӯa�s�i�s�W - addPpr.jsp</h3>
			</td>
			<td><a href="#"><img
					src="<%=request.getContextPath()%>/frontend/proprietorArea/images/111.jpg"
					width="100" height="100" border="1">�^����</a></td>
		</tr>
	</table>
	
	<div style="center">
		<h3>�s�i���:</h3>
		<%-- ���~��C --%>
		<c:if test="${not empty errorMsgs}">
			<font color='red'>�Эץ��H�U���~:
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

			<%-- enctype="multipart/form-data"   �O�o�Ϥ����\FORM���W�n�[����--%>
			<table border="0">

				<tr>
<%-- 					<td>�s�i���e:</td>
						<td><input type="text" name="pprMsgeCtx" size="45"
						value="<%=(pprVO == null) ? "�s�i���e" : pprVO.getPprMsgeCtx()%>" /></td>--%>
					<td>�s�i���e:</td>
					<td>
						<textarea name="pprMsgeCtx"
							value="<%=(pprVO == null) ? "�ثe�S�����e" : pprVO.getPprMsgeCtx()%>">
							<p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
						</textarea>						
						<script>
							CKEDITOR.replace('pprMsgeCtx');
						</script>						
					</td>
				</tr>
				<%-- showCalendar('form1','CheckIn','BTN_date')'CheckIn'����e�ɶ�,�]�w��ʷ|�����D���η�epass��'pprCheckIn'�~�O���� --%>
				<tr>
				<tr>
					<td>�s�i�W�[���:</td>
					<td><input type="text" class="dateTime"
						value="<%=pprVO == null ? sdf.format(now) : sdf.format(pprVO.getPprCheckIn())%>"
						name="pprCheckIn" /></td>
				</tr>
				<tr>
					<td>�s�i�U�[���:</td>
					<td><input type="text" class="dateTime"
						value="<%=pprVO == null ? sdf.format(now) : sdf.format(pprVO.getPprCheckOut())%>"
						name="pprCheckOut" /></td>
				</tr>

				<jsp:useBean id="spotSvc" scope="page"
					class="com.spot.model.SpotService" />
				<tr>
					<td>��ܼs�i�a�I:<font color=red><b>*</b></font></td>
					<td><select size="1" name="pprSpotNo">
							<c:forEach var="spotVO" items="${spotSvc.all}">
								<option value="${spotVO.spotNo}"
									${(param.spotNo==spotVO.spotNo)? 'selected':'' }>${spotVO.spotName}
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>�s�i�Ϥ�:<font color=red><b>*</b></font></td>
					<td><input type="FILE" class="upl" name="pprMsgeImg" size="45" /></td>
					<td><img class="preview"
						style="max-width: 150px; max-height: 150px;"
						src="<%=request.getContextPath()%>/proprietorArea/pprImg.do?pprMsgeNo=${pprVO.getPprMsgeNo()}" /></td>
				</tr>
			</table>
			<br> <input type="hidden" name="action" value="insert">
			<input type="submit" value="�e�X�s�W">
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
					$('.size').text("�ɮפj�p�G" + KB + " KB");
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