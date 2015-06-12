package level;

import java.util.*;

public class RandomLevel extends Level {

	Random random = new Random();

	public RandomLevel(int width, int height) {
		super(width, height);
	}

	public RandomLevel(String path) {
		super(path);
	}

	@Override
	protected void generateLevel() {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				tiles[y + x * width] = random.nextInt(4);
			}
		}
	}
}
