package tje.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.sql.*;

import tje.dao.SimpleArticleDAO;
import tje.jdbc.util.*;
import tje.model.Member;
import tje.model.SimpleArticle;
import tje.service.*;

public class ArticleCommand extends Command {
	private String formPage = "/WEB-INF/forms/article.jsp";
	private String submitPage = "/WEB-INF/submits/article.jsp";
	private String errorPage = "/WEB-INF/errors/article.jsp";

	private ArticleListService alService = new ArticleListService();
	private ArticleSearchService asService = new ArticleSearchService();
	private CommentListService clService = new CommentListService();
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
		
			
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			
			SimpleArticle simpleArticle = new SimpleArticle();
			
			
			HashMap<String, Object> resultMap = alService.service(values);
			
			/*
			ArrayList<SimpleArticle> simpleArticleArray = (ArrayList<SimpleArticle>)resultMap.get("articleList");
			
			for(SimpleArticle sa : simpleArticleArray) {
				int article_id = sa.getArticle_id();
				simpleArticle.setArticle_id(article_id);
				HashMap<String, Object> hm = new HashMap<String, Object>();
				hm.put("conn",conn);
				hm.put("article_id",article_id);
				Integer comment_count = (Integer)clService.service(hm).get("commentSize");
				System.out.println(comment_count);
			}
			*/
			
			
			request.setAttribute("articleList", resultMap.get("articleList"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return formPage;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String searchItem = request.getParameter("searchItem");
		String searchValue = request.getParameter("searchValue");
				
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("searchItem", searchItem);
			values.put("searchValue", searchValue);
			
			HashMap<String, Object> resultMap = asService.service(values);
			request.setAttribute("articleSearch", resultMap.get("articleSearch"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return submitPage;
	}
}
