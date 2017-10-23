define([],function () {
    function sleep(n) {
        var start = new Date().getTime();
        while (true) if (new Date().getTime() - start > n) break;
    }
    var run = function ( _url , _type ,_async ,  _data ,_func , filed) {
        $.ajax({
            url:_url,
            type: _type,
            async:_async,
            data:_data,
            //timeout:8000,       //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            beforeSend:function(xhr){
                if(filed != null)
                    $(filed).show();
            },
            success:function(data,textStatus,jqXHR){
                _func(data);
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
                sleep(1);
                if (filed != null) {
                    $(filed).hide();
                }
            }
        })
    };
    return{
        run:run,
        sleep:sleep
    }
});