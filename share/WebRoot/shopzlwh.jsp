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
Object shopusername=request.getSession().getAttribute("");
ShopBean shopbean=null;
if(shopusername!=null){
 shopbean=ShopServer.getShopByName(shopusername.toString());
}


%>
<html lang="zh-cmn-Hans">
<head>
	<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>

    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="<%=path%>/style/weui.css"/>
    <link rel="stylesheet" href="<%=path%>/example/example.css"/> 
	<title>商家修改页面</title>

</head>

<body>
<script type="text/javascript">
$(document).ready(function(){

 

   $("#dialog_xgsb").hide();
   $("#dialog_xgcg").hide();
   $("#yqm_check").hide();
   $("#yhm_check").hide();
   $("#passwd_check").hide();
   $("#passwd2_check").hide();
   $("#gzhmc_check").hide();
   $("#imgurl_check").hide();
   $("#sjjj_check").hide();
   $("#imgurl").hide();
   $("#dialog_xgmm").hide();  
 
 

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
  
 
 
 $("#dialog_xgcg_confirm").click(function(){
 
  $("#dialog_xgcg").hide();
 
 });
 
  $("#dialog_yqm_cancel").click(function(){
 window.location.href="<%=path%>/index/url/shop/shopdl";
 });
 
  $("#dialog_xgsb_confirm").click(function(){
   $("#dialog_xgsb").hide();
 });
 
   $("#formcancel").click(function(){
      window.parent.location.href="<%=path%>/index/url/main"; 
 });
 
 
    $("#xgmm").click(function(){
   
    $("#dialog_xgmm").show();
    
 });
 
    $("#dialog_xgmm_cancel").click(function(){
   
    $("#dialog_xgmm").hide();
    
 });
 
 


 
 
});  
 
 
  function checkform(){
 var canCommit=1;
 
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
$("#dialog_xgcg").show();
$("#xgcgcontent").html("修改成功");


}else  {
$("#dialog_xgsb").show();
$("#xgsbcontent").html("修改失败,"+data);
}}};

$("#zcform").ajaxSubmit(ajax_option);
}


 function checksinglemm(id){


switch(id){
 
case("ysmmshuru"):
var vals= $("#ysmmshuru").val();
if(vals==""||vals=="null"){
  $("#ysmmshuru_check").show();
  $("#ysmmshuru_check").val("原密码不能为空");
 }else if(vals.length<6){
  $("#ysmmshuru_check").show();
  $("#ysmmshuru_check").val("密码不能小于6位");
  canCommit=0;
 }else{
  $("#ysmmshuru_check").hide();
 }
break;

case("xmmshuru"):
var vals= $("#xmmshuru").val();
if(vals==""||vals=="null"){
  $("#xmmshuru_check").show();
  $("#xmmshuru_check").val("新密码不能为空");
 }else{
  $("#xmmshuru_check").hide();
 }

break;

case("cfmmshuru"):
var vals2= $("#xmmshuru").val();
var vals= $("#cfmmshuru").val();
if(vals==""||vals!=vals2){
  $("#cfmmshuru_check").show();
  $("#cfmmshuru_check").val("两次密码不同，请重新输入");
 }else{
  $("#cfmmshuru_check").hide();
 }

break;

}

 }
 
  function checkform_mm(){
 var canCommit=1;
 
 var vals= $("#ysmmshuru").val();
if(vals==""||vals=="null"){
  $("#ysmmshuru_check").show();
  $("#ysmmshuru_check").val("原密码不能为空");
  canCommit=0;
 }else{
  $("#ysmmshuru_check").hide();
 }
 
 var vals= $("#xmmshuru").val();
if(vals==""||vals=="null"){
  $("#xmmshuru_check").show();
  $("#xmmshuru_check").val("新密码不能为空");
  canCommit=0;
 }else if(vals.length<6){
  $("#xmmshuru_check").show();
  $("#xmmshuru_check").val("密码不能小于6位");
  canCommit=0;
 }else{
  $("#xmmshuru_check").hide();
 }
 
 var vals2= $("#cfmmshuru").val();
if(vals2==""||vals!=vals2){
  $("#cfmmshuru_check").show();
  $("#cfmmshuru_check").val("两次密码不同，请重新输入");
  canCommit=0;
 }else{
  $("#cfmmshuru_check").hide();
 }
 
if( canCommit==1){
submit_mm();
 }
  
 }

 
  function submit_mm(){
 
var ajax_option={
url:"<%=path%>/index/post/shop_xgmm",
type:"post",
success:function(data){
if(data=="true"){
$("#dialog_xgmm").hide();
$("#dialog_xgcg").show(); 


}else  {
$("#dialog_xgmm").hide();
$("#dialog_xgcg").show();
}}};

$("#xgmmform").ajaxSubmit(ajax_option);
}




  </script>
  

