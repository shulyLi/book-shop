define([
    'zepto', 'underscore', 'backbone',
    'text!appDir/page/shell-list-run-state/shell-list-run-state.html'
], function ($, _, Backbone, html) {

    return Backbone.View.extend({
        id: 'shell-list-run-state',
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
            console.log("destroy shell-list-run-state");
        }
    });

});