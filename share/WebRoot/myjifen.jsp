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
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String clientwx=(String)request.getSession().getAttribute("clientwx");
Client client=ClientServer.getClientInfo(clientwx);
%>
<html lang="zh-cmn-Hans">
<head>
	<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script src="http://cdn.bootcss.com/zclip/1.1.2/ZeroClipboard.swf"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="<%=path%>/style/weui.css"/>
    <link rel="stylesheet" href="<%=path%>/example/example.css"/> 
	<title>我的积分</title>

</head>

<body ontouchstart>
<script type="text/javascript">
var clientwx=<%=client.getClientWxid()%>;






$(document).ready(function(){
   $("#duihuanchenggong").hide();
 
   $("#dhmpbutton").click(function(){
 $.post("<%=path%>/index/interface/client/trans",
  {
    clientwx:clientwx
  },
  function(data,status){
    $("#duihuanchenggong").show();
    $("#duihuanchenggong_title").html("兑换成功");
    $("#duihuanjieguo").html("每100分可兑换一张门票，您已成功兑换"+data+"张门票！");
  });
 });
 
 
 $("#duihuanchenggong_confirm").click(function(){
  $("#duihuanchenggong").hide();
  });


 
 $("#shengchenglianjie").click(function(){
   
   if( $("#tuiguanglianjie").val()!=""&&$("#tuiguanglianjie").val()!="null"){
   $("#duihuanchenggong").show();
    $("#duihuanchenggong_title").html("生成链接失败");
    $("#duihuanjieguo").html("已经生成过推广链接，无法重复生成。");
   }else{
   
    $.post("<%=path%>/index/interface/client/gettuiguang",
  {
    clientwx:clientwx
  },
  function(data,status){
  
    $("#duihuanchenggong").show();
    $("#duihuanchenggong_title").html("生成链接成功");
    $("#duihuanjieguo").html("");
    $("#tuiguanglianjie").val(data);
  });
   }   
  });


$("#fuzhilianjie").click(function(){
 
   $('#tuiguanglianjie').zclip({ 
        path: 'http://cdn.bootcss.com/zclip/1.1.2/ZeroClipboard.swf', 
        copy: function(){//复制内容 
            return $('#fuzhilianjie').val(); 
        }, 
        afterCopy: function(){//复制成功 
            $("<span id='msg'/>").insertAfter($('#copy_input')).text('复制成功'); 
        } 
    }); 
}); 
 
 




});  
 
  </script>
     <div class="bd">
   <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">门票数：</label></div>
            <div class="weui_cell_bd ">
                <input class="weui_input" type="text" readonly="readonly"  id="mps"  value="<%=client.getTicket()%>"/>       
                <div id="error_kouling">  </div> 
            </div>
        </div>

        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">优惠豆：</label></div>
            <div class="weui_cell_bd ">
                <input class="weui_input" type="text" readonly="readonly"  id="dhmp"  value="<%=client.getScore()%>"/>  
                <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_primary" id="dhmpbutton">兑换门票</a>     
                <div id="error_kouling">  </div> 
            </div>
        </div>
        
        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">我的推广：</label></div>
            <div class="weui_cell_bd ">
                <input class="weui_input" type="text" readonly="readonly"  id="tuiguanglianjie"  value="<%=client.getTgurl()%>"/> 
                <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_primary" id="shengchenglianjie">生成链接</a>
                <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_primary" id="fuzhilianjie">复制链接</a>
                      
                <div id="error_kouling">  </div> 
            </div>
        </div>
   </div>     
   
   
   <div class="weui_dialog_confirm"  id="duihuanchenggong" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title" id="duihuanchenggong_title"></strong></div>
        <div class="weui_dialog_bd" id="duihuanjieguo"></div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="duihuanchenggong_confirm">确定</a>
        </div>
    </div>
</div>
   
   
   
   
	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
	<script src="<%=path%>/example/jquery.form.min.js"></script>
	<script src="<%=path%>/example/jquery.zclip.min.js"></script>
</body>
</html>