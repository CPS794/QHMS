'use strict';
var utility = window.utility || {};
utility.component = {
    "textbox": {
        "isGroup": false,
        "lable": "input",
        "close": false,
        "property": [
            "type", "class", "placeholder", "id", "value"
        ],
        "propertyDefault": {
            "type": "text",
            "class": "form-control"
        },
        "tag": ["readonly", "disabled"],
        "tagDefault": {},
        "value": ""
    },
    "textarea": {
        "isGroup": false,
        "lable": "textarea",
        "close": true,
        "property": [
            "class", "placeholder", "id", "rows"
        ],
        "propertyDefault": {
            "class": "form-control"
        },
        "tag": ["readonly", "disabled"],
        "tagDefault": {},
        "value": ""
    },
    "datetimebox": {
        "isGroup": false,
        "lable": "input",
        "close": false,
        "property": [
            "class", "placeholder", "id", "size", "type", "value"
        ],
        "propertyDefault": {
            "placeholder": "Please click here to select date and time!",
            "class": "form-control form_datetime",
            "size": "16",
            "type": "text"
        },
        "tag": ["readonly", "disabled"],
        "tagDefault": {},
        "value": ""
    },
    "datebox": {
        "isGroup": false,
        "lable": "input",
        "close": false,
        "property": [
            "class", "placeholder", "id", "size", "type", "value"
        ],
        "propertyDefault": {
            "placeholder": "Please click here to select date!",
            "class": "form-control form_date",
            "size": "16",
            "type": "text"
        },
        "tag": ["readonly", "disabled"],
        "tagDefault": {},
        "value": ""
    },
    "timebox": {
        "isGroup": false,
        "lable": "input",
        "close": false,
        "property": [
            "class", "placeholder", "id", "size", "type", "value"
        ],
        "propertyDefault": {
            "placeholder": "Please click here to select time!",
            "class": "form-control form_time",
            "size": "16",
            "type": "text"
        },
        "tag": ["readonly", "disabled"],
        "tagDefault": {},
        "value": ""
    },
    "checkbox": {
        "isGroup": true,
        "lable": "div",
        "close": true,
        "property": [
            "class", "id", "name"
        ],
        "propertyDefault": {
            "class": "checkbox"
        },
        "tag": ["readonly", "disabled"],
        "tagDefault": {},
        "innerOpen": "label><input type=\"checkbox\"",
        "innerClose": "/label",
        "divider": "<br>",
        "innerProperty": ["id", "name", "class"],
        "innerPropertyDefault": {},
        "innerTag": ["readonly", "disabled", "checked"],
        "innerTagDefault": {},
        "value": ""
    },
    "checkbox_inline": {
        "isGroup": true,
        "lable": "div",
        "close": true,
        "property": [
            "class", "id", "name"
        ],
        "propertyDefault": {
            "class": "checkbox-inline"
        },
        "tag": ["readonly", "disabled"],
        "tagDefault": {},
        "innerOpen": "label class=\"checkbox-inline\"><input type=\"checkbox\"",
        "innerClose": "/label",
        "divider": "",
        "innerProperty": ["id", "name", "class"],
        "innerPropertyDefault": {},
        "innerTag": ["readonly", "disabled", "checked"],
        "innerTagDefault": {},
        "value": ""
    },
    "radiobox": {
        "isGroup": true,
        "lable": "div",
        "close": true,
        "property": [
            "class", "id", "name"
        ],
        "propertyDefault": {
            "class": "radio"
        },
        "tag": ["readonly", "disabled"],
        "tagDefault": {},
        "innerOpen": "label><input type=\"radio\"",
        "innerClose": "/label",
        "divider": "<br>",
        "innerProperty": ["type", "id", "name", "class"],
        "innerPropertyDefault": {},
        "innerTag": ["readonly", "disabled", "checked"],
        "innerTagDefault": {},
        "value": ""
    },
    "radiobox_inline": {
        "isGroup": true,
        "lable": "div",
        "close": true,
        "property": [
            "class", "id", "name"
        ],
        "propertyDefault": {
            "class": "radio-inline"
        },
        "tag": ["readonly", "disabled"],
        "tagDefault": {},
        "innerOpen": "label class=\"radio-inline\"><input type=\"radio\"",
        "innerClose": "/label",
        "divider": "",
        "innerProperty": ["id", "name", "class"],
        "innerPropertyDefault": {},
        "innerTag": ["readonly", "disabled", "checked"],
        "innerTagDefault": {},
        "value": ""
    },
    "select": {
        "isGroup": true,
        "lable": "select",
        "close": true,
        "property": [
            "class", "id", "name", "onchange"
        ],
        "propertyDefault": {
            "class": "form-control"
        },
        "tag": ["readonly", "disabled"],
        "tagDefault": {},
        "innerOpen": "option",
        "innerClose": "/option",
        "divider": "",
        "innerProperty": ["id", "name", "class"],
        "innerPropertyDefault": {},
        "innerTag": ["readonly", "disabled", "selected"],
        "innerTagDefault": {},
        "value": ""
    },
    "multiple_select": {
        "isGroup": true,
        "lable": "select",
        "close": true,
        "property": [
            "class", "id", "name", "onchange"
        ],
        "propertyDefault": {
            "class": "form-control"
        },
        "tag": ["readonly", "disabled", "multiple"],
        "tagDefault": {
            "multiple": true
        },
        "innerOpen": "option",
        "innerClose": "/option",
        "divider": "",
        "innerProperty": ["id", "name", "class"],
        "innerPropertyDefault": {},
        "innerTag": ["readonly", "disabled", "selected"],
        "innerTagDefault": {},
        "value": ""
    },
    "object": {
        "isGroup": false,
        "lable": "div",
        "close": true,
        "property": [
            "type", "class", "placeholder", "id", "value"
        ],
        "propertyDefault": {},
        "tag": ["readonly", "disabled"],
        "tagDefault": {},
        "value": ""
    }
}

utility.defaultSummernoteSetting = {
    height: 200, // set editor height
    minHeight: null, // set minimum height of editor
    maxHeight: null, // set maximum height of editor
    focus: true, // set focus to editable area after initializing summernote);
    // dialogsInBody: true,
    toolbar: [
        // [groupName, [list of button]]
        ['style', ['bold', 'italic', 'underline', 'clear']],
        ['font', ['strikethrough', 'superscript', 'subscript']],
        ['fontname', ['fontname']],
        ['fontsize', ['fontsize']],
        ['color', ['color']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['insert', ['picture', 'link', 'table']],
        ['height', ['height']]
    ]
};
