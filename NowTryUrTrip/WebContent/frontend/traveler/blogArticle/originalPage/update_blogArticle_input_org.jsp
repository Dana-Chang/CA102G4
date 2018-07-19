<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blogArticle.model.*"%>
<%
	BlogArticleVO blogArticleVO = (BlogArticleVO) request.getAttribute("blogArticleVO");
%>
<html>
<head>
<title>文章資料修改 - update_blogArticle_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>留言資料修改 - update_blogArticle_input.jsp</h3>
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

<FORM METHOD="post" ACTION="blogArticle.do" name="form1">
<table border="0">
	<tr>
		<td>文章編號:</td>
		<td><%=blogArticleVO.getArticleNo()%></td>
	</tr>
	<tr>
		<td>作者編號:</td>
		<td><input type="TEXT" name="authorNo" size="30" value="<%=blogArticleVO.getAuthorNo()%>" /></td>
	</tr>
	<tr>
		<td>發文時間:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="30" readonly type="text" name="articleTime" value="<%=blogArticleVO.getArticleTime()%>">
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','msgTime','BTN_date');return false;"> -->
<!-- 		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="時間"></a> -->
		</td>
	</tr>
	<tr>
		<td>隱私程度:</td>
		<td><input type="TEXT" name="viewable" size="30" value="<%=blogArticleVO.getViewable()%>" /></td>
	</tr>	
	<tr>
		<td>是否被檢舉:</td>
		<td><input type="TEXT" name="reported" size="30" value="<%=blogArticleVO.getReported()%>" /></td>
	</tr>
	<tr>
		<td>管理員審閱:</td>
		<td><input type="TEXT" name="reviewed" size="30" value="<%=blogArticleVO.getReviewed()%>" /></td>
	</tr>
	<tr>
		<td>是否屏蔽:</td>
		<td><input type="TEXT" name="isBlocked" size="30" value="<%=blogArticleVO.getIsBlocked()%>" /></td>
	</tr>	
	<tr>
		<td>不屏蔽理由:</td>
		<td><input type="TEXT" name="blockedReason" size="30" value="<%=blogArticleVO.getBlockedReason()%>" /></td>
	</tr>
	<tr>
		<td>文章內容:</td>
		<td><input type="TEXT" name="articleContent" size="30" value="<%=blogArticleVO.getArticleContent()%>" /></td>
	</tr>
	<tr>
		<td>文章標題:</td>
		<td><input type="TEXT" name="articleTitle" size="30" value="<%=blogArticleVO.getArticleTitle()%>" /></td>
	</tr>	
		
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
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
<input type="submit" value="送出修改"></FORM>

</body>
</html>
