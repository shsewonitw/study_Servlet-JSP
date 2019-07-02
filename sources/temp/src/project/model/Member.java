package project.model;

import java.util.*;
import java.text.*;

public class Member {
	private String member_id;
	private String password;
	private String name;
	private String tel;
	private String address;
	private Date last_access_time;
	
	public Member() {}
	
	public Member(String member_id, String password, String name, String tel, String address, Date last_access_time) {
		this.member_id = member_id;
		this.password = password;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.last_access_time = last_access_time;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
		public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getLast_access_timeString() {
		if( last_access_time == null )
			return "로그인 이력이 존재하지 않습니다.";
		
		SimpleDateFormat sdf = 
				new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");		
		return sdf.format(last_access_time);
	}
	
	public Date getLast_access_time() {
		return last_access_time;
	}

	public void setLast_access_time(Date last_access_time) {
		this.last_access_time = last_access_time;
	}
	
}
	