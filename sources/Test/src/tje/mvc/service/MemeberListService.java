package tje.mvc.service;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tje.dao.*;
import tje.jdbc.*;
import tje.model.*;

public class MemeberListService extends Service {

	private static final String formPage = "/WEB-INF/forms/memberList.jsp";

	private static String jdbc_url;
	private static String jdbc_id;
	private static String jdbc_password;
	
	private MemberDAO memberDAO = new MemberDAO();

	@Override
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {


		ArrayList<Member> list = new ArrayList<>();

		// JDBC를 사용한 데이터베이스 처리
		Connection conn = JDBCConnection.getConnection();
		list = memberDAO.selectAll(conn);
		
		JDBCCloser.close(conn);
		
		request.setAttribute("list", list);

		return formPage;
	}


	@Override
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
