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
	    <h3 align="center" id="homeworkChapterTitle">本章作业</h3>
	    <div class="row">
	        <div class="col-md-1"></div>
	        <div class="col-md-8">
	            <h4 class="row" id="homeworkProblemType"></h4>
	            <br>
	            <div class="row" id="homeworkDescription_div"></div>
	            <br>
	            <div class="row" id="homeworkOption_div"></div>
	            <br>
	            <div class="row">
	                <button type="button" class="btn btn-default" onclick="goPrev_problem()">上一题</button>
	                <button type="button" class="btn btn-default" onclick="goNext_problem()">下一题</button>
	            </div>
	        </div>
	        <div class="col-md-2">
	            <h4 align="center">答题情况</h4>
	            <br>
	            <div class="row" id="viewPanel">
	                <div class="questionNumber" onclick="" id="thisTest0">1</div>
	            </div>
	            <br>
	            <div class="row">
	                <button type="button" class="btn btn-success" onclick="check_homework()">提交</button>
	                <button type="button" class="btn btn-danger" onclick="leave_homework()">离开</button>
	            </div>
	        </div>
	        <div class="col-md-1"></div>
	    </div>
	    <div class="modal fade" id="homeworkCheckModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	        <div class="modal-dialog modal-lg" role="document">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                    <h4 class="modal-title" id="homeworkCheckModalLabel">检查该章节作业</h4>
	                </div>
	                <div class="">
		                	<div class="col-md-1"></div>
		                	<div class="col-md-10" id="problemsCheckPanel"></div>
		                	<div class="col-md-1"></div>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-warning" onclick="submit_homework()">提交作业</button>
	                    <button type="button" class="btn btn-default" data-dismiss="modal">返回修改</button>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
    <!--End Main-->
    <input id="hiddenChapterId" value="${cid}" type="hidden" />
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/WebRoot/js/homework/homeworkDoing.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/WebRoot/css/homework/homework.css">
 
</html>
