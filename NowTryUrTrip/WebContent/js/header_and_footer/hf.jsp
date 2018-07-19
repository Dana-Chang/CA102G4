<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
// 頁面載入時將header/footer載入，並計算header的高度

    $(document).ready(function() {
        $("#header").load("<%=request.getContextPath()%>/header_and_footer/frontend/header.jsp",function() {
            alert('$(".fh").height() is :' + $(".fh").height());
            $(".content-cap").css('height', $(".fh").height());
        });
        $("#footer").load("<%=request.getContextPath()%>/header_and_footer/frontend/footer.jsp");
    });

    $(window).load(
        alert("window loaded")
            );

    // 在視窗改變長寬時重新計算header的高度
    $(window).resize(function() {
        $(".content-cap").css('height', $(".fh").height());
        $(".content").css('padding-bottom', $(".footer").height());
    });