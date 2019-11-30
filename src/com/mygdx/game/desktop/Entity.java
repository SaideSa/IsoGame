package com.mygdx.game.desktop;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
//abstrakte Klasse mit x und y (Originale Position)
public abstract class Entity {

	public abstract String name();
	public Vector2 pos;
	public Vector2 renderPos;
//	public float xOld, yOld;
	
	public abstract void render(SpriteBatch batch);
	public abstract void update(float delta);
	
	public abstract void move();
	
	
}
