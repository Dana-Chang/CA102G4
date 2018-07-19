<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Spot: Home</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>IBM Spot: Home</h3> <font color=red>( MVC )</font></td>
		</tr>
	</table>

	<p>This is the Home page for IBM Spot: Home</p>

	<h3>��Ƭd��:</h3>
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
	<ul>
		<li><a href='listAllSpot.jsp'>List</a> all Spots.</li>
		<br>
		<br>

		<li>
			<FORM METHOD="post" ACTION="backSpot.do">
				<b>��J���I�s�� (1000�}�l):</b> <input type="text" name="spotNo"> <input
					type="submit" value="�e�X"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<jsp:useBean id="spotSvc" scope="page"
			class="com.spot.model.SpotService" />
		<li>
			<FORM METHOD="post" ACTION="backSpot.do">
				<b>��J���I�s�� (1001�}�l):</b> <select size="1" name="spotNo">
					<c:forEach var="spotVO" items="${spotSvc.all}">
						<option value="${spotVO.spotNo}">${spotVO.spotNo}
					</c:forEach>
				</select> <input type="submit" value="�e�X"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="backSpot.do">
				<b>��ܴ��I�W��:</b> <select size="1" name="spotNo">
					<c:forEach var="spotVO" items="${spotSvc.all}">
						<option value="${spotVO.spotNo}">${spotVO.spotName}
					</c:forEach>
				</select> <input type="submit" value="�e�X"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>
	</ul>

	<h3>���I�޲z</h3>
	<ul>
		<li><a href='addSpot.jsp'>Add</a> a new Spot.</li>
	</ul>
</body>
</html>
