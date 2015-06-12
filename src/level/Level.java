package level;

import game.*;

public class Level {

	protected int width, height;
	protected int[] tiles;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
	}

	public void update() {
		/*
		 * Update the movement of the entities Update everything @ 60UP/s gahh!
		 */

	}

	private void time() {
		// Modifies the color of the tiles based on the in-game time
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		int x0 = xScroll >> 4, x1 = (xScroll + screen.width()) >> 4;
		int y0 = yScroll >> 4, y1 = (yScroll + screen.height()) >> 4;

	}

	protected Tile getTile(int x, int y) {
		switch (tiles[x + y * width]) {
		case 0:
			return Tile.grass;
		}

		return Tile.tileVoid;
	}

	protected void loadLevel(String path) {

	}

	protected void generateLevel() {
	}

}
