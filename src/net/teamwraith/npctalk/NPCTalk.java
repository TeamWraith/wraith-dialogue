package net.teamwraith.npctalk;

import net.teamwraith.npctalk.Character.Sex;
import net.teamwraith.npctalk.Character.Species;
import net.teamwraith.npctalk.gui.GUIListener;

public class NPCTalk {

	public static void main(String[] args) {
		GUIListener app = new GUIListener();
		
		Character test = new Character("Ghat", Sex.MALE, Species.HUMAN);
	}
}
