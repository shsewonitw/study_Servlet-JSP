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

import tje.model.*;
import tje.jdbc.*;
import tje.dao.*;

public class MemeberDetailService extends Service {

	// GET 방식의 요청일 경우 반환할 페이지 정보
	private String formPage = "/WEB-INF/forms/memberDetail.jsp";
	
	private MemberDAO memberDAO = new MemberDAO();
	

	@Override
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {

		
		// 조회를 하고자 하는 멤버의 아이디값을 쿼리 스트링에 추출
		String id = request.getParameter("id");
		
		// 조회할 회원 정보를 저장할 객체 생성
		// (포워딩되는 JSP 파일에서 참조할 객체)
		Member member = new Member(id,null,null,0,null,null);
		
		// JDBC를 사용한 데이터베이스 처리
		Connection conn = JDBCConnection.getConnection();
		Member searchMember = memberDAO.selectOne(conn, member);
		JDBCCloser.close(conn);
		
		
		
		request.setAttribute("member", searchMember);
		
		return formPage;
	}


	
	
	@Override
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

