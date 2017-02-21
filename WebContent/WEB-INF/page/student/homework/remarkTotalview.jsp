<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homework Remark</title>
<%@ include file="../../common/include.jsp"%>
</head>
<body class="sidebar-push">
	<%@ include file="../../common/sidebar.jsp"%>
	<%@ include file="../../common/include-datatable.jsp"%>
	<%@ include file="../../common/include-datetimepicker.jsp"%>
	<%@ include file="../../common/include-custom.jsp"%>
	<!--Start Main-->
    <div class="main">
        <h3 id="homeworkRemarkTitle" align="center">作业批阅</h3>
        <div class="container">
            <div class="row">
                <div>
                    <table id="homeworkRemarkTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="modal fade" id="homeworkRemarkModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="homeworkRemarkModalLabel">作业评分</h4>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-10">
                            <div class="row">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><span class="glyphicon glyphicon-menu-hamburger col-md-2" aria-hidden="true"></span>题目</h3>
                                    </div>
                                    <div class="panel-body" id="problem_panel">
                                        
                                    </div>
                                </div>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><span class="glyphicon glyphicon-menu-hamburger col-md-2" aria-hidden="true"></span>参考答案</h3>
                                    </div>
                                    <div class="panel-body" id="problem_answer">
                                        
                                    </div>
                                </div>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><span class="glyphicon glyphicon-menu-hamburger col-md-2" aria-hidden="true"></span>同学回答</h3>
                                    </div>
                                    <div class="panel-body" id="student_answer">
                                        
                                    </div>
                                </div>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><span class="glyphicon glyphicon-menu-hamburger col-md-2" aria-hidden="true"></span>评分及评价</h3>
                                    </div>
                                    <div class="panel-body" id="remark_panel">
                                        <div id="full_point_panel"></div>
                                        <div class="form-inline">
                                            <label class="control-label" for="remark_point">评分：</label>
                                            <input type="text" class="form-control" placeholder="请填不超过满分的整数" id="remark_point" value="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" onclick="submit_remark()">提交评价</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">撤销评价</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--End Main-->
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/WebRoot/js/homework/remarkTotalView.js"></script>

</html>