<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Problem Modify</title>
<%@ include file="../../common/include.jsp"%>
<%@ include file="../../common/include-summernote.jsp"%>
<%@ include file="../../common/include-custom.jsp"%>
</head>
<body class="sidebar-push">
	<%@ include file="../../common/sidebar.jsp"%>
	<!--Start Main-->
    <div class="main container">
        <h3 align="center">修改题目</h3>
        <!-- 题目编号、章节、类型、分值 -->
        <div class="row form-inline">
            <div class="form-group col-md-3">
                <label class="control-label" for="AddProblemId">题目编号：</label>
                <input type="text" class="form-control" placeholder="" id="AddProblemId" value="" disabled>
            </div>
            <div class="form-group col-md-3" id="AddProblemChapter_div">
                <label class="control-label" for="AddProblemChapter">章节：</label>
            </div>
            <div class="form-group col-md-3" id="AddProblemType_div">
                <label class="control-label" for="AddProblemType">题目类型：</label>
            </div>
            <div class="form-group col-md-3">
                <label class="control-label" for="AddProblemPoint">默认分值：</label>
                <input type="text" class="form-control" placeholder="默认分值（整数）" id="AddProblemPoint" value="">
            </div>
        </div>
        <br>
        <!-- 题干 -->
        <div class="row">
            <div class="col-md-1">
                <label class="control-label" for="AddProblemDescription">题干：</label>
            </div>
            <div class="col-md-11">
                <div id="AddProblemDescription"></div>
            </div>
        </div>
        <!-- 选项 & 答案 -->
        <div id="targetArea"></div>
        <!-- 1. 判断题 -->
        <!-- 2. 单项选择题 -->
        <!-- 3. 不定项选择题 -->
        <!-- 4. 填空题 -->
        <!-- 5. 简答题 -->
        <!-- 6. 综合题 -->
        <br>
        <!-- 按钮组 -->
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6" align="center">
                <button type="button" class="btn btn-success" onclick="submitProblem()">提交题目</button>
                <button type="button" class="btn btn-danger" onclick="previousPage()">返回上页</button>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>
    <!--End Main-->
    <input id="hiddenProblemId" value="${pid}" type="hidden" />
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/WebRoot/js/problem/studentModifyProblem.js"></script>
</html>
