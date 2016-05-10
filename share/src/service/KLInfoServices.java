package service;

import java.util.ArrayList;
import java.util.List;
import util.TextUtil;
import bean.Imformation;
import dao.ClientType;
import dao.InfoDao;
import dao.InfoType;

public class KLInfoServices {
	
/*
 * 获取口令信息
 */
   public static List<Imformation> getInfo(InfoType it ){
	   	InfoDao idao =new InfoDao();
		
	   	//调试信息
			List<Imformation> list=new ArrayList<Imformation>();
			Imformation info=new Imformation();
			info.setImfoId(123123);
			info.setAllowVisit(100);
			info.setClientImg("http://www.5068.com/u/faceimg/20140815125220.jpg");
			info.setClientName("用户甲"+it.toString());
			info.setKouling(it.toString());
			info.setIntroduct_acount(1000);
			info.setIntroduct_num(100);
			info.setOutputsdate(TextUtil.getOutputDay("20160215"));
			info.setShour(10);
			info.setSminute(00);
			list.add(info);
			Imformation info2=new Imformation();
			info2.setImfoId(2323);
			info2.setAllowVisit(100);
			info2.setClientImg("http://www.5068.com/u/faceimg/20140815125220.jpg");
			info2.setClientName("用户乙"+it.toString());
			info2.setKouling(it.toString());
			info2.setIntroduct_acount(1000);
			info2.setIntroduct_num(100);
			info2.setOutputsdate(TextUtil.getOutputDay("20160215"));
			info2.setShour(10);
			info2.setSminute(00);
			list.add(info2);
			return list;
			
		
//			return idao.getTopInfoByMysql(it,TextUtil.getCurrentDay()) ;
		
	   	
	}
   
   /*
    * 用门票看口令,返回口令
    */
   public static String useTicket(String infoid,String clientwx){
	   
	   if(ClientServer.getClientInfo(ClientType.ticket, clientwx)>0){
		   ClientServer.useTicket(clientwx, -1);
		   return  getInfoKL(infoid);
		}else{
		   return "null";
		}
	   
   }
   
   
	/*
	 * 根据id获取口令
	 */
   public static String getInfoKL(String infoid){
	   
	   return "红包口令："+"优惠速递乐享生活";

   }
   
   /*
	 * 根据id获取口令
	 */
  public static Integer getInfoType(String infoid){
	   
	   return 0;

  }
   
  	/*
	 * 根据推广页面
	 */
  public static String getInfoRedirectUrl(String infoid){
	   
	   return "http://www.baidu.com";

}
   
//   /*
//    * 获取我读取过的口令信息
//    */ 
//   public static List<Imformation> getInfo(InfoType it,int date){
//	   	InfoDao idao =new InfoDao();
//		return idao.getTopInfoByMysql(it,getCurrentDay());
//	}
   
    /*
     * 新增口令信息
     */
   public static boolean postKL(Imformation info){
	   
	   InfoDao dao =new InfoDao();
//	  return  dao.postKL(info);
	   return true;
	     
   }
   
}
