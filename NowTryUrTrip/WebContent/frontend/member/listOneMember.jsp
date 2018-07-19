<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.member.model.*"%>
<!--  EmpServlet.java(Concroller), 存入req的empVO物件 -->
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
