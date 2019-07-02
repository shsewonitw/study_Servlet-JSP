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

public class MemberLoginCommand extends Command {

	private MemberSelectService msService = new MemberSelectService();
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		
		return null;
	}
	
	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		String member_id = request.getParameter("member_id").trim().toLowerCase();
		String password= request.getParameter("password").trim().toLowerCase();
		
		Member member = new Member(member_id, password, null, null, null, null);
		Member selectedMember = null;
		try(Connection conn = ConnectionProvider.getConnection()){
			HashMap<String,Object> values = new HashMap<>();
			values.put("conn",conn);
			values.put("member",member);
			
			selectedMember = (Member)msService.service(values).get("result");
			
			if(selectedMember == null)
				return null;
			
			if( member.getPassword().equals(selectedMember.getPassword()) ) {
				// 로그인 성공
				
				HttpSession session = request.getSession();
				session.setAttribute("login_member", selectedMember);
				
				response.setContentType("text/plane;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(true);
				out.flush();
				out.close();
			} else {
				// 로그인 실패
				response.setContentType("text/plane;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(false);
				out.flush();
				out.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}