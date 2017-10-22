// Filename: app.js
define(['underscore'], function (_) {
    var pages = {}, currentPage, defaultAction = 'home';

    function add() {
        var id, i, idSplits;
        for (i = 0; i < arguments.length; i++) {
            id = arguments[i].prototype.id;
            idSplits = id.split('-');
            id = idSplits.slice(idSplits.indexOf('page') + 1).join('-');
            pages[id] = arguments[i];
        }

    }
    function remove(name) {
        delete page[name];
        return pages;
    }
    function navigate(action, query, state) {
        action = action || defaultAction;
        if(pages[action]) {
            currentPage && currentPage.destroy();
            currentPage = new pages[action](query && _.query2json(query) || null, state && _.query2json(state) || null);
        } else {
            throw new Error('page [' + action + '] doesn\'t exist!');
        }
    }
    function setDefault(action) {
        defaultAction = action || defaultAction;
    }

    return {
        add: add,
        remove: remove,
        navigate: navigate,
        setDefault: setDefault
    };
});