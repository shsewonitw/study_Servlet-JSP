package project.dao;

import java.sql.*;
import java.util.*;

import project.jdbc.util.*;
import project.model.*;
import project.jdbc.util.Closer;
import project.model.Member;

public class MemberDAO {
	private Member getInstance(ResultSet rs) throws SQLException {		
		Member obj = new Member(
				rs.getString("member_id"),
				rs.getString("password"),
				rs.getString("name"),
				rs.getString("tel"),
				rs.getString("address"),
				rs.getTimestamp("last_access_time")
				);				
		return obj;
	}
	
	public Member selectOne(Connection conn, Member obj) {
		Member result = null;
		String sql = "select * from member where member_id = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getMember_id());
			
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				result = getInstance(rs);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		return result;
	}
	
	public ArrayList<Member> selectAll(Connection conn) {
		ArrayList<Member> result = new ArrayList<>();
		String sql = "select * from member";
		
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

	private void setPreparedStatement(int index, String value, PreparedStatement pstmt) throws SQLException  {
		if( value != null && value.length() > 0 )
			pstmt.setString(index, value);
		else
			pstmt.setNull(index, Types.NULL);
	}		
		
	public boolean insert(Connection conn, Member obj) {
		boolean result = false;
		String sql = "insert into member values (?,?,?,?,?,now())";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, obj.getMember_id());
			pstmt.setString(2, obj.getPassword());
			pstmt.setString(3, obj.getName());
					
			setPreparedStatement(4, obj.getTel(), pstmt);
			setPreparedStatement(5, obj.getAddress(), pstmt);
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
	
	public boolean updateLastAT(Connection conn, Member obj) {
		boolean result = false;
		String sql = "update member set last_access_time = now() where member_id = ?";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, obj.getMember_id());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}		
		
	public boolean updatePassword(Connection conn, Member obj) {
		boolean result = false;
		String sql = "update member set password = ? where member_id = ?";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getPassword());
			pstmt.setString(2, obj.getMember_id());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
}













