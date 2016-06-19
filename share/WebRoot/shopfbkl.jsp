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
	<title>商家发口令页面</title>

</head>

<body>
<script type="text/javascript">
$(document).ready(function(){
$("#dialog_result_success").hide();
$("#dialog_klqr").hide();
$("#dialog_result_fail").hide();
$("#hbkl_check").hide();
$("#ffsj_check").hide();
$("#hbzje_check").hide();
$("#hbzsl_check").hide();
$("#tglj_check").hide();
$("#dialog_result_fail").hide();
$("#tgdshuru_check").hide();





$("#dialog_tgd_confirm").click(function(){


var ajax_option={
url:"<%=path%>/index/interface/shop/tgd_use",
type:"post",
success:function(data){
if(data=="true"){
$("#dialog_tgd").hide();

$("#tgd").val($("#tgdshuru").val());


}else {

$("#tgdshuru_check").show();
$("#tgdshuru_check").val("推广豆无效，请输入正确的推广豆！");
}}};


$("#yqmform").ajaxSubmit(ajax_option);


});


$("#gmyqd").click(function(){

window.parent.location.href="http://weidian.com/s/254545908?wfr=c";

});







$("#dialog_result_confirm").click(function(){
window.parent.location.href="<%=path%>/index/url/shop/shopmain";
});

$("#formcancel").click(function(){
window.parent.location.href="<%=path%>/index/url/shop/shopmain";
});

$("#dialog_result_confirm_fail").click(function(){
$("#dialog_result_fail").hide();
});


$("#dialog_klqr_confirm").click(function(){
$("#dialog_klqr").hide();
var ajax_option={
url:"<%=path%>/index/post/shop_fakouling",
type:"post",
success:function(data){
if(data=="1"){
$("#dialog_result_success").show();
}else if(data=="2"){
$("#dialog_result_fail").show();
}
}
};
$("#koulingform").ajaxSubmit(ajax_option);
});

$("#dialogklqrdeny").click(function(){
$("#dialog_klqr").hide();
});



});  
 


 function checkform(){
 var canCommit=1;
 
 var vals= $("#hbkl").val();
if(vals==""||vals=="null"){
  $("#hbkl_check").show();
  $("#hbkl_check").val("红包口令不能为空");
  canCommit=0;
 }else{
  $("#hbkl_check").hide();
 }
 var vals= $("#ffsj").val();
 if(vals==""||vals.length<14){
  $("#ffsj_check").show();
  $("#ffsj_check").val("日期格式不正确，请重新输入日期和时间");
  canCommit=0;
 }else{
  $("#ffsj_check").hide();
 }
 var vals= $("#hbzje").val();
if(vals==""||vals=="null"){
  $("#hbzje_check").show();
  $("#hbzje_check").val("红包金额必填");
  canCommit=0;
 }else{
  $("#hbzje_check").hide();
 }
 var vals= $("#hbzsl").val();
if(vals==""||vals=="null"){
  $("#hbzsl_check").show();
  $("#hbzsl_check").val("红包数量必填");
  canCommit=0;
 }else{
  $("#hbzsl_check").hide();
 }
 var vals= $("#tglj").val();
if(vals==""||vals=="null"){
  $("#tglj_check").show();
  $("#tglj_check").val("推广链接必填");
  canCommit=0;
 }else{
  $("#tglj_check").hide();
 }
 
 if( canCommit==1){
  showConfirm();
 }
 
 }
 
 function checksingle(id){
 
switch(id){
case("hbkl_check"):
var vals= $("#hbkl").val();
if(vals==""||vals=="null"){
  $("#hbkl_check").show();
  $("#hbkl_check").val("红包口令不能为空");
 }else{
  $("#hbkl_check").hide();
 }
break;
 
case("ffsj_check"):
var vals= $("#ffsj").val();
 if(vals==""||vals.length<14){
  $("#ffsj_check").show();
  $("#ffsj_check").val("日期格式不正确，请重新输入日期和时间");
 }else{
  $("#ffsj_check").hide();
 }
break;
 
case("hbzje_check"):
var vals= $("#hbzje").val();
if(vals==""||vals=="null"){
  $("#hbzje_check").show();
  $("#hbzje_check").val("红包金额必填");
 }else{
  $("#hbzje_check").hide();
 }
break; 
 
case("hbzsl_check"):
var vals= $("#hbzsl").val();
if(vals==""||vals=="null"){
  $("#hbzsl_check").show();
  $("#hbzsl_check").val("红包数量必填");
 }else{
  $("#hbzsl_check").hide();
 }
break;  
 
case("tglj_check"):
var vals= $("#tglj").val();
if(vals==""||vals=="null"){
  $("#tglj_check").show();
  $("#tglj_check").val("推广链接必填");
 }else{
  $("#tglj_check").hide();
 }
break;   
 
 }
 
 }
 
 
