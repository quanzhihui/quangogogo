package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.MysqlUtil;

public class CodeDao {


	public  String yqm_insert="insert into yqm values('NULL',?,NULL,? )";
	
	public  String yqm_update="update yqm set shopid=? , usedate=?  where yqm=?";
	
	public  String tgd_insert="insert into tgd values('NULL',?,NULL,? )";
	
	public  String tgd_update="update tgd set shopid=? , usedate=?  where yqm=?";
	
	//初次生成码
		public boolean createCode(String type,String code,Date createDatetime ){
			Connection conn = MysqlUtil.getInstance().getConnection();
			PreparedStatement  sta=null;
			try {
				if("yqm".equals(type)){
					sta=conn.prepareStatement(yqm_insert);
					
				}else{
					sta=conn.prepareStatement(tgd_insert);
					
				}
				
				sta.setString(1, code);
				sta.setDate(2, createDatetime);
					
			  return sta.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		//使用码注册或发消息
		public boolean useCode(String type,String shopid,Date useDatetime,String code ){
					Connection conn = MysqlUtil.getInstance().getConnection();
					PreparedStatement  sta=null;
					try {
						if("yqm".equals(type)){
							sta=conn.prepareStatement(yqm_update);		
						}else{
							sta=conn.prepareStatement(tgd_update);
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
		
//		//生成推广豆
//		public boolean createTGD(String shopid,Date useDatetime,String tgd ){
//							Connection conn = MysqlUtil.getInstance().getConnection();
//							PreparedStatement  sta=null;
//							try {
//									sta=conn.prepareStatement(tgd_insert);
//									sta.setString(1, shopid);
//									sta.setDate(2, useDatetime);
//									sta.setString(3, tgd);
//									return sta.execute();
//							} catch (SQLException e) {
//								e.printStackTrace();
//								return false;
//							}
//						}
//		
//		//使用推广豆
//				public boolean useTGD(String shopid,Date useDatetime,String tgd ){
//							Connection conn = MysqlUtil.getInstance().getConnection();
//							PreparedStatement  sta=null;
//							try {
//									sta=conn.prepareStatement(tgd_update);
//									sta.setString(1, shopid);
//									sta.setDate(2, useDatetime);
//									sta.setString(3, tgd);
//									return sta.execute();
//							} catch (SQLException e) {
//								e.printStackTrace();
//								return false;
//							}
//						}
}
