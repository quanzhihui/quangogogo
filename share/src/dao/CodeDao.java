package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.MysqlUtil;
import util.TextUtil;

public class CodeDao {


	 String yqm_insert="insert into code_yqm values('NULL',?,NULL,? )";
	 String tgd_insert="insert into code_tgd values('NULL',?,NULL,1,?,NULL )";


	//初次生成码
		public boolean createCode(String type,String code,Date createDatetime ){
			Connection conn = MysqlUtil.getInstance().getConnection();
			PreparedStatement  sta=null;
			try {
				if("yqm".equals(type)){
					sta=conn.prepareStatement(yqm_insert);
				}else if("tgd".equals(type)){
					sta=conn.prepareStatement(tgd_insert);
				}else{
					return false;
				}
				
				sta.setString(1, code);
				sta.setDate(2, createDatetime);
					
			  return sta.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
		String tgd_update="update code_tgd set shopid=? , usedate=? ,isvalid=0  where tgd=? ";	
		String yqm_update="update code_yqm set shopid=? , usedate=? ,isvalid=0 where yqm=?";
				
		//使用码注册或发消息
		public boolean useCode(String type,String shopid,Date useDatetime,String code ){
					Connection conn = MysqlUtil.getInstance().getConnection();
					PreparedStatement  sta=null;
					try {
						if("yqm".equals(type)){
							sta=conn.prepareStatement(yqm_update);		
						}else if("tgd".equals(type)){
							sta=conn.prepareStatement(tgd_update);
						}else{
							return false;
						}
						sta.setString(1, shopid);
						sta.setDate(2, useDatetime);
						sta.setString(3, code);
							return sta.execute();
					} catch (SQLException e) {
						e.printStackTrace();
						return false;
					}
				}
		
	  //验证是否靠谱邀请码
	    static String yqmSql="select 1 from code_yqm where yqm=? and isvalid=1 ";
	  //验证是否靠谱推广豆
	    static String tgdSql="select 1 from code_tgd where tgd=? and isvalid=1 ";
		 	
		public boolean checkCode(String type,String code){
			Connection conn = MysqlUtil.getInstance().getConnection();
			PreparedStatement sta=null;
			try {	
				if("yqm".equals(type)){
					sta=conn.prepareStatement(yqmSql);
				}else if("tgd".equals(type)){
					sta=conn.prepareStatement(tgdSql);
				}else{
					return false;
				}
					sta.setString(1, code);				
				ResultSet rs=sta.executeQuery();
				
					return rs.next();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
				
			
}
