var chapter;
var chapterInfo;
var problems;

$(document).ready(function() {
    chapter = $('#hiddenChapterId').val();
    init();
});

function init() {
    $.ajax({
        type: "POST",
        url: "getChapterAnswers.action",
        data: "chapterId=" + chapter,
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                problems = data.answers;
                chapterInfo = data.chapter;
                initPage(data.grade);
            } else {
                alert(data.message);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("获取答案失败，连接出现问题！");
        }
    });
}

function initPage(grade) {
    $('#answerChapterTitle').html(chapterInfo.displayName + ": " + grade + "分");
    $('#answerPanel').html("");
    keys = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
    for (var i = 0; i < problems.length; i++) {
        var problemString = '<div class="row">';
        problemString += '<b>' + (i + 1) + '</b>' + '. ';
        problemString += problems[i].description;
        problemString += '<br>'
        if (problems[i].typeId == 2) {
            var options = eval(problems[i].option);
            // console.log(options);
            for (var j = 0; j < 4; j++) {
                problemString += keys[j] + '. ' + options[j];
                problemString += '<br>'
            };
        } else if (problems[i].typeId == 3) {
            var options = eval(problems[i].option);
            for (var j = 0; j < options.length; j++) {
                problemString += keys[j] + '. ' + options[j];
                problemString += '<br>'
            };
        }
        problemString += '<b>你的回答：</b>';
        problemString += eval(problems[i].answer);
        problemString += '<br>'
        problemString += '<b>参考答案：</b>';
        problemString += eval(problems[i].problemAnswer);
        problemString += '<br>'
        problemString += '<b>本题分值：</b>' + problems[i].fullPoint + '  ' + '<b>你的得分：</b>' + problems[i].point;
        problemString += '<br>'
        problemString += '<br>'
        problemString += '</div>';
        $('#answerPanel').append(problemString);
    };
}

function goBack() {
    window.location = "home.action";
}

function showEvaluate () {
    window.location = "evaluationManage.action";
}