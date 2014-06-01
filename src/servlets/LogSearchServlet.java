package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.LogService;
import services.ServicesTools;


@SuppressWarnings("serial")
public class LogSearchServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
			if (!(req.getParameterMap().containsKey("query")
					|| req.getParameterMap().containsKey("key"))){
				resp.getWriter().println(ServicesTools.error101());
			}else{
				resp.setContentType("text/plain");
				String query = req.getParameter("query");
				String key = req.getParameter("key");
				resp.getWriter().println(LogService.logSearchService(query, key));

			}	
			
		
	}
	
}
