package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import bean.Wxbean_AccessTokenBean;

import util.JonsonUtil;
import util.ShareConst;


//access_token中控服务
public class TokenServer {

	
	public static void init(){
		
		try {
			url= new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+TokenServer.getappId()+"&secret="+TokenServer.getSecret());
			
			new RefreshThread().start();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}  
		
	}
	
	static class RefreshThread extends Thread {

		@Override
		public void run() {
			
			try {
				TokenServer.refreshAccess_token();
				//每6000秒刷新一次，规定的时间是2个小时内有效
				Thread.sleep(6000000);
			} catch (InterruptedException e) {
				 
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	private static String access_token=null;
	static URL url =null;
	
	//刷新中控服务
	public static void refreshAccess_token(){
		try {
			
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();  
			urlConnection.setRequestMethod("GET");  
			urlConnection.connect();
			InputStream inputStream = urlConnection.getInputStream(); 
			String responseStr = convertToString(inputStream);  
			
			Object bean=JonsonUtil.jiexi(responseStr, Wxbean_AccessTokenBean.class);
			if(bean!=null){
				Wxbean_AccessTokenBean atb=(Wxbean_AccessTokenBean)bean;
				System.out.println(atb.getAccess_token());
				access_token=atb.getAccess_token();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}
	
	
	public static String getAccess_token(){
		return access_token;
	}
	
	//转化字符串
	 public static String convertToString(InputStream inputStream){  
	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);  
	        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
	        StringBuilder result = new StringBuilder();  
	        String line = null;  
	        try {  
	            while((line = bufferedReader.readLine()) != null){  
	                result.append(line + "\n");  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            try{  
	                inputStreamReader.close();  
	                inputStream.close();  
	                bufferedReader.close();  
	            }catch(IOException e){  
	                e.printStackTrace();  
	            }  
	        }  
	        return result.toString();  
	    }  
	
	 
	 public static Long getTimeStame(){
		 
		 Calendar ca=Calendar.getInstance();
		 return ca.getTime().getTime();
		 
	 }
	 
	 
	 

	 public static String getEncodingAESKey(){
		 return "tvYlQWyPcZp63pRkjBogUfR3QaC19fIp4eZXtAc0l77";
	 }
	 
	 
	 public static String getappId(){
		 return "wxb99d4d4ba2f634c9";
	 }
	 
	 public static String getSecret(){
		 return "ac83a7b9df779952be32c5d9a2519443";
	 }
	 
	 
	 
	 
//	public static void main(String[] args){
//		
//		init();
//	}
	
}
