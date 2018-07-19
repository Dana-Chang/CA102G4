<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>IBM Member: Home</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>IBM Member: Home</h3> <font color=red>( MVC )</font></td>
		</tr>
	</table>

	<p>This is the Home page for IBM Member: Home</p>

	<h3>��Ƭd��:</h3>
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

	<ul>
		<li><a href='<%=request.getContextPath()%>/frontend/member/listAllMember.jsp'>List</a> all Emps.</li>
		<br>
		<br>

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Member/mem.do">
				<b>��J���u�s�� (�p0001):</b> 
				<input type="text" name="memId"> 
				<input type="submit" value="�e�X"> 
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Member/mem.do">
				<b>��ܭ��u�s��:</b> 
				<select size="1" name="memId">
					<c:forEach var="memVO" items="${memSvc.all}">
						<option value="${memVO.memId}">${memVO.memId}
					</c:forEach>
				</select> <input type="submit" value="�e�X">
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/Member/mem.do">
				<b>��ܭ��u�m�W:</b> 
				<select size="1" name="memId">
					<c:forEach var="memVO" items="${memSvc.all}">
						<option value="${memVO.memId}">${memVO.memName}
					</c:forEach>
				</select> 
				<input type="submit" value="�e�X"> 
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>
	</ul>


	<h3>���u�޲z</h3>

	<ul>
		<li><a href='<%=request.getContextPath()%>/frontend/member/addMember.jsp'>Add</a> a new Emp.</li>
	</ul>

</body>

</html>
