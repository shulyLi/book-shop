define([
    'jquery', 'cookie', 'underscore', 'backbone',
    'text!almost/page/book-user-manager/book-user-manager.html', 'ShulyTool'
], function ($, cookie, _, Backbone, html, ShulyTool) {
    return Backbone.View.extend({
        id: 'book-user-manager',
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
            this.start()
        },
        start: function () {
            var table = $("#dataTable").dataTable({
                "lengthChange": false,
                "searching": true,
                "autoWidth": false,
                bJQueryUI:true,
                ajax:  "/console/user/list",
                columns:[
                    {"data": "id" },
                    {"data": "email"},
                    {"data": "userName"},
                    {"data": "userType"},
                    {"data": "createTime"},
                ]
            });
            var resolve = this.resolve;
            $('#dataTable tbody').on( 'click', 'tr', function () {
                if ($(this).hasClass('selected') ) {
                    var orderNo = $(this).children("td:first-child").html();
                    resolve(orderNo, draw);
                    $(this).removeClass('selected');
                }
                else {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            } );
        },
        solve: function () {

        },
        destroy: function () {
            console.log("destroy book-user-manager");
        }
    });
});