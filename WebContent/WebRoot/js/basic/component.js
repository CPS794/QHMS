// new a component of sertain type with given value
function getComponent(type, value) {
    var dc = utility.component;

    // clear and start 
    var component = "";
    // open tag
    component += "<";
    component += dc[type].lable;
    component += " ";
    // property in open tag
    for (var i = 0; i < dc[type].property.length; i++) {
        component += dc[type].property[i];
        component += "=\"";
        if (value[dc[type].property[i]] != null) {
            component += value[dc[type].property[i]];
        } else if (dc[type].propertyDefault[dc[type].property[i]] != null) {
            component += dc[type].propertyDefault[dc[type].property[i]];
        } else {
            component += "";
        }
        component += "\" ";
    };
    // 0-1 property in open tag
    for (var i = 0; i < dc[type].tag.length; i++) {
        if (value[dc[type].tag[i]] != null) {
            if (value[dc[type].tag[i]])
                component += dc[type].tag[i];
        } else if (dc[type].tagDefault[dc[type].tag[i]]) {
            component += dc[type].tag[i];
        }
    };
    // end of open tag
    component += ">";

    // inner sub-tag 
    if (dc[type].isGroup) {
        for (var i = 0; i < value.array.length; i++) {
            // divider between sub-tags
            if (i > 0) {
                component += dc[type].divider;
            }
            // start of sub-tag
            // open tag of sub-tag
            component += "<";
            component += dc[type].innerOpen;
            // property in sub-tag
            for (var j = 0; j < dc[type].innerProperty.length; j++) {
                component += " ";
                component += dc[type].innerProperty[j];
                component += "=\"";
                if (value.inner[dc[type].innerProperty[j]] != null) {
                    component += value.inner[dc[type].innerProperty[j]];
                } else if (dc[type].innerPropertyDefault[dc[type].innerProperty[j]] != null) {
                    component += dc[type].innerPropertyDefault[dc[type].innerProperty[j]];
                } else {
                    component += "";
                }
                component += "\"";
            };
            if (value.array[i].key != null) {
                component += " value=\"" + value.array[i].key + "\" ";
            }
            // 0-1 property in sub-tag
            for (var j = 0; j < dc[type].innerTag.length; j++) {
                if (value.array[i][dc[type].innerTag[j]]) {
                    component += " " + dc[type].innerTag[j];
                }
            };
            // end of open tag in sub-tag
            component += ">";
            component += value.array[i].value;
            component += "<";
            component += dc[type].innerClose;
            component += ">";
        };
    }

    if (dc[type].close) {
        component += value.value;
        component += "</" + dc[type].lable + ">";
    }
    return component;
}

// bound with getComponent to get DateTimePicker
function initComponent() {
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayBtn: true
    });
    $(".form_date").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 2,
        autoclose: true,
        todayBtn: true
    });
    $(".form_time").datetimepicker({
        format: 'hh:ii',
        startView: 1,
        minView: 0,
        maxView: 1,
        minuteStep: 1,
        autoclose: true,
        todayBtn: true
    });
}

