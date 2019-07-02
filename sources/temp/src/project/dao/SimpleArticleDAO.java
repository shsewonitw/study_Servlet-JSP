package project.dao;

import java.sql.*;
import java.util.*;

import project.jdbc.util.*;
import project.model.*;
import project.jdbc.util.Closer;

public class SimpleArticleDAO {
	private SimpleArticle getInstance(ResultSet rs) throws SQLException {		
		SimpleArticle obj = new SimpleArticle(
				rs.getInt("article_id"),
				rs.getString("title"),
				rs.getString("member_id"),
				rs.getString("name"),				
				rs.getTimestamp("write_time"),
				rs.getInt("read_count"),
				rs.getInt("comment_count")
				);				
		return obj;
	}
			
	public int selectAllCount(Connection conn) {
		int result = 0;
		String sql = "select count(*) from simpleArticle";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	public ArrayList<SimpleArticle> selectAll(Connection conn) {
		ArrayList<SimpleArticle> result = new ArrayList<>();
		String sql = "select * from simpleArticle";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			while( rs.next() )
				result.add(getInstance(rs));
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	public ArrayList<SimpleArticle> select(Connection conn, String searchItem, String searchValue) {
		ArrayList<SimpleArticle> result = new ArrayList<>();
		String sql = String.format("select * from simpleArticle where %s like ?", searchItem);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, "%" + searchValue + "%");
			
			rs = pstmt.executeQuery();
			
			while( rs.next() )
				result.add(getInstance(rs));
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
}
