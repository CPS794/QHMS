function login() {
    if (window.self != window.top) {
        window.top.location = window.location;
    }
    var user = new Object();
    if ($("#userType").val() == "null") {
        $("#helpMessage").text("请选择用户类型！");
    } else {
        user.userType = Enum_UserType[$("#userType").val()];
        user.userName = $("#userName").val();
        user.password = cal_md5($("#password").val());
        $.ajax({
            type: "POST",
            url: "login.action",
            data: user,
            dataType: "json",
            success: function(data, textStatus, jqXHR) {
                if (data.success) {
                    window.location = data.location;
                } else {
                    $("#helpMessage").text(data.message);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Something really bad happened " + textStatus);
                alert("连接出现问题！");
            }
        });
    }
}

function reset() {
    $("#userType").val("null");
    $("#userName").val("");
    $("#password").val("");
    $("#helpMessage").text("");
}

function changeType() {
    if ($("#userType").val() != "null") {
        $("#helpMessage").text("");
    }
}
