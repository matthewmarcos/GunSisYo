package level;

import sprite.*;
import game.*;

public class Tile {

	public int x, y;
	public Sprite sprite;

	public static Tile grass = new TileGrass(Sprite.grass);
	public static Tile tileVoid = new TileVoid(Sprite.voidSprite);

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {
		this.x = x;
		this.y = y;

	}

	public boolean solid() {
		return false;
	}
}
