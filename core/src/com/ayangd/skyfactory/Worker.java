package com.ayangd.skyfactory;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import static com.ayangd.skyfactory.SkyFactory.gridSize;

public class Worker {
	Vector2 position;
	Texture texture;
	public Worker(int x, int y) {
		position = new Vector2(x - (x % gridSize),y - (y % gridSize));
		changeSkin();
	}
	
	public Worker(Vector2 position) {
		this.position = position;
		changeSkin();
	}
	
	public void changeSkin() {
		Pixmap pixmap = new Pixmap(gridSize, (int) (gridSize * 1.75), Pixmap.Format.RGBA8888);
		pixmap.setColor((float) Math.random(), (float)Math.random(), (float) Math.random(), 0.5f);
		pixmap.fillRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
		texture = new Texture(pixmap);
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(texture, position.x, position.y);
	}
}
