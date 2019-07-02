package project.sevice;

import java.sql.*;
import java.util.*;
import project.dao.*;
import project.model.*;

public class ArticleListService implements Service {
	private SimpleArticleDAO simpleArticleDAO = new SimpleArticleDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		
		result.put("articleList", 
				simpleArticleDAO.selectAll(conn));
		
		return result;
	}
}







