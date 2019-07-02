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

public class ArticleWriteCommand extends Command {
	private String formPage = "/WEB-INF/forms/article_write.jsp";
	private String submitPage = "/WEB-INF/submits/article_write.jsp";
	private String errorPage = "/WEB-INF/errors/article_write.jsp";


	private ArticleWriteService awService = new ArticleWriteService();
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("login_member");
		
		DetailArticle detailArticle = new DetailArticle(0, member.getMember_id(), null, title, content, null, null, 0);
		
		try (Connection conn = ConnectionProvider.getConnection()) {

			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("detailArticle", detailArticle);
			HashMap<String, Object> resultMap = awService.service(values);

			if( !(boolean)resultMap.get("result") ){
				request.setAttribute("errorMsg", "게시글 작성과정에서 문제가 발생했습니다.");
				return errorPage;
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return submitPage;
	}

}