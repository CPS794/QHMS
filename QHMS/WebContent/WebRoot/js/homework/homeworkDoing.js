var types = [];
var anti_types = [];
var chapter;
var chapterInfo;
var problems;
var answer = [];
var now;

$(document).ready(function() {
    chapter = $('#hiddenChapterId').val();
    init();
});

function init() {
    $.ajax({
        type: "POST",
        url: "getChapterProblems.action",
        data: "chapterId=" + chapter,
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                problems = data.problems;
                for (var i = 0; i < problems.length; i++) {
                    answer[i] = {};
                    answer[i].answer = '[]';
                    answer[i].problemId = problems[i].id;
                };
                initPage();
            } else {
                alert(data.message);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("获取题目失败，连接出现问题！");
        }
    });
}

function initPage() {
    $.ajax({
        type: "POST",
        url: "addProblemPageInit.action",
        data: "",
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                for (var i = 0; i < data.object_chapters.length; i++) {
                    if (data.object_chapters[i].id == chapter) {
                        chapterInfo = data.object_chapters[i];
                    }
                };
                types = data.types;
                for (var i = 0; i < types.length; i++) {
                    anti_types[types[i].id] = i;
                };
                showPage();
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

function showPage() {
    // title
    $('#homeworkChapterTitle').html(chapterInfo.displayName + ' 作业');

    // view panel
    $('#viewPanel').html("");
    for (var i = 0; i < problems.length; i++) {
        $('#viewPanel').append('<div class="questionNumber" onclick="goTo_problem(' + i + ')" id="problem' + i + '">' + (i + 1) + '</div>')
    };

    show_problem(0);
}

function reset_homework() {}

function check_homework() {
    save_problem(now);
    $('#problemsCheckPanel').html('<br>');
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
        if (notAnswer(i)) {
            problemString += '<h4><span class="label label-danger">你没有回答这道题！</span></h4>';
        } else {
            problemString += eval(answer[i].answer);
        }
        problemString += '<br>'
        problemString += '<br>'
        problemString += '</div>';
        $('#problemsCheckPanel').append(problemString);
    };
    $('#problemsCheckPanel').append('<hr>');
    $('#homeworkCheckModal').modal('toggle');
}

function submit_homework() {
    save_problem(now);
    var notFinishYet = false;
    for (var i = 0; i < answer.length; i++) {
        if (notAnswer(i)) {
            notFinishYet = true;
            break;
        }
    };
    if (notFinishYet) {
        var s = confirm('你还有题目没有回答完整。确定要提交吗？');;
        if (!s) {
            return;
        }
    }
    console.log(answer);
    var r = confirm('每章作业只能提交一次，提交后不可修改，将由系统和教师组批改评分。确定要提交吗？');
    if (r == true) {
        $.ajax({
            type: "POST",
            url: "submitHomework.action",
            data: "chapter=" + chapterInfo.id + "&answers=" + encodeURIComponent(JSON.stringify(answer)),
            dataType: "json",
            success: function(data, textStatus, jqXHR) {
                if (data.success) {
                    alert('作业提交成功！');
                    window.location = "home.action";
                } else {
                    alert(data.message);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Something really bad happened " + textStatus);
                alert("作业提交失败，连接出现问题！");
            }
        });
    }
}

function notAnswer(index) {
    if (answer[index].answer == '[null]' || answer[index].answer == '[]' || answer[index].answer == '[""]' || answer[index].answer == '["<p><br></p>"]') {
        return true;
    } else {
        return false;
    }
}

function save_problem(index) {
    answer[index].answer = JSON.stringify(
        getFunction(eval('getDefaultAnswer' + types[anti_types[problems[index].typeId]].id), ['problemOption_' + index]));
    // console.log(answer[index].answer);
    // alert(answer[index].problemId);
    if (notAnswer(index)) {
        $('#problem' + index).removeClass('questionNumberFinished').addClass('questionNumber');
    } else {
        $('#problem' + index).removeClass('questionNumber').addClass('questionNumberFinished');
    }
}

function show_problem(index) {
    now = index;
    // alert('show ' + index);
    $('#homeworkProblemType').html(types[anti_types[problems[index].typeId]].name + ' ' + '（本题 ' + problems[index].point + ' 分）');
    $('#homeworkDescription_div').html('' + (index + 1) + '. ' + problems[index].description);
    doFunction(eval('showOption' + (anti_types[problems[index].typeId] + 1)), ['homeworkOption_div', 'problemOption_' + index, eval(problems[index].option)]);
    if (!notAnswer(index)) {
        doFunction(eval('setAnswer' + (anti_types[problems[index].typeId] + 1)), ['problemOption_' + index, eval(answer[index].answer)]);
    }
}

function goTo_problem(index) {
    save_problem(now);
    show_problem(index);
}

function goNext_problem() {
    save_problem(now);
    if (now == problems.length - 1) {
        alert("已经是最后一个题目了！")
    } else {
        goTo_problem(now + 1);
    }
}

function goPrev_problem() {
    save_problem(now);
    if (now == 0) {
        alert("已经是第一个题目了！")
    } else {
        goTo_problem(now - 1);
    }
}

function leave_homework() {
    var r = confirm('离开页面将会丢失本次的答题情况。确定要离开吗？');
    if (r == true) {
        window.location = "home.action";
    }
}
