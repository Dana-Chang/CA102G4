<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pointTransaction.model.*"%>    
<%@ page import="com.member.model.*"%>
    
<%
	PointTransactionService pointTransactionSvc = new PointTransactionService();
    List<PointTransactionVO> list = pointTransactionSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>�Ҧ����u���</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����u��� - listAllEmp1_byDAO.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>��������s��</th>
		<th>�|���s��</th>
		<th>�_�l�I�ƾl�B</th>
		<th>�_�l�{���l�B</th>
		<th>�������</th>
		<th>���ʪ��B</th>
		<th>����ɶ�</th>
		<th>�����I��</th>
		<th>�ק�</th>
		<th>�R��</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="pointTransactionVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'}>
			<td>${pointTransactionVO.transactionNo}</td>
			<td>${pointTransactionVO.memId}</td>
			<td>${pointTransactionVO.startPoint}</td>
			<td>${pointTransactionVO.startCash}</td>
			<td>${pointTransactionVO.transactionDescription}</td>
			<td>${pointTransactionVO.changeCash}</td>
			<td>${pointTransactionVO.transactionTime}</td>
			<td>${pointTransactionVO.changePoint}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/buyAgentMgt/pointTransaction.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="transactionNo" value="${pointTransactionVO.transactionNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/buyAgentMgt/pointTransaction.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="transactionNo" value="${pointTransactionVO.transactionNo}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>	
	


</body>
</html>