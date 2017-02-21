// import menuJson in menu.js
var userMenu;
var systemMenu;
var menuHtml = "";
var userType = "";
var teacher = false;
var passwordTip = ""; //tip中显示的内容跨越弹窗存在，所以必须设置成全局变量

// main function
$(document).ready(function() {
    userType = $("#hiddenType").val();
    userMenu = menu.menuJson.userMenu[userType];
    systemMenu = menu.menuJson.systemMenu;
    console.log(userMenu);
    initMenu();
    setBtnClick();
    setLiClick();
});

/**
 * the click action of "MENU" : 
 *      slide right to open the menu
 *      or slide left to close it
 */
function setBtnClick() {
    $('#sidebarToggle').click(function() {
        $('.sidebar-left').toggleClass('sidebar-open');
        $('.sidebar-push').toggleClass('sidebar-push-toright');
    });
}

function initMenu() {
    $("#nav").html("");
    menuHtml = "";
    $("#userInfo").html("欢迎 " + $("#hiddenName").val() + userMenu.userText + " 使用<br>题库作业管理系统！");
    menuHtml += '<ul class="' + 'mainMenu' + '">';
    // homepage link
    addHomepageMenu();
    // user specific menu
    for (var i = 0; i < userMenu.item.length; i++) {
        menuHtml += '<li class="mainLi">';
        menuHtml += userMenu.item[i].text;
        menuHtml += '<ul class="' + 'subMenu' + '">';
        for (var j = 0; j < userMenu.item[i].subMenu.length; j++) {
            menuHtml += '<li>';
            menuHtml += '<a href="' + userMenu.item[i].subMenu[j].url + '">' + userMenu.item[i].subMenu[j].text + '</a>';
            menuHtml += '</li>';
        };
        menuHtml += '</ul>';
        menuHtml += '</li>';
    };
    // system menu
    addSystemMenu();
    menuHtml += '</ul>';
    $("#nav").append(menuHtml);
    // 子菜单默认隐藏，去掉下一行就默认显示
    $(".mainLi").children().hide();
}

/**
 * click to show sub menu and click to hide
 */
function setLiClick() {
    $(".mainLi").click(function(event) {
        if ($(this).children().is(":hidden")) {
            $(this).children().show('400', function() {});
        } else {
            $(this).children().hide('400', function() {});
        }
    });
}


/**
 * Add homepage link
 */
function addHomepageMenu() {
    menuHtml += '<li onclick=goHome()  class="mainLi">' + menu.menuJson.homepage.text + '<Li>';
}


/**
 * Add system menu such as logout or so
 */
function addSystemMenu() {
    // for (var i = 0; i < systemMenu.length - 1; i++) {
    //     menuHtml += '<li class="mainLi">';
    //     menuHtml += systemMenu[i].text;
    //     menuHtml += '<ul class="' + 'subMenu' + '">';
    //     for (var j = 0; j < systemMenu[i].subMenu.length; j++) {
    //         menuHtml += '<li onclick=' + systemMenu[i].subMenu[j].click + '("' + systemMenu[i].subMenu[j].url + '")' + '>';
    //         menuHtml += '<a href="">' + systemMenu[i].subMenu[j].text + '</a>';
    //         menuHtml += '</li>';
    //     };
    //     menuHtml += '</ul>';
    //     menuHtml += '</li>';
    // };
    menuHtml += '<li onclick=showPasswordDialog("password.action")  class="mainLi">' + '修改密码' + '<Li>';
    menuHtml += '<li onclick=exitSystem()  class="mainLi">' + '安全退出' + '<Li>';
    menuHtml += '<li onclick=aboutUs()  class="mainLi">' + '关于我们' + '<Li>';
}

function showPasswordDialog(url) {
    var textBefore = '<div class="row"><div class="col-md-12"><form class="form-horizontal"><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="oldPassword">请输入原密码：</label><div class="col-md-5"><input id="oldPassword" name="oldPassword" type="password" placeholder="原密码" class="form-control input-md"></div></div><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="newPassword">请输入新密码：</label><div class="col-md-5"><input id="newPassword" name="newPassword" type="password" placeholder="新密码" class="form-control input-md"></div></div><div class="form-group"><div class="col-md-1"></div><label class="col-md-4 control-label" for="confirmPassword">请确认新密码：</label><div class="col-md-5"><input id="confirmPassword" name="confirmPassword" type="password" placeholder="再次输入新密码" class="form-control input-md"><span class="help-block" id="passwordNotSame">';
    var textAfter = '</span></div></div></form></div></div>';

    bootbox.dialog({
        title: "修改密码",
        message: textBefore + passwordTip + textAfter,
        buttons: {
            success: {
                label: "确定修改",
                className: "btn-primary",
                callback: function() {
                    if ($("#newPassword").val() == $("#confirmPassword").val()) {
                        modifyPassword(url, $("#oldPassword").val(), $("#newPassword").val());
                    } else {
                        passwordTip = "两次输入的新密码不一致！";
                        showPasswordDialog(url);
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

function modifyPassword(url, oldPwd, newPwd) {
	oldPwd = cal_md5(oldPwd);
	newPwd = cal_md5(newPwd);
    passwordTip = "";
    var dataString = "oldPassword=" + oldPwd + "&newPassword=" + newPwd;
    $.ajax({
        type: "POST",
        url: url,
        data: dataString,
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
            if (data.success) {
                alert("密码修改成功！");
            } else {
                passwordTip = data.message;
                showPasswordDialog(url);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            alert("修改失败，连接出现问题！");
        }
    });
}

/**
 * return to the homepage of this type of user
 */
function goHome() {
    window.location = menu.menuJson.homepage.url;
}

/**
 * logout and clear sessions
 */
function exitSystem() {
    bootbox.dialog({
        title: "确定要安全退出系统吗？",
        message: "安全退出后，您的本次连接产生的信息将不会被保留。",
        buttons: {
            success: {
                label: "确定退出",
                className: "btn-primary",
                callback: function() {
                    $.ajax({
                        type: "POST",
                        url: systemMenu[systemMenu.length - 1].url,
                        dataType: "json",
                        success: function(data, textStatus, jqXHR) {
                            window.location = menu.menuJson.loginpage.url;
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            goHome();
                        }
                    });
                }
            },
            failed: {
                label: "继续浏览",
                className: "btn-success",
                callback: function() {}
            }
        }
    });
}

/**
 * Pop out a window to show information about us
 */
function aboutUs() {
    // #### TODO
    bootbox.alert('毕竟能力有限，这个系统还有很多不足的地方，如果你有兴趣有时间，可以向管理员提BUG，虽然我不确定我有没有兴趣有没有时间改。');
}
