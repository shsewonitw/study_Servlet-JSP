package tje.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.JsonArray;

import java.util.*;
import java.io.*;
import java.sql.*;

import tje.service.*;
import tje.dao.*;
import tje.jdbc.util.*;
import tje.model.*;
public class RecipeDetailCommand extends Command {
	
	private String formPage = "/WEB-INF/forms/recipeDetail.jsp";
	private String errorPage = "/WEB-INF/forms/authError.jsp";
	private RecipeDetailService rdService = new RecipeDetailService();
	private SimpleRecipeDAO simpleRecipeDAO = new SimpleRecipeDAO();
	private ThumbsUpDAO thumbsUpDAO = new ThumbsUpDAO();
	private CommentDAO commentDAO = new CommentDAO();
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Member login_member = (Member)session.getAttribute("login_member");
		if(login_member == null)
			return errorPage;
		String member_id = login_member.getMember_id();
		String strRecipe_id = request.getParameter("recipe_id");
		int recipe_id = 0;
		if(strRecipe_id != null && strRecipe_id.length() > 0) {
			recipe_id = Integer.parseInt(strRecipe_id);
		}
		ThumbsUp thumbsUp = new ThumbsUp(recipe_id, member_id);
		Recipe recipe = new Recipe();
		recipe.setRecipe_id(recipe_id);
		Recipe selectedRecipe = null;
		Comment comment = new Comment();
		comment.setRecipe_id(recipe_id);
		SimpleRecipe selectedSimpleRecipe = null;
		try(Connection conn = ConnectionProvider.getConnection()){
			HashMap<String,Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("model", recipe);
			
			boolean flag = simpleRecipeDAO.plusRead_count(conn, recipe);
			selectedRecipe = (Recipe)rdService.service(values).get("result");
			selectedSimpleRecipe = simpleRecipeDAO.selectOne(conn, recipe);
			
			if(selectedRecipe == null || selectedSimpleRecipe == null || !flag ) {
				return errorPage;
			}
			
			
			boolean isLike = thumbsUpDAO.dupleCheck(conn, thumbsUp);
			
			request.setAttribute("isLike", isLike);
			request.setAttribute("selectedRecipe", selectedRecipe);
			request.setAttribute("selectedSimpleRecipe", selectedSimpleRecipe);
			
			// 댓글 가져옴
			ArrayList<Comment> commentList = commentDAO.selectWhereRecipeId(conn, comment); 
			request.setAttribute("commentList", commentList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return formPage;
	}

	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		// var params = "login_member_id="+login_member_id+"&recipe_id="+recipe_id+"&comment_content="+comment_content;
		// 댓글등록 ajax
		String login_member_id = request.getParameter("login_member_id");
		String strRecipe_id = request.getParameter("recipe_id");
		int recipe_id = 0;
		if(strRecipe_id != null && strRecipe_id.length() > 0) {
			recipe_id = Integer.parseInt(strRecipe_id);
		}
		String comment_content = request.getParameter("comment_content");
		Comment comment = new Comment();
		comment.setRecipe_id(recipe_id);
		comment.setContent(comment_content);
		comment.setMember_id(login_member_id);
		
		try(Connection conn = ConnectionProvider.getConnection()){
			boolean result = commentDAO.insert(conn, comment);	
			int last_insert_id = commentDAO.last_insert_id(conn);
			if(result) {
				response.setContentType("text/plane;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(comment_content+"|+|"+true+"|+|"+last_insert_id);
				
				out.flush();
				out.close();
			} else {
				response.setContentType("text/plane;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(false);
				out.flush();
				out.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}