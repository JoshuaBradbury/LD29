package uk.co.newagedev.LD29.tiles;

import java.util.ArrayList;

import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.entities.Entity;
import uk.co.newagedev.LD29.entities.EntityPlayer;
import uk.co.newagedev.LD29.graphics.SpriteSheet;
import uk.co.newagedev.LD29.states.GameState;

public class TileElevator extends Tile {

	public TileElevator() {
		super(SpriteSheet.HOSPITAL_TILES.getSprite(3), TileType.ELEVATOR, true);
	}
	
	public void update() {
		ArrayList<Entity> entitiesInRange = ((GameState) (Main.getScreen().getCurrentState())).getMap().getEntitiesInRange(getLocation(), 96);
		for (Entity entity : entitiesInRange) {
			if (entity instanceof EntityPlayer) {
				if (((EntityPlayer) entity).getJumping() > 0) {
					((EntityPlayer) entity).setJumping(100);
				}
			}
		}
	}
}
