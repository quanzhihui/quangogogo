package severlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ShareConst;



/*
 * 图片处理器
 */
public class ImgSeverlet extends HttpServlet {

	
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

		String[] url = request.getRequestURI().split("\\/");
		InputStream in =null;
		try{
			File file = new File(ShareConst.imgPath+File.separator+url[url.length-1]);
			in = new FileInputStream(file);
			response.setHeader("Content-Type","image/jped");
			 int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				response.getOutputStream().write(tempbyte);
            }
			
			response.flushBuffer();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in!=null){
				in.close();
			}
			
		}
		
	
	
	
	}
		


	@Override
	public void destroy() {
	 
		
	}

}
