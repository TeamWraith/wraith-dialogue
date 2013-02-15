package net.teamwraith.npctalk;

public class infoReadify {


	public void pull(InfoReadable info){
		infoReadify.set(info);
		System.out.println(info.getActorName() + " is my name.");
		System.out.println("I'm a " + info.getSex().toLowerCase());
		System.out.println("I'm also a " + info.getType().toLowerCase());
		System.out.println("I'm of the race " + info.getRace().toLowerCase());
		System.out.println("I'm " + info.getAge() + " years old :3");
	}

	public static void set(InfoReadable info){
		info.setActorName("Ghat");
		info.setSex(2);
		info.setType(1);
		info.setRace("Owl");
		info.setAge(32);
	}
}
