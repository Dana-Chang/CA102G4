<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM BlogMsgReply: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM BlogMsgReply: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM BlogMsgReply: Home</p>

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
  <li><a href='listAllBlogMsgReply.jsp'>List</a> all BlogMsgReplys. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="blogMsgReply.do" >
        <b>輸入回覆留言編號 (如1):</b>
        <input type="text" name="replyNo">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="blogMsgReplySvc" scope="page" class="com.blogMsgReply.model.BlogMsgReplyService" />
   
  <li>
     <FORM METHOD="post" ACTION="blogMsgReply.do" >
       <b>選擇回覆留言編號:</b>
       <select size="1" name="replyNo">
         <c:forEach var="blogMsgReplyVO" items="${blogMsgReplySvc.all}" > 
          <option value="${blogMsgReplyVO.replyNo}">${blogMsgReplyVO.replyNo}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
</ul>


<h3>留言管理</h3>

<ul>
  <li><a href='addBlogMsgReply.jsp'>Add</a> a new BlogMsgReply.</li>
</ul>

</body>

</html>
