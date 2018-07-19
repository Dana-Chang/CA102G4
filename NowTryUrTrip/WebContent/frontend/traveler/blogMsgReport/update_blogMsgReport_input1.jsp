<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blogMsgReport.model.*"%>
<%
	BlogMsgReportVO blogMsgReportVO = (BlogMsgReportVO) request.getAttribute("blogMsgReportVO"); //EmpServlet.java (Concroller), 存入req的blogMsgReportVO物件 (包括幫忙取出的blogMsgReportVO, 也包括輸入資料錯誤時的blogMsgReportVO物件)
%>
<html>
<head>
<title>員工資料修改 - update_blogMsgReport_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>留言資料修改 - update_blogMsgReport_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
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

<FORM METHOD="post" ACTION="blogMsgReport.do" name="form1">
<table border="0">
	<tr>
		<td>檢舉編號:<font color=red><b>*</b></font></td>
		<td><%=blogMsgReportVO.getCmtReportNo()%></td>
	</tr>
	<tr>

	<tr>
		<td>管理員已審閱:</td>
		<td><input type="TEXT" name="adminChecked" size="45"	value="<%=blogMsgReportVO.getAdminChecked()%>" /></td>
	</tr>
	<tr>
		<td>不屏蔽原因:</td>
		<td><input type="TEXT" name="noRpReason" size="45" value="<%=blogMsgReportVO.getNoRpReason()%>" /></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="cmtReportNo" value="<%=blogMsgReportVO.getCmtReportNo()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
