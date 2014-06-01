package servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.LogService;
import services.ServicesTools;
import services.user.LoginService;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
			if (!(req.getParameterMap().containsKey("login")
					|| req.getParameterMap().containsKey("password"))){
				resp.getWriter().println(ServicesTools.error101());
			}else{
				resp.setContentType("text/plain");
				String login = req.getParameter("login");
				String password = req.getParameter("password");
				resp.getWriter().println(LoginService.loginService(login, password));

			}
	
//			String adrIP = req.getRemoteAddr();
//			String nomClient = req.getRemoteHost();
//			String service = req.getHeader("LoginService");			
//			resp.getWriter().println(LogService.logService(service,adrIP,nomClient, login));
			
	}
}
