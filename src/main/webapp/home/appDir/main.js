require.config({
    baseUrl: '/home/',
    appDir: 'appDir',
    paths: {
        zepto: 'static/zepto',
        underscore: 'static/underscore',
        backbone: 'static/backbone',
        jquery: 'static/jquery',
        app: 'appDir/app',
        router: 'appDir/router',
        text: 'static/text'
    },
    shim: {
        'backbone': {
            //These script dependencies should be loaded before loading
            //backbone.js
            deps: ['underscore', 'zepto'],
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

require(['zepto', 'app'], function ($, App) {
    $(function () {
        App.initialize();
    });
});