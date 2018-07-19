<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<html>
<head>
<title>員工資料修改 - update_member_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料修改 - update_member_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Member/mem.do" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=memberVO.getMemId()%></td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="memName" size="45" value="<%=memberVO.getMemName()%>" /></td>
	</tr>
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="memEmail" size="45"	value="<%=memberVO.getMemEmail()%>" /></td>
	</tr>
	
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="memPsw" size="45"	value="<%=memberVO.getMemPsw()%>" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="TEXT" name="memGender" size="45" value="<%=memberVO.getMemGender()%>" /></td>
	</tr>
	<tr>
		<td>身分:</td>
		<td><input type="TEXT" name="memType" size="45" value="<%=memberVO.getMemType()%>" /></td>
	</tr>
	<tr>
		<td>手機號碼:</td>
		<td><input type="TEXT" name="memCell" size="45" value="<%=memberVO.getMemCell()%>" /></td>
	</tr>
	<tr>
		<td>住家電話:</td>
		<td><input type="TEXT" name="memTel" size="45" value="<%=memberVO.getMemTel()%>" /></td>
	</tr>
	<tr>
		<td>住址:</td>
		<td><input type="TEXT" name="memAdd" size="45" value="<%=memberVO.getMemAdd()%>" /></td>
	</tr>
	<tr>
		<td>大頭照:</td>
		<td><input class="upl" type="file" name="memImg" size="45" value="<%=memberVO.getMemImg()%>" /></td>
		<td><img class="preview" style="max-width: 150px; max-height: 150px;" src="<%=request.getContextPath()%>/Member/memImg.do?memId=${memberVO.memId}"/></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="memId" value="<%=memberVO.getMemId()%>">
<input type="submit" value="送出修改"></FORM>

</body>
<script type="text/javascript">
$(function (){
 
    function format_float(num, pos)
    {
        var size = Math.pow(10, pos);
        return Math.round(num * size) / size;
    }
 
    function preview(input) {
 
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            
            reader.onload = function (e) {
                $('.preview').attr('src', e.target.result);
                var KB = format_float(e.total / 1024, 2);
                $('.size').text("檔案大小：" + KB + " KB");
            }
 
            reader.readAsDataURL(input.files[0]);
        }
    }
 
    $("body").on("change", ".upl", function (){
        preview(this);
    })
    
})
</script>
</html>
