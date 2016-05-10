package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.MysqlUtil;
import bean.Imformation;

public class InfoDao {

	//首页热度查询
	public static String reduInfo="select * from infomation where authflow=0 order by visitor desc,zan desc ,keng asc";
	//即将开始查询
	public static String ontimeInfo="select * from infomation where date=? authflow=0 order by hour desc limit 20 ";
	//历史红包查询
	public static String historyInfo="select * from infomation where authflow=1 order by visitor desc,zan desc ,keng asc limit 20";
	
	
	public List<Imformation> getTopInfoByMysql(InfoType it,int date){
		List<Imformation> list=new ArrayList<Imformation>();
		Connection conn = MysqlUtil.getInstance().getConnection();
		PreparedStatement  sta=null;
		try {
			switch(it){
			case redu:
				sta=conn.prepareStatement(reduInfo);
			break;
			case ontime:
				sta=conn.prepareStatement(ontimeInfo);
				sta.setInt(1, date);
			break;	
			case history:
				sta=conn.prepareStatement(historyInfo);
			break;	
			}
			ResultSet rs=sta.executeQuery();
			while(rs.next()){
				
				Imformation info=new Imformation();
				info.setClientId(rs.getInt("clientid"));
				info.setClientName(rs.getString("clientName"));
				info.setIntroduct_acount(rs.getInt("introduct_acount"));
				info.setIntroduct_num(rs.getInt("introduct_num"));
				info.setKouling(rs.getString("kouling"));
				info.setSdate(rs.getInt("sdate"));
				info.setShour(rs.getInt("shour"));
				info.setSminute(rs.getInt("sminute"));
				info.setZan(rs.getInt("zan"));
				info.setKeng(rs.getInt("keng"));
				info.setVisitor(rs.getInt("visitor"));
				info.setAllowVisit(rs.getInt("allowvisit"));
				list.add(info);
				
			}
			return list ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 插入口令
	 */
	public static String addInfo="insert into infomation value(?,?,?,?,?,?,?,?,?,?);";
	
	public boolean postKL(Imformation info){
		Connection conn = MysqlUtil.getInstance().getConnection();
		try {
			PreparedStatement  sta=conn.prepareStatement(addInfo);
			//表结构定了再说
			return true;
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		
		
		
		
		
	}
	
	
}
