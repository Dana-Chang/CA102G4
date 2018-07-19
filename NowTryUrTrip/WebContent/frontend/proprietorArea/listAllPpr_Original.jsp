<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.proprietorArea.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    PprService pprSvc = new PprService();
    List<PprVO> list = pprSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�Ҧ��s�i��� - listAllPpr.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ��s�i��� - listAllPpr.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

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
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="pprVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'}>
			<td>${pprVO.pprMsgeNo}</td>
			<td>${pprVO.pprSpotNo}</td>
			<td>${pprVO.pprCheckIn}</td>
			<td>${pprVO.pprCheckOut}</td>
			<td>${pprVO.pprMsgeCtx}</td>
			<td><img style="max-width: 200px; max-height:200px;" src="<%=request.getContextPath()%>/proprietorArea/pprImg.do?pprMsgeNo=${pprVO.pprMsgeNo}"/></td>			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/proprietorArea/ppr.do">
			     <input type="submit" value="�ק�"> 
			     <input type="hidden" name="pprMsgeNo" value="${pprVO.pprMsgeNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/proprietorArea/ppr.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="pprMsgeNo" value="${pprVO.pprMsgeNo}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>			
		</tr>
	</c:forEach>	
</table>
<%@ include file="pages/page2.file" %>

<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</html>
