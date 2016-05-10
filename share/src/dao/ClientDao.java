package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import util.MysqlUtil;
import util.TextUtil;
import bean.Client;

public class ClientDao {

	
	//门票查询
	public static String clientTicket="select ticket from client where clientwx=?";
	//积分查询
	public static String clientScore="select score from client where clientwx=?";
	
	//门票加积分查询
	public Client getclientInfo(String clientwx ){
		Connection conn = MysqlUtil.getInstance().getConnection();
		PreparedStatement  sta=null;
		try {
			
				sta=conn.prepareStatement(clientTicket);
				sta.setString(1, clientwx);
			
			Client cli=new Client();
			ResultSet rs=sta.executeQuery();
			rs.next();
			cli.setScore(rs.getInt("score"));
			cli.setTicket(rs.getInt("ticket"));
			return cli;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//单查门票或积分
	public int getclientInfo(ClientType ct,String clientwx ){
		Connection conn = MysqlUtil.getInstance().getConnection();
		PreparedStatement  sta=null;
		try {
			switch(ct){
			case ticket:
				sta=conn.prepareStatement(clientTicket);
				sta.setString(1, clientwx);
			break;
			case score:
				sta=conn.prepareStatement(clientScore);
				sta.setString(1, clientwx);
			break;	
			}
			
			ResultSet rs=sta.executeQuery();
			rs.next();
			return rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	//增加/减少门票
	public static String addTicket="update client set ticket=ticket+? where clientwx=?";
	public int addclientTicket(String clientwx,int num){
		Connection conn = MysqlUtil.getInstance().getConnection();
		try {
			PreparedStatement sta=conn.prepareStatement(addTicket);
			sta.setInt(1, num);
			sta.setString(2, clientwx);
			sta.executeUpdate();
			return getclientInfo(ClientType.ticket,clientwx);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	//查询当天是否已签到
	
	public static String query_qiandao="select 1 from qiandao where clientwx=? and date=?";
	public boolean getQiandao(String clientwx ){
		Connection conn = MysqlUtil.getInstance().getConnection();
		PreparedStatement  sta=null;
		try {	
				sta=conn.prepareStatement(query_qiandao);
				sta.setString(1, clientwx);
				sta.setInt(2, TextUtil.getCurrentDay());	
			ResultSet rs=sta.executeQuery();
			if(rs.next()){
				return false;
			}else{
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	//执行签到
	public static String insert_qiandao="insert into qiandao value('NULL',?,?) ";
	public boolean setQiandao(String clientwx){
		Connection conn = MysqlUtil.getInstance().getConnection();
		try {
			PreparedStatement sta=conn.prepareStatement(insert_qiandao);
			sta.setString(1, clientwx);
			sta.setInt(2, TextUtil.getCurrentDay());
			sta.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	//增加/减少分数
	public static String addScore_update="update client set score=score+? where clientwx=?";
	public int addclientScore(String clientwx,int num){
		Connection conn = MysqlUtil.getInstance().getConnection();
		try {
			PreparedStatement sta=conn.prepareStatement(clientTicket);
			sta.setInt(1, num);
			sta.setString(2, clientwx);
			sta.executeUpdate();
			return getclientInfo(ClientType.score,clientwx);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	
}
