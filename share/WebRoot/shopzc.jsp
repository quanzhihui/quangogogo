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


$("#dialog_yqm_confirm").click(function(){


var ajax_option={
url:"<%=path%>/index/interface/shop/yqm_registe",
type:"post",
success:function(data){
if(data=="true"){
$("#dialog_yqm").hide();

$("#yqm").val($("#yqmshuru").val());

}else {
$("#yqmshuru_check").val("邀请码无效，请输入正确的邀请码！");
}}};


$("#yqmform").ajaxSubmit(ajax_option);


});



 $("#dialog_zcsb").hide();
$("#dialog_zccg").hide();
 $("#yqm_check").hide();
   $("#yhm_check").hide();
   $("#passwd_check").hide();
   $("#passwd2_check").hide();
   $("#gzhmc_check").hide();
   $("#imgurl_check").hide();
   $("#sjjj_check").hide();
   $("#imgurl").hide();

$("#shangchuan").change(function(){

 var formData = new FormData($("#zcform")[0]);  
     $.ajax({  
          url: '<%=path%>/index/interface/shop/image' ,  
          type: 'POST',  
          data: formData,  
          async: true,  
          cache: false,  
          contentType: false,  
          processData: false,  
          success: function (returndata) {  
                   $("#file_upload_ui").attr("style",  "background-image:url("+returndata+")");
                   $("#imgurl").val(returndata);
          },  
          error: function (returndata) {  
              alert(returndata);  
          }  
     });  
});
  
 
 
 $("#dialog_zccg_confirm").click(function(){
 window.location.href="<%=path%>/index/url/shop/shopdl";
 });
 
  $("#dialog_yqm_cancel").click(function(){
 window.location.href="<%=path%>/index/url/shop/shopdl";
 });
 
  $("#dialog_zcsb_confirm").click(function(){
   $("#dialog_zcsb").hide();
 });
 
 
});  
 
 
  function checkform(){
 var canCommit=1;

var vals= $("#yqm").val();
if(vals==""||vals=="null"){
  $("#yqm_check").show();
  $("#yqm_check").val("邀请码不能为空");
  canCommit=0;
 }else{
  $("#yqm_check").hide();
 }

var vals= $("#yhm").val();
if(vals==""||vals=="null"){
  $("#yhm_check").show();
  $("#yhm_check").val("用户名不能为空");
  canCommit=0;
 }else{
  $("#yhm_check").hide();
 }

var vals= $("#passwd").val();
if(vals==""||vals=="null"){
  $("#passwd_check").show();
  $("#passwd_check").val("密码不能为空");
  canCommit=0;
 }else if(vals.length<6){
  $("#passwd_check").show();
  $("#passwd_check").val("密码不能小于6位");
  canCommit=0;
 }else{
  $("#passwd_check").hide();
 }
 
 var vals2= $("#passwd2").val();
if(vals2==""||vals2=="null"){
  $("#passwd2_check").show();
  $("#passwd2_check").val("请重复录入密码");
  canCommit=0;
 }else if(vals2!=vals){
 $("#passwd2_check").show();
  $("#passwd2_check").val("两次密码输入不同，请重新输入"); 
  canCommit=0;
 }else{
  $("#passwd2_check").hide();
 }
 
 var vals= $("#gzhmc").val();
if(vals==""||vals=="null"){
  $("#gzhmc_check").show();
  $("#gzhmc_check").val("名称不能为空");
  canCommit=0;
 }else{
  $("#gzhmc_check").hide();
 }
 
 var vals= $("#imgurl").val();
if(vals==""||vals=="null"){
  $("#imgurl_check").show();
  $("#imgurl_check").val("公众号头像不能为空");
  canCommit=0;
 }else{
  $("#imgurl_check").hide();
 }
 
 var vals= $("#sjjj").val();
if(vals==""||vals=="null"){
  $("#sjjj_check").show();
  $("#sjjj_check").val("给大家介绍下公众号吧");
  canCommit=0;
 }else{
  $("#sjjj_check").hide();
 }
 
 
if( canCommit==1){
submit();
 }
 
 
 
 }
 
 function checksingle(id){


switch(id){
case("yqm"):
var vals= $("#yqm").val();
if(vals==""||vals=="null"){
  $("#yqm_check").show();
  $("#yqm_check").val("邀请码不能为空");
 }else{
  $("#yqm_check").hide();
 }
break;

case("yhm"):
var vals= $("#yhm").val();
if(vals==""||vals=="null"){
  $("#yhm_check").show();
  $("#yhm_check").val("用户名不能为空");
 }else{
  $("#yhm_check").hide();
 }
break;

case("passwd"):
var vals= $("#passwd").val();
if(vals==""||vals=="null"){
  $("#passwd_check").show();
  $("#passwd_check").val("密码不能为空");
 }else if(vals.length<6){
  $("#passwd_check").show();
  $("#passwd_check").val("密码不能小于6位");
 }else{
  $("#passwd_check").hide();
 }
 //再检查下密码
 checksingle('passwd2');
 
break;

case("passwd2"):
var vals= $("#passwd").val();
var vals2= $("#passwd2").val();
if(vals2==""||vals2=="null"){
  $("#passwd2_check").show();
  $("#passwd2_check").val("请重复录入密码");
 }else if(vals2!=vals){
  $("#passwd2_check").show();
  $("#passwd2_check").val("两次密码输入不同，请重新输入"); 
 } else{
  $("#passwd2_check").hide();
 }
 
 
 
 
break;

case("gzhmc"):
var vals= $("#gzhmc").val();
if(vals==""||vals=="null"){
  $("#gzhmc_check").show();
  $("#gzhmc_check").val("名称不能为空");
 }else{
  $("#gzhmc_check").hide();
 }
break;

case("imgurl"):
var vals= $("#imgurl").val();
if(vals==""||vals=="null"){
  $("#imgurl_check").show();
  $("#imgurl_check").val("公众号头像不能为空");
 }else{
  $("#imgurl_check").hide();
 }

break;

case("sjjj"):
var vals= $("#sjjj").val();
if(vals==""||vals=="null"){
  $("#sjjj_check").show();
  $("#sjjj_check").val("给大家介绍下公众号吧");
 }else{
  $("#sjjj_check").hide();
 }

break;

}

 }
 
 
 
  function submit(){
 
var ajax_option={
url:"<%=path%>/index/post/shop_zhuce",
type:"post",
success:function(data){
if(data=="true"){
$("#dialog_zccg").show();
$("#zccgcontent").html("注册成功");


}else  {
$("#dialog_zcsb").show();
$("#zcsbcontent").html("注册失败,"+data);
}}};

$("#zcform").ajaxSubmit(ajax_option);
}

