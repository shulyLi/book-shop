require.config({
    baseUrl: '/home/',
    appDir: 'almost',
    paths: {
        zepto: 'static/zepto',
        underscore: 'static/underscore',
        backbone: 'static/backbone',
        text: 'static/text',
        'page-manager': 'static/page-manager',
        md5: 'static/md5',
        app: 'almost/app',
        router: 'almost/router',
     //   wechatShare: 'static/wechatShare',
        ShulyTool:'static/ShulyTool'
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
    optimizeAllPluginResources: true,
    excludeShallow: '',
    waitSeconds: 0,
    urlArgs: 'v=1.0.0'
});

require(['zepto', 'app'], function ($, App) {
    $(function () {
        App.initialize();
    });
});
window.hostIp = "";
