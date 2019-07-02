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
public class RecipeListCommand extends Command {

	private String formPage = "/WEB-INF/forms/recipeList.jsp";
	private String errorPage = "/WEB-INF/forms/authError.jsp";
	private RecipeListService rlService = new RecipeListService();
	private SimpleRecipeDAO simpleRecipeDAO = new SimpleRecipeDAO();
	private CommentDAO commentDAO = new CommentDAO();
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Member login_member = (Member)session.getAttribute("login_member");
		if(login_member == null)
			return errorPage;
		
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			HashMap<String, Object> values = new HashMap<>();	
			values.put("conn", conn);
			
			ArrayList<SimpleRecipe> result = (ArrayList<SimpleRecipe>)rlService.service(values).get("result");
			request.setAttribute("SimpleRecipeList", result);
			
			/*     페이지 기능          */
			ArrayList<ArrayList<SimpleRecipe>> subResult = new ArrayList<ArrayList<SimpleRecipe>>();
			int listSize = result.size();
			int pageSize = (listSize / 10);
			if( pageSize % 10 > 0 )
				pageSize++;
			request.setAttribute("pageSize",pageSize);
			
			ArrayList<SimpleRecipe> temp = new ArrayList<>();
			for(int i = 0 ; i < listSize ; i++) {
				
				temp.add(result.get(i));
				
				if(i != 0 && i%10 == 9) {
					subResult.add(temp);
					temp = new ArrayList<>();
				}else if(i == listSize-1) {
					subResult.add(temp);
					temp = new ArrayList<>();
				}
			}
			

			String strPAGENUMBER = (String)request.getParameter("PAGENUMBER");
			int PAGENUMBER = 0;
			if(strPAGENUMBER == null) {
				PAGENUMBER = 0;
			} else {
				PAGENUMBER = Integer.parseInt(strPAGENUMBER);
			}
						
			request.setAttribute("SimpleRecipeList", subResult.get(PAGENUMBER));
			
			
			
			//	subResult.add(result.subList(0, 10));
			
