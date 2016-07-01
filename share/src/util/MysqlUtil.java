package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class MysqlUtil {
	 private Vector<Connection> pool;
	    private static String  url;
	    private static String username;
	    private static String password;
	    private static String driverClassName;
	    private static int poolSize ;
	    private static MysqlUtil instance = null;
	    
	    //私有构造方法，禁止外部创建本类的对象，要想获得本类的对象，通过<code>getInstance</code>方法
	    private MysqlUtil(){
	       
	        init();
	    }
	    
	    //连接池初始化方法，读取属性文件的内容，建立连接池中的初始连接
	    private void init(){
	        readConfig();
	        pool = new Vector<Connection>(poolSize);
	        addConnection();
	    }
	    
	    //返回连接到连接池中
	    public synchronized void release(Connection conn){
	        pool.add(conn);
	    }
	    
	    //关闭连接池中的所有数据库连接
	    public synchronized void closePool(){
	        for (int i = 0; i < pool.size(); i++) {
	            try {
	                ((Connection)pool.get(i)).close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            //在循环的时候不推荐remove
//	            pool.remove(i);
	        }
	    }
	    
	    //返回当前连接池的一个对象
	    public static MysqlUtil getInstance(){
	        if (instance == null) {
	            instance = new MysqlUtil();
	        }
	        return instance;
	    }
	    
	  //返回连接池中的一个数据库连接
	    public synchronized Connection getConnection(){
	    	 Connection conn=null;
	        if (pool.size() > 0) {
	            conn = pool.get(0);
	            pool.remove(conn);
	          conn=checkConnection(conn);
	        
	            return conn;
	        }
	        //说明使用量太大，需要等待
	        return null;
	        
	    }
	    
	    //在连接池中创建初始设置的数据库连接
	    private Connection renewConnection(){
	        Connection conn = null;
	        
	            try {
	                Class.forName(driverClassName);
	                conn = java.sql.DriverManager.getConnection(url, username, password);
	              
	               
	                
	            } catch (ClassNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	         return conn;
	    }
	    
	    //测试是否能连接，否则重连
	 
	    private Connection checkConnection(Connection conn){
	    	
	    	try {
	    		if(conn==null||!conn.isValid(1000)){
	        		return renewConnection();
	        	}else{
	        		PreparedStatement  pre=conn.prepareStatement("SELECT 1 from DUAL;");
				    pre.executeQuery();
	        	}
			} catch (Exception e) {
				//说明连接失效了
				return renewConnection();
			}
	    	return conn;
					
	    }
	    
	    //在连接池中创建初始设置的数据库连接
	    private void addConnection(){
	        Connection coon = null;
	        for (int i = 0; i < poolSize; i++) {
	            try {
	                Class.forName(driverClassName);
	                coon = java.sql.DriverManager.getConnection(url, username, password);
	                pool.add(coon);
	            } catch (ClassNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	    }
	    
		    //读取设置连接池的属性文件
		    private void readConfig(){

		    	    this.driverClassName ="com.mysql.jdbc.Driver";
		            this.username ="focuspv";
		            this.password = "focuspv";
		            this.url = "jdbc:mysql://10.10.90.174:3306/share?autoReconnect=true";
		            this.poolSize =10;
		    	
//	    	    this.driverClassName ="com.mysql.jdbc.Driver";
//	            this.username ="root";
//	            this.password = "Qzh-1986!";
//	            this.url = "jdbc:mysql://localhost:3306/share?autoReconnect=true";
//	            this.poolSize =10;
		    	
		    }
	
	
}
