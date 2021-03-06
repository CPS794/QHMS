<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Manage</title>
<%@ include file="../../common/include.jsp"%>
<%@ include file="../../common/include-datatable.jsp"%>
<%@ include file="../../common/include-datetimepicker.jsp"%>
<%@ include file="../../common/include-custom.jsp"%>
</head>
<body class="sidebar-push">
    <%@ include file="../../common/sidebar.jsp"%>
        <!--Start Main-->
        <div class="main">
            <h3 align="center">系统管理员 管理</h3>
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <table id="userManageTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
            <!-- Modal -->
            <%@ include file="../../common/upload.jsp"%>
            <!-- End Modal -->
        </div>
        <!--End Main-->
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/WebRoot/js/manage/userManageAdmin.js"></script>

</html>