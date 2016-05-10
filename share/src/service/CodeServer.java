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
	 * 获取10位邀请码,或6位消息码
	 */
	public static String getCode(String type){	 
	        StringBuffer sb = new StringBuffer();  
	        int length=6;
	        if("yqm".equals(type)){
	        	length=10;
	        }//其余情况都算发消息码
	        
	        for(int i = 0 ; i < length; ++i){  
	            int number = random.nextInt(str.length());//[0,str.length())  
	            sb.append(str.charAt(number));  
	        }  
	        //生成邀请码创建时间
	        Date date=new Date(new java.util.Date().getTime());
	        String code=sb.toString();
	        CodeDao dao=new CodeDao();
	        if(dao.createCode(type,code,date)){
	        	return code;  
	        }else{
	        	return null ;
	        }
	}
	
	public static void useCode(String type,String shopid,String yqm ){	
		//生成邀请码使用时间
        Date useDatetime=new Date(new java.util.Date().getTime());
        CodeDao dao=new CodeDao();
        dao.useCode(type,shopid, useDatetime, yqm);
	}

	
	public static void main(String[] args){
		System.out.println(new Date(new java.util.Date().getTime()));
	}
	
}
