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
	<title>发口令页面</title>

</head>

<body>
<script type="text/javascript">
$(document).ready(function(){
$("#dialog_result_success").hide();
 $("#tgd_create").click(function(){
 
 
 
 var ajax_option={
url:"<%=path%>/index/interface/code/tgdcreate",
type:"post",
success:function(data){
//1成功，-1失败
if(data!="null"){
$("#tgd").val("data");
}
}
};

$("#tgdform").ajaxSubmit(ajax_option);
 
});
 




});  
 



 function checkform(){

 }
 
  
 
  </script>
  <form id="tgdform" name="koulingform" method="post" >
  <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">生成推广豆</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text"  value="" id="tgd"  />  
                <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_primary" id="tgd_create" >按钮</a>
                <div id="error_kouling">  </div> 
            </div>
   </div>  
 </form>
 
 

<form id="koulingform" name="koulingform" method="post" >

<div class="weui_cells weui_cells_form">
       
        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">红包口令</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text" name="hbkl" id="hbkl"  placeholder="请输入红包口令"  onblur="checksingle('hbkl_check')"/>  
                 <input class="weui_input" type="text" id="hbkl_check" readonly="readonly"/>  
                
            </div>
        </div>
 
 
</div>
</form>




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





	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
	<script src="<%=path%>/example/jquery.form.min.js"></script>
</body>
</html>