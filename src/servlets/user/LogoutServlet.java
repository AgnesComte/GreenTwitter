package servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.LogService;
import services.ServicesTools;
import services.user.LogoutService;

@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
			if (!(req.getParameterMap().containsKey("key"))){
				resp.getWriter().println(ServicesTools.error101());
			}else{
				resp.setContentType("text/plain");
				String key = req.getParameter("key");
				resp.getWriter().println(LogoutService.logout(key));
				
//				String adrIP = req.getRemoteAddr();
//				String nomClient = req.getRemoteHost();
//				String service = req.getHeader("LogoutService");			
//				resp.getWriter().println(LogService.logService(service,adrIP,nomClient, key));
			}
			
	
	}
	
}
