<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%><!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
 --><link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<!-- header.css以及footer.css勿刪… -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header_and_footer/header.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header_and_footer/footer.css">
<!-- 本頁專屬css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/frontend/tripPlan/tripPlan.css">
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body id="bootstrap-overrides">
	<!-- 我會在header tag放內容，勿動 -->
	<div id="header" class="fh"></div>
	<div class="container-fluid content-cap">參考header的高度</div>
	<div class="container-fluid content" style="">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
				<div class="page-header">
					<h3>
						地點 / 行程列表
					</h3>
					<div id='position'></div>
			  	</div>
				<div id="spots_n_trips" class="panel-group" role="tablist" aria-multiselectable="true">
				  <!-- 區塊1 -->
				  <div class="panel panel-success">
				    <div class="panel-heading" role="tab" id="panel1">
				      <h4 class="panel-title">
				        <a href="#spots" data-parent="#spots_n_trips" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="spots">
				          <!-- <div class="page-header"> -->
							<!-- <h3> -->
								地點列表
							<!-- </h3> -->
						  <!-- </div> -->
				        </a>
				      </h4>
				    </div>
				    <div id="spots" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1">
				      <div class="panel-body">
						<form>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-btn">
										<button id="sptCatBtn" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span>依評分</span><span class="caret"></span>
										</button>
								        <ul id="sptCatList" class="dropdown-menu">
								          <li><a href="##">依評分</a></li>
								          <li><a href="##">依地區</a></li>
								          <li><a href="##">依類型</a></li>
								        </ul>
									</span>
										<!-- spotRate
									        	4321
									        spotLoc
												northen
												central
												southen
												easten
									        spotType
									        	hotel
									        	restaurant
									        	attraction -->
									<span class="input-group-btn">
										<button id="sptCatOptsBtn" name="spotRate" value="4" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span>4<i class="fa fa-star" aria-hidden="true"></i>以上</span><span class="caret"></span>
										</button>
								        <ul id="sptCatOptsList" class="dropdown-menu">
								          <li value="4"><a href="##">4<i class="fa fa-star" aria-hidden="true"></i>以上</a></li>
								          <li value="3"><a href="##">3<i class="fa fa-star" aria-hidden="true"></i>以上</a></li>
								          <li value="2"><a href="##">2<i class="fa fa-star" aria-hidden="true"></i>以上</a></li>
								          <li value="1"><a href="##">1<i class="fa fa-star" aria-hidden="true"></i>以上</a></li>
								        </ul>
									</span>
									<input id="SpotKeyword" name='keyword' type="text" class="form-control" placeholder="請輸入關鍵字找店名">
									<input type="hidden" name="action" value="spotQuery">
									<span class="input-group-btn">
										<button id='spotQueryBtn' type='button' class="btn btn-info" type="button"><i class="fa fa-search" aria-hidden="true"></i></button>						
									</span>
								</div>
							</div>
						</form>
						<div class="thumbnail" style="background-color: #b4c8da">
							<ul class="pager caption">
							  	<select id="tripDateSelector" style="padding: 3px;border-radius: 8px; border-color: #ddd">
								  <option value="0">加到第1天</option>
								  <option value="1">加到第2天</option>
								</select>
							  <li><a id="spotPrev" href="##" class="prev glyphicon glyphicon-chevron-left"></a></li>
							  	<select id="spotPageSelector" style="padding: 3px;border-radius: 8px; border-color: #ddd">
								  <option value="0">第1頁</option>
								  <option value="1">第2頁</option>
								</select>
							  <li><a id="spotNext" href="##" class="next glyphicon glyphicon-chevron-right"></a></li>
							</ul>
						</div>
						<div class="fixRatio fixRatio70">
							<div id='spotList' class="list-group scrollFine"> 
								<!-- <li id="10" class="list-group-item-success list-group-item clearfix active draggable">
									<span class="badge badge-cust pull-left"><span>4</span><i class="fa fa-star" aria-hidden="true"></i></span>
									<span class="badge badge-cust pull-left"><span>10</span><i class="fa fa-comment" aria-hidden="true"></i></span> 城東大學
									<button class="btn btn-info pull-right " type="button">加入</button>
								</li>
								<li id="11" class="list-group-item-success list-group-item clearfix draggable">
									<span class="badge badge-cust pull-left">10</span> 南台灣
									<button class="btn btn-info pull-right " type="button">加入</button>
								</li>
								<li id="12" class="list-group-item-success list-group-item clearfix draggable">
									<span class="badge badge-cust pull-left">10</span> 天龍國
									<button class="btn btn-info pull-right " type="button">加入</button>
								</li> -->
							</div>
						</div>
				      </div>
				    </div>
				  </div>
				  <!-- 區塊2 -->
				  <div class="panel panel-success">
				    <div class="panel-heading" role="tab" id="panel2">
				      <h4 class="panel-title">
				        <a href="#trips" data-parent="#spots_n_trips" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="trips">
				          <!-- <div class="page-header"> -->
							<!-- <h3> -->
								行程列表
							<!-- </h3> -->
						  <!-- </div> -->
				        </a>
				      </h4>
				    </div>
				    <div id="trips" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
				      <div class="panel-body">
				        <form>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-btn">
										<button id="tripCatBtn" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span>依評分</span><span class="caret"></span>
										</button>
								        <ul id="tripCatList" class="dropdown-menu">
								          <li><a href="#">依評分</a></li>
								          <li><a href="#">依類型</a></li>
								        </ul>						
									</span>
									<!-- 	tripRate
									        	4321
									        tripType
												northen
												central
												southen
												easten
									        tripDays
									        	1
									        	2
									        	3 -->
									<span class="input-group-btn">
										<button id="tripCatOptsBtn" name="tripRate" value="4" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span>4<i class="fa fa-star" aria-hidden="true"></i>以上</span><span class="caret"></span>
										</button>
								        <ul id="tripCatOptsList" class="dropdown-menu">
								          <li value="4"><a href="#">4<i class="fa fa-star" aria-hidden="true"></i>以上</a></li>
								          <li value="3"><a href="#">3<i class="fa fa-star" aria-hidden="true"></i>以上</a></li>
								          <li value="2"><a href="#">2<i class="fa fa-star" aria-hidden="true"></i>以上</a></li>
								          <li value="1"><a href="#">1<i class="fa fa-star" aria-hidden="true"></i>以上</a></li>
								        </ul>						
									</span>
									<input type="text" class="form-control" placeholder="請輸入關鍵字">
									<span class="input-group-btn">
										<button class="btn btn-info" type="button">
											<i class="fa fa-search" aria-hidden="true"></i>
										</button>
									</span>
								</div>
							</div>
						</form>
						<div class="pager">
							<!-- <h4 class="pull-left">
								<span class="text-info">地點留言</span><small class="pull-left"> 1 / 3 頁</small>
							</h4> -->
							<!-- <small class="pull-left"> 1 / 3 頁</small> -->
							<ul class="pagination pagination-sm">
								<li><a href="#" class="glyphicon glyphicon-chevron-left"></a></li>
								<li class="active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#" class="glyphicon glyphicon-chevron-right"></a></li>
							</ul>
						</div>
						<div class="thumbnail" style="background-color: #b4c8da">
							<ul class="pager caption">
							  <li><a id="groupMemPrev" href="##" class="prev glyphicon glyphicon-chevron-left"></a></li>
							  <select class="selectpicker"  data-width="80px" title="頁數">
							  	<option>第100頁</option>
							  	<option>第20頁</option>
							  </select>
							  <li><a id="groupMemNext" href="##" class="next glyphicon glyphicon-chevron-right"></a></li>
							</ul>
						</div>
						<div class="fixRatio fixRatio70">
							<div class="list-group scrollFine">
								<li class="list-group-item-success list-group-item clearfix active">
									<form>
										<div class="form-group">
											<span class="badge badge-cust pull-left"><span>4</span><i class="fa fa-star" aria-hidden="true"></i></span><span class="badge badge-cust pull-left"><span>10</span><i class="fa fa-comment" aria-hidden="true"></i></span></span> 陽明山一日遊
											<button class="btn btn-info pull-right " type="button">匯入行程</button>
										</div>
									</form>
								</li>
								<li class="list-group-item-success list-group-item clearfix">
									<form>
										<div class="form-group">
											<span class="badge badge-cust pull-left"><span>4</span><i class="fa fa-star" aria-hidden="true"></i></span><span class="badge badge-cust pull-left"><span>10</span><i class="fa fa-comment" aria-hidden="true"></i></span></span> 宜蘭羅東三日遊
											<button class="btn btn-info pull-right " type="button">匯入行程</button>
										</div>
									</form>
								</li>
								<li class="list-group-item-success list-group-item clearfix">
									<form>
										<div class="form-group">
											<span class="badge badge-cust pull-left"><span>4</span><i class="fa fa-star" aria-hidden="true"></i></span><span class="badge badge-cust pull-left"><span>10</span><i class="fa fa-comment" aria-hidden="true"></i></span></span> 東部七日遊
											<button class="btn btn-info pull-right " type="button">匯入行程</button>
										</div>
									</form>
								</li>
							</div>
						</div>
				      </div>
				    </div>
				  </div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<div class="page-header">
					<h3>
						行程路線 / 地點詳情<small></small>
					</h3>
				</div>
				<div id="carousel-spotMap" class="carousel slide" data-interval="false">
					<!-- 幻燈片小圓點區 -->
					<ol class="carousel-indicators outside">
						<li data-target="#carousel-spotMap" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-spotMap" data-slide-to="1" class=""></li>
					</ol>
					<!-- 幻燈片主圖區 -->
					<div class="carousel-inner">
						<div class="item active">
							<div class="spotSummaryBtn">
								<a id="spotSummaryBtn" class="btn btn-lg btn-info" href="##" role="button">行程摘要</a>
							</div>
							<div id="tripMap" class="carousel-item fixRatio fixRatio80"></div>
								<div id="spotSummaryFrame" class="carousel-caption outside" style="display: none;">
									<div class="text-justify  fixRatio fixRatio80">
					                	<div id="spotSummaryCnt" class="col-xs-10 col-sm-10 col-xs-offset-1 col-sm-offset-1 scrollFine">
					                		<p>行程摘要如下：<br>
					                		1>去海邊釣魚<br>
					                		2>去山上打野味<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		3>去百貨公司掃貨<br>
					                		…
					                		</p>
				                		</div>
				                	</div>
								</div>
							</div>
						<div class="item">
							<div id="" class="carousel-item fixRatio fixRatio80">
								<div class="thumbnail scrollFine">
									<div class="caption clearfix">
										<div class="siema fixRatio fixRatio50" style="text-align:center; margin-bottom: 15px;">
									        <img src="<%=request.getContextPath()%>/img/667463.jpg" alt="Siema image" />
									        <img src="https://pawelgrzybek.com/siema/assets/siema--pink.svg" alt="Siema image" />
									        <img src="https://pawelgrzybek.com/siema/assets/siema--yellow.svg" alt="Siema image" />
								    	</div>
						    			<ul class="pager">
										  <li><a id="siemaPrev" href="##" class="prev glyphicon glyphicon-chevron-left"></a></li>
										  <li><a id="siemaNext" href="##" class="next glyphicon glyphicon-chevron-right"></a></li>
										</ul>
									</div>
									
									
								    <style class="cp-pen-styles">
