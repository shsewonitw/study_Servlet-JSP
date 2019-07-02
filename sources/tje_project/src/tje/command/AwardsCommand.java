package tje.command;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tje.jdbc.util.ConnectionProvider;
import tje.model.*;
import tje.dao.*;

public class AwardsCommand extends Command {

	private String formPage = "/WEB-INF/forms/awards.jsp";
	private String errorPage = "/WEB-INF/forms/authError.jsp";
	
	private SimpleRecipeDAO simpleRecipeDAO = new SimpleRecipeDAO();
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Member login_member = (Member)session.getAttribute("login_member");
		if(login_member == null)
			return errorPage;
		
		try(Connection conn = ConnectionProvider.getConnection()){
			ArrayList<SimpleRecipe> bestRecipeList_beginner = simpleRecipeDAO.selectBeginnerBest(conn);
			ArrayList<SimpleRecipe> bestRecipeList_intermediate = simpleRecipeDAO.selectIntermediateBest(conn);
			ArrayList<SimpleRecipe> bestRecipeList_advanced = simpleRecipeDAO.selectAdvancedBest(conn);
			
			request.setAttribute("bestRecipeList_beginner", bestRecipeList_beginner);
			request.setAttribute("bestRecipeList_intermediate", bestRecipeList_intermediate);
			request.setAttribute("bestRecipeList_advanced", bestRecipeList_advanced);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return formPage;
	}
	
	
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
}