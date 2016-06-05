package service;

import java.util.Date;
import java.util.List;
import bean.ViewLog;
import dao.LogDao;

public class LogServer {

	
	/*
	 * 写入用户看信息，使用门票记录
	 */
	public static void writeViewLog(String clientwx,int infoId,Date time){
		LogDao idao=new LogDao();
		
		idao.addClientViewLog(clientwx, KLInfoServices.getKlByinfoId(infoId), time);
	}
	
	/*
	 * 读取用户看信息，使用门票记录
	 */
	public static List<ViewLog> readViewLog(String clientwx){
		LogDao idao=new LogDao();
		
		return idao.viewClientTicket(clientwx);
	}
	
	
	
	
	
	
	/*
	 * 用户点击链接情况
	 */
	public static boolean tgUrlClickLog(String clientwx,int infoId,String url){
		LogDao idao=new LogDao();
		return true;
	}
	
}
