function AJAX(_url, _type, _async, _data, _func) {
    $.ajax({
        url: _url,
        type: _type,
        async: _async,
        data: _data,
        timeout: 5000,       //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        beforeSend: function (xhr) {
        },
        success: function (data, textStatus, jqXHR) {
            if (data.code != 0) {
                if (data.msg != null && data.msg != "") {
                    alert(data.msg);
                }
            } else {
                _func(data.data);
            }
        },
        error: function (xhr, textStatus) {
        },
        complete: function () {
        }
    })
}