<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blogArticle.model.*"%>
<%
BlogArticleVO blogArticleVO = (BlogArticleVO) request.getAttribute("blogArticleVO"); //EmpServlet.java(Concroller), 存入req的blogArticleVO物件
%>
<html>
<head>
<title>文章檢舉資料 - listOneBlogArticle.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>文章檢舉資料 - ListOneBlogArticle.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>文章編號</th>
		<th>作者編號</th>
		<th>發文時間</th>
		<th>隱私程度</th>
		<th>是否被檢舉</th>
		<th>管理員已審閱</th>
		<th>是否屏蔽</th>
		<th>不屏蔽理由</th>
		<th>文章內容</th>
		<th>文章標題</th>
	</tr>
	<tr align='center' valign='middle'>
			<td>${blogArticleVO.articleNo}</td>
			<td>${blogArticleVO.authorNo}</td>
			<td>${blogArticleVO.articleTime}</td>
			<td>${blogArticleVO.viewable}</td>
			<td>${blogArticleVO.reported}</td>
			<td>${blogArticleVO.reviewed}</td>
			<td>${blogArticleVO.isBlocked}</td>
			<td>${blogArticleVO.blockedReason}</td>
			<td>${blogArticleVO.articleContent}</td>
			<td>${blogArticleVO.articleTitle}</td>	
	</tr>
</table>

</body>
</html>
