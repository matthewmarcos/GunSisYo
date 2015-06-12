package level;

import sprite.*;
import game.*;

public class TileGrass extends Tile {

	public TileGrass(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		// Do rendering here!
		screen.renderTile(x, y, this);

	}
}
