package tje.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.sql.*;

import tje.dao.SimpleArticleDAO;
import tje.jdbc.util.*;
import tje.model.*;
import tje.service.*;

public class MainCommand extends Command {
	private String formPage = "/WEB-INF/forms/main.jsp";
	private String submitPage = null;
	private String errorPage = null;

	private ArticleCountService acService = new ArticleCountService();
	// GET 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			
			Integer articleCount = (Integer)acService.service(values).get("articleCount");
			request.setAttribute("articleCount", articleCount);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return formPage;
	}
	
	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		return submitPage;
	}
	
}














