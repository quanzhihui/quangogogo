package service;


public class AdminServer {

	
	
	
	/*
	 * admin登录
	 */
	public static int shopLoging(String username, String password){
	 
		if("admin".equals(username)&&"qzh_1986!".equals(password)){
			return 1;
		}else return -1;
		
		
	}
	
	
	
	
}
