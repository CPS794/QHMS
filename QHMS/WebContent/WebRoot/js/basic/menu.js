'use strict';
var address = "http://localhost:8080/QHMS";
var menu = window.menu || {};
menu.menuJson = {
    "homepage": {
        "text": "系统首页",
        "url": "home.action"
    },
    "loginpage": { // ##### TODO
        "text": "安全退出",
        "url": "index.action"
    },
    "userMenu": [{
        "user": "student",
        "userText": "同学",
        "item": [{
            "text": "学生组功能",
            "subMenu": [{
                    "text": "章节总览",
                    "url": "home.action"
                }
                /*, {
                                "text": "查看成绩",
                                "url": ""
                            }*/
                , {
                    "text": "评价教师组",
                    "url": "evaluationManage.action"
                }
            ]
        }, {
            "text": "教师组功能",
            "subMenu": [{
                "text": "查看所在小组",
                "url": "viewMyGroup.action"
            }, {
                "text": "题目管理",
                "url": "problemManage.action"
            }, {
                "text": "作业批阅",
                "url": "remarkHomework.action"
            }]
        }]
    }, {
        "user": "admin",
        "userText": "老师",
        "item": [{
            "text": "用户管理",
            "subMenu": [{
                "text": "系统管理员",
                "url": "adminManage.action"
            }, {
                "text": "学生",
                "url": "studentManage.action"
            }]
        }, {
            "text": "题库管理",
            "subMenu": [{
                "text": "题目管理",
                "url": "problemManage.action"
            }]
        }, {
            "text": "成绩管理",
            "subMenu": [
                /*{
                    "text": "教师组成绩",
                    "url": ""
                }, */
                {
                    "text": "作业成绩",
                    "url": "scoreManage.action"
                }
            ]
        }]
    }],
    "systemMenu": [{
        "text": "个人信息",
        "subMenu": [{
            "text": "修改信息",
            "click": "showPasswordDialog",
            "url": "modifyInfo.action"
        }, {
            "text": "修改密码",
            "click": "showPasswordDialog",
            "url": "password.action"
        }]
    }, {
        "text": "安全退出",
        "url": "logout.action"
    }]
}
