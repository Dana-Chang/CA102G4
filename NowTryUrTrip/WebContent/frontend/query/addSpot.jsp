<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spot.model.*"%>
<%
	SpotVO spotVO = (SpotVO) request.getAttribute("spotVO");
%>

<html>
<head>
<title>���I��Ʒs�W - addSpot.jsp</title>
</head>

<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>���I��Ʒs�W - addSpot.jsp</h3>
			</td>
			<td><a href="select_page.jsp"><img src="../items/back.png"
					width="100" height="100" border="1">�^����</a></td>
		</tr>
	</table>

	<h3>���I���:</h3>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/query/spot.do" name="form1" enctype="multipart/form-data">
		<table border="0">
			<tr>
				<td>���I�W��:</td>
				<td><input type="TEXT" name="spotName"
					value="<%=(spotVO == null) ? null : spotVO.getSpotName()%>" /></td>
			</tr>
			<tr>
				<td>���I����:</td>
				<td><input type="TEXT" name="spotType"
					value="<%=(spotVO == null) ? null : spotVO.getSpotType()%>" /></td>
			</tr>
			<tr>
				<td>���I�g��:</td>
				<td><input type="text" name="spotLng"
					value="<%=(spotVO == null) ? null : spotVO.getSpotLng()%>" /></td>
			</tr>
			<tr>
				<td>���I�n��:</td>
				<td><input type="TEXT" name="spotLat"
					value="<%=(spotVO == null) ? null : spotVO.getSpotLat()%>" /></td>
			</tr>
			<tr>
				<td>���I����:</td>
				<td><input type="text" name="spotRate"
					value="<%=(spotVO == null) ? null : spotVO.getSpotRate()%>" /></td>
			</tr>
			<tr>
				<td>���I²��:</td>
				<td><input type="text" name="spotOverview"
					value="<%=(spotVO == null) ? null : spotVO.getSpotOverview()%>" /></td>
			</tr>
			<tr>
				<td>���I�Ӥ�:</td>
				<td><input type="file" name="spotPhoto" id="imgInp" /> <img
					id="blah" src="<%=request.getContextPath() %>/query/DBImgReader.do?spotNo=${spotVO.spotNo }" alt="�ثe�S���Ϥ�" width=250px /></td>
			</tr>
			<tr>
				<td>���I�֦����a:</td>
				<td><input type="TEXT" name="spotOwner"
					value="<%=(spotVO == null) ? null : spotVO.getSpotOwner()%>" /></td>
			</tr>
			<tr>
				<td>�O�_�ˬd:</td>
				<td><input type="TEXT" name="spotChk"
					value="<%=(spotVO == null) ? null : spotVO.getSpotChk()%>" /></td>
			</tr>
			<tr>
				<td>�O�_�B�z:</td>
				<td><input type="TEXT" name="spotHdl"
					value="<%=(spotVO == null) ? null : spotVO.getSpotHdl()%>" /></td>
			</tr>
			<tr>
				<td>�O�_�Q�̽�:</td>
				<td><input type="TEXT" name="spotIsBlocked"
					value="<%=(spotVO == null) ? null : spotVO.getSpotIsBlocked()%>" /></td>
			</tr>
			<tr>
				<td>�̽���]:</td>
				<td><input type="TEXT" name="spotBlockedReason"
					value="<%=(spotVO == null) ? null : spotVO.getSpotBlockedReason()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
	</FORM>
</body>

</html>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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

