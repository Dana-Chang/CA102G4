<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blogArticle.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    BlogArticleService blogArticleSvc = new BlogArticleService();
    List<BlogArticleVO> list = blogArticleSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有留言資料 - listAllBlogArticle.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有員工資料 - ListAllArticleReport.jsp</h3>
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
			     <input type="submit" value="修改">
			     <input type="hidden" name="articleNo" value="${blogArticleVO.articleNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blogArticle/blogArticle.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="articleNo" value="${blogArticleVO.articleNo}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
