<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	Integer tripNo = new Integer(request.getParameter("tripNo"));
	TripService tripSvc = new TripService();
	TripVO tripVO = tripSvc.getOneTrip(tripNo);
	pageContext.setAttribute("tripVO", tripVO);
	Map<String,String> typeMap = new HashMap<String,String>();
	Map<String,String> isPublicMap = new HashMap<String,String>();
	typeMap.put("0", "一般行程");
	typeMap.put("1", "店家行程");
	typeMap.put("2", "平台優選");
	isPublicMap.put("0", "私人");
	isPublicMap.put("1", "公開");
	pageContext.setAttribute("typeMap", typeMap);
	pageContext.setAttribute("isPublicMap", isPublicMap);
%>
<!DOCTYPE html>
<html lang="">
	<head>
	<meta charset="utf8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Title Page</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="ckeditor/contents.css">
	<style type="text/css">
		table tr td{
			border:solid black 1px;
			table-layout: fixed;
		}
		
		table tr th{
			border:solid black 1px;
			table-layout: fixed;
			background-color:#ddd
		}
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
	</style>
	</head>
	<body>
		
	<div class="col-xs-12 col-sm-2" ></div>
	<div class="col-xs-12 col-sm-8" >
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-3">
					<div style="font-size:16px;text-align:center">搜尋行程</div>
					<form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/tripPlan/frontTrip.do" name="form1">
						<fieldset >
							<legend><span style="font-size: 12px">選擇類型</span></legend>
							<div>
								<input type="checkbox" id="type_public" name="trip_type" value="0">
								<label for="type_public">一般行程</label>
							</div>
							<div>
							    <input type="checkbox" id="type_shop" name="trip_type" value="1">
							    <label for="type_shop">店家行程</label>
							</div>
							<div>
							    <input type="checkbox" id="type_admin" name="trip_type" value="2">
							    <label for="type_admin">平台優選</label>
							</div>
						</fieldset>	<br><br>
						<fieldset>
							<legend><span style="font-size: 12px">選擇出發日期的區間</span></legend>
							<label for="from">開始日期</label>
							<input type="text" id="from" name="from"><br>
							<label for="to">結束日期</label>
							<input type="text" id="to" name="to">
						</fieldset><br><br>
						<fieldset>
							<legend><span style="font-size: 12px">搜尋關鍵字</span></legend>
							<input type="text" name="trip_keyword" placeholder="請輸入關鍵字"><br>
						</fieldset><br><br><br>
						<fieldset class="rating">
							<legend>
								<span style="font-size: 12px">選擇評分</span>
							</legend>
							<div>選擇最低星數</div>
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
						</fieldset><br><br><br><br><br><br><br>
						<fieldset>
							<legend><span style="font-size: 12px">排序</span></legend>
							<div>
								<input type="radio" id="sorting" name="sorting" value="byRate">
								<label for="sorting">依評分</label>
							</div>
							<div>
								<input type="radio" id="sorting" name="sorting" value="byNo">
								<label for="sorting">依編號</label>
							</div>
							<div>
								<input type="radio" id="sorting" name="sorting" value="byDate">
								<label for="sorting">依時間先後</label>
							</div>
						</fieldset>
						<hr>
						<button type="submit" class="btn btn-primary">查詢</button>
						<input type="hidden" name="action" value="get_composite_query">
					</form>
				</div>
				<div class="col-xs-12 col-sm-9" > 
					<table class="table table-responsive" align='center' valign='middle' >
						<tr>
						<th>行程編號</th>
						<td>${tripVO.tripNo}</td>
					</tr>
					<tr>
						<th>會員編號</th>
						<td>${tripVO.memId}</td>
					</tr>	
					<tr>
						<th>行程名稱</th>
						<td>${tripVO.tripName}</td>
					</tr>	
					<tr>
						<th>出發時間</th>
						<td><fmt:formatDate value="${tripVO.departTime}" pattern="yyyy/MM/dd HH:mm" /></td>
					</tr>	
					<tr>
						<th>行程類型</th>
						<td>${typeMap.get(tripVO.tripType)}</td>
					</tr>	
					<tr>
						<th>行程上架時間</th>
						<td><fmt:formatDate value="${tripVO.tripAddTime}" pattern="yyyy/MM/dd HH:mm" /></td>
					</tr>	
					<tr>
						<th>行程下架時間</th>
						<td><fmt:formatDate value="${tripVO.tripRmvTime}" pattern="yyyy/MM/dd HH:mm" /></td>
					</tr>	
					<tr>
						<th>行程星等</th>
						<td><span class="stars">${tripVO.tripRate/2}</span></td>
					</tr>	
					<tr>
						<th>行程是否公開</th>
						<td>${isPublicMap.get(tripVO.tripIsPublic)}</td>
					</tr>	
					<tr>
						<th>行程價格</th>
						<td>${tripVO.tripPrice}元</td>
					</tr>
					<tr>
						<th>行程廣告照片</th>
						<td>
							<img src="<%=request.getContextPath() %>/BackTripImgReader.do?tripNo=${tripVO.tripNo }" class="img-responsive img-thumbnail">
						</td>
					</tr>	
					<tr>
						<th>行程介紹</th>
						<td>${tripVO.tripDesc}</td>
					</tr>	
					<tr>
						<th>行程內容</th>
						<td>連到中和那邊</td>
					</tr>	
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-2" ></div>
		
	</body>
</html>
<script>
$(document).ready(function() {
	$('span.stars').stars();
});


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