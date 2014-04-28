package uk.co.newagedev.LD29.states;

import java.awt.Color;

import uk.co.newagedev.LD29.maps.Map;
import uk.co.newagedev.LD29.tiles.Tile;
import uk.co.newagedev.LD29.tiles.TileSpawner;

public class GameState extends State {

	private boolean spawned;

	public GameState() {
		super(new Color(20, 20, 20), Map.HOSPITAL);
	}

	public void render() {
		super.render();
		getMap().render();
	}

	public void update() {
		if (!spawned) {
			spawned = true;
			for (Tile tile : getMap().getTiles()) {
				if (tile instanceof TileSpawner) {
					TileSpawner t = (TileSpawner) tile;
					t.spawn(3);
				}
			}
		}
		getMap().update();
	}
}
