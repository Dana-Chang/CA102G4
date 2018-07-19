<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blogArticle.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	BlogArticleService blogArticleSvc = new BlogArticleService();
	List<BlogArticleVO> list = blogArticleSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<%
	BlogArticleVO blogArticleVO = (BlogArticleVO) request.getAttribute("blogArticleVO");
%>
<!DOCTYPE html>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<title>部落格_文章列表_會員視角</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link
	href="<%=request.getContextPath()%>/frontend/chatroom/chatroom.css"
	rel="stylesheet">

<style type="text/css">
.add {
	margin: 10px 20px;
	border: 0;
	background-color: #fdfdfd;
}

.list-unstyled li {
	padding: 5px 15px;
}

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

img.right {
	float: right;
	margin-left: 15px;
}

img.left {
	float: left;
	margin-right: 15px;
}

#form2 {
	float: left;
	padding-right: 6px;
}
</style>
</head>
<body>
	<!-- 這裡放header -->
	<hr>
	<!-- Page Content -->
	<div class="container-fluid">

		<div class="row">

			<!-- Blog Post Content Column -->
			<div class="col-sm-12 col-md-2"></div>

			<!-- Blog Entries Column -->
			<div class="col-sm-12 col-md-6">
				<h1 class="header">Pikachu 的部落格</h1>
				<hr>
				<%@ include file="page1.file"%>
				<c:forEach var="blogArticleVO" items="${list}"
					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

					<h3>
						<a
							href="blogArticle.do?articleNo=${blogArticleVO.articleNo}&action=getOne_For_Display">${blogArticleVO.articleTitle}</a>
					</h3>

					<p>
						<span class="glyphicon glyphicon-time post-time"></span> 發表於
						<fmt:formatDate value="${blogArticleVO.articleTime}"
							pattern="yyyy/MM/dd HH:mm" />
					</p>

					<div style="height: 100px; overflow: hidden;">
						${blogArticleVO.articleContent}</div>
					<br>
					<a class="btn btn-primary"
						href="blogArticle.do?articleNo=${blogArticleVO.articleNo}&action=getOne_For_Display">Read
						More <span class="glyphicon glyphicon-chevron-right"></span>
					</a>

					<hr>
				</c:forEach>
				<%@ include file="page2.file"%>

			</div>

			<!-- Blog Comments -->

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
						個人首頁 : <a href="blog_articlelist.html">點我(Test)</a>
					</p>
					<p>電子郵件 : Pikachu@gmail.com</p>


				</div>

				<div class="well">
					<h4>文章列表</h4>
					<div class="row">
						<ul class="list-unstyled">
							<c:forEach var="blogArticleVO" items="${list}">
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
							<c:forEach var="blogArticleVO" items="${list}">
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
		src="<%=request.getContextPath()%>/frontend/chatroom/chatroom.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
