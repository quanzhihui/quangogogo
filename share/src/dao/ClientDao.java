package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.MysqlUtil;
import util.TextUtil;
import bean.Client;
import bean.Imformation;

public class ClientDao {

	
	//门票查询
	public static String clientTicket="select ticket from client where clientwx=?";
	//积分查询
	public static String clientScore="select score from client where clientwx=?";
	
	//所有信息查询
	public static String clientinfo="select * from client where clientwx=?";
	
	
	//门票加积分查询
	public Client getclientInfo(String clientwx ){
		Connection conn = MysqlUtil.getInstance().getConnection();
		PreparedStatement  sta=null;
		try {
			
			sta=conn.prepareStatement(clientinfo);
			sta.setString(1, clientwx);
			Client cli=new Client();
			ResultSet rs=sta.executeQuery();
			if(rs.next()){
			cli.setScore(rs.getInt("score"));
			cli.setTicket(rs.getInt("ticket"));
			cli.setTgurl(rs.getString("tgurl"));
			cli.setClientName(rs.getString("clientname"));
			cli.setClientImg(rs.getString("clientimg"));		
			return cli;
			}else return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			MysqlUtil.getInstance().release(conn);
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
			if(rs.next()){
			return rs.getInt(1);
			}else return 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			MysqlUtil.getInstance().release(conn);
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
		}finally{
			MysqlUtil.getInstance().release(conn);
		}
	}
	
	//查询当天是否已签到
	
	public static String query_qiandao="select 1 from log_qiandao where clientwx=? and date=?";
	public boolean getQiandao(String clientwx ){
		Connection conn = MysqlUtil.getInstance().getConnection();
		PreparedStatement  sta=null;
		try {	
				sta=conn.prepareStatement(query_qiandao);
				sta.setString(1, clientwx);
				sta.setInt(2, TextUtil.getCurrentDay());	
			ResultSet rs=sta.executeQuery();
			return rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			MysqlUtil.getInstance().release(conn);
		}
	}
	
	//执行签到
	public static String insert_qiandao="insert into log_qiandao value(NULL,?,?) ";
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
		}finally{
			MysqlUtil.getInstance().release(conn);
		}
	}
	
	
	
	//增加/减少分数
	public static String addScore_update="update client set score=score+ ? where clientwx= ?";
	public int addclientScore(String clientwx,int num){
		Connection conn = MysqlUtil.getInstance().getConnection();
		try {
			PreparedStatement sta=conn.prepareStatement(addScore_update);
			sta.setInt(1, num);
			sta.setString(2, clientwx);
			sta.executeUpdate();
			return getclientInfo(ClientType.score,clientwx);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			MysqlUtil.getInstance().release(conn);
		}
	}
	
	/*
	 * 获取读过的口令
	 */	
	public static String getMyInfo="select * from viewlog where clientwx=? and authflow=1 order by visitor desc,zan desc ,keng asc";
	
	public List<Imformation> getMyInfoByMysql(String clientwx){
		List<Imformation> list=new ArrayList<Imformation>();
		Connection conn = MysqlUtil.getInstance().getConnection();
		try{
	
		PreparedStatement  sta=conn.prepareStatement(getMyInfo);
	
			ResultSet rs=sta.executeQuery();
			while(rs.next()){
				
				Imformation info=new Imformation();
				info.setClientWx(rs.getString("clientwx"));
				info.setClientName(rs.getString("clientName"));
				info.setIntroduct_acount(rs.getInt("introduct_acount"));
				info.setIntroduct_num(rs.getInt("introduct_num"));
				info.setKouling(rs.getString("kouling"));
				info.setSdate(rs.getInt("sdate"));
				info.setStime(rs.getInt("shour"));
				info.setVisitor(rs.getInt("visitor"));
				info.setAllowVisit(rs.getInt("allowvisit"));
				list.add(info);
				
			}
			return list ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			MysqlUtil.getInstance().release(conn);
		}
	}

	
	
}
