package com.mygdx.game.desktop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
//von Entity abgeleitet 
public class EnvObject {
	private Vector2 renderPos;
	private Vector2 pos;
	private Texture envObj;
	private int width = 64;
	private int height = 128;
	String name;
	
	public EnvObject(Vector2 renderPos, Vector2 pos, Texture envObj, String name) {
		this.renderPos = renderPos;
		this.pos = pos;
		this.envObj = envObj;
		this.name = name;
	}
	public EnvObject(Vector2 renderPos, Vector2 pos, int width, int height, Texture envObj, String name) {
		this.renderPos = renderPos;
		this.pos = pos;
		this.envObj = envObj;
		this.width = width;
		this.height = height;
		this.name = name;
	}
	
	public Vector2 getRenderPos() {
		return renderPos;
	}
	
	public Vector2 getPos() {
		return pos;
		
	}
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
		
	}
	
	public Texture getTex() {
		return envObj;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

