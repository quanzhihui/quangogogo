

wx.ready(function(){
	var tt='快到红包社区抢99元分享大红包';
	var lk='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb99d4d4ba2f634c9&redirect_uri=http%3a%2f%2fwww.o2ohappy.com%2fshare%2findex%2furl%2fmain&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect' ;// 分享链接;
	var img="http://www.o2ohappy.com/share/example/images/logo.png";
	var des='最做好的红包分享社区';
	
	
	  wx.checkJsApi({
	      jsApiList: [
	             'onMenuShareTimeline',
	             'onMenuShareAppMessage',
	             'onMenuShareQQ',
	             'onMenuShareWeibo',
	             'onMenuShareQZone'
	      ],
	      success: function (res) {
	        alert(JSON.stringify(res));
	      }
	    });
	
	
	
	
	
	
wx.onMenuShareTimeline({
    title: tt , // 分享标题
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
});

wx.error(function (res) {
	  alert(res.errMsg);
	});


