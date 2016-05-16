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
$("#dialog_klqr").hide();


$("#shangchuan").change(function(){
 
  $("#filelocation").val( $("#shangchuan").val());
 
 var formData = new FormData($( "#koulingform" )[0]);  
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

          },  
          error: function (returndata) {  
              alert(returndata);  
          }  
     });  

});





 
});  
 


  
  </script>
  

<form id="koulingform" name="koulingform" method="post"  enctype="multipart/form-data">

<div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">邀请码</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text" name="hbkl" id="hbkl"    onblur="checksingle('hbkl_check')"/>  
                 <input class="weui_input" type="text" id="hbkl_check" readonly="readonly"/>  
                <div id="error_kouling">  </div> 
            </div>
        </div>
       
       
       
        <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">用户名</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="datetime-local"  placeholder="点击录入用户名" id="ffsj" onblur="checksingle('ffsj_check')"/>
                 <input class="weui_input" type="text" id="ffsj_check" readonly="readonly"/>  
            </div>
        </div>

      
      <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">密码</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="password"  placeholder="点击录入密码" id="ffsj" onblur="checksingle('ffsj_check')"/>
                 <input class="weui_input" type="text" id="ffsj_check" readonly="readonly"/>  
            </div>
        </div>
      
          <div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">确认密码</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="password"  placeholder="请重复录入密码" id="ffsj" onblur="checksingle('ffsj_check')"/>
                 <input class="weui_input" type="text" id="ffsj_check" readonly="readonly"/>  
            </div>
        </div>  


 	<div class="weui_cell">
            <div class="weui_cell_hd"><label for="" class="weui_label">公众号或微店名称</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input" type="password"  placeholder="点击录入公众号或微店名称" id="ffsj" onblur="checksingle('ffsj_check')"/>
                 <input class="weui_input" type="text" id="ffsj_check" readonly="readonly"/>  
            </div>
        </div>  
        
      
       <div class="weui_cells weui_cells_form">
        <div class="weui_cell">
            <div class="weui_cell_bd weui_cell_primary">
                <div class="weui_uploader">
                    <div class="weui_uploader_hd weui_cell">
                        <div class="weui_cell_bd weui_cell_primary">图片上传</div>
                        <div class="weui_cell_ft">0/2</div>
                    </div>
                    <div class="weui_uploader_bd">
                        <ul class="weui_uploader_files"  >
               
    <li class="weui_uploader_file"  id="file_upload_ui"  style="background-image:url()"></li>
                      
                           
                            
                            
                        </ul>
                        <div class="weui_uploader_input_wrp">
  							<input class="weui_input" type="text" id="filelocation" name="filelocation" readonly="readonly"   />  
                            <input class="weui_uploader_input" type="file" accept="image/jpg,image/jpeg,image/png,image/gif" multipart/form-data id="shangchuan" name="shangchuan" />
                        </div>
                    </div>
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

                      	 


	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
	<script src="<%=path%>/example/jquery.form.min.js"></script>
</body>
</html>