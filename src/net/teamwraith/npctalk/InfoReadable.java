package net.teamwraith.npctalk;

public class InfoReadable {
		
	// --- Private: 
	private String actorName = "NOT NAMED";
	private String sex = "NOT DEFINED";
	private String type = "NOT DEFINED";
	
	
	// --- Getters:
	public String getActorName(){
		return this.actorName;
	}
	public String getSex(){
		return this.sex;
	}
	public String getType() {
		return this.type;
	}
	
	
	// --- Setters:
	public void setActorName(String actorName){
		this.actorName = actorName;
	}
	public void setSex(int sex){
		if (sex==0)
			this.sex = "Male";
		else if (sex==1)
			this.sex = "Female";
		else
			this.sex = "Undefined sex";
	}
	public void setType(int type){
		if (type==0)
			this.type = "Main character";
		else if (type==1)
			this.type = "Minor character";
		else
			this.type = "Undefined character";
	}
}
