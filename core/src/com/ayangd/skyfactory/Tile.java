package com.ayangd.skyfactory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {
	static int size = 20;
	int x,y;
	Texture texture;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(texture, x * 20, y * 20);
	}
}
