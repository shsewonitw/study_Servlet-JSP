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
import tje.dao.*;

public class ThumbsUpCommand extends Command {

	private ThumbsUpDAO thumbsUpDAO = new ThumbsUpDAO();
	private RecipeDAO recipeDAO = new RecipeDAO();
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {

		String member_id = request.getParameter("login_member_id");
		String strRecipe_id = request.getParameter("recipe_id");
		
		
		if (strRecipe_id == null)
			return null;
		if (member_id != null && member_id.length() == 0) {
			return null;
		}
		int recipe_id = Integer.parseInt(strRecipe_id);
		
		Recipe recipe = new Recipe();
		recipe.setRecipe_id(recipe_id);
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			ThumbsUp thumbsUp = new ThumbsUp(recipe_id,member_id);
			
			Boolean result = null;
			result = thumbsUpDAO.dupleCheck(conn, thumbsUp);
			
			if(result == null)
				return null;
			
			if(result) {
				// 좋아요 중복체크 걸렸을때
				boolean flag1 = recipeDAO.minusLike_count(conn, recipe);
				boolean flag2 = thumbsUpDAO.delete(conn, thumbsUp);
				
				if(!flag1 || !flag2)
					return null;
				
				response.setContentType("text/plane;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(2);
				out.flush();
				out.close();
				return null;
			}
			else {
				// 좋아요 중복체크 걸리지 않았을때
				boolean flag1 = recipeDAO.plusLike_count(conn, recipe);
				boolean flag2 = thumbsUpDAO.insert(conn, thumbsUp);
				
				if(!flag1 || !flag2)
					return null;
				
				response.setContentType("text/plane;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(1);
				out.flush();
				out.close();
				return null;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {


		
		return null;
	}
}