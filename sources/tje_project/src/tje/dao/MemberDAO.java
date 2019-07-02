package tje.dao;

import tje.jdbc.util.Closer;
import tje.model.*;
import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {
	private Member getInstance(ResultSet rs) throws SQLException {		
		Member obj = new Member(
				rs.getString("member_id"),
				rs.getString("password"),
				rs.getString("nickname"),
				rs.getString("email"),
				rs.getTimestamp("regist_date"),
				rs.getTimestamp("last_access_time"));
		return obj;
	}
	
	
	public Member selectOneWhereId(Connection conn, Member obj) {
		Member result = null;
		String sql = "select * from member where member_id = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getMember_id());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = getInstance(rs);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	public Member selectOneWhereNickName(Connection conn, Member obj) {
		Member result = null;
		String sql = "select * from member where nickname = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getNickname());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = getInstance(rs);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	public Member selectOneWhereEmail(Connection conn, Member obj) {
		Member result = null;
		String sql = "select * from member where email = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getEmail());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = getInstance(rs);
			}
		} catch(Exception e) {
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
