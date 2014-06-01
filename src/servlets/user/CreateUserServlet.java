package servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.LogService;
import services.ServicesTools;
import services.user.CreateUserService;

@SuppressWarnings("serial")
public class CreateUserServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
			if (!(req.getParameterMap().containsKey("login")
					|| req.getParameterMap().containsKey("lastname")
					|| req.getParameterMap().containsKey("firstname")
					|| req.getParameterMap().containsKey("password"))
					){
				resp.getWriter().println(ServicesTools.error101());
			}else{
				resp.setContentType("text/plain");
				String lastname = req.getParameter("lastname");
				String firstname = req.getParameter("firstname");
				String login = req.getParameter("login");
				String password = req.getParameter("password");
				resp.getWriter().println(CreateUserService.createUser(lastname, firstname, login, password));
				
//				String adrIP = req.getRemoteAddr();
//				String nomClient = req.getRemoteHost();
//				String service = req.getHeader("CreateUserService");			
//				resp.getWriter().println(LogService.logService(service,adrIP,nomClient, login));
			}
			
			
	}
	
}
