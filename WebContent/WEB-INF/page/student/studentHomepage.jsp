<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Course Total View</title>
<%@ include file="../common/include.jsp"%>
</head>
<body class="sidebar-push">
	<%@ include file="../common/sidebar.jsp"%>
	<%@ include file="../common/include-datatable.jsp"%>
	<%@ include file="../common/include-datetimepicker.jsp"%>
	<%@ include file="../common/include-custom.jsp"%>
	<!--Start Main-->
    <div class="main">
        <h3 id="groupInfo" align="center">课程总览</h3>
        <div class="container">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <table id="courseTotalView" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>
    </div>
    <!--End Main-->
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/WebRoot/js/homework/homeworkTotalView.js"></script>

</html>