package tje.dao;

import tje.jdbc.util.Closer;
import tje.jdbc.util.ConnectionProvider;
import tje.model.*;
import java.sql.*;
import java.util.*;

public class CommentDAO {
	private Comment getInstance(ResultSet rs) throws SQLException {		
		Comment obj = new Comment(
				rs.getInt("comment_id"),
				rs.getInt("recipe_id"),
				rs.getString("member_id"),
				rs.getString("content"),
				rs.getTimestamp("write_time")
				);
		return obj;
	}
	
	public int selectCount(Connection conn, SimpleRecipe obj) {
		int result = 0;
		String sql = "select count(*) from comment where recipe_id = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getRecipe_id());
			
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
	
	public ArrayList<Comment> selectWhereRecipeId(Connection conn, Comment obj){
		ArrayList<Comment> commentList = new ArrayList<>();
		String sql = "select * from comment where recipe_id = ? order by write_time desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getRecipe_id());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				commentList.add(getInstance(rs));
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		Closer.close(rs);
		Closer.close(pstmt);
		
		return commentList;
	}
	
	public boolean insert(Connection conn, Comment obj) {
		boolean result = false;
		String sql = "insert into Comment values (null,?,?,?,now())";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getRecipe_id());
			pstmt.setString(2, obj.getMember_id());
			pstmt.setString(3, obj.getContent());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		return result;
	}
	
	
	public boolean delete(Connection conn, Comment obj) {
		boolean result = false;
		String sql = "delete from comment where comment_id = ? and member_id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getComment_id());
			pstmt.setString(2, obj.getMember_id());
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
