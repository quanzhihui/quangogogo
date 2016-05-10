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
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="/share/style/weui.css"/>
    <link rel="stylesheet" href="/share/example/example.css"/> 
	<title>发口令页面</title>

</head>

<body>
<script type="text/javascript">

$().ready(function(){

if($("#koulingform").validate({
rules:{
hbkl:{required:true,minlength:2},
},
messages:{
hbkl:{required:"必须填写",minlength:"最少2个字符"}
}

})){
  //post
}
  
});  
  

  
  </script>
  

<form id="koulingform" method="post" action="/share/post/client_fakouling">

<div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">红包口令</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text" name="hbkl"  placeholder="请输入红包口令"/>  
                <div id="error_kouling">  </div> 
               
                
            </div>
        </div>
       
         <div class="weui_cell ">
            <div class="weui_cell_hd"><label for="" class="weui_label">兑现日期</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="number"  pattern="[0-9]*" value="weui input error" placeholder="请输入日期，如2016年1月1日，请输入20160101"/>
            </div>
            <div class="weui_cell_ft">
                <i class="weui_icon_warn"></i>
            </div>
        </div>
      
        <div class="weui_cell ">
            <div class="weui_cell_hd"><label for="" class="weui_label">兑现时间</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required " type="number"  pattern="[0-9]*" value="weui input error" placeholder="请输入时间，24小时制，如上午5点10分请输入0510,下午5点00分清输入1700"/>
            </div>
            <div class="weui_cell_ft">
                <i class="weui_icon_warn"></i>
            </div>
        </div>

 <div class="weui_cell ">
            <div class="weui_cell_hd"><label for="" class="weui_label">兑现时间</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required " name="introduct_acount" type="number"  pattern="[0-9]*" value="weui input error" placeholder="请输入红包金额"/>
            </div>
            <div class="weui_cell_ft">
                <i class="weui_icon_warn"></i>
            </div>
        </div>

    
    <div class="weui_btn_area">
    	 <input  type="submit"  value="确定"  /> 
         <a class="weui_btn weui_btn_primary"  id="showTooltips" onclick="aa()" >确定</a>
         <a class="weui_btn weui_btn_primary" id="formcancel" >取消</a>
    </div>

</div>
</form>

	<script src="/share/example/zepto.min.js"></script>
    <script src="/share/example/router.min.js"></script>
    <script src="/share/example/example.js"></script>

</body>
</html>