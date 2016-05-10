package severlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.InterfaceServer;




public class IndexSeverlet extends HttpServlet {

	
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
		//设置用户属性
		request.getSession().setAttribute("clientwx", "100");
		String code=(String)request.getAttribute("code");
 		String[] arg=request.getRequestURI().split("/");

		if(arg[arg.length-1].contains("ggg") ){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
			dispatcher.forward(request, response);	
		}else if(arg[arg.length-1].contains("index2") ){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/example/index.html");
			dispatcher.forward(request, response);	
		}else if(request.getRequestURI().contains("interface") ){
			InterfaceServer.getData( request, response);
		}else if(request.getRequestURI().contains("framemain") ){
			 if(request.getRequestURI().contains("ontime")){
				request.setAttribute("type", "ontime");
			}else if(request.getRequestURI().contains("history")){
				request.setAttribute("type", "history");
			}else{
				//默认是热度 
				request.setAttribute("type", "redu");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/framemain.jsp");
			dispatcher.forward(request, response);	
		}else if(request.getRequestURI().contains("fakouling.jsp") ){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/fakouling.jsp");
			dispatcher.forward(request, response);	
		}
		
	}
		


	@Override
	public void destroy() {
	 
		
	}

}
