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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SkyFactory extends ApplicationAdapter {
	static int gridSize = 20;
	
	SpriteBatch batch;
	Pixmap tileBuilderPixmap;
	Texture tileBuilder;
	Pixmap tileRemoverPixmap;
	Texture tileRemover;
	ArrayList<Tile> tiles;
	Worker worker;
	TileManager tileManager;
	BitmapFont versionFont;
	BitmapFont font;
	
	boolean build;
	boolean focused;
	
	Stage stage;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		tileBuilderPixmap = new Pixmap(gridSize, gridSize, Pixmap.Format.RGBA4444);
		tileBuilderPixmap.setColor(Color.GOLD);
		tileBuilderPixmap.drawRectangle(0, 0, gridSize, gridSize);
		tileBuilder = new Texture(tileBuilderPixmap);
		tileRemoverPixmap = new Pixmap(gridSize, gridSize, Pixmap.Format.RGBA4444);
		tileRemoverPixmap.setColor(Color.RED);
		tileRemoverPixmap.drawRectangle(0, 0, gridSize, gridSize);
		tileRemover = new Texture(tileRemoverPixmap);
		tiles = new ArrayList<Tile>();
		tiles.add(new DebugTile((Gdx.graphics.getWidth() / 2) / gridSize, (Gdx.graphics.getHeight() / 2) / gridSize));
		tileManager = new TileManager();
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("segoeui.ttf"));
		FreeTypeFontParameter par = new FreeTypeFontParameter();
		par.size = 16;
		par.characters = FreeTypeFontGenerator.DEFAULT_CHARS;
		par.color = Color.WHITE;
		versionFont = gen.generateFont(par);
		par.size = 12;
		par.color = Color.BLACK;
		font = gen.generateFont(par);
		gen.dispose();
		worker = new Worker((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2) + gridSize);
		gameUI();
		build = true;
		focused = true;
	}

	@Override
	public void render () {
		// Input Processing
		if(focused)
			if(Gdx.input.isTouched()) {
				int inX = Gdx.input.getX() / gridSize;
				int inY = (Gdx.graphics.getHeight() - Gdx.input.getY()) / gridSize;
				if(build) {
					tileManager.add(new DebugTile(inX, inY), tiles, worker);
				} else {
					tileManager.remove(inX, inY, tiles);
				}
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
		if(focused)
			if(!tileManager.isBlockedByWorker(Gdx.input.getX() / gridSize, (Gdx.graphics.getHeight() - Gdx.input.getY()) / gridSize, worker)) {
				if(build) {
					batch.draw(tileBuilder,
							   Gdx.input.getX() / gridSize * gridSize,
							   (Gdx.graphics.getHeight() - Gdx.input.getY()) / gridSize * gridSize
							   );
				} else {
				batch.draw(tileRemover,
						   Gdx.input.getX() / gridSize * gridSize,
						   (Gdx.graphics.getHeight() - Gdx.input.getY()) / gridSize * gridSize
						   );
				}
			}
		// Draw Worker
		worker.draw(batch);
		
		// Draw End
		
		// Draw Version
		versionFont.draw(batch, "Build: indev rc3" , 5, Gdx.graphics.getHeight() - 5);
		
		batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		tileBuilder.dispose();
	}
	
	public void gameUI() {
		// Create new stage
		stage = new Stage();
		
		// Create images for buttons
		Pixmap pup = new Pixmap(50,20,Pixmap.Format.RGBA4444);
		Pixmap pdown = new Pixmap(50,20,Pixmap.Format.RGBA4444);
		pup.setColor(new Color(1f, 1f, 0, 1f));
		pdown.setColor(new Color(0.5f, 0.5f, 0, 1f));
		pup.fillRectangle(0, 0, 50, 20);
		pdown.fillRectangle(0, 0, 50, 20);
		
		// Create buttonStyle
		TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(pdown)));
		buttonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(pup)));
		buttonStyle.font = font;
		
		// Create default listener for all UI Components
		ClickListener defaultListener = new ClickListener() {
			
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				focused = false;
			}
			
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				focused = true;
			}
			
		};
		
		// Create button
		final TextButton button = new TextButton("Remove", buttonStyle);
		button.addListener(defaultListener);
		button.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				build = !build;
				if(button.getText().toString().equals("Remove")) {
					button.setText("Build");
				} else {
					button.setText("Remove");
				}
			}
		});
		// Add components
		stage.addActor(button);
		
		// Enable inputs to flow through the stage
		Gdx.input.setInputProcessor(stage);
	}
}
