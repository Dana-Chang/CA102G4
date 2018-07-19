<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.proprietorArea.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
	java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	PprVO pprVO = (PprVO) request.getAttribute("pprVO");
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>建立優惠訊息</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- header.css以及footer.css勿刪… -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header_and_footer/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header_and_footer/footer.css">
	
<!-- * -->	<script type="text/javascript" src="js/calendarcode.js"></script>
<!-- * -->	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<!-- * -->	<script src="lib/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<!-- * -->	<link rel="stylesheet" type="text/css"href="<%=request.getContextPath()%>/frontend/proprietorArea/lib/jquery.datetimepicker.css" />
<!-- * -->	<script src="<%=request.getContextPath()%>/frontend/proprietorArea/lib/jquery.datetimepicker.full.js"></script>         
<!-- * -->  <script src="<%=request.getContextPath()%>/frontend/proprietorArea/ckeditor/ckeditor.js"></script> 
</head>
<body id="bootstrap-overrides">
    <!-- 我會在header tag放內容，勿動 -->
    <div id="header" class="fh"></div>
    <div class="content-cap">參考header的高度</div>
    <div class="content" style="">
        <div class="row">
            <div class="col-xs-12 col-sm-3"><!--優惠菜單-->
                <%@ include file="/frontend/proprietorArea/proprietorArea_Menu.jsp" %>
            </div>
            <div class="col-xs-12 col-sm-6">
            <%--	<%@ include file="/frontend/proprietorArea/222.jsp" %> --%>
	            <div class="panel panel-default">
	                 <div class="panel-heading" role="tab" id="panel2">
	                  <h4 class="panel-title">建立優惠訊息</h4>
	                </div>
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
	                <FORM METHOD="post"ACTION="<%=request.getContextPath()%>/proprietorArea/ppr.do"name="form1" enctype="multipart/form-data">
		                  <div class="panel-body">	                  
		                        <div class="first1">
		                            <font color="Orange"><h4><步驟1廣告圖片上傳></h4></font>		                            	                                
		                                <input type="FILE" class="upl" name="pprMsgeImg" size="45" />
		                                	<img class="preview"style="max-width: 250px; max-height: 250px;"src="<%=request.getContextPath()%>/proprietorArea/pprImg.do?pprMsgeNo=${pprVO.getPprMsgeNo()}" />    		
		                        </div>
		                        <div class="first2">                                      
		                             <h4><font color="Orange"><步驟2設定上下架時間></font></h4>                                       
										廣告上架日期:<input type="text" class="dateTime"value="<%=pprVO == null ? sdf.format(now) : sdf.format(pprVO.getPprCheckIn())%>"name="pprCheckIn" />
										廣告下架日期:<input	type="text" class="dateTime"value="<%=pprVO == null ? sdf.format(now) : sdf.format(pprVO.getPprCheckOut())%>"name="pprCheckOut" />
		                        </div>
		                        <div class="first3"><font color=Orange><h4><步驟3選擇地點></h4></font>
									<jsp:useBean id="spotSvc" scope="page" class="com.spot.model.SpotService" />		                        				
										<select size="1" name="pprSpotNo">
												<c:forEach var="spotVO" items="${spotSvc.all}">
													<option value="${spotVO.spotNo}"
														${(param.spotNo==spotVO.spotNo)? 'selected':'' }>${spotVO.spotName}
												</c:forEach>
										</select>		                        
		                        </div>
		                        <div class="first4">
		                            <h4><font color="Orange"><步驟4廣告文字描述></font></h4>                                                                               
										<textarea name="pprMsgeCtx"
											value="<%=(pprVO == null) ? "目前沒有內容" : pprVO.getPprMsgeCtx()%>">
											<p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
										</textarea>
										<script>CKEDITOR.replace( 'pprMsgeCtx', {});</script> 	
		                        </div>
		                        	<input type="hidden" name="action" value="insert">
		                            <input type="Submit" value="新增一筆廣告" style="position: relative;margin-left: 805px;">
		                  </div>
	                 </FORM>
	              </div>                                            
            </div>
            <div class="col-xs-12 col-sm-3"></div>
        </div>
    </div>
    <!-- 我會在footer tag放內容，勿動 -->
    <div id="footer" class=""></div>   
  <!--馬的這行有問題<script src="https://code.jquery.com/jquery.js"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/header_and_footer/hf.jsp"></script>  		   
</body>
<!-- * --><script type="text/javascript">
		$('.dateTime').datetimepicker();
		$(function() {
			function format_float(num, pos) {
				var size = Math.pow(10, pos);
				return Math.round(num * size) / size;
			}
	
			function preview(input) {
	
				if (input.files && input.files[0]) {
					var reader = new FileReader();
	
					reader.onload = function(e) {
						$('.preview').attr('src', e.target.result);
						var KB = format_float(e.total / 1024, 2);
						$('.size').text("檔案大小：" + KB + " KB");
					}
	
					reader.readAsDataURL(input.files[0]);
				}
			}
			$("body").on("change", ".upl", function() {
				preview(this);
			})
	
		})
	</script>             
</html>
