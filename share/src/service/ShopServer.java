package service;

import bean.ShopBean;
import dao.CodeDao;
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
		
		return sd.shopLoging(username, password);
		
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
