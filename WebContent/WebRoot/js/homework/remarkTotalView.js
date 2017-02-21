var dataSet = {};
var chapters = [];
var status;
var now_rows;

$(document).ready(function() {
    dataSet.dataColumn = [{
        data: "typeId",
        title: "题型",
        width: "6%",
        component: "textbox",
        readonly: true
    }, {
        data: "problemId",
        title: "题号",
        width: "6%",
        component: "textbox",
        readonly: false
    }, {
        data: "description_to_display",
        title: "题干",
        width: "30%",
        component: "textbox",
        readonly: false
    }, {
        data: "student_fullname",
        title: "答题人",
        width: "13%",
        component: "textbox",
        readonly: false
    }, {
        data: "answer_to_display",
        title: "同学的回答",
        width: "20%",
        component: "textbox",
        readonly: false
    }, {
        data: "teacher_fullname",
        title: "评分人",
        width: "13%",
        component: "textbox",
        readonly: true
    }, {
        data: "fullPoint",
        title: "分值",
        width: "6%",
        component: "textbox",
        readonly: false
    }, {
        data: "point",
        title: "得分",
        width: "6%",
        component: "textbox",
        readonly: false
    }];
    getRemarkList();
});

function getRemarkList() {
    $.ajax({
        type: "POST",
        url: "listAnswers.action",
        data: "",
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                status = data.status;
                dataSet.data = data.answers;
                for (var i = 0; i < dataSet.data.length; i++) {
                    dataSet.data[i].description_to_display = dataSet.data[i].description
                        .replace(/<\/p>/gi, "\n")
                        .replace(/<br\/?>/gi, "\n")
                        .replace(/<\/?[^>]+(>|$)/g, "");
                    dataSet.data[i].answer_to_display = [];
                    for (var j = 0; j < dataSet.data[i].answer.length; j++) {
                        dataSet.data[i].answer_to_display[j] = dataSet.data[i].answer[j]
                            .replace(/<\/p>/gi, "\n")
                            .replace(/<br\/?>/gi, "\n")
                            .replace(/<\/?[^>]+(>|$)/g, "");
                    };
                    if (dataSet.data[i].teacher_fullname == "") {
                        dataSet.data[i].teacher_fullname = "未批改";
                        dataSet.data[i].point = "";
                    }

                };
                initDataTable("homeworkRemarkTable", dataSet);
            } else {
                alert(data.message);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("修改失败，连接出现问题！");
        }
    });
}

function initDataTable(id, dataSet) {
    $("#" + id).DataTable({
        fixedHeader: true,
        responsive: true,
        select: {
            style: 'single'
        },
        dom: 'Bfrt<"col-md-4"l><"col-md-4"i><"col-md-4"p>',
        buttons: [{
            extend: 'colvis',
            columns: ':not(:first-child)'
        }, {
            text: '打分',
            action: function(e, dt, node, config) {
                showRemarkWindow(id);
            }
        }, {
            text: '修改打分',
            action: function(e, dt, node, config) {
                showModifyRemarkWindow(id);
            }
        }, {
            text: '发布答案',
            action: function(e, dt, node, config) {
                if (status > 1) {
                    alert("该章节答案已经发布，请勿重复操作！");
                } else {
                    var r = confirm("答案发布后，作业被批改完成的同学将能看到参考答案。确定要发布答案吗？");
                    if (r == true) {
                        publicAnswer();
                    }
                }
            }
        }, {
            text: '错题情况',
            action: function(e, dt, node, config) {
                var r = confirm("即将下载目前为止已评分（包括机判和人判）的题目的错误情况。建议等所有同学的作业都批阅完毕后下载，否则未批阅的部分将不在下载结果中。");
                if (r == true) {
                    downloadAnswer();
                }
            }
        }],
        data: dataSet.data,
        columns: dataSet.dataColumn
    });
}

function checkSingleSelect(id) {
    var table = $("#" + id).DataTable();
    var rows = table.rows('.selected').indexes();
    if (rows.length < 1) {
        alert("请选择一行后再进行操作！");
    } else if (rows.length > 1) {
        alert("请不要选择多行！");
    } else {
        return true;
    }
}

function showRemarkWindow(id) {
    if (checkSingleSelect(id)) {
        var table = $("#" + id).DataTable();
        now_rows = table.rows('.selected').indexes();
        var data = table.rows(now_rows).data();
        if (data[0].teacher_fullname != '未批改') {
            alert("已有分数的回答请使用“修改打分”功能进行修改！");
        } else {
            $('#problem_panel').html(data[0].description);
            var problemAnswerString = '';
            for (var i = 0; i < data[0].problemAnswer.length; i++) {
                problemAnswerString += data[0].problemAnswer[i];
                problemAnswerString += '<br>';
            };
            $('#problem_answer').html(problemAnswerString);
            var answerString = '';
            for (var i = 0; i < data[0].answer.length; i++) {
                answerString += data[0].answer[i];
                answerString += '<br>';
            };
            $('#student_answer').html(answerString);
            $('#full_point_panel').html('本题满分 ' + data[0].fullPoint + ' 分');
            $('#homeworkRemarkModal').modal('toggle');
        }
    }
}

