package tje.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Recipe {
	private int recipe_id;
	private String member_id;
	private String category;
	private String title;
	private String content;
	private Date write_time;
	private Date last_update_time;
	private int read_count;
	private int like_count;

	
	public Recipe() {
	}

	public Recipe(int recipe_id, String member_id, String category, String title, String content, Date write_time,
			Date last_update_time, int read_count, int like_count) {
		this.recipe_id = recipe_id;
		this.member_id = member_id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.write_time = write_time;
		this.last_update_time = last_update_time;
		this.read_count = read_count;
		this.like_count = like_count;
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getLast_update_timeString() {
		SimpleDateFormat sdf = 
				new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		return sdf.format(last_update_time);
	}
	
	public Date getLast_update_time() {
		return last_update_time;
	}

	public void setLast_update_time(Date last_update_time) {
		this.last_update_time = last_update_time;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	
	
	
}
