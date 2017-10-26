define([
    'underscore', 'backbone', 'ShulyTool',
    'text!almost/page/book-index/book-index.html'
], function (_, Backbone, ShulyTool, html) {
    var addRow =  function (num) {
        var id = "father" + num;
        var rowStr = '<div class="row Delete" id ="' + id + '"> </div>';
        $("#book-body").append(rowStr);
        return $("#" + id);
    };
    var appendStr=  function (data) {
        return '<section class="col-sm-3 box-primry">' +
            '<div class="box" >' +
            '<div class="box-header with-border">' +
            '<h3 class="box-title">' + data.bookName + '</h3>' +
            '</div>' +
            '<div class="box-body">' +
            '<a href="#book-detail?goodId=' + data.id + '" >' +
            '<img class ="img-responsive img-thumbnail" src="' + data.bookHead + '">' +
            '</a>' +
            '</div>' +
            '<div class="box-footer">' +
            '<p >' + data.tag + '</p>' +
            '<p >销量：' + data.sellCnt + '，作者:' + data.bookAuthor + '</p>' +
            '</div>' +
            '</div>' +
            '</section>';
    };
    var queryParam = {
        father: null,
        num: 0,
        pageNum: 0
    };
    var initParam = function () {
        queryParam.father  = null;
        queryParam.num     = 0;
        queryParam.pageNum = 0;
    };
    var queryBook = function () {
        var tagStr = $("#query-book-tags").select2('val');
        if (tagStr === null) {
            tagStr = "";
        } else {
            tagStr = tagStr.toString();
        }
        var param = {
            page: queryParam.pageNum,
            name: $("#query-data").val(),
            tags: tagStr
        };
        ShulyTool.run("/console/good/index/summary", "GET", false, param, function (data) {
            $.each(data, function (idx, value) {
                if (queryParam.num % 4 === 0) {
                    queryParam.father = addRow(queryParam.num);
                }
                queryParam.father.append(appendStr(value));
                queryParam.num = queryParam.num + 1;
            });
            queryParam.pageNum = queryParam.pageNum + 1;
        }, null);
    };
    return Backbone.View.extend({
        id: 'book-index',
        fragment: document.createDocumentFragment(),
        $pageWrapper: $('#page-wrapper'),
        template: _.template(html),
        events: {
            "click #query-submit": 'clickQuery'
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
            initParam();
            $("#query-book-tags").select2();
            $("#book-body").empty();
            queryBook();
            $(window).scroll(function () {
                if (Math.abs($(document).scrollTop() + $(window).height() - $(document).height()) <= 0.23) {
                    console.log("button");
                    queryBook();
                }
            });
        },
        clickQuery: function () {
            $("#book-body").empty();
            initParam();
            queryBook();
        },
        destroy: function () {
            console.log("destroy book-index");
        }
    });

});