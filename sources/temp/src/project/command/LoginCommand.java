package project.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import project.jdbc.util.ConnectionProvider;
import project.model.*;
import project.sevice.*;

public class LoginCommand extends Command {
	
	private String formPage = "/WEB-INF/forms/login.jsp";
	private String submitPage = "/WEB-INF/submits/login.jsp";
	private String errorPage = "/WEB-INF/errors/login.jsp";
	
	private MemberIDCheckService micService = new MemberIDCheckService();
	private MemberUpdateService muService = new MemberUpdateService();	
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}
	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		// 저장된 ID값 입력
		String member_id = request.getParameter("member_id");
		String password = request.getParameter("password");
		String save_member_id = request.getParameter("save_member_id");
		
		Member member = new Member(member_id, password, null, null, null, null);
		
		try (Connection conn = ConnectionProvider.getConnection()){
			
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("member", member);
			HashMap<String, Object> resultMap = micService.service(values);
			
			if(!(boolean)resultMap.get("result")) {
				request.setAttribute("errorMsg", "ID의 값이 존재하지 않습니다.");
				return formPage;
			}
			
			Member searchedMember = (Member)resultMap.get("searchedMember");
			boolean isLogin = searchedMember.getPassword().equals(member.getPassword());
			
			if(!isLogin) {
				request.setAttribute("errorMsg_PW", "PASSWORD를 확인하세요.");
			}
			
			values.put("type", "last_access_time");
			resultMap = muService.service(values);
			
			if(!(boolean) resultMap.get("result")) {
				request.setAttribute("errorMsg", "로그인과정에서 문제발생.");
				return errorPage;
			}
					
			
			HttpSession session = request.getSession();
			
			searchedMember.setLast_access_time(new java.util.Date());
			session.setAttribute("login_member", searchedMember);
			
			if(save_member_id != null && save_member_id.equals("true")) {
				response.addCookie(new Cookie("save_member_id", member_id));
			} else {
				Cookie cookie = new Cookie("save_member_id", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}							
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return submitPage;
	}
	
	
}
