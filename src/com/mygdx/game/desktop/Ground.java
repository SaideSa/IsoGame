package com.mygdx.game.desktop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ground {
	
	private Vector2 renderPos;
	private Vector2 mapPos;
	private Texture sand;
	
	public Ground(Vector2 renderPos, Vector2 mapPos, Texture sand) {
		this.renderPos = renderPos;
		this.mapPos = mapPos;
		this.sand = sand;
	}
	
	public Vector2 getRenderPos() {
		return renderPos;
	}
	
	public Vector2 getMapPos() {
		return mapPos;
		
	}
	
	public Texture getTex() {
		return sand;
	}

}
