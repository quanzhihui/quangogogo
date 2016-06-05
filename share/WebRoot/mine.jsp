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
	<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="<%=path%>/style/weui.css"/>
    <link rel="stylesheet" href="<%=path%>/example/example.css"/> 
	<title>我的</title>
    

</head>

<body >

<script>
var clientwx=<%=100%>;

$(document).ready(function(){
 
 $("#wdjf").click(function(){
 $("#wdjf").addClass("weui_bar_item_on");
 $("#kgdkl").removeClass("weui_bar_item_on");
 $("#fdkl").removeClass("weui_bar_item_on");
  document.getElementById("mineframe").src="<%=path%>/index/info/url/tab/mineframe/myjifen";
 });
 
 $("#kgdkl").click(function(){
 $("#kgdkl").addClass("weui_bar_item_on");
 $("#wdjf").removeClass("weui_bar_item_on");
  $("#fdkl").removeClass("weui_bar_item_on");
  document.getElementById("mineframe").src="<%=path%>/index/info/url/tab/mineframe/mykgdkl";
 });
 
 $("#fdkl").click(function(){
 $("#fdkl").addClass("weui_bar_item_on");
 $("#kgdkl").removeClass("weui_bar_item_on");
 $("#wdjf").removeClass("weui_bar_item_on");
 
  document.getElementById("mineframe").src="<%=path%>/index/info/url/tab/mineframe/fdkl";
 });

 
 
 
 });

</script>

<div class="bd" style="height: 8%;">
    <div class="weui_tab">
        <div class="weui_navbar">
            <div class="weui_navbar_item  weui_bar_item_on"  id='wdjf'>
                我的积分
            </div>
            <div class="weui_navbar_item"   id='kgdkl'>
                看过的口令
            </div>
      <div class="weui_navbar_item"   id='fdkl'>
               我发的口令
            </div>
        </div>
      
    </div>
</div>
     


<iframe class="weui_panel_bd" height=84% width=90%  src="<%=path%>/index/info/url/tab/mineframe/myjifen"   scrolling="auto" frameborder="0" id="mineframe"> </iframe>


 







	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
	


   
	
</body>
</html>