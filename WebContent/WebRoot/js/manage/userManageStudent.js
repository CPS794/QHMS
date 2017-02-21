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
    }, {
        data: "group",
        title: "分组",
        component: "textbox",
        readonly: true
    }];
    getUserList(Enum_UserType.student);
});

function getUserList(userType) {
    var url = AntiEnum_UserType[userType] + "List.action";

    $.ajax({
        type: "POST",
        url: url,
        data: "",
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                dataSet.data = data.users;
                chapters = data.chapters;
                // alert("连上了");
                initDataTable("userManageTable", dataSet);
                initDoubleClick("userManageTable", dataSet);
            } else {
                alert("连上了但是没有看到success");
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
            text: '修改密码',
            action: function(e, dt, node, config) {
                if (checkSingleSelect(id)) {
                    // alert('修改密码！');
                    showModifyPassword(id);
                }
            }
        }, {
            text: '新增用户',
            action: function(e, dt, node, config) {
                // alert('新增用户！');
                showAddUser();
            }
        }, {
            text: '删除用户',
            action: function(e, dt, node, config) {
                // alert('删除用户！');
                deleteUsers(id);
            }
        }, {
            text: '设置分组',
            action: function(e, dt, node, config) {
                showSetGroupWindow(id);
                // alert('批量导入！');
            }
        }, {
            text: '批量导入',
            action: function(e, dt, node, config) {
                showFileUploadWindow();
                // alert('批量导入！');
            }
        }],
        data: dataSet.data,
        columns: dataSet.dataColumn
    });
}

function getContent(data, index) {
    // default component
    var component = {
        "array": [],
        "value": "",
        "inner": {}
    };

    component.id = "DataTableModify_" + dataSet.dataColumn[index].data;
    component.value = data[dataSet.dataColumn[index].data];
    if (dataSet.dataColumn[index].readonly) {
        component.disabled = true;
    }
    if (dataSet.dataColumn[index].component == "select" || dataSet.dataColumn[index].component == "multiple_select") {
        component.array = dataSet.map[dataSet.dataColumn[index].source];
        for (var i = 0; i < component.array.length; i++) {
            if (component.array[i].value == component.value) {
                component.array[i].selected = true;
            } else {
                component.array[i].selected = false;
            }
        };
    }
    // console.log(component);
    return component;
}

function initDoubleClick(id, dataSet) {
    var table = $("#" + id).DataTable();
    $("#" + id + " tbody").on('dblclick', 'tr', function() {
        var data = table.row(this).data();
        $(this).toggleClass('selected');
        var modifyDialogBody = "";
        for (var i = 0; i < dataSet.dataColumn.length; i++) {
            // open tag of a row
            modifyDialogBody += "<div class='row'>";
            // lable
            modifyDialogBody += "<label class=\"control-label col-xs-4 col-md-4 text-right\">" + dataSet.dataColumn[i].title + "</label>";
            // open tag of content
            modifyDialogBody += "<div class=\"col-xs-8 col-md-8\">";
            // content body
            // modifyDialogBody += "<input type=\"text\" class=\"form-control\" id=\"DataTableModify_" + dataSet.dataColumn[i].data + "\" value=\"" + data[dataSet.dataColumn[i].data] + "\"" + (dataSet.dataColumn[i].readonly ? " readonly=\"readonly\"" : "") + ">";
            modifyDialogBody += getComponent(dataSet.dataColumn[i].component, getContent(data, i));
            // close tag of content
            modifyDialogBody += "</div>";
            // close tag of a row
            modifyDialogBody += "</div>";
        };
        bootbox.dialog({
            title: "编辑内容",
            message: modifyDialogBody,
            buttons: {
                success: {
                    label: "确定修改",
                    className: "btn-primary",
                    callback: function() {
                        var modifyResult = {};
                        for (var i = 0; i < dataSet.dataColumn.length; i++) {
                            modifyResult[dataSet.dataColumn[i].data] = $("#DataTableModify_" + dataSet.dataColumn[i].data).val();
                        }
                        datatableModifySubmit(modifyResult);
                    }
                },
                failed: {
                    label: "取消修改",
                    className: "btn-danger",
                    callback: function() {
                        datatableModifyCancel(id, dataSet);
                    }
                }
            }
        });
        initComponent();

    });
}

function datatableModifySubmit(modifyResult) {
    console.log(modifyResult);

    var dataString = "userId=" + modifyResult.id + "&userName=" + modifyResult.name + "&studentClass=" + modifyResult.studentClass + "&userType=" + Enum_UserType.student;
    $.ajax({
        type: "POST",
        url: "modifyUser.action",
        data: dataString,
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                alert("用户修改成功！");
                location.reload();
            } else {
                alert(data.message);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("用户修改失败，连接出现问题！");
        }
    });
}

