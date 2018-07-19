<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.proprietorArea.model.*"%>
<%@ page import ="java.sql.Timestamp"%>
<%@ page import ="java.text.SimpleDateFormat"%>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    PprService pprSvc = new PprService();
    List<PprVO> list = pprSvc.getAll();
    pageContext.setAttribute("list",list);   
%>
<!DOCTYPE html>
<html lang="">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>所有廣告資料</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- header.css以及footer.css勿刪… -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header_and_footer/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header_and_footer/footer.css"> 
    <style type="text/css">
    	table tr td{
			border:solid black 1px;
			table-layout: fixed;
		}
		
		table tr th{
			border:solid black 1px;
			table-layout: fixed;
			background-color:#ddd
		}
    </style>     
</head>



<body id="bootstrap-overrides">
    <!-- 我會在header tag放內容，勿動 -->
    <div id="header" class="fh"></div>
    <div class="content-cap">參考header的高度</div>
    <div class="container-fluid" style="">
        <div class="row">
            <div class="col-xs-12 col-sm-3"><!--優惠菜單-->
                <%@ include file="/frontend/proprietorArea/proprietorArea_Menu.jsp" %>
            </div>
            <div class="col-xs-12 col-sm-6">
	            <div class="content">
	                <div class="row">
	                  <h4>
	                      所有優惠訊息
	                  </h4>
	                </div>
	                	<div>
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
							<table border='1' bordercolor='#CCCCFF' width='800'>
								<tr>
									<th>廣告編號</th>
									<th>景點</th>
									<th>上架時間</th>
									<th>下架時間</th>
									<th>廣告內容</th>
									<th>廣告圖片</th>										
								</tr>
								<!-- 時間設置 -->
								<% 								 
								 Calendar now = Calendar.getInstance();
								 now.setTime(new java.sql.Timestamp(System.currentTimeMillis()));							
								%>							
<%@ include file="pages/page1.file" %> 
								<jsp:useBean id="spotSvc" scope="page" class="com.spot.model.SpotService" />		
								<c:forEach var="pprVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr align='center' valign='middle'}>
										<td>${pprVO.pprMsgeNo}</td>
										<td><c:forEach var="spotVO" items="${spotSvc.all}">
												<c:if test="${spotVO.spotNo==pprVO.pprSpotNo}">
												${spotVO.spotName}
												</c:if>			
											</c:forEach>
										</td>
										<td>${pprVO.pprCheckIn}</td>
										<td>${pprVO.pprCheckOut}</td>
										<td>${pprVO.pprMsgeCtx}</td>
										<td><img style="max-width: 200px; max-height:200px;" src="<%=request.getContextPath()%>/proprietorArea/pprImg.do?pprMsgeNo=${pprVO.pprMsgeNo}"/></td>					
									</tr>
								</c:forEach>	
							</table>
<%@ include file="pages/page2.file" %>
	                  </div>
	              </div>                                              
            </div>
            <div class="col-xs-12 col-sm-3"></div>
        </div>
    </div>
    <!-- 我會在footer tag放內容，勿動 -->
    <div id="footer" class=""></div>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/header_and_footer/hf.jsp"></script>
    <script src="ckeditor/ckeditor.js"></script>    
<!--     <script>CKEDITOR.replace( 'content', {});</script> -->
</body>
</html>
