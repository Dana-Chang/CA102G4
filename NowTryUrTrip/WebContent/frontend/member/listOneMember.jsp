<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.member.model.*"%>
<!--  EmpServlet.java(Concroller), �s�Jreq��empVO���� -->
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<html>
<head>
</head>
<body bgcolor='white'>
	
	</table>

	<table border='1' bordercolor='#CCCCFF' width='600'>
		<tr>
			<th>�|���m�W</th>
			<th>�b��</th>
			<th>�ʧO</th>
			<th>����</th>
			<th>�b�����A</th>
			<th>������X</th>
			<th>��a�q��</th>
			<th>�a�}</th>
			<th>�j�Y��</th>
		</tr>
		<tr align='center' valign='middle'>
			<td><%=memberVO.getMemName()%></td>
			<td><%=memberVO.getMemEmail()%></td>
			<td><%=memberVO.getMemGender()%></td>
			<td><%=memberVO.getMemType()%></td>
			<td><%=memberVO.getMemStatus()%></td>
			<td><%=memberVO.getMemCell()%></td>
			<td><%=memberVO.getMemTel()%></td>
			<td><%=memberVO.getMemAdd()%></td>
			<td><img style="max-width: 150px; max-height: 150px;" src="<%=request.getContextPath()%>/Member/memImg.do?memId=${memberVO.memId}"/></td>
		</tr>
	</table>

</body>
</html>
