package service;

import java.util.Date;
import dao.LogDao;

public class LogServer {

	
	/*
	 * 写入用户看信息，使用门票记录
	 */
	public static void writeViewLog(String clientwx,int infoId,Date time){
		LogDao idao=new LogDao();
		idao.addClientTicket(clientwx, infoId, time);
	}
	
	/*
	 * 读取用户看信息，使用门票记录
	 */
	public static void readViewLog(String clientwx){
		LogDao idao=new LogDao();
		idao.viewClientTicket(clientwx);
	}
	
}
