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
     <link rel="stylesheet" href="<%=path%>/example/table.css"/> 
    <% List<Information> infoList = KLInfoServices.getInfo(InfoType.unaudit);%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="<%=path%>/style/weui.css"/>
    <link rel="stylesheet" href="<%=path%>/example/example.css"/> 
	<title>发口令页面</title>

</head>

<body>
<script type="text/javascript">
$(document).ready(function(){
$("#xgmmform_check").hide();


 $("#tgd_create").click(function(){
 
 var ajax_option={
url:"<%=path%>/index/interface/code/tgdcreate",
type:"post",
success:function(data){
//1成功，-1失败

$("#tgd").val(data);

}
};
$("#tgdform").ajaxSubmit(ajax_option);
});
 

 $("#yqm_create").click(function(){

 var ajax_option={
url:"<%=path%>/index/interface/code/yqmcreate",
type:"post",
success:function(data){


$("#yqm").val(data);

}
};
$("#yqmform").ajaxSubmit(ajax_option);
 
});


 $("#xgmm").click(function(){

 var shopname=$("#shopname").val();
 var newpassword=$("#newpassword").val();

if(shopname==null||shopname==""||newpassword==null||newpassword==""||newpassword.length<6){
$("#xgmmform_check").val("用户名或密码不能为空,密码不能小于6位");
$("#xgmmform_check").show();
return;
}

 var ajax_option={
url:"<%=path%>/index/interface/adminxgmm",
type:"post",
success:function(data){
//1成功，-1失败
if(data=="-1"){
 $("#xgmmform_check").val("密码修改失败，可能是用户名不正确");
}else if(data=="1"){
$("#xgmmform_check").val("密码修改成功");
}
$("#xgmmform_check").show();
}
};
$("#xgmmform").ajaxSubmit(ajax_option);
 
});



 $("#auditbutton").click(function(){


 var ajax_option={
url:"<%=path%>/index/interface/admin_audit",
type:"post",
success:function(data){
//1成功，-1失败
if(data=="-1"){
 alert("审批失败");
}else if(data=="1"){
 window.location.href="<%=path%>/admin/adminmain"; 
}
}
};
$("#auditform").ajaxSubmit(ajax_option);
});





});  
 






  </script>
  <form id="tgdform"   method="post" >
  <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">生成推广豆</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text"  value="" id="tgd"  />  
                <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_primary" id="tgd_create" >生成推广豆</a>
                <div id="error_kouling">  </div> 
            </div>
   </div>  
 </form>
 
 
   <form id="yqmform"  method="post" >
  <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">生成邀请码</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text"  value="" id="yqm"  />  
                <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_primary" id="yqm_create" >生成邀请码</a>
                <div id="error_kouling">  </div> 
            </div>
   </div>  
 </form>
 
 
<form id="xgmmform"  method="post" >
<div class="weui_cells weui_cells_form">
       
        <div class="weui_cell">
            <div class="weui_cell_hd"><label class="weui_label">修改密码</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="weui_input required" type="text" id="shopname"   name="shopname"  placeholder="请输入商户用户名或公众号名"  "/>
                <input class="weui_input required" type="text" id="newpassword" name="newpassword" placeholder="请输入新密码，不少于6位"  />  
                  <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_primary" id="xgmm" >确认修改</a>
                <input class="weui_input" type="text" id="xgmmform_check" readonly="readonly"/>  
                
            </div>
        </div>
 
 
</div>
</form>

<form id="auditform"  method="post" >
<table class="bordered">

    <thead>
    <tr>
    	 <th>红包类型</th>    
        <th>红包时间</th>        
        <th>红包口令</th>
        <th>活动链接</th>
        <th>红包金额</th>
        <th>红包数量</th>
        <th>是否批准</th>  
         <th>审批意见</th>  
    </tr>
    </thead>
    
    <% for(int i=0;i<infoList.size();i++){
    
    %>
    <tr>
     	<td><%=TextUtil.getNameByKlType(infoList.get(i).getType())%>   <input  style="display:none;" name="kltype_<%=infoList.get(i).getInfoId()%>" value=<%=infoList.get(i).getType() %>  > </td>     
        <td><%=TextUtil.getOutputDayTimeStamp(infoList.get(i).getStime()) %></td>        
        <td><%=infoList.get(i).getKouling() %></td>
        <td>
        <a href="javascript:;" id="<%=infoList.get(i).getInfoId()%>" > <%=infoList.get(i).getTgurl() %></a>
           
        <script>
        $(document).ready(function(){
	  $("#<%=infoList.get(i).getInfoId()%>").click(function(){
	  infoid="<%=infoList.get(i).getInfoId()%>";
	  tgurl="<%=infoList.get(i).getTgurl()%>";
	   $.post("<%=path%>/index/interface/log/tuiguanclick",
  {
    clientwx:clientid,
    tgurl:tgurl,
    infomationid:infoid
  },
  function(data,status){
  window.parent.location.href=tgurl;
  });
	  
	  });
	});
        </script>
        </td>
     <td><%=infoList.get(i).getIntroduct_acount() %></td>
     <td><%=infoList.get(i).getIntroduct_num()    %></td>   
      <td>  
            
                <select class="weui_select" id="isaudit_<%=infoList.get(i).getInfoId()%>" name="isaudit_<%=infoList.get(i).getInfoId()%>">
                    <option value="0">待审批</option>
                    <option value="1">同意</option>
                    <option value="-1">不同意</option>     
                </select>
            
      </td>
       <td><input name="auditreason_<%=infoList.get(i).getInfoId()%>"    > </td>
    
    </tr>        
   <% }%>

</table>

     <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_primary" id="auditbutton" >审  批</a>
</form>





	<script src="<%=path%>/example/zepto.min.js"></script>
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
	<script src="<%=path%>/example/jquery.form.min.js"></script>
</body>
</html>