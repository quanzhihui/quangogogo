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
	<title>红包分享页面</title>
    

</head>

<body >

<script>
var clientwx=<%=100%>;

$(document).ready(function(){

  document.getElementById("mainframe").src="<%=path%>/index/url/shop/tab/shopmain/lskl";

$("#lskl").click(function(){
 
 $("#lskl").addClass("weui_bar_item_on");
 $("#fbkl").removeClass("weui_bar_item_on");
 $("#zlwh").removeClass("weui_bar_item_on");

  document.getElementById("mainframe").src="<%=path%>/index/url/shop/tab/shopmain/lskl";
 });
 
 $("#fbkl").click(function(){
 $("#fbkl").addClass("weui_bar_item_on");
 $("#lskl").removeClass("weui_bar_item_on");
 $("#zlwh").removeClass("weui_bar_item_on");
 document.getElementById("mainframe").src="<%=path%>/index/url/shop/tab/shopmain/fbkl";
 });
 
 $("#zlwh").click(function(){
 $("#zlwh").addClass("weui_bar_item_on");
 $("#lskl").removeClass("weui_bar_item_on");
 $("#fbkl").removeClass("weui_bar_item_on");
  document.getElementById("mainframe").src="<%=path%>/index/url/shop/tab/shopmain/zlwh";
 });
 

 });

</script>

<div class="bd" style="height: 8%;">
    <div class="weui_tab">
        <div class="weui_navbar">
            <div class="weui_navbar_item  weui_bar_item_on"  id='lskl'>
                历史口令
            </div>
            <div class="weui_navbar_item"   id='fbkl'>
                发布口令
            </div>
            <div class="weui_navbar_item"   id='zlwh'>
                资料维护
            </div>
        </div>
      
    </div>
</div>
     


<iframe class="weui_panel_bd" height=84% width=100%  src="<%=path%>/index/info/tab/shopmain/lskl.jsp"   scrolling="auto" frameborder="0" id="mainframe"> </iframe>





 



	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
	


   
	
</body>
</html>