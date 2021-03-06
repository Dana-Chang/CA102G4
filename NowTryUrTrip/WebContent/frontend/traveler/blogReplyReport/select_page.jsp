<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM BlogReplyReport: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM BlogReplyReport: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM BlogReplyReport: Home</p>

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
  <li><a href='listAllBlogReplyReport.jsp'>List</a> all BlogReplyReports. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="blogReplyReport.do" >
        <b>輸入檢舉編號 (如1):</b>
        <input type="text" name="replyReportNo">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="blogReplyReportSvc" scope="page" class="com.blogReplyReport.model.BlogReplyReportService" />
   
  <li>
     <FORM METHOD="post" ACTION="blogReplyReport.do" >
       <b>選擇檢舉編號:</b>
       <select size="1" name="replyReportNo">
         <c:forEach var="blogReplyReportVO" items="${blogReplyReportSvc.all}" > 
          <option value="${blogReplyReportVO.replyReportNo}">${blogReplyReportVO.replyReportNo}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
</ul>


<h3>留言管理</h3>

<ul>
  <li><a href='addBlogReplyReport.jsp'>Add</a> a new BlogReplyReport.</li>
</ul>

</body>

</html>