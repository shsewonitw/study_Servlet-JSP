package tje.dao;

import tje.jdbc.util.Closer;
import tje.jdbc.util.ConnectionProvider;
import tje.model.*;
import java.sql.*;
import java.util.*;

public class RecipeDAO {
	private Recipe getInstance(ResultSet rs) throws SQLException {		
		Recipe obj = new Recipe(
				rs.getInt("recipe_id"),
				rs.getString("member_id"),
				rs.getString("category"),
				rs.getString("title"),
				rs.getString("content"),
				rs.getTimestamp("write_time"),
				rs.getTimestamp("last_update_time"),
				rs.getInt("read_count"),
				rs.getInt("like_count")
				);
		return obj;
	}
	
	
	public boolean insert(Connection conn, Recipe obj) {
		boolean result = false;
		String sql = "insert into recipe values (null,?,?,?,?,now(),now(),0,0)";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getMember_id());
			pstmt.setString(2, obj.getCategory());
			pstmt.setString(3, obj.getTitle());
			pstmt.setString(4, obj.getContent());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		return result;
	}
	
	
	public int last_insert_id(Connection conn) {
		int result=0;
		String sql = "select last_insert_id()";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() )
				result = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		return result;
	}
	
	public Recipe selectOne(Connection conn, Recipe obj) {
		Recipe result = null;
		ResultSet rs = null;
		String sql = "select * from recipe where recipe_id = ?";
		PreparedStatement pstmt = null;
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getRecipe_id());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = getInstance(rs);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		Closer.close(rs);
		return result;
	}
	
	public boolean plusLike_count(Connection conn, Recipe obj) {
		boolean result = false;
		String sql = "update Recipe set like_count = like_count + 1 where recipe_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getRecipe_id());
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		return result;
	}
	
	public boolean minusLike_count(Connection conn, Recipe obj) {
		boolean result = false;
		String sql = "update Recipe set like_count = like_count - 1 where recipe_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getRecipe_id());
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		return result;
	}
	
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!아직 수정안함
	public boolean update(Connection conn, Member obj) {
		boolean result = false;
		String sql = "update member set password = ? , nickname = ? , email = ? where member_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getPassword());
			pstmt.setString(2, obj.getNickname());
			pstmt.setString(3, obj.getEmail());
			pstmt.setString(4, obj.getMember_id());
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		return result;
	}
}
