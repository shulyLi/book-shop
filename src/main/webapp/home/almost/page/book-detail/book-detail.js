define([
    'jquery', 'cookie', 'underscore', 'backbone',
    'almost/page/book-detail/MyArea',
    'text!almost/page/book-detail/book-detail.html', 'ShulyTool'
], function ($, cookie, _, Backbone, MyArea, html, ShulyTool) {
    var drawDetail = function(data) {
        $("#bookHead").attr("src", data.bookHead);
        $("#bookAuthor a").text(data.bookAuthor);
        $("#stock a").text(data.stock);
        $("#sellCnt a").text(data.sellCnt);
        $("#price a").text(data.price);
        $("#betterPart a").text(data.betterPart);
        $("#bookName").text("《" + data.bookName + "》");
        $("#bookDetail").text(data.detail);
        $("#book-part").html(data.index.replace(/\n/g, "<br>"));
        /**
            <span class="label label-danger">UI Design</span>
         */
        var tagClass = ['label-primary', 'label-info', 'label-success', 'label-danger', 'label-warning'];
        var tagStr = "";
        var tagList = data.tag.split(",");
        if (tagList.length === 1 && tagList[0] === "") {
            tagList.push("无标签");
        }
        $.each(tagList, function (idx, value) {
            var item = parseInt((Math.random() * 100)) % 5;
            tagStr += '<span class="label ' + tagClass[item] + '">' + value + '</span> ';
        });

        $("#tag").html(tagStr);
        $("#book-id").val(data.id);
        var str =　bookTableStr(data);
        $("#dealModle #goodTable tbody").empty();
        $("#dealModle #goodTable tbody").append(str);
        $('#dealNum').bind('input propertychange', function(){
            var num=$(this).val();
            $("#dealPriceTable").empty();
            $("#dealPriceTable").append('<tr><th style="width:50%">商品价格:</th><td>￥'+ data.price  / 100.0+'</td></tr>');
            $("#dealPriceTable").append('<tr><th style="width:50%">运费:</th><td>￥'+ 20.00+'</td></tr>');
            $("#dealPriceTable").append('<tr><th style="width:50%">总价:</th><td>￥'+(2000 + data.price * num) / 100.0 +'</td></tr>');
        });
    };
    var bookTableStr=  function (good) {
        return "<tr class='warning'>" +
            "<th>"+good.bookName+"</th>" +
            "<th ><img class='img-responsive' width='100%' src="+good.bookHead+" ></th>" +
            "<th>"+good.bookAuthor+"</th>" +
            "<th>"+good.price / 100.+"</th>" +
            "<th>" +
            "<div class='input-group'>" +
            "<input id = 'dealNum'type='text' " + "class='form-control'>" +
            "<span class='input-group-addon'>本</span></div>"+
            "</th>" +
            "</tr>";
    };
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
            ShulyTool.run("/console/good/" + goodId, "GET", false, null, function (data) {
                drawDetail(data);
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
                $("#dealModle").modal("hide");
                ShulyTool.sleep(300);
                window.location.href = "#book-order-manager"
            }, null);
        },
        destroy: function () {
            console.log("destroy book-detail");
        }
    });
});