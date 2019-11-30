package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Key extends Item {
	private Vector2 renderPos;
	private Vector2 pos;
	private Texture texture;

	public Key(Vector2 renderPosition, Vector2 pos) { 
		super(renderPosition, pos, new Texture(Gdx.files.internal("key.png")));
		// TODO Auto-generated constructor stub
		this.renderPos = renderPosition;
		this.pos = pos;
		this.texture = new Texture(Gdx.files.internal("key.png"));
	}
	
	public Vector2 getRenderPos() {
		return renderPos;
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public int getHeight() {
		return texture.getHeight();
		
	}
	
	public int getWidth() {
		return texture.getWidth();
		
	}

}
