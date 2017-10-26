// Filename: app.js
define([
    'jquery',
    'underscore',
    'backbone',
    'ShulyTool', 'cookie',
    'select2',
    'bootstrap',
    'ALT',
    'datatables',
    'datatablesboot',
    'router',
    'md5',
], function ($, _, Backbone, ShulyTool, cookie) {
    var menuMap = {
        default : '<li> <a href="#book-index"> <i class="fa fa-search"></i> <span>主页</span> </a> </li>',
        good    : '<li> <a href="#book-good-manager"> <i class="fa fa-book"></i> <span>管理书籍</span> </a> </li>',
        order   : '<li> <a href="#book-order-manager"> <i class="fa fa-file-pdf-o"></i> <span>订单管理</span> </a> </li>',
        user    : '<li> <a href="#book-user-manager"> <i class="fa fa-user"></i> <span>用户管理</span> </a> </li>'
    }
    var initialize = function () {
        Backbone.history.start();
        ShulyTool.run("/console/user", "GET", true, null, function (data) {
            $("#index-home-menu").empty();
            $("#index-home-menu").append(menuMap.default);
            if (data === "" || data === null || data === "null" || data === undefined) {
                $("#index-login-ali").text("去登陆");
                return;
            }
            $("#index-login-ali").text("注销");
            $.each(data.power, function (idx, value) {
                $("#index-home-menu").append(menuMap[value]);
            })
            $("#index-home-user-name").text(data.userName);
            $("#index-home-user-head").attr("src", data.userHead === "None" ? "/upload/head/default_head.jpg" : data.userHead);
        }, null);

        $("#index-login-ali").click(function () {
            cookie("user-sso", "", {
                path:"/"
            });
            window.location.href = "/view/login.html"
        })
    };

    return {
        initialize: initialize
    };
});