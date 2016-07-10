<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="service.*"%>
<%@page import="java.util.List"%>
<%@page import="bean.*"%>
<%@page import="util.*"%>
<%@page import="dao.*"%>
<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path ;
%>
<html lang="zh-cmn-Hans">
<head>
	<script src="<%=basePath%>/example/jquery.min.js"></script>

	
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="<%=basePath%>/style/weui.css"/>
    <link rel="stylesheet" href="<%=basePath%>/example/example.css"/> 
 
 
    
	<title>抢红包社区</title>
    

</head>

<body >

<script>
$(document).ready(function(){
var clientwx='<%=request.getSession().getAttribute("clientwx")%>';


$("#redu").click(function(){
 
 $("#redu").addClass("weui_bar_item_on");
 $("#ontime").removeClass("weui_bar_item_on");
 $("#history").removeClass("weui_bar_item_on");

  document.getElementById("mainframe").src="<%=path%>/index/info/tab/framemain/redu.jsp";
 });
 
 $("#ontime").click(function(){
 $("#ontime").addClass("weui_bar_item_on");
 $("#redu").removeClass("weui_bar_item_on");
 $("#history").removeClass("weui_bar_item_on");
 document.getElementById("mainframe").src="<%=path%>/index/info/tab/framemain/ontime.jsp";
 });
 
 $("#history").click(function(){
 $("#history").addClass("weui_bar_item_on");
 $("#redu").removeClass("weui_bar_item_on");
 $("#ontime").removeClass("weui_bar_item_on");
  document.getElementById("mainframe").src="<%=path%>/index/info/tab/framemain/history.jsp";
 });
 
 $("#dialog_qiandao").hide();
 $("#fenxiang").hide();
 
 $("#qiandao").click(function(){

 $.post("<%=path%>/index/interface/client/qiandao",
  {
    client:clientwx
  },
  function(data,status){
  if(data=="1"){
  
   $("#qiandaotitle").html("签到成功");
   $("#qiandaoresult").html("签到成功送50优惠豆，快到【我的】里兑换门票吧！");
  }else{
   $("#qiandaotitle").html("已签到过");
   $("#qiandaoresult").html("今日已签到，明天再来吧！");
  }
  $("#dialog_qiandao").show();
  });

 });
 
 $("#dialog_qiandao_confirm").click(function(){
$("#dialog_qiandao").hide();
 });
 
 $("#fakouling").click(function(){
	window.location.href="<%=path%>/index/url/fakouling";
 });
 
  $("#wode").click(function(){
	window.location.href="<%=path%>/index/url/mine/main";
 });
 
 $("#shangjiarukou").click(function(){
	window.location.href="<%=path%>/index/url/shop/shopdl";
 });
 $("#dialog_fenxiang_confirm").click(function(){
	 $("#fenxiang").hide();
 });
 
 
 });

</script>

<div class="bd" style="height:8%;"  >
    <div class="weui_tab" style = "background-color:#F7F1F1";>
        <div class="weui_navbar"   >
            <div class="weui_navbar_item  weui_bar_item_on"  id='redu'>
                热度红包
            </div>
            <div class="weui_navbar_item"   id='ontime'>
                即将开抢
            </div>
            <div class="weui_navbar_item"   id='history'>
                最新红包
            </div>
        </div>
      
    </div>
</div>
     


<iframe class="weui_panel_bd"  height=80% width=100%  src="<%=path%>/index/info/tab/framemain/redu.jsp"   scrolling="yes" frameborder="1" id="mainframe"> </iframe>



    <div class="weui_tabbar">
        <a href="javascript:;" class="weui_tabbar_item weui_bar_item_on"  id="qiandao">
            <div class="weui_tabbar_icon">
                <img src="<%=path%>/example/images/icon_nav_button.png" alt="">
            </div>
            <p class="weui_tabbar_label">签到</p>
        </a>
        <a href="javascript:;" class="weui_tabbar_item" id="fakouling">
            <div class="weui_tabbar_icon">
                <img src="<%=path%>/example/images/icon_nav_msg.png" alt="">
            </div>
            <p class="weui_tabbar_label" >发口令</p>
        </a>
        <a href="javascript:;" class="weui_tabbar_item" id="wode">
            <div class="weui_tabbar_icon">
                <img src="<%=path%>/example/images/icon_nav_article.png" alt="">
            </div>
            <p class="weui_tabbar_label" >我的</p>
        </a>
        <a href="javascript:;" class="weui_tabbar_item" id="shangjiarukou">
            <div class="weui_tabbar_icon">
                <img src="<%=path%>/example/images/icon_nav_cell.png" alt="">
            </div>
            <p class="weui_tabbar_label" >商家入口</p>
        </a>
    </div>


<div class="weui_dialog_confirm"  id="dialog_qiandao" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title" id="qiandaotitle"></strong></div>
        <div class="weui_dialog_bd" id="qiandaoresult"></div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_qiandao_confirm">确定</a>
        </div>
    </div>
</div>

<div class="weui_dialog_confirm"  id="fenxiang" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title"></strong></div>
        <div class="weui_dialog_bd" >分享成功，赠送20积分</div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_fenxiang_confirm">确定</a>
        </div>
    </div>
</div>




	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script>
	
	wx.config({
    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: '<%=TokenServer.getappId()%>', // 必填，公众号的唯一标识
    timestamp: '<%=request.getSession().getAttribute("wxtime")%>', // 必填，生成签名的时间戳
    nonceStr: '<%=TokenServer.getNoncestr()%>', // 必填，生成签名的随机串
    signature: '<%=request.getSession().getAttribute("signname")%>',// 必填，签名，见附录1
    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});


	
	</script>

    <script src="<%=basePath%>/example/wx.js"></script>	

   
	
</body>
</html>