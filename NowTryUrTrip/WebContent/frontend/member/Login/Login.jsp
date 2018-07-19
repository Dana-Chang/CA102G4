<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="">

<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/frontend/Login/main.css">
<style type="text/css">
</style>
<script src="/css/frontend/Login/new.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
	<div class="container">
		<div class="card card-container">
			<img id="profile-img" class="profile-img-card"
				src="<%=request.getContextPath()%>/css/frontend/Login/avatar_2x.png" />
			<p id="profile-name" class="profile-name-card"></p>
			<form method="post" class="form-signin" role="form"
				action="<%=request.getContextPath()%>/Member/mem.do?action=login">
				<div class="input-group margin-bottom-sm">
					<span class="input-group-addon">
					<i class="fa fa-cog fa-spin fa-1x fa-fw"></i></span> 
					<input class="form-control" type="email" name="memEmail" placeholder="Account" required autofocus>
				</div>
				<div class="input-group">
					<span class="input-group-addon">
					<i class="fa fa-key fa-fw" aria-hidden="true"></i></span> 
					<input class="form-control" type="password" name="memPsw" placeholder="Password" required>
				</div>
				<div>
					<c:if test="${not empty errorMsgs}">
						<font color='red'> <c:forEach var="message"
								items="${errorMsgs}">
								<script>
									swal("${message}", "請修正錯誤", "error")
								</script>
							</c:forEach>
						</font>
					</c:if>
				</div>
				<button class="btn btn-lg btn-success btn-block" type="submit">登入</button>
				<input type="hidden" name="action" value="login">
			</form>
		</div>
	</div>
</body>

</html>
