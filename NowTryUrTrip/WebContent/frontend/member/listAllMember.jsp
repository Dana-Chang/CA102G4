<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemberService memSvc = new MemberService();
	List<MemberVO> list = memSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>所有員工資料 - listAllMember.jsp</title>
</head>
<body bgcolor='white'>
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
			<th>會員編號</th>
			<th>會員姓名</th>
			<th>帳號</th>
			<th>性別</th>
			<th>身分</th>
			<th>帳號狀態</th>
			<th>手機號碼</th>
			<th>住家電話</th>
			<th>地址</th>
			<th>大頭照</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle'>
				<td>${memVO.memId}</td>
				<td>${memVO.memName}</td>
				<td>${memVO.memEmail}</td>
				<td>${memVO.memGender}</td>
				<td>${memVO.memType}</td>
				<td>${memVO.memStatus}</td>
				<td>${memVO.memCell}</td>
				<td>${memVO.memTel}</td>
				<td>${memVO.memAdd}</td>
				<td><img style="width: 150px;" src="<%=request.getContextPath()%>/Member/memImg.do?memId=${memVO.memId}"/></td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Member/mem.do">
						<input type="submit" value="修改"> <input type="hidden" name="memId" value="${memVO.memId}">
						<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Member/mem.do">
						<input type="submit" value="刪除"> 
						<input type="hidden" name="memId" value="${memVO.memId}"> 
						<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Friend/friend.do">
						<input type="submit" value="加入好友"> 
						<input type="hidden" name="friendId" value="${memVO.memId}"> 
						<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						<input type="hidden" name="action" value="insert">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Friend/friend.do">
						<input type="submit" value="確認好友"> 
						<input type="hidden" name="friendId" value="${memVO.memId}"> 
						<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						<input type="hidden" name="action" value="befriend">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>