// Styles of Problem adding Page (*6)
function getContentStyle1(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-md-2">';
    content += '<label class="control-label">答案：</label>';
    content += '</div>';
    content += '<div class="col-md-10 form-inline">';
    content += '<div class="radio" id="' + targetId + '_answer_1" name="">';
    content += '<label>';
    content += '<input type="radio" id="' + targetId + '_answer_1_1_1" name="' + targetId + '_answer_1" class="" value="true">正确</label>';
    content += '<br>';
    content += '<label>';
    content += '<input type="radio" id="' + targetId + '_answer_1_1_2" name="' + targetId + '_answer_1" class="" value="false">错误</label>';
    content += '</div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function getContentStyle2(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-md-2">';
    content += '<label class="control-label">选项及答案：</label>';
    content += '</div>';
    content += '<div class="col-md-10 form-inline">';
    content += '<div class="radio" id="' + targetId + '_answer_2" name="">';
    content += '<div class="form-group col-md-6">';
    content += '<label>';
    content += '<input type="radio" id="' + targetId + '_answer_2_1_1" name="' + targetId + '_answer_2" class="" value="A">A.';
    content += '</label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_2_1_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label>';
    content += '<input type="radio" id="' + targetId + '_answer_2_2_1" name="' + targetId + '_answer_2" class="" value="B">B.';
    content += '</label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_2_2_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label>';
    content += '<input type="radio" id="' + targetId + '_answer_2_3_1" name="' + targetId + '_answer_2" class="" value="C">C.';
    content += '</label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_2_3_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label>';
    content += '<input type="radio" id="' + targetId + '_answer_2_4_1" name="' + targetId + '_answer_2" class="" value="D">D.';
    content += '</label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_2_4_2" value="">';
    content += '</div>';
    content += '</div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function getContentStyle3(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-md-2">';
    content += '<label class="control-label">选项及答案：</label>';
    content += '</div>';
    content += '<div class="col-md-10 form-inline">';
    content += '<div class="checkbox" id="' + targetId + '_answer_3" name="">';
    content += '<div class="form-group col-md-6">';
    content += '<label>';
    content += '<input type="checkbox" id="' + targetId + '_answer_3_1_1" name="' + targetId + '_answer_3" class="" value="A">A.';
    content += '</label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_3_1_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label>';
    content += '<input type="checkbox" id="' + targetId + '_answer_3_2_1" name="' + targetId + '_answer_3" class="" value="B">B.';
    content += '</label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_3_2_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label>';
    content += '<input type="checkbox" id="' + targetId + '_answer_3_3_1" name="' + targetId + '_answer_3" class="" value="C">C.';
    content += '</label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_3_3_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label>';
    content += '<input type="checkbox" id="' + targetId + '_answer_3_4_1" name="' + targetId + '_answer_3" class="" value="D">D.';
    content += '</label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_3_4_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label>';
    content += '<input type="checkbox" id="' + targetId + '_answer_3_5_1" name="' + targetId + '_answer_3" class="" value="E">E.';
    content += '</label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_3_5_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label>';
    content += '<input type="checkbox" id="' + targetId + '_answer_3_6_1" name="' + targetId + '_answer_3" class="" value="F">F.';
    content += '</label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_3_6_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label>';
    content += '<input type="checkbox" id="' + targetId + '_answer_3_7_1" name="' + targetId + '_answer_3" class="" value="G">G.';
    content += '</label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_3_7_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label>';
    content += '<input type="checkbox" id="' + targetId + '_answer_3_8_1" name="' + targetId + '_answer_3" class="" value="H">H.';
    content += '</label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_3_8_2" value="">';
    content += '</div>';
    content += '</div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function getContentStyle4(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-md-2">';
    content += '<label class="control-label">答案 （多余部分留空）：</label>';
    content += '</div>';
    content += '<div class="col-md-10 form-inline">';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_1">1. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_1" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_2">2. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_3">3. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_3" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_4">4. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_4" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_5">5. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_5" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_6">6. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_6" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_7">7. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_7" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_8">8. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_8" value="">';
    content += '</div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function getContentStyle5(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-md-2">';
    content += '<label class="control-label">答案：</label>';
    content += '</div>';
    content += '<div class="col-md-10 form-inline">';
    content += '<textarea id="' + targetId + '_answer_5" name="' + targetId + '_answer_5" style="width:80%" rows="3"></textarea>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function getContentStyle6(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-md-1">';
    content += '<label class="control-label" for="' + targetId + '_answer_6">答案：</label>';
    content += '</div>';
    content += '<div class="col-md-11">';
    content += '<div id="' + targetId + '_answer_6"></div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
    summernoteSetting = utility.defaultSummernoteSetting;
    summernoteSetting.callbacks = {
        onImageUpload: function(files, editor, $editable) {
            sendFile(files, editor, $editable, targetId);
        }
    }
    $('#' + targetId + '_answer_6').summernote(utility.summernoteSetting);
}