function showModifyRemarkWindow(id) {
    if (checkSingleSelect(id)) {
        var table = $("#" + id).DataTable();
        now_rows = table.rows('.selected').indexes();
        var data = table.rows(now_rows).data();

        var userId = $('#hiddenNumber').val();
        if (userId != data[0].teacherId && data[0].teacher_fullname != '未批改') {
            var r = confirm('你的队友' + data[0].teacher_fullname + '同学给这个答案给出这样的分数应该有他的理由，建议与其讨论后再对评分进行修改。你确定要修改评分吗？');
            if (r == true) {
                $('#problem_panel').html(data[0].description);
                var problemAnswerString = '';
                for (var i = 0; i < data[0].problemAnswer.length; i++) {
                    problemAnswerString += data[0].problemAnswer[i];
                    problemAnswerString += '<br>';
                };
                $('#problem_answer').html(problemAnswerString);
                var answerString = '';
                for (var i = 0; i < data[0].answer.length; i++) {
                    answerString += data[0].answer[i];
                    answerString += '<br>';
                };
                $('#student_answer').html(answerString);
                $('#full_point_panel').html('本题满分 ' + data[0].fullPoint + ' 分');
                $('#remark_point').val(data[0].point);
                $('#homeworkRemarkModal').modal('toggle');
            }
        } else {
            $('#problem_panel').html(data[0].description);
            var problemAnswerString = '';
            for (var i = 0; i < data[0].problemAnswer.length; i++) {
                problemAnswerString += data[0].problemAnswer[i];
                problemAnswerString += '<br>';
            };
            $('#problem_answer').html(problemAnswerString);
            var answerString = '';
            for (var i = 0; i < data[0].answer.length; i++) {
                answerString += data[0].answer[i];
                answerString += '<br>';
            };
            $('#student_answer').html(answerString);
            $('#full_point_panel').html('本题满分 ' + data[0].fullPoint + ' 分');
            $('#remark_point').val(data[0].point);
            $('#homeworkRemarkModal').modal('toggle');
        }
    };
}

function deleteProblems(id) {
    if (status > 0) {
        alert("该章节作业已经发布，不允许再删除！");
    } else {
        var table = $("#" + id).DataTable();
        var rows = table.rows('.selected').indexes();
        if (rows.length == 0) {
            alert("请选择需要删除的行！");
        } else {
            var data = table.rows(rows).data();
            var deleteIds = [];
            for (var i = 0; i < data.length; i++) {
                deleteIds[i] = data[i].id;
            }
            var r = confirm("删除操作不可恢复，你确定要删除这 " + rows.length + " 个题目吗？");
            if (r == true) {
                var dataString = "deleteIds=" + deleteIds;
                $.ajax({
                    type: "POST",
                    url: "deleteProblem.action",
                    data: dataString,
                    dataType: "json",
                    success: function(data, textStatus, jqXHR) {
                        if (data.success) {
                            alert("题目删除成功！");
                            location.reload();
                        } else {
                            alert(data.message);
                        }
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.log("Something really bad happened " + textStatus);
                        alert("题目删除失败，连接出现问题！");
                    }
                });
            } else {
                return;
            }
        }
    }
}

function publicAnswer() {
    $.ajax({
        type: "POST",
        url: "publicAnswer.action",
        data: "",
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                alert("答案发布成功！");
                location.reload();
            } else {
                alert(data.message);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("答案发布失败，连接出现问题！");
        }
    });
}

function submit_remark() {
    id = 'homeworkRemarkTable';
    var table = $("#" + id).DataTable();
    var data = table.rows(now_rows).data();
    var point = $('#remark_point').val();
    if (point < 0) {
        alert("请手下留情，不要给负分！");
    } else if (point > data[0].fullPoint) {
        alert("请实事求是，不要超过满分！");
    } else if (parseInt(point) != point * 1) {
        alert("请体谅系统，填个整数的分值！");
    } else {
        $.ajax({
            type: "POST",
            url: "submitRemark.action",
            data: "problemId=" + data[0].problemId + "&studentId=" + data[0].studentId + "&point=" + point,
            dataType: "json",
            success: function(data, textStatus, jqXHR) {
                if (data.success) {
                    alert("评分成功！");
                    location.reload();
                } else {
                    alert(data.message);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Something really bad happened " + textStatus);
                alert("评分失败，连接出现问题！");
            }
        });
    }
}

function downloadAnswer() {
    location.href = "downloadAnswer.action";
}
