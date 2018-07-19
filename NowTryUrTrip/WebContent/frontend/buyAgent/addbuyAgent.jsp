<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.purchasingOrder.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	PurchasingOrderService purchasingOrderSvc = new PurchasingOrderService();
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	if(memberVO != null){
	Integer memId = memberVO.getMemId();
	
	}else{
		memberVO = (MemberVO) request.getAttribute("memberVO");
	}
	
%>


<!DOCTYPE html>
<html >

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Title Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- header.css以及footer.css勿刪… -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header_and_footer/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header_and_footer/footer.css">
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        
        <style type="text/css">
    .aa {
        background-color: #EEE;
    }
    
    .bb {
        color: red;
    }
    .cc{
    	background-color: white;
    }
    </style>    
</head>

<body id="bootstrap-overrides">
    <!-- 我會在header tag放內容，勿動 -->
    <div id="header" class="fh"></div>
    <div class="content-cap">參考header的高度</div>
    <div class="container-fluid content" style="">
        <div class="row" style="background-color: white">
            <div class="col-xs-12 col-sm-2 " style="margin:0">
                <div class="panel panel-default">
                    <ul class="list-group">
                        <a href="<%=request.getContextPath() %>/frontend/buyAgent/buyAgent.jsp" class="list-group-item">代買需求</a>
                        <a href="<%=request.getContextPath() %>/frontend/buyAgent/buyAgentlist.jsp" class="list-group-item">代買接單</a>
                        <a href="<%=request.getContextPath() %>/frontend/buyAgent/buyAgent.jsp" class="list-group-item">點數交易</a>
                    </ul>
                </div>
            </div>
            <div class="col-xs-12 col-sm-8 aa" style="margin:0,width:50%">
                <div class="row " style="margin-bottom: 20%">
                <h2 class="text-buyer-secondary bb">需求清單</h2>
                     <div class="col-xs-12 col-sm-1">
                    </div>
                    <div class="col-xs-12 col-sm-10 cc">
                    
                    </div>
                    <div class="col-xs-12 col-sm-1">
                    </div>   
                </div>
                <div class="row"  style="margin-bottom: 30% ">
             		<h2 class="text-buyer-secondary bb">待回覆清單</h2>
                    <div class="col-xs-12 col-sm-1">
                    </div>
                    <div class="col-xs-12 col-sm-10 cc">
                    </div>
                    <div class="col-xs-12 col-sm-1">
                	</div>
            	</div>
    		</div>
            <div class="col-xs-12 col-sm-2 " style="margin:0">

                </div>
           
        </div>
    </div>
    <!-- 我會在footer tag放內容，勿動 -->
    <div id="footer" class=""></div>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/header_and_footer/hf.jsp"></script>
</body>

</html>


