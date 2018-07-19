<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM PurchasingOrder: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM PurchasingOrder: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM PurchasingOrder: Home</p>

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
  <jsp:useBean id="purchasingOrderSrc" scope="page" class="com.purchasingOrder.model.PurchasingOrderService" />
  <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" /> 

  
  <li>
     <FORM METHOD="post" ACTION="purchasingOrder.do" >
       <b>選擇會員查詢編號:</b>
       <select size="1" name="memId">
         <c:forEach var="memberVO" items="${memberSvc.all}"> 
          <option value="${memberVO.memId}">${memberVO.memId}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOneALL">
     </FORM>
  </li>
</ul>


</body>

</html>
