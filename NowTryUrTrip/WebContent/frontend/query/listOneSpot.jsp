<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="java.util.*"%>
<%
SpotVO spotVO = (SpotVO) request.getAttribute("spotVO"); 
%>
<html>
<head>
<title>���u��� - listOneSpot.jsp</title>
</head>
<body bgcolor='white'>
	<table border='1' cellpadding='5' cellspacing='0' width='600'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>���u��� - ListOneSpot.jsp</h3> <a href="select_page.jsp"><img
					src="../items/back1.gif" width="100" height="32" border="0">�^����</a>
			</td>
		</tr>
	</table>

	<table border='1' bordercolor='#CCCCFF' width='600'>
		<tr>
			<th>���I�s��</th>
			<th>���I�W��</th>
			<th>���I����</th>
			<th>���I�g��</th>
			<th>���I�n��</th>
			<th>���I����</th>
			<th>���I²��</th>
			<th>���I�Ӥ�</th>
			<th>���I�֦����a</th>
			<th>�O�_�Q�|��</th>
			<th>�O�_�ˬd</th>
			<th>�O�_�B�z</th>
			<th>�O�_�Q�̽�</th>
			<th>�̽���]</th>
		</tr>
		<tr align='center' valign='middle'>
			<td>${spotVO.spotNo}</td>
			<td>${spotVO.spotName}</td>
			<td>${spotVO.spotType}</td>
			<td>${spotVO.spotLng}</td>
			<td>${spotVO.spotLat}</td>
			<td>${spotVO.spotRate}</td>
			<td>${spotVO.spotOverview}</td>
			<td><img src="<%=request.getContextPath() %>/query/DBImgReader.do?spotNo=${spotVO.spotNo }" width=250px></td>
			<td>${spotVO.spotOwner}</td>
			<td>${spotVO.spotRpt}</td>
			<td>${spotVO.spotChk}</td>
			<td>${spotVO.spotHdl}</td>
			<td>${spotVO.spotIsBlocked}</td>
			<td>${spotVO.spotBlockedReason}</td>
		</tr>
	</table>
</body>
</html>
