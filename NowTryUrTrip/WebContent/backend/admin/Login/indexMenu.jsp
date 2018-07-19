<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>
<div class="sidebar-nav">
	<ul>
		<!------------------------管理員管理 ------------------------------>
<c:if test = "${loginManagerVO.mgrIdentity == 1}">
		<li><a href="#" data-target=".dashboard-menu"
			class="nav-header collapsed" data-toggle="collapse"> <i
				class="fa fa-fw fa-dashboard"></i> 管理員管理<i class="fa fa-collapse"></i></a></li>
		<li><ul class="dashboard-menu nav nav-list collapse ">
				<li><a
					href="<%=request.getContextPath()%>/backend/admin/addManager.jsp">
						<span class="fa fa-caret-right"></span> 新增管理員
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/backend/admin/updateManager.jsp">
						<span class="fa fa-caret-right"></span>管理員資訊修改
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/backend/admin/listAllManagerIdentity.jsp">
						<span class="fa fa-caret-right"></span>管理員權限
				</a></li>
			</ul></li>

		<!----------------------------- 會員管理 ---------------------------->

		<li data-popover="true" data-placement="right"><a href="#"
			data-target=".premium-menu" class="nav-header collapsed"
			data-toggle="collapse"> <i class="fa fa-fw fa-fighter-jet"></i>
				會員管理 <i class="fa fa-collapse"></i></a></li>
		<li><ul class="premium-menu nav nav-list collapse ">
				<li><a
					href="<%=request.getContextPath()%>/backend/admin/member/listAllMember.jsp">
						<span class="fa fa-caret-right"></span> 一般會員管理
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/backend/admin/storeInf/listAllStoreInf.jsp">
						<span class="fa fa-caret-right"></span> 店家管理
				</a></li>
			</ul></li>



		<!-- --------------------日誌專區----------------------- -->

		<li><a href="#" data-target=".legal-menu"
			class="nav-header collapsed" data-toggle="collapse"><i
				class="fa fa-fw fa-legal"></i> 部落格管理<i class="fa fa-collapse"></i></a></li>
		<li><ul class="legal-menu nav nav-list collapse">
				<li><a href="<%=request.getContextPath()%>/backend/blog/article_list.jsp"><span
						class="fa fa-caret-right"></span> 文章檢舉處理</a></li>
				<li><a href="<%=request.getContextPath()%>/backend/blog/message_list.jsp"><span
						class="fa fa-caret-right"></span> 留言檢舉處理</a></li>
			</ul></li>

		<!-- ------------------------優惠訊息管理---------------------------- -->
		<li>
			<a href="#" data-target=".promtMgt-menu" class="nav-header collapsed" data-toggle="collapse">
				<i class="fa fa-fw fa-question-circle"></i> 優惠訊息  <i class="fa fa-collapse"></i>
			</a>
		</li>
		<li>
			<ul class="promtMgt-menu nav nav-list collapse">
				<li>
					<a href ="<%=request.getContextPath() %>/backend/promtMgt/listAllPpr.jsp" >
						<span class="fa fa-caret-right"></span>優惠訊息列表
					</a>
				</li>						
			</ul>						
		</li>
				
		<!-- ----------------------------景點管理專區------------------------------ -->
		<li>
			<a href="#" data-target=".spot-menu" class="nav-header collapsed" data-toggle="collapse">
				<i class="fa fa-fw fa-heart"></i>景點管理<i class="fa fa-collapse"></i>
			</a>
		</li>
		<li>
			<ul class="spot-menu nav nav-list collapse">
				<li>
					<a href="<%=request.getContextPath() %>/backend/spotMgt/listAllSpot.jsp">
						<span class="fa fa-caret-right"></span>景點列表
					</a>
				</li>
				<li>
					<a href="<%=request.getContextPath() %>/backend/spotMgt/addSpot.jsp">
						<span class="fa fa-caret-right"></span>新增景點
					</a>
				</li>
				<li>
					<a href="<%=request.getContextPath() %>/backend/spotMgt/checkSpot.jsp">
						<span class="fa fa-caret-right"></span>景點審核
					</a>
				</li>
				<li>
					<a href="<%=request.getContextPath() %>/backend/spotMgt/spotRptList.jsp">
						<span class="fa fa-caret-right"></span>景點檢舉處理
					</a>
				</li>
				<li>
					<a href="<%=request.getContextPath() %>/backend/spotMgt/spotCmntList.jsp">
						<span class="fa fa-caret-right"></span>景點評論列表
					</a>
				</li>
				<li>
					<a href="<%=request.getContextPath() %>/backend/spotMgt/spotCmntRptList.jsp">
						<span class="fa fa-caret-right"></span>景點評論檢舉處理
					</a>
				</li>
				<li>
					<a href="<%=request.getContextPath() %>/backend/spotMgt/addSpotCmnt.jsp">
						<span class="fa fa-caret-right"></span>新增景點評論
					</a>
				</li>
			</ul>
		</li>


</c:if>

		<!-- ------------------------代購管理---------------------------- -->
<c:if test= "${loginManagerVO.mgrIdentity == 2 || loginManagerVO.mgrIdentity == 1}">

		<li><a href="<%=request.getContextPath() %>/backend/buyAgentMgt/select_page.jsp" class="nav-header collapsed"> <i
				class="fa fa-fw fa-comment"></i> 代購管理 <i class="fa fa-collapse"></i></a></li>
				</c:if>
		<!-- -------------------套裝行程管理---------------------- -->
<%-- 		<c:if test= "${loginManagerVO.mgrIdentity == 3 || loginManagerVO.mgrIdentity == 1}"> --%>
<!-- 			<li data-popover="true" data-placement="right"> -->
			<li>
				<a href="#" data-target=".trip-menu" class="nav-header collapsed" data-toggle="collapse">
					<i class="fa fa-fw fa-briefcase"></i> 套裝行程管理<i class="fa fa-collapse"></i>
				</a>
			</li>
			<li>
				<ul class="trip-menu nav nav-list collapse">
					<li class="active">
						<a href="<%=request.getContextPath() %>/backend/tripMgt/trip_list.jsp">
							<span class="fa fa-caret-right"></span>行程列表
						</a>
					</li>
					<li>
						<a href="<%=request.getContextPath() %>/backend/tripMgt/addTrip.jsp">
							<span class="fa fa-caret-right"></span>新增行程
						</a>
					</li>
					<li>
						<a href="<%=request.getContextPath() %>/backend/tripMgt/tripCmntList.jsp">
							<span class="fa fa-caret-right"></span>行程評論列表
						</a>
					</li>
					<li>
						<a href="<%=request.getContextPath() %>/backend/tripMgt/tripCmntRptList.jsp">
							<span class="fa fa-caret-right"></span>行程評論檢舉處理
						</a>
					</li>
				</ul>
			</li>
<%-- 		</c:if> --%>
	</ul>
</div>