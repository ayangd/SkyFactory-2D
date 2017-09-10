package com.ayangd.skyfactory;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import static com.ayangd.skyfactory.SkyFactory.gridSize;

public class DebugTile extends Tile {

	public DebugTile(int x, int y) {
		super(x, y);
		Pixmap pixmap = new Pixmap(gridSize, gridSize, Pixmap.Format.RGBA8888);
		pixmap.setColor(0, 0, 1f, 1f);;
		pixmap.fillRectangle(0, 0, gridSize, gridSize);
		texture = new Texture(pixmap);
	}

}
