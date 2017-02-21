var dataSet = {};
var chapters = [];

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
        url: "evaluationTotalView.action",
        data: "",
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                dataSet.data = data.chapters;
                for (var i = 0; i < dataSet.data.length; i++) {
                    dataSet.data[i].links = '<a href="#"></a>';
                    if (data['chapter_' + data.chapters[i].id] != null) {
                        chapters[data.chapters[i].id] = data['chapter_' + data.chapters[i].id];
                    };
                    if (dataSet.data[i].displayStatus == "未评价") {
                        dataSet.data[i].links = '<a onclick="showEvaluateWindow(' + i + ')">评价</a>';
                    } else if (dataSet.data[i].displayStatus == "已评价") {
                        dataSet.data[i].links = '<a onclick="showMyEvaluation(' + i + ')">查看我的评价</a>';
                    }
                };
                initDataTable("evaluationTable", dataSet);
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

function showEvaluateWindow(chapter) {
    var thisChapter = chapters[dataSet.data[chapter].id];
    var evaluateWindowBody = '<h4 align="center">每项满分均为100分，请填写非负整数分值。</h4>';
    for (var i = 0; i < thisChapter.length; i++) {
        evaluateWindowBody += '<div class="row">';
        evaluateWindowBody += '<label class=\'control-label col-xs-4 col-md-4 text-right\'>' + thisChapter[i].scoreName + ': ' + '</label>';
        evaluateWindowBody += '<div class=\'col-xs-8 col-md-8\'>';
        evaluateWindowBody += '<input id="score' + thisChapter[i].scorePercentageId + '" " type="text" placeholder="请填写非负整数分值" class="form-control">';
        evaluateWindowBody += '</div>';
        evaluateWindowBody += '</div>';
    };
    bootbox.dialog({
        title: '评价第 ' + dataSet.data[chapter].id + ' 组 ' + dataSet.data[chapter].displayName,
        message: evaluateWindowBody,
        buttons: {
            success: {
                label: "提交评价",
                className: "btn-primary",
                callback: function() {
                    var evaluation=[];
                    for (var i = 0; i < thisChapter.length; i++) {
                        var x=parseInt($('#score'+thisChapter[i].scorePercentageId).val());
                        if (x<0) x=0;
                        if (x>100) x=100;
                        evaluation[thisChapter[i].scorePercentageId]=x;
                    };
                    $.ajax({
                        type: "POST",
                        url: "evaluationSubmit.action",
                        data: "chapterId="+dataSet.data[chapter].id+"&evaluation="+evaluation,
                        dataType: "json",
                        success: function(data, textStatus, jqXHR) {
                            if (data.success) {
                                alert("评价成功！");
                                location.reload();
                            } else {
                                alert(data.message);
                            }
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.log("Something really bad happened " + textStatus);
                            alert("评价失败，连接出现问题！");
                        }
                    });
                }
            },
            failed: {
                label: "取消评价",
                className: "btn-danger",
                callback: function() {}
            }
        }
    });
}

function showMyEvaluation (chapter) {
    var thisChapter = chapters[dataSet.data[chapter].id];
    var evaluateWindowBody = '<h4 align="center">你给这个小组的评分如下：</h4>';
    for (var i = 0; i < thisChapter.length; i++) {
        evaluateWindowBody += '<div class="row">';
        evaluateWindowBody += '<div class="col-xs-4 col-md-4"></div>';
        evaluateWindowBody += '<div class="col-xs-8 col-md-8">';
        evaluateWindowBody += thisChapter[i].scoreName+': ';
        evaluateWindowBody += thisChapter[i].point;
        evaluateWindowBody += '</div>';
        evaluateWindowBody += '</div>';
        evaluateWindowBody += '</div>';
    };
    bootbox.dialog({
        title: '评价第 ' + dataSet.data[chapter].id + ' 组 ' + dataSet.data[chapter].displayName,
        message: evaluateWindowBody
    });
}
