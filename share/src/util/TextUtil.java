package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TextUtil {

	   /*
	    * 获取当前日期
	    */
	   static  SimpleDateFormat dayformat = new  SimpleDateFormat("yyyyMMdd");
	  
	   
	   public static int getCurrentDay(){
		   Calendar cal = Calendar.getInstance();// 取当前日期。
		   return Integer.valueOf(dayformat.format(cal.getTime()));
	   }
	   /*
	    * 格式化日期（20160505）
	    */
	   public static String getOutputDay(String time){
		   
		  String year=time.substring(0,4);
		  String month=time.substring(4,6);
		  String day=time.substring(6,8);
		  
		  return  year+"年"+month+"月"+day+"日";
	   }
	   
	  
	   
	   static  SimpleDateFormat dayformat_timestamp = new  SimpleDateFormat("yyyy年MM月dd日 HH:mm"); 
	   /*
	    * 格式化日期（时间戳）
	    */
	   public static String getOutputDayTimeStamp(long time){
		   
			Calendar ca=Calendar.getInstance();
			ca.setTimeInMillis(time);
			Date date=ca.getTime();
			
			  return  dayformat_timestamp.format(date);
		   }
	   
	   
	   static  SimpleDateFormat dayformat_timestamp2 = new  SimpleDateFormat("yyyy-MM-dd HH:mm"); 
	   /*
	    * 转化为日期（时间戳）
	    */
	   public static Date getTimeByString(String date){
		  try {
			return dayformat_timestamp2.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		   }
	   
	   
	   static  SimpleDateFormat dayformat_timestamp3 = new  SimpleDateFormat("yyyyMMdd"); 
	   /*
	    * 转化为普通日期
	    */
	   public static Integer getTimeByString(Date date){
		  try {		 
				return Integer.valueOf(dayformat_timestamp3.format(date));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		   }
	   
	   
}
