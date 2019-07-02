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
			
			// 게시글목록 배열 받아와서 request에 저장
			HashMap<String, Object> resultMap = alService.service(values);
			request.setAttribute("articleList", resultMap.get("articleList"));
			
			// 각 게시글의 댓글 갯수 받아와서 request에 저장
			
			ArrayList<SimpleArticle>  saArray = (ArrayList<SimpleArticle>)resultMap.get("articleList"); 
			HashMap<Long,Integer> ccMap = new HashMap<>();

			for(SimpleArticle sa : saArray) {
				Comment model = new Comment();
				Integer article_id = sa.getArticle_id();
				model.setArticle_id(article_id);
				HashMap<String, Object> hm = new HashMap<>();
				hm.put("conn", conn);
				hm.put("model", model);
				Integer commentSize = (Integer)clService.service(hm).get("commentSize");
				ccMap.put(article_id.longValue(), commentSize);
			}
			
			request.setAttribute("ccMap", ccMap);

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
