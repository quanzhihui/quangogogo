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
	<title>商家注册页面</title>

</head>

<body>
<script type="text/javascript">
$(document).ready(function(){

$("#dl_klqr").hide();
$("#dialog_wjma").hide();

$("#formcancel").click(function(){
window.location.href="<%=path%>/index/url/main";
});


$("#dl_klqr_confirm").click(function(){

$("#dl_klqr").hide();
});


$("#wjmm").click(function(){
$("#dialog_wjma").show();
});

$("#dialog_wjma_confirm").click(function(){
$("#dialog_wjma").hide();
});


$("#sjzc").click(function(){
window.location.href="<%=path%>/index/url/shop/shopzc";
});


});

 function checkform(){
 var canCommit=1;
 var shopusername=$("#shopusername").val();
 if(shopusername==""){
  $("#shopusername_check").show();
  $("#shopusername_check").val("用户名不能为空");
   canCommit=0;
 }else{
  $("#shopusername_check").hide();
 }
 
 var shoppassword=$("#shoppassword").val();
 if(shoppassword==""||shoppassword.length<6){
  $("#shoppassword_check").show();
  $("#shoppassword_check").val("密码不能少于6位");
   canCommit=0;
 }else{
  $("#shoppassword_check").hide();
 }
 if( canCommit==1){
  commit();
 }
 
 }
 
 function checksingle(id){

 if(id=="shopusername_check"){
 
  var vals=$("#shopusername").val();
 if(vals==""||vals=="null"){
  $("#shopusername_check").show();
  $("#shopusername_check").val("用户名不能为空");
 }else{
  $("#shopusername_check").hide();
 }
 }else if(id=="shoppassword_check"){

  var vals=$("#shoppassword").val();
  if(vals==""||vals.length<6){
  $("#shoppassword_check").show();
  $("#shoppassword_check").val("密码不能少于6位");
 }else{
  $("#shoppassword_check").hide();
 }
 }
 }



function commit(){

var ajax_option={
url:"<%=path%>/index/interface/shop/userlogin",

type:"post",
success:function(data){
//1成功，-1失败
if(data!="1"){
$("#klqrtitle").html("用户名或密码不正确，请重新输入！");
$("#dl_klqr").show();
}else{
window.location.href="<%=path%>/index/url/shop/shopmain";
}
}
};

$("#sjdlform").ajaxSubmit(ajax_option);

}
  
  </script>
  
<form id="sjdlform"  method="post" >

<div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">商家用户名</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text" name="shopusername" id="shopusername"  placeholder="请输入商户名"  onblur="checksingle('shopusername_check')"/>  
                 <input class="weui_input" type="text" id="shopusername_check" readonly="readonly"/>  
                <div id="error_kouling">  </div> 
            </div>
        </div>
        
        <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">商家密码</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="password"  placeholder="请输入商户密码" id="shoppassword" name="shoppassword" onblur="checksingle('shoppassword_check')"/>
                 <input class="weui_input" type="text" id="shoppassword_check" readonly="readonly"/>  
            </div>
        </div>

    <div class="weui_btn_area">
         <a class="weui_btn weui_btn_primary"  id="showTooltips" onclick="checkform()">确定</a>
         <a class="weui_btn weui_btn_primary" id="formcancel"  >返回主界面</a>
    </div>
      
</div>
</form>


<div class="weui_dialog_confirm"  id="dl_klqr" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd" id="klqrtitle" ><strong class="weui_dialog_title"></strong></div>
        <div class="weui_dialog_bd" id="klqrcontent"></div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dl_klqr_confirm" >确定</a>
          
            
        </div>
    </div>
</div>
<div class="hd">
 <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_default" id="sjzc">商家注册</a>
  <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_default" id="wjmm">忘记密码</a>
</div>

<div class="weui_dialog_confirm"  id="dialog_wjma" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd" id="klqrtitle" ><strong class="weui_dialog_title">请在工作时间联系公众号的微店管理员，微店名称：优惠速递，联系的时候请说明是忘记密码并报公众号名字，他会尽快往公众号发送临时密码，登陆后重置密码即可。</strong></div>
        <div class="weui_dialog_bd" id="klqrcontent">微店名称：优惠速递  </div>
        <div class="weui_dialog_bd" id="klqrcontent">微店地址：http://weidian.com/s/254545908</div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_wjma_confirm" >确定</a>
          
            
        </div>
    </div>
</div>


	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
	<script src="<%=path%>/example/jquery.form.min.js"></script>
</body>
</html>