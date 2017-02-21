var dataSet = {};
var chapters = [];
var passwordTip = ""; //tip中显示的内容跨越弹窗存在，所以必须设置成全局变量

$(document).ready(function() {
    dataSet.dataColumn = [{
        data: "id",
        title: "题号",
        width: "6%",
        component: "textbox",
        readonly: true
    }, {
        data: "typeId",
        title: "题型",
        width: "6%",
        component: "textbox",
        readonly: false
    }, {
        data: "chapterId",
        title: "章节",
        width: "6%",
        component: "textbox",
        readonly: false
    }, {
        data: "author_fullname",
        title: "出题人",
        width: "16%",
        component: "textbox",
        readonly: true
    }, {
        data: "description",
        title: "题干",
        width: "60%",
        component: "textbox",
        readonly: false
    }, {
        data: "point",
        title: "分值",
        width: "6%",
        component: "textbox",
        readonly: false
    }];
    getProblemList();
});

function getProblemList() {
    $.ajax({
        type: "POST",
        url: "listProblems.action",
        data: "",
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                dataSet.data = data.problems;
                for (var i = 0; i < dataSet.data.length; i++) {
                    dataSet.data[i].description = dataSet.data[i].description
                        .replace(/<\/p>/gi, "\n")
                        .replace(/<br\/?>/gi, "\n")
                        .replace(/<\/?[^>]+(>|$)/g, "")
                };
                initDataTable("problemManageTable", dataSet);
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
            style: 'multi'
        },
        dom: 'Bfrt<"col-md-4"l><"col-md-4"i><"col-md-4"p>',
        buttons: [{
            extend: 'colvis',
            columns: ':not(:first-child)'
        }, {
            text: '添加题目',
            action: function(e, dt, node, config) {
                showAddProblem();
            }
        }, {
            text: '修改题目',
            action: function(e, dt, node, config) {
                showModifyProblem(id);
            }
        }, {
            text: '删除题目',
            action: function(e, dt, node, config) {
                deleteProblems(id);
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

function showAddProblem() {
    window.location = "addProblemPage.action";
}

function showModifyProblem(id) {
    if (checkSingleSelect(id)) {
        var table = $("#" + id).DataTable();
        var rows = table.rows('.selected').indexes();
        var data = table.rows(rows).data();
        window.location = "modifyProblemPage.action?problemId=" + data[0].id;
    }
}

function deleteProblems(id) {
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
