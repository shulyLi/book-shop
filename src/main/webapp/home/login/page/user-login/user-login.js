define([
    'zepto', 'underscore', 'backbone',
    'text!login/page/user-login/user-login.html'
], function ($, _, Backbone, html) {
    return Backbone.View.extend({
        id: 'user-login',
        fragment: document.createDocumentFragment(),
        $pageWrapper: $('#body-wrapper'),
        template: _.template(html),
        events: {
            'click #login-sub' : 'login'
        },
        initialize: function () {
            this.render();
            /*
             $("#loginSub").click(function () {
             var name= $("#name").val();
             var pswd= $("#pswd").val();
             if(name.length<4 || name.length >15){
             alert("名字长度[4,15]");
             return ;
             }
             if(pswd.length<5||pswd.length>20){
             alert("密码长度范围[5,20]")
             return ;
             }
             $.ajax({
             url:'/key/user/login',
             data :{
             "name":name,
             "password":pswd
             },
             dataType:"json",   //返回格式为json
             async:true, //请求是否异步，默认为异步，这也是ajax重要特性
             type:"post",   //请求方式
             traditional:true,
             beforeSend:function() {
             },
             success:function(data) {
             if(data.goto!=""){
             window.location.href=data.goto;
             }
             else if(data.msg!=""){
             alert(data.msg);
             return ;
             }
             },
             complete:function() {
             },
             error:function() {
             }
             });
             })*/
        },
        login:function () {
            alert(2);
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