package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ServicesTools;
import core.database.DBStatic;



@SuppressWarnings("serial")
public class TestMySql extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		
		
		if (!(req.getParameterMap().containsKey("login")
				|| req.getParameterMap().containsKey("password"))){
			resp.getWriter().println(ServicesTools.error101());
		}else{
			resp.setContentType("text/plain");
			String login = req.getParameter("login");
//			String password = req.getParameter("password");
			
			resp.getWriter().println("Test login : " +login);
			try {
				resp.getWriter().println("Test login : " +login);
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection=DriverManager.getConnection("jdbc:mysql://"
						+ DBStatic.mysql_host + "/" + DBStatic.mysql_db,
						DBStatic.mysql_username, DBStatic.mysql_password);
					resp.getWriter().println("Test login : " +login);
					PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE LOGIN=?;");
					statement.setString(1, login);
					resp.getWriter().println("ivi");
					ResultSet rs = statement.executeQuery();
					resp.getWriter().println("ivi2");
					if (rs.next()) {
						resp.getWriter().println("cool");
					}
					resp.getWriter().println("iv3");
					statement.close();
					connection.close();
			
			} catch (Exception e) {
				resp.getWriter().println("Problem :"+e.getMessage());
				e.printStackTrace();
			}
			
				
		}
	}
}