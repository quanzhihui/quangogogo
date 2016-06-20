package severlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class WxSeverlet extends HttpServlet {

	
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
		
		if(uri.contains("/wx/wxtokentest")){
			String echostr =request.getParameter("echostr");
			if(echostr!=null){
				response.getWriter().write(echostr);
				System.out.println("it is weixin");
			}else{
				System.out.println("it is not weixin");
			}
		}else if(uri.contains("/wx/autoanswer")){
			
			response.getWriter().write("success");
		}
		
	
	
	}
		


	@Override
	public void destroy() {
	 
		
	}

}
