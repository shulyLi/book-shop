// Filename: app.js
define([
    'jquery',
    'underscore',
    'backbone',

    'select2',
    'bootstrap',
    'ALT',

    'router',
    'md5',
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