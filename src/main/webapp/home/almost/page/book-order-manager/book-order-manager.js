define([
    'jquery', 'underscore', 'backbone','cookie',
    'text!almost/page/book-order-manager/book-order-manager.html', 'ShulyTool'
], function ($, _, Backbone, cookie, html, ShulyTool) {
    return Backbone.View.extend({
        id: 'book-order-manger',
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
            var table = $("#dataTable").dataTable({
                "lengthChange": false,
                "searching": true,
                "autoWidth": false,
                bJQueryUI:true,
                ajax:  "/console/order/list",
                columns:[
                    {"data": "orderNo" },
                    {"data": "bookName"},
                    {"data": "bookAuthor"},
                    {"data": "cnt"},
                    {"data": "price"},
                    {"data": "state"}
                ]
            });
        },
        destroy: function () {
            console.log("destroy book-order-manger");
        }
    });
});