package service;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.FileUploadUtil;
import util.ShareConst;
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
			String tmpInfoid=request.getParameter("imformationid");
			Integer infoid =0 ;
			if(tmpInfoid!=null){
				infoid =Integer.valueOf(tmpInfoid);
			}
		
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
				
				
				
			}else if(uri.indexOf("/log/") != -1){
				
				 if (uri.indexOf("/log/tuiguanclick") != -1) {
					String tgurl=request.getParameter("imformationid");
						LogServer.tgUrlClickLog(clientwx, Integer.valueOf(infoid), tgurl);
						response.getWriter().write("ok");
				}
				
			}else if(uri.indexOf("/shop/") != -1){
				
				 if (uri.indexOf("/shop/userlogin") != -1) {
					String shopname=request.getParameter("shopname");
					String shoppassword=request.getParameter("shoppassword");
					request.getSession().setAttribute("shopname",shopname);
					response.getWriter().write(String.valueOf(ShopServer.shopLoging(shopname, shoppassword)));
				}else if (uri.indexOf("/shop/image") != -1) {
					
					ServletFileUpload upload=FileUploadUtil.getServletFileUpload();
					List<FileItem> fileItems = upload.parseRequest(request);  
					String imgName="";
					   for (Iterator iter = fileItems.iterator(); iter.hasNext();) {  
						   FileItem item = (FileItem) iter.next();  
						   if(item.getName()==null||"".equals(item.getName())){
							   continue;
						   }
						   imgName=CodeServer.getImageCode()+".png";
						   File savedFile = new File(ShareConst.imgPath, imgName);
						   item.write(savedFile);
					   }
					
					   System.out.println("http://"+ShareConst.domain+ShareConst.projectname+"/img/"+imgName);
					response.getWriter().write("http://"+ShareConst.domain+ShareConst.projectname+"/img/"+imgName);
				}else if (uri.indexOf("/shop/yqm_registe") != -1) {
					String yqm=request.getParameter("yqmshuru");
					
					if(ShopServer.isRightYzm(yqm)){				
						response.getWriter().write("true");
					}else{
						response.getWriter().write("false");
					}
					
				}else if (uri.indexOf("/shop/tgd_use") != -1) {
					String tgd=request.getParameter("yhdshuru");
					
					if(ShopServer.isRightYhd(tgd)){				
						response.getWriter().write("true");
					}else{
						response.getWriter().write("false");
					}
					
				}
				 
				 
				 
				
			}
			

		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		
	}
	
	
}
