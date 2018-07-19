<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.pointTransaction.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
PointTransactionVO pointTransactionVO = (PointTransactionVO) request.getAttribute("pointTransactionVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>點數交易資料 - listOnePointTransaction.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>點數交易資料 - ListOnePointTransaction.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>交易紀錄編號</th>
		<th>會員編號</th>
		<th>起始點數餘額</th>
		<th>起始現金餘額</th>
		<th>交易說明</th>
		<th>異動金額</th>
		<th>交易時間</th>
		<th>異動點數</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${pointTransactionVO.transactionNo}</td>
			<td>${pointTransactionVO.memId}</td>
			<td>${pointTransactionVO.startPoint}</td>
			<td>${pointTransactionVO.startCash}</td>
			<td>${pointTransactionVO.transactionDescription}</td>
			<td>${pointTransactionVO.changeCash}</td>
			<td>${pointTransactionVO.transactionTime}</td>
			<td>${pointTransactionVO.changePoint}</td>
		
	</tr>
</table>

</body>
</html>
