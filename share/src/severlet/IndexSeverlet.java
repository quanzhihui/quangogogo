package severlet;

import java.io.IOException;

import javassist.compiler.ast.CondExpr;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ClientServer;
import service.CodeServer;
import service.InterfaceServer;
import service.PostServer;
import service.ShopServer;
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

	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object openid=request.getAttribute("openid");
		if(openid==null){
			openid="111";
		}
		String clientwx=openid.toString();
		//设置用户属性
		request.getSession().setAttribute("clientwx", openid);
		
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
				int a=PostServer.postShopZC(request);
				System.out.println(a);
				response.getWriter().write(String.valueOf(a));
			}
			
			
			
			
		}
		
	}
		


	@Override
	public void destroy() {
	 
		
	}

}
