define([
    'jquery', 'underscore', 'backbone',
    'text!almost/page/book-good-manager/book-good-manager.html', 'ShulyTool'
], function ($, _, Backbone, html, ShulyTool) {
    var what = function () {

        var table = $("#dataTable").dataTable({
            "lengthChange": false,
            "searching": true,
            "autoWidth": false,
            bJQueryUI: true,
            order: [[0, "desc"]],
            columns: [
                {"data": "id"},
                {"data": "bookName"},
                {"data": "bookAuthor"},
                {"data": "price"},
                {"data": "sellCnt"},
                {"data": "tag"},
                {"data": "stock"}
            ],
        });
        $('#dataTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                var goodId = $(this).children("td:first-child").html();
                resolve(goodId);
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
    }
    var resolve =  function(goodId) {
        console.log("changeId : " +  goodId);
        $("#book-id").val(goodId);
        ShulyTool.run("/console/good/" + goodId, "GET", false, null, function (data) {
            $("#book-modal").modal("show");
            $.each(data, function(name, value) {
                if (name === 'id') return
                if (name === 'bookHead') {
                    $("#bookHead").attr("src", value);
                }
                if (name === 'tag') {
                    $("#tag").select2("val", value.split(','));
                } else {
                    $("#" + name).val(value)
                }
            });

        }, null);
    };
    var initTable= function() {
        ShulyTool.run("/console/good/list/summary", "GET", false, null, function (data) {
            var table = $('#dataTable').dataTable();
            table.fnClearTable(); //清空一下table
            table.fnDestroy(); //还原初始化了的datatable
            $("#dataTable").dataTable({
                "lengthChange": false,
                "searching": true,
                "autoWidth": false,
                bJQueryUI:true,
                order: [[ 0, "desc" ]],
                data:data,
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
        }, null);
    };
    return Backbone.View.extend({
        id: 'book-good-manager',
        fragment: document.createDocumentFragment(),
        $pageWrapper: $('#page-wrapper'),
        template: _.template(html),
        events: {
            "click #book-create" : "createBook",
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
            $("#book-pic-submit").click(this.uploadPic);
        },
        start: function () {
            what();
            initTable();
        },
        createBook: function () {
            console.log("create Book");
            $("#book-id").val("");
            $("#book-modal").modal("show");
        },
        postHttpBook: function () {
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
                bookHead: $("#bookHead").attr("src"),
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
            initTable();
        },
        uploadPic: function () {
            var formData = new FormData();
            formData.append("picture", $( "#choose-pic" )[0].files[0]);
            $.ajax({
                url : "/console/upload/book/head",
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success:function(data,textStatus,jqXHR){
                    $("#bookHead").attr("src", data);
                },
                error:function(xhr,textStatus){
                    console.log(xhr);
                    if (xhr.responseJSON.err === "NoSuchUser") {
                        window.location.href = "/view/login.html";
                        return
                    }
                    alert("错误代码:" + xhr.status + "\n具体错误:" + xhr.responseJSON.err+ "\n具体信息:" + xhr.responseJSON.msg);
                },
                complete:function() {
                }
            });
        },

        destroy: function () {
            console.log("destroy book-good-manager");
        }
    });
});