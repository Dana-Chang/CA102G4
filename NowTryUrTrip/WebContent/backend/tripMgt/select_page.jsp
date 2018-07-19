<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>IBM Trip: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Trip: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Trip: Home</p>

<h3>��Ƭd��:</h3>
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
<ul>
  <li><a href='trip_list.jsp'>List</a> all Trips. </li> <br><br>
  
  
  <li>
    <FORM METHOD="post" ACTION="BackTrip.do" >
        <b>��J��{�s�� :</b>
        <input type="text" name="tripNo">
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  

  <jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService" />
   
  <li>
     <FORM METHOD="post" ACTION="BackTrip.do" >
       <b>��J��{�s�� :</b>
       <select size="1" name="tripNo">
         <c:forEach var="tripVO" items="${tripSvc.all}" > 
          <option value="${tripVO.tripNo}">${tripVO.tripNo}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="BackTrip.do" >
       <b>��ܦ�{�W��:</b>
       <select size="1" name="tripNo">
         <c:forEach var="tripVO" items="${tripSvc.all}" > 
          <option value="${tripVO.tripNo}">${tripVO.tripName}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>

<h3>��{�޲z</h3>
<ul>
  <li><a href='addTrip.jsp'>Add</a> a new Trip.</li>
</ul>
</body>
</html>