			// --------------- //
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
		request.setAttribute("whatMethod", "GET");
		return formPage;
	}
	

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String level = request.getParameter("level");
		
		if( level.equals("level1") ) {
			// 초급요리 눌렀을때
			try(Connection conn = ConnectionProvider.getConnection()) {
				SimpleRecipe simpleRecipe = new SimpleRecipe();
				simpleRecipe.setCategory("beginner");
				ArrayList<SimpleRecipe> result = simpleRecipeDAO.selectFromCategory(conn, simpleRecipe);
				request.setAttribute("SimpleRecipeList", result);
				/*     페이지 기능          */
				ArrayList<ArrayList<SimpleRecipe>> subResult = new ArrayList<ArrayList<SimpleRecipe>>();
				int listSize = result.size();
				int pageSize = (listSize / 10);
				if( pageSize % 10 > 0 )
					pageSize++;
				request.setAttribute("pageSize",pageSize);
				
				ArrayList<SimpleRecipe> temp = new ArrayList<>();
				for(int i = 0 ; i < listSize ; i++) {
					
					temp.add(result.get(i));
					
					if(i != 0 && i%10 == 9) {
						subResult.add(temp);
						temp = new ArrayList<>();
					}else if(i == listSize-1) {
						subResult.add(temp);
						temp = new ArrayList<>();
					}
				}
				

				String strPAGENUMBER = (String)request.getParameter("PAGENUMBER");
				int PAGENUMBER = 0;
				if(strPAGENUMBER == null) {
					PAGENUMBER = 0;
				} else {
					PAGENUMBER = Integer.parseInt(strPAGENUMBER);
				}
							
				request.setAttribute("SimpleRecipeList", subResult.get(PAGENUMBER));
				
				
				
				//	subResult.add(result.subList(0, 10));
				
				// --------------- //
				HashMap<Long,Integer> ccMap = new HashMap<>();

				for(SimpleRecipe sr : result) {
					SimpleRecipe model = new SimpleRecipe();
					Integer recipe_id = sr.getRecipe_id();
					model.setRecipe_id(recipe_id);
					Integer commentSize = (Integer)commentDAO.selectCount(conn, model);
					ccMap.put(recipe_id.longValue(), commentSize);
				}
				request.setAttribute("ccMap", ccMap);
				request.setAttribute("whatMethod", "POST1");
			} catch(Exception e){
				e.printStackTrace();
			}
		} else if( level.equals("level2") ) {
			// 중금요리 눌렀을때
			try(Connection conn = ConnectionProvider.getConnection()) {
				SimpleRecipe simpleRecipe = new SimpleRecipe();
				simpleRecipe.setCategory("intermediate");
				ArrayList<SimpleRecipe> result = simpleRecipeDAO.selectFromCategory(conn, simpleRecipe);
				request.setAttribute("SimpleRecipeList", result);
				
				/*     페이지 기능          */
				ArrayList<ArrayList<SimpleRecipe>> subResult = new ArrayList<ArrayList<SimpleRecipe>>();
				int listSize = result.size();
				int pageSize = (listSize / 10);
				if( pageSize % 10 > 0 )
					pageSize++;
				request.setAttribute("pageSize",pageSize);
				
				ArrayList<SimpleRecipe> temp = new ArrayList<>();
				for(int i = 0 ; i < listSize ; i++) {
					
					temp.add(result.get(i));
					
					if(i != 0 && i%10 == 9) {
						subResult.add(temp);
						temp = new ArrayList<>();
					}else if(i == listSize-1) {
						subResult.add(temp);
						temp = new ArrayList<>();
					}
				}
				

				String strPAGENUMBER = (String)request.getParameter("PAGENUMBER");
				int PAGENUMBER = 0;
				if(strPAGENUMBER == null) {
					PAGENUMBER = 0;
				} else {
					PAGENUMBER = Integer.parseInt(strPAGENUMBER);
				}
							
				request.setAttribute("SimpleRecipeList", subResult.get(PAGENUMBER));
				
				
				
				//	subResult.add(result.subList(0, 10));
				
				// --------------- //
				
				HashMap<Long,Integer> ccMap = new HashMap<>();

				for(SimpleRecipe sr : result) {
					SimpleRecipe model = new SimpleRecipe();
					Integer recipe_id = sr.getRecipe_id();
					model.setRecipe_id(recipe_id);
					Integer commentSize = (Integer)commentDAO.selectCount(conn, model);
					ccMap.put(recipe_id.longValue(), commentSize);
				}
				request.setAttribute("ccMap", ccMap);
				request.setAttribute("whatMethod", "POST2");
			} catch(Exception e){
				e.printStackTrace();
			}
		} else if( level.equals("level3") ) {
			// 고급요리 눌렀을때
			try(Connection conn = ConnectionProvider.getConnection()) {
				SimpleRecipe simpleRecipe = new SimpleRecipe();
				simpleRecipe.setCategory("advanced");
				ArrayList<SimpleRecipe> result = simpleRecipeDAO.selectFromCategory(conn, simpleRecipe);
				request.setAttribute("SimpleRecipeList", result);
				
				/*     페이지 기능          */
				ArrayList<ArrayList<SimpleRecipe>> subResult = new ArrayList<ArrayList<SimpleRecipe>>();
				int listSize = result.size();
				int pageSize = (listSize / 10);
				if( pageSize % 10 > 0 )
					pageSize++;
				request.setAttribute("pageSize",pageSize);
				
				ArrayList<SimpleRecipe> temp = new ArrayList<>();
				for(int i = 0 ; i < listSize ; i++) {
					
					temp.add(result.get(i));
					
					if(i != 0 && i%10 == 9) {
						subResult.add(temp);
						temp = new ArrayList<>();
					}else if(i == listSize-1) {
						subResult.add(temp);
						temp = new ArrayList<>();
					}
				}
				

				String strPAGENUMBER = (String)request.getParameter("PAGENUMBER");
				int PAGENUMBER = 0;
				if(strPAGENUMBER == null) {
					PAGENUMBER = 0;
				} else {
					PAGENUMBER = Integer.parseInt(strPAGENUMBER);
				}
							
				request.setAttribute("SimpleRecipeList", subResult.get(PAGENUMBER));
				
				
				
				//	subResult.add(result.subList(0, 10));
				
				// --------------- //
				
				HashMap<Long,Integer> ccMap = new HashMap<>();

				for(SimpleRecipe sr : result) {
					SimpleRecipe model = new SimpleRecipe();
					Integer recipe_id = sr.getRecipe_id();
					model.setRecipe_id(recipe_id);
					Integer commentSize = (Integer)commentDAO.selectCount(conn, model);
					ccMap.put(recipe_id.longValue(), commentSize);
				}
				request.setAttribute("ccMap", ccMap);
				request.setAttribute("whatMethod", "POST3");
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		return formPage;
	}
}