package mainboard.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import org.apache.jasper.runtime.JspWriterImpl;
import org.apache.jasper.tagplugins.jstl.core.Out;

import member.command.CommandHandler;

public class BoardDownloadHandler implements CommandHandler {
	
	private static String FORM_VIEW = "index.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("utf-8");
		String fileName = req.getParameter("fn");
		
		String path = "C:\\Users\\Kwongaeun\\git\\MES_Project\\MES\\WebContent\\WEB-INF\\download\\" + fileName;
		
		byte[] b = new byte[4096];
		File f = new File(path);
	 	FileInputStream fis = new FileInputStream(path);
	 	
	 	String sMimeType = req.getSession().getServletContext().getMimeType(path);
	 	if(sMimeType==null) sMimeType = "application/octet-stream"; 

		String sEncoding = URLEncoder.encode(fileName, "utf-8"); 
		
	    res.setContentType(sMimeType);
	    res.setHeader("Content-Transfer-Encoding", "binary");
		res.setHeader("Content-Disposition", "attachment; filename = " + sEncoding);
		
		ServletOutputStream os = res.getOutputStream();
		
		int numRead;
		
		while((numRead = fis.read(b, 0, b.length)) != -1) {
			os.write(b, 0, numRead);
		}
		
		os.flush();
		os.close();
		fis.close();
		
		return FORM_VIEW;
		
	}

}
