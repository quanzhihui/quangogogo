package service;

import util.ShareCache;
import util.ShareConst;
import bean.Client;
import dao.ClientDao;
import dao.ClientType;

public class ClientServer {

	
	 /*
	  * 获取客户信息
	  */
	   public static int getClientInfo(ClientType it,String clientwx){
		   ClientDao idao =new ClientDao();
			return idao.getclientInfo(it,clientwx);
		}
	   
	   /*
		  * 获取客户信息
		  */
		   public static Client getClientInfo(String clientwx){

			   ClientDao idao =new ClientDao();
				return idao.getclientInfo(clientwx);
			}
		   
		   /*
		    * 增加/减少门票 ,-1表示门票不够，不能再扣减了
		    */
		   public static int useTicket(String clientwx,int num){
			   
			   ClientDao idao =new ClientDao();
			  int ticketnum=idao.getclientInfo(ClientType.ticket, clientwx);
			  if(ticketnum-num>=0) {
				  return  idao.addclientTicket(clientwx, -num);
			  }else{
				  return -1;
			  }
			
		   }
	   
		   
		   /*
		    * 签到
		    */
		   public static boolean qiandao(String clientwx){
			   
			   ClientDao idao =new ClientDao();
			  
			   //如果没签到
			   if(!idao.getQiandao(clientwx)){
				   //签到并加分
				   idao.setQiandao(clientwx);
				   idao.addclientScore(clientwx,ShareConst.qianDaoScore);
				   return true;
			   }
			  return false;
			 
		   }
		   
		   /*
		    * 增加/减少分数
		    */
		   public static int addScore(String clientwx,int score){
			   
			   ClientDao idao =new ClientDao();
			 //如果是负数，则表示没有分数可以扣减了
			return   idao.addclientScore(clientwx, score);
		   }
		   
		   /*
		    * 分数兑换门票
		    */
		   public static int transTicket(String clientwx){
			   
			  ClientDao idao =new ClientDao();
			  int score=idao.getclientInfo(ClientType.score, clientwx);
			  int gainTicket=score/ShareConst.ScoreDuiHuanMenPiao;
			  if(gainTicket>0){
				  //修改分数
				  idao.addclientTicket(clientwx,-gainTicket*ShareConst.ScoreDuiHuanMenPiao);
				  //返回修改的门票
				  return   idao.addclientTicket(clientwx, gainTicket); 
			  }
			 return 0;
		   }
	   
		   /*
		    * 获取推广链接
		    */
		   public static String getTGurl(String clientwx){
			   ClientDao idao =new ClientDao();
			   String tgurl=idao.getclientInfo(clientwx).getTgurl();
			   if(tgurl==null){
				   tgurl="http://"+ShareConst.domain+"/share/index/url/main/?tj="+CodeServer.getCode(null, ShareConst.yqmlength);
				   idao.addClientTgurl(tgurl, clientwx);
			   }
			   return tgurl; 
		   }
		   
		   
		  /*
		   * 是否新用户，不是新用户则注册,新用户返回true,老用户返回false
		   */
		  
	public static boolean clientRegist(String clientwx) {

		if (!ShareCache.userSet.contains(clientwx)) {
			ClientDao idao = new ClientDao();
			if (idao.getclientInfo(clientwx) == null) {
				Client client = new Client();
				client.setClientWxid(clientwx);
				client.setScore(ShareConst.init_user_score);
				client.setTicket(ShareConst.init_user_ticket);
				if (idao.clientRegist(client)) {
					ShareCache.userSet.add(clientwx);
					return true;
				}

			}else{
				//数据库有，内存没有，进行同步
 				ShareCache.userSet.add(clientwx);
			}
		}
		return false;
	}
		   
		   	  /*
			   * 用户增加名字
			   */
			  
			   public static boolean addClientName(String clientwx,String name){
				   ClientDao idao =new ClientDao();
				   return   idao.addClientName(clientwx, name);
			   }
		   
}
