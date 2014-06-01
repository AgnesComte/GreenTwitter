package servlets.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.LogService;
import services.ServicesTools;
import services.comment.SearchCommentsService;

@SuppressWarnings("serial")
public class SearchCommentsServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
			if (!(req.getParameterMap().containsKey("key")
					|| req.getParameterMap().containsKey("query")
					|| req.getParameterMap().containsKey("friends"))){
				resp.getWriter().println(ServicesTools.error101());
			}else{
				resp.setContentType("text/plain");
				String key = req.getParameter("key");
				String query = req.getParameter("query");
				String friends = req.getParameter("friends");
				resp.getWriter().println(SearchCommentsService.searchCommentsService(key,query,friends));
				
//				String adrIP = req.getRemoteAddr();
//				String nomClient = req.getRemoteHost();
//				String service = req.getHeader("SearchCommentService");			
//				resp.getWriter().println(LogService.logService(service,adrIP,nomClient, key));
			}
			
			
	}

}
