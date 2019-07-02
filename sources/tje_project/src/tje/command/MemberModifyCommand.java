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

public class MemberModifyCommand extends Command {
	private String formPage = "/WEB-INF/forms/memberModify.jsp";
	private String errorPage = "/WEB-INF/forms/authError.jsp";
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String password = request.getParameter("checkPassword");
		Member member = null;
		member = (Member)session.getAttribute("login_member"); 
		
		if( password.length() < 1 || member == null )
			return errorPage;
		
		if( !password.equals(member.getPassword()) ) {
			return errorPage;
		}
		
		return formPage;
		
	}
}