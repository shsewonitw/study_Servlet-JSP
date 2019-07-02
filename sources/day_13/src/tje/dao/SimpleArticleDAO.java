package tje.dao;

import java.sql.*;
import java.util.*;
import tje.jdbc.util.*;
import tje.model.*;
public class SimpleArticleDAO {
	private SimpleArticle getInstance(ResultSet rs) throws SQLException {
		SimpleArticle obj = new SimpleArticle(
				rs.getInt("article_id"),
				rs.getString("title"),
				rs.getString("member_id"),
				rs.getString("name"),
				rs.getTimestamp("write_time"),
				rs.getInt("read_count"));
		return obj;
	}
	

	public ArrayList<SimpleArticle> selectAll(Connection conn){
		ArrayList<SimpleArticle> result = new ArrayList<SimpleArticle>();
		String sql = "select * from simplearticle";
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				result.add(getInstance(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Closer.close(rs);
		Closer.close(stmt);
		
		return result;
	}
}
