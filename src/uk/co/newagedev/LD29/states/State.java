package uk.co.newagedev.LD29.states;

import java.awt.Color;

import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.maps.Map;

public class State {

	private Color bg;
	private Map map;
	
	public State(Color bg, Map map) {
		this.bg = bg;
		this.map = map;
	}
	
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public void render() {
		Main.getScreen().fillScreen(bg);
	}
	
	public void update() {
		
	}
	
}
