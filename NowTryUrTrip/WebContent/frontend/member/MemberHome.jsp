<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%> 

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>   
    
<html lang="">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<%=request.getContextPath()%>/css/frontend/member/MemberDashboard.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/frontend/member/MemberHome.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/css/header_and_footer/header.css" rel="stylesheet" >
<script src="https://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/header_and_footer/hf.jsp"></script>  
<script src="<%=request.getContextPath()%>/js/frontend/member/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body id="bootstrap-overrides">
    <!-- 我會在header tag放內容，勿動 -->
    <div id="header" class="fh"></div>
    <div class="content-cap">參考header的高度</div>
    <div class="container-fluid content" style="">
    
<div class="col-sm-3 col-md-2 sidebar" style=" width: 280px;">
		 <div class="sidebar_top">
			 <h1>${memberVO.memName}</h1> 
			 <img style="width: 150px;" src="<%=request.getContextPath()%>/Member/memImg.do?memId=${memberVO.memId}"/>
		 </div>
		<div class="details">
			 <h3>點數</h3>
			 <p>${memberVO.memBop}</p>  	 
			 <h3>EMAIL</h3>
			 <p><a href="mailto@example.com">mail@example.com</a></p>
			 <address>
			 <h3>ADDRESS</h3>
			 <span>Lorem ipsum,</span>
			 <span>Sed dolor sit,</span>
			 <span>Praesent porta.</span>
			 </address>
		</div>
		<div class="clearfix"></div>
</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	 <div class="content">
		 <div class="container-fluid">
					<div class="row">
						<div role="tabpanel">
							<!-- 標籤面板：標籤區 -->
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active">
								<a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">好友專區</a>
								</li>
								<li role="presentation">
								<a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">我的行程</a>
								</li>
								<li role="presentation">
								<a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">評論</a>
								</li>
								<li role="presentation">
								<a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">附近設施</a>
								</li>
							</ul>
							<!-- 標籤面板：內容區 -->
							<div class="tab-content">
								<!-- 景點簡介 -->
								<div role="tabpanel" class="tab-pane active" id="tab1"><c:import url="/frontend/member/Frined/listAllFriend.jsp"></c:import></div>
								<!-- 相簿 -->
								<div role="tabpanel" class="tab-pane" id="tab2">
								<div role="tabpanel" class="tab-pane active" id="tab1"><c:import url="/frontend/tripPlan/trip_list.jsp"></c:import></div>	
								</div>
							
							<jsp:useBean id="spotCmntSvc" class="com.spotCmnt.model.SpotCmntService" scope="page" />
							<jsp:useBean id="memberSvc" class="com.member.model.MemberService" scope="page" />
							<div role="tabpanel" class="tab-pane" id="tab3">
								<div class="container-fluid" >
									<!-- 一則評論 -->
									<%
										int count = 1;
									%>
									<div id="commentList">
									<c:forEach var="spotCmntVO" items="${spotCmntSvc.all}">
										<c:if test="${spotVO.spotNo == spotCmntVO.spotNo }">
											<div class="row" style="background-color: #888">
												<div class="col-xs-12 col-sm-3" style="background-color: #666;">
													<table>
													<tr>
														<td rowspan="2" style="padding-top: 10px; padding-bottom:10px;width:80px"><img src="<%=request.getContextPath() %>/Member/memImg.do?memId=1" style="max-width: 100%;"></td>
														<td>ID:${memberSvc.getOneMember(spotCmntVO.memId).memId}</td>
													</tr>
													<tr>
														<td>姓名:${memberSvc.getOneMember(spotCmntVO.memId).memName }</td>
													</tr>
													</table>
												</div>
												<div class="col-xs-12 col-sm-9">
													<div class="row" style="background-color: #ccc">
														
															第<%=count++%>則評論
														
													</div>
													<div class="row" style="background-color: #ccc">
														<c:forEach begin="1" end="${spotCmntVO.rate }">
															<i class="fa fa-star"></i>
														</c:forEach>
													</div>
													<div class="row" style="background-color: #eee">
														<span>${spotCmntVO.cmnt }</span>
													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
									</div>
									<div class="row" style="background-color: #ccc">
										<div class="col-xs-12 col-sm-3">
											<table>
												<tr>
													<td rowspan="2" style="padding-top: 10px; padding-bottom:10px;width:80px">
														<img src="<%=request.getContextPath() %>/Member/memImg.do?memId=1" style="max-width: 100%;">
													</td>
													<td id="memId">會員ID</td>
												</tr>
												<tr>
													<td id="memName">會員名稱</td>
												</tr>
											</table>
										</div>
										<div class="col-xs-12 col-sm-9">
											<fieldset class="rating">
											    <input type="radio" id="star5" name="rating" value="10" />
											    <label class = "full" for="star5" title="Awesome - 5 stars"></label>
											    <input type="radio" id="star4half" name="rating" value="9" />
											    <label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
											    <input type="radio" id="star4" name="rating" value="8" />
											    <label class = "full" for="star4" title="Pretty good - 4 stars"></label>
											    <input type="radio" id="star3half" name="rating" value="7" />
											    <label class="half" for="star3half" title="Meh - 3.5 stars"></label>
											    <input type="radio" id="star3" name="rating" value="6" />
											    <label class = "full" for="star3" title="Meh - 3 stars"></label>
											    <input type="radio" id="star2half" name="rating" value="5" />
											    <label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
											    <input type="radio" id="star2" name="rating" value="4" />
											    <label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
											    <input type="radio" id="star1half" name="rating" value="3" />
											    <label class="half" for="star1half" title="Meh - 1.5 stars"></label>
											    <input type="radio" id="star1" name="rating" value="2" />
											    <label class = "full" for="star1" title="Sucks big time - 1 star"></label>
											    <input type="radio" id="starhalf" name="rating" value="1" />
											    <label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
											</fieldset>
											<textarea rows="5" placeholder="輸入你想說的話" id="cmnt" style="width:100%; box-sizing:border-box;resize: none;margin-top:5px;margin-bottom:5px"></textarea>
										</div>
										<button type="button" class="btn btn-primary"  style="float:right;" id="leaveCmnt" onclick="leaveCmnt()">留言</button>
									</div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="tab4" style="width:100%; height:100%;">
											<div id="map"></div>
							</div>
							</div>
						</div>
					</div>
				</div>
		 
		 
		 <div class="copywrite">
			 <p>© 2015 Curriculum Vitae All Rights Reseverd | Design by <a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>
		 </div>
	 </div>
</div>
</body>
</html>