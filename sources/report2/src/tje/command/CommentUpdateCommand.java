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

public class CommentUpdateCommand extends Command {
	private String formPage = "/WEB-INF/forms/comment_update.jsp";
	private String submitPage = "/WEB-INF/forms/detail_article.jsp";
	private String errorPage = "/WEB-INF/errors/comment_update.jsp";

	private ArticleUpdateService auService = new ArticleUpdateService();
	private CommentListService clService = new CommentListService();
	private CommentUpdateService cuService = new CommentUpdateService();
	// GET 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
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

			resultMap = clService.service(values);

			if ((Comment)resultMap.get("commentOne") == null) {
				request.setAttribute("errorMsg", "댓글 수정 과정 문제발생");
				return errorPage;
			}

			
			request.setAttribute("comment", resultMap.get("commentOne"));

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		String strComment_id = request.getParameter("comment_id");
		String strArticle_id = request.getParameter("article_id");
		int comment_id = 0;
		try {
			comment_id = Integer.parseInt(strComment_id);
		} catch (Exception e) {
			request.setAttribute("errorMsg", "잘못된 접근입니다.");
			return errorPage;
		}
		
		String content = request.getParameter("content");
		
		
		boolean result = false;
		try(Connection conn = ConnectionProvider.getConnection()){
			Comment comment = new Comment();
			comment.setComment_id(comment_id);
			comment.setContent(content);
			
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("model",comment);
			
			HashMap<String,Object> resultMap = new HashMap<>();
			resultMap = cuService.service(values);
			result = (boolean)resultMap.get("result");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(!result) {
			request.setAttribute("errorMsg", "댓글 수정과정에서 문제가 발생했습니다.");
			return errorPage;
		}
		request.setAttribute("article_id", strArticle_id);
		return new DetailArticleCommand().processForm(request, response);
	}


}