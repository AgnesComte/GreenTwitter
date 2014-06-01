package servlets.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.LogService;
import services.ServicesTools;
import services.comment.AddCommentService;

@SuppressWarnings("serial")
public class AddCommentServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
			if (!(req.getParameterMap().containsKey("key")
					|| req.getParameterMap().containsKey("text"))){
				resp.getWriter().println(ServicesTools.error101());
			}else{
				resp.setContentType("text/plain");
				String key = req.getParameter("key");
				String text = req.getParameter("text");
				resp.getWriter().println(AddCommentService.addCommentService(key,text));
				
//				String adrIP = req.getRemoteAddr();
//				String nomClient = req.getRemoteHost();
//				String service = req.getHeader("AddCommentService");			
//				resp.getWriter().println(LogService.logService(service,adrIP,nomClient, key));
			}
			
	}
	
	
}
