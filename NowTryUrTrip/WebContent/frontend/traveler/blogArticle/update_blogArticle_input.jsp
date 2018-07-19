<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blogArticle.model.*"%>
<%
	BlogArticleVO blogArticleVO = (BlogArticleVO) request.getAttribute("blogArticleVO");
%>
<!DOCTYPE html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<title>部落格_文章編輯</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="js/plan.css">
<link
	href="<%=request.getContextPath()%>/frontend/chatroom/chatroom.css"
	rel="stylesheet">
<script src="ckeditor/ckeditor.js"></script>

<style type="text/css">
footer {
	margin: 50px 0;
}

hr {
	border-top: 1px dashed #aaa;
}

body {
	padding-top: 70px;
	background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0.6) 0%,
		rgba(255, 255, 255, 0.6) 100%), url(<%=request.getContextPath()%>/frontend/traveler/blogArticle/images/sky.jpg);
}

.well {
	background-color: #F0F8FF;
	border-radius: 20px;
}

.aa input, .back {
	float: right;
}

.form-control {
	width: 80px;
	float: left;
	margin-top: 10px;
}

.btn2 {
	width: 100%;
	margin-bottom: 10px;
}

.btn1 .btn {
	margin: 10px;
	margin-bottom: 0px;
}

.bb {
	margin-right: 15px;
}

