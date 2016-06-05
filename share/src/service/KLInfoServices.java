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
   public static String useTicket(int infoid,String clientwx){
	   
	   if(ClientServer.useTicket(clientwx, -1)>0){
		   LogServer.writeViewLog(clientwx,infoid,new Date());
		   return  getInfoKL(infoid);
		}else{
		   return "null";
		}
   }
   
   
	/*
	 * 根据id获取口令
	 */
   public static String getInfoKL(Integer infoid){
	   
	   InfoDao id=new InfoDao();
	   return  id.getInfoById(infoid).getKouling();
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
  	public static int postKL(Information info){
	  InfoDao id=new InfoDao();
	  return id.postKL(info);

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
				int infoid=Integer.valueOf(infoidString);
				//审批
				if(id.auditKL(infoid, auditstatus,reason==null?"无意见":reason)<0){
					return -1;
				}
			}
			
			
		}
		return 1;	 
   }
  
  
	
 	
	
  
  
}
