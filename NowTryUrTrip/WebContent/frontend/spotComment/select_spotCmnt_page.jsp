<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.spotCmnt.model.*"%>
<%
Set<Integer> h = new TreeSet<Integer>();
SpotCmntService spotCmntSvc = new SpotCmntService();
%>
<html>
<head>
<title>IBM Spot Comment: Home</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>IBM Spot Comment: Home</h3> <font color=red>( MVC )</font></td>
		</tr>
	</table>

	<p>This is the Home page for IBM Spot Comment: Home</p>

	<h3>資料查詢:</h3>
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
	<ul>
		<li><a href='listAllSpotCmnt.jsp'>List</a> all Spot Comments.</li>
		<br>
		<br>

		<li>
			<FORM METHOD="post" ACTION="spotCmnt.do">
				<b>輸入景點評論編號 (1開始):</b> <input type="text" name="spotCmntNo">
					<input type="submit" value="送出"> 
					<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>
		
		<li>
			<FORM METHOD="post" ACTION="spotCmnt.do">
				<b>選擇景點評論編號 (1001開始):</b>
				<select size="1" name="spotCmntNo">
					<c:forEach var="spotCmntVO" items="${spotCmntSvc.all}">
						<option value="${spotCmntVO.spotCmntNo}">${spotCmntVO.spotCmntNo}
					</c:forEach>
				</select>
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
		<li>
			<FORM METHOD="post" ACTION="spotCmnt.do">
				<b>選擇留言的人的編號:</b>
				
				<select size="1" name="memId">
				<% 
					for(SpotCmntVO spotCmntVO : spotCmntSvc.getAll())
					 h.add(spotCmntVO.getMemId());
				%>
				
				
				
					<c:forEach var="memId" items="${h}">
						<option value="${memId}">${memId}
					</c:forEach>
				</select>
				<input type="submit" value="送出"> <input type="hidden" name="action" value="getSpotCmnts_By_memId">
			</FORM>
		</li>
	</ul>
	<h3>景點管理</h3>
	<ul>
		<li><a href='addspotCmntCmnt.jsp'>Add</a> a new spotCmntCmnt.</li>
	</ul>
</body>
</html>
