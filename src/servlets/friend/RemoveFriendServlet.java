package servlets.friend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.LogService;
import services.ServicesTools;
import services.friend.RemoveFriendService;

@SuppressWarnings("serial")
public class RemoveFriendServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
			if (!(req.getParameterMap().containsKey("key")
					|| req.getParameterMap().containsKey("idTo"))){
				resp.getWriter().println(ServicesTools.error101());
			}else{
				resp.setContentType("text/plain");
				String key = req.getParameter("key");
				String idTo = req.getParameter("idTo");
				resp.getWriter().println(RemoveFriendService.removeFriendService(key, idTo));
				
//				String adrIP = req.getRemoteAddr();
//				String nomClient = req.getRemoteHost();
//				String service = req.getHeader("RemoveFriendsService");			
//				resp.getWriter().println(LogService.logService(service,adrIP,nomClient, key));
			}
			
			
			
			
	}
	
}
