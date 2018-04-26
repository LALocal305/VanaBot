package org.botCreators.VanaBot.Utility;

public class CustomEmote implements Comparable<CustomEmote>{

	private String id;
	private String name;
	
	
	public CustomEmote(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(CustomEmote o) {
		return this.getName().compareTo(o.getName());
	}
	
	
}
