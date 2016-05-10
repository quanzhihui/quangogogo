package service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientType;

/*
 * 专用于取接口服务
 */
public class InterfaceServer {

	/*
	 * 映射
	 * client ClientServer
	 * code CodeServer
	 * kl KLInfoServices
	 * log LogServer
	 * 
	 */
	
	
	
	public static void getData(HttpServletRequest request,HttpServletResponse response){
	try{
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf8");
	
	if(request.getRequestURI().indexOf("/client/type")!=-1){
		String clientwx=request.getParameter("client");
		String type=request.getParameter("type").toString();
		if("ticket".equals(type)){
			response.getWriter().write(String.valueOf(ClientServer.getClientInfo(ClientType.ticket, clientwx)));
			
		}else if("score".equals(type)){
			
			response.getWriter().write(String.valueOf(ClientServer.getClientInfo(ClientType.score, clientwx)));
		}else if("all".equals(type)){
			ClientServer.getClientInfo(clientwx);
		}		
	}else if(request.getRequestURI().indexOf("info/useticket")!=-1){
		String infoid=request.getParameter("imfomationid");
		String clientwx=request.getParameter("clientwx");
		response.getWriter().write(KLInfoServices.useTicket(infoid,clientwx));
	
	 }else if(request.getRequestURI().indexOf("info/redirect")!=-1){
		 String infoid=request.getParameter("imfomationid");
		 String url=KLInfoServices.getInfoRedirectUrl(infoid);
		 if(url!=null){
			 response.getWriter().write(KLInfoServices.getInfoRedirectUrl(infoid))  ;
		 }else{
			 response.getWriter().write("/share/index/")  ;
		 }
		
	 }else if(request.getRequestURI().indexOf("client/qiandao")!=-1){
		 String clientwx=request.getParameter("clientwx");
		 response.getWriter().write(ClientServer.qiandao(clientwx)?"1":"0")  ;
	 }
	
	
	}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		
	}
	
	
}
