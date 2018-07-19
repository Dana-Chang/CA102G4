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
<title>部落格_新增文章</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="js/plan.css">
<link href="js/chatroom.css" rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/frontend/chatroom/chatroom.css"
	rel="stylesheet">
<script src="ckeditor/ckeditor.js"></script>

<style type="text/css">
hr {
	border-top: 1px dashed #aaa;
}

body {
	padding-top: 70px;
	background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0.6) 0%,
		rgba(255, 255, 255, 0.6) 100%), url(<%=request.getContextPath()%>/frontend/traveler/blogArticle/images/sky.jpg);
}

footer {
	margin: 50px 0;
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
</style>

</head>
<body>
	<hr>
	<div class="cintainer-fluid">
		<div class="row">
			<FORM METHOD="post" ACTION="blogArticle.do" name="form1">
				<div class="well col-sm-12 col-md-2 bb">
					<tr>
						<td>作者編號:</td>
						<td><input type="TEXT" name="authorNo" size="30"
							value="<%=(blogArticleVO == null) ? "001" : blogArticleVO.getAuthorNo()%>" /></td>
					</tr>
					<br>
				</div>

				<div class="well col-sm-12 col-md-6 aa">

					<input type="text" name="articleTitle"
						value="<%=(blogArticleVO == null) ? "" : blogArticleVO.getArticleTitle()%>"
						style="width: 300px; height: auto;" placeholder="請輸入文章標題"
						; required="required">

					<textarea name="articleContent"
						value="<%=(blogArticleVO == null) ? "目前沒有內容" : blogArticleVO.getArticleContent()%>">
						
						<p>
							<img alt="" class="left"
								src="https://www.bkosborne.com/sites/default/files/waterwheelcarousel/1.jpg"
								style="height: 90px; width: 160px" />
						</p>

						<p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>


					</textarea>

					<script>
						CKEDITOR.replace('articleContent');
					</script>


					<div class="btn1">
						<input type="hidden" name="action" value="insert"> <input
							class="btn btn-default" type="submit" value="送出"> <input
							class="btn btn-default back" type="button" value="取消編輯(Test)"
							onclick="history.back()"> <select class="form-control"
							name="viewable">
							<option value="0">公開</option>
							<option value="1">好友</option>
							<option value="2">私人</option>
						</select>
					</div>

				</div>
			</FORM>
			<div class="col-sm-12 col-md-2">

				<a
					href="<%=request.getContextPath()%>/blogArticle/listAllBlogArticle.jsp"
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

