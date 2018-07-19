<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pointTransaction.model.*"%>
<%
	PointTransactionVO pointTransactionVO = (PointTransactionVO) request.getAttribute("pointTransactionVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>員工資料修改 - update_pointTransaction_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>點數交易資料修改 - update_pointTransaction_input.jsp</h3>
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

<FORM METHOD="post" ACTION="pointTransaction.do" name="form1">
<table border="0">
	<tr>
		<td>點數交易編號:<font color=red><b>*</b></font></td>
		<td><%=pointTransactionVO.getTransactionNo()%></td>
	</tr>
	
	
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td>[<%=pointTransactionVO.getMemId()%>]</td>
	</tr>
	
	<tr>
		<td>起始點數餘額:</td>
		<td>[<%=pointTransactionVO.getStartPoint()%>]</td>
	</tr>
	<tr>
		<td>起始現金餘額:</td>
		<td>[<%=pointTransactionVO.getStartCash()%>]</td>
	</tr>
	
	
	<tr>
		<td>交易說明:</td>
		<td><select size="1" name="transactionDescription">
			　<option value="儲存點數">儲存點數</option>
　			 <option value="兌換現金">兌換現金</option> 
			</select>
		</td>
	</tr>

	<tr>
		<td>異動現金:</td>
		<td><input type="TEXT" name="changeCash" size="45"	value="<%=pointTransactionVO.getChangeCash()%>" /></td>
	</tr>

	<tr>
		<td>前次交易時間:</td>
		<td><%=pointTransactionVO.getTransactionTime()%></td>
	</tr>
	<tr>
		<td>異動點數:</td>
		<td><input type="TEXT" name="changePoint" size="45"	value="<%=pointTransactionVO.getChangePoint()%>" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="transactionNo" value="<%=pointTransactionVO.getTransactionNo()%>">
<input type="hidden" name="memId" value="<%=pointTransactionVO.getMemId()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>