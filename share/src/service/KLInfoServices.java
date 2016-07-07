package service;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import util.TextUtil;
import bean.Information;
import dao.InfoCreateType;
import dao.InfoDao;
import dao.InfoType;

public class KLInfoServices {
	
/*
 * 获取口令信息
 */
   public static List<Information> getInfo(InfoType it ){
	   	InfoDao idao =new InfoDao();
		return idao.getTopInfoByMysql(it,TextUtil.getCurrentDay()) ;

	}
   
   /*
    * 用户查看自己发的口令
    */
      public static List<Information> getClientInfo(InfoCreateType type,String clientwx){
   	   	InfoDao idao =new InfoDao();
   		return idao.getInfoByClientwx(type,clientwx) ;

   	}  
   
   
   /*
    * 用门票看口令,返回口令
    */
   public static String useTicket(InfoCreateType type,int infoid,String clientwx,int costTicket){
	   
	   if(ClientServer.useTicket(clientwx, costTicket)>0){
		   LogServer.writeViewLog(clientwx,infoid,new Date());
		    //修改表单信息，访客增加1
		 	InfoDao idao =new InfoDao();
		 	idao.updateVistor(type,infoid);
		 	Information info=getInfoKL(infoid);
		   return  info.getKouling()+";"+info.getType()+";"+info.getVisitor()+";"+info.getAllowVisit();
		}else{
		   return "null";
		}
   }
   
   
	/*
	 * 根据id获取口令
	 */
   public static Information getInfoKL(Integer infoid){
	   
	   InfoDao id=new InfoDao();
	   return  id.getInfoById(infoid);
   }
   
   
   
  	/*
	 * 根据id获取推广页面
	 */
  public static String getInfoRedirectUrl(Integer infoid){
	  InfoDao id=new InfoDao();
	  return id.getInfoById(infoid).getTgurl();

}
   

	/*
	 * 根据id获取推广页面
	 */
  	public static int postKL(InfoCreateType type,Information info){
	  InfoDao id=new InfoDao();
	  return id.postKL(type,info);

  	}
  	
	/*
	 * 根据id获取整个口令信息
	 */
  	public static Information getKlByinfoId(Integer infoid){
	  InfoDao id=new InfoDao();
	  return id.getInfoById(infoid);

  	}
  	
  
  	/*
  	 * 查看口令是否已经发过了
  	 */
	public static boolean checkKlExist(String kl,Long time){
		 InfoDao id=new InfoDao();
		 return id.checkKlExist(kl, time); 
   }
  
	/*
  	 * 审批口令信息
  	 */
	public static int auditKL(HttpServletRequest request){
		Enumeration<String> names=request.getParameterNames();
		 InfoDao id=new InfoDao(); 
		  
		while(names.hasMoreElements()){
			String name=names.nextElement();
			if(name.startsWith("isaudit_")){
				int auditstatus=Integer.valueOf(request.getParameter(name));
				if(auditstatus==0) {
					continue;	
				}
				String infoidString=name.replaceAll("isaudit_", "");
				String reason=request.getParameter("auditreason_"+infoidString);
				
				InfoCreateType kltype=null;
				if(request.getParameter("kltype_"+infoidString)==null){
					return -1;
				}else if("0".equals(request.getParameter("kltype_"+infoidString))){
					kltype=InfoCreateType.client;
				}else if("1".equals(request.getParameter("kltype_"+infoidString))){
					kltype=InfoCreateType.shop;
				}else{
					return -1;
				}
				 
				
				int infoid=Integer.valueOf(infoidString);
				//审批
				if(id.auditKL(kltype,infoid, auditstatus,reason==null?"无意见":reason)<0){
					return -1;
				}
			} 
			
			
		}
		return 1;	 
   }
  
  
	
 	
	
  
  
}
