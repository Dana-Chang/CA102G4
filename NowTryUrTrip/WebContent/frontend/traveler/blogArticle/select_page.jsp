<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<h3>文章查詢:</h3>
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
  <li><a href='listAllBlogArticle.jsp'>List</a> all BlogArticles. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="blogArticle.do" >
        <b>輸入文章編號 (如1):</b>
        <input type="text" name="articleNo">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="blogArticleSvc" scope="page" class="com.blogArticle.model.BlogArticleService" />
   
  <li>
     <FORM METHOD="post" ACTION="blogArticle.do" >
       <b>選擇文章編號:</b>
       <select size="1" name="articleNo">
         <c:forEach var="blogArticleVO" items="${blogArticleSvc.all}" > 
          <option value="${blogArticleVO.articleNo}">${blogArticleVO.articleNo}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
</ul>

<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="blogArticle.do" name="form1">
        <b><font color=blue>萬用複合查詢:</font></b> <br>
        <b>輸入文章標題:</b>
        <input type="text" name="articleTitle" value="001"><br>
           
       <b>輸入文章內容:</b>
       <input type="text" name="articleContent" value=""><br>
  
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="search">
     </FORM>
  </li>
</ul>

<h3>新增文章</h3>

<ul>
  <li><a href='addBlogArticle.jsp'>Add</a> a new BlogArticle.</li>
</ul>

</body>

</html>
