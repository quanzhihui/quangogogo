package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Imformation;
import bean.ViewLog;
import dao.ClientDao;
import dao.LogDao;

public class LogServer {

	
	/*
	 * 写入用户看信息，使用门票记录
	 */
	public static void writeViewLog(String clientwx,int infoId,Date time){
		LogDao idao=new LogDao();
		idao.addClientViewLog(clientwx, infoId, time);
	}
	
	/*
	 * 读取用户看信息，使用门票记录
	 */
	public static List<ViewLog> readViewLog(String clientwx){
		LogDao idao=new LogDao();
		
//		return idao.viewClientTicket(clientwx);
		
	
		List<ViewLog> list=new ArrayList<ViewLog>();
		ViewLog log1=new ViewLog();
		log1.setInfoId(1);
		log1.setKouling("DF");
		log1.setStime(10202555);
		log1.setTgurl("http://www.sina.com");
		list.add(log1);
		ViewLog log2=new ViewLog();
		log2.setInfoId(331);
		log2.setKouling("DF");
		log2.setStime(10202555);
		log2.setTgurl("http://www.baidu.com");
		list.add(log2);
		return list;
	}
	
	
	
	
	
	
	/*
	 * 用户点击链接情况
	 */
	public static boolean tgUrlClickLog(String clientwx,int infoId,String url){
		LogDao idao=new LogDao();
		return true;
	}
	
}