function datatableModifyCancel(id, dataSet) {
    var table = $("#" + id).DataTable();
    table.rows().deselect();
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

function showModifyPassword(id) {
    var table = $("#" + id).DataTable();
    var rows = table.rows('.selected').indexes();
    var data = table.rows(rows).data();
    var textBefore = '<div class="row"><div class="col-md-12"><form class="form-horizontal"><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="DataTableModifyPassword_id">学号：</label><div class="col-md-5"><input type="text" class="form-control" placeholder="" id="DataTableModifyPassword_id" value="' + data[0].id + '" disabled=""></div></div><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="DataTableModifyPassword_name">姓名：</label><div class="col-md-5"><input type="text" class="form-control" placeholder="" id="DataTableModifyPassword_name" value="' + data[0].name + '" disabled=""></div></div><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="DataTableModifyPassword_studentClass">班级：</label><div class="col-md-5"><input type="text" class="form-control" placeholder="" id="DataTableModifyPassword_studentClass" value="' + data[0].studentClass + '" disabled=""></div></div><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="newPassword">请输入新密码：</label><div class="col-md-5"><input id="newPassword" name="newPassword" type="password" placeholder="新密码" class="form-control input-md"></div></div><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="confirmPassword">请确认新密码：</label><div class="col-md-5"><input id="confirmPassword" name="confirmPassword" type="password" placeholder="再次输入新密码" class="form-control input-md"><span class="help-block" id="passwordNotSame">';
    var textAfter = '</span></div></div></form></div></div>';
    var url = menu.menuJson.systemMenu[0].subMenu[1].url;

    bootbox.dialog({
        title: "修改" + data[0].name + "的密码",
        message: textBefore + passwordTip + textAfter,
        buttons: {
            success: {
                label: "确定修改",
                className: "btn-primary",
                callback: function() {
                    if ($("#newPassword").val() == $("#confirmPassword").val()) {
                        modifyStudentPassword(data[0].id, data[0].type, $("#newPassword").val(), id);
                    } else {
                        passwordTip = "两次输入的新密码不一致！";
                        showModifyPassword(id);
                    }
                }
            },
            failed: {
                label: "取消修改",
                className: "btn-danger",
                callback: function() {}
            }
        }
    });
}

function modifyStudentPassword(userId, userType, newPwd, id) {
    passwordTip = "";
    newPwd = cal_md5(newPwd);
    var dataString = "userId=" + userId + "&userType=" + userType + "&newPassword=" + newPwd;
    $.ajax({
        type: "POST",
        url: "passwordManage.action",
        data: dataString,
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                alert("密码修改成功！");
            } else {
                passwordTip = data.message;
                showModifyPassword(id);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("修改失败，连接出现问题！");
        }
    });
}

function showAddUser() {
    var textBefore = '<div class="row"><div class="col-md-12"><form class="form-horizontal"><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="DataTableAddUser_id">学号：</label><div class="col-md-5"><input type="text" class="form-control" placeholder="" id="DataTableAddUser_id" value=""></div></div><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="DataTableAddUser_name">姓名：</label><div class="col-md-5"><input type="text" class="form-control" placeholder="" id="DataTableAddUser_name" value=""></div></div><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="DataTableAddUser_studentClass">班级：</label><div class="col-md-5"><input type="text" class="form-control" placeholder="" id="DataTableAddUser_studentClass" value=""></div></div><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="newPassword">请输入新密码：</label><div class="col-md-5"><input id="newPassword" name="newPassword" type="password" placeholder="新密码" class="form-control input-md"></div></div><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="confirmPassword">请确认新密码：</label><div class="col-md-5"><input id="confirmPassword" name="confirmPassword" type="password" placeholder="再次输入新密码" class="form-control input-md"><span class="help-block" id="passwordNotSame">';
    var textAfter = '</span></div></div></form></div></div>';
    var url = menu.menuJson.systemMenu[0].subMenu[1].url;

    bootbox.dialog({
        title: "新增用户",
        message: textBefore + passwordTip + textAfter,
        buttons: {
            success: {
                label: "确定",
                className: "btn-primary",
                callback: function() {
                    if ($("#newPassword").val() == $("#confirmPassword").val()) {
                        addUser($("#DataTableAddUser_id").val(), $("#DataTableAddUser_name").val(), $("#DataTableAddUser_studentClass").val(), Enum_UserType.student, $("#newPassword").val());
                    } else {
                        passwordTip = "两次输入的密码不一致！";
                        showAddUser();
                    }
                }
            },
            failed: {
                label: "取消",
                className: "btn-danger",
                callback: function() {}
            }
        }
    });
}

function addUser(userId, userName, studentClass, userType, password) {
    passwordTip = "";
    password = cal_md5(password);
    var dataString = "userId=" + userId + "&userName=" + userName + "&userType=" + userType + "&studentClass=" + studentClass + "&password=" + password;
    $.ajax({
        type: "POST",
        url: "addUser.action",
        data: dataString,
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                alert("用户添加成功！");
                location.reload();
            } else {
                passwordTip = data.message;
                showAddUser();
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("用户添加失败，连接出现问题！");
        }
    });
}

