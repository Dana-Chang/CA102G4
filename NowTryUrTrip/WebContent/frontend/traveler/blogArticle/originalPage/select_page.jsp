<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM BlogArticle: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM BlogArticle: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM BlogArticle: Home</p>

<h3>��Ƭd��:</h3>
<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listAllBlogArticle.jsp'>List</a> all BlogArticles. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="blogArticle.do" >
        <b>��J�峹�s�� (�p1):</b>
        <input type="text" name="articleNo">
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="blogArticleSvc" scope="page" class="com.blogArticle.model.BlogArticleService" />
   
  <li>
     <FORM METHOD="post" ACTION="blogArticle.do" >
       <b>��ܤ峹�s��:</b>
       <select size="1" name="articleNo">
         <c:forEach var="blogArticleVO" items="${blogArticleSvc.all}" > 
          <option value="${blogArticleVO.articleNo}">${blogArticleVO.articleNo}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
</ul>


<h3>�峹�޲z</h3>

<ul>
  <li><a href='addBlogArticle.jsp'>Add</a> a new BlogArticle.</li>
</ul>

</body>

</html>