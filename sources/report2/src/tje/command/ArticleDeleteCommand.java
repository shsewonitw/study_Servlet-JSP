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

public class ArticleDeleteCommand extends Command {
	private String formPage = "/WEB-INF/forms/article_delete.jsp";
	private String submitPage = "/WEB-INF/forms/article.jsp";
	private String errorPage = "/WEB-INF/errors/article_delete.jsp";

	private ArticleDeleteService adService = new ArticleDeleteService();
	
	
	// GET 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		
	
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String strArticle_id = request.getParameter("article_id");

		int article_id = 0;
		try {
			article_id = Integer.parseInt(strArticle_id);
		} catch (Exception e) {
			request.setAttribute("errorMsg", "잘못된 접근입니다.");
			return errorPage;
		}
		
		DetailArticle model = new DetailArticle();
		model.setArticle_id(article_id);
		
		try (Connection conn = ConnectionProvider.getConnection()) {

			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("model", model);

			HashMap<String, Object> resultMap = null;

			resultMap = adService.service(values);

			if (!(Boolean) resultMap.get("result")) {
				request.setAttribute("errorMsg", "게시글 삭제 과정에서 문제가 발생했습니다.");
				return errorPage;
			}

			request.setAttribute("detailArticle", resultMap.get("detailArticle"));


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return new ArticleCommand().processForm(request, response);
	}


}