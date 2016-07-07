
function onload(id,time,nstr,sign){

wx.config({
    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: id, // 必填，公众号的唯一标识
    timestamp: time, // 必填，生成签名的时间戳
    nonceStr: nstr, // 必填，生成签名的随机串
    signature: sign,// 必填，签名，见附录1
    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});

var tt='快到红包社区抢99元分享大红包';
var lk='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb99d4d4ba2f634c9&redirect_uri=http%3a%2f%2fwww.o2ohappy.com%2fshare%2findex%2furl%2fmain&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect' ;// 分享链接;
var img='';
var des='最做好的红包分享社区';
wx.onMenuShareTimeline({
    title:tt , // 分享标题
    link: lk,
    imgUrl: img, // 分享图标
    success: function () { 
    	 $("#fenxiang").show();
    },
    cancel: function () { 
        // 用户取消分享后执行的回调函数
    }
});


wx.onMenuShareAppMessage({
	title: tt, // 分享标题
    desc: des, // 分享描述
    link: lk, // 分享链接
    imgUrl: img, // 分享图标
    type: 'link', // 分享类型,music、video或link，不填默认为link
    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
    success: function () { 
    	 $("#fenxiang").show();
    },
    cancel: function () { 
    	
    }
});

wx.onMenuShareQQ({
    title: tt, // 分享标题
    desc: des, // 分享描述
    link: 'link', // 分享链接
    imgUrl: img, // 分享图标
    success: function () { 
    	 $("#fenxiang").show();
    },
    cancel: function () { 
       // 用户取消分享后执行的回调函数
    }
});

wx.onMenuShareWeibo({
    title: tt, // 分享标题
    desc: des, // 分享描述
    link: 'link', // 分享链接
    imgUrl: img, // 分享图标
    success: function () { 
   	 $("#fenxiang").show();
    },
    cancel: function () { 
        // 用户取消分享后执行的回调函数
    }
});


wx.onMenuShareQZone({
    title: tt, // 分享标题
    desc: des, // 分享描述
    link: 'link', // 分享链接
    imgUrl: img, // 分享图标
    success: function () { 
    	 $("#fenxiang").show();
    },
    cancel: function () { 
        // 用户取消分享后执行的回调函数
    }
});
}