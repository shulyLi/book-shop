define([
    'jquery', 'cookie', 'underscore', 'backbone',
    'text!almost/page/book-detail/book-detail.html', 'ShulyTool'
], function ($, cookie, _, Backbone, html, ShulyTool) {
    return Backbone.View.extend({
        id: 'book-detail',
        fragment: document.createDocumentFragment(),
        $pageWrapper: $('#page-wrapper'),
        template: _.template(html),
        events: {
        },
        initialize: function () {
            this.render();
        },
        render: function () {
            var fragment = this.fragment;
            this.$el = $(this.template()).appendTo(fragment);
            this.$pageWrapper.html(fragment);
            this.setElement(this.$pageWrapper.find('#' + this.id));
            this.start();
        },
        start: function () {
            var goodId = window.location.hash.substring(20);
            ShulyTool.run("/console/good/" + goodId, "GET", false, null, function (data) {
                $("#bookHead").attr('src', data.bookHead);
            }, null);
        },
        destroy: function () {
            console.log("destroy book-detail");
        }
    });
});