function deleteUsers(id) {
    var table = $("#" + id).DataTable();
    var rows = table.rows('.selected').indexes();
    if (rows.length == 0) {
        alert("请选择需要删除的行！");
    } else {
        var data = table.rows(rows).data();
        var deleteIds = [];
        for (var i = 0; i < data.length; i++) {
            if (data[i].id == $("#hiddenNumber").val()) {
                alert("不能删除自己！");
                return;
            }
            deleteIds[i] = data[i].id;
        }
        var r = confirm("删除操作不可恢复，你确定要删除这 " + rows.length + " 个用户吗？");
        if (r == true) {
            var dataString = "userType=" + Enum_UserType.student + "&deleteIds=" + deleteIds;
            $.ajax({
                type: "POST",
                url: "deleteUser.action",
                data: dataString,
                dataType: "json",
                success: function(data, textStatus, jqXHR) {
                    if (data.success) {
                        alert("用户删除成功！");
                        location.reload();
                    } else {
                        alert(data.message);
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("Something really bad happened " + textStatus);
                    alert("用户删除失败，连接出现问题！");
                }
            });
        } else {
            return;
        }
    }
}

function showSetGroupWindow(id) {
    var table = $("#" + id).DataTable();
    var rows = table.rows('.selected').indexes();
    if (rows.length == 0) {
        alert("请选择需要是设置分组的同学！");
    } else {
        var data = table.rows(rows).data();
        var groupedIds = [];
        for (var i = 0; i < data.length; i++) {
            groupedIds[i] = data[i].id;
        }
    }
    var component = {
        "array": [],
        "value": "",
        "inner": {}
    };
    component.id = "chapterSelect";
    component.array = chapters;
    var textBefore = '<div class="row"><div class="col-md-12"><form class="form-horizontal"><h3>已选择 ' + groupedIds.length + ' 位同学</h3><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label">设置分组：</label><div class="col-md-5">';
    var textAfter = '</div></div></form></div></div>';

    bootbox.dialog({
        title: "设置分组",
        message: textBefore + getComponent("select",component) + textAfter,
        buttons: {
            success: {
                label: "确定",
                className: "btn-primary",
                callback: function() {
                    $.ajax({
                        type: "POST",
                        url: "setGroup.action",
                        data: "studentIds="+groupedIds+"&group="+$("#chapterSelect").val(),
                        dataType: "json",
                        success: function(data, textStatus, jqXHR) {
                            if (data.success) {
                                alert("分组设置成功！");
                                location.reload();
                            } else {
                                alert(data.message);
                            }
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.log("Something really bad happened " + textStatus);
                            alert("分组设置失败，连接出现问题！");
                        }
                    });
                }
            },
            failed: {
                label: "取消",
                className: "btn-danger",
                callback: function() {}
            }
        }
    });
}

function afterUpload() {
    // alert("Finished!");
    $('#myModal').modal('toggle');
    $.ajax({
        type: "POST",
        url: "matchColumnStudent.action",
        data: "",
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                showColumnMatch(data.columns, data.candidate);
            } else {
                alert("Failed!");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("用户批量导入失败，连接出现问题！");
        }
    });
}

function showColumnMatch(columns, candidate) {
    var textString = "";
    var textBefore = '<div class="row"><div class="col-md-12"><form class="form-horizontal">';
    var textAfter = '</form></div></div>';
    var textIn1 = '<div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="columnMatchSelect_';
    var textIn2 = '">';
    var textIn3 = '</label><div class="col-md-5">';
    var textIn4 = '</div></div>';

    var component = {
        "array": [],
        "value": "",
        "inner": {}
    };
    for (var i = 0; i < candidate.length; i++) {
        component.array[i] = {};
        component.array[i].key = candidate[i];
        component.array[i].value = candidate[i];
    };

    console.log(columns);
    textString += textBefore;
    for (var i = 0; i < columns.length; i++) {
        textString += textIn1;
        textString += columns[i].id;
        textString += textIn2;
        textString += columns[i].displayName + "：";
        textString += textIn3;
        component.id = "columnMatchSelect_" + columns[i].id;
        textString += getComponent("select", component);
        textString += textIn4;
    };
    textString += textAfter;

    bootbox.dialog({
        title: "表格列与数据项匹配",
        message: textString,
        buttons: {
            success: {
                label: "确定",
                className: "btn-primary",
                callback: function() {
                    // alert("OK！");
                    for (var i = 0; i < columns.length; i++) {
                        columns[i].columnInExcel = $('#columnMatchSelect_' + columns[i].id).val();
                    };
                    console.log(columns);
                    importStudent(columns);
                }
            },
            failed: {
                label: "取消",
                className: "btn-danger",
                callback: function() {}
            }
        }
    });
}

function importStudent(columns) {
    $.ajax({
        type: "POST",
        url: "importStudent.action",
        data: "columns=" + JSON.stringify(columns),
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                alert("批量导入 " + data.count + " 个用户成功！");
                location.reload();
            } else {
                alert("用户批量导入失败！" + data.message);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("用户批量导入失败，连接出现问题！");
        }
    });
}
