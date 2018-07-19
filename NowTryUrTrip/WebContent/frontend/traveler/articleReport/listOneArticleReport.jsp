<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.articleReport.model.*"%>
<%
ArticleReportVO articleReportVO = (ArticleReportVO) request.getAttribute("articleReportVO"); //EmpServlet.java(Concroller), 存入req的articleReportVO物件
%>
<html>
<head>
<title>文章檢舉資料 - listOneArticleReport.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>文章檢舉資料 - ListOneArticleReport.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>檢舉編號</th>
		<th>部落格文章編號</th>
		<th>被檢舉會員編號</th>
		<th>檢舉原因</th>
		<th>檢舉時間</th>
		<th>管理員已審閱</th>
		<th>不屏蔽理由</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${articleReportVO.reportNo}</td>
		<td>${articleReportVO.articleNo}</td>
		<td>${articleReportVO.memId}</td>
		<td>${articleReportVO.rpReason}</td>
		<td>${articleReportVO.rpTime}</td>
		<td>${articleReportVO.adminChecked}</td>
		<td>${articleReportVO.noRpReason}</td>
	</tr>
</table>

</body>
</html>