$("#dialogklqrdeny").click(function(){
$("#dialog_klqr").hide();
});
 
 
 
 

  
  </script>
  

<form id="zcform" name="zcform" method="post"  enctype="multipart/form-data">

<div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">邀请码</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text" name="yqm" id="yqm"   readonly="readonly"/>  
                 <input class="weui_input" type="text" id="yqm_check" readonly="readonly"/>  
            </div>
        </div>
       
       
       
        <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">用户名</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="text"  placeholder="点击录入用户名" id="yhm" onblur="checksingle('yhm')"/>
                 <input class="weui_input" type="text" id="yhm_check" readonly="readonly"/>  
            </div>
        </div>

      
      <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">密码</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="password"  placeholder="点击录入密码" id="passwd" onblur="checksingle('passwd')"/>
                 <input class="weui_input" type="text" id="passwd_check" readonly="readonly"/>  
            </div>
        </div>
      
          <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">确认密码</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="password"  placeholder="请重复录入密码" id="passwd2" onblur="checksingle('passwd2')"/>
                 <input class="weui_input" type="text" id="passwd2_check" readonly="readonly"/>  
            </div>
        </div>  


 	<div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">公众号名称</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="text"  placeholder="点击录入公众号或微店名称" id="gzhmc" onblur="checksingle('gzhmc')"/>
                 <input class="weui_input" type="text" id="gzhmc_check" readonly="readonly"/>  
            </div>
        </div>  
        
          <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">公众号简介</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="text"  placeholder="请录入简介" id="sjjj" onblur="checksingle('sjjj')"/>
                 <input class="weui_input" type="text" id="sjjj_check" readonly="readonly" />  
            </div>
        </div>  
        
        
      

        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <div class="weui_uploader">
                    <div class="weui_uploader_hd weui_cell">
                        <div class="weui_cell_bd weui_cell_primary">公众号头像上传</div>
                        <div class="weui_cell_ft">0/2</div>
                    </div>
                    <div class="weui_uploader_bd">
                        <ul class="weui_uploader_files"  >
               
    <li class="weui_uploader_file"  id="file_upload_ui"  style="background-image:url()"></li>
                      
                           
                            
                            
                        </ul>
                        <div class="weui_uploader_input_wrp">
                        
  							<input class="weui_input" type="text" id="imgurl" name="imgurl" readonly="readonly"  />  
                            <input class="weui_uploader_input" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multipart/form-data id="shangchuan" name="shangchuan" />
                        	
                        </div>
                        <input class="weui_input" type="text" id="imgurl_check" readonly="readonly"  /> 
                    </div>
                </div>
            </div>
        </div>

      
     
      
    
    <div class="weui_btn_area">
         <a class="weui_btn weui_btn_primary"  id="showTooltips" onclick="checkform()">确定</a>
         <a class="weui_btn weui_btn_primary" id="formcancel"  >取消</a>
    </div>

</div>
</form>


<div class="weui_dialog_confirm"  id="dialog_zccg" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">注册成功</strong></div>
        <div class="weui_dialog_bd" id="zccgcontent"></div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_zccg_confirm" >确定</a>            
        </div>
    </div>
</div>


<div class="weui_dialog_confirm"  id="dialog_zcsb" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">注册失败</strong></div>
        <div class="weui_dialog_bd" id="zcsbcontent"></div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_zcsb_confirm" >确定</a>            
        </div>
    </div>
</div>


                      	 
 <div class="weui_dialog_confirm"  id="dialog_yqm" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">请输入邀请码</strong></div>
        <div class="weui_dialog_bd" >
        <form id="yqmform"  method="post">
        <input class="weui_input" type="text" id="yqmshuru"  placeholder="请录入邀请码"   /> 
        	<input class="weui_input" type="text" id="yqmshuru_check" readonly="readonly"  /> 
        </form>
        </div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_yqm_confirm" >确定</a>
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_yqm_cancel">取消</a>
            
        </div>
    </div>
</div>                     	 
                      	 
                      	 


	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
	<script src="<%=path%>/example/jquery.form.min.js"></script>
</body>
</html>