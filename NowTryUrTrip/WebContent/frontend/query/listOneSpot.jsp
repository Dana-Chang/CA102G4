<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="java.util.*"%>
<%
SpotVO spotVO = (SpotVO) request.getAttribute("spotVO"); 
%>
<html>
<head>
<title>員工資料 - listOneSpot.jsp</title>
</head>
<body bgcolor='white'>
	<table border='1' cellpadding='5' cellspacing='0' width='600'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>員工資料 - ListOneSpot.jsp</h3> <a href="select_page.jsp"><img
					src="../items/back1.gif" width="100" height="32" border="0">回首頁</a>
			</td>
		</tr>
	</table>

	<table border='1' bordercolor='#CCCCFF' width='600'>
		<tr>
			<th>景點編號</th>
			<th>景點名稱</th>
			<th>景點類型</th>
			<th>景點經度</th>
			<th>景點緯度</th>
			<th>景點評分</th>
			<th>景點簡介</th>
			<th>景點照片</th>
			<th>景點擁有店家</th>
			<th>是否被舉報</th>
			<th>是否檢查</th>
			<th>是否處理</th>
			<th>是否被屏蔽</th>
			<th>屏蔽原因</th>
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
