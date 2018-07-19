<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blogArticle.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="search" scope="request" type="java.util.List" />
<%
    BlogArticleService blogArticleSvc = new BlogArticleService();
    List<BlogArticleVO> list = blogArticleSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<title>部落格_文章列表_作者視角</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header_and_footer/header.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header_and_footer/footer.css">
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

hr {
	border-top: 1px dashed #aaa;
}

body {
	background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0.6) 0%,
		rgba(255, 255, 255, 0.6) 100%), url(<%=request.getContextPath()%>/frontend/traveler/blogArticle/images/sky.jpg);
}

.addArticle {
	float: right;
	margin-top: 15px;
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

#form2 {
	float: left;
	padding-right: 6px;
}
</style>
</head>
<body id="bootstrap-overrides">

	<div id="header" class="fh"></div>
	<div class="content-cap">參考header的高度</div>
	<hr>
	<!-- Page Content -->
	<div class="container-fluid">

		<div class="row">

			<!-- Blog Post Content Column -->
			<div class="col-sm-12 col-md-2"></div>

			<!-- Blog Entries Column -->
			<div class="col-sm-12 col-md-6">
				<h1 class="header">文章列表</h1>
				<hr>
				<c:forEach var="blogArticleVO" items="${search}">

					<h3>
						<a
							href="blogArticle.do?articleNo=${blogArticleVO.articleNo}&action=getOne_For_Display">${blogArticleVO.articleTitle}</a>
					</h3>
					
					<p>
						<span class="glyphicon glyphicon-time post-time"></span> 發表於
						<fmt:formatDate value="${blogArticleVO.articleTime}"
							pattern="yyyy/MM/dd HH:mm" />
							by ${blogArticleVO.authorNo}
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
						<ul class="list-unstyled">
							<li><a href="#">文章標題1</a></li>
							<li><a href="#">文章標題2</a></li>
							<li><a href="#">文章標題3</a></li>
							<li><a href="#">文章標題4</a></li>
						</ul>
					</div>
				</div>

				<div class="well">
					<h4>最新文章</h4>
					<div class="row">
						<!-- <div class="col-md-2 col-lg-2"> -->
						<ul class="list-unstyled">
							<c:forEach var="blogArticleVO" items="${list}">                 
                                <li>
                                	<a href="blogArticle.do?articleNo=${blogArticleVO.articleNo}&action=getOne_For_Display">${blogArticleVO.articleTitle}</a></a>
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

		<hr>
	</div>
	<!-- /.container -->
	<!-- Footer -->
	<div id="footer" class=""></div>

	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/chatroom/chatroom.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<%--     <script src="<%=request.getContextPath()%>/js/header_and_footer/hf.jsp"></script> --%>
</body>

</html>