/* 									    body { */
/* 									        width: 100%; */
/* 									        max-width: 40rem; */
/* 									        margin: 0 auto; */
/* 									    } */
									    
									    img {
									        width: 80%;
									    }
									
/* 									    .siema { */
/* 									        margin: 0 1rem ; */
/* 									    } */
								    </style>
									
									<div class="caption clearfix">
										<h4 class="pull-left">
											<span class="text-info">城東大學</span>
										</h4>
										<h4 class="pull-right">$24.99</h4>
									</div>
									<div id="spotDesc" class="caption clearfix">
										<div class="ratings clearfix">
											<p class="pull-right">15 reviews</p>
											<p>
												<i class="fa fa-star" aria-hidden="true"></i> <i
													class="fa fa-star" aria-hidden="true"></i> <i
													class="fa fa-star" aria-hidden="true"></i> <i
													class="fa fa-star-half-o" aria-hidden="true"></i> <i
													class="fa fa-star-o" aria-hidden="true"></i>
											</p>
										</div>
										<p class="text-justify">仔細下來當然當你處於新鮮海南什，房地產規劃標題全市終於另一個高級物業數量科研二級非法緊張，美容英文對於不同次數定位試試隨着地面那時機器，價值也在液晶記錄研究所露出早已才能這裡大家都留。</p>
										<div class="btn-toolbar" role="toolbar">
											<button class="btn btn-info pull-right " type="button">加入</button>
											<button href='#modal-id-cmnt' data-toggle="modal" class="btn btn-info pull-right " type="button">留言</button>
										</div>
									</div>
									<div class="caption">
										<div class="pager clearfix">
										<h4 class="pull-left">
											<span class="text-info">地點留言</span><small> 1 / 3 頁</small>
										</h4>
										<ul class="pagination pagination-sm pull-right">
											<li><a href="#" class="glyphicon glyphicon-chevron-left"></a></li>
											<li class="active"><a href="#">1</a></li>
											<li><a href="#">2</a></li>
											<li><a href="#">3</a></li>
											<li><a href="#" class="glyphicon glyphicon-chevron-right"></a></li>
										</ul>
										</div>
										<div class="panel-group" id="cmntList" role="tablist"
											aria-multiselectable="true">
											<!-- 區塊1 -->
											<div class="panel panel-success">
												<div class="panel-heading" role="tab" id="panel1">
													<h4 class="panel-title">
														<a href="#cmnt1" data-parent="#cmntList"
															data-toggle="collapse" role="button" class="collapsed" aria-expanded="false"
															aria-controls="cmnt1"> 這裡常下雨，冬天又很冷…
															<div class="ratings pull-right">
																<p>
																	<i class="fa fa-star" aria-hidden="true"></i> <i
																		class="fa fa-star" aria-hidden="true"></i> <i
																		class="fa fa-star" aria-hidden="true"></i> <i
																		class="fa fa-star-half-o" aria-hidden="true"></i> <i
																		class="fa fa-star-o" aria-hidden="true"></i>
																</p>
															</div>
														</a>
													</h4>
												</div>
												<div id="cmnt1" class="panel-collapse collapse"
													role="tabpanel" aria-labelledby="panel1">
													<form>
													<div class="panel-body">
														<p>
															服飾責任編輯告訴你微軟改造證據，上去規定我把導航宣佈，不停廣播透明湖北更大雲南並，已經好友雙方難以魅力手冊不，傢俱病人心情國語告訴你肯定得知皮膚強調這段某種石油從來，美容地方大哥提示也就指出大幅授權方式政治東北申請登記，帖子長沙情人書記備案計算機失去友情困難不出不。
														</p>
														<div class="form-group clearfix">
															<a href='#modal-id-rprt' data-toggle="modal" class="btn btn-info pull-right">檢舉留言</a>
														</div>
														</div>
													</form>
												</div>
											</div>
											<!-- 區塊2 -->
											<div class="panel panel-success">
												<div class="panel-heading" role="tab" id="panel2">
													<h4 class="panel-title">
														<a href="#cmnt2" data-parent="#cmntList"
															data-toggle="collapse" role="button" class="collapsed"
															aria-expanded="false" aria-controls="cmnt2">
															城東大學果然不愧是偶像劇熱門景點！
															<div class="ratings pull-right">
																<p>
																	<i class="fa fa-star" aria-hidden="true"></i> <i
																		class="fa fa-star" aria-hidden="true"></i> <i
																		class="fa fa-star" aria-hidden="true"></i> <i
																		class="fa fa-star-half-o" aria-hidden="true"></i> <i
																		class="fa fa-star-o" aria-hidden="true"></i>
																</p>
															</div>
														</a>
													</h4>
												</div>
												<div id="cmnt2" class="panel-collapse collapse"
													role="tabpanel" aria-labelledby="panel2">
													<div class="panel-body">
														<p>
														情況轉變本網站主演補充無奈精美日韓忘記加速第，委託許多讓我們西方另外小組方向官員硬件國有收益動漫附近還，因為邀請傳來文本一本一路，明年總數可見感染緊急臨時不願，機器插件所需她們體系狀況記得見到他們的求購口氣，再次自然是他四川精英想想明星窗口指標員，回應校園長沙發佈日期夫人這時房間財政一絲面積一部分，新技術風情他不來源音樂有。
														</p>
														<div class="form-group clearfix">
															<a href='#modal-id-rprt' data-toggle="modal" class="btn btn-info pull-right">檢舉留言</a>
														</div>
													</div>
												</div>
											</div>
											<!-- 區塊3 -->
											<div class="panel panel-success">
												<div class="panel-heading" role="tab" id="panel3">
													<h4 class="panel-title">
														<a href="#cmnt3" data-parent="#cmntList"
															data-toggle="collapse" role="button" class="collapsed"
															aria-expanded="false" aria-controls="cmnt3">
															正妹很多喔（疑？）
															<div class="ratings pull-right">
																<p>
																	<i class="fa fa-star" aria-hidden="true"></i> <i
																		class="fa fa-star" aria-hidden="true"></i> <i
																		class="fa fa-star" aria-hidden="true"></i> <i
																		class="fa fa-star-half-o" aria-hidden="true"></i> <i
																		class="fa fa-star-o" aria-hidden="true"></i>
																</p>
															</div>
														</a>
													</h4>
												</div>
												<div id="cmnt3" class="panel-collapse collapse"
													role="tabpanel" aria-labelledby="panel3">
													<div class="panel-body">
														<p>
														從來沒想起夫人感動不是協調中國氣氛讀，確實失望每次身子甚至字體唱片展示詳細信，倒是更新時間一個月攻擊處罰各項網通信。
														</p>
														<div class="form-group clearfix">
															<a href='#modal-id-rprt' data-toggle="modal" class="btn btn-info pull-right">檢舉留言</a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
							<!-- <div class="container">
								<div class="carousel-caption outside">
									<h1>CSS可樂的外掛真方便</h1>
									<p>你安裝了嗎？</p>
									<p>
										<a class="btn btn-lg btn-primary" href="#" role="button">更多</a>
									</p>
								</div>
							</div> -->
						</div>
					</div>
					<!-- 上下頁控制區 -->
					<a class="carousel-control outside left" href="#carousel-spotMap"
						data-slide="prev"><span
						class="glyphicon glyphicon-chevron-left"></span></a> <a
						class="carousel-control outside right" href="#carousel-spotMap"
						data-slide="next"><span
						class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
				<div class="modal fade" id="modal-id-cmnt">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title">留言</h4>
							</div>
							<div class="modal-body">
								人間仙境啊!!!!!!
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
								<button type="button" class="btn btn-primary">Save changes</button>
							</div>
						</div>
					</div>
				</div>
				<div class="modal fade" id="modal-id-rprt">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title">檢舉留言</h4>
							</div>
							<div class="modal-body">
								你要檢舉他嗎？為什麼？他人這麼好？
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
								<button type="button" class="btn btn-primary">Save changes</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-3">
				<div class="page-header">
					<h3>
						目前規劃行程<small></small>
					</h3>
				</div>
				<form>
					<div class="form-group">

					<div id="" class="panel-group" role="tablist" aria-multiselectable="true">
						<div id="tripOptFrame" class="panel panel-success"> <!-- dayFP fixed parent of day#  -->
							<div class="panel-heading clearfix" role="tab">
								<h4 class="panel-title pull-left" title="點擊以展開">
									<a href="#tripOpts" data-parent="#"
										data-toggle="collapse" role="button" aria-expanded="true"
										aria-controls="spots4arrange">行程規劃選項</a>
								</h4>
							</div>
							<div id="tripOpts" class="panel-collapse collapse in" role="tabpanel"
								aria-labelledby="panel1">
								<div class="panel-body">
									<div class="input-group">
										<span class="input-group-addon">
											<input id="remindAccom" type="checkbox" aria-label="...">
										</span>
										<span class="input-group-addon" style="text-align: justify;">
											提醒加住宿地點
										</span>
									</div>
									<div class="input-group">
										<span class="input-group-addon">
											<input id="roundTour" type="checkbox" aria-label="...">
										</span>
										<span class="input-group-addon" style="text-align: justify;" title="起點為第1天的第1個地點">
											起/終點為同一點
										</span>
									</div>
									<div class="input-group">
										<span class="input-group-addon">
											出發日期
										</span>
										<input class="form-control" type="date">
									</div>
									<div class="input-group">
										<div id="spot-btn-toolbar" class="btn-toolbar" role="toolbar">
											<div class="btn-group">
											  <button id="whichDayBtn" value="a" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span id="dayChoiced">全部</span><span class="caret"></span>
											  </button>
											  <ul id="whichDayList" class="dropdown-menu">
											    <li id='0'><a href="##">第1天</a></li>
											    <li id='u'><a href="##">待規劃</a></li>
											    <li id='a'><a href="##">全部</a></li>
											  </ul>
											  <button id="reviewSpotBtn" type="button" class="btn btn-info">預覽地點</button>
											</div>
											<div class="btn-group" role="group" aria-label="...">
												<button id="reviewTripBtn" type="button" class="btn btn-info">預覽行程</button>
											</div>
											<div class="btn-group" role="group" aria-label="...">
												 <button id="planTripBtn" class="btn btn-info" type="button">自動規劃</button>
											</div>
											<div class="btn-group" role="group" aria-label="...">
												 <button id="storeTripBtn" type="button" class="btn btn-info">儲存</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- day固定區塊 -->
						<div id="dayFP" class="panel panel-success"> <!-- dayFP fixed parent of day#  -->
							<div class="panel-heading clearfix" role="tab">
								<h4 class="panel-title pull-left" title="點擊以展開">
									<a href="#spots4arrange" data-parent="#"
										data-toggle="collapse" role="button" aria-expanded="true"
										aria-controls="spots4arrange">待規劃地點</a>
								</h4>
								<span id="addDayBtn" class="pull-right" title="新增一天行程"><i class="fa fa-plus-square" aria-hidden="true"></i></span>
							</div>
							<div id="spots4arrange" class="panel-collapse collapse in" role="tabpanel"
								aria-labelledby="panel1">
								<div class="panel-body">
									<div id="sortableDayu" class="list-group connectedSortable"> 
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="mSortableDays" class="panel-group" role="tablist"
						aria-multiselectable="true">
						<!-- day區塊1 -->
						<div id="dayP1" class="panel panel-success">
							<div class="panel-heading clearfix" role="tab">
								<h4 class="panel-title pull-left" title="點擊以展開">
									<a href="#day1" data-parent="#"
										data-toggle="collapse" role="button" class="collapsed" aria-expanded="false"
										aria-controls="day1">第<span>1</span>天行程</a>
								</h4>
								<span id="delDayBtn" class="pull-right" title="刪除本日行程"><i class="fa fa-minus-square" aria-hidden="true"></i></span>
							</div>
							<div id="day1" class="panel-collapse collapse" role="tabpanel"
								aria-labelledby="panel1">
								<div class="panel-body">
									<div id="sortableDay1" class="list-group connectedSortable"> 
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 我會在footer tag放內容，勿動 -->
	<div id="footer" class=""></div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!--   	<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script> -->
