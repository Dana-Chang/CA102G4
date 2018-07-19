<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blogReplyReport.model.*"%>
<%
BlogReplyReportVO blogReplyReportVO = (BlogReplyReportVO) request.getAttribute("blogReplyReportVO");
%>

<html>
<head>
<title>文章檢舉新增 - addBlogReplyReport.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
<style>
 	table {
 		display:none;
 	}

</style>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>留言檢舉新增 - addBlogReplyReport.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
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
<FORM METHOD="post" ACTION="blogReplyReport.do" name="form1">

<h4>檢舉原因:<h4>			
			<textarea required="required" name="rpReason" rows="3" class="form-control ta1"></textarea>
			<input type="hidden" name="action" value="insert">
			<input class="btn btn-default btn3" type="submit" value="確認">		
			<div style="clear: both;"></div>
<table border="0">
<input type="hidden" name="articleNo" value="${blogArticleVO.articleNo}">
	<tr>
		<td>留言編號:</td>
		<td><input type="TEXT" name="replyNo" size="45" 
			value="${blogMsgReplyVO.replyNo}" /></td>
	</tr>
	<tr>
		<td>檢舉會員編號:</td>
		<td><input type="TEXT" name="memId" size="45"
			value="<%= (blogReplyReportVO==null)? "001" : blogReplyReportVO.getMemId()%>" /></td>
	</tr>

</table>
<br>
<!-- <input type="hidden" name="action" value="insert"> -->
<!-- <input type="submit" value="送出新增"> -->
</FORM>
</body>

</html>
