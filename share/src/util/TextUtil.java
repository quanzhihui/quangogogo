package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	    * 格式化日期
	    */
	   public static String getOutputDay(String time){
		   
		  String year=time.substring(0,4);
		  String month=time.substring(4,6);
		  String day=time.substring(6,8);
		  
		  return  year+"年"+month+"月"+day+"日";
	   }
}
