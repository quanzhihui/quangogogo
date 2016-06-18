package severlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Wxbean_clientwx;

import service.ClientServer;
import service.InterfaceServer;
import service.PostServer;
import service.TokenServer;
import util.JonsonUtil;
import util.ShareConst;




public class IndexSeverlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	@Override
	public void init() {

		ShareConst.path = getServletContext().getRealPath("/");
		ShareConst.projectname=getServletContext().getContextPath();
		ShareConst.imgPath = ShareConst.path+"img";
		
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	//如果直接访问页面，则需要跳转到微信验证页面，从而获取openid
	
	private boolean hasClientWx(HttpServletRequest request){
		if(request.getSession().getAttribute("clientwx")!=null){
			return true;
		}else if(request.getParameter("code")!=null){
			String code=request.getParameter("code");
			if(code==null) return false;
			StringBuilder getOpenidUrl=new StringBuilder() ;
			getOpenidUrl
			.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
			.append(TokenServer.getappId())
			.append("&")
			.append("secret=")
			.append(TokenServer.getAccess_token())
			.append("&")
			.append("code=")
			.append(code)
			.append("&")
			.append("grant_type=authorization_code");
			BufferedReader in=null;
			try{
				 URL realUrl = new URL(getOpenidUrl.toString());
				 URLConnection connection = realUrl.openConnection();
				 connection.connect();
				  in = new BufferedReader(new InputStreamReader(
		                    connection.getInputStream()));
				 
				 StringBuilder json=new StringBuilder();
				 String line=null;
				 while ((line = in.readLine()) != null) {
					 json.append(line);
		            }
				 Object temp=JonsonUtil.jiexi(json.toString(), Wxbean_clientwx.class);
				
				 if(temp!=null){
					 Wxbean_clientwx wxbean=(Wxbean_clientwx)temp;
					 request.getSession().setAttribute("clientwx", wxbean.getOpenid());
					 return true;
				 }else{
					 return false;
				 }
				 
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}finally{
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		}else{
			//说明是从某一个页面来，需要跳转到微信授权页面
			return false;
		}
			 
	}
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!hasClientWx(request)){
			RequestDispatcher dispatcher = request.getRequestDispatcher(ShareConst.wxurl);
			dispatcher.forward(request, response);	
		}
		Object openid=request.getSession().getAttribute("clientwx");
		String clientwx=openid.toString();
//		String clientwx="2323";
//		request.getSession().setAttribute("clientwx",clientwx);
		System.out.println(clientwx);
		//设置用户属性
		
		
		//如果是第一次来则记录用户
		ClientServer.clientRegist(clientwx);
		
 		String uri=request.getRequestURI();
 		if(uri.contains("/url/")){
 			 if(uri.contains("/url/shop") ){
 				 //登录、注册页面不用验证权限，其他页面都需要
 				 if(uri.contains("/shop/shopdl") ){
 					if(request.getSession().getAttribute("shopusername")==null){
 						RequestDispatcher dispatcher = request.getRequestDispatcher("/shopdl.jsp");
 	 	 				dispatcher.forward(request, response);	
 	 	 				return;
 					}else{
 						RequestDispatcher dispatcher = request.getRequestDispatcher("/shopmain.jsp");
 	 	 				dispatcher.forward(request, response);	
 					}
 	 			}else if(uri.contains("/shop/shopzc")){
 	 				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopzc.jsp");
 	 				dispatcher.forward(request, response);
 	 				return;
	 			}
 				 
 				 
 				 //验证权限
 				if(request.getSession().getAttribute("shopusername")==null||"".equals(request.getSession().getAttribute("shopname"))){
 					RequestDispatcher dispatcher = request.getRequestDispatcher("/shopdl.jsp");
	 	 			dispatcher.forward(request, response);		
	 	 			return;
 				}
 				
 				if(uri.contains("/shop/shopmain") ){
 	 				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopmain.jsp");
 	 	 				dispatcher.forward(request, response);	
 	 			}else if(uri.contains("/url/shop/tab/shopmain/") ){
 					if(uri.contains("lskl")){
 						RequestDispatcher dispatcher = request.getRequestDispatcher("/shoplskl.jsp");
	 	 				dispatcher.forward(request, response);		
 					}else if(uri.contains("fbkl")){
 						RequestDispatcher dispatcher = request.getRequestDispatcher("/shopfbkl.jsp");
	 	 				dispatcher.forward(request, response);		
 					}else if(uri.contains("zlwh")){
 						RequestDispatcher dispatcher = request.getRequestDispatcher("/shopzlwh.jsp");
	 	 				dispatcher.forward(request, response);		
 					}
 					
 				}
 				
  				
  			}

 			
 			else if(uri.contains("/url/main") ){
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
 				dispatcher.forward(request, response);	
 			}else if(uri.contains("/url/index2") ){
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/example/index.html");
 				dispatcher.forward(request, response);	
 			}else if(uri.contains("/url/fakouling") ){
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/fakouling.jsp");
 				dispatcher.forward(request, response);	
 			}else if(uri.contains("/url/mine/main") ){
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/mine.jsp");
 				dispatcher.forward(request, response);	
 			}else if(uri.contains("/tab/mineframe/myjifen") ){
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/myjifen.jsp");
 				dispatcher.forward(request, response);	
 			}else if(uri.contains("/tab/mineframe/mykgdkl") ){
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/mykgdkl.jsp");
 				dispatcher.forward(request, response);	
 			}else if(uri.contains("/tab/mineframe/fdkl") ){
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/myfdkl.jsp");
 				dispatcher.forward(request, response);	
 			}
 			
 			
 			
 			
 			
 		}else if(uri.contains("interface") ){
			InterfaceServer.getData( clientwx,request, response);
		}else if(uri.contains("/tab/framemain") ){
			 if(uri.contains("ontime")){
				request.setAttribute("type", "ontime");
			}else if(uri.contains("history")){
				request.setAttribute("type", "history");
			}else{
				//默认是热度 
				request.setAttribute("type", "redu");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/framemain.jsp");
			dispatcher.forward(request, response);	
			
		}else if(uri.contains("post") ){
			if(uri.contains("post/client_fakouling")){
				//1代表成功，2代表口令已存在，0代表插库失败
				
				response.getWriter().write(String.valueOf(PostServer.postClientKL(request)));
				
			}else if(uri.contains("post/shop_fakouling")){
				response.getWriter().write(String.valueOf(PostServer.postShopKL(request)));
			}else if(uri.contains("post/shop_zhuce")){
				response.getWriter().write(String.valueOf(PostServer.postShopZC(request)));
			}else if(uri.contains("post/client_addname")){
				response.getWriter().write(String.valueOf(ClientServer.addClientName(clientwx, request.getParameter("yhnccontent"))));
			}
			
			
			
			
		}
		
	}
		


	@Override
	public void destroy() {
	 
		
	}

}
