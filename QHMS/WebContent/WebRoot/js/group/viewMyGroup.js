var dataSet = {};
var chapters = [];
var passwordTip = ""; //tip中显示的内容跨越弹窗存在，所以必须设置成全局变量

$(document).ready(function() {
    dataSet.dataColumn = [{
        data: "id",
        title: "学号",
        component: "textbox",
        readonly: true
    }, {
        data: "name",
        title: "姓名",
        component: "textbox",
        readonly: false
    }, {
        data: "studentClass",
        title: "班级",
        component: "textbox",
        readonly: false
    }];
    $.ajax({
        type: "POST",
        url: "getGroupMember.action",
        data: "",
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                dataSet.data = data.users;
                initDataTable("teamMember", dataSet);
            } else {
                alert(data.message);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("修改失败，连接出现问题！");
        }
    });
});

function initDataTable(id, dataSet) {
    $("#" + id).DataTable({
        fixedHeader: true,
        responsive: true,
        data: dataSet.data,
        columns: dataSet.dataColumn
    });
}
