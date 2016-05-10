package service;

import java.sql.Date;

import util.ScoreConst;

import bean.Client;
import dao.ClientDao;
import dao.ClientType;

public class ClientServer {

	
	 /*
	  * 获取客户信息
	  */
	   public static int getClientInfo(ClientType it,String clientwx){
		   ClientDao idao =new ClientDao();
		   return 1;
//			return idao.getclientInfo(it, clientwx);
		}
	   
	   /*
		  * 获取客户信息
		  */
		   public static Client getClientInfo(String clientwx){
			   Client cli=new Client();
			   cli.setClientid(100);
			   cli.setScore(200);
			   cli.setTicket(10);
		
			   return cli;
//			   ClientDao idao =new ClientDao();
//				return idao.getclientInfo( clientwx);
			}
		   
		   /*
		    * 增加/减少门票
		    */
		   public static boolean useTicket(String clientwx,int num){
			   
			   ClientDao idao =new ClientDao();
			  
//			   idao.addclientScore(clientwx, num);
			  return true; 
		   }
	   
		   
		   /*
		    * 签到
		    */
		   public static boolean qiandao(String clientwx){
			   
			   ClientDao idao =new ClientDao();
			  
			   //如果没签到
//			   if(!idao.getQiandao(clientwx)){
//				   //签到并加分
//				   idao.setQiandao(clientwx);
//				   idao.addclientScore(clientwx,ScoreConst.qianDaoScore);
//				   return true;
//			   }
//			  return false;
			   return true;
		   }
		   
		   
		   /*
		    * 增加/减少分数
		    */
		   public static boolean addScore(String clientwx,int score){
			   
			   ClientDao idao =new ClientDao();
			  
//			   idao.addclientScore(clientwx, num);
			  return true; 
		   }
		   
		   
	   
}
