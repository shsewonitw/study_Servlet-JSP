package tje.service;

import java.sql.*;
import java.util.*;
import tje.dao.*;
import tje.model.*;

public class CommentUpdateService implements Service {
	private CommentDAO commentDAO = new CommentDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		
		Connection conn = (Connection)values.get("conn");
		Comment model = (Comment)values.get("model");		
		
		result.put("result", 
				commentDAO.update(conn, model));
		
		return result;
	}
}







