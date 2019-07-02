package tje.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.io.*;
import java.sql.*;

import tje.service.*;
import tje.jdbc.util.*;
import tje.model.*;

public class MemberModifyAcceptCommand extends Command {
	
	private String errorPage = "/WEB-INF/forms/authError.jsp";
	
	private MemberModifyService mmService = new MemberModifyService();
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String member_id = request.getParameter("modify_member_id").trim().toLowerCase();
		String password= request.getParameter("modify_password").trim().toLowerCase();
		String nickname = request.getParameter("modify_nickname").trim().toLowerCase();
		String email = request.getParameter("modify_email").trim().toLowerCase();
		
		Member member = new Member(member_id, password, nickname, email, null, null);
		try(Connection conn = ConnectionProvider.getConnection()){
			HashMap<String,Object> values = new HashMap<>();
			values.put("conn",conn);
			values.put("member",member);
			
			boolean result = (boolean)mmService.service(values).get("result");
			
			if(!result)
				return errorPage;
			
			HttpSession session = request.getSession();
			session.setAttribute("login_member", member);
			
			return new MainCommand().processForm(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
}