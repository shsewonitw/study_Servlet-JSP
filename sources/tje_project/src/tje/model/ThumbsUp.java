package tje.model;

public class ThumbsUp {
	private int recipe_id;
	private String member_id;
	
	
	
	public ThumbsUp() {
	}


	public ThumbsUp(int recipe_id, String member_id) {
		this.recipe_id = recipe_id;
		this.member_id = member_id;
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
	
	
	
}