function showConfirm(){

var kl=$("#hbkl").val();
var timesplit=$("#ffsj").val().split("T");
var daysplit=timesplit[0].split("-");
var year=daysplit[0];
var month=daysplit[1];;
var day=daysplit[2];;
var time=timesplit[1];

$("#klqrcontent").html("红包口令为："+kl+",时间:"+year+"年"+month+"月"+day+"日 "+time);
$("#dialog_klqr").show();
} 
 
  
  </script>
  

<form id="koulingform" name="koulingform" method="post" >

<div class="weui_cells weui_cells_form">
		<div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">推广豆</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text" name="tgd" id="tgd"  placeholder="请输入推广豆" readonly="readonly" />  
               
                <div id="error_kouling">  </div> 
            </div>
        </div>


        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">红包口令</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text" name="hbkl" id="hbkl"  placeholder="请输入红包口令"  onblur="checksingle('hbkl_check')"/>  
                 <input class="weui_input" type="text" id="hbkl_check" readonly="readonly"/>  
                <div id="error_kouling">  </div> 
            </div>
        </div>
       
          		<div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">发放时间</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="datetime-local"  placeholder="点击录入发放时间" id="ffsj" name="ffsj" onblur="checksingle('ffsj_check')"/>
                 <input class="weui_input" type="text" id="ffsj_check" readonly="readonly"/>  
            </div>
        </div>


    <div class="weui_cell ">
            <div class="weui_cell_hd"><label for="" class="weui_label">红包总金额</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input  " id="hbzje" name="hbzje" type="number"  pattern="[0-9]*" value="weui input error" placeholder="请输入红包总金额"  onblur="checksingle('hbzje_check')"/>
             <input class="weui_input" type="text" id="hbzje_check" readonly="readonly"/>  
            </div>
            <div class="weui_cell_ft">
                <i class="weui_icon_warn"></i>
            </div>
        </div> 
    
       <div class="weui_cell ">
            <div class="weui_cell_hd"><label for="" class="weui_label">红包总数量</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input  " id="hbzsl"  name="hbzsl" type="number"  pattern="[0-9]*" value="weui input error" placeholder="请输入红包总数量" onblur="checksingle('hbzsl_check')"/>
                  <input class="weui_input" type="text" id="hbzsl_check" readonly="readonly"/>  
            </div>
            <div class="weui_cell_ft">
                <i class="weui_icon_warn"></i>
            </div>
        </div>
    
     <div class="weui_cell ">
            <div class="weui_cell_hd"><label for="" class="weui_label">推广链接</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" id="tglj" name="tglj" type="text"    placeholder="请输入推广链接" onblur="checksingle('tglj_check')" />
                <input class="weui_input" type="text" id="tglj_check" readonly="readonly"/>  
            </div>
            <div class="weui_cell_ft">
                <i class="weui_icon_warn"></i>
            </div>
        </div>
    
    <div class="weui_btn_area">
         <a class="weui_btn weui_btn_primary"  id="showTooltips" onclick="checkform()">确定</a>
         <a class="weui_btn weui_btn_primary" id="formcancel"  >取消</a>
    </div>

</div>
</form>


<div class="weui_dialog_confirm"  id="dialog_klqr" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">口令确认</strong></div>
        <div class="weui_dialog_bd" id="klqrcontent"></div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_klqr_confirm" >确定</a>
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialogklqrdeny">取消</a>
            
        </div>
    </div>
</div>

<div class="weui_dialog_confirm"  id="dialog_result_success" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">分享成功</strong></div>
        <div class="weui_dialog_bd" id="success_content">发送成功，管理员正在审核红包口令，感谢您的分享！</div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_result_confirm">确定</a>
            
            
        </div>
    </div>
</div>


<div class="weui_dialog_confirm"  id="dialog_result_fail" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">分享失败</strong></div>
        <div class="weui_dialog_bd" >很遗憾，口令已经被分享过了，再找个新口令吧O(∩_∩)O</div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_result_confirm_fail">返回发口令界面</a>
        </div>
    </div>
</div>


 <div class="weui_dialog_confirm"  id="dialog_tgd" >
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">请输入推广豆</strong></div>
        <div class="weui_dialog_bd" >
        <form id="yqmform"  method="post">
        <input class="weui_input" type="text" id="tgdshuru" name="tgdshuru" placeholder="请输入推广豆"   /> 
          <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_primary" id="gmyqd">购买推广豆</a>  
        	<input class="weui_input" type="text" id="tgdshuru_check" readonly="readonly"  /> 
        </form>
        </div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_tgd_confirm" >确定</a>
          
            
        </div>
    </div>
</div>          


   
  

	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
	<script src="<%=path%>/example/jquery.form.min.js"></script>
</body>
</html>