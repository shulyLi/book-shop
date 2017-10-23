define([
    'jquery', 'cookie', 'underscore', 'backbone',
    'almost/page/book-detail/MyArea',
    'text!almost/page/book-detail/book-detail.html', 'ShulyTool'
], function ($, cookie, _, Backbone, MyArea, html, ShulyTool) {
    return Backbone.View.extend({
        id: 'book-detail',
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
            MyArea.ojbk();// select初始化
        },
        start: function () {
            var goodId = window.location.hash.substring(20);
            var trans = this;

            ShulyTool.run("/console/good/" + goodId, "GET", false, null, function (data) {
                $("#bookHead").attr("src", data.bookHead);
                $("#bookAuthor a").text(data.bookAuthor);
                $("#stock a").text(data.stock);
                $("#sellCnt a").text(data.sellCnt);
                $("#price a").text(data.price);
                $("#betterPart a").text(data.betterPart);
                $("#bookName").text(data.bookName);
                $("#tag").text(data.tag);
                trans.good = data;
                $("#book-id").val(trans.good.id);
                var str =　trans.bookTableStr(trans.good);
                $("#dealModle #goodTable tbody").empty();
                $("#dealModle #goodTable tbody").append(str);
                $('#dealNum').bind('input propertychange', function(){
                    var num=$(this).val();
                    $("#dealPriceTable").empty();
                    $("#dealPriceTable").append('<tr><th style="width:50%">商品价格:</th><td>￥'+ trans.good.price * 0.01+'</td></tr>');
                    $("#dealPriceTable").append('<tr><th style="width:50%">运费:</th><td>￥'+ 20.0+'</td></tr>');
                    $("#dealPriceTable").append('<tr><th style="width:50%">总价:</th><td>￥'+(20 + trans.good.price * num * 0.01) +'</td></tr>');
                });
            }, null);
            $("#deal-submit").click(this.dealSubmit);
        },
        dealSubmit: function () {
            var num = $('#dealNum').val();
            if (isNaN(num) || num <= 0) {
                alert("这个数字不对吧？")
                return;
            }
            var buyPhone = $("#buyPhone").val();
            if (isNaN(buyPhone) || buyPhone < 13000000000 || buyPhone >= 19900000000) {
                alert("电话号码不对啊!");
                return;
            }
            var param = {
                'bookId':   $("#book-id").val(),
                'cnt':      num,
                'detailAddress': $("#add").val(),
                'province': $("#s_province").val(),
                'city':     $("#s_city").val(),
                'county':   $("#s_county").val(),
                'buyName':  $("#buyName").val(),
                'buyPhone': $("#buyPhone").val()
            };
            ShulyTool.run("/console/order", "POST", false, param, function (data) {

            }, null);
        },
        bookTableStr: function (good) {
            return "<tr class='warning'>" +
            "<th>"+good.bookName+"</th>" +
            "<th ><img class='img-responsive' width='100%' src="+good.bookHead+" ></th>" +
            "<th>"+good.bookAuthor+"</th>" +
            "<th>"+good.price+"</th>" +
            "<th>" +
            "<div class='input-group'>" +
                "<input id = 'dealNum'type='text' " + "class='form-control'>" +
                "<span class='input-group-addon'>本</span></div>"+
            "</th>" +
            "</tr>";
        },
        destroy: function () {
            console.log("destroy book-detail");
        }
    });
});