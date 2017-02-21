var dataSet = {};
var chapters = [];
var passwordTip = ""; //tip中显示的内容跨越弹窗存在，所以必须设置成全局变量

$(document).ready(function() {
    dataSet.dataColumn = [{
        data: "id",
        title: "章节号",
        component: "textbox",
        readonly: true
    }, {
        data: "displayName",
        title: "章节",
        component: "textbox",
        readonly: true
    }, {
        data: "displayStatus",
        title: "状态",
        component: "textbox",
        readonly: false
    }, {
        data: "links",
        title: "操作",
        component: "textbox",
        readonly: false
    }];
    $.ajax({
        type: "POST",
        url: "homeworkTotalView.action",
        data: "",
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                dataSet.data = data.chapters;
                for (var i = 0; i < dataSet.data.length; i++) {
                    dataSet.data[i].links = '<a href="#"></a>';
                    if (dataSet.data[i].displayStatus == "作业待完成") {
                        dataSet.data[i].links = '<a href="doHomework.action?chapter=' + dataSet.data[i].id + '">开始做作业</a>';
                    } else if (dataSet.data[i].displayStatus == "作业批改中") {
                        dataSet.data[i].links = '<a href="myAnswer.action?chapter=' + dataSet.data[i].id + '">我的回答</a>';
                    } else if (dataSet.data[i].displayStatus == "作业已批改") {
                        dataSet.data[i].links = '<a href="showAnswer.action?chapter=' + dataSet.data[i].id + '">查看答案</a>';
                    }
                };
                initDataTable("courseTotalView", dataSet);
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
