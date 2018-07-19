<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.storeInf.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	StoreInfVO storeInfVO = (StoreInfVO) request.getAttribute("storeInfVO");
%>
<html>
<head>
<title>�|����Ʒs�W</title>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script type="text/javascript" src="js/calendarcode.js"></script>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
</head>

<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>



	<h4>
		���a���:<font color=red><b>*</b></font>���������
	</h4>
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

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/Member/mem.do" name="form1"
		enctype="multipart/form-data">
		<table border="0">

			<tr>
				<td>�j�Y��:<font color=red><b>*</b></font></td>
				<td><input type="FILE" class="upl" name="memImg" size="45" /></td>
				<td><img class="preview"
					style="max-width: 150px; max-height: 150px;"
					src="<%=request.getContextPath()%>/Member/memImg.do?memId=${memberVO.memId}" /></td>
			</tr>
			<tr>
				<td>�|���m�W:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="memName" size="45"
					value="<%=(memberVO == null) ? "�d�ç�" : memberVO.getMemName()%>" /></td>
			</tr>
			<tr>
				<td>�t�d�H:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="principal" size="45"
					value="<%=(storeInfVO == null) ? "���R�q" : storeInfVO.getPrincipal()%>" /></td>
			</tr>
			<tr>
				<td>�b��:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="memEmail" size="45"
					value="<%=(memberVO == null) ? "rgrtwee@yahoo.com.tw" : memberVO.getMemEmail()%>" /></td>
			</tr>
			<tr>
				<td>�K�X:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="memPsw" size="45"
					value="<%=(memberVO == null) ? "123" : memberVO.getMemPsw()%>" /></td>
			</tr>
			<tr>
				<td>�ʧO:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="memGender" size="45"
					value="<%=(memberVO == null) ? "1" : memberVO.getMemGender()%>" /></td>
			</tr>
			<tr>
				<td><font color=red><b></b></font></td>
				<td><input type="hidden" name="memType" size="45"
					value="<%=(memberVO == null) ? "1" : memberVO.getMemType()%>" /></td>
			</tr>
			<tr>
				<td>������X:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="memCell" size="45"
					value="<%=(memberVO == null) ? "0986532658" : memberVO.getMemCell()%>" /></td>
			</tr>
			<tr>
				<td>�s���q��:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="storCall" size="45"
					value="<%=(storeInfVO == null) ? "0975456321" : storeInfVO.getStorCall()%>" /></td>
			</tr>
			<tr>
				<td>��a�q��:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="memTel" size="45"
					value="<%=(memberVO == null) ? "(07)-7584695" : memberVO.getMemTel()%>" /></td>
			</tr>
			<tr>
				<td>��}:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="memAdd" size="45"
					value="<%=(memberVO == null) ? "��鿤�����ϥ�����@�q439��" : memberVO.getMemAdd()%>" /></td>
			</tr>
			<tr>
				<td>�q�H�a�}:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="storAdd" size="45"
					value="<%=(storeInfVO == null) ? "��鿤�����ϥ�����@�q439��" : storeInfVO.getStorAdd()%>" /></td>
			</tr>


			<tr>
				<td>�Τ@�s��:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="uniformNum" size="45"
					value="<%=(storeInfVO == null) ? "54236258" : storeInfVO.getUniformNum()%>" /></td>
			</tr>
			<tr>
				<td>�l���ϸ�:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="postalCode" size="45"
					value="<%=(storeInfVO == null) ? "325" : storeInfVO.getPostalCode()%>" /></td>
			</tr>
			<tr>
				<td>���a���O:</td>
				<td>
				<select name="category" class="form-control span12">
					<option value="">�п��</option>
					<option value="1">���]</option>
					<option value="2">�\�U</option>
					<option value="3">���J</option>
					<option value="4">�Ȧ��</option>
				</select>
				</td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insertStoreInf">
		<input type="submit" value="�e�X�s�W">
	</FORM>
</body>
<script type="text/javascript">
	$(function() {

		function format_float(num, pos) {
			var size = Math.pow(10, pos);
			return Math.round(num * size) / size;
		}

		function preview(input) {

			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('.preview').attr('src', e.target.result);
					var KB = format_float(e.total / 1024, 2);
					$('.size').text("�ɮפj�p�G" + KB + " KB");
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$("body").on("change", ".upl", function() {
			preview(this);
		})

	})
</script>
</html>