// Default Options of Problem adding Page (*6)
function getDefaultOption1(vArgument) {
    var result = [];
    return result;
}

function getDefaultOption2(vArgument) {
    targetId = vArgument[0];
    var result = [];
    for (var i = 0; i < 4; i++) {
        result[i] = $("#" + targetId + "_answer_2_" + (i + 1) + "_2").val();
    };
    return result;
}

function getDefaultOption3(vArgument) {
    targetId = vArgument[0];
    var result = [];
    for (var i = 0; i < 8; i++) {
        var tempOption = $("#" + targetId + "_answer_3_" + (i + 1) + "_2").val();
        if (tempOption != "") {
            result[i] = tempOption;
        } else {
            break;
        }
    };
    return result;
}

function getDefaultOption4(vArgument) {
    var result = [];
    return result;
}

function getDefaultOption5(vArgument) {
    var result = [];
    return result;
}

function getDefaultOption6(vArgument) {
    var result = [];
    return result;
}

// Default Answers of Problem adding Page (*6)
function getDefaultAnswer1(vArgument) {
    targetId = vArgument[0];
    var result = [];
    result[0] = "";
    result[0] = $('input[name="' + targetId + '_answer_1"]:checked').val();
    return result;
}

function getDefaultAnswer2(vArgument) {
    targetId = vArgument[0];
    var result = [];
    result[0] = "";
    result[0] = $('input[name="' + targetId + '_answer_2"]:checked').val();
    return result;
}

function getDefaultAnswer3(vArgument) {
    targetId = vArgument[0];
    var result = [];
    result[0] = "";
    $('input[name="' + targetId + '_answer_3"]:checked').each(function() {
        result[0] += $(this).val();
    })
    return result;
}

function getDefaultAnswer4(vArgument) {
    targetId = vArgument[0];
    var result = [];
    for (var i = 0; i < 8; i++) {
        var tempOption = $("#" + targetId + "_answer_4_" + (i + 1)).val();
        if (tempOption != "") {
            result[i] = tempOption;
        } else {
            break;
        }
    };
    return result;
}

function getDefaultAnswer5(vArgument) {
    targetId = vArgument[0];
    var result = [];
    result[0] = $("#" + targetId + "_answer_5").val();
    return result;
}

function getDefaultAnswer6(vArgument) {
    targetId = vArgument[0];
    var result = [];
    result[0] = $('#' + targetId + '_answer_6').summernote('code');
    return result;
}

// Set Options of Problem modifying Page (*6)
function setOption1(vArgument) {}

function setOption2(vArgument) {
    targetId = vArgument[0];
    options = vArgument[1];
    for (var i = 0; i < 4; i++) {
        $("#" + targetId + "_answer_2_" + (i + 1) + "_2").val(options[i]);
    };
}

function setOption3(vArgument) {
    targetId = vArgument[0];
    options = vArgument[1];
    for (var i = 0; i < options.length; i++) {
        $("#" + targetId + "_answer_3_" + (i + 1) + "_2").val(options[i]);
    };
}

function setOption4(vArgument) {}

function setOption5(vArgument) {}

function setOption6(vArgument) {}


// Set Answers of Problem modifying Page (*6)
function setAnswer1(vArgument) {
    targetId = vArgument[0];
    var answers = vArgument[1];
    $('input[name="' + targetId + '_answer_1"][value=' + answers[0] + ']').attr("checked", true);
}

function setAnswer2(vArgument) {
    targetId = vArgument[0];
    var answers = vArgument[1];
    $('input[name="' + targetId + '_answer_2"][value=' + answers[0] + ']').attr("checked", true);
}

