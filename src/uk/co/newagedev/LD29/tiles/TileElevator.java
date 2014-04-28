package uk.co.newagedev.LD29.tiles;

import java.util.ArrayList;

import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.entities.Entity;
import uk.co.newagedev.LD29.entities.EntityPlayer;
import uk.co.newagedev.LD29.graphics.SpriteSheet;

public class TileElevator extends Tile {

	public TileElevator() {
		super(SpriteSheet.HOSPITAL_TILES.getSprite(4), TileType.ELEVATOR, true);
	}
	
	public void update() {
		ArrayList<Entity> entitiesInRange = Main.getScreen().getCurrentState().getMap().getEntitiesInRange(getLocation(), 80);
		for (Entity entity : entitiesInRange) {
			if (entity instanceof EntityPlayer) {
				if (((EntityPlayer) entity).getJumping() > 0) {
					((EntityPlayer) entity).setJumping(70);
				}
			}
		}
	}
}