<!-- 	<script src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script> -->
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script> -->
	<script async defer	src="http://maps.google.com/maps/api/js?key=AIzaSyAXeHxxFsqgWK46lpAaX7rJW3igePi41WM"></script>
	<script src="<%=request.getContextPath()%>/js/header_and_footer/hf.jsp"></script>
	<script> //google map 相關
	var $g = {
		"infoWindow": {},
		"mMInfo":{ //myMarker Information
			'id' : 'myLoc',
			'sClass' : 'active',
			'rate' : '5',
			'reviews' : '∞',
			'overview' : '這裡是您現在所在的概略位置…',
			'title' : '您在這裡！',
			'name' : '您的位置',
			'type' : -1,
			'color' : 'white',
			'text': 'N',
			'fontWeight' : 'bold',
			'photo' : 'https://pawelgrzybek.com/siema/assets/siema--pink.svg'
		},
		"spotMarkers": [],
		"map": {},
		"dirSrc": {},
		"dirDisp": {},
		"markerReuseOBuild": function(sInfo, $spJson){
			var find = false;
			var id = sInfo.spotNo;
			for (var i = 0 ; i < $g.spotMarkers.length; i++) {//不用跳過myLoc，因為查看不到
				if($g.spotMarkers[i].id == id){//如果存在就拿來用
					find = true;
					return $g.spotMarkers[i].marker;
				} 
			}
			//如果不存在就建立一個新的						
			for (var i = 0; i < $spJson.length; i++) {
				if($spJson[i].spotNo == id){
					sInfo.spotRate = $spJson[i].spotRate;
					sInfo.spotName = $spJson[i].spotName;
					sInfo.spotLat = $spJson[i].spotLat;
					sInfo.spotLng = $spJson[i].spotLng;
					sInfo.spotOverview = $spJson[i].spotOverview;
					sInfo.spotPhoto = $spJson[i].spotPhoto;
					// var marker = $g.markerBuilder(sInfo);
					// $g.putMarkerArr(marker, sInfo, "");
					find = true;
					// return marker;
				}
			}
			if(!find){
				console.log('$spJson沒有該marker代表的地點');
				//如果沒有就用ajax去後端拿回一個來
				$.ajax({
					 async: false,
					 type:"GET",
					 url:"<%=request.getContextPath()%>/SpotsRetriver.do",
					 data:{
					 	action:'spotQueryByNo',
					 	spotNo: id
					 },
					 dataType:"text	",
					 cache: false,
					 success:function (spot){//從server拿到的spots資訊
					 	if("context沒有符合地點！" == spot){
					 		console.log("context沒有符合地點！");
					 	}else{
					 		var spotJ = JSON.parse(spot);
					 		sInfo.spotRate = spotJ[0].spotRate;
							sInfo.spotName = spotJ[0].spotName;
							sInfo.spotLat = spotJ[0].spotLat;
							sInfo.spotLng = spotJ[0].spotLng;
							sInfo.spotOverview = spotJ[0].spotOverview;
							sInfo.spotPhoto = spotJ[0].spotPhoto;
							find = true;
						 	console.log("spotPhoto noMatch is: "+ sInfo.spotPhoto);
					 	}
				     },
		             error:function(){alert("error")}
	             });
			}
			if(!find) return console.log("地點不在context中！！");
			var marker = $g.markerBuilder(sInfo);
			$g.putMarkerArr(marker, sInfo, "");
			return marker;
		},
		"markerBuilder": function(sInfo){
			var marker = new google.maps.Marker({
				position : {
					lat: parseFloat(sInfo.spotLat),
					lng: parseFloat(sInfo.spotLng)
				},
				map : $g.map,
				draggable : false,
				title : sInfo.spotName + '\n' + '（按滑鼠右鍵以關閉標記）'
			});
			return marker;
		},
		"putMarkerArr": function(marker, sInfo, id){
			var mkrCtnr = new Object(); //marker container
			if(sInfo == undefined || sInfo == ""){
				mkrCtnr.id = id;
			} else{
				mkrCtnr.id = sInfo.spotNo;
			}
			mkrCtnr.marker = marker;
			$g.spotMarkers.push(mkrCtnr);
		},
		"markerIdFinder": function(marker){
			var latLng = marker.getPosition();
			var lat = latLng.lat();
			var lng = latLng.lng();
			var glatLng;
			var glat;
			var glng;
			for (var i = 0; i < $g.spotMarkers.length; i++) {
				glatLng = $g.spotMarkers[i].marker.getPosition();
				glat = glatLng.lat();
				glng = glatLng.lng();
				if(glat == lat && glng == lng){
					return $g.spotMarkers[i].id;
				}
				
			}
			return 'myLoc'; //因為初始時在array內不會有marker, 所以這個marker一定是自己目前所在位置
		},
		"markerEventSetter": function(marker){
			//設定並秀出 infoWindow
			google.maps.event.addListener(marker, "click", function() {
				$g.InfoWindowSetter(marker);
				$g.infoWindow.open($g.map, marker);
	        });
	        //雙擊關閉marker
	        google.maps.event.addListener(marker, "rightclick", function() {
	            marker.setMap(null);
	        });
		},
		"keepOnlyMyloc" : function(){
			for (var i = 0 + 1; i < $g.spotMarkers.length; i++) {
				$g.spotMarkers[i].marker.setMap(null);
			};
		},
		"id2sInfo": function(id){ //id to spotInfo
			var find = false;
			var sInfo = new Object();
			sInfo.id = id; //給myloc使用
			for (var i = 0; i < $sp.spots.Json.length; i++) {
				if(id == $sp.spots.Json[i].spotNo){
					sInfo.spotRate = $sp.spots.Json[i].spotRate;
					sInfo.spotName = $sp.spots.Json[i].spotName;
					sInfo.spotOverview = $sp.spots.Json[i].spotOverview;
					sInfo.spotPhoto = $sp.spots.Json[i].spotPhoto;
					find = true;
					// alert("spotPhoto match is: "+ sInfo.spotPhoto);
				}
			}
			if(!find){
				console.log('$spJson沒有該marker代表的地點');
				//如果沒有就用ajax去後端拿回一個來
				$.ajax({
					 async: false,
					 type:"GET",
					 url:"<%=request.getContextPath()%>/SpotsRetriver.do",
					 data:{
					 	action:'spotQueryByNo',
					 	spotNo: id
					 },
					 dataType:"text	",
					 cache: false,
					 success:function (spot){//從server拿到的spots資訊
					 	if("context沒有符合地點！" == spot){
					 		console.log("context沒有符合地點！");
					 	}else{
					 		var spotJ = JSON.parse(spot);
					 		sInfo.spotRate = spotJ[0].spotRate;
							sInfo.spotName = spotJ[0].spotName;
							sInfo.spotLat = spotJ[0].spotLat;
							sInfo.spotLng = spotJ[0].spotLng;
							sInfo.spotOverview = spotJ[0].spotOverview;
							sInfo.spotPhoto = spotJ[0].spotPhoto;
							find = true;
						 	console.log("spotPhoto noMatch is: "+ sInfo.spotPhoto);
					 	}
				     },
		             error:function(){alert("error")}
	             });
			}
			return sInfo;
		},
		"infoWCtentSetter": function(sInfo){
			var contentString, photo, name, star, overview, id;
			if('myLoc' == sInfo.id){
				photo = $g.mMInfo.photo;
				name = $g.mMInfo.name;
				star = $g.starCalc($g.mMInfo.rate);
				overview = $g.mMInfo.overview;
				id = $g.mMInfo.id;
			} else{
				// alert('sInfo.spotPhoto infoWCtentSetter is:'+ sInfo.spotPhoto);
				photo = 'data:image/png;base64, ' + sInfo.spotPhoto;
				name = sInfo.spotName;
				star = $g.starCalc(sInfo.spotRate);
				overview = sInfo.spotOverview;
				id = sInfo.id;
			}
			contentString = 
		 	'<div class="thumbnail" style="background-color: #b4c8da;">'+
		 		'<div class="caption" style="text-align: center;">'+
		 			'<img src="' + photo +'" />'+
		 		'</div>'+
		 		'<div class="caption clearfix">'+
		 			'<h4 class="pull-left"><span class="text-info">'+ name +'</span></h4>'+
		 			'<h4 class="pull-right">'+ '$∞' +'</h4>'+
		 		'</div>'+
		 		'<div id="spotDesc" class="caption clearfix">'+
		 			'<div class="ratings clearfix">'+
		 				'<p class="pull-right">'+ '∞' +' reviews</p>'+
					 	'<p>'+
						 	star +
					 	'</p>'+
		 			'</div>'+
		 			'<p class="text-justify">'+
		 			overview +
		 			'</p>'+
		 			'<div id="addMkrBtn" class="btn-toolbar" role="toolbar">' +
		 			'<button id='+ id +' class="btn btn-info pull-right " type="button">加入</button>' +
		 			'</div>'+
		 		'</div>'+
		 	'</div>';
		 	return contentString;
		},
		"starCalc": function(spotRate){
			var str;
			var fullStars = Math.floor(spotRate);
			var halfStar = Math.round(spotRate) - fullStars;
			var emptyStar = 5 - Math.round	(spotRate);
			str = '<i class="fa fa-star" aria-hidden="true"></i>'.repeat(fullStars) +
				'<i class="fa fa-star-half-o" aria-hidden="true"></i>'.repeat(halfStar)+
				'<i class="fa fa-star-o" aria-hidden="true"></i>'.repeat(emptyStar);
			return str;
		},
		"InfoWindowSetter": function(marker){
			var id = $g.markerIdFinder(marker);
			var sInfo = $g.id2sInfo(id);
			var contentString = $g.infoWCtentSetter(sInfo);
			$g.infoWindow.setContent(contentString);
		},
		"getOFetchSpot": function($spJson, id){
			var find = false;
			var sInfo = new Object();
			sInfo.id = id; 
			var spot;
			if('myLoc' == id || $spJson.length == 0){
				alert('$spJson.length is 0');
				//如果 id = 'myloc' 另外加一個位置給它
				alert('id =' + id);
				spot = $sp.listSpotInit({
					'myLoc' : true
				});
				alert('spot exists? '+ spot);
				alert('spot id? '+ spot.attr('id'));
				return spot;
			};
			for (var i = 0; i < $spJson.length; i++) {
				alert('$spJson[i].spotNo is :'+ $spJson[i].spotNo + '<br>' +
					'id is:' + id);
				if($spJson[i].spotNo == id){
					spot = $sp.listSpotInit({
						'j' : $spJson,
						'i' : i
					});
					alert('id = found!!');
					alert('spot exists?'+ spot);
					alert('spot id?'+ spot.attr('id'));
					find = true;
					return spot;
				}
			}
			if(!find){
				//如果沒有就用ajax去後端拿回一個來
				alert('id = no found!! go to tomcat');
				$.ajax({
					 async: false,
					 type:"GET",
					 url:"<%=request.getContextPath()%>/SpotsRetriver.do",
					 data:{
					 	action:'spotQueryByNo',
					 	spotNo: id
					 },
					 dataType:"text	",
					 cache: false,
					 success:function (spot){//從server拿到的spots資訊
					 	if("context沒有符合地點！" == spot){
					 		console.log("context沒有符合地點！");
					 	}else{
					 		var spotJ = JSON.parse(spot);
					 		sInfo.spotRate = spotJ[0].spotRate;
					 		sInfo.spotReviews = spotJ[0].spotRate;
							sInfo.spotName = spotJ[0].spotName;
							sInfo.spotType = spotJ[0].spotType;
							sInfo.spotLat = spotJ[0].spotLat;
							sInfo.spotLng = spotJ[0].spotLng;
							find = true;
						 	console.log("spotPhoto noMatch is: "+ sInfo.spotPhoto);
					 	}
				     },
		             error:function(){alert("error")}
	             });	
			}
			spot = $sp.listSpotInit({
				'sInfo' : sInfo
			})
			alert('back from cat, spot exists?'+ spot);
			alert('spot id?'+ spot.attr('id'));
			return spot;
		},
		"infoWindowIniter": function(marker, map){
			var infoWindow = new google.maps.InfoWindow({map: map});

			var id = $g.markerIdFinder(marker);
			var sInfo = $g.id2sInfo(id);
			var contentString = $g.infoWCtentSetter(sInfo);
		 	infoWindow.setContent(contentString);
	        infoWindow.setOptions({
	        	maxWidth: 400
	        });
	        infoWindow.open(map, marker);
	        $g.infoWindow = infoWindow;
		},
		"initMap": function(){
			var dirSrc = new google.maps.DirectionsService;
			var dirDisp = new google.maps.DirectionsRenderer;
			var map = new google.maps.Map(document.getElementById('tripMap'),{
				zoom : 14,
				center : {lat: 0, lng: 0},
				mapTypeId : google.maps.MapTypeId.ROADMAP
			});		

			$g.dirSrc = dirSrc;
			$g.dirDisp = dirDisp;
			$g.map = map;

			dirDisp.setMap(map);

			if(navigator.geolocation){
					navigator.geolocation.getCurrentPosition(initSuccCB, initErrCB,{
						enableHighAccuracy:false,
						timeout:100000
					});
					
				}else{
					alert('Geolocation NO Support!');
			}

			function initSuccCB(pos){

				var myPos = {
		          lat: pos.coords.latitude,
		          lng: pos.coords.longitude
		        };
		        map.setCenter(myPos);

		        //建立客製化marker

				var myMarker = new google.maps.Marker({
					position : myPos,
					map : map,
					draggable : true,
					title : '你在這裡！',
					label : {
						color: 'white',
						text: 'N',
						fontWeight : 'bold'
					}
				});

		        // console.log('$g.spotMarkers[0].id is: '+ $g.spotMarkers[0].id);
		        // console.log('$g.spotMarkers.length is: '+ $g.spotMarkers.length);

		        $g.infoWindowIniter(myMarker, map);
		        //把marker放進array內使用
				$g.putMarkerArr(myMarker, "", 'myLoc');

				google.maps.event.addListener(myMarker, "click", function() {
	       			$g.InfoWindowSetter(myMarker);        
		            $g.infoWindow.open(map, myMarker);
		        });
		        //位置改變時更新mylocInDay的位置
		        google.maps.event.addListener(myMarker, "dragend", function() {
		        	var LatLng = myMarker.getPosition();
		            $d.spotsInDay('').each(function(e){
		            	if('myLoc' == $(this).attr('id')){
		            		$(this).children('#spotLat').val(LatLng.lat());
		            		$(this).children('#spotLng').val(LatLng.lng());
		            	}
		            })
		        });
			}
			
			function initErrCB(e){
				alert('Code: '+e.code+'\nMessage: '+e.message);
			}
		}
	}
	</script>
	<script> //sp means spot
	var $sp = {	
		"spots" : {
			"Json" : "",
			"list" : {
				"rows": 10,
				"calcCPI": function(crntPageIndex, maxPageIndex){ //CPI current page index
					$sp.spots.list.crntPageIndex = ( crntPageIndex + (maxPageIndex + 1) ) % (maxPageIndex + 1);
					return $sp.spots.list.crntPageIndex;
				},
				"crntPageIndex": 0,
				"maxPageIndex" : 0
			}
		},
		"groupMemPagesMaxIndex" : "0",
		"listSpotInit" : function(opts){
			// opts = {
			//	'myLoc' : true,
			// 	'j' : $spJson,
			// 	'i' : i,
			// 	'sInfo' : sInfo
			// }
			var spot;
			var i = opts.i;
			var id, sClass, rate, reviews, name, type,glatLng , lat, lng;

			if(opts.myLoc){
				id = $g.mMInfo.id;
				sClass = ' active'; 
				rate = $g.mMInfo.rate; 
				reviews = '∞'; 
				name = $g.mMInfo.name; 
				type = $g.mMInfo.type; 
				glatLng = $g.spotMarkers[0].marker.getPosition();
				lat = glatLng.lat(); 
				lng = glatLng.lng();
			} else if(opts.j){
				id = opts.j[i].spotNo;
				sClass = ''; 
				rate = opts.j[i].spotRate; 
				reviews = opts.j[i].spotRate; 
				name = opts.j[i].spotName; 
				type = opts.j[i].spotType; 
				glatLng = '';
				lat = opts.j[i].spotLat; 
				lng = opts.j[i].spotLng;
			} else if(opts.sInfo){
				id = opts.sInfo.id;
				sClass = ''; 
				rate = opts.sInfo.spotRate; 
				reviews = opts.sInfo.spotReviews; 
				name = opts.sInfo.spotName; 
				type = opts.sInfo.spotType; 
				glatLng = '';
				lat = opts.sInfo.spotLat; 
				lng = opts.sInfo.spotLng;
			}
			spot = $('<li id=' + id + ' class="list-group-item-success list-group-item clearfix draggable ui-draggable ui-draggable-handle'+ sClass +'">'+
			'<span class="badge badge-cust pull-left"><span id="spotRate">'+ rate +
			'</span><i class="fa fa-star" aria-hidden="true"></i></span>'+
			'<span class="badge badge-cust pull-left"><span id="spotReviews">'+ reviews +
			'</span><i class="fa fa-comment" aria-hidden="true"></i></span>'+
			'<span id="spotName">'+ name + '</span>'+
			'<input id="spotType" type="hidden" name="spotType" value="'+ type +'">'+
			'<input id="spotLat" type="hidden" name="spotLat" value="'+ lat +'">'+
			'<input id="spotLng" type="hidden" name="spotLng" value="'+ lng +'">'+
			'<div id="spot-btn-toolbar" class="btn-toolbar" role="toolbar">'+
				'<button id="checkBtn" class="spot btn btn-info pull-right " type="button">查看</button>'+
				'<button id="addBtn" class="spot btn btn-info pull-right " type="button">加入</button>'+					
			'</div>'+
			'</li>');
			return spot;
		},
		"putJson2litems" : function($spJson, rows, crntPageIndex, maxPageIndex){
			newPageIndex = $sp.spots.list.calcCPI(crntPageIndex, maxPageIndex);

			var startIndex = newPageIndex*rows;
			var lastRowIndex = startIndex + calcRows($spJson, rows, newPageIndex, maxPageIndex) - 1;

			$('#spotList').empty();
			for (var i = startIndex; i < lastRowIndex + 1; i++) {
    			$sp.listSpotInit({
					'j' : $spJson,
					'i' : i
				}).appendTo('#spotList');
    		}

			// 每次產生的時候一定要綁定事件，不然會無法drag
    		$( ".draggable" ).draggable({
		      start: function( e, ui ) {
		    	ui.helper.attr("id", e.currentTarget.id).css({
			    		'z-index' : 100,
			    		'width' : e.currentTarget.width,
			    		'height': e.currentTarget.height
			    		}).appendTo($(this).parent().parent());
		      },
		      drag: function( e, ui ) {
		      },
		      stop: function(e, ui) {
				var spotType = ui.helper.children('#spotType').val();
	            ui.helper.removeAttr( "style" ).attr('class', 'list-group-item-success list-group-item clearfix').append($sp.addSpotTime2Spend(spotType));
	            ui.helper.children('#spot-btn-toolbar').remove();
	       	  },
		      connectToSortable: "[id^='sortableDay']",
		      helper: "clone",
		      revert: "invalid"
		    });

    		//private func
    		function calcRows($spJson, rows, newPageIndex, maxPageIndex){
    			if(newPageIndex == maxPageIndex){
    				return	$spJson.length % rows;
    			}else{
    				return	rows;
    			}
    		}
    		return newPageIndex;
		},
		"addSpotTime2Spend" : function(spotType){
			var h;
			var m;
			switch(spotType) {
			    case '0':
			        h = 2; m = 30;
			        break;
			    case '1':
			        h = 1; m = 0;
			        break;
			    case '2':
			        h = 8; m = 0;
			        break;
			    default:
			        ;
			}
			return '<div class="input-group pull-right" style="border-collapse: inherit;">'+
						'<span class="input-group-addon">預計停留</span>'+
						'<input id="spotHour" class="form-control" type="number" value="'+ h +'" max=23 min=0 step=1>'+
						'<span class="input-group-addon">時</span>'+
						'<input id="spotMin" class="form-control" type="number" value="'+ m +'" max=59 min=0 step=30>'+
						'<span class="input-group-addon">分</span>'+
						'<span class="input-group-btn">'+
							'<button id="delBtn" class="btn btn-info" type="button">刪除</button>'+
							'<button id="checkBtn" class="spot btn btn-info" type="button">查看</button>'+
						'</span>'+	
					'</div>';
		},
		"setPageselector" : function(maxPageIndex, selector, dayOpage){
			$(selector).empty();
			for (var i = 0; i < maxPageIndex + 1; i++) {
				$(selector).append("<option value="+ i +">第"+ (i + 1) + dayOpage +"</option>");
			}
			$(selector).children('option:first-child').attr('selected', true);
		},
		"listsCreater" : function( selector ,rows){
			$(selector).empty();
    		for (var i = 0; i < rows; i++) {
    			$(selector).append('<li value=""><a href="##"></a></li>');
    		}
		}
	}
	</script>
	<script> //infoWindow 事件綁定
	$(document)
	.on('click', '#addMkrBtn', function(e){ //從infoWindow加spot
		var id = $(this).children('button').attr('id');
		var $spJson = $sp.spots.Json;
		var spot = $g.getOFetchSpot($spJson, id);
		var spotType = spot.children('#spotType').val();

		var spotAbout2Go = spot.clone().append($sp.addSpotTime2Spend(spotType));
		if(id == 'myLoc'){ //如果是myLoc就放在最前面
			spotAbout2Go.prependTo('#sortableDayu');
		} else { //如果不是，就放在最後面
			spotAbout2Go.appendTo('#sortableDayu');
		}
		spotAbout2Go.children('#spot-btn-toolbar').remove();
	});		
	</script>
	<script> //carousal 事件綁定
	$(document)
	.on('slid.bs.carousel', '#carousel-spotMap', function(e){ //從infoWindow加spot
		//map拉回中心點
		google.maps.event.trigger($g.map, 'resize');

		//infowindow重開
		var LatLng = $g.infoWindow.getPosition();
		var crntMkr; //current marker with infoWindow
		var mLatLng; //marker LatLng
		for (var i = 0; i < $g.spotMarkers.length; i++) {
			crntMkr = $g.spotMarkers[i].marker;
			mLatLng = crntMkr.getPosition();
			if(mLatLng.lat() == LatLng.lat() &&
				mLatLng.lng() == LatLng.lng()){
				$g.infoWindow.open($.map, crntMkr);
			}
		}
		//siema resize
		mySiema.resizeHandler();
	});
	</script>
	<script> //左側spotList 事件綁定
		$(document)
		.on('click', '#sptCatList li', function(e){
			switch($(this).index()) {
			    case 0:
			    	var topRate = 4;
			    	$('#sptCatBtn').children('span:first-child').html('依評分');			    	
			    	$("#sptCatOptsBtn").attr({
			    		'name' : 'spotRate',
			    		'value' : topRate
			    		}).children('span:first-child').html(topRate+'<i class="fa fa-star" aria-hidden="true"></i>以上');

		    		$sp.listsCreater('#sptCatOptsList', topRate);

			        $("#sptCatOptsList>li").each(function(i){
			        	$(this).attr({
			    		'name' : 'spotRate',
			    		'value' : (topRate - i)
			    		}).children('a').html(topRate - i + '<i class="fa fa-star" aria-hidden="true"></i>以上');
			        });
			        break;
			    case 1:
			    	var locs = 5;
			    	$('#sptCatBtn').children('span:first-child').html('依地區');
			    	$("#sptCatOptsBtn").attr({
			    		'name' : 'spotLoc',
			    		'value' : 0
			    		}).children('span:first-child').html('北部');

		    		$sp.listsCreater('#sptCatOptsList', locs);

			    	$("#sptCatOptsList>li").each(function(i){
			    		var locNames = ['北部','中部','南部','東部','外離島'];
			        	$(this).attr({
			    		'name' : 'spotLoc',
			    		'value' : i
			    		}).children('a').html(locNames[i]);
			        });
			        break;
		        case 2:
		        	var types = 3;
					$('#sptCatBtn').children('span:first-child').html('依類型');
					$("#sptCatOptsBtn").attr({
			    		'name' : 'spotType',
			    		'value' : 0
			    		}).children('span:first-child').html('景點');	

		    		$sp.listsCreater('#sptCatOptsList', types);

			        $("#sptCatOptsList>li").each(function(i){
			    		var typeNames = ['景點','餐廳','旅館'];
			        	$(this).attr({
			    		'name' : 'spotType',
			    		'value' : i
			    		}).children('a').html(typeNames[i]);
			        });
			        break;
			    default:
			    	return;
			}
		})
		.on('click', '#sptCatOptsList li', function(e){
			$('#sptCatOptsBtn').attr('value', $(this).attr('value')).children('span:first-child').html($(this).children('a').html());
		})
		.on('click', '#spotQueryBtn', function(e){
			$.ajax({
			type:"GET",
			url:"<%=request.getContextPath()%>/SpotsRetriver.do",
			data:{
				action:"spotQuery",
			 	spotRate:($('#sptCatOptsBtn').attr('name') == 'spotRate' ? $('#sptCatOptsBtn').val() : ''),
			 	spotLoc:($('#sptCatOptsBtn').attr('name') == 'spotLoc' ? $('#sptCatOptsBtn').val() : ''),
			 	spotType:($('#sptCatOptsBtn').attr('name') == 'spotType' ? $('#sptCatOptsBtn').val() : ''),
			 	keyword:$('#SpotKeyword').val()
			},
			dataType:"text	",
			cache: false,
			success:function (spots){//從server拿到的spots資訊
				if("context沒有符合地點！" == spots){
			 		$('#spotList').empty();
			 		$('#spotList').append(
			 			'<li class="list-group-item-success list-group-item clearfix active draggable ui-draggable ui-draggable-handle">'+
			 			"沒有符合地點！"+
	    				'</li>'
			 			);
				}else{
				 	$sp.spots.Json = JSON.parse(spots);
					// alert('$sp.spots.Json[0].spotName is:' + $sp.spots.Json[0].spotName);
				 	$sp.spots.list.maxPageIndex = Math.ceil($sp.spots.Json.length/$sp.spots.list.rows) - 1;
				 	$sp.putJson2litems($sp.spots.Json, $sp.spots.list.rows, $sp.spots.list.crntPageIndex , $sp.spots.list.maxPageIndex);
					console.log('$sp.spots.list.maxPageIndex is ' + $sp.spots.list.maxPageIndex);
				 	$sp.setPageselector($sp.spots.list.maxPageIndex, '#spotPageSelector', "頁");
					console.log($sp);
			 	}
		    },
	        error:function(){alert("error")}
	        });
		})
		.on('click', '#spotPrev', function(e){
			$('#spotPageSelector option[value='+ $sp.spots.list.crntPageIndex +']').attr('selected', false);
			$sp.putJson2litems($sp.spots.Json, $sp.spots.list.rows, $sp.spots.list.crntPageIndex -= 1 , $sp.spots.list.maxPageIndex);
			$('#spotPageSelector option[value='+ $sp.spots.list.crntPageIndex +']').attr('selected', true);
		})
		.on('click', '#spotNext', function(e){
			$('#spotPageSelector option[value='+ $sp.spots.list.crntPageIndex +']').attr('selected', false);
			$sp.putJson2litems($sp.spots.Json, $sp.spots.list.rows, $sp.spots.list.crntPageIndex += 1 , $sp.spots.list.maxPageIndex);
			$('#spotPageSelector option[value='+ $sp.spots.list.crntPageIndex +']').attr('selected', true);
		});
	</script>
	<script> //右側trip規劃功能 事件綁定
		
	</script>
	<script> // d means day
	var $d = {
		"dayArr" : [],
		"spotsInDay" : function(whichdayIndex){ // whichdayIndex放''表示全找
			if('a' == whichdayIndex) whichdayIndex = '';
			return $('[id^="sortableDay'+ whichdayIndex +'"] li');
		},
		"dayIndex" : function(dayP){ 
			return dayP.index('#mSortableDays>div[id^="dayP"]');
		},
		"days" : function(){
			return $('#mSortableDays').children().length;
		}, // 在dayList裡面有多少day
		"addDay" : function(){
			$('#mSortableDays').append(function(){
				var id = $d.days() + 1;
				var day = 
					'<div id="dayP'+ id +'" class="panel panel-success ui-sortable-handle">' + //dayP# parent of day#
						'<div class="panel-heading clearfix" role="tab">' +
							'<h4 class="panel-title pull-left" title="點擊以展開">' +
								'<a href="#day'+ id +'" data-parent="#" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="">第<span>'+ id +'</span>天行程</a>' +
							'</h4>' +
							'<span id="delDayBtn" class="pull-right" title="刪除本日行程"><i class="fa fa-minus-square" aria-hidden="true"></i></span>' +
						'</div>' +
						'<div id="day'+ id +'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="">' +
							'<div class="panel-body">' +
								'<div id="sortableDay'+ id +'" class="list-group connectedSortable ui-sortable">' +
								'</div>' +
							'</div>' +
						'</div>' +
					'</div>';
				return day;
			})
			//todo listener要重綁…
			$d.daySortBinder();
		},
		"daySortBinder" : function(){
			$( "[id^='sortableDay']" ).sortable({
				connectWith: ".connectedSortable"
			}).disableSelection();
		},
		"changeDayIndex" : function(){
			$('#mSortableDays>div').each(function(e){
				// console.log('dayIndex is'+ $d.dayIndex($(this)));
				var index = $d.dayIndex($(this));
				$(this).attr('id', 'dayP' + index)
				.find('h4>a[href^="#day"]').attr('href', '#day' + index)
				.find('span').html(index + 1);
				$(this)
				.children('[id^="day"]').attr('id', 'day' + index)
				.find('[id^="sortableDay"]').attr('id', 'sortableDay' + index);
			});
		},
		"updateWDL" : function(){ // WDL whichDayList
			$('#whichDayList').empty();
			for (var i = 0; i < $d.days(); i++) {
				$('#whichDayList').append($('<li id='+ i +'><a href="##">第'+ (i+1) +'天</a></li>'));
			}
			$('#whichDayList')
			.append($('<li id="u"><a href="##">待規劃</a></li>'))
			.append($('<li id="a"><a href="##">全部</a></li>'));
			$('#whichDayBtn').val('a').find('#dayChoiced').text('全部');
		}
	}
	</script>
	<script> //右側dayList 事件綁定
		$(document) //行程規劃按鍵
		.on('click', '#whichDayList li', function(e){		
			$('#whichDayBtn').val($(this).attr('id')).find('#dayChoiced').text($(this).text());
		})
		.on('click', '#reviewSpotBtn', function(e){
			$g.keepOnlyMyloc();
			var val = $('#whichDayBtn').val();
			console.log('$("#whichDayBtn").val() is:'+ val);
			if(val == 'a') val = ''; //a表all, 在 [id^="sortableDay'+ val +'"]中val應設為''
			var sInfo = new Object();
			var marker;
			$d.spotsInDay(val).each(function(e){
				console.log('$d.spotsInDay(val).attr("id") is' + $d.spotsInDay(val).attr('id'));
				sInfo.spotNo = $(this).attr('id');
				marker = $g.markerReuseOBuild(sInfo, $sp.spots.Json);
				marker.setMap($g.map);
			});
		})
		.on('click', '#reviewTripBtn', function(e){		
			//確認是否提醒加住宿地點
			var ra = $('#remindAccom').prop('checked');
			//確認起/終點為同一點			
			var rt = $('#roundTour').prop('checked');
			var SID; //spots in day
			//要 spotNo
			//要 spotName
			//要 spotType	判斷是否為旅館
			//要 spotLat
			//要 spotLng
			//要 spotHour
			//要 spotMin
			var startP; //起點
			var endP; //終點
			var waypts = []; //waypoints
			for (var i = 0; i < $d.days(); i++) {
				console.log('第'+ (i+1) +'天');
				SID = $d.spotsInDay(i);
				SID.each(function(j, e){
					console.log($(this).attr('id') + ' : ' + j);
					if(!startP){
						startP = new google.maps.LatLng({
							lat: parseFloat($(this).find('#spotLat').val()),
							lng: parseFloat($(this).find('#spotLng').val())
						});
						console.log('startP.lat() is: ' + startP.lat());
						console.log('startP.lng() is: ' + startP.lng());
						return; // 等於continue
					}
					waypts.push({
	                    location: new google.maps.LatLng({
							lat: parseFloat($(this).find('#spotLat').val()),
							lng: parseFloat($(this).find('#spotLng').val())
						}),
	                    stopover: true
                	});
				});
			}

			if(rt){
				endP = startP;
			}else{
				endP = waypts.pop().location;
			};

			$g.dirSrc.route({
	            origin: startP,
	            destination: endP,
	            waypoints: waypts,
	            optimizeWaypoints: false,
	            travelMode: 'DRIVING'
	        }, function(res, status) {
	            if (status === 'OK') {
	                $g.dirDisp.setDirections(res);

	                // console.log("res.routes is : " + JSON.stringify(res.routes[0].legs));

	                var route = res.routes[0];
	                var summaryPanel = '';
	                // For each route, display summary information.
	                for (var i = 0; i < route.legs.length; i++) {
	                    var routeSegment = i + 1;
	                    summaryPanel += '<b>Route Segment: ' + routeSegment +
	                        '</b><br>';
	                    // summaryPanel += route.legs[i].start_address + ' to ';
	                    // summaryPanel += route.legs[i].end_address + '<br>';
	                    // summaryPanel += route.legs[i].distance.text + '<br><br>';
	                    if (i == route.legs.length - 1 && i == 0) {
	                        summaryPanel += '起點緯經度：' + 'i位置' + i + '<br>' + route.legs[i].start_location + '<br>';
	                        summaryPanel += '終點緯經度：' + 'i位置' + i + '<br>' + route.legs[i].end_location + '<br>';
	                    } else if (i == route.legs.length - 1) {
	                        summaryPanel += '中點緯經度：' + 'i位置' + i + '<br>' + route.legs[i].start_location + '<br>';
	                        summaryPanel += '終點緯經度：' + 'i位置' + i + '<br>' + route.legs[i].end_location + '<br>';
	                    } else if (i == 0) {
	                        summaryPanel += '起點緯經度：' + 'i位置' + i + '<br>' + route.legs[i].start_location + '<br>';
	                    } else {
	                        summaryPanel += '中點緯經度：' + 'i位置' + i + '<br>' + route.legs[i].start_location + '<br>';
	                    }
	                        summaryPanel += '時間（秒）：' + 'i位置' + i + '<br>' + route.legs[i].duration.text + '<br>';
	                }
	                alert(summaryPanel);
	            } else {
	                alert('Directions request failed due to ' + status);
	            }
	        });

			function calc_n_DispRt(dirSrc, dirDisp){
				//第一層迴圈是個別的day計算
					//找到
			};


			calc_n_DispRt($g.dirSrc, $g.dirDisp);
		});

		$(document) //待規劃地點&每日行程
		.on('mousedown', '#addDayBtn', function(e){ //同時加mousedown, mouseup 會讓click 失效
			$(this).html('<i class="fa fa-plus-square-o" aria-hidden="true"></i>');
		})
		.on('mouseup', '#addDayBtn', function(e){
			$(this).html('<i class="fa fa-plus-square" aria-hidden="true"></i>');
			$d.addDay();
			$d.updateWDL();
		})
		.on('mousedown', '#delDayBtn', function(e){
			$(this).html('<i class="fa fa-minus-square-o" aria-hidden="true"></i>');
		})
		.on('mouseup', '#delDayBtn', function(e){
			$(this).html('<i class="fa fa-minus-square" aria-hidden="true"></i>');
			var del = confirm('您即將要刪除本日行程，其中所有的地點都將遺失！');
			if(del) $(this).parent().parent().remove();
			$d.updateWDL();
		});

		/*vv div onchange事件的替代方案*/
		// 重算每個day的順序並且改id值等…
		var observer = new MutationObserver(function(mutations) {
			var changed = false;
			mutations.forEach(function(mutation) {
			  // You can check the actual changes here
			});
			$d.changeDayIndex();
		});
		var target = document.getElementById('mSortableDays');
		observer.observe(target, {
			attributes: true,
			childList: true,
			characterData: true
		});
		/*^^ div onchange事件的替代方案*/
	</script>
	<script> //day本身 事件綁定
	    $( "ul, li" ).disableSelection();
   		$( "#mSortableDays" ).sortable({
	      revert: true
	    });
	    $d.daySortBinder();
		$("#spotSummaryBtn").click(function() {
		  $("#spotSummaryFrame").fadeToggle();
		});
  	</script>
	<script> //spot本身 事件綁定
		$(document)
		.on('click', '#checkBtn', function(e){
		
			var $spJson = $sp.spots.Json;
			var sInfo = new Object(); // spot info
			// var spot = $(this).parent().parent(); //在spotList中的spot
			var spot = $(this).parents('li'); //在spotList中的spot
			sInfo.spotNo = spot.attr('id');

			//取得marker
			var marker = $g.markerReuseOBuild(sInfo, $spJson);
			//開啟marker
			marker.setMap($g.map);

			$g.InfoWindowSetter(marker);
			$g.infoWindow.open($g.map, marker);
			$g.markerEventSetter(marker);

		})
		.on('click', '#addBtn', function(e){ //addBtn 與 drag 要做的事一樣（砍掉tool-bar）
			var spot = $(this).parent().parent();
			var spotType = spot.children('#spotType').val();
			spot.clone().attr('class', 'list-group-item-success list-group-item clearfix').append($sp.addSpotTime2Spend(spotType)).appendTo('#sortableDayu').children('#spot-btn-toolbar').remove();
		})
		.on('click', '#delBtn', function(e){
			$(this).parent().parent().parent().remove();
		});
	</script>
	<script> //頁面載入時執行
		$(window).ready($g.initMap());
	</script>
  	<script src='https://pawelgrzybek.com/siema/assets/siema.min.js'></script>
    <script> //carousal 地點部分, about to deprecated...
    'use strict';
    var mySiema = new Siema({
        selector: '.siema',
        perPage: 1,
        loop: true
    });
    $(document)
    .on('click', '#siemaPrev', function(e){
    	return mySiema.prev();
    })
    .on('click', '#siemaNext', function(e){
    	return mySiema.next();
    });
    </script>
  	<script> //行程列表 事件綁定, about to deprecated...
	$("#tripCatList li").click(function(e){
		switch($(this).index()) {
		    case 0:
		    	var topRate = 4;
		    	$('#tripCatBtn').children('span:first-child').html('依評分');			    	
		    	$("#tripCatOptsBtn").attr({
		    		'name' : 'tripRate',
		    		'value' : topRate
		    		}).children('span:first-child').html(topRate+'<i class="fa fa-star" aria-hidden="true"></i>以上');

	    		$sp.listsCreater('#tripCatOptsList', topRate);

		        $("#tripCatOptsList>li").each(function(i){
		        	$(this).attr({
		    		'name' : 'tripRate',
		    		'value' : (topRate - i)
		    		}).children('a').html(topRate - i + '<i class="fa fa-star" aria-hidden="true"></i>以上');
		        });
		        break;
		    case 1:
		    	var types = 2;
		    	$('#tripCatBtn').children('span:first-child').html('依類型');
		    	$("#tripCatOptsBtn").attr({
		    		'name' : 'tripType',
		    		'value' : 0
		    		}).children('span:first-child').html('私人行程');

	    		$sp.listsCreater('#tripCatOptsList', types);

		    	$("#tripCatOptsList>li").each(function(i){
		    		var typeNames = ['私人行程','套裝行程'];
		        	$(this).attr({
		    		'name' : 'tripType',
		    		'value' : i
		    		}).children('a').html(typeNames[i]);
		        });
		        break;
		    default:
		    	return;
		}
	});
	$(document).on('click', '#tripCatOptsList li', function(e){
		$('#tripCatOptsBtn').attr('value', $(this).attr('value')).children('span:first-child').html($(this).children('a').html());
	});
	</script>
</body>
</html>