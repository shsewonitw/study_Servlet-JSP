package tje.service;

import java.sql.*;
import java.util.*;
import tje.dao.*;
import tje.model.*;

public class MemberSelectService implements Service {
	private MemberDAO memberDAO = new MemberDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		Member member = (Member)values.get("member");
		
		if( member.getMember_id() != null ) {
			result.put("result", 
					memberDAO.selectOneWhereId(conn, member));
		} else if ( member.getNickname() != null ) {
			result.put("result", 
					memberDAO.selectOneWhereNickName(conn, member));
		} else if ( member.getEmail() != null ) {
			result.put("result", 
					memberDAO.selectOneWhereEmail(conn, member));
		}
	
		
		return result;
	}
}







