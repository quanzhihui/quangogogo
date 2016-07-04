package service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.InfoCreateType;

import util.FileUploadUtil;
import util.ShareConst;
import util.TextUtil;
import bean.Client;
import bean.Information;
import bean.ShopBean;

/*
 * 表单提交服务器
 */
public class PostServer {

	/*
	 * 用户发口令
	 */
	public static int postClientKL(HttpServletRequest request){
		
		//查看是否已经发过口令
		String hbkl=request.getParameter("hbkl")==null?"":request.getParameter("hbkl").toString();
		if("".equals(hbkl)){
			return -1;
		}
		
		if(request.getParameter("ffsj")==null){
			return -1;
		}	
		String ffsj=request.getParameter("ffsj").toString().replaceAll("T", " ");
		Date kldate= TextUtil.getTimeByString(ffsj);
		
		
		//如果口令已存在，则不允许再发送
		if(KLInfoServices.checkKlExist(hbkl, kldate.getTime())){
			return 2;
		}
			
		Information info =new Information();
		
		String clientwx=null;
		if(request.getSession().getAttribute("clientwx")==null){
			return -1;
		}
		
		
		clientwx=request.getSession().getAttribute("clientwx").toString();
		
		if(ShareConst.ispublic){
			Client client=ClientServer.getClientInfo(clientwx);
			if(client==null){
				return -1;
				}
			info.setClientWx(clientwx);
			info.setClientName(client.getClientName());
			info.setClientImg(client.getClientImg());
		}
		
		info.setType(ShareConst.fkltype_user);
		info.setIntroduct_acount(request.getParameter("hbzje")==null?0:Integer.valueOf(request.getParameter("hbzje")));
		info.setIntroduct_num(request.getParameter("hbzsl")==null?0:Integer.valueOf(request.getParameter("hbzsl")));
		info.setTgurl("");
		try {
			info.setKouling(new String(hbkl.getBytes(),"utf8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		info.setSdate(TextUtil.getTimeByString(kldate));
		info.setStime(kldate.getTime());
		info.setVisitor(0);
		info.setAllowVisit(ShareConst.init_user_dsrk);
		info.setAuthflow(0);
		
		
		return KLInfoServices.postKL(InfoCreateType.client,info);
		
		 
	}
	
	/*
	 * 商家发口令
	 */
	public static int postShopKL(HttpServletRequest request){



		
		//查看是否已经发过口令
		String hbkl=request.getParameter("hbkl")==null?"":request.getParameter("hbkl").toString();
		if("".equals(hbkl)){
			return -1;
		}
		
		if(request.getParameter("ffsj")==null){
			return -1;
		}	
		String ffsj=request.getParameter("ffsj").toString().replaceAll("T", " ");
		Date kldate= TextUtil.getTimeByString(ffsj);
		
		
		
		//如果口令已存在，则不允许再发送
		if(KLInfoServices.checkKlExist(hbkl, kldate.getTime())){
			return 2;
		}
		
			
		Information info =new Information();
		
		String shopusername=null;
		if(request.getSession().getAttribute("shopusername")==null){
			return -1;
		}
		
		
		shopusername=request.getSession().getAttribute("shopusername").toString();
		
		ShopBean shop=ShopServer.getShopByName(shopusername);
		if(shop==null){
			return -1;
			}
		
		
		info.setClientWx(shopusername);
		info.setClientName(shop.getShopName());
		info.setClientImg(shop.getShopImg());
		info.setType(ShareConst.fkltype_shop);
		info.setIntroduct_acount(request.getParameter("hbzje")==null?0:Integer.valueOf(request.getParameter("hbzje")));
		info.setIntroduct_num(request.getParameter("hbzsl")==null?0:Integer.valueOf(request.getParameter("hbzsl")));
		info.setTgurl(request.getParameter("tglj")==null?"": request.getParameter("tglj").toString() );
		info.setKouling(hbkl);
		info.setSdate(TextUtil.getTimeByString(kldate));
		info.setStime(kldate.getTime());
		info.setVisitor(0);
		info.setAllowVisit(ShareConst.init_shop_dsrk);
		info.setAuthflow(0);
		
		
		return KLInfoServices.postKL(InfoCreateType.shop,info);

		 
	}
	
	
	/*
	 * 商家注册
	 */
	public static int postShopZC(HttpServletRequest request) {
		
		ServletFileUpload upload=FileUploadUtil.getServletFileUpload();
		List<FileItem> fileItems;
		try {
			fileItems = upload.parseRequest(request);
			 ShopBean shop=new ShopBean();
			 String yqm=null;
		String imgName="";
		   for (Iterator iter = fileItems.iterator(); iter.hasNext();) {  
			   FileItem item = (FileItem) iter.next();  
			   if(item.isFormField()&&item.getString()!=null&&!"".equals(item.getString())){
				   System.out.println(item.getString());
				   //没有邀请码不允许注册
				   if("yqm".equals(item.getFieldName())&&!"".equals(item.getString())){
					  yqm=item.getString();
				   }else if("yqm".equals(item.getFieldName())&&(null==item.getString()||"".equals(item.getString()))){
					   return -1;
				   }
				  
				   if("shoppassword".equals(item.getFieldName())) {
					   shop.setShopUserPasswd(item.getString());
				   }else if("shopusername".equals(item.getFieldName())) {
					   shop.setShopUserName(item.getString());
				   }else if("gzhmc".equals(item.getFieldName())) {
					   shop.setShopName(item.getString());
				   }else if("sjjj".equals(item.getFieldName())) {
					   shop.setShopIntroduct(item.getString());
				   }else if("imgurl".equals(item.getFieldName())) {
					   shop.setShopImg(item.getString());
				   }
			   }
		   }
		   if(yqm!=null&&ShopServer.shopRegist(shop)>0){
				//使用邀请码
			   CodeServer.useYQMCode(ShopServer.getShopByName(shop.getShopName()).getShopid(), yqm) ;
			   return 1;
		   
		   }
		   return -1;
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}  

	}
	
	
 
	
	
	
	
	
	
}
