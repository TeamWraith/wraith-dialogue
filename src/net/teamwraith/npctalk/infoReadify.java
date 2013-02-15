package net.teamwraith.npctalk;

public class infoReadify {


	public void pull(InfoReadable info){
		infoReadify.set(info);
		System.out.println(info.getActorName() + " is my name.");
		System.out.println("I'm a " + info.getSex().toLowerCase());
		System.out.println("I'm also a " + info.getType().toLowerCase());
	}

	public static void set(InfoReadable info){
		info.setActorName("Ghat");
		info.setSex(2);
		info.setType(1);
	}
}
