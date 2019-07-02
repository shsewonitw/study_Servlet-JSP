package tje.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.sql.*;

import tje.dao.SimpleArticleDAO;
import tje.jdbc.util.*;
import tje.model.*;
import tje.service.*;

public class DetailArticleCommand extends Command {
	private String formPage = "/WEB-INF/forms/detail_article.jsp";
	private String submitPage = "/WEB-INF/submits/detail_article.jsp";
	private String errorPage = "/WEB-INF/errors/detail_article.jsp";

	private DetailArticleUpdateReadCountService daurcService = new DetailArticleUpdateReadCountService();
	private DetailArticleSearchService dasService = new DetailArticleSearchService();
	private CommentListService clService = new CommentListService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
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

			if (request.getMethod().equals("GET")) {
				resultMap = daurcService.service(values);
				if (!(Boolean) resultMap.get("result")) {
					request.setAttribute("errorMsg", "조회수 갱신에 에러가 발생했습니다.");
					return errorPage;
				}
			}

			resultMap = dasService.service(values);

			if (!(Boolean) resultMap.get("result")) {
				request.setAttribute("errorMsg", "게시글을 찾을 수 없습니다.");
				return errorPage;
			}

			request.setAttribute("detailArticle", resultMap.get("detailArticle"));

			// 댓글 조회
			Comment comment = new Comment();
			comment.setArticle_id(article_id);
			values.put("model", comment);

			resultMap = clService.service(values);
			request.setAttribute("commentSize", resultMap.get("commentSize"));
			request.setAttribute("commentList", resultMap.get("commentList"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return formPage;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		return processForm(request, response);
	}
}
