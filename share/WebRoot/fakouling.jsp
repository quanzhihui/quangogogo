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
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html lang="zh-cmn-Hans">
<head>
	<script src="<%=basePath%>/example/jquery.min.js"></script>

    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="<%=basePath%>/style/weui.css"/>
    <link rel="stylesheet" href="<%=basePath%>/example/example.css"/> 
	<title>发口令页面</title>

</head>

<body>
<script type="text/javascript">
$(document).ready(function(){
$("#dialog_result_success").hide();
$("#dialog_klqr").hide();
$("#dialog_result_fail").hide();
$("#hbkl_check").hide();
$("#ffsj_check").hide();
$("#dialog_result_fail").hide();
 




$("#dialog_result_confirm").click(function(){
$("#dialog_result_success").hide();
});

 



$("#formcancel").click(function(){
window.location.href="<%=path%>/index/url/main";
});

$("#dialog_result_confirm_fail").click(function(){
$("#dialog_result_fail").hide();
});


$("#dialog_klqr_confirm").click(function(){
$("#dialog_klqr").hide();
var ajax_option={
url:"<%=path%>/index/post/client_fakouling",
type:"post",
clearForm: true,
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
 var hbkl=$("#hbkl").val();
 if(hbkl==""){
  $("#hbkl_check").show();
  $("#hbkl_check").val("红包口令不能为空");
   canCommit=0;
 }else{
  $("#hbkl_check").hide();
 }
 
 var ffsj=$("#ffsj").val();
 if(ffsj==""||ffsj.length<14){
  $("#ffsj_check").show();
  $("#ffsj_check").val("日期格式不正确，请重新输入日期和时间");
   canCommit=0;
 }else{
  $("#ffsj_check").hide();
 }
 if( canCommit==1){
  showConfirm();
 }
 
 }
 
 function checksingle(id){

 if(id=="hbkl_check"){
 
  var vals=$("#hbkl").val();
 if(vals==""||vals=="null"){
  $("#hbkl_check").show();
  $("#hbkl_check").val("红包口令不能为空");
 }else{
  $("#hbkl_check").hide();
 }
 }else if(id=="ffsj_check"){

  var vals=$("#ffsj").val();
  if(vals==""||vals.length<14){
  $("#ffsj_check").show();
  $("#ffsj_check").val("日期格式不正确，请重新输入日期和时间");
 }else{
  $("#ffsj_check").hide();
 }
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
            <div class="weui_cell_hd"><label class="weui_label">红包口令</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text" name="hbkl" id="hbkl"  placeholder="请输入红包口令"  onblur="checksingle('hbkl_check')"/>  
                 <input class="weui_input" type="text" id="hbkl_check" readonly="readonly"/>  
                
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
            <div class="weui_cell_hd"><label for="" class="weui_label">商家名称</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" name="sjmc" type="text"   placeholder="请输入商家名称"/>
            </div>
            <div class="weui_cell_ft">
                <i class="weui_icon_warn"></i>
            </div>
        </div>
 

    <div class="weui_cell ">
            <div class="weui_cell_hd"><label for="" class="weui_label">红包总金额（可不填）</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input  " name="hbzje" type="number"  pattern="[0-9]*" value="weui input error" placeholder="请输入红包总金额"/>
            </div>
            <div class="weui_cell_ft">
                <i class="weui_icon_warn"></i>
            </div>
        </div> 
    
       <div class="weui_cell ">
            <div class="weui_cell_hd"><label for="" class="weui_label">红包总数量（可不填）</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input  " name="hbzsl" type="number"  pattern="[0-9]*" value="weui input error" placeholder="请输入红包总数量"/>
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
        <div class="weui_dialog_bd" id="success_content">发送成功，管理员正在审核红包口令，审核通过后将给您赠送200优惠豆，感谢您的分享！</div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_result_confirm">返回发口令界面</a>
            
            
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





	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
	<script src="<%=path%>/example/jquery.form.min.js"></script>
</body>
</html>