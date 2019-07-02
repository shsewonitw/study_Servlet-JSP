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

public class MemberJoinCommand extends Command {

	private MemberRegistService mrService = new MemberRegistService();
	private MemberSelectService msService = new MemberSelectService();
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		String type = request.getParameter("type");
		String member_id = null;
		String nickname = null;
		String email = null;
		
		try(Connection conn = ConnectionProvider.getConnection()){
			Member member = new Member();
			HashMap<String, Object> values = new HashMap<>();
			Member selectedMember = null;
			boolean result = false;
			if(type.equals("idCheck")) {
				member_id = request.getParameter("member_id");
				
				if(member_id != null && member_id.length() == 0) {
					return null;
				}
				member_id = member_id.trim().toLowerCase();
				member.setMember_id(member_id);
				values.put("conn", conn);
				values.put("member",member);
				selectedMember = (Member)msService.service(values).get("result"); 
				
				if(selectedMember == null) {
					result = false;
					return null;
				}
				
				result = member_id.equals(selectedMember.getMember_id()) ? true : false;
			} else if(type.equals("nickNameCheck")) {
				nickname = request.getParameter("nickName");
				
				if(nickname != null && nickname.length() == 0) {
					return null;
				}
				nickname = nickname.trim().toLowerCase();
				member.setNickname(nickname);
				values.put("conn", conn);
				values.put("member",member);
				selectedMember = (Member)msService.service(values).get("result"); 
				
				if(selectedMember == null) {
					result = false;
					return null;
				}
				
				result = nickname.equals(selectedMember.getNickname()) ? true : false;
			}	else if(type.equals("modify_nickNameCheck")) {
				nickname = request.getParameter("nickName");
				
				if(nickname != null && nickname.length() == 0) {
					return null;
				}
				HttpSession session = request.getSession();
				Member login_member = (Member)session.getAttribute("login_member");
				
				if( (login_member != null) && (nickname.equals(login_member.getNickname())) ) {
					response.setContentType("text/plane;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println(false);
					out.flush();
					out.close();
					return null;
				}
				
				nickname = nickname.trim().toLowerCase();
				member.setNickname(nickname);
				values.put("conn", conn);
				values.put("member",member);
				selectedMember = (Member)msService.service(values).get("result"); 
				
				if(selectedMember == null) {
					result = false;
					return null;
				}
				
				result = nickname.equals(selectedMember.getNickname()) ? true : false;
			} else if(type.equals("emailCheck")) {
				email = request.getParameter("email");
				if(email != null && email.length() == 0) {
					return null;
				}
				
				
				email = email.trim().toLowerCase();
				member.setEmail(email);
				values.put("conn", conn);
				values.put("member",member);
				selectedMember = (Member)msService.service(values).get("result"); 
				
				if(selectedMember == null) {
					result = false;
					return null;
				}
				
				result = email.equals(selectedMember.getEmail()) ? true : false;
			} else if(type.equals("modify_emailCheck")) {
				email = request.getParameter("email");
				if(email != null && email.length() == 0) {
					return null;
				}
				
				HttpSession session = request.getSession();
				Member login_member = (Member)session.getAttribute("login_member");
				
				if( (login_member != null) && (email.equals(login_member.getEmail())) ) {
					response.setContentType("text/plane;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println(false);
					out.flush();
					out.close();
					return null;
				}
				
				email = email.trim().toLowerCase();
				member.setEmail(email);
				values.put("conn", conn);
				values.put("member",member);
				selectedMember = (Member)msService.service(values).get("result"); 
				
				if(selectedMember == null) {
					result = false;
					return null;
				}
				
				result = email.equals(selectedMember.getEmail()) ? true : false;
			}
			
			response.setContentType("text/plane;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {

		String member_id = request.getParameter("member_id").trim().toLowerCase();
		String password= request.getParameter("password").trim().toLowerCase();
		String nickname = request.getParameter("nickname").trim().toLowerCase();
		String email = request.getParameter("email").trim().toLowerCase();
		
		Member member = new Member(member_id, password, nickname, email, null, null);
		
		try(Connection conn = ConnectionProvider.getConnection()){
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn",conn);
			values.put("member",member);
			
			boolean result = (boolean)mrService.service(values).get("result");
			
			
		
			// 회원가입 성공
			response.setContentType("text/plane;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}