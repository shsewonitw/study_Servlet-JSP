package tje.dao;

import tje.jdbc.util.Closer;
import tje.model.*;
import java.sql.*;
import java.util.ArrayList;

public class ThumbsUpDAO {
	private ThumbsUp getInstance(ResultSet rs) throws SQLException {		
		ThumbsUp obj = new ThumbsUp(
				rs.getInt("recipe_id"),
				rs.getString("member_id"));
		return obj;
	}
	
	
	public boolean dupleCheck(Connection conn, ThumbsUp obj) {
		boolean result = false;
		String sql = "select count(*) from thumbsup where recipe_id = ? and member_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getRecipe_id());
			pstmt.setString(2, obj.getMember_id());
			
			rs = pstmt.executeQuery();
				
			if(rs.next()) {
				result = rs.getInt(1) == 1 ? true : false;
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	public boolean insert(Connection conn, ThumbsUp obj) {
		boolean result = false;
		String sql = "insert into thumbsUp values (?,?)";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getRecipe_id());
			pstmt.setString(2, obj.getMember_id());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		return result;
	}
	
	public boolean delete(Connection conn, ThumbsUp obj) {
		boolean result = false;
		String sql = "delete from thumbsUp where recipe_id = ? and member_id = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getRecipe_id());
			pstmt.setString(2, obj.getMember_id());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		return result;
	}
	
	public int selectCountWhereRecipeId(Connection conn, ThumbsUp obj) {
		int result = 0;
		String sql = "select count(*) from thumbsup where recipe_id = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getRecipe_id());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	


	
	public boolean insert(Connection conn, Member obj) {
		boolean result = false;
		String sql = "insert into member values (?,?,?,?,now(),null)";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getMember_id());
			pstmt.setString(2, obj.getPassword());
			pstmt.setString(3, obj.getNickname());
			pstmt.setString(4, obj.getEmail());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		return result;
	}
	
	
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
