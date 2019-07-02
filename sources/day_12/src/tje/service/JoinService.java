package tje.service;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class JoinService extends Service {
	// 
	private String formPage = "/WEB-INF/views/join.jsp";
	private String submitPage = "/WEB-INF/views/join.jsp";
	
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}
	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		return submitPage;
	}
}
