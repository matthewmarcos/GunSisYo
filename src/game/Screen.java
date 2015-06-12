package game;

import java.util.*;

import level.*;
import sprite.*;

public class Screen {

	private final int MAP_SIZE = 64;
	private final int MAP_SIZE_MASK = MAP_SIZE - 1;
	private int width, height;
	public int[] pixels, tiles;
	// private Random random = new Random();
	public int xOffset, yOffset;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		tiles = new int[MAP_SIZE * MAP_SIZE];

		// random = new Random();

		// for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
		// // Randomizes any color from black to white
		// tiles[i] = random.nextInt(0xFFFFFF);
		// }
	}

	public void render(int yOffset, int xOffset) {
		for (int y = 0; y < height; y++) {
			int yp = y - yOffset;
			if (yp < 0 || yp >= height) continue;
			for (int x = 0; x < width; x++) {
				int xp = x - xOffset;
				if (xp < 0 || xp >= width) continue;
				pixels[xp + yp * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];
			}
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderTile(int xp, int yp, Tile tile) {
		/*
		 * Renders only what you can see inside the screen -Saves resources
		 */
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			// For absolute positioning
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) break;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset; this.yOffset = yOffset;
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}
}
