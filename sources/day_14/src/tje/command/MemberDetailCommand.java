package tje.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.util.Date;
import java.sql.*;
import tje.jdbc.util.*;
import tje.model.Member;
import tje.service.*;

public class MemberDetailCommand extends Command {
	private String formPage = "/WEB-INF/forms/member_detail.jsp";
	private String errorPage = "/WEB-INF/errors/member_detail.jsp";
	private MemberIDCheckService micService = new MemberIDCheckService();


	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute("member_id_detail");
		Member member = (Member)session.getAttribute("login_member");
		
		session.removeAttribute("member_id_detail");
		
		if(member_id == null || !member.getMember_id().equals(member_id)) {
			request.setAttribute("errorMsg", "잘못된 요청이 실행되었습니다.");
			return errorPage;
		}
		
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return null;
	}

}