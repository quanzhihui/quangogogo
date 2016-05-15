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
 List<ViewLog> infoList=  LogServer.readViewLog(clientwx);
%>
<html lang="zh-cmn-Hans">
<head>
	<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="<%=path%>/style/weui.css"/>
    <link rel="stylesheet" href="<%=path%>/example/example.css"/> 
    <link rel="stylesheet" href="<%=path%>/example/table.css"/> 
    
    
	<title>商家历史口令</title>

</head>

<body>



<script type="text/javascript">

var clientid=<%=clientwx%>

        $(document).ready(function(){
        
         $("#gobacktomain").click(function(){
               window.parent.location.href="<%=path%>/index/url/main"; 
          });
        });


 </script>



<table class="bordered">

    <thead>
    <tr>
        <th>红包时间</th>        
        <th>红包口令</th>
        <th>活动链接</th>
    </tr>
    </thead>
    
    <% for(int i=0;i<infoList.size();i++){
    
    %>
    <tr>
        <td><%=TextUtil.getOutputDayTimeStamp(infoList.get(i).getStime()) %></td>        
        <td><%=infoList.get(i).getKouling() %></td>
        <td>
        <a href="javascript:;" id="<%=infoList.get(i).getInfoId()%>" > <%=infoList.get(i).getTgurl() %></a>
           
        <script>
        $(document).ready(function(){
	  $("#<%=infoList.get(i).getInfoId()%>").click(function(){
	  infoid="<%=infoList.get(i).getInfoId()%>";
	  tgurl="<%=infoList.get(i).getTgurl()%>";
	   $.post("<%=path%>/index/interface/log/tuiguanclick",
  {
    clientwx:clientid,
    tgurl:tgurl,
    infomationid:infoid
  },
  function(data,status){
  window.parent.location.href=tgurl;
  });
	  
	  });
	});
        </script>
        
        
        </td>
    </tr>        
   <% }%>

</table>
<div class="hd">
<div class="bd spacing">
 <a href="javascript:;" class="weui_btn weui_btn_primary" id="gobacktomain">返回红包界面</a>
</div>
</div>
  
  
 
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
</body>
</html>