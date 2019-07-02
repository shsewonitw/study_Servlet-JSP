package tje.service;

import java.sql.*;
import java.util.*;
import tje.dao.*;
import tje.model.*;

public class RecipeRegistService implements Service {
	private RecipeDAO recipeDAO = new RecipeDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		Recipe recipe = (Recipe)values.get("recipe");
				
		result.put("result", 
				recipeDAO.insert(conn, recipe));
		
		return result;
	}
}