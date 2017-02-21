<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">File Upload</h4>
        </div>
        <div class="modal-body">
            <form id="UploadForm" action="FileUpload" method="post" enctype="multipart/form-data" class="form">
                <div class="row">
                    <div class="col-md-9">
                        <input type="file" id="myfile" name="myfile">
                    </div>
                    <div class="col-md-3">
                        <input type="submit" class="form-control btn-primary" value="Upload">
                    </div>
                </div>
            </form>
            <br />
            <div class="progress">
                <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
                    0%
                </div>
            </div>
            <br />
            <div id="message"></div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
    </div>
</div>
<!-- import jQuery Form -->
<script type="text/javascript" src="${pageContext.request.contextPath}/WebRoot/plugin/jquery/jquery.form.js"></script>
<!-- import File Upload Script -->
<script type="text/javascript" src="${pageContext.request.contextPath}/WebRoot/js/basic/fileUpload.js"></script>
</div>