$(document).ready(function() {
    var options = {
        beforeSend: function() {
            $("#progressbox").show();
            // clear everything
            $(".progress-bar").width('0%');
            $("#message").empty();
            $(".progress-bar").html("0%");
        },
        uploadProgress: function(event, position, total, percentComplete) {
            $(".progress-bar").width(percentComplete + '%');
            $(".progress-bar").html(percentComplete + '%');

            // change message text to red after 50%
            if (percentComplete > 50) {
                $("#message").html("<font color='red'>File Upload is in progress</font>");
            }
        },
        success: function() {
            $(".progress-bar").width('100%');
            $(".progress-bar").html('100%');
            afterUpload();
        },
        complete: function(response) {
            $("#message").html("<font color='blue'>Your file has been uploaded!</font>");
        },
        error: function() {
            $("#message").html("<font color='red'> ERROR: unable to upload files</font>");
        }
    };
    $("#UploadForm").ajaxForm(options);
});

function showFileUploadWindow() {
    $('#myModal').modal('toggle');
}
