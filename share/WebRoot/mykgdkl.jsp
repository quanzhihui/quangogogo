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
    <link rel="stylesheet" href="<%=path%>/style/weui.css"/>
    <link rel="stylesheet" href="<%=path%>/example/example.css"/> 
	<title>发口令页面</title>

</head>

<body>
<script type="text/javascript">

$(document).ready(function(){

$("#dialog_result_success").hide();
$("#dialog_klqr").hide();
$("#dialog_result_fail").hide();


$("#koulingform").validate( {
rules:{
hbkl:{required:true,minlength:2},
dxrq:{required:true,minlength:8,maxlength:8},
dxsj:{required:true,minlength:4,maxlength:4}
},
messages:{
hbkl:{required:"必须填写",minlength:"最少2个字符"},
dxrq:{required:"必须填写",minlength:"必须为8位数字，如20160510"},
dxsj:{required:"必须填写",minlength:"必须为4位数字，如1710"}
},

submitHandler:function(form) {
		
var ajax_option={
url:"<%=path%>/index/post/client_fakouling",
type:"post",
success:function(data){
if(data=="1"){
$("#dialog_result_success").show();

}else if(data=="2"){
$("#dialog_result_fail").show();
}

}
};
		$(form).ajaxSubmit(ajax_option);
	}

});



$("#dialog_klqr_confirm").click(function(){
$("#dialog_klqr").hide();
$("#koulingform").submit();
});
$("#dialog_klqr_cancel").click(function(){
$("#dialog_klqr").hide();
});
$("#dialog_result_confirm").click(function(){

window.location.href="<%=path%>/index/url/main";
});

$("#formcancel").click(function(){
window.location.href="<%=path%>/index/url/main";
});

$("#dialog_result_confirm_fail").click(function(){
$("#dialog_result_fail").hide();
});



});  
 
function showConfirm(){
var kl=$("#hbkl").val();
var year=$("#dxrq").val().substring(0,4);
var month=$("#dxrq").val().substring(4,6);
var day=$("#dxrq").val().substring(6,8);
var hour=$("#dxsj").val().substring(0,2);
var minute=$("#dxsj").val().substring(2,4);

$("#klqrcontent").html("红包口令为："+kl+",时间:"+year+"年"+month+"月"+day+"日 "+hour+":"+minute);
$("#dialog_klqr").show();
} 



 
  
  </script>
  

<form id="koulingform" name="koulingform" method="post" >

<div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">红包口令</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text" name="hbkl" id="hbkl"  placeholder="请输入红包口令"/>  
                <div id="error_kouling">  </div> 
            </div>
        </div>
       
         <div class="weui_cell ">
            <div class="weui_cell_hd"><label for="" class="weui_label">兑现日期</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required"  name="dxrq"  id="dxrq" type="number"  pattern="[0-9]*" value="weui input error" placeholder="请输入日期，如2016年1月1日，请输入20160101"/>
            </div>
            <div class="weui_cell_ft">
                <i class="weui_icon_warn"></i>
            </div>
        </div>
      
        <div class="weui_cell ">
            <div class="weui_cell_hd"><label for="" class="weui_label">兑现时间</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required " name="dxsj"   id="dxsj" type="number"  pattern="[0-9]*" value="weui input error" placeholder="请输入时间，24小时制，如上午5点10分请输入0510,下午5点00分清输入1700"/>
            </div>
            <div class="weui_cell_ft">
                <i class="weui_icon_warn"></i>
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
            <div class="weui_cell_hd"><label for="" class="weui_label">允许多少人看（可不填,默认100）</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input  " name="yxdsrk" type="number"  pattern="[0-9]*" value="weui input error" placeholder="允许多少人看"/>
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
         <a class="weui_btn weui_btn_primary"  id="showTooltips" onclick="showConfirm()">确定</a>
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
            <a href="javascript:;" class="weui_btn_dialog primary" id="dialog_klqr_confirm">确定</a>
            <a href="" class="weui_btn_dialog primary" id="dialog_klqr_cancel">取消</a>
            
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