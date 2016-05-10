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
    <link rel="stylesheet" href="/share/style/weui.css"/>
    <link rel="stylesheet" href="/share/example/example.css"/> 
	<title>发口令页面</title>
    

</head>

<body>
<div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">红包口令</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="text"  placeholder="请输入红包口令"/>
            </div>
        </div>
       
         <div class="weui_cell ">
            <div class="weui_cell_hd"><label for="" class="weui_label">兑现日期</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="number"  pattern="[0-9]*" value="weui input error" placeholder="请输入日期，如2016年1月1日，请输入20160101"/>
            </div>
            <div class="weui_cell_ft">
                <i class="weui_icon_warn"></i>
            </div>
        </div>
      
       

    <div class="weui_cells_tips">底部说明文字底部说明文字</div>
    <div class="weui_btn_area">
        <a class="weui_btn weui_btn_primary" href="javascript:" id="showTooltips">确定</a>
    </div>

</div>


	<script src="/share/example/zepto.min.js"></script>
    <script src="/share/example/router.min.js"></script>
    <script src="/share/example/example.js"></script>


</body>
</html>