<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blogArticle.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    BlogArticleService blogArticleSvc = new BlogArticleService();
    List<BlogArticleVO> list = blogArticleSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�Ҧ��d����� - listAllBlogArticle.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����u��� - ListAllArticleReport.jsp</h3>
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
	<%@ include file="page1.file" %> 
	<c:forEach var="blogArticleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blogArticle/blogArticle.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="articleNo" value="${blogArticleVO.articleNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blogArticle/blogArticle.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="articleNo" value="${blogArticleVO.articleNo}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
