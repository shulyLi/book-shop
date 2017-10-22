require.config({
    baseUrl: '/home/',
    appDir: 'almost',
    paths: {
        jquery: 'static/jquery',
        zepto: 'static/zepto',
        cookie: 'static/cookie',
        underscore: 'static/underscore',
        backbone: 'static/backbone',
        app: 'almost/app',
        router: 'almost/router',
        text: 'static/text',
        ShulyTool:'static/ShulyTool',
        md5:'static/md5',
        select2: 'static/select2',
        bootstrap: 'static/bootstrap',
        ALT: 'static/ALT',
        datatables : "static/jquery.dataTables",
        datatablesboot: "static/dataTables.bootstrap"
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
        'jquery': {
            exports : 'jQuery'
        },
        'bootstrap': {
            deps: ['jquery']
        },


        'ALT': {
            deps: ['jquery', 'bootstrap']
        },
        'datatables': {
            deps: ['jquery', 'bootstrap', 'ALT'], exports: "datatables"
        },
        'datatablesboot' : {
            deps: ['jquery', 'datatables']
        },
        modules: []
    },
    optimizeAllPluginResources: true,
    excludeShallow: '',
    waitSeconds: 0,
    urlArgs: 'v=1.0.0'
});

require(['jquery', 'app'], function ($, App) {
    $(function () {
        App.initialize();
    });
});
window.hostIp = "";
