package util;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadUtil {

	
	static ServletFileUpload upload=null;
	public static void init(){
		
		
		
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		 factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
	    File tempPathFile = new File(ShareConst.path+"/tmpimg/");
		 if (!tempPathFile.exists()) {
			   tempPathFile.mkdirs();
			  }
		 factory.setRepository(tempPathFile);// 设置缓冲区目录
		 upload = new ServletFileUpload(factory);
		 //最大不能超过512KB
		 upload.setSizeMax(512000);  
	}
	 
	public static ServletFileUpload  getServletFileUpload(){
	if(upload==null){
		init();
	}return upload;
		
	}
		
	
		
		
	}
	
	
	
	

