package service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import util.ShareConst;
import util.TextUtil;

import bean.Client;
import bean.Imformation;

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
		String ffsj=request.getParameter("ffsj").toString().replaceAll("T", "");
		Date kldate= TextUtil.getTimeByString(ffsj);
		
		
		//如果口令已存在，则不允许再发送
		if(KLInfoServices.checkKlExist(hbkl, kldate.getTime())){
			return 2;
		}
		
			
		Imformation info =new Imformation();
		
		String clientwx=null;
		if(request.getSession().getAttribute("clientwx")==null){
			return -1;
		}
		
		
		clientwx=request.getSession().getAttribute("clientwx").toString();
		
		Client client=ClientServer.getClientInfo(clientwx);
		if(client==null){
			return -1;
			}
		
		
		info.setClientWx(clientwx);
		info.setClientName(client.getClientName());
		info.setClientImg(client.getClientImg());
		info.setType(ShareConst.fkltype_user);
		info.setIntroduct_acount(request.getParameter("hbzje")==null?0:Integer.valueOf(request.getParameter("hbzje")));
		info.setIntroduct_num(request.getParameter("hbzsl")==null?0:Integer.valueOf(request.getParameter("hbzsl")));
		info.setTgurl("");
		info.setKouling(hbkl);
		
		
		info.setSdate(TextUtil.getTimeByString(kldate));
		info.setStime(kldate.getTime());
		info.setVisitor(0);
		info.setAllowVisit(request.getParameter("yxdsrk")==null?0:Integer.valueOf(request.getParameter("yxdsrk")));
		info.setAuthflow(0);
		
		
		return KLInfoServices.postKL(info);
		
		 
	}
	
	/*
	 * 商家发口令
	 */
	public static int postShopKL(HttpServletRequest request){
		request.getParameter("");
		Imformation info =new Imformation();
//		return KLInfoServices.postKL(info);	
		return 1;
	}
	
	
	/*
	 * 商家注册
	 */
	public static String postShopZC(HttpServletRequest request){
		request.getParameter("");
//		Shop info =new Shop();
//		return KLInfoServices.postKL(info);	
		String shopname=request.getParameter("shopname");
		request.getSession().setAttribute("shopname", shopname);
		return "true";
	}
	
	
	/*
	 * 修改密码
	 */
	public static String postShopXGMM(HttpServletRequest request){
		request.getParameter("");
//		Shop info =new Shop();
//		return KLInfoServices.postKL(info);	
		return "true";
	}
	
}
