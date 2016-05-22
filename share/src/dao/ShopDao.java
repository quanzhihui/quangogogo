package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.MysqlUtil;
import bean.ShopBean;

public class ShopDao {

	
	
    static String loginsql="select 1 from shop where shopusername=? and shopuserpasswd=?";
	/*
	 * 商家登录，1表示登录成功，-1表示查无此号
	 */
	public int shopLoging(String username, String password){
		 
		

		Connection conn = MysqlUtil.getInstance().getConnection();
		PreparedStatement  sta=null;
		try {
			
			sta=conn.prepareStatement(loginsql);
			sta.setString(1, username);
			sta.setString(2, password);
			ShopBean shop=new ShopBean();
			ResultSet rs=sta.executeQuery();
//			rs.next();
//			shop.setShopName(username);
//			shop.setShopName(rs.getString("shopname"));
//			shop.setShopImg(rs.getString("shopimg"));
//			shop.setShopIntroduct(rs.getString("shopintroduct"));		
			if(rs.next()){
				return 1;
			}else{
				return -1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	
	}

	
	
	
}
