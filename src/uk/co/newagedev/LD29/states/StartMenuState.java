package uk.co.newagedev.LD29.states;

import java.awt.Color;
import java.awt.Font;

import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.tiles.Tile;
import uk.co.newagedev.LD29.tiles.TileSpawner;

public class StartMenuState extends MenuState {

	private int count = 0;
	private boolean spawned;
	
	public StartMenuState() {
		super(new Font("Tahoma", Font.BOLD, 25), 200);
		addOption("Play");
		addOption("Exit");
		getMap().setScrollX((Main.WIDTH / 2) - 600);
		getMap().setScrollY((Main.HEIGHT / 2) - 2500);
	}
	
	public void render() {
		getMap().setWin(false);
		super.render();
		Main.getScreen().displayText(187, 100, 75, "Virtual DJ", new Color(0, 128, 0), "HIERANARCHY");
	}
	
	public void update() {
		super.update();
		if (!spawned) {
			spawned = true;
			for (Tile tile : getMap().getTiles()) {
				if (tile instanceof TileSpawner) {
					TileSpawner t = (TileSpawner) tile;
					t.spawn(3);
				}
			}
		}
		count++;
		if (count > 0 && count < 300) {
			getMap().setScrollX(getMap().getScrollX() - 4);
		} else if (count >= 300 && count < 450) {
			getMap().setScrollX(getMap().getScrollX() - 4);
			getMap().setScrollY(getMap().getScrollY() + 4);
		} else if (count >= 450 && count < 750) {
			getMap().setScrollX(getMap().getScrollX() + 4);
		} else if (count >= 750 && count < 900) {
			getMap().setScrollX(getMap().getScrollX() + 4);
			getMap().setScrollY(getMap().getScrollY() - 4);
		} else {
			count = 1;
		}
		switch (getOptionPressed()) {
		case "Play":
			Main.getScreen().setState(new GameState());
			getMap().clearEntities();
			break;
		case "Exit":
			System.exit(0);
			break;
		}
	}
}
