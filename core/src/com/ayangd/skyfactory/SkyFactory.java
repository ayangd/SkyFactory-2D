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
	static int gridSize = 20;
	
	SpriteBatch batch;
	Pixmap tileSelectorPixmap;
	Texture tileSelector;
	ArrayList<Tile> tiles;
	Worker worker;
	TileManager tileManager;
	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		tileSelectorPixmap = new Pixmap(gridSize, gridSize, Pixmap.Format.RGBA4444);
		tileSelectorPixmap.setColor(Color.GOLD);
		tileSelectorPixmap.drawRectangle(0, 0, gridSize, gridSize);
		tileSelector = new Texture(tileSelectorPixmap);
		tiles = new ArrayList<Tile>();
		tiles.add(new DebugTile((Gdx.graphics.getWidth() / 2) / gridSize, (Gdx.graphics.getHeight() / 2) / gridSize));
		tileManager = new TileManager();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		worker = new Worker((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2) + gridSize);
	}

	@Override
	public void render () {
		// Input Processing
		if(Gdx.input.isTouched()) {
			int inX = Gdx.input.getX() / gridSize;
			int inY = (Gdx.graphics.getHeight() - Gdx.input.getY()) / gridSize;
			tileManager.add(new DebugTile(inX, inY), tiles, worker);
		}
		
		// Draw
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		// Draw Start
		
		// Draw Tiles
		if(tiles.size() != 0)
			for(int i = 0; i != tiles.size(); i++) {
				tiles.get(i).draw(batch);
			}
		
		// Draw Selector
		batch.draw(tileSelector,
				   Gdx.input.getX() / gridSize * gridSize,
				   (Gdx.graphics.getHeight() - Gdx.input.getY()) / gridSize * gridSize
				   );
		// Draw Worker
		worker.draw(batch);
		
		// Draw End
		
		// Draw Version
		font.draw(batch, "Build: indev rc2" , 5, Gdx.graphics.getHeight() - 5);
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		tileSelector.dispose();
	}
}
