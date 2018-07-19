<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blogMsgReply.model.*"%>
<%
	BlogMsgReplyVO blogMsgReplyVO = (BlogMsgReplyVO) request.getAttribute("blogMsgReplyVO"); //EmpServlet.java (Concroller), 存入req的blogMsgReplyVO物件 (包括幫忙取出的blogMsgReplyVO, 也包括輸入資料錯誤時的blogMsgReplyVO物件)
%>
<html>
<head>
<title>員工資料修改 - update_blogMsg_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>留言資料修改 - update_blogMsg_input.jsp</h3>
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

<FORM METHOD="post" ACTION="blogMsgReply.do" name="form1">
<table border="0">
	<tr>
		<td>回覆留言編號:<font color=red><b>*</b></font></td>
		<td><%=blogMsgReplyVO.getReplyNo()%></td>
	</tr>	
	<tr>
		<td>被檢舉:</td>
		<td><input type="TEXT" name="reported" size="45"	value="<%=blogMsgReplyVO.getReported()%>" /></td>
	</tr>
	<tr>
		<td>管理員已審閱:</td>
		<td><input type="TEXT" name="reviewed" size="45" value="<%=blogMsgReplyVO.getReviewed()%>" /></td>
	</tr>
	<tr>
		<td>被屏蔽:</td>
		<td><input type="TEXT" name="isBlocked" size="45" value="<%=blogMsgReplyVO.getIsBlocked()%>" /></td>
	</tr>
	<tr>
		<td>不屏蔽理由:</td>
		<td><input type="TEXT" name="blockedReason" size="45" value="<%=blogMsgReplyVO.getBlockedReason()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="replyNo" value="<%=blogMsgReplyVO.getReplyNo()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
