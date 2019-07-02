package tje.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.io.*;
import java.sql.*;

import tje.service.*;
import tje.dao.*;
import tje.jdbc.util.*;
import tje.model.*;
public class CommentDeleteCommand extends Command {
	
	private String formPage = "/WEB-INF/forms/recipeDetail.jsp";
	private String errorPage = "/WEB-INF/forms/authError.jsp";
	private CommentDAO commentDAO = new CommentDAO();
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Member login_member = (Member)session.getAttribute("login_member");
		if(login_member == null)
			return errorPage;
		
		String strDeleteCommentId = request.getParameter("deleteCommentId");
		if(strDeleteCommentId.length() < 1)
			return errorPage;
		int deleteCommentId = Integer.parseInt(strDeleteCommentId);
		
		try(Connection conn = ConnectionProvider.getConnection()){
			Comment comment = new Comment();
			comment.setComment_id(deleteCommentId);
			comment.setMember_id(login_member.getMember_id());
			boolean flag = commentDAO.delete(conn, comment);
			
			if(!flag) {
				response.setContentType("text/plane;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(false);
				out.flush();
				out.close();
				return null;
			}
			
			response.setContentType("text/plane;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(deleteCommentId);
			out.flush();
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
	
		return null;
	}
}