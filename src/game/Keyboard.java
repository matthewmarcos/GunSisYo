package game;

import java.awt.event.*;

public class Keyboard implements KeyListener {

	private boolean[] keys;
	public boolean up, down, left, right, sprint;

	public Keyboard() {
		keys = new boolean[120];
	}

	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		sprint = keys[KeyEvent.VK_SHIFT];

		// for(int i=0; i<keys.length ; i++) {
		// if(keys[i]) {
		// System.out.println(i);
		// }
		// }
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
