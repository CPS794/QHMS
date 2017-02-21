<%@page import="cn.edu.ustb.sem.datastructure.po.user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/WebRoot/css/basic/sidebar.css">
<script
	src="${pageContext.request.contextPath}/WebRoot/js/basic/menu.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/WebRoot/js/basic/sidebar.js"
	type="text/javascript"></script>
<!-- 用于从session中取值的隐藏域，本页面需要学号  -->
<%
	User currentUser = (User) (session.getAttribute("CurrentUser"));
%>
<input id="hiddenNumber" value="<%=currentUser.getId()%>" type="hidden" />
<input id="hiddenName" value="<%=currentUser.getName()%>" type="hidden" />
<input id="hiddenType" value="<%=currentUser.getType()%>" type="hidden" />

<!--Start Sidebar-->
<div class="sidebar sidebar-vertical sidebar-left" id="mainNav">
	<div id="logo">
		<img class="picture"
			src="${pageContext.request.contextPath}/WebRoot/image/userIcon.png"
			height="200" width="200">
		<br>
		<span id="userInfo">欢迎使用<br>题库作业管理系统！
		</span>
		<br>
	</div>
	<nav id="nav"></nav>
</div>
<!--End Sidebar-->

<div class="menuBtn" id="sidebarToggle">菜单</div>