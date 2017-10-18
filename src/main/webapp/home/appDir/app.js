//app.js
define(['zepto', 'underscore', 'backbone', 'router',], function ($, _, Backbone) {
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