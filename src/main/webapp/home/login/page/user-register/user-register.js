define([
    'zepto', 'underscore', 'backbone',
    'text!login/page/user-register/user-register.html', 'ShulyTool'
], function ($, _, Backbone, html, ShulyTool) {
    return Backbone.View.extend({
        id: 'user-register',
        fragment: document.createDocumentFragment(),
        $pageWrapper: $('#body-wrapper'),
        template: _.template(html),
        events: {
            'click #register-sub' : 'register'
        },
        initialize: function () {
            this.render();
        },
        render: function () {
            var fragment = this.fragment;
            this.$el = $(this.template()).appendTo(fragment);
            this.$pageWrapper.html(fragment);
            this.setElement(this.$pageWrapper.find('#' + this.id));
        },
        register: function () {
            var email = $("#email").val();
            var word = $("#password").val();
            var rewd = $("#rewd").val();
            var name = $("#name").val();
            if(email.length < 4){
                alert("郵箱长度[4,)");
                return ;
            }
            if(name.length < 4){
                alert("名字长度[4,)");
                return ;
            }
            if(word === null || rewd === null ||word !== rewd){
                alert("俩次密码不一样啊");
                return ;
            }
            if(word.length<5||word.length>20){
                alert("密码长度范围[5,20]");
                return ;
            }
            var param = {
                'email' : email,
                'password' : word,
                'userName' : name
            };
            ShulyTool.run('/console/user/register', 'POST', true, param, function(data) {
                // todo
            }, null);
        },
        start: function () {
        },
        destroy: function () {
            console.log("destroy shell-show-home");
        }

    });
});