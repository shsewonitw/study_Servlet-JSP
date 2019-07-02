package project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import project.jdbc.util.ConnectionProvider;
import project.model.*;
import project.sevice.MemberIDCheckService;
import project.sevice.MemberRegistService;

public class RegistCheckCommand extends Command {
	
	private MemberIDCheckService micService = new MemberIDCheckService();
	
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		// 저장된 ID값 입력
		String member_id = request.getParameter("member_id");
				
		Member member = new Member();
		member.setMember_id(member_id);
		
		// boolean result = false;
		Member selectedMember = null;
		try (Connection conn = ConnectionProvider.getConnection()){
			
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("member", member);
			
			// result = (Boolean)micService.service(values).get("result");
			selectedMember = (Member)micService.service(values).get("searchedMember");
			System.out.println(selectedMember.getMember_id());
			
			
			if(selectedMember != null && !selectedMember.getMember_id().equals(member_id)) {
				// 아이디중복 안됨
				response.setContentType("text/plane;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(false);
				out.flush();
				out.close();	
				return null;
			} else {
				// 아이디 중복임
				response.setContentType("text/plane;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(true);
				out.flush();
				out.close();
				return null;
			}
				
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	
				
		return null;		
	}
	
	
		
}
