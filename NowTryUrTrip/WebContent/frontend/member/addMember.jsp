<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<html>
<head>
<title>會員資料新增 </title>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/backend/admin/sweetalert.css">
<script src="<%=request.getContextPath()%>/backend/admin/Login/lib/sweetalert.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/calendarcode.js"></script>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
</head>

<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>



	<h4>
		會員資料:<font color=red><b>*</b></font>為必填欄位
	</h4>
	<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
					<script>swal("${message}", "", "error")</script>
					</c:forEach>
				</ul>
		</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Member/mem.do" name="form1" enctype="multipart/form-data">
		<table border="0">

			<tr>
				<td>會員姓名:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="memName" size="45"
					value="<%=(memberVO == null) ? "吳永志" : memberVO.getMemName()%>" /></td>
			</tr>
			<tr>
				<td>帳號:<font color=red><b>*</b></font></td>
				<td><input type="Email" name="memEmail" size="45"
					value="<%=(memberVO == null) ? "rgrtwee@yahoo.com.tw" : memberVO.getMemEmail()%>" /></td>
			</tr>
			<tr>
				<td>密碼:<font color=red><b>*</b></font></td>
				<td><input type="password" name="memPsw" size="45"
					value="<%=(memberVO == null) ? "123" : memberVO.getMemPsw()%>" /></td>
			</tr>
			<tr>
				<td>性別:</td>
				<td>
				<select name="memGender" class="form-control span12">
					<option value="">請選擇</option>
					<option value="1">男</option>
					<option value="2">女</option>
				</select>
				</td>
			</tr>
			<tr>
				<td><font color=red><b></b></font></td>
				<td><input type="hidden" name="memType" size="45"
					value="<%=(memberVO == null) ? "1" : memberVO.getMemType()%>" /></td>
			</tr>
			<tr>
				<td>手機號碼:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="memCell" size="45"
					value="<%=(memberVO == null) ? "0986532658" : memberVO.getMemCell()%>" /></td>
			</tr>
			<tr>
				<td>住家電話:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="memTel" size="45"
					value="<%=(memberVO == null) ? "(07)-7584695" : memberVO.getMemTel()%>" /></td>
			</tr>
			<tr>
				<td>住址:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="memAdd" size="45"
					value="<%=(memberVO == null) ? "桃園縣楊梅區幼獅路一段439號" : memberVO.getMemAdd()%>" /></td>
			</tr>
			<tr>
				<td>大頭照:<font color=red><b>*</b></font></td>
				<td><input type="FILE" class="upl" name="memImg" size="45"/></td>
				<td><img class="preview" style="max-width: 150px; max-height: 150px;" src="<%=request.getContextPath()%>/Member/memImg.do?memId=${memberVO.memId}"/></td>
			</tr>

		</table>
		<br> 
		<input type="hidden"  name="action" value="insert"> 
		<input type="submit" value="送出新增">
	</FORM>
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
