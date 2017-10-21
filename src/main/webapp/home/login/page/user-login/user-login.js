define([
    'jquery', 'cookie', 'underscore', 'backbone',
    'text!login/page/user-login/user-login.html', 'ShulyTool'
], function ($, cookie, _, Backbone, html, ShulyTool) {
    return Backbone.View.extend({
        id: 'user-login',
        fragment: document.createDocumentFragment(),
        $pageWrapper: $('#body-wrapper'),
        template: _.template(html),
        events: {
            'click #login-sub': 'login'
        },
        initialize: function () {
            this.render();
        },
        login: function () {
            var email = $("#email").val();
            var pswd = $("#pswd").val();
            if (email.length < 4) {
                alert("郵箱长度[4,)");
                return;
            }
            if (pswd.length < 5 || pswd.length > 20) {
                alert("密码长度范围[5,20]");
                return;
            }
            var param = {
                'email': email,
                'password': pswd
            };
            ShulyTool.run('/console/user/login', 'GET', true, param, function (data) {
                $.cookie("user-sso", data, {
                    path:"/"
                });
                console.log($.cookie("user-sso"));
                window.location.href = "/"
            }, null);
        },
        render: function () {
            var fragment = this.fragment;
            this.$el = $(this.template()).appendTo(fragment);
            this.$pageWrapper.html(fragment);
            this.setElement(this.$pageWrapper.find('#' + this.id));
            this.start();
        },
        start: function () {
        },
        destroy: function () {
            console.log("destroy shell-show-home");
        }
    });
});