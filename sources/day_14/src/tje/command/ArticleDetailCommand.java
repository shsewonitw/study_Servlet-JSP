package tje.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.util.Date;
import java.sql.*;
import tje.jdbc.util.*;
import tje.model.DetailArticle;
import tje.model.Member;
import tje.service.*;

public class ArticleDetailCommand extends Command {
	private String formPage = "/WEB-INF/forms/article_detail.jsp";
	private String submitPage = "/WEB-INF/submits/article_detail.jsp";
	private String errorPage = "/WEB-INF/errors/article_detail.jsp";


	private ArticleDetailService adService = new ArticleDetailService();
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		Integer article_id = Integer.parseInt(request.getParameter("article_id"));
		DetailArticle detailArticle = new DetailArticle(article_id, null, null, null, null, null, null, 0);
		
		try (Connection conn = ConnectionProvider.getConnection()) {

			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("detailArticle", detailArticle);
			HashMap<String, Object> resultMap = adService.service(values);

			if( (DetailArticle)resultMap.get("result1") == null || (boolean)resultMap.get("result2") == false ){
				request.setAttribute("errorMsg", "게시글 로딩과정에서 문제가 발생했습니다.");
				return errorPage;
			}
			
			request.setAttribute("detailArticle", (DetailArticle)resultMap.get("result1"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		return submitPage;
	}

}