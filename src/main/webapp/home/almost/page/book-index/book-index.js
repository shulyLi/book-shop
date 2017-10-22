define([
    'underscore', 'backbone','ShulyTool',
    'text!almost/page/book-index/book-index.html'
], function ( _, Backbone, ShulyTool, html) {

    return Backbone.View.extend({
        id: 'book-index',
        fragment: document.createDocumentFragment(),
        $pageWrapper: $('#page-wrapper'),
        template: _.template(html),
        events: {
            "click #query-submit": 'clickQuery'
        },
        father:null,
        num:0,
        pageNum:0,
        initialize: function () {
            this.render();
        },
        render: function () {
            var fragment = this.fragment;
            this.$el = $(this.template()).appendTo(fragment);
            this.$pageWrapper.html(fragment);
            this.setElement(this.$pageWrapper.find('#' + this.id));
            this.init();
        },
        init: function () {
            this.initTag();
            this.queryBook();
            $(window).scroll(function(){
                if(Math.abs($(document).scrollTop() + $(window).height() - $(document).height())<=0.23) {
                    console.log("button")
                    this.queryBook();
                }
            });
        },

        initTag: function() {
            // todo 目前 类型是 写死的.
            $("#query-book-tags").select2();
        },
        initBody: function () {
            this.father=null;
            this.num=0;
            this.pageNum=0;
            $("#book-body").empty();
        },
        clickQuery: function () {
            this.initBody();
            this.queryBook();
        },
        queryBook: function () {
            var tagStr = $("#query-book-tags").select2('val');
            if (tagStr === null) {
                tagStr = "";
            } else {
                tagStr = tagStr.toString();
            }
            var param = {
                page : this.pageNum,
                name : $("#query-data").val(),
                tags : tagStr

            };
            var appendStr = this.appendStr;
            var addRow = this.addRow;
            var num = this.num;
            var pageNum = this.pageNum;
            var father  = this.father;
            ShulyTool.run("/console/good/index/summary", "GET", false, param, function (data) {
                $.each(data, function (idx, value) {
                    if(num %4 === 0){
                        father = addRow(num);
                    }
                    father.append(appendStr(value));
                    num++;
                });
                pageNum++;
            }, null);
        },
        addRow: function(num) {
            var id = "father" + num;
            var rowStr='<div class="row Delete" id ="'+id+'"> </div>';
            $("#book-body").append(rowStr);
            return $("#"+id);
        },
        appendStr: function (data) {
            var divStr=
                '<section class="col-sm-3 box-primry">'+
                    '<div class="box" >'+
                        '<div class="box-header with-border">'+
                            '<h3 class="box-title">'+data.bookName+'</h3>'+
                        '</div>'+
                        '<div class="box-body">'+
                            '<a href="#book-detail?goodId='+data.id +'" >'+
                                '<img class ="img-responsive img-thumbnail" src="'+data.bookHead+'">' +
                            '</a>'+
                        '</div>'+
                        '<div class="box-footer">' +
                            '<p >'+data.tag+'</p>'+
                            '<p >销量：'+data.sellCnt+'，作者:'+data.bookAuthor+'</p>'+
                        '</div>'+
                    '</div>'+
                '</section>';
            return divStr;
        },
        destroy: function () {
            console.log("destroy book-index");
        }
    });

});