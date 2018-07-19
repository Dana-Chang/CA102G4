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
<title>點數交易資料新增 - addPointTransaction.jsp</title></head>
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
		<td><select size="1" name="memId">
			<c:forEach var="memberVO" items="${memberSvc.all}"> 
          <option value="${memberVO.memId}">${memberVO.memId}
         </c:forEach>   			
		</select></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="getOne">
<input type="submit" value="送出新增"></FORM>
</body>

</html>