function setAnswer3(vArgument) {
    targetId = vArgument[0];
    var answers = vArgument[1];
    for (var i = 0; i < answers[0].length; i++) {
        $('input[name="' + targetId + '_answer_3"][value=' + answers[0][i] + ']').attr("checked", true);
    };
}

function setAnswer4(vArgument) {
    targetId = vArgument[0];
    var answers = vArgument[1];
    for (var i = 0; i < answers.length; i++) {
        $("#" + targetId + "_answer_4_" + (i + 1)).val(answers[i]);
    };
}

function setAnswer5(vArgument) {
    targetId = vArgument[0];
    var answers = vArgument[1];
    $("#" + targetId + "_answer_5").val(answers[0]);
}

function setAnswer6(vArgument) {
    targetId = vArgument[0];
    var answers = vArgument[1];
    $('#' + targetId + '_answer_6').summernote('code', answers[0]);
}

// Show Options of Homework Page (*6)
function showOption1(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-xs-1"></div>'
    content += '<div class="form-inline col-xs-10">';
    content += '<div class="radio" id="' + targetId + '_answer_1" name="">';
    content += '<label>';
    content += '<input type="radio" id="' + targetId + '_answer_1_1_1" name="' + targetId + '_answer_1" class="" value="true">正确</label>';
    content += '<br>';
    content += '<label>';
    content += '<input type="radio" id="' + targetId + '_answer_1_1_2" name="' + targetId + '_answer_1" class="" value="false">错误</label>';
    content += '</div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function showOption2(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    options = vArgument[2];
    keys = ['A', 'B', 'C', 'D'];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-xs-1"></div>'
    content += '<div class="col-xs-10 form-inline">';
    content += '<div class="radio" id="' + targetId + '_answer_2" name="">';
    for (var i = 0; i < 4; i++) {
        content += '<label>';
        content += '<input type="radio" id="' + targetId + '_answer_2_' + (i + 1) + '_1" name="' + targetId + '_answer_2" class="" value="' + keys[i] + '">' + options[i] + '</label>';
        content += '<br>';
    };

    content += '</div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function showOption3(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    options = vArgument[2];
    keys = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-xs-1"></div>'
    content += '<div class="col-xs-10 form-inline">';
    content += '<div class="radio" id="' + targetId + '_answer_2" name="">';
    for (var i = 0; i < options.length; i++) {
        content += '<label>';
        content += '<input type="checkbox" id="' + targetId + '_answer_3_' + (i + 1) + '_1" name="' + targetId + '_answer_3" class="" value="' + keys[i] + '">' + options[i] + '</label>';
        content += '<br>';
    };
    content += '</div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function showOption4(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-md-2">';
    content += '<label class="control-label">填空题 （多余部分留空）：</label>';
    content += '</div>';
    content += '<div class="col-md-10 form-inline">';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_1">1. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_1" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_2">2. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_3">3. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_3" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_4">4. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_4" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_5">5. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_5" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_6">6. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_6" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_7">7. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_7" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_8">8. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_8" value="">';
    content += '</div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function showOption5(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-md-2">';
    content += '<label class="control-label">答案：</label>';
    content += '</div>';
    content += '<div class="col-md-10 form-inline">';
    content += '<textarea id="' + targetId + '_answer_5" name="' + targetId + '_answer_5" style="width:80%" rows="3"></textarea>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function showOption6(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-md-2">';
    content += '<label class="control-label" for="' + targetId + '_answer_6">答案：</label>';
    content += '</div>';
    content += '<div class="col-md-10">';
    content += '<div id="' + targetId + '_answer_6"></div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
    summernoteSetting = utility.defaultSummernoteSetting;
    summernoteSetting.callbacks = {
        onImageUpload: function(files, editor, $editable) {
            sendFile(files, editor, $editable, targetId);
        }
    }
    $('#' + targetId + '_answer_6').summernote(utility.summernoteSetting);
}

// Show Plain Options of Homework Page (*6)
function getPlainOption1(vArgument) {
    var content = "";
    return content;
}

