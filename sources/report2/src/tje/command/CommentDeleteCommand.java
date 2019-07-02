package tje.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tje.service.*;
import tje.jdbc.util.*;
import tje.model.Comment;
import tje.model.DetailArticle;
import tje.model.Member;

import java.util.*;
import java.sql.*;

public class CommentDeleteCommand extends Command {
	private String formPage = "/WEB-INF/forms/comment_delete.jsp";
	private String submitPage = "";
	private String errorPage = "/WEB-INF/errors/comment_delete.jsp";

	private CommentDeleteService cdService = new CommentDeleteService();
	private ArticleDeleteService adService = new ArticleDeleteService();
	
	
	// GET 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String strArticle_id = request.getParameter("article_id");
		String strComment_id = request.getParameter("comment_id");
		int comment_id = 0;
		try {
			comment_id = Integer.parseInt(strComment_id);
		} catch (Exception e) {
			request.setAttribute("errorMsg", "잘못된 접근입니다.");
			return errorPage;
		}
		
		Comment model = new Comment();
		model.setComment_id(comment_id);
		
		try (Connection conn = ConnectionProvider.getConnection()) {

			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("model", model);

			HashMap<String, Object> resultMap = null;

			resultMap = cdService.service(values);

			if (!(Boolean) resultMap.get("result")) {
				request.setAttribute("errorMsg", "댓글 삭제 과정에서 문제가 발생했습니다.");
				return errorPage;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		submitPage = "/auth/article_detail.do?"+strArticle_id;
		return submitPage;
	}


}