package tje.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.io.*;
import java.sql.*;

import tje.service.*;
import tje.jdbc.util.*;
import tje.model.*;
public class RecipeRegistCommand extends Command {
	private String formPage = "/WEB-INF/forms/recipeRegist.jsp";
	private String submitPage = "/WEB-INF/forms/recipeRegistSubmit.jsp";
	private String errorPage = "/WEB-INF/forms/authError.jsp";
	private boolean isReload = false;
	private RecipeRegistService rrService = new RecipeRegistService();
	private LastInsertIdService liService = new LastInsertIdService();
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Member login_member = (Member)session.getAttribute("login_member");
		if(login_member == null)
			return errorPage;
		
		isReload = false;
		return formPage;
	}

	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		// 새로고침 했을때 글이 다시 등록되지 않도록 작업
		if(isReload)
			return submitPage;
		
		HttpSession session = request.getSession();
		
		Member login_member = (Member)session.getAttribute("login_member");
		String category = (String)request.getParameter("category");
		String title = (String)request.getParameter("title");
		String content = (String)request.getParameter("content");
		
		if(login_member == null)
			return errorPage;
		
		String member_id = login_member.getMember_id();
		Recipe recipe = new Recipe();
		recipe.setMember_id(member_id);
		recipe.setCategory(category);
		recipe.setTitle(title);
		recipe.setContent(content);
		boolean result = false;
		try(Connection conn = ConnectionProvider.getConnection()){
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn",conn);
			values.put("recipe", recipe);
			
			result = (boolean)rrService.service(values).get("result");
			
			if( !result )
				return errorPage;
			
			isReload = true;
			int last_insert_id = (int)liService.service(values).get("result");
			request.setAttribute("last_insert_id", last_insert_id);
			return submitPage;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
		
	}
}