package com.ayangd.skyfactory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.ayangd.skyfactory.SkyFactory.gridSize;

public class Tile {
	int x,y;
	Texture texture;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(texture, x * gridSize, y * gridSize);
	}
}
