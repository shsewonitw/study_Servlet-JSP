package tje.service;

import java.sql.*;
import java.util.*;
import tje.dao.*;
import tje.model.*;

public class ArticleFiveService implements Service {
	private SimpleArticleDAO simpleArticleDAO = new SimpleArticleDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		SimpleArticle model = (SimpleArticle)values.get("model");
		result.put("articleFive",
				simpleArticleDAO.selectFive(conn, model));
		
		return result;
	}
}







