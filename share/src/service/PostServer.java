package service;

import javax.servlet.http.HttpServletRequest;
import bean.Imformation;

/*
 * 表单提交服务器
 */
public class PostServer {

	/*
	 * 用户发口令
	 */
	public static int postClientKL(HttpServletRequest request){
		request.getParameter("");
		Imformation info =new Imformation();
//		return KLInfoServices.postKL(info);	
		return 1;
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
