<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.proprietorArea.model.*"%>
<%@ page import="com.spot.model.*"%>
<%-- �����m�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>
<%PprVO pprVO = (PprVO) request.getAttribute("pprVO");%>

<%-- ���X ������DeptVO����--%>
<%
  SpotService spotSvc = new SpotService();
  SpotVO spotVO = spotSvc.getOneSpot(pprVO.getPprSpotNo());
%>
<html>
<head>
<title>���u��� - listOneEmp.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� Script ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���u��� - listOnePpr.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>pprNO</th>
		<th>spotNO</th>
		<th>checkIn�ɶ�</th>
		<th>checkOut�ɶ�</th>
		<th>�s�i���e</th>
		<th>�s�i�Ϥ�</th>
		<th>�ק�</th>
		<th>�R��</th>	
	</tr>
	<tr align='center' valign='middle'>
	<%Integer b = 2; %>
			<td><%=pprVO.getPprMsgeNo()%></td>
			<td><%=pprVO.getPprSpotNo()%></td>
			<td><%=pprVO.getPprCheckIn()%></td>			
			<td><%=pprVO.getPprCheckOut()%></td>
			<td><%=pprVO.getPprMsgeCtx()%></td>
			<td><img style="max-width: 200px; max-height:200px;" src="<%=request.getContextPath()%>/ppr/pprImg.do?pprMsgeNo=${pprVO.pprMsgeNo}"/></td>			
		    <td><%=pprVO.getPprSpotNo()%>�i<%=spotVO.getSpotName()%> - <%=spotVO.getSpotType()%>�j</td>
	</tr>
</table>

</body>
</html>
