package service;

import java.util.List;
import java.util.Map;

import util.ShareCache;
import util.ShareConst;
import bean.Client;
import bean.Wxbean_ClientBean;
import dao.ClientDao;
import dao.ClientType;

public class ClientServer {

	//系统重启的时候，将数据库中的客户信息加载到内存中，避免大量查库
	public static void init(){
		 ClientDao idao =new ClientDao();
		 List<Client> list=idao.getAllClientInfo();
		 Map<String ,Client> map= ShareCache.userMap;
		 for(Client c:list){
			 map.put(c.getClientWxid(), c);
		 }
	}
	
	
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
		   public static String transTicket(String clientwx){
			   
			  ClientDao idao =new ClientDao();
			  Integer score=idao.getclientInfo(ClientType.score, clientwx);
			  Integer nowTicket=0;
			  Integer gainTicket=score/ShareConst.ScoreDuiHuanMenPiao;
			  if(gainTicket>0){
				  //修改分数
				  score=idao.addclientScore(clientwx,-gainTicket*ShareConst.ScoreDuiHuanMenPiao);
				  nowTicket=idao.addclientTicket(clientwx, gainTicket);
				  //返回修改的门票
				  return  score.toString()+";"+gainTicket.toString()+";"+nowTicket; 
			  }
			 return "0;0;0";
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
		  
	public static boolean clientRegist(String clientwx,Wxbean_ClientBean bean) {

		if (!ShareCache.userMap.containsKey(clientwx)) {
			ClientDao idao = new ClientDao();
			Client client=idao.getclientInfo(clientwx);
			if ( client== null) {
				client = new Client();
				client.setClientWxid(clientwx);
				client.setClientImg(bean.getHeadimgurl());
				client.setClientName(bean.getNickname());
				client.setScore(ShareConst.init_user_score);
				client.setTicket(ShareConst.init_user_ticket);
				
				client.setSex(bean.getSex());
				client.setCountry(bean.getCountry());
				client.setProvince(bean.getProvince());
				client.setCity(bean.getCity());
				client.setPrivilege(bean.getPrivilege());
				client.setUnionid(bean.getUnionid());
				if (idao.clientRegist(client)) {
					ShareCache.userMap.put(clientwx,client);
					return true;
				}

			}else{
				//数据库有，内存没有，进行同步
				
 				ShareCache.userMap.put(clientwx,client);
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
