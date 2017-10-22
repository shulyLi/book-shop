define(['zepto', 'underscore', 'jweixin-1.0.0'], function ($, _, wx) {
    /*二次分享处理函数  */
//shareInfo  duixiang
    function shareInfoFn(shareInfo) {
        var wxShareRoute = [
            'onMenuShareTimeline',//朋友圈
            'onMenuShareAppMessage',//微信好友
            'onMenuShareQQ',//qq好友
            'onMenuShareWeibo',//腾讯微博
            'openLocation',
            'getLocation'
        ];
        wxShareRoute.forEach(function (route) {
            wx[route]({
                title: shareInfo.title, // 分享标题
                desc: shareInfo.desc,//分享内容
                link: shareInfo.link, // 分享链接
                imgUrl: shareInfo.imgUrl, // 分享图标
                success: function (res) {
                    // 用户确认分享后执行的回调函数
                    // alert(res.errMsg);
                },
                cancel: function (res) {
                    // 用户取消分享后执行的回调函数
                    //  alert(res.errMsg);
                }
            })
        });
    }

    /*微信检查*/
// 1 判断当前版本是否支持指定 JS 接口，支持批量判断
    function shareCheck() {
        wx.checkJsApi({
            jsApiList: [
                'getNetworkType',
                'previewImage'
            ],
            success: function (res) {
                // alert(JSON.stringify(res));
            }
        });
    }

    function shareWxError() {
        wx.error(function (res) {
            alert('微信错误: ' + JSON.stringify(res));
            // alert(res.errMsg);
        });
    }
    /*微信配置*/
    function shareConfig(obj) {
        var appId = 'wx0bd1d7499bc95dcc';
        wx.config({
            debug: false,
            appId: appId,
            timestamp: obj.timestamp,
            nonceStr: obj.noncestr,
            signature: obj.signature,
            jsApiList: [
                'checkJsApi',
                'onMenuShareTimeline',
                'onMenuShareAppMessage',
                'onMenuShareQQ',
                'onMenuShareWeibo',
                'openLocation',
                'getLocation'
            ]
        });
    }

//shareInfo  分享到朋友圈与其他文案不一致时
////分享成功调用接口时，需传入type，type需约定
////兼容直接分享，及以前晚餐券，717活动
    function shareExceptPYQ(shareInfo, shareInfoPyq) {
        //alert(shareInfo.link)
        var wxShareRoute = [
            'onMenuShareAppMessage',//微信好友
            'onMenuShareQQ',//qq好友
            'onMenuShareWeibo',//腾讯微博
            'onMenuShareTimeline'//朋友圈
        ];
        wxShareRoute.forEach(function (route) {
            var links = shareInfoPyq.link;
            if (route != "onMenuShareTimeline") {
                wx[route]({
                    title: shareInfo.title, // 分享标题
                    desc: shareInfo.desc,//分享内容
                    link: shareInfo.link, // 分享链接
                    imgUrl: shareInfo.imgUrl, // 分享图标
                    success: function (res) {
                        if (shareInfo.share_type && shareInfo.share_type != '') {
                            var type = shareInfo.share_type;
                            //alert(type);
                            if (type == 201508) {
                                var phone = shareInfo.phone;
                                if (phone) {
                                    CHJCF817Coupons(phone, "weiXin", "coupon", "weiXin", shareInfoPyq.urlShareKey, shareInfoPyq.shareKeyNew);
                                }
                            } else if (type == 20150802) {
                                var phone = shareInfo.phone;
                                if (phone) {
                                    get817ReCoupons(phone, "weiXin", "coupon", "weiXin", shareInfoPyq.urlShareKey, shareInfoPyq.shareKeyNew);
                                }
                            } else if (type == 20150906) {
                                var phone = shareInfo.phone;
                                get(phone, "share");
                                //location.reload();
                                window.location.href = window.location.href;
                            } else if (type == 2015091401) {
                                location.reload();
                            } else if (type == 2015091402) {
                                //alert(2222)
                                location.href = shareInfo.href;
                            } else if (type == 20150917) {
                                //alert(2222)

                                var phone = shareInfoPyq.phone;
                                CHJCF917Coupons(phone, "weiXin", "coupon", "weiXin", shareInfoPyq.urlShareKey, shareInfoPyq.shareKeyNew);
                                $(".bgMen").hide();
                            }
                        } else {
                            if (shareInfo.phone && shareInfo.shareKey && shareInfo.newShareKey) {
                                var phone = shareInfo.phone;
                                var shareKey = shareInfo.shareKey;
                                var newShareKey = shareInfo.newShareKey;
                                jumpPage(phone, shareKey, newShareKey);
                            } else if (shareInfo.phone) {
                                var phone = shareInfo.phone;
                                cWorkTimeCoupon("", phone, "");
                            }
                        }
                        // alert(1);
                        $(".bgMen").hide();
                    },
                    cancel: function (res) {
                        // alert(shareInfoPyq.share_type)
                        if (shareInfoPyq.share_type && shareInfoPyq.share_type != '') {
                            var type = shareInfoPyq.share_type;
                            if (type == 2015091401) {
                                //影藏蒙版
                                $(".men_box").hide();
                                $(".men_box").find("img").hide();
                            } else if (type == 2015091402) {
                                $(".men_box").hide();
                                $(".men_box").find("img").hide();
                            }
                        }
                        $(".bgMen").hide();
                    }
                })
            } else {
                wx[route]({
                    title: shareInfoPyq.title, // 分享到朋友圈标题
                    desc: shareInfoPyq.desc,//分享到朋友圈内容
                    link: shareInfoPyq.link, // 分享到朋友圈链接
                    imgUrl: shareInfoPyq.imgUrl, // 分享到朋友圈图标
                    success: function (res) {
                        if (shareInfoPyq.share_type && shareInfoPyq.share_type != '') {
                            var type = shareInfoPyq.share_type;
                            //alert(type);
                            // alert(shareInfoPyq.share_phone);
                            if (type == 201508) {
                                var phone = shareInfoPyq.phone;
                                CHJCF817Coupons(phone, "weiXin", "coupon", "weiXin", shareInfoPyq.urlShareKey, shareInfoPyq.shareKeyNew);
                            } else if (type == 20150802) {
                                var phone = shareInfo.phone;
                                if (phone) {
                                    get817Coupons(phone, "weiXin", "coupon", "weiXin", shareInfoPyq.urlShareKey, shareInfoPyq.shareKeyNew);
                                }
                            } else if (type == 20150906) {
                                var phone = shareInfo.phone;
                                get(phone, "share");
                                //location.reload();
                                window.location.href = window.location.href;
                                // alert(1);
                            } else if (type == 2015091401) {
                                //alert(1);
                                location.reload();
                            } else if (type == 2015091402) {
                                //alert(2);
                                location.href = shareInfo.href;
                            } else if (type == 20150917) {
                                //alert(2222)
                                var phone = shareInfoPyq.phone;
                                CHJCF917Coupons(phone, "weiXin", "coupon", "weiXin", shareInfoPyq.urlShareKey, shareInfoPyq.shareKeyNew);
                                $(".bgMen").hide();
                            }
                        } else {
                            if (shareInfoPyq.phone && shareInfoPyq.shareKey && shareInfoPyq.newShareKey) {
                                var phone = shareInfoPyq.pWhone;
                                var shareKey = shareInfoPyq.shareKey;
                                var newShareKey = shareInfoPyq.newShareKey;
                                jumpPage(phone, shareKey, newShareKey);
                            } else if (shareInfoPyq.phone) {
                                var phone = shareInfoPyq.phone;
                                cWorkTimeCoupon("", phone, "");
                            }


                        }
                        $(".bgMen").hide();
                    },
                    cancel: function (res) {
                        //alert(res.msg)
                        if (shareInfoPyq.share_type && shareInfoPyq.share_type != '') {
                            var type = shareInfoPyq.share_type;
                            if (type == 2015091401) {
                                //影藏蒙版
                                $(".men_box").hide();
                                $(".men_box").find("img").hide();
                            } else if (type == 2015091402) {
                                //影藏蒙版
                                $(".men_box").hide();
                                $(".men_box").find("img").hide();
                            }
                        }
                        $(".bgMen").hide();
                    }
                })
            }
        });
    }

    function weiXinShare(shareInfo, shareInfoPyq) {
        var curUrl = _.urlencode(location.href.split('#')[0]);
        var url = '/Activity/getWXSignature?url=' + curUrl;
        $.ajax({
            url: url,
            dataType: 'json',
            success: function (data) {
                if (data.code == 0) {
                    shareConfig(data.data);
                    wx.ready(function () {
                        // 1 判断当前版本是否支持指定 JS 接口，支持批量判断
                        shareCheck();
                        // 2. 分享接口
                        // 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口   user=key
                        shareExceptPYQ(shareInfo, shareInfoPyq)
                    });
                } else {
                    shareWxError();
                }
            },
            error: shareWxError
        })//var data = sendGet(url);

    }
    shareWxError();
    return {
        weiXinShare: weiXinShare
    }
});

