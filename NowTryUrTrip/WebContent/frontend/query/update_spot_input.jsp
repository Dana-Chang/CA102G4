<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spot.model.*"%>
<%
	SpotVO spotVO = (SpotVO) request.getAttribute("spotVO");
%>
<html>
<head>
<title>景點資料修改 - update_spot_input.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>景點資料修改 - update_spot_input.jsp</h3>
				<a href="select_page.jsp">
					<img src="../items/back1.gif" width="100" height="32" border="0">回首頁
				</a>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/query/spot.do" name="form1" enctype="multipart/form-data">
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
				<td>景點類型:</td>
				<td>
					<input type="TEXT" name="spotType" value="<%=spotVO.getSpotType()%>" />
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
					<img id="blah" src="<%=request.getContextPath() %>/query/DBImgReader.do?spotNo=${spotVO.spotNo }" alt="目前沒有圖片" width=250px />
				</td>
			</tr>
			<tr>
				<td>景點擁有店家:</td>
				<td>
					<input type="TEXT" name="spotOwner" value="<%=spotVO.getSpotOwner()%>" />
				</td>
			</tr>
			<tr>
				<td>是否檢查:</td>
				<td>
					<input type="TEXT" name="spotChk" value="<%=spotVO.getSpotChk()%>" />
				</td>
			</tr>
			<tr>
				<td>是否處理:</td>
				<td>
					<input type="TEXT" name="spotHdl" value="<%=spotVO.getSpotHdl()%>" />
				</td>
			</tr>
			<tr>
				<td>是否被屏蔽:</td>
				<td>
					<input type="TEXT" name="spotIsBlocked" value="<%=spotVO.getSpotIsBlocked()%>" />
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
</body>
</html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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

