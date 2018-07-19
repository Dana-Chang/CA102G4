<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blogArticle.model.*"%>
<%@ page import="com.blogMsg.model.*"%>
<%@ page import="com.blogMsgReply.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	BlogArticleVO blogArticleVO = (BlogArticleVO) request.getAttribute("blogArticleVO"); //EmpServlet.java(Concroller), 存入req的blogArticleVO物件
	BlogMsgService blogMsgSvc = new BlogMsgService();
	List<BlogMsgVO> list = blogMsgSvc.getAll();
	pageContext.setAttribute("list", list);
	BlogMsgReplyService blogMsgReplySvc = new BlogMsgReplyService();
	List<BlogMsgReplyVO> list1 = blogMsgReplySvc.getAll();
	pageContext.setAttribute("list1", list1);
	BlogArticleService blogArticleSvc = new BlogArticleService();
	List<BlogArticleVO> list2 = blogArticleSvc.getAll();
	pageContext.setAttribute("list2", list2);
%>
<!DOCTYPE html>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<title>部落格_內文_會員視角</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/frontend/traveler/blogArticle/js/plan.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link
	href="<%=request.getContextPath()%>/frontend/chatroom/chatroom.css"
	rel="stylesheet">

<style type="text/css">
.list-unstyled li {
	padding: 5px 15px;
}

footer {
	margin: 50px 0;
}

.bk1 {
	margin-bottom: 30px;
}

hr {
	border-top: 1px dashed #aaa;
}

.add {
	margin: 10px 20px;
	border: 0;
	background-color: #fdfdfd;
}

body {
	padding-top: 70px;
	background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0.6) 0%,
		rgba(255, 255, 255, 0.6) 100%), url(<%=request.getContextPath()%>/frontend/traveler/blogArticle/images/sky.jpg);
}

textarea {
	resize: none;
}

.reportlvmsg {
	margin: 10px;
	margin-left: 0px;
	margin-top: -10px;
	float: right;
}

.reportlvmsg2 {
	margin: 10px;
	margin-left: 0px;
	margin-top: -10px;
	float: right;
}

.reply {
	margin: 10px;
	margin-top: -10px;
	float: right;
}

.btn3 {
	float: right;
	margin-top: 10px;
}

img.right {
	float: right;
	margin-left: 15px;
}

img.left {
	float: left;
	margin-right: 15px;
}

.well {
	background-color: #F0F8FF;
	border-radius: 20px;
}

.media {
	padding-bottom: 10px;
}

.mt-3 {
	margin-left: 80px;
}

.btn1 {
	float: right;
}
</style>

<script type="text/javascript">
		$(document).ready(function() {
	
			$('.block1').hide();
			$('.block2').hide();
			$('.block3').hide();
			$('.block4').hide();	
			
			$('.reportArticle').click(function() {
				$('.block1').slideToggle();
			});
			
		});
		
		function replyMsg(e){
			$('#reply'+e).slideToggle();
		}
	
		function reportMsg(e){
			$('#report'+e).slideToggle();
		}
		
		function reportMsg2(e){
			$('#report2'+e).slideToggle();
		}		
	</script>

