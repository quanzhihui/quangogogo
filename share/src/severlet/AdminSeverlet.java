package severlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AdminServer;
import service.InterfaceServer;
import service.ShopServer;




public class AdminSeverlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	@Override
	public void init() {

	
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
     
	}

	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String uri=request.getRequestURI();

			 if(uri.contains("admin") ){
				 //登录、注册页面不用验证权限，其他页面都需要
				 if(uri.contains("/admin/login") ){
	 				RequestDispatcher dispatcher = request.getRequestDispatcher("/admindl.jsp");
	 				dispatcher.forward(request, response);	
	 				return;
				 }else  if(uri.contains("/admin/interface/adminlogin") ){
		 				
		 				String adminname=request.getParameter("shopname");
						String adminpassword=request.getParameter("shoppassword");
					
						int status=AdminServer.shopLoging(adminname, adminpassword);
						//登录成功，设置session值
						if(status==1){
							request.getSession().setAttribute("adminname",adminname);
						}
						response.getWriter().write(String.valueOf(status));
						return ;
		 			}
				 
				 //验证权限
				if(request.getSession().getAttribute("adminname")==null||"".equals(request.getSession().getAttribute("adminname"))){
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admindl.jsp");
	 	 			dispatcher.forward(request, response);		
	 	 			return;
				}
				
				if(uri.contains("/admin/adminmain") ){
	 				RequestDispatcher dispatcher = request.getRequestDispatcher("/adminmain.jsp");
	 	 			dispatcher.forward(request, response);	
	 	 			return ;
	 			}else if(uri.contains("/admin/interface") ){
	 				InterfaceServer.getData( null,request, response);
	 			}
				
				
 			}

			 RequestDispatcher dispatcher = request.getRequestDispatcher("/admindl.jsp");
			 dispatcher.forward(request, response);	
			 return;
	}
		


	@Override
	public void destroy() {
	 
		
	}

}
