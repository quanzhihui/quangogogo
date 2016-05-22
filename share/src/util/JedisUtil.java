//package util;
//
//import redis.clients.jedis.Jedis;
//
//public class JedisUtil {
//
//	
//	
//	static Jedis jedis=null;
//	
//	
//	public static Jedis getJedis(){
//		if(jedis==null){
//			jedis=new Jedis("139.196.18.79",6379);
//			jedis.auth("quanzhihui123");
//		}
//		return jedis;
//	}
//	
//	public static void main(String[] args){
//		getJedis().append("abc","ddd");
//	}
//	
//	
//	
//	
//}
