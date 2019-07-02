package tje.command;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.*;

import tje.service.*;
import tje.jdbc.util.*;
import tje.model.*;
import tje.dao.*;
public class LikeRecipeListCommand extends Command {

	private String formPage = "/WEB-INF/forms/likeRecipeList.jsp";
	private String errorPage = "/WEB-INF/forms/authError.jsp";
	private SimpleRecipeDAO simpleRecipeDAO = new SimpleRecipeDAO();
	private CommentDAO commentDAO = new CommentDAO();
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Member login_member = (Member) session.getAttribute("login_member");
		if(login_member == null)
			return errorPage;
		
		String member_id = login_member.getMember_id();
		SimpleRecipe simpleRecipe = new SimpleRecipe();
		simpleRecipe.setMember_id(member_id);
		try(Connection conn = ConnectionProvider.getConnection()) {
			ArrayList<SimpleRecipe> result = simpleRecipeDAO.selectLikeList(conn, simpleRecipe);
			request.setAttribute("likeRecipeList", result);
			
			HashMap<Long,Integer> ccMap = new HashMap<>();

			for(SimpleRecipe sr : result) {
				SimpleRecipe model = new SimpleRecipe();
				Integer recipe_id = sr.getRecipe_id();
				model.setRecipe_id(recipe_id);
				Integer commentSize = (Integer)commentDAO.selectCount(conn, model);
				ccMap.put(recipe_id.longValue(), commentSize);
			}
			request.setAttribute("ccMap", ccMap);
		} catch(Exception e){
			e.printStackTrace();
		}
		return formPage;
	}
	

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
	
		return null;
	}
}