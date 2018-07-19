<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.storeInf.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	StoreInfVO storeInfVO = (StoreInfVO) request.getAttribute("storeInfVO");
	String check = (String) request.getAttribute("check");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontend/member/Login/Login.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/backend/admin/sweetalert.css">
<script src="<%=request.getContextPath()%>/js/frontend/member/Login.js"></script>
<script src="<%=request.getContextPath()%>/backend/admin/Login/lib/sweetalert.min.js" type="text/javascript"></script>

<style>
td {
	white-space: nowrap;
}
</style>
<!-- <script type="text/javascript"> 
// $( document ).ready(function() {
// alert("=====" + "${check}" +"________");
	
// });
</script>-->

<div class="container-fluid">
	<div class="fh-banner">
		<div class="row">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-4">
						<div class="row">
							<div class="logo">
								<a href="<%=request.getContextPath()%>/index.jsp">
									<div class="logo-icon">
										<img class="img-responsive"
											src="<%=request.getContextPath()%>/img/icon/nowtryurtrip_logo.png">
									</div>
									<div class="logo-text">NowTryUrTrip</div>
								</a>
							</div>
						</div>
					</div>
					<c:if test="${memberVO.memId == null}">
						<div class="col-xs-12 col-sm-8">
							<div class="fh-banner-top">
								<ul class="nav navbar-nav navbar-right">
									<li class="dropdown"><a class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">註冊 <b class="caret"></b></a>
										<ul class="dropdown-menu">
											<li><a data-toggle="modal" data-target="#myModal">會員註冊</a></li>
											<input type="hidden" name="check" value="${check}"></input>

											<li><a data-toggle="modal" data-target="#addStoreInf">店家註冊</a></li>
										</ul></li>
								</ul>
								<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
									aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title" id="myModalLabel">會員註冊</h4>
											</div>
											<div class="modal-body">
													<div>
														<img class="preview" style="max-width: 150px; max-height: 150px;"
															src="<%=request.getContextPath()%>/Member/memImg.do?memId=${memberVO.memId}" />
													</div>
													<h4>
													會員資料:<font color=red><b>*</b></font>為必填欄位
												</h4>
												<%-- 錯誤表列 --%>
												<c:if test="${not empty errorMsgs}">
													<ul>
														<c:forEach var="message" items="${errorMsgs}">
															<script>
																swal(
																		"${message}",
																		"",
																		"error")
															</script>
														</c:forEach>
													</ul>
												</c:if>

												<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/Member/mem.do" name="form1" enctype="multipart/form-data">
													<table class="table" border="0">
													<thead></thead>
													<tbody>
														<tr>
															<td>會員姓名:<font color=red><b>*</b></font></td>
															<td><input type="TEXT" name="memName" size="45"
																value="<%=(memberVO == null) ? "吳永志" : memberVO.getMemName()%>" /></td>
														</tr>
														<tr>
															<td>帳號:<font color=red><b>*</b></font></td>
															<td><input type="Email" name="memEmail" size="45"
																value="<%=(memberVO == null) ? "rgrtwee@yahoo.com.tw" : memberVO.getMemEmail()%>" /></td>
														</tr>
														<tr>
															<td>密碼:<font color=red><b>*</b></font></td>
															<td><input type="password" name="memPsw" size="45"
																value="<%=(memberVO == null) ? "123" : memberVO.getMemPsw()%>" /></td>
														</tr>
														<tr>
															<td>性別:</td>
															<td><select name="memGender"
																class="form-control span12">
																	<option value="">請選擇</option>
																	<option value="1">男</option>
																	<option value="2">女</option>
															</select></td>
														</tr>
														<tr>
															<td><font color=red><b></b></font></td>
															<td><input type="hidden" name="memType" size="45"
																value="<%=(memberVO == null) ? "1" : memberVO.getMemType()%>" /></td>
														</tr>
														<tr>
															<td>手機號碼:<font color=red><b>*</b></font></td>
															<td><input type="TEXT" name="memCell" size="45"
																value="<%=(memberVO == null) ? "0986532658" : memberVO.getMemCell()%>" /></td>
														</tr>
														<tr>
															<td>住家電話:<font color=red><b>*</b></font></td>
															<td><input type="TEXT" name="memTel" size="45"
																value="<%=(memberVO == null) ? "(07)-7584695" : memberVO.getMemTel()%>" /></td>
														</tr>
														<tr>
															<td>住址:<font color=red><b>*</b></font></td>
															<td><input type="TEXT" name="memAdd" size="45"
																value="<%=(memberVO == null) ? "桃園縣楊梅區幼獅路一段439號" : memberVO.getMemAdd()%>" /></td>
														</tr>
														<tr>
															<td>大頭照:<font color=red><b>*</b></font></td>
															<td><input type="FILE" class="upl" name="memImg"
																size="45" /></td>
														</tr>
														</tbody>
													</table>
											<div class="modal-footer">
												<input type="hidden" name="action" value="insert"> <input
													type="submit" class="btn btn-primary" value="送出新增"/>
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
											</div>
											</FORM>
											</div>
										</div><!-- /.modal-content -->
									</div><!-- /.modal -->
								</div>
							</div>
								<div class="modal fade" id="addStoreInf" tabindex="-1" role="dialog"
									aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title" id="myModalLabel">店家註冊</h4>
											</div>
											<div class="modal-body">
												<div>
													<div>
														<img class="preview"
															style="max-width: 150px; max-height: 150px;"
															src="<%=request.getContextPath()%>/Member/memImg.do?memId=${memberVO.memId}" />
													</div>
													<h4>
														店家資料:<font color=red><b>*</b></font>為必填欄位
													</h4>
													<%-- 錯誤表列 --%>
													<c:if test="${not empty errorMsgs}">
														<font color='red'>請修正以下錯誤:
															<ul>
																<c:forEach var="message" items="${errorMsgs}">
																	<li>${message}</li>
																</c:forEach>
															</ul>
														</font>
													</c:if>

													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/Member/mem.do"
														name="form1" enctype="multipart/form-data">
														<table class="table" border="0">
														<thead></thead>
														<tbody>
															<tr>
																<td>大頭照:<font color=red><b>*</b></font></td>
																<td><input type="FILE" class="upl" name="memImg"
																	size="45" /></td>
															</tr>
															<tr>
																<td>會員姓名:<font color=red><b>*</b></font></td>
																<td><input type="TEXT" name="memName" size="45"
																	value="<%=(memberVO == null) ? "吳永志" : memberVO.getMemName()%>" /></td>
															</tr>
															<tr>
																<td>負責人:<font color=red><b>*</b></font></td>
																<td><input type="TEXT" name="principal" size="45"
																	value="<%=(storeInfVO == null) ? "美麗島" : storeInfVO.getPrincipal()%>" /></td>
															</tr>
															<tr>
																<td>帳號:<font color=red><b>*</b></font></td>
																<td><input type="TEXT" name="memEmail" size="45"
																	value="<%=(memberVO == null) ? "rgrtwee@yahoo.com.tw" : memberVO.getMemEmail()%>" /></td>
															</tr>
															<tr>
																<td>密碼:<font color=red><b>*</b></font></td>
																<td><input type="TEXT" name="memPsw" size="45"
																	value="<%=(memberVO == null) ? "123" : memberVO.getMemPsw()%>" /></td>
															</tr>
															<tr>
																<td>性別:<font color=red><b>*</b></font></td>
																<td><input type="TEXT" name="memGender" size="45"
																	value="<%=(memberVO == null) ? "1" : memberVO.getMemGender()%>" /></td>
															</tr>
															<tr>
																<td><font color=red><b></b></font></td>
																<td><input type="hidden" name="memType" size="45"
																	value="<%=(memberVO == null) ? "1" : memberVO.getMemType()%>" /></td>
															</tr>
															<tr>
																<td>手機號碼:<font color=red><b>*</b></font></td>
																<td><input type="TEXT" name="memCell" size="45"
																	value="<%=(memberVO == null) ? "0986532658" : memberVO.getMemCell()%>" /></td>
															</tr>
															<tr>
																<td>連絡電話:<font color=red><b>*</b></font></td>
																<td><input type="TEXT" name="storCall" size="45"
																	value="<%=(storeInfVO == null) ? "0975456321" : storeInfVO.getStorCall()%>" /></td>
															</tr>
															<tr>
																<td>住家電話:<font color=red><b>*</b></font></td>
																<td><input type="TEXT" name="memTel" size="45"
																	value="<%=(memberVO == null) ? "(07)-7584695" : memberVO.getMemTel()%>" /></td>
															</tr>
															<tr>
																<td>住址:<font color=red><b>*</b></font></td>
																<td><input type="TEXT" name="memAdd" size="45"
																	value="<%=(memberVO == null) ? "桃園縣楊梅區幼獅路一段439號" : memberVO.getMemAdd()%>" /></td>
															</tr>
															<tr>
																<td>通信地址:<font color=red><b>*</b></font></td>
																<td><input type="TEXT" name="storAdd" size="45"
																	value="<%=(storeInfVO == null) ? "桃園縣楊梅區幼獅路一段439號" : storeInfVO.getStorAdd()%>" /></td>
															</tr>


															<tr>
																<td>統一編號:<font color=red><b>*</b></font></td>
																<td><input type="TEXT" name="uniformNum" size="45"
																	value="<%=(storeInfVO == null) ? "54236258" : storeInfVO.getUniformNum()%>" /></td>
															</tr>
															<tr>
																<td>郵遞區號:<font color=red><b>*</b></font></td>
																<td><input type="TEXT" name="postalCode" size="45"
																	value="<%=(storeInfVO == null) ? "325" : storeInfVO.getPostalCode()%>" /></td>
															</tr>
															<tr>
																<td>店家類別:</td>
																<td><select name="category"
																	class="form-control span12">
																		<option value="">請選擇</option>
																		<option value="1">旅館</option>
																		<option value="2">餐廳</option>
																		<option value="3">民宿</option>
																		<option value="4">旅行社</option>
																</select></td>
															</tr>
															</tbody>
														</table>
														<div class="modal-footer">
															<input type="hidden" name="action" value="insertStoreInf">
															<input type="submit" class="btn btn-primary" value="送出新增">
															<button type="button" class="btn btn-default"
																data-dismiss="modal">关闭</button>
														</div>
													</FORM>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal -->
									</div>
								</div>
									<div class="fh-banner-top">
										<ul class="nav navbar-nav navbar-right">
											<li class="dropdown"><a href='#modal-id'
												data-toggle="modal" class="btn btn-primary">${memberVO.memName}登入</a>
												<div class="modal fade" id="modal-id">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal"
																	aria-hidden="true">&times;</button>
																<h4 class="modal-title">標題</h4>
															</div>
															<div class="modal-body">
																<div class="container"
																	style="width: 60%; margin: o auto;">
																	<img id="profile-img" class="profile-img-card"
																		src="<%=request.getContextPath()%>/img/icon/nowtryurtrip_logo.png" />
																	<p id="profile-name" class="profile-name-card"></p>
																	<form method="post" class="form-signin" role="form"
																		action="<%=request.getContextPath()%>/Member/mem.do?action=login">
																		<div class="input-group margin-bottom-sm">
																			<span class="input-group-addon"> <i
																				class="fa fa-cog fa-spin fa-1x fa-fw"></i></span> <input
																				class="form-control" type="email" name="memEmail"
																				placeholder="Account" required autofocus>
																		</div>
																		<div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-key fa-fw" aria-hidden="true"></i></span> <input
																				class="form-control" type="password" name="memPsw"
																				placeholder="Password" required>
																		</div>
																		<div>
																			<c:if test="${not empty errorMsgs}">
																				<font color='red'> <c:forEach var="message"
																						items="${errorMsgs}">
																						<script>
																					swal(
																							"${message}",
																							"請修正錯誤",
																							"error")
																				</script>
																					</c:forEach>
																				</font>
																			</c:if>
																		</div>
																		<button class="btn btn-lg btn-success btn-block"
																			type="submit">登入</button>
																		<input type="hidden" name="action" value="login">
																	</form>
																</div>
															</div>
														</div>
													</div>
												</div></li>
										</ul>
									</div>
								</div></c:if>
					<c:if test="${memberVO.memId != null}">
						<div class="col-xs-12 col-sm-8">
							<div class="fh-banner-top">
								<ul class="nav navbar-nav navbar-right">
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown">${memberVO.memName} 你好 <b
											class="caret"></b></a>
										<ul class="dropdown-menu">


											<li><a href="<%=request.getContextPath()%>/frontend/member/MemberHome.jsp">個人資料</a></li>
											<li><a href="#">個人部落格</a></li>
											<li><form role="form"
													action="<%=request.getContextPath()%>/Member/mem.do?action=logout">
													<button class="btn btn-primary pull-right" type="submit">登出</button>
													<input type="hidden" name="action" value="logout">
												</form></li>
										</ul></li>
								</ul>
							</div>
						</div>
					</c:if>
					<div class="col-xs-12 col-sm-8">
						<div class="fh-banner-bottom">
							<ul class="nav navbar-nav navbar-left">
								<li><a href="#">好友揪團</a></li>
								<li><a
									href="<%=request.getContextPath()%>/frontend/tripPlan/tripPlan.jsp">行程規劃</a></li>
								<li><a href="<%=request.getContextPath()%>/frontend/buyAgent/buyAgent.jsp">找人代買</a></li>
								<li><a href="#">優惠訊息</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function (){
 
    function format_float(num, pos)
    {
        var size = Math.pow(10, pos);
        return Math.round(num * size) / size;
    }
 
    function preview(input) {
 
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            
            reader.onload = function (e) {
                $('.preview').attr('src', e.target.result);
                var KB = format_float(e.total / 1024, 2);
                $('.size').text("檔案大小：" + KB + " KB");
            }
 
            reader.readAsDataURL(input.files[0]);
        }
    }
 
    $("body").on("change", ".upl", function (){
        preview(this);
    })
    
})
</script>