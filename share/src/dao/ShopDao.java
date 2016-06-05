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
		}finally{
			MysqlUtil.getInstance().release(conn);
		}
	
	}

	
	/*
	 * 商家登录，1表示修改成功，-1表示查无此号
	 */
//	  static String checkShopSql="select 1 from  shop where shopusername=? ";
	  static String shopUpateSql="update shop  set shopuserpasswd=? where shopusername=? ";
	
	  public int changeShopPasswd(String username, String password){
		Connection conn = MysqlUtil.getInstance().getConnection();
		PreparedStatement  sta=null;
		try {
			
			sta=conn.prepareStatement(shopUpateSql);
			sta.setString(1, password);
			sta.setString(2, username);
			
			if(sta.executeUpdate()>0){
				return 1;
			}else{
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally{
			MysqlUtil.getInstance().release(conn);
		}
		
		return -1;
		
	}
	
	  
	  static String shopRegistSql="insert into shop values(?,?,?,?,?,?);";
		
	  public int shopRegist(ShopBean shop){
		Connection conn = MysqlUtil.getInstance().getConnection();
		PreparedStatement  sta=null;
		try {
			
			sta=conn.prepareStatement(shopRegistSql);
			sta.setNull(1, java.sql.Types.INTEGER);
			sta.setString(2, shop.getShopName());
			sta.setString(3 , shop.getShopUserPasswd() );
			sta.setString(4 , shop.getShopName() );
			sta.setString( 5, shop.getShopImg() );
			sta.setString(6 ,  shop.getShopIntroduct());
			if(sta.executeUpdate()>0){
				return 1;
			}else{
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally{
			MysqlUtil.getInstance().release(conn);
		}
		
		return -1;
		
	}
	  
	  
	    static String getShopByNameSql="select * from shop where  shopusername=?  ";
		/*
		 * 查找商家
		 */
		public ShopBean getShopByName(String shopusername){
			Connection conn = MysqlUtil.getInstance().getConnection();
			PreparedStatement  sta=null;
			ShopBean shop=new ShopBean();
			try {
				
				sta=conn.prepareStatement(getShopByNameSql);
				sta.setString(1, shopusername);
				 
				
				ResultSet rs=sta.executeQuery();
				rs.next();
				shop.setShopid(rs.getInt("shopid"));	
				shop.setShopUserName(rs.getString("shopusername"));
				shop.setShopName(shopusername);
				shop.setShopImg(rs.getString("shopimg"));
				shop.setShopIntroduct(rs.getString("shopintroduct"));		
				 
				return shop;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally{
				MysqlUtil.getInstance().release(conn);
			}
		
		}

	
}
