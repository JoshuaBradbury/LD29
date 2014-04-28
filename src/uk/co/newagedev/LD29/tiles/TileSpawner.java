package uk.co.newagedev.LD29.tiles;

import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.entities.EntityRat;
import uk.co.newagedev.LD29.graphics.Sprite;
import uk.co.newagedev.LD29.maps.Map;

public class TileSpawner extends Tile {
	
	public TileSpawner() {
		super(Sprite.BLANK, TileType.SPAWNER);
	}
	
	public void spawn(int amount) {
		Map map =  Main.getScreen().getCurrentState().getMap();
		for (int i = 0; i < amount; i++) {
			map.addEntity(new EntityRat(getLocation().getInstance()));
		}
	}
}
