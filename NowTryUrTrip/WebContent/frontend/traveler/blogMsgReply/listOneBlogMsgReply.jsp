<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.blogMsgReply.model.*"%>
<%
BlogMsgReplyVO blogMsgReplyVO = (BlogMsgReplyVO) request.getAttribute("blogMsgReplyVO"); //EmpServlet.java(Concroller), 存入req的blogMsgReplyVO物件
%>
<html>
<head>
<title>留言資料 - listOneBlogMsgReply.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>留言資料 - ListOneBlogMsgReply.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>回復留言編號</th>
		<th>文章留言編號</th>
		<th>留言會員編號</th>
		<th>留言內容</th>
		<th>留言時間</th>
		<th>被檢舉</th>
		<th>管理員已審閱</th>
		<th>被屏蔽</th>
		<th>屏蔽/不屏蔽理由</th>
	</tr>
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
	</tr>
</table>

</body>
</html>
