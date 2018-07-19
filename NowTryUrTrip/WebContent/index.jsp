<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.controller.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Title Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend/member/main.css">
    <!-- header.css以及footer.css勿刪… -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header_and_footer/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header_and_footer/footer.css">
        
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body id="bootstrap-overrides">
    <!-- 我會在header tag放內容，勿動 -->
    <div id="header" class="fh"></div>
    <div class="content-cap">參考header的高度</div>
    <div class="container-fluid content" style="">
        <div class="row">
            <div id="carousel-id" class="carousel slide" data-ride="carousel">
                    <!-- 幻燈片小圓點區 -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-id" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-id" data-slide-to="1" class=""></li>
                        <li data-target="#carousel-id" data-slide-to="2" class=""></li>
                        <li data-target="#carousel-id" data-slide-to="3" class=""></li>
                        <li data-target="#carousel-id" data-slide-to="4" class=""></li>
                        <li data-target="#carousel-id" data-slide-to="5" class=""></li>
                        <li data-target="#carousel-id" data-slide-to="6" class=""></li>
                        <li data-target="#carousel-id" data-slide-to="7" class=""></li>
                        <li data-target="#carousel-id" data-slide-to="8" class=""></li>
                        <li data-target="#carousel-id" data-slide-to="9" class=""></li>
                    </ol>
                    <!-- 幻燈片主圖區 -->
                    <div class="carousel-inner">
                        <div class="container top" style="">
                            <div class="search">
                                <div class="select">
                                    <form>
                                        <select class="aa" name="YourLocation">
                                            <option class="active" value="Taipei">地區</option>
                                            <option value="Taipei">台北</option>
                                            <option value="Taoyuan">桃園</option>
                                            <option value="Hsinchu">新竹</option>
                                            <option value="Miaoli">苗栗</option>
                                        </select>
                                    </form>
                                </div>
                                <div class="select">
                                    <form>
                                        <select class="aa" name="YourLocation">
                                            <option class="active" value="Taipei">地點</option>
                                            <option value="Taipei">飯店</option>
                                            <option value="Taoyuan">餐廳</option>
                                            <option value="Hsinchu">樂園</option>
                                            <option value="Miaoli">風景</option>
                                        </select>
                                    </form>
                                </div>
                                <div class="select">
                                    <form>
                                        <select class="aa" name="YourLocation">
                                            <option class="active" value="Taipei">種類</option>
                                            <option value="Taipei">景點</option>
                                            <option value="Taoyuan">行程</option>
                                            <option value="Hsinchu">日誌</option>
                                        </select>
                                    </form>
                                </div>
                                <div class="select">
                                    <input class="aa tt" type="text" name="" placeholder="請輸入關鍵字" />
                                </div>
                                <div class="select">
                                    <input type="button" class="btn btn-info " value="查詢" />
                                </div>
                            </div>
                        </div>
                        <div class="item active">
                            <img src="<%=request.getContextPath()%>/img/01_resized.jpg" alt="">
                        </div>
                        <div class="item">
                            <img src="<%=request.getContextPath()%>/img/02_resized.jpg" alt="">
                        </div>
                        <div class="item">
                            <img src="<%=request.getContextPath()%>/img/03_resized.jpg" alt="">
                        </div>
                        <div class="item">
                            <img src="<%=request.getContextPath()%>/img/04_resized.jpg" alt="">
                        </div>
                        <div class="item">
                            <img src="<%=request.getContextPath()%>/img/05_resized.jpg" alt="">
                        </div>
                        <div class="item">
                            <img src="<%=request.getContextPath()%>/img/06_resized.jpg" alt="">
                        </div>
                        <div class="item">
                            <img src="<%=request.getContextPath()%>/img/07_resized.jpg" alt="">
                        </div>
                        <div class="item">
                            <img src="<%=request.getContextPath()%>/img/08_resized.jpg" alt="">
                        </div>
                    </div>
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
