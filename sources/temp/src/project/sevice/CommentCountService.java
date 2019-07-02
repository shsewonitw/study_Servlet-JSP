package project.sevice;

import java.sql.*;
import java.util.*;
import project.dao.*;
import project.model.*;

public class CommentCountService implements Service {
	private CommentDAO commentDAO = new CommentDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		
		Connection conn = (Connection)values.get("conn");
		Comment model = (Comment)values.get("mobel");
				
		result.put("commentSize", 
				commentDAO.selectCount(conn, model));
		
		return result;
	}
}







