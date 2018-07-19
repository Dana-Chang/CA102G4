<%@ page contentType="text/html; charset=utf8"%>
<%@ page import="com.spot.model.*"%>
<%@ page import="com.spotPhoto.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	SpotVO spotVO = (SpotVO) request.getAttribute("spotVO");
	SpotPhotoService spotPhotoSvc = new SpotPhotoService();
	List<SpotPhotoVO> spotPhotoList = spotPhotoSvc.getAll();
	pageContext.setAttribute("spotPhotoList", spotPhotoList);
%>
<jsp:useBean id="spotCmntSvc" class="com.spotCmnt.model.SpotCmntService" scope="page" />
<jsp:useBean id="memberSvc" class="com.member.model.MemberService" scope="page" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>View Search</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/header_and_footer/hf.jsp"></script>
<link href="<%=request.getContextPath()%>/css/header_and_footer/header.css" rel="stylesheet" >
<style type="text/css">

fieldset, label { margin: 0; padding: 0; }

/****** Style Star Rating Widget *****/

.rating { 
  border: none;
  float: left;
}

.rating > input { display: none; } 
.rating > label:before { 
  margin: 5px;
  font-size: 1.25em;
  font-family: FontAwesome;
  display: inline-block;
  content: "\f005";
}

.rating > .half:before { 
  content: "\f089";
  position: absolute;
}

.rating > label { 
  color: #ddd; 
 float: right; 
}

/***** CSS Magic to Highlight Stars on Hover *****/

.rating > input:checked ~ label, /* show gold star when clicked */
.rating:not(:checked) > label:hover, /* hover current star */
.rating:not(:checked) > label:hover ~ label { color: #FFD700;  } /* hover previous stars in list */

.rating > input:checked + label:hover, /* hover current star when changing rating */
.rating > input:checked ~ label:hover,
.rating > label:hover ~ input:checked ~ label, /* lighten current selection */
.rating > input:checked ~ label:hover ~ label { color: #FFED85;  } 

/***************** 顯示星星********************/

span.stars, span.stars span {
    display: block;
    background: url(stars.png) 0 -16px repeat-x;
    width: 80px;
    height: 16px;
}

span.stars span {
    background-position: 0 0;
}

/*相簿*/
div.gallery {
	margin: 5px;
	border: 1px solid #ccc;
	float: left;
	width: 180px;
}

div.gallery:hover {
	border: 1px solid #777;
}

div.gallery img {
	width: 100%;
	height: auto;
}

div.desc {
	padding: 15px;
	text-align: center;
}
#map {
        height: 100%;
      }
      
#contextMenu {
	position: absolute;
	display:none;
}
</style>
</head>
<body id="bootstrap-overrides">
	<div id="header" class="fh"></div>
	<div class="content-cap">參考header的高度</div>
	<div class="container-fluid content">
		<div class="row">
			<div class="col-xs-12 col-sm-2 aa">
			
				<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/frontend/query/frontSpot.do" style="margin-top:150px">
					<b>選擇類型:</b> <br>
					<input type="checkbox" name="spotType" value="0">景點
					<input type="checkbox" name="spotType" value="1">飯店
					<input type="checkbox" name="spotType" value="2">餐廳<br>
					<b>輸入想搜尋的東西:<br></b> <input type="text" name="spotAddr"><br>
					<input type="submit" value="送出"> 
					<input type="hidden" name="action" value="getSpotQuery">
					<input type="hidden" name="spotNo" value="${spotVO.spotNo}">
					<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
				</FORM>
				<div style=" height:360px; overflow:auto;">
				<table>
				<tr style="border:1px solid black">
					<th>景點名稱</th>
				</tr>
				<c:forEach var="spotVO" items="${listSpots_ByCompositeQuery}" >
					<tr class="queryList" style="border:1px solid black">
						<td>
							<div onclick="showMarker(${spotVO.spotLat},${spotVO.spotLng},'${spotVO.spotName}')">
							<b style="font-size:9px">${spotVO.spotName}</b><br><span style="font-size:9px">${spotVO.spotAddr}</span></div>
						</td>
					</tr>
				</c:forEach>
				</table>
				</div>
			</div>
			
			
			<div class="col-xs-12 col-sm-8 bb">
				<div class="header_container">
					<h1>
						<p>${spotVO.spotName}</p>
						<span style="float:right;font-size:8px"><a href="#">我要回報</a></span>
					</h1>
					
					
					<div class="spotRating">
						${spotVO.spotRate/2 }
						<span class="stars">${spotVO.spotRate/2 }</span>
						
