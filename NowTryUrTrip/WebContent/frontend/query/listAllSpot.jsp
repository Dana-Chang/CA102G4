<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.spot.model.*"%>
<%
	SpotService spotSvc = new SpotService();
	List<SpotVO> list = spotSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有景點資料 - listAllSpot.jsp</title>
</head>
<body bgcolor='white'>
	<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>所有景點資料 - ListAllEmp.jsp</h3> <a href="select_page.jsp"><img
					src="../items/back1.gif" width="100" height="32" border="0">回首頁</a>
			</td>
		</tr>
	</table>

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

	<table border='1' bordercolor='#CCCCFF' width='800'>
		<tr>
			<th>景點編號</th>
			<th>景點名稱</th>
			<th>景點類型</th>
			<th>景點經度</th>
			<th>景點緯度</th>
			<th>景點評分</th>
			<th>景點簡介</th>
			<th>景點照片</th>
			<th>景點擁有店家</th>
			<th>是否被舉報</th>
			<th>是否檢查</th>
			<th>是否處理</th>
			<th>是否被屏蔽</th>
			<th>屏蔽原因</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="spotVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle'>
				<td>${spotVO.spotNo}</td>
				<td>${spotVO.spotName}</td>
				<td>${spotVO.spotType}</td>
				<td>${spotVO.spotLng}</td>
				<td>${spotVO.spotLat}</td>
				<td>${spotVO.spotRate}</td>
				<td>${spotVO.spotOverview}</td>
				<td><img src="<%=request.getContextPath() %>/query/SpotImgReader.do?spotNo=${spotVO.spotNo }" width=250px></td>
				<td>${spotVO.spotOwner}</td>
				<td>${spotVO.spotChk}</td>
				<td>${spotVO.spotHdl}</td>
				<td>${spotVO.spotIsBlocked}</td>
				<td>${spotVO.spotBlockedReason}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/query/frontSpot.do">
						<input type="submit" value="修改">
						<input type="hidden" name="spotNo" value="${spotVO.spotNo}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/query/spot.do">
						<input type="submit" value="刪除">
						<input type="hidden" name="spotNo" value="${spotVO.spotNo}">
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
</body>
</html>
