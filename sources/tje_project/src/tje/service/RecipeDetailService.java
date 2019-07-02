package tje.service;

import java.sql.*;
import java.util.*;
import tje.dao.*;
import tje.model.*;

public class RecipeDetailService implements Service {
	private RecipeDAO recipeDAO = new RecipeDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		Recipe model = (Recipe)values.get("model");
				
	
		result.put("result", 
				recipeDAO.selectOne(conn,model));
		
		return result;
	}
}