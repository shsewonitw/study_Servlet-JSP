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

public class ArticleUpdateCommand extends Command {
	private String formPage = "/WEB-INF/forms/article_update.jsp";
	private String submitPage = "/WEB-INF/submits/article_update.jsp";
	private String errorPage = "/WEB-INF/errors/article_update.jsp";

	private DetailArticleSearchService dasService = new DetailArticleSearchService();
	private ArticleUpdateService auService = new ArticleUpdateService();
	
	
	// GET 요청일 경우의 처리 로직을 구현하는 메소드
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

			resultMap = dasService.service(values);

			if (!(Boolean) resultMap.get("result")) {
				request.setAttribute("errorMsg", "게시글을 찾을 수 없습니다.");
				return errorPage;
			}

			request.setAttribute("detailArticle", resultMap.get("detailArticle"));

			// DetailArticle의 member_id 와 현재 로그인된 member_id 비교
			HttpSession session = request.getSession();
			Member login_member = (Member)session.getAttribute("login_member");
			DetailArticle detailArticle = (DetailArticle)resultMap.get("detailArticle");
			
			boolean isArticleWriter = (login_member.getMember_id().equals(detailArticle.getMember_id()));
			if(!isArticleWriter)
				return errorPage;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		boolean result = false;
		try(Connection conn = ConnectionProvider.getConnection()){
			DetailArticle detailArticle = new DetailArticle();
			detailArticle.setArticle_id(article_id);
			detailArticle.setTitle(title);
			detailArticle.setContent(content);
			
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("model",detailArticle);
			
			HashMap<String,Object> resultMap = new HashMap<>();
			resultMap = auService.service(values);
			result = (boolean)resultMap.get("result");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(!result) {
			request.setAttribute("errorMsg", "게시글 수정과정에서 문제가 발생했습니다.");
			return errorPage;
		}
		request.setAttribute("article_id", strArticle_id);
		return submitPage;
	}


}