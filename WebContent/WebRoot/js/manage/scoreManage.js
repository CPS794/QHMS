var dataSet = {};

$(document).ready(function() {
    dataSet.dataColumn = [{
        title: "学号",
        component: "textbox",
        readonly: true
    }, {
        title: "姓名",
        component: "textbox",
        readonly: false
    }, {
        title: "班级",
        component: "textbox",
        readonly: false
    }, {
        title: "分组",
        component: "textbox",
        readonly: false
    }];
    scoreList();
});

function scoreList() {
    var url = "scoreList.action";

    $.ajax({
        type: "POST",
        url: url,
        data: "",
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                // alert("连上了");
                for (var i = 0; i < data.chapters.length; i++) {
                    var chapterId = {
                        title: data.chapters[i].chineseId
                    };
                    // alert(data.chapters[i].chineseId);
                    dataSet.dataColumn.push(chapterId);
                };
                dataSet.dataColumn.push({ title: "平均分" });
                dataSet.data = data.grades;
                dataSet.chapters = data.chapters;
                initScoreHref();
                initDataTable("scoreManageTable", dataSet);
            } else {
                alert(data.message);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("查询失败，连接出现问题！");
        }
    });
}

function initScoreHref() {
    for (var i = 0; i < dataSet.data.length; i++) {
        for (var j = 4; j < dataSet.data[i].length - 1; j++) {
            if (dataSet.data[i][j] != '出题组' && dataSet.data[i][j] >= 0 && dataSet.data[i][j] <= 100) {
                dataSet.data[i][j] = '<a href=showAnswer.action?student=' + dataSet.data[i][0] + '&chapter=' + dataSet.chapters[j - 4].id + '>' + dataSet.data[i][j] + '</a>';
            }
        };
    };
}

function initDataTable(id, dataSet) {
    $("#" + id).DataTable({
        fixedHeader: true,
        responsive: true,
        dom: 'Bfrt<"col-md-4"l><"col-md-4"i><"col-md-4"p>',
        buttons: [{
            extend: 'colvis',
            columns: ':not(:first-child)'
        }, {
            text: '成绩导出',
            action: function(e, dt, node, config) {
                downloadScore();
                // alert('批量导入！');
            }
        }],
        data: dataSet.data,
        columns: dataSet.dataColumn
    });
}

function downloadScore () {
    location.href = "downloadGrade.action";
}