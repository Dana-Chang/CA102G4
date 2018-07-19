<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.blogMsg.model.*"%>
<%
BlogMsgVO blogMsgVO = (BlogMsgVO) request.getAttribute("blogMsgVO"); //EmpServlet.java(Concroller), 存入req的blogMsgVO物件
%>
<html>
<head>
<title>留言資料 - listOneBlogMsg.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>留言資料 - ListOneBlogMsg.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>留言編號</th>
		<th>部落格文章編號</th>
		<th>留言會員編號</th>
		<th>留言內容</th>
		<th>留言時間</th>
		<th>被檢舉</th>
		<th>管理員已審閱</th>
		<th>被屏蔽</th>
		<th>屏蔽/不屏蔽理由</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${blogMsgVO.msgNo}</td>
		<td>${blogMsgVO.articleNo}</td>
		<td>${blogMsgVO.msgMemId}</td>
		<td>${blogMsgVO.msgContent}</td>
		<td>${blogMsgVO.msgTime}</td>
		<td>${blogMsgVO.reported}</td>
		<td>${blogMsgVO.reviewed}</td>
		<td>${blogMsgVO.isBlocked}</td>
		<td>${blogMsgVO.blockedReason}</td>
	</tr>
</table>

</body>
</html>
