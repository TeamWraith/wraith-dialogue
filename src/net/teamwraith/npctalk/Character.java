package net.teamwraith.npctalk;

/**
 * Represents a character that would appear in
 * dialogue. 
 */
public class Character {
		
	/**
	 * A character's type, that is, whether it's 
	 * a main character or a minor character.
	 */
	public enum Type {
		MAIN, 
		MINOR, 
		OTHER
	}
	
	public enum Sex {
		MALE, 
		FEMALE, 
		OTHER
	}
	
	/**
	 * The character's species. Defaults to other,
	 * which means it's undefined.
	 * 
	 * These are the species of <i>Lands of Loriana</i>.
	 * Later on, these will be modifiable, but for now
	 * they'll remain specific.
	 */
	public enum Species {
		DWARF 		("Dwarf"),
		ELF 		("Elf"),
		HUMAN 		("Human"),
		NORD 		("Nord"),
		/**
		 * The Rhaz Túk are the main antagonists 
		 * of <i>Lands of Loriana</i>.
		 * 
		 * @param name - Returns a print-friendly
		 * version of the name (that is, with the
		 * <i>ú</i>).
		 */
		RHAZ_TUK 	("Rhaz Túk"),
		/**
		 * This is what a character's species 
		 * defaults to. If it's not set, it
		 * probably doesn't matter.
		 */
		OTHER		("undefined");
		
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
		TRUE_NEUTRAL 	("True Neutral", 	
			"True neutral, the alignment of nature."),
		CHAOTIC_NEUTRAL ("Chaotic Neutral",	"?"),
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
	private String name = "???";
	private Sex sex = Sex.OTHER;
	private Type type = Type.OTHER;
	private Species species = Species.OTHER;
	private Morality morality = Morality.TRUE_NEUTRAL;
	
	// --- Constructors:
	public Character(String name, Sex sex, Species species, Type type, Morality morality) {
		this (name, sex, species, type);
		this.morality = morality;
	}
	
	public Character(String name, Sex sex, Species species, Type type) {
		this (name, sex, species);
		this.type = type;
	}
	
	/**
	 * The bare minimum of info.
	 */
	public Character(String name, Sex sex, Species species) {
		this.name = name;
		this.sex = sex;
		this.species = species;
	}

	@Override
	public String toString() {
		return name;
	}

	// --- Getters:
	public String getName(){
		return name;
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
	public void setName(String name){
		this.name = name;
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
