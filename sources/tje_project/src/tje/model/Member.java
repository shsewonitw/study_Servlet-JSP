package tje.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Member {
	private String member_id;
	private String password;
	private String nickname;
	private String email;
	private Date regist_date;
	private Date last_access_time;
	
	
	
	public Member() {
	}

	public Member(String member_id, String password, String nickname, String email, Date regist_date,
			Date last_access_time) {
		this.member_id = member_id;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.regist_date = regist_date;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegist_dateString() {
		SimpleDateFormat sdf = 
				new SimpleDateFormat("yyyy년 MM월 dd일");		
		return sdf.format(regist_date);
	}
	
	public Date getRegist_date() {
		return regist_date;
	}

	public void setRegist_date(Date regist_date) {
		this.regist_date = regist_date;
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
