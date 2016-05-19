package service;

import bean.ShopBean;
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
	 * 验证是否是靠谱的优惠豆
	 */
	public static boolean isRightYhd(String yhd){
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
	
	
	/*
	 * 根据用户名获取商家信息
	 */
	public static ShopBean getShopByUserName(String username ){
		ShopDao sd=new ShopDao();
		ShopBean sb=new ShopBean();
		sb.setShopName("清华");
		sb.setShopImg("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1934167097,2725626347&fm=116&gp=0.jpg");
		sb.setShopUserPasswd("sdlfjlskdjf");
		sb.setShopIntroduct("da的反党反");
				
		
		return sb;
		
	}
	
	
	
	
	
	
}
