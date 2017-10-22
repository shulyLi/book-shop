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
            alert(1);
            this.initBody();
            this.queryBook();
        },
        queryBook: function () {
            var param = {
                page : this.pageNum,
                name : $("#query-data").val(),
                tags : $("#query-book-tags").select2('val')
            };
            ShulyTool.run("/console/good/index/summary", "GET", false, param, function (data) {
/*                $.each(data, function (idx, value) {
                    if(this.num %4 === 0){
                        this.addRow();
                    }
                    this.append(value);
                    this.num++;
                });*/
                this.pageNum++;
            }, null);
        },


        addRow: function() {
            var id="father"+num;
            var rowStr='<div class="row Delete" id ="'+id+'"> </div>';
            $("#book-body").append(rowStr);
            this.father = $("#"+id);
        },
        append: function (data) {
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
                            '<p >'+item.tag+'</p>'+
                            '<p >销量：'+data.sellCnt+'，作者:'+data.bookAuthor+'</p>'+
                        '</div>'+
                    '</div>'+
                '</section>';
            this.father.append(divStr);
        },
        destroy: function () {
            console.log("destroy book-index");
        }
    });

});