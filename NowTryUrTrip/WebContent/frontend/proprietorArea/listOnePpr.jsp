<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.proprietorArea.model.*"%>
<%@ page import="com.spot.model.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%PprVO pprVO = (PprVO) request.getAttribute("pprVO");%>

<%-- 取出 對應的DeptVO物件--%>
<%
  SpotService spotSvc = new SpotService();
  SpotVO spotVO = spotSvc.getOneSpot(pprVO.getPprSpotNo());
%>
<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 Script 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - listOnePpr.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>pprNO</th>
		<th>spotNO</th>
		<th>checkIn時間</th>
		<th>checkOut時間</th>
		<th>廣告內容</th>
		<th>廣告圖片</th>
		<th>修改</th>
		<th>刪除</th>	
	</tr>
	<tr align='center' valign='middle'>
	<%Integer b = 2; %>
			<td><%=pprVO.getPprMsgeNo()%></td>
			<td><%=pprVO.getPprSpotNo()%></td>
			<td><%=pprVO.getPprCheckIn()%></td>			
			<td><%=pprVO.getPprCheckOut()%></td>
			<td><%=pprVO.getPprMsgeCtx()%></td>
			<td><img style="max-width: 200px; max-height:200px;" src="<%=request.getContextPath()%>/ppr/pprImg.do?pprMsgeNo=${pprVO.pprMsgeNo}"/></td>			
		    <td><%=pprVO.getPprSpotNo()%>【<%=spotVO.getSpotName()%> - <%=spotVO.getSpotType()%>】</td>
	</tr>
</table>

</body>
</html>
