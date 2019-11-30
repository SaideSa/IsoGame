package com.mygdx.game.desktop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Item {
	
	private Texture t;
	public  Vector2 renderPos;
	public Vector2 mapPos;
	
	public Item(Vector2 renderPos, Vector2 mapPos, Texture t) {
		this.renderPos = renderPos;
		this.mapPos = mapPos;
		this.t = t;
		
	}
	
	public void render(SpriteBatch batch) {
		if(t != null) {
			batch.draw(t, renderPos.x, renderPos.y);
		}
	}
}
