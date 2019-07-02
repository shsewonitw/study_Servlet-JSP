package project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.sql.*;

import project.jdbc.util.ConnectionProvider;
import project.model.*;
import project.sevice.MemberIDCheckService;
import project.sevice.MemberRegistService;

public class RegistCommand extends Command {
	
	private String formPage = "/WEB-INF/forms/regist.jsp";
	private String submitPage = "/WEB-INF/submits/regist.jsp";
	private String errorPage = "/WEB-INF/errors/regist.jsp";
	
	private MemberIDCheckService micService = new MemberIDCheckService();
	private MemberRegistService mrService = new MemberRegistService();
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}
	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		// 저장된 ID값 입력
		String member_id = request.getParameter("member_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");		
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		
		Member member = new Member(
				member_id, password, name, tel, address, null);
				
		try (Connection conn = ConnectionProvider.getConnection()){
			
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("member", member);
			boolean result = (Boolean)micService.service(values).get("result");
			
			if(result) {
				request.setAttribute("errorMsg", "ID의 값이 중복 되었습니다.");
				return formPage;
			}
			
			result = (Boolean)mrService.service(values).get("result");
			
			if(result) {
				request.setAttribute("errorMsg", "가입과정에서 문제발생");
				return errorPage;
			}
			
			request.setAttribute("member", micService.service(values).get("searchedMember"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return submitPage;
	}
		
}
