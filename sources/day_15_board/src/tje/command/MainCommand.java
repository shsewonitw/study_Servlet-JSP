package tje.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.*;
import tje.jdbc.util.*;
import tje.service.*;

public class MainCommand extends Command {
	private String formPage = "/WEB-INF/forms/main.jsp";
	private String submitPage = null;
	private String errorPage = null;
	
	private ArticleCountService acService = new ArticleCountService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if( session == null || session.getAttribute("login_member") == null )
			return formPage;
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			request.setAttribute("articleCount", 
					acService.service(values).get("articleCount"));			
		}catch (Exception e) {
		}
		
		return formPage;
	}
	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		return submitPage;
	}
	
}














