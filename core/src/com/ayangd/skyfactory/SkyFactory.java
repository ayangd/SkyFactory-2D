package com.ayangd.skyfactory;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SkyFactory extends ApplicationAdapter {
	SpriteBatch batch;
	Pixmap tileSelectorPixmap;
	Texture tileSelector;
	ArrayList<Tile> tiles;
	TileManager tileManager;
	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		tileSelectorPixmap = new Pixmap(Tile.size, Tile.size, Pixmap.Format.RGBA4444);
		tileSelectorPixmap.setColor(Color.GOLD);
		tileSelectorPixmap.drawRectangle(0, 0, Tile.size, Tile.size);
		tileSelector = new Texture(tileSelectorPixmap);
		tiles = new ArrayList<Tile>();
		tileManager = new TileManager();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
	}

	@Override
	public void render () {
		// Input Processing
		if(Gdx.input.isTouched()) {
			int inX = Gdx.input.getX() / Tile.size;
			int inY = (Gdx.graphics.getHeight() - Gdx.input.getY()) / Tile.size;
			tileManager.add(new DebugTile(inX, inY), tiles);
		}
		
		// Draw
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		// Draw Start
		if(tiles.size() != 0)
			for(int i = 0; i != tiles.size(); i++) {
				tiles.get(i).draw(batch);
			}
		batch.draw(tileSelector,
				   Gdx.input.getX() / Tile.size * Tile.size,
				   (Gdx.graphics.getHeight() - Gdx.input.getY()) / Tile.size * Tile.size
				   );
		// Draw End
		
		// Draw Version
		font.draw(batch, "Build: indev rc1" , 5, Gdx.graphics.getHeight() - 5);
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		tileSelector.dispose();
	}
}
