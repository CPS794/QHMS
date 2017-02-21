<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Problem Manage</title>
<%@ include file="../common/include.jsp"%>
<%@ include file="../common/include-datatable.jsp"%>
<%@ include file="../common/include-datetimepicker.jsp"%>
<%@ include file="../common/include-custom.jsp"%>
</head>
<body class="sidebar-push">
    <%@ include file="../common/sidebar.jsp"%>
        <!--Start Main-->
        <div class="main">
            <h3 align="center">题目管理</h3>
            <div class="container">
                <div class="row">
                    <table id="problemManageTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- Modal Upload  -->
            <%@ include file="../common/upload.jsp"%>
            <!-- End Modal Upload -->
        </div>
        <!--End Main-->
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/WebRoot/js/manage/adminManageProblem.js"></script>

</html>