define([
    'jquery', 'underscore', 'backbone',
    'text!almost/page/book-good-manager/book-good-manager.html', 'ShulyTool'
], function ($, _, Backbone, html, ShulyTool) {
    return Backbone.View.extend({
        id: 'book-good-manager',
        fragment: document.createDocumentFragment(),
        $pageWrapper: $('#page-wrapper'),
        template: _.template(html),
        events: {
            "click #book-pic-submit": "uploadPic",
            "click #book-create" : "createBook",
            "click #good-submit" : "postHttpBook"
        },
        initialize: function () {
            this.render();
        },
        render: function () {
            var fragment = this.fragment;
            this.$el = $(this.template()).appendTo(fragment);
            this.$pageWrapper.html(fragment);
            this.setElement(this.$pageWrapper.find('#' + this.id));
            $("#tag").select2();
            this.start();
            $("#good-submit").unbind("click");
            $("#good-submit").click(this.postHttpBook);
        },

        start: function () {
            var table = $("#dataTable").dataTable({
                "lengthChange": false,
                "searching": true,
                "autoWidth": false,
                bJQueryUI:true,
                ajax:  "/console/good/list/summary",
                rowId: 'id',
                columns:[
                    {"data": "id" },
                    {"data": "bookName"},
                    {"data": "bookAuthor"},
                    {"data": "price"},
                    {"data": "sellCnt"},
                    {"data": "tag"},
                    {"data": "stock"}
                ],
            });
            var resolve = this.resolve;
            $('#dataTable tbody').on( 'click', 'tr', function () {
                if ($(this).hasClass('selected') ) {
                    var goodId = $(this).children("td:first-child").html();
                    resolve(goodId);
                    $(this).removeClass('selected');
                }
                else {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            } );
        },
        createBook: function () {
            console.log("create Book");
            $("#book-id").val("");
            $("#book-modal").modal("show");

        },
        resolve: function(goodId) {
            console.log("changeId : " +  goodId);
            $("#book-id").val(goodId);
            ShulyTool.run("/console/good/" + goodId, "GET", false, null, function (data) {
                $("#book-modal").modal("show");
                $.each(data, function(name, value) {
                    if (name === 'id' || name === "bookHead") return;
                    if (name === 'tag') return;
                    $("#" + name).val(value)
                });
            }, null);
        },
        postHttpBook: function () {
            var start = this.start;
            var goodId =  $("#book-id").val();
            console.log("apply : " +  goodId);
            var tagStr = $("#tag").select2('val');
            if (tagStr === null) {
                tagStr = "";
            } else {
                tagStr = tagStr.toString();
            }
            var param = {
                bookName : $("#bookName").val(),
                bookAuthor:  $("#bookAuthor").val(),
                bookHead: $("#bookHead")[0].src,
                price:  $("#price").val(),
                simpleDesc:  $("#simpleDesc").val(),
                tag:tagStr,
                stock:  $("#stock").val(),
                betterPart:  $("#betterPart").val(),
                index:  $("#index").val(),
                detail:  $("#detail").val(),
            };
            if (goodId == "") { // 创建
                console.log("创建 : " +  goodId);
                ShulyTool.run("/console/good", "POST", false, param, function (data) {
                    $("#book-modal").modal("hide");
                }, null);
            } else { // 更新
                console.log("更新 : " +  goodId);
                ShulyTool.run("/console/good/" + goodId, "POST", false, param, function (data) {
                    $("#book-modal").modal("hide");
                }, null);
            }

            var dttable = $('#dataTable').dataTable();
            dttable.fnClearTable(); //清空一下table
            dttable.fnDestroy(); //还原初始化了的datatable
            $("#dataTable").dataTable({
                "lengthChange": false,
                "searching": true,
                "autoWidth": false,
                bJQueryUI:true,
                ajax:  "/console/good/list/summary",
                rowId: 'id',
                columns:[
                    {"data": "id" },
                    {"data": "bookName"},
                    {"data": "bookAuthor"},
                    {"data": "price"},
                    {"data": "sellCnt"},
                    {"data": "tag"},
                    {"data": "stock"}
                ],
            });
        },
        uploadPic: function () {
            console.log("uploadPic")
        },

        destroy: function () {
            console.log("destroy book-good-manager");
        }
    });
});