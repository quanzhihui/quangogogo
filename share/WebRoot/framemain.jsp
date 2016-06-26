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
    <link rel="stylesheet" href="<%=path%>/style/weui.css"/>
    <link rel="stylesheet" href="<%=path%>/example/example.css"/> 
	<title>红包分享页面</title>
</head>
<body ontouchstart>

<script>
	var clientwx=<%=100%>;
	var infoid;
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
	
	 $.post("<%=path%>/index/interface/info/useticket",
  {
    client:clientwx,
    type:"ticket",
    informationid:infoid
  },
  function(data,status){
  if(data!="null"){
  $("#dialog3").show();
  $("#hongbaokouling").html(data);
  
  
   $.post("<%=path%>/index/interface/info/gettype",
  {
    informationid:infoid
  },
  function(data,status){
  if(data=="1"){
  
  $("#shoptiaozhuan").html("口令已经放到【我的】中，先去发口令的商家那看看有什么好东东吧。");
  $("#dialog3_confirm").click(function(){
  
  $.post("<%=path%>/index/interface/info/redirect",
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
  
  });  
  
  }else{
  $("#dialog2").show();
  }
    
  });
	  });
	
});
 

</script>

<div class="bd">
    
      
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
       <%for(int i=0;i<infolist.size();i++){ %>
       <div class="weui_panel weui_panel_access">
       
       
        <div class="weui_panel_bd">
            <a href="javascript:void(0);" class="weui_media_box weui_media_appmsg">
              
                 <div class="weui_cell_hd"><img src="<%=infolist.get(i).getClientImg()%>" alt="" style="width:20px;margin-right:5px;display:block"></div>
           		 <div class="weui_cell_hd"><%=infolist.get(i).getClientName() %></div>
                
               	 <div class="weui_media_bd">
                   
                    <p class="weui_media_desc">预计红包金额：<%=infolist.get(i).getIntroduct_acount() %></p>
                    <p class="weui_media_desc">预计红包数量：<%=infolist.get(i).getIntroduct_num() %></p>
                    <p class="weui_media_desc">预计时间：<%=TextUtil.getOutputDayTimeStamp(infolist.get(i).getStime()) %> </p>
             		<p class="weui_media_desc">已有<%=infolist.get(i).getVisitor() %>人看过，目前还有<%=infolist.get(i).getAllowVisit()-infolist.get(i).getVisitor() %>人能看</p>
             		  
                </div>
            </a>
            <button class="weui_btn weui_btn_plain_primary"  id="<%=infolist.get(i).getInfoId()%>"> 查看口令 </button> 
        
        <script>
        $(document).ready(function(){
	  $("#<%=infolist.get(i).getInfoId()%>").click(function(){
	  infoid=<%=infolist.get(i).getInfoId()%>;
	  $("#dialog1").show();
	  });
	 
	});
        </script>
        
        </div>
         
        
        
        
    </div>
   
    <%} %>
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