function getPlainOption2(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    options = vArgument[2];
    keys = ['A', 'B', 'C', 'D'];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-xs-1"></div>'
    content += '<div class="col-xs-10 form-inline">';
    content += '<div class="radio" id="' + targetId + '_answer_2" name="">';
    for (var i = 0; i < 4; i++) {
        content += '<label>';
        content += '<input type="radio" id="' + targetId + '_answer_2_' + (i + 1) + '_1" name="' + targetId + '_answer_2" class="" value="' + keys[i] + '">' + options[i] + '</label>';
        content += '<br>';
    };

    content += '</div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function getPlainOption3(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    options = vArgument[2];
    keys = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-xs-1"></div>'
    content += '<div class="col-xs-10 form-inline">';
    content += '<div class="radio" id="' + targetId + '_answer_2" name="">';
    for (var i = 0; i < options.length; i++) {
        content += '<label>';
        content += '<input type="checkbox" id="' + targetId + '_answer_3_' + (i + 1) + '_1" name="' + targetId + '_answer_3" class="" value="' + keys[i] + '">' + options[i] + '</label>';
        content += '<br>';
    };
    content += '</div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function getPlainOption4(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-md-2">';
    content += '<label class="control-label">填空题 （多余部分留空）：</label>';
    content += '</div>';
    content += '<div class="col-md-10 form-inline">';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_1">1. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_1" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_2">2. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_2" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_3">3. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_3" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_4">4. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_4" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_5">5. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_5" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_6">6. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_6" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_7">7. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_7" value="">';
    content += '</div>';
    content += '<div class="form-group col-md-6">';
    content += '<label class="control-label" for="' + targetId + '_answer_4_8">8. </label>';
    content += '<input type="text" class="form-control" style="width: 80%" placeholder="" id="' + targetId + '_answer_4_8" value="">';
    content += '</div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function getPlainOption5(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-md-2">';
    content += '<label class="control-label">答案：</label>';
    content += '</div>';
    content += '<div class="col-md-10 form-inline">';
    content += '<textarea id="' + targetId + '_answer_5" name="' + targetId + '_answer_5" style="width:80%" rows="3"></textarea>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
}

function getPlainOption6(vArgument) {
    parentId = vArgument[0];
    targetId = vArgument[1];
    var content = "";
    content += '<div class="row">';
    content += '<div class="col-md-2">';
    content += '<label class="control-label" for="' + targetId + '_answer_6">答案：</label>';
    content += '</div>';
    content += '<div class="col-md-10">';
    content += '<div id="' + targetId + '_answer_6"></div>';
    content += '</div>';
    content += '</div>';
    $("#" + parentId).html(content);
    summernoteSetting = utility.defaultSummernoteSetting;
    summernoteSetting.callbacks = {
        onImageUpload: function(files, editor, $editable) {
            sendFile(files, editor, $editable, targetId);
        }
    }
    $('#' + targetId + '_answer_6').summernote(utility.summernoteSetting);
}

function doFunction(fnFunction, vArgument) {
    fnFunction(vArgument);
}

function getFunction(fnFunction, vArgument) {
    return fnFunction(vArgument);
}

function sendFile(files, editor, $editable, id) {
    //这里files是因为我设置了可上传多张图片，所以需要依次添加到formData中
    var hideForm = $('#UploadForm');
    var formData = new FormData();
    for (f in files) {
        formData.append("file", files[f]);
    }

    $.ajax({
        data: formData,
        type: "POST",
        url: "ImageUpload",
        cache: false,
        contentType: false,
        processData: false,
        success: function(data) {
            data = JSON.parse(data);
            var imageUrl = data.ImageURLs;
            console.log(data);
            for (i in imageUrl) {
                // alert(imageUrl[i]);
                $('#' + id).summernote('editor.insertImage', imageUrl[i]);
            }

        },
        error: function() {
            console.log("uploadError");
        }
    })
}
