package uk.co.newagedev.LD29;

import javax.swing.JFrame;

import uk.co.newagedev.LD29.graphics.Screen;
import uk.co.newagedev.LD29.input.Mouse;
import uk.co.newagedev.LD29.input.Keyboard;
import uk.co.newagedev.LD29.states.StartMenuState;
import uk.co.newagedev.LD29.input.KeyBinding;

public class Main implements Runnable {

	private static Screen screen;
	private static JFrame frame;
	public static final int WIDTH = 1080, HEIGHT = 720, MAX_UPS = 60;
	private Thread thread;
	private boolean running;
	public static final String title = "LD29 - Hieranarchy";
	private int fps, ups;
	public static Keyboard keyboard;
	public static Mouse mouse;

	private void init() {
		keyboard = new Keyboard();
		mouse = new Mouse();
		getScreen().addKeyListener(keyboard);
		getScreen().addMouseListener(mouse);
		getScreen().addMouseMotionListener(mouse);
	}

	public synchronized void start() {
		thread = new Thread(this, title);
		running = true;
		init();
		getScreen().setState(new StartMenuState());
		run();
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		frame = new JFrame();
		Main main = new Main();
		main.setScreen(new Screen());
		frame.setTitle(title);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(getScreen());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();

		main.start();
	}

	boolean gameUpdate = false;

	public void run() {
		long lastTime = System.nanoTime();
		long secondTime = System.currentTimeMillis();
		final double ns = 1000000000 / MAX_UPS;
		double delta = 0;
		while (running) {
			screen.render();
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				ups++;
				delta--;
			}
			fps++;
			if (System.currentTimeMillis() - secondTime >= 1000) {
				frame.setTitle(title + "   FPS: " + fps + " UPS: " + ups);
				secondTime += 1000;
				fps = 0;
				ups = 0;
			}
		}
	}

	public void update() {
		getScreen().getCurrentState().update();
		for (KeyBinding binding : KeyBinding.keyBindings) {
			binding.update();
		}
		keyboard.update();
		mouse.update();
	}

	public static Screen getScreen() {
		return screen;
	}

	private void setScreen(Screen scr) {
		screen = scr;
	}

}
