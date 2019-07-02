package tje.dao;

import tje.jdbc.util.Closer;
import tje.model.*;
import java.sql.*;
import java.util.ArrayList;

public class SimpleRecipeDAO {
	private SimpleRecipe getInstance(ResultSet rs) throws SQLException {		
		SimpleRecipe obj = new SimpleRecipe(
				rs.getInt("recipe_id"),
				rs.getString("member_id"),
				rs.getString("category"),
				rs.getString("title"),
				rs.getString("nickname"),
				rs.getTimestamp("write_time"),
				rs.getInt("read_count"),
				rs.getInt("like_count"));
		return obj;
	}

	// beginner 카테고리에서 좋아요 많이 받은 게시물들
	public ArrayList<SimpleRecipe> selectBeginnerBest(Connection conn) {
		ArrayList<SimpleRecipe> result = new ArrayList<>();
		String sql = "select * from simpleRecipe where category = 'beginner' and like_count = (select max(like_count) from simpleRecipe where category = 'beginner')";
		
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
	
	// advanced 카테고리에서 좋아요 많이 받은 게시물들
	public ArrayList<SimpleRecipe> selectAdvancedBest(Connection conn) {
		ArrayList<SimpleRecipe> result = new ArrayList<>();
		String sql = "select * from simpleRecipe where category = 'advanced' and like_count = (select max(like_count) from simpleRecipe where category = 'advanced')";
		
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
	
	// intermediate 카테고리에서 좋아요 많이 받은 게시물들
	public ArrayList<SimpleRecipe> selectIntermediateBest(Connection conn) {
		ArrayList<SimpleRecipe> result = new ArrayList<>();
		String sql = "select * from simpleRecipe where category = 'intermediate' and like_count = (select max(like_count) from simpleRecipe where category = 'intermediate')";
		
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
	
	// 내가 좋아요한 게시물 쿼리
	public ArrayList<SimpleRecipe> selectLikeList(Connection conn,SimpleRecipe model) {
		ArrayList<SimpleRecipe> result = new ArrayList<>();
		String sql = "select * from simpleRecipe where recipe_id in (select recipe_id from thumbsup where member_id = ?) order by write_time desc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, model.getMember_id());
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
	
	public SimpleRecipe selectOne(Connection conn, Recipe model) {
		SimpleRecipe result = null;
		String sql = "select * from simplerecipe where recipe_id = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, model.getRecipe_id());
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				result = getInstance(rs);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	
	public ArrayList<SimpleRecipe> selectAll(Connection conn) {
		ArrayList<SimpleRecipe> result = new ArrayList<>();
		String sql = "select * from simpleRecipe order by write_time desc";
		
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
	
	
	public ArrayList<SimpleRecipe> selectFromMember_id(Connection conn, SimpleRecipe obj) {
		ArrayList<SimpleRecipe> result = new ArrayList<>();
		String sql = "select * from simpleRecipe where member_id = ? order by write_time desc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);						
			pstmt.setString(1, obj.getMember_id());
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
	
	public ArrayList<SimpleRecipe> selectFromCategory(Connection conn, SimpleRecipe obj) {
		ArrayList<SimpleRecipe> result = new ArrayList<>();
		String sql = "select * from simpleRecipe where category = ? order by write_time desc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);						
			pstmt.setString(1, obj.getCategory());
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
	
	public boolean plusRead_count(Connection conn, Recipe model) {
		Boolean result = null;
		String sql = "update recipe set read_count = read_count + 1 where recipe_id = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, model.getRecipe_id());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		return result;
	}
	
}
