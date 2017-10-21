define(['zepto', 'underscore', 'backbone', 'router'], function ($, _, Backbone) {
    var currentPage = null;

    function navigate(Page, query, state) {
        currentPage && currentPage.destroy();
        currentPage = new Page(query && _.query2json(query) || null, state && _.query2json(state) || null);
    }

    var AppRouter = Backbone.Router.extend({
        routes: {
            '*action(/:query)(/:state)': 'handleRoute'
        },
        handleRoute: function (action, query, state) {
            console.log(action, query, state);
            action = action || 'user-login';
            require(['login/page/' + action + '/' + action], function (Page) {
                navigate(Page, query, state);
            });
        }
    });
    return new AppRouter();
});