var types = [];
var anti_types = [];

$(document).ready(function() {
    summernoteSetting=utility.defaultSummernoteSetting;
    summernoteSetting.callbacks={
        onImageUpload: function(files, editor, $editable) {
            sendFile(files,editor,$editable,"AddProblemDescription");
        }
    }
    $('#AddProblemDescription').summernote(summernoteSetting);
    initPage();
});

function initPage() {
    $.ajax({
        type: "POST",
        url: "addProblemPageInit.action",
        data: "",
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                types = data.types;
                for (var i = 0; i < types.length; i++) {
                    anti_types[types[i].id] = i;
                };
                setChapter(data.chapters);
                setProblemType(data.problemTypes);
            } else {
                alert(data.message);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("获取信息失败，连接出现问题！");
        }
    });
}

function setChapter(chapters) {
    var component = {
        "array": [],
        "value": "",
        "inner": {}
    };
    component.id = "AddProblemChapter";
    component.name = "AddProblemChapter";
    component.array = chapters;
    $("#AddProblemChapter_div").append(getComponent("select", component));
}

function setProblemType(problemType) {
    var component = {
        "array": [],
        "value": "",
        "inner": {}
    };
    component.id = "AddProblemType";
    component.name = "AddProblemType";
    component.array = problemType;
    component.onchange = "setDisplayArea(anti_types[$('#AddProblemType').val()])";
    $("#AddProblemType_div").append(getComponent("select", component));
    setDisplayArea(anti_types[$('#AddProblemType').val()]);
}

function setDisplayArea(targetType) {
    var targetIds = ["targetArea", "target_" + types[targetType].id];
    doFunction(eval("getContentStyle" + types[targetType].id), targetIds);
    $("#AddProblemPoint").val(types[targetType].point);
}

function previousPage() {
    window.location = "problemManage.action";
}

function submitProblem() {
    var nowType = anti_types[$('#AddProblemType').val()];
    var targetId = ["target_" + types[nowType].id];
    var problem = {};
    problem.typeId = $('#AddProblemType').val();
    problem.chapterId = $("#AddProblemChapter").val();
    problem.description = $("#AddProblemDescription").summernote("code");
    problem.option = JSON.stringify(getFunction(eval("getDefaultOption" + types[nowType].id), targetId));
    problem.answer = JSON.stringify(getFunction(eval("getDefaultAnswer" + types[nowType].id), targetId));
    problem.point = $("#AddProblemPoint").val();
    var dataString = JSON.stringify(problem);
    console.log(dataString);
    $.ajax({
        type: "POST",
        url: "addProblem.action",
        data: "problem=" + encodeURIComponent(JSON.stringify(problem)),
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                alert("添加题目成功！");
                previousPage();
            } else {
                alert(data.message);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("添加题目失败，连接出现问题！");
        }
    });
}

