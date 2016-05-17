package service;

import dao.ShopDao;


/*
 * 商家服务
 */
public class ShopServer {

	
	/*
	 * 商家登录
	 */
	public static int shopLoging(String username, String password){
		ShopDao sd=new ShopDao();
		
		return 1;
		
	}
	
	
	/*
	 * 验证是否是靠谱的验证码
	 */
	public static boolean isRightYzm(String yqm){
		ShopDao sd=new ShopDao();
		
		return true;
		
	}
	

	/*
	 * 使用验证码
	 */
	public static int shopUseYzm(String username, String password){
		ShopDao sd=new ShopDao();
		
		return 1;
		
	}
	
	
	
	
	
	
}
