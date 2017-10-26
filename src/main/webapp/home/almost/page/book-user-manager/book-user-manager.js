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
            this.initTable(this);
        },
        initTable: function (GCD) {
            ShulyTool.run("/console/user/list", "GET", false, null, function (data) {
                var table = $("#dataTable").dataTable({
                    "lengthChange": false,
                    "searching": true,
                    "autoWidth": false,
                    bJQueryUI:true,
                    order: [[ 0, "desc" ]],
                    data: data,
                    columns:[
                        {"data": "id" },
                        {"data": "email"},
                        {"data": "userName"},
                        {"data": "userType"},
                        {"data": "createTime"},
                    ]
                });
                $('#dataTable tbody').on( 'click', 'tr', function () {
                    if ($(this).hasClass('selected') ) {
                        var userId = $(this).children("td:first-child").html();
                        GCD.solve(userId);
                        $(this).removeClass('selected');
                    }
                    else {
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                } );
            }, null);
        },
        solve: function(GCD) {

        },
        destroy: function () {
            console.log("destroy book-user-manager");
        }
    });
});