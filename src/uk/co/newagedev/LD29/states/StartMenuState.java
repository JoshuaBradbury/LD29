package uk.co.newagedev.LD29.states;

import java.awt.Font;

import uk.co.newagedev.LD29.Main;

public class StartMenuState extends MenuState {

	public StartMenuState() {
		super(new Font("Tahoma", Font.BOLD, 25));
		addOption("Play");
		addOption("Story");
		addOption("Options");
		addOption("Exit");
	}
	
	public void update() {
		super.update();
		switch (getOptionPressed()) {
		case "Play":
			Main.getScreen().setState(new GameState());
			break;
		case "Exit":
			System.exit(0);
			break;
		case "Story":
			break;
		case "Options":
			break;
		}
	}
}
