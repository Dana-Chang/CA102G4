<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pointTransaction.model.*"%>
<%@ page import="com.member.model.*"%>

<%
PointTransactionVO pointTransactionVO = (PointTransactionVO) request.getAttribute("PointTransactionVO");
MemberVO memberVO = (MemberVO) request.getAttribute("MemberVO");
%>

<html>
<head>
<title>點數交易資料新增 - addPointTransaction2.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>點數交易資料新增 - addPointTransaction.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>交易資料:</h3>
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

<jsp:useBean id="pointTransactionSrc" scope="page" class="com.pointTransaction.model.PointTransactionService" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" /> 

<FORM METHOD="post" ACTION="pointTransaction.do" name="form1">

<table border="0">

	
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="memId" size="45" 
			value="${memberVO.memId}" readonly ${memberVO.memId}  /></td>
	</tr>
	
	
	
	
	<tr>
		<td>起始點數餘額:</td>
		<td><input type="TEXT" name="startPoint" size="45" 
			value="${memberVO.memBop}" readonly ${memberVO.memBop}  /></td>
	</tr>
	<tr>
		<td>起始現金餘額:</td>
		<td><input type="TEXT" name="startCash" size="45"
			value="${memberVO.memCb}" readonly ${memberVO.memCb}  /></td>
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
		<td>異動金額:</td>
		<td><input type="TEXT" name="changeCash" size="45" 
			value="<%= (pointTransactionVO==null)? "0" : pointTransactionVO.getChangeCash()%>" /></td>
	</tr>
	<tr>
		<td>異動點數:</td>
		<td><input type="TEXT" name="changePoint" size="45"
			value="<%= (pointTransactionVO==null)? "0" : pointTransactionVO.getChangePoint()%>" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>