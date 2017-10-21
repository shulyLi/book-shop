define([
    'zepto', 'underscore', 'backbone',
    'text!appDir/page/book-index/book-index.html'
], function ($, _, Backbone, html) {

    return Backbone.View.extend({
        id: 'book-index',
        fragment: document.createDocumentFragment(),
        $pageWrapper: $('#page-wrapper'),
        template: _.template(html),
        events: {},
        initialize: function () {
            this.render();
        },
        render: function () {
            var fragment = this.fragment;
            this.$el = $(this.template()).appendTo(fragment);
            this.$pageWrapper.html(fragment);
            this.setElement(this.$pageWrapper.find('#' + this.id));
        },
        destroy: function () {
            console.log("destroy book-index");
        }
    });

});