<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<title>部落格_首頁</title>
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
hr {
	border-top: 1px dashed #aaa;
}

.list-unstyled li {
	padding: 5px 15px;
}

body {
	background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0.6) 0%,
		rgba(255, 255, 255, 0.6) 100%),
		url(/AA107G5/frontend/traveler/blogArticle/images/sky.jpg);
}

.well {
	background-color: #F0F8FF;
	border-radius: 20px;
}

.bloghpblock1img {
	width: 300px;
	height: 180px;
	margin-bottom: 25px;
	margin-top: 25px;
	float: left;
}

.bloghomearticle {
	width: 300px;
	height: 180px;
	box-shadow: inset 0px -50px 10px -5px rgba(0, 0, 0, 0.3);
	background-repeat: no-repeat;
	position: absolute;
}

.bloghomearticle div {
	padding-top: 144px;
	text-align: center;
	color: white;
	font-size: 20px;
	font-family: 'Helvetica Neue', Helvetica, Arial, STHeiti,
		'Microsoft JhengHei', 微軟正黑體, sans-serif;
}

#bloghpblock1 .well {
	overflow: hidden;
}

#bk1 div {
	padding-left: 0px;
}

#bk2 {
	padding-left: 10px;
	text-align: center;
}
</style>
</head>

<body id="bootstrap-overrides">

	<div id="header" class="fh"></div>
	<div class="content-cap">參考header的高度</div>
	<hr>

	<!-- Page Content -->
	<!--     <div class="container"> -->
	<div class="container-fluid">
		<div class="row">

			<!-- Blog Post Content Column -->

			<div class="col-sm-12 col-md-6 col-md-offset-2" id="bloghpblock1">
				<div class="well row" id="bk1">
					<h3>
						推薦文章
						<h3>
							<div class="col-sm-12 col-md-4">

								<div class="bloghpblock1img"
									style="background: url(https://www.bkosborne.com/sites/default/files/waterwheelcarousel/8.jpg);">
									<a href="#" class="bloghomearticle"><div>
											<b>京阪神五日遊</b>
										</div></a>
								</div>
								<div class="bloghpblock1img"
									style="background: url(https://www.bkosborne.com/sites/default/files/waterwheelcarousel/3.jpg);">
									<a href="#" class="bloghomearticle"><a href="#"
										class="bloghomearticle"><div>
												<b>十日環島自由行</b>
											</div></a>
								</div>

							</div>
							<div class="col-sm-12 col-md-4">

								<div class="bloghpblock1img"
									style="background: url(https://www.bkosborne.com/sites/default/files/waterwheelcarousel/9.jpg);">
									<a href="#" class="bloghomearticle"><div>
											<b>港澳三天兩夜自由行</b>
										</div></a>
								</div>
								<div class="bloghpblock1img"
									style="background: url(https://www.bkosborne.com/sites/default/files/waterwheelcarousel/7.jpg);">
									<a href="#" class="bloghomearticle"><div>
											<b>北歐賞極光</b>
										</div></a>
								</div>

							</div>
							<div class="col-sm-12 col-md-4">

								<div class="bloghpblock1img"
									style="background: url(https://www.bkosborne.com/sites/default/files/waterwheelcarousel/1.jpg);">
									<a href="#" class="bloghomearticle"><div>
											<b>台南小吃一日吃到飽</b>
										</div></a>
								</div>
								<div class="bloghpblock1img"
									style="background: url(https://www.bkosborne.com/sites/default/files/waterwheelcarousel/2.jpg);">
									<a href="#" class="bloghomearticle"><div>
											<b>阿里山賞櫻+日出</b>
										</div></a>
								</div>

							</div>
				</div>

				<hr>

				<!-- Blog Categories Well -->

				<div class="well row">
					<h4>推薦部落客</h4>
					<br>
					<div id="bk2">
						<div class="col-sm-12 col-md-2">
							<a href="listAllBlogArticle_memberView.jsp"><img
								src="https://yt3.ggpht.com/-eSR9OAyJvFs/AAAAAAAAAAI/AAAAAAAAAAA/It1I1mC-mUM/s100-c-k-no-mo-rj-c0xffffff/photo.jpg"
								class="img-responsive"> <span>Pikachu</span></a>
						</div>
						<div class="col-sm-12 col-md-2">
							<a href="#"><img
								src="https://api.fnkr.net/testimg/100x100/00CED1/FFF/?text=img+placeholder"
								class="img-responsive">
								<div>部落客暱稱</div></a>
						</div>
						<div class="col-sm-12 col-md-2">
							<a href="#"><img
								src="https://api.fnkr.net/testimg/100x100/00CED1/FFF/?text=img+placeholder"
								class="img-responsive">
								<div>部落客暱稱</div></a>
						</div>
						<div class="col-sm-12 col-md-2">
							<a href="#"><img
								src="https://api.fnkr.net/testimg/100x100/00CED1/FFF/?text=img+placeholder"
								class="img-responsive">
								<div>部落客暱稱</div></a>
						</div>
						<div class="col-sm-12 col-md-2">
							<a href="#"><img
								src="https://api.fnkr.net/testimg/100x100/00CED1/FFF/?text=img+placeholder"
								class="img-responsive">
								<div>部落客暱稱</div></a>
						</div>
						<div class="col-sm-12 col-md-2">
							<a href="#"><img
								src="https://api.fnkr.net/testimg/100x100/00CED1/FFF/?text=img+placeholder"
								class="img-responsive">
								<div>部落客暱稱</div></a>
						</div>
					</div>
					<!-- </div> -->
				</div>
				<!-- /.row -->

				<hr>

				<div class="well row">
					<h4>最新文章</h4>
					<br>
					<ul style="text-align: center;" class="list-unstyled" id="bk2">
						<li class="col-sm-12 col-md-2"><a
							href="listOneBlogArticle.jsp"><img
								src="https://api.fnkr.net/testimg/100x100/00CED1/FFF/?text=img+placeholder"
								class="img-responsive">
								<div>文章標題</div></a></li>
						<li class="col-sm-12 col-md-2"><a href="#"><img
								src="https://api.fnkr.net/testimg/100x100/00CED1/FFF/?text=img+placeholder"
								class="img-responsive">
								<div>文章標題</div></a></li>
						<li class="col-sm-12 col-md-2"><a href="#"><img
								src="https://api.fnkr.net/testimg/100x100/00CED1/FFF/?text=img+placeholder"
								class="img-responsive">
								<div>文章標題</div></a></li>
						<li class="col-sm-12 col-md-2"><a href="#"><img
								src="https://api.fnkr.net/testimg/100x100/00CED1/FFF/?text=img+placeholder"
								class="img-responsive">
								<div>文章標題</div></a></li>
						<li class="col-sm-12 col-md-2"><a href="#"><img
								src="https://api.fnkr.net/testimg/100x100/00CED1/FFF/?text=img+placeholder"
								class="img-responsive">
								<div>文章標題</div></a></li>
						<li class="col-sm-12 col-md-2"><a href="#"><img
								src="https://api.fnkr.net/testimg/100x100/00CED1/FFF/?text=img+placeholder"
								class="img-responsive">
								<div>文章標題</div></a></li>
					</ul>
					<!-- </div> -->
				</div>
				<!-- /.row -->
			</div>

			<div class="col-sm-12 col-md-2">

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

				<div class="well">
					<h4>最近瀏覽的文章</h4>
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
					<h4>好友的部落格</h4>
					<div class="row">
						<!-- <div class="col-md-2 col-lg-2"> -->
						<ul class="list-unstyled">
							<li><a href="blog_articlelist.html">Pikachu(Test)</a></li>
							<li><a href="#">部落客暱稱2</a></li>
							<li><a href="#">部落客暱稱3</a></li>
							<li><a href="#">部落客暱稱4</a></li>
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
	    <script src="<%=request.getContextPath()%>/js/header_and_footer/hf.jsp"></script>
</body>

</html>
