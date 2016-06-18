package util;

import bean.Wxbean_AccessTokenBean;
import net.sf.json.JSONObject;

public class JonsonUtil {

	//解析json串
	public static Object jiexi(String json,Class<?> beanClass){
		if(json==null||"".equals(json)||json.contains("errcode")) return null;
		 JSONObject jsonObject = JSONObject.fromObject( json );
		 return JSONObject.toBean(jsonObject,beanClass );
	}
	
public static void main(String[] args){
		
	String s="{access_token:'sdfs';expires_in:'dsds'}";
	System.out.println(jiexi(s,Wxbean_AccessTokenBean.class));
	
	}
}
