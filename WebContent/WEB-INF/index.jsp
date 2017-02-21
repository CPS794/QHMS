<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to QHMS!</title>
<%@ include file="page/common/include.jsp"%>
<%@ include file="page/common/include-custom.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/WebRoot/css/basic/login.css">
</head>

<body>
    <div class="container">
        <div class="row qhms-login-panel">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">请先登录</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row qhms-row-text">
                            <label class="control-label col-md-4">学号/工号：</label>
                            <div class="col-md-8">
                                <input type="text" id="userName" name="userName" class="form-control">
                            </div>
                        </div>
                        <div class="row qhms-row-text">
                            <label class="control-label col-md-4">密码：</label>
                            <div class="col-md-8">
                                <input type="password" id="password" name="password" class="form-control">
                            </div>
                        </div>
                        <div class="row qhms-row-text">
                            <label class="control-label col-md-4">用户类型：</label>
                            <div class="col-md-8">
                                <select id="userType" name="userType" class="form-control" onchange="changeType()">
                                    <option value="null" selected>--请选择--</option>
                                    <option value="student">学生</option>
                                    <option value="admin">管理员</option>
                                </select>
                                <span class="help-block" id="helpMessage"></span>
                            </div>
                        </div>
                        <div class="row">
                        	<div class="col-md-2 col-xs-2"></div>
                        	<button type="button" class="btn btn-success col-md-3 col-xs-3" onclick="login()">登录</button>
                        	<div class="col-md-2 col-xs-2"></div>
                        	<button type="button" class="btn btn-primary col-md-3 col-xs-3" onclick="reset()">重置</button>
                        	<div class="col-md-2 col-xs-2"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</body>
<script src="${pageContext.request.contextPath}/WebRoot/js/basic/login.js" type="text/javascript"></script>
</html>