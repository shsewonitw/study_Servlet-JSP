package tje.service;

import java.sql.*;
import java.util.*;
import tje.dao.*;
import tje.model.*;

public class RecipeListService implements Service {
	private SimpleRecipeDAO simpleRecipeDAO = new SimpleRecipeDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		
				
		result.put("result", 
				simpleRecipeDAO.selectAll(conn));
		
		return result;
	}
}