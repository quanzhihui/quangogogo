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

public class InfoDao {

	
//	authflow=1 表示审批有效，0表示审批中，-1表示未通过审批
	//首页热度查询
	 static String reduInfo="select * from information where authflow>0 and stime-?>0  order by visitor desc";
	//即将开始查询
	 static String ontimeInfo="select * from information where sdate=? and authflow>0 and stime-?>0  order by (stime-?) desc limit 10 ";
	//最新红包查询
	 static String historyInfo="select * from information where authflow>0 and stime-?>0   order by stime  desc limit 10";
	//未审批查询
	 static String unauditInfo="select * from information where authflow=0 ";
	//
	
	public List<Information> getTopInfoByMysql(InfoType it,int date){
		List<Information> list=new ArrayList<Information>();
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
				sta.setLong(3, time.getTime());
			break;	
			case history:
				sta=conn.prepareStatement(historyInfo);
				sta.setLong(1, time.getTime());
			break;	
			case unaudit:
				sta=conn.prepareStatement(unauditInfo);
			break;	
			
			}
			
			
			ResultSet rs=sta.executeQuery();
			while(rs.next()){
				
				Information info=new Information();
				info.setInfoId(rs.getInt("id"));
				info.setClientWx(rs.getString("clientwx"));
				info.setClientName(rs.getString("clientName"));
				info.setClientImg(rs.getString("clientimg"));
				info.setType(Integer.valueOf(rs.getString("type")));
				info.setIntroduct_acount(rs.getInt("introduct_acount"));
				info.setIntroduct_num(rs.getInt("introduct_num"));
				info.setKouling(rs.getString("kouling"));
				info.setSdate(rs.getInt("sdate"));
				info.setStime(rs.getLong("stime"));
				info.setVisitor(rs.getInt("visitor"));
				info.setAllowVisit(rs.getInt("allowvisit"));
				info.setTgurl (rs.getString("tgurl"));
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
	 static String addInfoClient="insert into information value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	 static String addInfoShop="insert into shopinfo value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	public int postKL(InfoCreateType type,Information info){
		Connection conn = MysqlUtil.getInstance().getConnection();
		try {
			PreparedStatement sta=null;
				if(InfoCreateType.client.equals(type)){
					sta=conn.prepareStatement(addInfoClient);
				}else if(InfoCreateType.shop.equals(type)){
					sta=conn.prepareStatement(addInfoShop);
				}
				
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
			

			if(sta.executeUpdate()>0){
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
	
	 public Information getInfoById(int infoid){
			 
			Connection conn = MysqlUtil.getInstance().getConnection();
			PreparedStatement  sta=null;
			try {
				sta=conn.prepareStatement(InfoById);
				sta.setInt(1, infoid);
				ResultSet rs=sta.executeQuery();
				
					if(rs.next()){
					
					Information info=new Information();
					info.setInfoId(infoid);
					info.setClientWx(rs.getString("clientwx"));
					info.setClientName(rs.getString("clientName"));
					info.setIntroduct_acount(rs.getInt("introduct_acount"));
					info.setIntroduct_num(rs.getInt("introduct_num"));
					info.setKouling(rs.getString("kouling"));
					info.setSdate(rs.getInt("sdate"));
					info.setStime(rs.getLong("stime"));
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
	 
	 
		/*
		 * 插入口令
		 */
		 static String auditInfoClient="update information set authflow=? ,authreason=? where id=? ";
		 static String auditInfoShop="update shopinfo set authflow=? ,authreason=? where id=? ";
		public int auditKL(InfoCreateType type,int infoId,int auditState,String reason){
			Connection conn = MysqlUtil.getInstance().getConnection();
			try {
				PreparedStatement sta=null ;
				if(InfoCreateType.client.equals(type ) ){
					sta=conn.prepareStatement(auditInfoClient);
				}else if(InfoCreateType.shop.equals(type)){
					sta=conn.prepareStatement(auditInfoShop);
				}
				
				sta.setInt(1, auditState) ;
				sta.setString(2,reason);
				sta.setInt(3,infoId);
			 int ss=sta.executeUpdate();
				return ss;

				 
			
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}finally{
				MysqlUtil.getInstance().release(conn);
			}
			 

		}
	 
		
		//根据用户查看信息 
		 static String InfoByClientwx="select * from information where  clientwx=? ";
		//根据商家用户名查看信息,shopusername等价于clientwx,shopname等价于clientname ,shopimg等价于clientimg
		 static String InfoByshopName="select * from shopinfo where  clientwx=? ";
		
	
		 public List<Information> getInfoByClientwx(InfoCreateType type,String name){
				 
				Connection conn = MysqlUtil.getInstance().getConnection();
				PreparedStatement  sta=null;
				List<Information> list=new ArrayList<Information>();
				try {
				if(InfoCreateType.client.equals(type)){
					sta=conn.prepareStatement(InfoByClientwx);
					
				}else if(InfoCreateType.shop.equals(type)){
					sta=conn.prepareStatement(InfoByshopName);
				 
				}
				sta.setString(1, name);
					ResultSet rs=sta.executeQuery();
					
						while(rs.next()){
							
						Information info=new Information();
						info.setClientWx(rs.getString("clientwx"));
						info.setClientName(rs.getString("clientName"));
						info.setIntroduct_acount(rs.getInt("introduct_acount"));
						info.setIntroduct_num(rs.getInt("introduct_num"));
						info.setKouling(rs.getString("kouling"));
						info.setSdate(rs.getInt("sdate"));
						info.setStime(rs.getLong("stime"));
						info.setVisitor(rs.getInt("visitor"));
						info.setAllowVisit(rs.getInt("allowvisit"));
						info.setAuthreason(rs.getString("authreason")); 
						info.setTgurl(rs.getString("tgurl"));
						list.add(info);
						
						}
						
						 return list;
						
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}finally{
					MysqlUtil.getInstance().release(conn);
				}
			}
	 
		 /*
			 * 修改访客数量
			 */
			 static String addInfoVisitorClient="update information set visitor=visitor +1  where id=? ";
			 static String addInfoVisitorShop="update shopinfo set visitor=visitor +1  where id=? ";
			public int updateVistor(InfoCreateType type,int infoId){
				Connection conn = MysqlUtil.getInstance().getConnection();
				try {
					PreparedStatement sta=null ;
					if(InfoCreateType.client.equals(type ) ){
						sta=conn.prepareStatement(addInfoVisitorClient);
					}else if(InfoCreateType.shop.equals(type)){
						sta=conn.prepareStatement(addInfoVisitorShop);
					}
					
					sta.setInt(1, infoId) ;
					return sta.executeUpdate();

					 
				
				} catch (SQLException e) {
					e.printStackTrace();
					return -1;
				}finally{
					MysqlUtil.getInstance().release(conn);
				}
				 

			}
		 
		 
}
