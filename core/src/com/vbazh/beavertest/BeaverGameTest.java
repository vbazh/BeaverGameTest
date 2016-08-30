package com.vbazh.beavertest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vbazh.beavertest.Screens.Menu;

public class BeaverGameTest extends Game {
	public static final int WIDTH = 380;
	public static final int HEIGHT = 208;
	public static final float PPM = 100; //pixelpermeter

	public static SpriteBatch batch;
	public static AssetManager manager;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		setScreen(new Menu(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}



	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
		super.render();
	}

}
