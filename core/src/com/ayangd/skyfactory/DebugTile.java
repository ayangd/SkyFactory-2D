package com.ayangd.skyfactory;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class DebugTile extends Tile {

	public DebugTile(int x, int y) {
		super(x, y);
		Pixmap pixmap = new Pixmap(size, size, Pixmap.Format.RGBA8888);
		pixmap.setColor(0, 0, 1f, 1f);;
		pixmap.fillRectangle(0, 0, size, size);
		texture = new Texture(pixmap);
	}

}
