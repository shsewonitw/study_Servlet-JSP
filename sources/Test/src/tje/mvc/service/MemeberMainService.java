package tje.mvc.service;

import java.io.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemeberMainService extends Service {
	
	String formPage = "/WEB-INF/forms/main.jsp";
	

	@Override
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}

	@Override
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	
}