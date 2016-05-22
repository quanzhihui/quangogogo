package service;

import java.util.ArrayList;
import java.util.Date;
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
		return idao.getTopInfoByMysql(it,TextUtil.getCurrentDay()) ;
//	   	//调试信息
//			List<Imformation> list=new ArrayList<Imformation>();
//			Imformation info=new Imformation();
//			info.setImfoId(123123);
//			info.setAllowVisit(100);
//			info.setClientImg("http://www.5068.com/u/faceimg/20140815125220.jpg");
//			info.setClientName("用户甲"+it.toString());
//			info.setKouling(it.toString());
//			info.setIntroduct_acount(1000);
//			info.setIntroduct_num(100);
//			info.setOutputsdate(TextUtil.getOutputDay("20160215"));
//			info.setStime(10);
//			list.add(info);
//			Imformation info2=new Imformation();
//			info2.setImfoId(2323);
//			info2.setAllowVisit(100);
//			info2.setClientImg("http://www.5068.com/u/faceimg/20140815125220.jpg");
//			info2.setClientName("用户乙"+it.toString());
//			info2.setKouling(it.toString());
//			info2.setIntroduct_acount(1000);
//			info2.setIntroduct_num(100);
//			info2.setOutputsdate(TextUtil.getOutputDay("20160215"));
//			info.setStime(10);
//			list.add(info2);
//			return list;
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
  	public static int postKL(Imformation info){
	  InfoDao id=new InfoDao();
	  return id.postKL(info);

  	}
  
  	/*
  	 * 查看口令是否已经发过了
  	 */
	public static boolean checkKlExist(String kl,Long time){
		 InfoDao id=new InfoDao();
		 return id.checkKlExist(kl, time); 
   }
  
  
  
  
  
}
