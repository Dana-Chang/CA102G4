<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blogMsgReport.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    BlogMsgReportService blogMsgReportSvc = new BlogMsgReportService();
    List<BlogMsgReportVO> list = blogMsgReportSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>文章檢舉資料 - listAllBlogMsgReport.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有被檢舉文章 - ListAllBlogMsgReport.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>檢舉編號</th>
		<th>部落格留言編號</th>
		<th>檢舉會員編號</th>
		<th>檢舉原因</th>
		<th>檢舉時間</th>
		<th>管理員已審閱</th>
		<th>不屏蔽理由</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="blogMsgReportVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${blogMsgReportVO.cmtReportNo}</td>
			<td>${blogMsgReportVO.msgNo}</td>
			<td>${blogMsgReportVO.memId}</td>
			<td>${blogMsgReportVO.rpReason}</td>
			<td>${blogMsgReportVO.rpTime}</td>
			<td>${blogMsgReportVO.adminChecked}</td>
			<td>${blogMsgReportVO.noRpReason}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blogMsgReport/blogMsgReport.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="cmtReportNo" value="${blogMsgReportVO.cmtReportNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blogMsgReport/blogMsgReport.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="cmtReportNo" value="${blogMsgReportVO.cmtReportNo}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
