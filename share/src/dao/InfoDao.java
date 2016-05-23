package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.MysqlUtil;
import bean.Imformation;

public class InfoDao {

	
//	authflow=1 表示审批有效，0表示审批中，-1表示未通过审批
	//首页热度查询
	 static String reduInfo="select * from information where authflow>0 and stime-?>0  order by visitor desc";
	//即将开始查询
	 static String ontimeInfo="select * from information where sdate=? and authflow>0 order by (stime-?) desc limit 20 ";
	//历史红包查询
	 static String historyInfo="select * from information where authflow>0 and stime-?<=0 order by stime  desc limit 20";
	
	
	public List<Imformation> getTopInfoByMysql(InfoType it,int date){
		List<Imformation> list=new ArrayList<Imformation>();
		Connection conn = MysqlUtil.getInstance().getConnection();
		PreparedStatement  sta=null;
		Date time=new Date();
		
		try {
			switch(it){
			case redu:
				sta=conn.prepareStatement(reduInfo);
				sta.setLong(1, time.getTime());
			break;
			case ontime:
				sta=conn.prepareStatement(ontimeInfo);
				sta.setInt(1, date);
				sta.setLong(2, time.getTime());
			break;	
			case history:
				sta=conn.prepareStatement(historyInfo);
				sta.setLong(1, time.getTime());
			break;	
			}
			
			
			ResultSet rs=sta.executeQuery();
			while(rs.next()){
				
				Imformation info=new Imformation();
				info.setClientWx(rs.getString("clientwx"));
				info.setClientName(rs.getString("clientName"));
				info.setIntroduct_acount(rs.getInt("introduct_acount"));
				info.setIntroduct_num(rs.getInt("introduct_num"));
				info.setKouling(rs.getString("kouling"));
				info.setSdate(rs.getInt("sdate"));
				info.setStime(rs.getInt("stime"));
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
	
	/*
	 * 插入口令
	 */
	 static String addInfo="insert into information value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	public int postKL(Imformation info){
		Connection conn = MysqlUtil.getInstance().getConnection();
		try {
			PreparedStatement sta=conn.prepareStatement(addInfo);
			sta.setNull(1, java.sql.Types.INTEGER) ;
			sta.setString(2,info.getClientWx());
			sta.setString(3,info.getClientName());
			sta.setString(4,info.getClientImg());
			sta.setInt(5,info.getType());
			sta.setInt(6,info.getIntroduct_acount());
			sta.setInt(7,info.getIntroduct_num());
			sta.setString(8,info.getTgurl());
			sta.setString(9,info.getKouling());
			sta.setInt(10,info.getSdate());
			sta.setLong(11,info.getStime());
			sta.setInt(12,info.getVisitor());
			sta.setInt(13,info.getAllowVisit());
			sta.setInt(14,info.getAuthflow());
			sta.setString(15,info.getAuthreason());
			boolean status=sta.execute();

			if(status){
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
	
	
	//根据id查看信息
	 static String InfoById="select * from information where  id=? ";
	
	 public Imformation getInfoById(int infoid){
			 
			Connection conn = MysqlUtil.getInstance().getConnection();
			PreparedStatement  sta=null;
			try {
				sta=conn.prepareStatement(InfoById);
				sta.setInt(1, infoid);
				ResultSet rs=sta.executeQuery();
				
					if(rs.next()){
					
					Imformation info=new Imformation();
					info.setClientWx(rs.getString("clientwx"));
					info.setClientName(rs.getString("clientName"));
					info.setIntroduct_acount(rs.getInt("introduct_acount"));
					info.setIntroduct_num(rs.getInt("introduct_num"));
					info.setKouling(rs.getString("kouling"));
					info.setSdate(rs.getInt("sdate"));
					info.setStime(rs.getInt("stime"));
					info.setVisitor(rs.getInt("visitor"));
					info.setAllowVisit(rs.getInt("allowvisit"));
					info.setAuthreason(rs.getString("authreason")); 
					return info ;
					}else return null;
					
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally{
				MysqlUtil.getInstance().release(conn);
			}
		}
	
	//根据id查看信息
	 static String checkKlExist="select 1 from information where kouling=? and stime=?  ";
	
	 	/*
	  	 * 查看口令是否已经发过了
	  	 */
		public  boolean checkKlExist(String kl,long time){
			Connection conn = MysqlUtil.getInstance().getConnection();
			PreparedStatement  sta=null;
			try {
				sta=conn.prepareStatement(checkKlExist);
				sta.setString(1, kl);
				sta.setLong(2, time);
				ResultSet rs=sta.executeQuery();
				
				return rs.next() ;

		} catch (SQLException e) {
			e.printStackTrace();
			   return false;
		}finally{
			MysqlUtil.getInstance().release(conn);
		}
			 
	   }
	 
	 
	 
	 
	 
	 
}
