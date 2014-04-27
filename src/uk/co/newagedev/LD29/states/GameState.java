package uk.co.newagedev.LD29.states;

import java.awt.Color;

import uk.co.newagedev.LD29.maps.Map;

public class GameState extends State {

	private Map map;

	public GameState() {
		super(Color.BLACK);
		map = Map.HOSPITAL;
	}

	public void render() {
		super.render();
		map.render();
	}
	
	public Map getMap() {
		return map;
	}

	public void update() {
		map.update();
	}
}