table {
	display: none;
}
</style>
</head>
<body>
	<hr>
	<div class="cintainer-fluid">
		<div class="row">
			<FORM METHOD="post" ACTION="blogArticle.do" name="form1">
				<div class="col-sm-12 col-md-2 bb">
					<table>
						<tr>
							<td>文章編號:<font color=red><b>*</b></font></td>
							<td><%=blogArticleVO.getArticleNo()%></td>
						</tr>
						<br>
						<tr>
							<td>作者編號:</td>
							<td><input type="TEXT" name="authorNo" size="30"
								value="<%=blogArticleVO.getAuthorNo()%>" /></td>
						</tr>
						<br>
						<tr>
							<td>發文時間:</td>
							<td bgcolor="#CCCCFF"><input class="cal-TextBox"
								onFocus="this.blur()" size="20" readonly type="text"
								name="articleTime" value="<%=blogArticleVO.getArticleTime()%>">
								<!-- 			<a class="so-BtnLink" --> <!-- 			href="javascript:calClick();return false;" -->
								<!-- 			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
								<!-- 			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
								<!-- 			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','msgTime','BTN_date');return false;"> -->
								<!-- 		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="時間"></a> -->
							</td>
						</tr>
						<br>
						<tr>
							<td>是否被檢舉:</td>
							<td><input type="TEXT" name="reported" size="30"
								value="<%=blogArticleVO.getReported()%>" /></td>
						</tr>
						<br>
						<tr>
							<td>管理員審閱:</td>
							<td><input type="TEXT" name="reviewed" size="30"
								value="<%=blogArticleVO.getReviewed()%>" /></td>
						</tr>
						<br>
						<tr>
							<td>是否屏蔽:</td>
							<td><input type="TEXT" name="isBlocked" size="30"
								value="<%=blogArticleVO.getIsBlocked()%>" /></td>
						</tr>
						<br>
						<tr>
							<td>不屏蔽理由:</td>
							<td><input type="TEXT" name="blockedReason" size="30"
								value="<%=blogArticleVO.getBlockedReason()%>" /></td>
						</tr>
					</table>
				</div>
				<div class="well col-sm-12 col-md-6 aa">

					<input type="TEXT" name="articleTitle" size="45"
						value="<%=blogArticleVO.getArticleTitle()%>"
						style="width: 300px; height: auto;" placeholder="請輸入文章標題"
						; required="required" />

					<textarea name="articleContent">
						<%=blogArticleVO.getArticleContent()%>
					</textarea>


					<script>
						CKEDITOR.replace('articleContent');
					</script>


					<div class="btn1">

						<FORM>
							<input type="hidden" name="action" value="update"> <input
								type="hidden" name="articleNo"
								value="<%=blogArticleVO.getArticleNo()%>"> <select
								class="form-control" type="hidden" name="viewable">
								<option value="0">公開</option>
								<c:if test="${blogArticleVO.viewable.equals('1')}">
									<option value="1" selected>好友</option>
								</c:if>
								<c:if test="${!blogArticleVO.viewable.equals('1')}">
									<option value="1">好友</option>
								</c:if>
								<c:if test="${blogArticleVO.viewable.equals('2')}">
									<option value="2" selected>私人</option>
								</c:if>
								<c:if test="${!blogArticleVO.viewable.equals('2')}">
									<option value="2">私人</option>
								</c:if>

							</select> <input class="btn btn-default" type="submit" value="儲存並送出">
						</FORM>
						<input class="btn btn-default back" type="button"
							value="取消編輯(Test)" onclick="history.back()">
						<FORM>
							<input type="hidden" name="action" value="delete"> <input
								type="hidden" name="articleNo"
								value="<%=blogArticleVO.getArticleNo()%>"> <input
								class="btn btn-primary" type="submit" value="刪除此文章">
						</FORM>

					</div>

				</div>
			</FORM>
			<div class="col-sm-12 col-md-2">

				<a
					href="<%=request.getContextPath()%>/frontend/traveler/blogArticle/listAllBlogArticle.jsp"
					class="btn btn-default btn-lg btn2" role="button">文章列表</a>

				<!-- 我的行程 -->
				<div class="well">
					<div class="panel panel-group panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">行程資訊</h3>
						</div>
					</div>
					<div class="panel-body schedule">
						<div class="panel panel-group panel-primary">
							<div class="panel-heading" data-toggle="collapse"
								data-target="#cc1" aria-expanded="false" aria-controls="#cc1">
								第一天
								<div class="day_close">x</div>
							</div>
							<div class="panel-body list-group plainList collapse" id="cc1">
								<a href="#" class="list-group-item active">
									<h4 class="list-group-item-heading">行程景點 1</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a> <a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">行程景點 2</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a> <a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">行程景點 3</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a> <a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">行程景點 4</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a> <a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">行程景點 5</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a> <a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">行程景點 6</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a> <a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">行程景點 7</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a> <a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">行程景點 8</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a>
							</div>
						</div>
						<div class="panel panel-group panel-primary">
							<div class="panel-heading" data-toggle="collapse"
								data-target="#cc2" aria-expanded="false" aria-controls="#cc2">
								第二天
								<div class="day_close">x</div>
							</div>
							<div class="panel-body list-group plainList collapse" id="cc2">
								<a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">行程景點 9</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a> <a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">行程景點 10</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a> <a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">行程景點 11</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a> <a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">行程景點 12</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a> <a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">行程景點 13</h4>
									<p class="list-group-item-text">預計停留30分鐘</p>
								</a>
							</div>
						</div>
					</div>
				</div>
				<!--行程結束-->
			</div>
			<!-- 聊天室 -->
			<div class="col-sm-12 col-md-2">
				<div class="box">
					<div class="head">Chat Room</div>
					<div class="chat-sidebar">
						<div class="sidebar-name">
							<!-- Pass username and display name to register popup -->
							<a
								href="javascript:register_popup('narayan-prusty', 'Narayan Prusty');">
								<img width="30" height="30" src="" /> <span>Narayan
									Prusty</span>
							</a>
						</div>
						<div class="sidebar-name">
							<a href="javascript:register_popup('qnimate', 'QNimate');"> <img
								width="30" height="30" src="" /> <span>QNimate</span>
							</a>
						</div>
						<div class="sidebar-name">
							<a href="javascript:register_popup('qscutter', 'QScutter');">
								<img width="30" height="30" src="" /> <span>QScutter</span>
							</a>
						</div>
					</div>
				</div>
			</div>
			<!-- /聊天室 -->
		</div>

		<hr>

		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-sm-12 col-md-12"></div>
			</div>
			<!-- /.row -->
		</footer>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/chatroom/chatroom.js"></script>
	<script type="text/javascript" src="js/plan.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>