<%-- 						<c:forEach begin="1" end="${spotVO.spotRate }"> --%>
<!-- 							<i class="fa fa-star"></i> -->
<%-- 						</c:forEach> --%>
						<br>
						<% int cmntCount=0; %>
						<c:forEach var="spotCmntVO" items="${spotCmntSvc.all}" varStatus="s">
							<c:if test="${spotVO.spotNo == spotCmntVO.spotNo }">
								<%++cmntCount;%>
							</c:if>
						</c:forEach>
						<a class="reviewLink" onclick="activaTab(tab3)">有<%=cmntCount %>則評論</a>
					</div>
				</div>
				<div class="container-fluid">
					<div class="row">
						<div role="tabpanel">
							<!-- 標籤面板：標籤區 -->
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active">
								<a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">景點簡介</a>
								</li>
								<li role="presentation">
								<a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">照片</a>
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
								<div role="tabpanel" class="tab-pane active" id="tab1">${spotVO.spotOverview}</div>
								<!-- 相簿 -->
								<div role="tabpanel" class="tab-pane" id="tab2">
									<div class="gallery" style="background-color:#eee;width:870px">
										<c:forEach var="spotPhotoVO" items="${spotPhotoList }">
											<c:if test="${spotVO.spotNo==spotPhotoVO.spotNo}">
												<div style="float:left;margin:10px">
													<div class="title">${spotPhotoVO.photoTitle }</div>
													<div style="width:180px">
														<img src="<%=request.getContextPath() %>/frontSpotPhotoReader.do?spotPhotoNo=${spotPhotoVO.spotPhotoNo }">
													</div>
												</div>
											</c:if>
										</c:forEach>
										<div style="clear:both"></div>
									</div>
									<div style="float:right">
										<button class="btn btn-primary" data-toggle="modal" data-target="#myModal"><img src="<%=request.getContextPath() %>/frontend/items/add.png" width=50px style="float:right"/></button>
									</div>
								</div>
							
							
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
														<c:forEach begin="1" end="${spotCmntVO.rate }"><i class="fa fa-star"></i></c:forEach>
													</div>
													<div class="row" style="background-color: #eee">
														<span>${spotCmntVO.cmnt }</span>
														<button type="button" style="float:right">檢舉留言</button>
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
			</div>
		</div>
		<div class="col-xs-12 col-sm-2 cc"></div>
	</div>
	<div class="container">
		<div class="modal fade" id="myModal">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <form action="<%=request.getContextPath() %>/frontend/query/frontSpotPhoto.do" enctype="multipart/form-data" role="form" method="post">
		                <div class="modal-header">
		                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		                    <h4 class="modal-title">Upload Photo</h4>
		                </div>
		                <div class="modal-body" id="photoInput">
		                    <input type="file" name="img" accept="image/*">
		                    <input type="text" name=" photoTitle" placeholder="請輸入照片的敘述">
		                    <input type="hidden" name="action" value="upload_photos">
		                    <input type="hidden" name="memId" value="1">
		                    <input type="hidden" name="spotNo" value="${spotVO.spotNo }">
		                </div>
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
		                    <button type="submit" class="btn btn-primary">Upload</button>
		                </div>
		            </form>
		        </div>
		    </div>
		</div>
	</div>
<!-- 	右鍵選單 -->
	<div id="contextMenu" class="dropdown clearfix">
	    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" style="display:block;position:static;margin-bottom:5px;">
			<li><a tabindex="-1" href="#">加入我的行程</a></li>
			<li><a tabindex="-1" href="#">查看詳細訊</a></li>
	    </ul>
	</div>
</body>
</html>

