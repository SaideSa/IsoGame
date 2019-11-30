package com.mygdx.game.desktop;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;


public class Isometric extends Game{
	
	private SpriteBatch batch;
	private GameScreen gScreen;
	
	public void create() {
		batch = new SpriteBatch();
		gScreen = new GameScreen(batch);
		setScreen(gScreen);
	}
	
	public void render() {
		super.render();
	}
	
	public void dispose() {
		batch.dispose();
		super.dispose();
	}
	

}
