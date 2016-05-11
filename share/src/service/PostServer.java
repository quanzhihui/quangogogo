package service;

import javax.servlet.http.HttpServletRequest;

import bean.Imformation;

/*
 * 表单提交服务器
 */
public class PostServer {

	/*
	 * 普通用户发送口令
	 */
	public static int postClientKL(HttpServletRequest request){
		request.getParameter("");
		Imformation info =new Imformation();
//		return KLInfoServices.postKL(info);	
		return 2;
	}
	
	/*
	 * 商家用户发送口令
	 */
	public static boolean postShopKL(HttpServletRequest request){
		request.getParameter("");
		Imformation info =new Imformation();
		return KLInfoServices.postKL(info);	
	}
	
	
}
