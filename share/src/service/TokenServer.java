package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import util.JonsonUtil;
import bean.Wxbean_AccessTokenBean;
import bean.Wxbean_jsapi;


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
	private static String jsapiTicket=null;
	static URL url =null;
	static URL jsapiurl =null;
	
	//
	private static String noncestr="youhuisudiniubiniubi";
	
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
			
			//刷新签名
			jsapiurl= new URL("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+TokenServer.getAccess_token()+"&type=jsapi");
		    urlConnection = (HttpURLConnection)jsapiurl.openConnection();  
			urlConnection.setRequestMethod("GET");  
			urlConnection.connect();
		    inputStream = urlConnection.getInputStream(); 
		    responseStr = convertToString(inputStream);  
			
			 bean=JonsonUtil.jiexi(responseStr, Wxbean_jsapi.class);
			if(bean!=null){
				Wxbean_jsapi jsapi=(Wxbean_jsapi)bean;
				System.out.println(jsapi.getTicket());
				jsapiTicket=jsapi.getTicket();
			}
			
//			Date d=new Date();
//			long timestatmp=d.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}
	
	/*
	 * 获取签名，用于wx.config
	 */
	public static String getSignName(String url) {

		StringBuilder bd = new StringBuilder();
		bd.append("jsapi_ticket=").append(jsapiTicket).append("&noncestr=")
				.append(noncestr).append("&timestamp=")
				.append(Calendar.getInstance().getTimeInMillis())
				.append("&url=").append(url);
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA1");
			byte[] result = mDigest.digest(bd.toString().getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
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
