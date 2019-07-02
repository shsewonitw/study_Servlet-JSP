package tje.service;

import java.sql.*;
import tje.model.*;
import java.util.*;

import tje.dao.*;


public class MemberRegistService implements Service {
	private MemberDAO memberDAO = new MemberDAO();
	
	@Override
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String,Object> result = new HashMap<String, Object>();
		Connection conn = (Connection)values.get("conn");
		Member member = (Member)values.get("member");
		
		
		result.put("result", 
				memberDAO.insert(conn, member));

		return result;
	}
}
