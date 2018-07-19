<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blogMsgReply.model.*"%>
<%
BlogMsgReplyVO blogMsgReplyVO = (BlogMsgReplyVO) request.getAttribute("blogMsgReplyVO");
%>

<html>
<head>
<title>留言資料新增 - addBlogMsgReply.jsp</title></head>
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
		<h3>留言資料新增 - addBlogMsgReply.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<!-- <h3>留言資料:</h3> -->
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

<FORM METHOD="post" ACTION="blogMsgReply.do" name="form1">
<div class="well">
<h4>回覆留言 :</h4>		
		<textarea name="msgContent" id= rows="3" class="form-control ta1" required="required"></textarea>
		<input type="hidden" name="action" value="insert">
		<input class="btn btn-default btn3" type="submit" value="確認">
		<div style="clear: both;"></div>
</div>
<table border="0">
<input type="hidden" name="articleNo" value="${blogArticleVO.articleNo}">
	<tr>
		<td>文章留言編號:</td>
		<td><input type="TEXT" name="msgNo" size="45" 
			value="${blogMsgVO.msgNo}" /></td>
	</tr>
	<tr>
		<td>留言會員編號:</td>
		<td><input type="TEXT" name="msgMemId" size="45"
			value="<%= (blogMsgReplyVO==null)? "0001" : blogMsgReplyVO.getMsgMemId()%>" /></td>
	</tr>
		

</table>
<br>
</FORM>
</body>

</html>
