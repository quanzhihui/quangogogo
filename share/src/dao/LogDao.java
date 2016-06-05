package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.MysqlUtil;
import bean.Information;
import bean.ViewLog;

public class LogDao {

	//增加查看日志
	public static String addViewLog="insert into log_viewlog values(?,?,?,?,?,?,?,?)";
	public boolean addClientViewLog(String clientwx,Information  info,Date time){
		Connection conn = MysqlUtil.getInstance().getConnection();
		try {
			PreparedStatement sta=conn.prepareStatement(addViewLog);
			sta.setNull(1, java.sql.Types.INTEGER) ;
			sta.setString(2, clientwx);
			sta.setInt(3, info.getInfoId());
			sta.setLong(4, time.getTime() );
			sta.setString(5, info.getKouling() );
			sta.setInt(6, info.getSdate() );
			sta.setLong(7, info.getStime() );
			sta.setString(8, info.getTgurl());
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
	public static String viewTicket="select * from log_viewlog where clientwx=? order by stime desc limit 20 ";
	public List<ViewLog> viewClientTicket(String clientwx ){
		Connection conn = MysqlUtil.getInstance().getConnection();
		List<ViewLog> list=new ArrayList<ViewLog>();
		try {
			PreparedStatement sta=conn.prepareStatement(viewTicket);
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
