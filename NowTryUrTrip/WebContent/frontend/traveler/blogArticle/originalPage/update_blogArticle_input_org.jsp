<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blogArticle.model.*"%>
<%
	BlogArticleVO blogArticleVO = (BlogArticleVO) request.getAttribute("blogArticleVO");
%>
<html>
<head>
<title>�峹��ƭק� - update_blogArticle_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�d����ƭק� - update_blogArticle_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>��ƭק�:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="blogArticle.do" name="form1">
<table border="0">
	<tr>
		<td>�峹�s��:</td>
		<td><%=blogArticleVO.getArticleNo()%></td>
	</tr>
	<tr>
		<td>�@�̽s��:</td>
		<td><input type="TEXT" name="authorNo" size="30" value="<%=blogArticleVO.getAuthorNo()%>" /></td>
	</tr>
	<tr>
		<td>�o��ɶ�:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="30" readonly type="text" name="articleTime" value="<%=blogArticleVO.getArticleTime()%>">
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','msgTime','BTN_date');return false;"> -->
<!-- 		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="�ɶ�"></a> -->
		</td>
	</tr>
	<tr>
		<td>���p�{��:</td>
		<td><input type="TEXT" name="viewable" size="30" value="<%=blogArticleVO.getViewable()%>" /></td>
	</tr>	
	<tr>
		<td>�O�_�Q���|:</td>
		<td><input type="TEXT" name="reported" size="30" value="<%=blogArticleVO.getReported()%>" /></td>
	</tr>
	<tr>
		<td>�޲z���f�\:</td>
		<td><input type="TEXT" name="reviewed" size="30" value="<%=blogArticleVO.getReviewed()%>" /></td>
	</tr>
	<tr>
		<td>�O�_�̽�:</td>
		<td><input type="TEXT" name="isBlocked" size="30" value="<%=blogArticleVO.getIsBlocked()%>" /></td>
	</tr>	
	<tr>
		<td>���̽��z��:</td>
		<td><input type="TEXT" name="blockedReason" size="30" value="<%=blogArticleVO.getBlockedReason()%>" /></td>
	</tr>
	<tr>
		<td>�峹���e:</td>
		<td><input type="TEXT" name="articleContent" size="30" value="<%=blogArticleVO.getArticleContent()%>" /></td>
	</tr>
	<tr>
		<td>�峹���D:</td>
		<td><input type="TEXT" name="articleTitle" size="30" value="<%=blogArticleVO.getArticleTitle()%>" /></td>
	</tr>	
		
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(blogArticleVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="articleNo" value="<%=blogArticleVO.getArticleNo()%>">
<input type="submit" value="�e�X�ק�"></FORM>

</body>
</html>
