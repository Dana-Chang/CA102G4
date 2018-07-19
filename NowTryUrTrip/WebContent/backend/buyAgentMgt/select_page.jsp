<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM PointTransaction: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM PointTransaction: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM PointTransaction: Home</p>

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
  <li><a href='listAllPointTransaction.jsp'>List</a> all PointTransactions<font color=blue>(byDAO).</font> </li><br><br>
 
  
  <li>
    <FORM METHOD="post" ACTION="pointTransaction.do" >
        <b>輸入交易編號 :</b>
        <input type="text" name="transactionNo">
        <input type="submit" value="送出"><font color=blue>(資料格式驗證  by Controller ).</font> 
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  

  <jsp:useBean id="pointTransactionSrc" scope="page" class="com.pointTransaction.model.PointTransactionService" />
  <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" /> 
  <li>
     <FORM METHOD="post" ACTION="pointTransaction.do" >
       <b>選擇交易編號:</b>
       <select size="1" name="transactionNo">
         <c:forEach var="PointTransactionVO" items="${pointTransactionSrc.all}" > 
          <option value="${PointTransactionVO.transactionNo}">${PointTransactionVO.transactionNo}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="pointTransaction.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="transactionNo">
         <c:forEach var="memberVO" items="${memberSvc.all}"> 
          <option value="${memberVO.memId}">${memberVO.memId}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>

<h3>點數交易管理</h3>

<ul>
  <li><a href='addPointTransaction.jsp'>Add</a> a new PointTransaction.</li>
</ul>

</body>

</html>
