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
String shopusername=(String)request.getSession().getAttribute("shopusername");
List<Information> infoList=  KLInfoServices.getClientInfo(InfoCreateType.shop,shopusername);
%>
<html lang="zh-cmn-Hans">
<head>
	<script src="<%=basePath%>example/jquery.min.js"></script>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="<%=path%>/style/weui.css"/>
    <link rel="stylesheet" href="<%=path%>/example/example.css"/> 
    <link rel="stylesheet" href="<%=path%>/example/table.css"/> 
    
    
	<title>商家历史口令</title>

</head>

<body>


<script type="text/javascript">

var clientid=<%=shopusername%>

        $(document).ready(function(){
        
         $("#gobacktomain").click(function(){
               window.parent.location.href="<%=path%>/index/url/main"; 
          });
        });


 </script>



<table class="bordered">

    <thead>
    <tr>
        <th>红包时间</th>        
        <th>红包口令</th>
        <th>审批状态</th>
        <th>红包领取数</th>
        <th>审批意见</th>
    </tr>
    </thead>
    
    <% for(int i=0;i<infoList.size();i++){
    
    %>
    <tr>
        <td><%=TextUtil.getOutputDayTimeStamp(infoList.get(i).getStime()) %></td>        
        <td><%=infoList.get(i).getKouling() %></td>
        <td> <%=TextUtil.getNameByAuditType(infoList.get(i).getAuthflow() )%>       </td>
         <td> <%=infoList.get(i).getVisitor() %>       </td>
        <td> <%=infoList.get(i).getAuthreason() %>       </td>
      
    </tr>        
   <% }%>

</table>
<div class="hd">
<div class="bd spacing">
 <a href="javascript:;" class="weui_btn weui_btn_primary" id="gobacktomain">返回红包界面</a>
</div>
</div>
  
  
 
    <script src="<%=path%>/example/router.min.js"></script>
    <script src="<%=path%>/example/example.js"></script>
</body>
</html>