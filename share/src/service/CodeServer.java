package service;

import java.sql.Date;
import java.util.Random;

import dao.CodeDao;


/*
 * 二维码生成接口
 */
public class CodeServer {
	 static Random random = new Random(); 
	 static String str="abcdefghijklmnopqrstuvwxyz0123456789";  
     
	/*
	 * 获取10位邀请码,或6位消息码,或8位推广码（用户推荐让别人登陆）
	 */
	public static String getCode(String type,int length){	 
	        StringBuffer sb = new StringBuffer();  
	        
	        for(int i = 0 ; i < length; ++i){  
	            int number = random.nextInt(str.length());//[0,str.length())  
	            sb.append(str.charAt(number));  
	        }  
	        //生成邀请码创建时间
	        Date date=new Date(new java.util.Date().getTime());
	        String code=sb.toString();
	        CodeDao dao=new CodeDao();
	        //没有type表示不需要存储
	        if(type!=null){
	        	if(dao.createCode(type,code,date)){
		        	return code;  
		        }else{
		        	return null ;
		        }
	        }else{
	        	return code;
	        }
	        
	        
	}
	

	
	 /*
	  * 生成图片随机码
	  */
	public static String getImageCode(){
		return new java.util.Date().getTime()+getCode(null,4);
		
	}
	
	
	
	
	/*
	 * 验证是否是靠谱的邀请码
	 */
	public static boolean isRightYzm(String yqm){
		CodeDao cd=new CodeDao();	
		return cd.checkCode("yqm",yqm);
	}
	
	/*
	 * 使用邀请码
	 */
	public static void useYQMCode(String shopid,String yqm ){	
		//生成邀请码使用时间
        Date useDatetime=new Date(new java.util.Date().getTime());
        CodeDao dao=new CodeDao();
        dao.useCode("yqm",shopid, useDatetime, yqm);
        
	}

	/*
	 * 验证是否是靠谱的推广豆
	 */
	public static boolean isRightYhd(String tgd){
		CodeDao cd=new CodeDao();	
		return cd.checkCode("tgd",tgd);
	}
	
	/*
	 * 使用推广豆
	 */
	public static void useTGDCode(String shopid,String yqm ){	
		//生成邀请码使用时间
        Date useDatetime=new Date(new java.util.Date().getTime());
        CodeDao dao=new CodeDao();
        dao.useCode("tgd",shopid, useDatetime, yqm);
	}
	
	
	
	
	
	
	
}