<script type="text/javascript">
	$(document).ready(function() {
		
		$(".attractions>a").click(function(e) {
			$(".attractions>a.active").removeClass("active");
			$(this).attr("class", "list-group-item active");
			e.preventDefault();
			// 避免跳回頂端
		});
		$(".plainList>a").click(function(e) {
			$(".plainList>a.active").removeClass("active");
			$(this).attr("class", "list-group-item active");
			e.preventDefault();
			// 避免跳回頂端
		});
		$(".placeAround>a").click(function(e) {
			$(".placeAround>a.active").removeClass("active");
			$(this).attr("class", "list-group-item active");
			e.preventDefault();
			// 避免跳回頂端
		});
		$(".content").css('padding-top', $(".navbar").height());
		$(".content").css('padding-bottom', $(".footer").height());

		$(".reviewLink").click(function(e) {
			$(".nav-tabs>li.active").removeClass("active");
			$(".nav-tabs>li").eq(2).addClass("active");
			$("#tab3").attr("class", "tab-pane active");
			e.preventDefault();
		});

		$(".navbar-nav>li").hover(function() {
			$(this).css("background-color", "#E74C3C");
		}, function() {
			$(this).css("background-color", "#2980B9");
		});
		$(".Login").click(function() {
			var h = $("body").height() + 'px';
			$("#black_overlay").css({
				"height" : h,
				"visibility" : "visible"
			});
			$(".added").css('display', 'block');
		});

		$(".close").click(function() {
			$(".added").css('display', 'none');
			$("#black_overlay").css("visibility", "hidden");
		});
		
// 		計算星星長度
		$('span.stars').stars();
		
	});

    var map;
    var myMarker=null;
    var marker=null;
    function initMap() {
	var latlng = new google.maps.LatLng( ${spotVO.spotLat},${spotVO.spotLng});    	
	map = new google.maps.Map(document.getElementById('map'), {
        center: latlng,
        zoom: 14
      });
	myMarker = new google.maps.Marker({
	    	  position:latlng,
	    	  map:map,
	    	  title:"you are here!",
	    	  icon:"../items/placeholder.png"  
	      });
	}
    //按了tab之後再init地圖一次
    $('a[href="#tab4"]').click(function(e) {
        setTimeout(initMap, 100);
    });
    
    function showMarker(lat,lng, name){
    	map.setCenter({
    		lat:lat,
    		lng:lng
    	});
    	if (marker!=null){
    		marker.setMap(null);
    	}
    	marker = new google.maps.Marker({animation: google.maps.Animation.DROP,position: {lat:lat,lng:lng},map:map,title:name})
    }
    
	$(function() {
    	  
		var $contextMenu = $("#contextMenu");
		  	  
		$("body").on("contextmenu", "table tr", function(e) {
			$contextMenu.css({
				display: "block",
				left: e.pageX,
				top: e.pageY
			});
			return false;
		});
  
		$contextMenu.on("click", "a", function() {
			$contextMenu.hide();
		});
		$('body').click(function () {
			$contextMenu.hide();
		});
	}); 
	function leaveCmnt(){
		$.ajax({
			type:"GET",
			url:"frontSpotCmnt.do",
			data:{"action":"insert","memId":"1","spotNo":${spotVO.spotNo},"cmnt":$('#cmnt').val(),"rate":$('.rating input:checked').val()==null?'0':$('.rating input:checked').val()},
			dataType:"json",
			success:function (data){
				console.log(data.memId);
				console.log('<img src="<%=request.getContextPath() %>/Member/memImg.do?memId=1" style="max-width: 100%;">');
				$("#commentList").append('<div class="row" style="background-color: #888">'+
						'<div class="col-xs-12 col-sm-3" style="background-color: #666;">'+
							'<table><tr>'+
							'<td rowspan="2" style="padding-top: 10px; padding-bottom:10px;width:80px">'+
							'<img src="<%=request.getContextPath() %>/Member/memImg.do?memId=1" style="max-width: 100%;">'+
							'</td><td>ID:'+data.memId+'</td></tr><tr><td>姓名:XXX</td></tr></table></div><div class="col-xs-12 col-sm-9">'+
							'<div class="row" style="background-color: #ccc">第xx則評論</div>'+
							'<div class="row" style="background-color: #ccc">'+
							'<i class="fa fa-star"></i>'.repeat(data.rate)+
							'</div><div class="row" style="background-color: #eee"><span>'+data.cmnt+
							'</span></div></div></div>');
			},
			error:function(){alert("error")}
		});
		
		$('#cmnt').val('');
	}
	$.fn.stars = function() {
	    return $(this).each(function() {
	        // Get the value
	        var val = parseFloat($(this).html());
	        // Make sure that the value is in 0 - 5 range, multiply to get width
	        var size = Math.max(0, (Math.min(5, val))) * 16;
	        // Create stars holder
	        var $span = $('<span />').width(size);
	        // Replace the numerical value with stars
	        $(this).html($span);
	    });
	}
    
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDHV7GxAc0IWzZQ1bTnAkWlrhI5gdGISsw&callback=initMap" async defer></script>