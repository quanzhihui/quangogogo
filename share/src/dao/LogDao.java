package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.MysqlUtil;
import bean.ViewLog;

public class LogDao {

	//增加查看日志
	public static String addTicket="inser into viewlog values(NULL,?,?,?)";
	public boolean addClientViewLog(String clientwx,int infoId,Date time){
		Connection conn = MysqlUtil.getInstance().getConnection();
		try {
			PreparedStatement sta=conn.prepareStatement(addTicket);
			sta.setString(1, clientwx);
			sta.setInt(2, infoId);
			sta.setDate(3, new java.sql.Date(time.getTime()) );
			sta.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			MysqlUtil.getInstance().release(conn);
		}
	}
	
	//查看日志
	public static String viewTicket="select * from viewlog where clientwx=?";
	public List<ViewLog> viewClientTicket(String clientwx ){
		Connection conn = MysqlUtil.getInstance().getConnection();
		List<ViewLog> list=new ArrayList<ViewLog>();
		try {
			PreparedStatement sta=conn.prepareStatement(addTicket);
			sta.setString(1, clientwx);
			ResultSet rs=sta.executeQuery();
			while(rs.next()){
			ViewLog vl=new ViewLog();
			vl.setClientWx(rs.getString("clientwx")) ;
			vl.setInfoId(rs.getInt("infoid"));
			vl.setViewdate(new Date(rs.getLong("viewdate")));
			vl.setKouling(rs.getString("kouling"));
			vl.setSdate(rs.getInt("sdate"));
			vl.setStime(rs.getInt("stime"));
			vl.setTgurl(rs.getString("tgurl"));
			list.add(vl);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			MysqlUtil.getInstance().release(conn);
		}
	}
	
}
