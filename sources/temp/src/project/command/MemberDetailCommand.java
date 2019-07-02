package project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.sql.*;

import project.jdbc.util.ConnectionProvider;
import project.model.*;
import project.sevice.*;


public class MemberDetailCommand extends Command {
	
	private String formPage = "/WEB-INF/forms/member_detail.jsp";	
	private String errorPage = "/WEB-INF/errors/member_detail.jsp";
	
	private MemberIDCheckService micService = new MemberIDCheckService();
	private MemberUpdateService muService = new MemberUpdateService();
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Member member = (Member)session.getAttribute("login_member");
		String member_id = (String)session.getAttribute("member_id_detail");
		
		session.removeAttribute("member_id_detail");
		
		if(member_id == null || member.getMember_id().equalsIgnoreCase(member_id)) {
			request.setAttribute("errorMsg", "잘못된 요청입니다.");
			return errorPage;
		}
		
		return formPage;
	}
	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return null;
	}
		
}
