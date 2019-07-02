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

public class MemberCheckCommand extends Command {
	private String formPage = "/WEB-INF/forms/memberCheck.jsp";

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 입력 패스워드
		String password = request.getParameter("password");
		// 로그인회원 패스워드
		Member login_member = (Member) session.getAttribute("login_member");
		response.setContentType("text/plane;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();

			if (password.length() < 1 || login_member == null) {
				out.println(false);
				out.flush();
				out.close();
			}

			if (password.equals(login_member.getPassword())) {
				out.println(true);
				out.flush();
				out.close();
			} else {
				out.println(false);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}