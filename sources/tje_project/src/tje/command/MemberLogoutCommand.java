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

public class MemberLogoutCommand extends Command {
	private String formPage = null;
	private MemberSelectService msService = new MemberSelectService();
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("login_member");
		
		formPage = request.getParameter("url");
		//System.out.println(formPage);
		try {
			response.sendRedirect(formPage);
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}