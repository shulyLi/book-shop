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
                columns:[
                    {"data": "orderNo" },
                    {"data": "bookName"},
                    {"data": "bookAuthor"},
                    {"data": "cnt"},
                    {"data": "payFee"},
                    {"data": "state"},
                    {"data": "createTime"},
                ]
            });
            var resolve = this.resolve;
            var draw = this.draw;
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
            this.initTable();
        },
        initTable: function() {
            ShulyTool.run("/console/order/list", "GET", false, null, function (data) {
                var table = $('#dataTable').dataTable();
                table.fnClearTable(); //清空一下table
                table.fnDestroy(); //还原初始化了的datatable
                $("#dataTable").dataTable({
                    "lengthChange": false,
                    "searching": true,
                    "autoWidth": false,
                    bJQueryUI:true,
                    data: data,
                    columns:[
                        {"data": "orderNo" },
                        {"data": "bookName"},
                        {"data": "bookAuthor"},
                        {"data": "cnt"},
                        {"data": "payFee"},
                        {"data": "state"},
                        {"data": "createTime"},
                    ]
                });
            }, null);
        },
        resolve: function (orderNo, draw) {
            ShulyTool.run("/console/order/" + orderNo, "GET", false, null, function (data) {
                $("#order-modal").modal("show");
                draw(data)
            }, null);
        },
        draw: function (data){
            var addHead = data.addressHead.split(",");
            $("#s_province").val(addHead[0]);
            $("#s_city").val(addHead[1]);
            $("#s_county").val(addHead[2]);
            $("#add").val(data.addressTail);

            $("#buyName").val(data.receiveName);
            $("#buyPhone").val(data.receivePhone);
            var str =　
                "<tr class='warning'>" +
                    "<th>"+data.bookName+"</th>" +
                    "<th ><img class='img-responsive' width='100%' src="+data.bookHead+" ></th>" +
                    "<th>"+data.bookAuthor+"</th>" +
                    "<th>"+data.bookPrice / 100.+"</th>" +
                    "<th>" +
                        data.cnt  + "本"+
                    "</th>" +
                "</tr>";
            $("#order-modal #goodTable tbody").empty();
            $("#order-modal #goodTable tbody").append(str);
            $("#dealPriceTable").empty();
            $("#dealPriceTable").append('<tr><th style="width:50%">商品单价:</th><td>￥'+ data.bookPrice/100.0 +'</td></tr>');
            $("#dealPriceTable").append('<tr><th style="width:50%">运费:</th><td>￥'+ 20.0+'</td></tr>');
            $("#dealPriceTable").append('<tr><th style="width:50%">总价:</th><td>￥'+ data.payFee +'</td></tr>');
        },
        destroy: function () {
            console.log("destroy book-order-manger");
        }
    });
});