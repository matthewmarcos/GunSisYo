package level;

import game.*;
import sprite.*;

public class TileVoid extends Tile {

	public TileVoid(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		// Do rendering here!
		screen.renderTile(x, y, this);

	}
}
