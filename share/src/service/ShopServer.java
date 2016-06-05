package service;

import bean.ShopBean;
import dao.CodeDao;
import dao.InfoDao;
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
	public static ShopBean getShopByName(String shopName ){
		ShopDao sd=new ShopDao();
	
		return 	sd.getShopByName(shopName);

		
	}
	
	
	/*
	 * 修改商家密码
	 */
	public static int changeShopPasswd(String username, String password){
		ShopDao sd=new ShopDao();
		return sd.changeShopPasswd(username, password);
	}
	
	
	/*
  	 * 商家注册
  	 */
	public static int shopRegist(ShopBean shop ){
		ShopDao sd=new ShopDao();
		return  sd.shopRegist(shop);
   }
	
	
	
	
}
