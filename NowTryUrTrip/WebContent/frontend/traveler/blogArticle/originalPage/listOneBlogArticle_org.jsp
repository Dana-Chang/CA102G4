<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blogArticle.model.*"%>
<%
BlogArticleVO blogArticleVO = (BlogArticleVO) request.getAttribute("blogArticleVO"); //EmpServlet.java(Concroller), �s�Jreq��blogArticleVO����
%>
<html>
<head>
<title>�峹���|��� - listOneBlogArticle.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�峹���|��� - ListOneBlogArticle.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>�峹�s��</th>
		<th>�@�̽s��</th>
		<th>�o��ɶ�</th>
		<th>���p�{��</th>
		<th>�O�_�Q���|</th>
		<th>�޲z���w�f�\</th>
		<th>�O�_�̽�</th>
		<th>���̽��z��</th>
		<th>�峹���e</th>
		<th>�峹���D</th>
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
