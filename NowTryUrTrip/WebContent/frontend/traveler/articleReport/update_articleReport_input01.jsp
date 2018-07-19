<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.articleReport.model.*"%>
<%
	ArticleReportVO articleReportVO = (ArticleReportVO) request.getAttribute("articleReportVO"); //EmpServlet.java (Concroller), 存入req的articleReportVO物件 (包括幫忙取出的articleReportVO, 也包括輸入資料錯誤時的articleReportVO物件)
%>
<html>
<head>
<body>
	<%@ include file="/backend/blog/indexMenu.jsp" %>
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

<FORM METHOD="post" ACTION="articleReport.do" name="form1">
<table border="0">
	<tr>
		<td>檢舉編號:<font color=red><b>*</b></font></td>
		<td><%=articleReportVO.getReportNo()%></td>
	</tr>
	<tr>
		<td>文章編號:</td>
		<td><%=articleReportVO.getArticleNo()%></td>
	</tr>
	<tr>
		<td>留言會員編號:</td>
		<td><%=articleReportVO.getMemId()%></td>
	</tr>
	<tr>
		<td>檢舉原因:</td>
		<td><%=articleReportVO.getRpReason()%></td>
	</tr>	
	<tr>
		<td>檢舉時間:</td>
		<td><%=articleReportVO.getRpTime()%></td>
	</tr>
	<tr>
		<td>管理員已審閱:</td>
		<td><input type="TEXT" name="adminChecked" size="45"	value="<%=articleReportVO.getAdminChecked()%>" /></td>
	</tr>
	<tr>
		<td>不屏蔽原因:</td>
		<td><input type="TEXT" name="noRpReason" size="45" value="<%=articleReportVO.getNoRpReason()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="reportNo" value="<%=articleReportVO.getReportNo()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
