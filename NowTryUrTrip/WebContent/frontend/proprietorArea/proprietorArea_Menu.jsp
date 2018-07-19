<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager.model.*"%>
<!DOCTYPE html>
<html lang="">
<head>
	
</head>


<body>

<div class="sidebar-nav">
	<ul>
		<!------------------------優惠訊息------------------------------>
	    <ul class="list-group">
	        <li class="list-group-item active">優惠訊息管理</li>
	        <li class="list-group-item"><a href="<%= request.getContextPath ()%>/frontend/proprietorArea/proprietorArea_add.jsp">我的優惠訊息新增</li>
	        <li class="list-group-item "><a href="">我的優惠訊息列表</a></li>
	        <li class="list-group-item"><a href="<%= request.getContextPath ()%>/frontend/proprietorArea/listAllPpr.jsp">所有優惠訊息列表</a></li>
	        <li class="list-group-item">清單項目 3</li>
	        <li class="list-group-item">清單項目 4</li>
	        <li class="list-group-item">清單項目 5</li>                                   
	    </ul>
	</ul>
</div>


</body>
</html>