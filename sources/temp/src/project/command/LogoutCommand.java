package project.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import java.util.*;
import java.sql.*;

import project.jdbc.util.ConnectionProvider;
import project.model.*;
import project.sevice.*;

public class LogoutCommand extends Command {
	
	private String formPage = "/WEB-INF/forms/logout.jsp";
	private String submitPage = "/WEB-INF/submits/logout.jsp";
	private String errorPage = "/WEB-INF/errors/logout.jsp";
	
	private MemberUpdateService muService = new MemberUpdateService();
		
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}
	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Member member = (Member)session.getAttribute("login_member");
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("member", member);
			values.put("tpye", "last_access_time");
			
			HashMap<String, Object> resultMap = muService.service(values);
			
			if(!(boolean) resultMap.get("result")) {
				request.setAttribute("errorMsg", "로그아웃과정에서 문제 발생");
				return errorPage;
			}
			
			session.invalidate();
			request.setAttribute("logout_member", member);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return submitPage;
	}
	
	
}
