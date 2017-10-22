require.config({
    baseUrl: '/home/',
    appDir: 'login',
    paths: {
        jquery: 'static/jquery',
        cookie: 'static/cookie',
        underscore: 'static/underscore',
        backbone: 'static/backbone',
        app: 'login/app',
        router: 'login/router',
        text: 'static/text',
        ShulyTool:'static/ShulyTool'
    },
    shim: {
        'backbone': {
            //These script dependencies should be loaded before loading
            //backbone.js
            deps: ['underscore', 'jquery'],
            //Once loaded, use the global 'Backbone' as the
            //module value.
            exports: 'Backbone'
        },
        'underscore': {
            exports: '_'
        },
        modules: []
    },
    waitSeconds: 0,
    urlArgs: 'v=1.0.0'
});

require(['jquery', 'app'], function ($, App) {
    $(function () {
        App.initialize();
    });
});