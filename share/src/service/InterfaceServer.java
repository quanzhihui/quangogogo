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
import dao.InfoCreateType;

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
	
	
	
	public static void getData(String clientwx,HttpServletRequest request,HttpServletResponse response){
		try {
//			response.setHeader("content-type", "text/html;charset=UTF-8");
//			response.setCharacterEncoding("utf8");
			String uri = request.getRequestURI();
			String tmpInfoid=request.getParameter("informationid");
			Integer infoid =0 ;
			if(tmpInfoid!=null){
				infoid =Integer.valueOf(tmpInfoid);
			}
		
		 

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
					String type=request.getParameter("type");
					if (String.valueOf(ShareConst.fkltype_shop).equals(type)) {

						response.getWriter().write(KLInfoServices.useTicket(
												InfoCreateType.shop, infoid,
												clientwx));

					} else {
						response.getWriter().write(KLInfoServices.useTicket(
												InfoCreateType.client, infoid,
												clientwx));

					}
					

				} else if (uri.indexOf("info/redirect") != -1) {

					String url = KLInfoServices.getInfoRedirectUrl(infoid);
					if (url != null) {
						response.getWriter().write(
								KLInfoServices.getInfoRedirectUrl(infoid));
					} else {
						response.getWriter().write("/share/index/url/main");
					}

				} else if (uri.indexOf("info/gettype") != -1) {
					response.getWriter().write(
							KLInfoServices.getKlByinfoId(infoid).getType()+"");								
				}
				
			}else if(uri.indexOf("/log/") != -1){
				
				 if (uri.indexOf("/log/tuiguanclick") != -1) {
					String tgurl=request.getParameter("informationid");
						LogServer.tgUrlClickLog(clientwx, Integer.valueOf(infoid), tgurl);
						response.getWriter().write("ok");
				}
				
			}else if(uri.indexOf("/shop/") != -1){
				
				 if (uri.indexOf("/shop/userlogin") != -1) {
					String shopusername=request.getParameter("shopusername");
					String shoppassword=request.getParameter("shoppassword");
				
					int status=ShopServer.shopLoging(shopusername, shoppassword);
				//登录成功，设置session值
					if(status==1){
						request.getSession().setAttribute("shopusername",shopusername);
					}
					response.getWriter().write(String.valueOf(status));
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
					
					 
					response.getWriter().write("http://"+ShareConst.domain+ShareConst.projectname+"/img/"+imgName);
				}else if (uri.indexOf("/shop/yqm_registe") != -1) {
					String yqm=request.getParameter("yqmshuru");
					
					if(CodeServer.isRightYzm(yqm)){				
						response.getWriter().write("true");
					}else{
						response.getWriter().write("false");
					}
					
				}else if (uri.indexOf("/shop/tgd_use") != -1) {
					String tgd=request.getParameter("tgdshuru");
					
					if(CodeServer.isRightYhd(tgd)){				
						response.getWriter().write("true");
					}else{
						response.getWriter().write("false");
					}
					
				}

			}else if(uri.indexOf("/code") != -1){
				//验证是否登录
				if(request.getSession().getAttribute("adminname")==null){
					response.getWriter().write("null");
					return;
				}
				
				 if(uri.indexOf("/code/tgdcreate") != -1){
						response.getWriter().write(CodeServer.getCode("tgd", ShareConst.tgdlength));
						 return ;
				}else if(uri.indexOf("/code/yqmcreate") != -1){
					
					 response.getWriter().write( CodeServer.getCode("yqm", ShareConst.yqmlength));
					 return ;
				}
				
				
			}else if(uri.indexOf("/admin") != -1){
				  if(uri.indexOf("/adminxgmm") != -1){
					  if(request.getSession().getAttribute("adminname")==null ){
						  response.getWriter().write("-1");
					  }
					String shopname=request.getParameter("shopname");
					String shoppassword=request.getParameter("newpassword");
				
					
					response.getWriter().write(String.valueOf(ShopServer.changeShopPasswd(shopname,shoppassword)));
				 }if(uri.indexOf("/admin_audit") != -1){
					  if(request.getSession().getAttribute("adminname")==null ){
						  response.getWriter().write("-1");
					}
					
					response.getWriter().write(
							String.valueOf(KLInfoServices.auditKL(request)));
					
				 }
				  
				  
			}
			

		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		
	}
	
	
}
