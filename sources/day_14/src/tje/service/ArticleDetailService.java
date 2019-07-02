package tje.service;

import java.sql.*;
import tje.model.*;
import java.util.*;

import tje.dao.*;


public class ArticleDetailService implements Service {
	private DetailArticleDAO detailArticleDAO = new DetailArticleDAO();
	
	@Override
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String,Object> result = new HashMap<String, Object>();
		Connection conn = (Connection)values.get("conn");
		
		
		
		result.put("result1", 
				detailArticleDAO.selectOne(conn, (DetailArticle)values.get("detailArticle")));
		
		result.put("result2",
				detailArticleDAO.updateRead_count(conn, (DetailArticle)values.get("detailArticle")));
		return result;
	}
}