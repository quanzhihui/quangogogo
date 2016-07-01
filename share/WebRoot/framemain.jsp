<%@page import="org.hibernate.validator.xml.GetterType"%>
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
%>
<html lang="zh-cmn-Hans">
<head>
	<script src="<%=basePath%>example/jquery.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="<%=basePath%>/style/weui.css"/>
    <link rel="stylesheet" href="<%=basePath%>/example/example.css"/> 
    <link rel="stylesheet" href="<%=basePath%>/example/index.css"/> 
      <link rel="stylesheet" href="<%=basePath%>/example/index.css"/> 
	<title>红包分享社区</title>
</head>
<body ontouchstart>

<script>
	var clientwx=<%=request.getSession().getAttribute("clientwx")%>;
	var infoid;
	var type;
//默认关闭
$(document).ready(function(){
$("#dialog1").hide();
$("#dialog2").hide();
$("#dialog3").hide();
	//取消按钮监听
$("#dialog1_cancel").click(function(){
$("#dialog1").hide();
	});
	
$("#dialog2_cancel").click(function(){
$("#dialog2").hide();
$("#dialog1").hide();
	});
	 
$("#dialog3_cancel").click(function(){
$("#dialog3").hide();
$("#dialog2").hide();
$("#dialog1").hide();
	});	 
	 
	 
$("#dialog1_confirm").click(function(){
	
	 $.post("<%=basePath%>/index/interface/info/useticket",
  {
    client:clientwx,
    type:type,
    informationid:infoid
  },
  function(data,status){
  if(data!="null"){
  dataArray=data.split(";");
  
  $("#dialog3").show();
  
  $("#hongbaokouling").html(dataArray[0]);
 var left= dataArray[3]-dataArray[2];
  $("#fk"+infoid).html("已有 "+dataArray[2]+"人看过，目前还有"+left+"人能看");
  
  if(dataArray[1]=="1"){
  
  $("#shoptiaozhuan").html("口令已经放到【我的】中，先去发口令的商家那看看有什么好东东吧。");
  $("#dialog3_confirm").click(function(){
  
  $.post("<%=basePath%>/index/interface/info/redirect",
  {
    informationid:infoid
    
  },
  function(data,status){
     window.parent.location.href=data;   
  });
 
  
  });
  
  
  }else{
  $("#shoptiaozhuan").html("口令已经放到【我的】中，可以随时查看。");
  $("#dialog3_confirm").click(function(){
  
$("#dialog3").hide();
$("#dialog2").hide();
$("#dialog1").hide();
  });

  }
  
 
  
  }else{
  $("#dialog2").show();
  }
    
  });
	  });
	
});
 

</script>

<div class="main">
    
    


      
      
	<!--资讯列表-->
	<div class="news">
		
		<!--资讯列表-->
		<div class="newslist">
		  <% 
      String type=request.getAttribute("type").toString();
      InfoType t=null;
      if(type!=null&&!"".equals(type)){
        t=Enum.valueOf(InfoType.class,type);
      }else{
       t=InfoType.redu;
      }
      List<Information> infolist=KLInfoServices.getInfo(t);
      %>
		 <% for(int i=0;i<infolist.size();i++){ %>
		
			<div class="qtao">
				<div class="mg">
					<div class="mgl"><img src="<%=infolist.get(i).getClientImg()%>" width="100%" /></div>
					<div class="mgr">
						<h3><%=TextUtil.getNameByKlType(infolist.get(i).getType())%></h3>
						<div class="what">
							<div class="time">
								<div class="tub"><img src="images/time.gif" /></div>
								<div class="shij">2014-10-25</div>
								<div class="clearBoth"></div>
							</div>
							<div class="time hit">
								<div class="tub"><img src="images/hit.gif" /></div>
								<div class="shij">1022</div>
								<div class="clearBoth"></div>
							</div>
							<div class="clearBoth"></div>
						</div>
						<div class="jianj">	预计有<%=infolist.get(i).getIntroduct_num() %>个红包，价值<%=infolist.get(i).getIntroduct_acount() %>元
						</div>
						<div class="ydu">
							<div class="anniu"><a href="#"><img src="images/ydu.gif" /></a></div>
							<div class="clearBoth"></div>
						</div>
					</div>
					<div class="clearBoth"></div>
				</div>
			</div>
			 
	
    <%} %>
    
    

		</div>
	</div>
	<!--底部-->
	<div class="bottom">
		<div class="bottom-top">
			<div class="linker">
				<a class="abq" href="#"><img src="images/xlweibo.gif" /></a>
				<a class="abq" href="#"><img src="images/txweibo.gif" /></a>
				<a class="abq" href="#"><img src="images/weixin.gif" /></a>
				<div class="liuy">
					<a href="message.html">
						<div class="tubiao"><img src="images/liuy.gif" /></div>
						<div class="wenzi">在线留言</div>
						<div class="clearBoth"></div>
					</a>
				</div>
				<div class="clearBoth"></div>
			</div>
		</div>
		<div class="bottom-bot">Powered By 居家吧</div>
	</div>
</div>
   






<div class="weui_dialog_confirm"  id="dialog1" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">门票使用</strong></div>
        <div class="weui_dialog_bd">查看口令将消耗一张门票，是否确定使用？</div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog default" id="dialog1_cancel">取消</a>
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog1_confirm">确定</a>
        </div>
    </div>
</div>


<div class="weui_dialog_confirm"  id="dialog2" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">门票使用</strong></div>
        <div class="weui_dialog_bd">门票不足，无法查看红包口令！</div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog default" id="dialog2_cancel">取消</a>
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog2_confirm">查看如何获取门票</a>
        </div>
    </div>
</div>

<div class="weui_dialog_confirm"  id="dialog3" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">红包口令</strong></div>
        <div class="weui_dialog_bd" id="hongbaokouling"></div>
        <div class="weui_dialog_bd" id="shoptiaozhuan"></div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog3_confirm">确定</a>
        </div>
    </div>
</div>





<script >






</script>

	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>



   
	
</body>
</html>