<form id="zcform" name="zcform" method="post"  enctype="multipart/form-data">

<div class="weui_cells weui_cells_form">
   

 	<div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">公众号名称</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="text"  placeholder="点击录入公众号或微店名称" id="gzhmc"  value="<%=shopbean.getShopName() %>" onblur="checksingle('gzhmc')"/>
                 <input class="weui_input" type="text" id="gzhmc_check" readonly="readonly"/>  
            </div>
        </div>  
        
          <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">公众号简介</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="text"  placeholder="请录入简介" id="sjjj" value=<%=shopbean.getShopIntroduct() %> onblur="checksingle('sjjj')"/>
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
               
    <li class="weui_uploader_file"  id="file_upload_ui"  style="background-image:url(<%=shopbean.getShopImg()%>)"></li>
                      
                           
                            
                            
                        </ul>
                        <div class="weui_uploader_input_wrp">
                        
  							<input class="weui_input" type="text" id="imgurl" name="imgurl" readonly="readonly" value="<%=shopbean.getShopImg() %>>"  />  
                            <input class="weui_uploader_input" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multipart/form-data id="shangchuan" name="shangchuan" />
                        
                        </div>
                        	<input class="weui_input" type="text" id="imgurl_check" readonly="readonly"  /> 
                    </div>
                </div>
            </div>
        </div>

      
     
      
    
    <div class="weui_btn_area">
         <a class="weui_btn weui_btn_primary"  id="showTooltips" onclick="checkform()">修改</a>
         <a class="weui_btn weui_btn_primary" id="formcancel"  >返回红包界面</a>
    </div>

</div>
</form>

  <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_default" id="xgmm">修改密码</a>


<div class="weui_dialog_confirm"  id="dialog_xgcg" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">修改成功</strong></div>
        <div class="weui_dialog_bd" id="xgcgcontent"></div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_xgcg_confirm"   ">确定</a>            
        </div>
    </div>
</div>


<div class="weui_dialog_confirm"  id="dialog_xgsb" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">修改失败</strong></div>
        <div class="weui_dialog_bd" id="xgsbcontent"></div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_xgsb_confirm" >确定</a>            
        </div>
    </div>
</div>


 <div class="weui_dialog_confirm"  id="dialog_xgmm" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">修改密码</strong></div>
        <div class="weui_dialog_bd" >
        <form id="xgmmform"  method="post">
        <input class="weui_input" type="password" id="ysmmshuru"  placeholder="请录入原密码" onblur="checksinglemm('ysmmshuru')"  /> 
        <input class="weui_input" type="text" id="ysmmshuru_check" readonly="readonly"  /> 
        	
        <input class="weui_input" type="password" id="xmmshuru"  placeholder="请录入新密码"   onblur="checksinglemm('xmmshuru')" /> 
        <input class="weui_input" type="text" id="xmmshuru_check" readonly="readonly"  />  	
        
        <input class="weui_input" type="password" id="cfmmshuru"  placeholder="请重复录入新密码" onblur="checksinglemm('cfmmshuru')" /> 
        <input class="weui_input" type="text" id="cfmmshuru_check" readonly="readonly"  />  	
        	
        </form>
        </div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_xgmm_confirm" onclick="checkform_mm()" >确定</a>
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_xgmm_cancel">取消</a>
            
        </div>
    </div>
</div>                     	 
         
                               	 
               	 
                      	 


	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
	<script src="<%=path%>/example/jquery.form.min.js"></script>
</body>
</html>