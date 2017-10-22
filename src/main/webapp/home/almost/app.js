// Filename: app.js
define([
    'zepto',
    'underscore',
    'backbone',
    'page-manager',
    'router',
    'md5'
], function ($, _, Backbone) {
    var initialize = function () {
        Backbone.history.start();
        //router.navigate('home', {
        //    trigger: true
        //})
    };

    return {
        initialize: initialize
    };
});