package tje.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tje.service.*;
import tje.jdbc.util.*;
import tje.model.Member;

import java.util.*;
import java.sql.*;

public class MemberUpdateCommand extends Command {
	private String formPage = "/WEB-INF/forms/member_update.jsp";
	private String submitPage = "/WEB-INF/submits/member_update.jsp";
	private String errorPage = "/WEB-INF/errors/member_update.jsp";

	private MemberUpdateService muService = new MemberUpdateService();

	// GET 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		int age = intConverter(request.getParameter("age"));
		int year = intConverter(request.getParameter("year"));
		int month = intConverter(request.getParameter("month"));
		int day = intConverter(request.getParameter("day"));
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		Member login_member = (Member)session.getAttribute("login_member");
		/*
		String member_id = login_member.getMember_id();
		
		Member member = new Member();
		member.setAge(age);
		member.setTel(tel);
		member.setAddress(address);
		member.setBirth(year, month, day);
		member.setMember_id(member_id);
		*/
		login_member.setAge(age);
		login_member.setTel(tel);
		login_member.setAddress(address);
		login_member.setBirth(year, month, day);
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			HashMap<String, Object> values = new HashMap<String, Object>();
			values.put("conn", conn);
			values.put("member", login_member);
			values.put("type","update_member");
			boolean result = (boolean) muService.service(values).get("result");

			result = (boolean) muService.service(values).get("result");

			if (!result) {
				request.setAttribute("errorMsg", "정보 수정 과정에서 문제가 발생했습니다.(관리자에게 문의하세요)");
				return errorPage;
			}

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("login_member", login_member);
		return submitPage;
	}

	private int intConverter(String source) {
		int data = 0;
		try {
			data = Integer.parseInt(source);
		} catch (Exception e) {
			;
		}
		return data;
	}
}