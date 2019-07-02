package tje.service;

import java.sql.*;
import java.util.*;
import tje.dao.*;
import tje.model.*;

public class LastInsertIdService implements Service {
	private RecipeDAO recipeDAO = new RecipeDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");

		
		result.put("result", 
				recipeDAO.last_insert_id(conn));
	
		return result;
	}
}







