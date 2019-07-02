package tje.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.sql.*;
import tje.jdbc.util.*;
import tje.model.Comment;
import tje.model.Member;
import tje.model.SimpleArticle;
import tje.service.*;

public class MemberDetailCommand extends Command {
	private String formPage = "/WEB-INF/forms/member_detail.jsp";
	private String errorPage = "/WEB-INF/errors/member_detail.jsp";
	
	private MemberIDCheckService micService = new MemberIDCheckService();
	private MemberUpdateService muService = new MemberUpdateService();
	private ArticleFiveService afService = new ArticleFiveService();
	private CommentListService clService = new CommentListService();
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Member member = (Member)session.getAttribute("login_member");
		String member_id = (String)session.getAttribute("member_id_detail");
		
		session.removeAttribute("member_id_detail");		
						
		if( member_id == null || !member.getMember_id().equals(member_id) ) {
			request.setAttribute("errorMsg", "잘못된 요청이 실행되었습니다.");
			return errorPage;
		}
		
		try(Connection conn = ConnectionProvider.getConnection()){
			HashMap<String, Object> values = new HashMap<>();
			SimpleArticle model = new SimpleArticle();
			model.setMember_id(member_id);
			values.put("conn", conn);
			values.put("model",model);
			HashMap<String, Object> resultMap = afService.service(values);
			request.setAttribute("articleFive", resultMap.get("articleFive"));
			
			
			
			Comment comment_model = new Comment();
			comment_model.setMember_id(member_id);
			values.put("conn", conn);
			values.put("model",comment_model);
			HashMap<String, Object> comment_resultMap = clService.service(values);
			request.setAttribute("commentFive", comment_resultMap.get("commentFive"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return formPage;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return null;
	}
}