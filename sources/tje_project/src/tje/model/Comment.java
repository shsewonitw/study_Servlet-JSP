package tje.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Comment {
	private int comment_id;
	private int recipe_id;
	private String member_id;
	private String content;
	private Date write_time;
	
	
		// Local Time -> UTC/GMT Time  
		public static long convertLocalTimeToUTC(long pv_localDateTime)  
		{  
		    long lv_UTCTime = pv_localDateTime;  
		      
		    TimeZone z = TimeZone.getDefault();  
		    //int offset = z.getRawOffset(); // The offset not includes daylight savings time  
		    int offset = z.getOffset(pv_localDateTime); // The offset includes daylight savings time  
		    lv_UTCTime = pv_localDateTime - offset;  
		    return lv_UTCTime;  
		}  
		  
		// UTC/GMT Time -> Local Time  
		public static long convertUTCToLocalTime(long pv_UTCDateTime)  
		{  
		    long lv_localDateTime = pv_UTCDateTime;  
		      
		    TimeZone z = TimeZone.getDefault();  
		    //int offset = z.getRawOffset(); // The offset not includes daylight savings time  
		    int offset = z.getOffset(pv_UTCDateTime); // The offset includes daylight savings time  
		      
		    lv_localDateTime = pv_UTCDateTime + offset;  
		      
		    return lv_localDateTime;  
		}  
	
	
	public Comment() {
	}

	

	public Comment(int comment_id, int recipe_id, String member_id, String content, Date write_time) {
		super();
		this.comment_id = comment_id;
		this.recipe_id = recipe_id;
		this.member_id = member_id;
		this.content = content;
		this.write_time = write_time;
	}



	public String getMember_id() {
		return member_id;
	}



	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}



	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getWrite_timeLong() {
		Long pv_localDateTime = write_time.getTime();
		return convertLocalTimeToUTC(pv_localDateTime);
	}
	
	public String getWrite_timeString() {
		SimpleDateFormat sdf = 
				new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");		
		return sdf.format(write_time);
	}
	
	public Date getWrite_time() {
		return write_time;
	}

	public void setWrite_time(Date write_time) {
		this.write_time = write_time;
	}
	
	
	
}
