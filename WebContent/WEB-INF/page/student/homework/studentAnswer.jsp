<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homework</title>
<%@ include file="../../common/include.jsp"%>
<%@ include file="../../common/include-summernote.jsp"%>
<%@ include file="../../common/include-custom.jsp"%>
</head>
<body class="sidebar-push">
	<%@ include file="../../common/sidebar.jsp"%>
	<!--Start Main-->
    <div class="main container">
	    <h3 align="center" id="answerChapterTitle">本章作业</h3>
	    <div class="row">
	        <div class="col-md-1"></div>
        	<div class="col-md-10" id="answerPanel"></div>
        	<div class="col-md-1"></div>
	    </div>
	    <!-- 按钮组 -->
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6" align="center">
                <button type="button" class="btn btn-danger" onclick="goBack()">返回上页</button>
            </div>
            <div class="col-md-3"></div>
        </div>
	</div>
    <!--End Main-->
    <input id="hiddenChapterId" value="${cid}" type="hidden" />
    <input id="hiddenStudentId" value="${uid}" type="hidden" />
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/WebRoot/js/homework/studentAnswer.js"></script>
</html>
