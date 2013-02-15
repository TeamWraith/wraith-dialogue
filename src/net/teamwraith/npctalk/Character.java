package net.teamwraith.npctalk;

public class Character {
		
	public enum Type {
		MAIN, 
		MINOR, 
		UNDEFINED
	}
	
	public enum Sex {
		MALE, 
		FEMALE, 
		OTHER
	}
	
	public enum Species {
		DWARF 		("Dwarf"),
		ELF 		("Elf"),
		HUMAN 		("Human"),
		NORD 		("Nord"),
		RHAZ_TUK 	("Rhaz Túk"),
		OTHER 		("???");
		
		private final String name;
		
		Species (String name) {
			this.name = name;
		}
		
		/**
		 * Gets a printable version of the
		 * species' name. 
		 */
		public String getSpeciesName() {
			return name;
		}
	}
	
	// maybe add descriptions?
	public enum Morality {
		LAWFUL_GOOD 	("Lawful Good",		"?"),
		NEUTRAL_GOOD 	("Neutral Good", 	"?"),
		CHAOTIC_GOOD 	("Chaotic Good", 	"?"),
		LAWFUL_NEUTRAL 	("Lawful Neutral", 	"?"),
		TRUE_NEUTRAL 	("True Neutral", 	"?"),
		CHAOTIC_NEUTRAL ("Chaotic Neutral", "?"),
		LAWFUL_EVIL 	("Lawful Evil", 	"?"),
		NEUTRAL_EVIL 	("Neutral Evil", 	"?"),
		CHAOTIC_EVIL 	("Chaotic Evil", 	"?");
		
		private final String name;
		private final String description;
		
		Morality (String name, String description) {
			this.name = name;
			this.description = description;
		}
		
		public String getMoralityName() {
			return name;
		}
		
		public String getDescription() {
			return description;
		}
	}
	
	// --- Private: 
	private String actorName = "undefined!";
	private Sex sex = Sex.OTHER;
	private Type type = Type.UNDEFINED;
	private Species species = Species.OTHER;
	private Morality morality = Morality.TRUE_NEUTRAL;
	
	

	// --- Getters:
	public String getActorName(){
		return actorName;
	}
	
	public Sex getSex(){
		return sex;
	}
	
	public Type getType() {
		return type;
	}

	public Species getSpecies() {
		return species;
	}
	
	public Morality getMorality() {
		return morality;
	}
	
	// --- Setters:
	public void setActorName(String actorName){
		this.actorName = actorName;
	}
	
	public void setSex(Sex sex){
		this.sex = sex;
	}
	
	public void setType(Type type){
		this.type = type;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}
	
	public void setMorality(Morality morality) {
		this.morality = morality;
	}
}
