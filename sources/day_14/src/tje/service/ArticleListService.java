package tje.service;

import java.sql.*;
import tje.model.*;
import java.util.*;

import tje.dao.*;


public class ArticleListService implements Service {
	private SimpleArticleDAO simpleArticleDAO = new SimpleArticleDAO();
	
	@Override
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String,Object> result = new HashMap<String, Object>();
		Connection conn = (Connection)values.get("conn");
		
		result.put("articleList", 
				simpleArticleDAO.selectAll(conn));
		
	
		return result;
	}
}