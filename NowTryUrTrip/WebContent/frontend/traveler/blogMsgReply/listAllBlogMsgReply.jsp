<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blogMsgReply.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    BlogMsgReplyService blogMsgReplySvc = new BlogMsgReplyService();
    List<BlogMsgReplyVO> list = blogMsgReplySvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有留言資料 - listAllBlogMsg.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有員工資料 - ListAllBlogMsg.jsp</h3>
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
		<th>回覆留言編號</th>
		<th>文章留言編號</th>
		<th>留言會員編號</th>
		<th>留言內容</th>
		<th>留言時間</th>
		<th>被檢舉</th>
		<th>管理員已審閱</th>
		<th>被屏蔽</th>
		<th>不屏蔽理由</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="blogMsgReplyVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${blogMsgReplyVO.replyNo}</td>
			<td>${blogMsgReplyVO.msgNo}</td>
			<td>${blogMsgReplyVO.msgMemId}</td>
			<td>${blogMsgReplyVO.msgContent}</td>
			<td>${blogMsgReplyVO.msgTime}</td>
			<td>${blogMsgReplyVO.reported}</td>
			<td>${blogMsgReplyVO.reviewed}</td>
			<td>${blogMsgReplyVO.isBlocked}</td>
			<td>${blogMsgReplyVO.blockedReason}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blogMsgReply/blogMsgReply.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="replyNo" value="${blogMsgReplyVO.replyNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blogMsgReply/blogMsgReply.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="replyNo" value="${blogMsgReplyVO.replyNo}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