</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="blog_homePage.html">回部落格首頁(Test)</a>
			</div>

			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 左選單 -->
				<ul class="nav navbar-nav">
					<li><a href="#">關於CSS可樂</a></li>
					<li><a href="#">CSS教學</a></li>
					<li><a href="#">CSS範例</a></li>
					<li><a href="#">原創CSS</a></li>
				</ul>

				<!-- 右選單 -->
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Amos 您好</a></li>
					<li><a href="#">登出</a></li>
					<li><a href="#">個人設定</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">繁體中文 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">繁體中文</a></li>
							<li><a href="#">English</a></li>
							<li><a href="#">日本語</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</div>
	</nav>

	<hr>

	<!-- Page Content -->
	<!--     <div class="container"> -->
	<div class="container-fluid">
		<div class="row">

			<!-- Blog Post Content Column -->
			<div class="col-sm-2 col-md-2 col-md-offset-1">
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
					<!--行程結束-->
				</div>
			</div>

			<div class="col-sm-6 col-md-6">

				<!-- Blog Post -->

				<!-- Title -->
				<h1>${blogArticleVO.articleTitle}</h1>

				<hr>

				<!-- Date/Time -->
				<p>
					<span class="glyphicon glyphicon-time post-time"></span> 發表於
					<fmt:formatDate value="${blogArticleVO.articleTime}"
						pattern="yyyy/MM/dd HH:mm" />
				</p>
				<button class="btn btn-primary reportArticle">檢舉文章</button>

				<div class="block1">
					<div>

						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/articleReport/articleReport.do"
							name="form1">
							<%@ include file="../articleReport/addArticleReport.jsp"%>
						</FORM>
						<hr>
					</div>
				</div>
				<div class="btn1">
					<a class="btn btn-default"
						href="<%=request.getContextPath()%>/frontend/traveler/blogArticle/listAllBlogArticle.jsp"
						role="button">回文章列表(Test)</a>
				</div>
				<br style="clear: all">
				<hr>

				<div class="col-md-12 bk1">${blogArticleVO.articleContent}</div>

				<hr style="clear: both; margin-bottom: 30px">

				<!-- Blog Comments -->

				<!-- Comments Form -->
				<div class="well">
					<h4>我要留言:</h4>

					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/blogMsg/blogMsg.do">
						<%@ include file="../blogMsg/addBlogMsg.jsp"%>
					</FORM>

					<div style="clear: both;"></div>

				</div>

				<hr>
				<!-- Comment -->
				<c:forEach var="blogMsgVO" items="${list}">
					<c:if test="${blogArticleVO.articleNo== blogMsgVO.articleNo}">
						<div class="media">
							<div class="media-body">
								<h4 class="media-heading">${blogMsgVO.msgMemId}
									留言於: <small>${blogMsgVO.msgTime}</small>
								</h4>
								${blogMsgVO.msgContent}
								<!-- Nested Comment -->
								<a class=" btn btn-default btn-sm reportlvmsg"
									onclick="reportMsg(${blogMsgVO.msgNo})">檢舉</a> <a
									class="btn btn-default btn-sm reply"
									onclick="replyMsg(${blogMsgVO.msgNo})">回覆</a>
							</div>
							<div class="block2" id="reply${blogMsgVO.msgNo}">
								<div>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/blogMsgReply/blogMsgReply.do">
										<%@ include file="../blogMsgReply/addBlogMsgReply.jsp"%>
									</FORM>
								</div>
							</div>
							<div class="block3" id="report${blogMsgVO.msgNo}">
								<div>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/blogMsgReport/blogMsgReport.do">
										<%@ include file="../blogMsgReport/addBlogMsgReport.jsp"%>
									</FORM>
								</div>
							</div>
							<c:forEach var="blogMsgReplyVO" items="${list1}">
								<c:if test="${blogMsgVO.msgNo == blogMsgReplyVO.msgNo}">
									<div class="media mt-3">
										<div class="media-body">
											<h4 class="media-heading">
												${blogMsgReplyVO.msgMemId} 回應於 <small>${blogMsgReplyVO.msgTime}</small>
											</h4>
											${blogMsgReplyVO.msgContent} <a
												class=" btn btn-default btn-sm reportlvmsg2"
												onclick="reportMsg2(${blogMsgReplyVO.replyNo})">檢舉</a>
											<div style="clear: both;"></div>
											<div class="block4" id="report2${blogMsgReplyVO.replyNo}">
												<div>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/blogReplyReport/blogReplyReport.do">
														<%@ include
															file="../blogReplyReport/addBlogReplyReport.jsp"%>
													</FORM>

												</div>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
						<!-- End Nested Comment -->

					</c:if>
				</c:forEach>

			</div>

			<!-- Blog Sidebar Widgets Column -->
			<div class="col-sm-2 col-md-2">

				<!-- Blog Search Well -->
				<div class="well">
					<h4>搜尋文章</h4>
					<FORM METHOD="post" ACTION="blogArticle.do" name="form1">
						<div class="input-group">
							<input type="text" class="form-control" name="articleContent">
							<span class="input-group-btn">
								<button class="btn btn-default" type="submit">
									<span class="glyphicon glyphicon-search"></span>
								</button> <input type="hidden" name="action" value="search">
							</span>
						</div>
					</FORM>
				</div>

				<!-- Blog Categories Well -->
				<div class="well">
					<h4>熱門文章</h4>
					<div class="row">
						<!-- <div class="col-md-2 col-lg-2"> -->
						<ul class="list-unstyled">
							<li><a href="#">文章標題1</a></li>
							<li><a href="#">文章標題2</a></li>
							<li><a href="#">文章標題3</a></li>
							<li><a href="#">文章標題4</a></li>
						</ul>
						<!-- </div> -->
					</div>
					<!-- /.row -->
				</div>

				<div class="well">
					<h4>關於作者</h4>

					<div class="row">
						<img class="add-img-responsive col-sm-5 col-md-5"
							src="https://yt3.ggpht.com/-eSR9OAyJvFs/AAAAAAAAAAI/AAAAAAAAAAA/It1I1mC-mUM/s100-c-k-no-mo-rj-c0xffffff/photo.jpg"
							class="img-responsive">
						<button class="add">加入好友</button>
					</div>
					<hr>

					<p>暱稱 : Pikachu</p>
					<p>
						個人首頁 : <a href="blog_articlelist.html">點我</a>
					</p>
					<p>電子郵件 : Pikachu@gmail.com</p>


				</div>

				<div class="well">
					<h4>文章列表</h4>
					<div class="row">
						<ul class="list-unstyled">
							<c:forEach var="blogArticleVO" items="${list2}">
								<li><a
									href="blogArticle.do?articleNo=${blogArticleVO.articleNo}&action=getOne_For_Display">${blogArticleVO.articleTitle}</a></a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>

				<div class="well">
					<h4>最新文章</h4>
					<div class="row">
						<!-- <div class="col-md-2 col-lg-2"> -->
						<ul class="list-unstyled">
							<c:forEach var="blogArticleVO" items="${list2}">
								<li><a
									href="blogArticle.do?articleNo=${blogArticleVO.articleNo}&action=getOne_For_Display">${blogArticleVO.articleTitle}</a></a>
								</li>
							</c:forEach>
						</ul>
						<!-- </div> -->
					</div>
					<!-- /.row -->
				</div>
			</div>

			<!-- 聊天室 -->
			<div class="col-sm-12 col-md-1">
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
		<!-- /.row -->

		<hr>

		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-sm-12 col-md-12"></div>
			</div>
			<!-- /.row -->
		</footer>

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/traveler/blogArticle/js/plan.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/chatroom/chatroom.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
