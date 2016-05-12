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
		try {
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf8");
			String uri = request.getRequestURI();
			String infoid = request.getParameter("imfomationid");
			String clientwx = request.getParameter("clientwx");

			if (uri.indexOf("/client") != -1) {
				if (uri.indexOf("/client/type") != -1) {

					String type = request.getParameter("type").toString();
					if ("ticket".equals(type)) {
						response.getWriter().write(
								String.valueOf(ClientServer.getClientInfo(
										ClientType.ticket, clientwx)));

					} else if ("score".equals(type)) {

						response.getWriter().write(
								String.valueOf(ClientServer.getClientInfo(
										ClientType.score, clientwx)));
					} else if ("all".equals(type)) {
						ClientServer.getClientInfo(clientwx);
					}
				} else if (uri.indexOf("client/qiandao") != -1) {

					response.getWriter().write(
							ClientServer.qiandao(clientwx) ? "1" : "0");
				} else if (uri.indexOf("/client/trans") != -1) {
					
					response.getWriter().write(String.valueOf(ClientServer.transTicket(clientwx)));
					
				}else if (uri.indexOf("/client/trans") != -1) {	
					response.getWriter().write(String.valueOf(ClientServer.transTicket(clientwx)));
				}else if (uri.indexOf("/client/gettuiguang") != -1) {
					
					response.getWriter().write(ClientServer.getTGurl(clientwx));
					
				}

				
			} else if (uri.indexOf("/info") != -1) {
				if (uri.indexOf("/info/useticket") != -1) {

					response.getWriter().write(
							KLInfoServices.useTicket(infoid, clientwx));

				} else if (uri.indexOf("info/redirect") != -1) {

					String url = KLInfoServices.getInfoRedirectUrl(infoid);
					if (url != null) {
						response.getWriter().write(
								KLInfoServices.getInfoRedirectUrl(infoid));
					} else {
						response.getWriter().write("/share/index/");
					}

				}
			}

		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		
	}
	
	
}
