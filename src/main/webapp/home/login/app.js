//app.js
define(['jquery', 'underscore', 'backbone', 'cookie', 'router'], function ($, _, Backbone, cookie) {
    var initialize = function () {
        Backbone.history.start();
    };
    return {
        initialize: initialize
    };
});