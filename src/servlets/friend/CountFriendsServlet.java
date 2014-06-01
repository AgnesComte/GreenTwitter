package servlets.friend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.LogService;
import services.ServicesTools;
import services.friend.CountFriendsService;

@SuppressWarnings("serial")
public class CountFriendsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
			if (!(req.getParameterMap().containsKey("key"))){
				resp.getWriter().println(ServicesTools.error101());
			}else{
				resp.setContentType("text/plain");
				String key = req.getParameter("key");
				resp.getWriter().println(CountFriendsService.countFriendsService(key));
				
//				String adrIP = req.getRemoteAddr();
//				String nomClient = req.getRemoteHost();
//				String service = req.getHeader("CountFriendsService");			
//				resp.getWriter().println(LogService.logService(service,adrIP,nomClient, key));
			}
			
			
	}

}
