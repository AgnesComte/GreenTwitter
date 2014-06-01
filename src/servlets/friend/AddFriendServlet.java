package servlets.friend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.LogService;
import services.ServicesTools;
import services.friend.AddFriendService;

@SuppressWarnings("serial")
public class AddFriendServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
			if (!(req.getParameterMap().containsKey("key")
					|| req.getParameterMap().containsKey("idTo"))){
				resp.getWriter().println(ServicesTools.error101());
			}else{
				resp.setContentType("text/plain");
				String key = req.getParameter("key");
				String idTo = req.getParameter("idTo");
				resp.getWriter().println(AddFriendService.addFriendService(key, idTo));	
//				
//				String adrIP = req.getRemoteAddr();
//				String nomClient = req.getRemoteHost();
//				String service = req.getHeader("AddFriendService");			
//				resp.getWriter().println(LogService.logService(service,adrIP,nomClient, key));
			}
			
			
	}
}
