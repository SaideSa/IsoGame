package com.mygdx.game.desktop;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Cactus {

	public Texture cactusImg;
	private Vector2 renderPos;
	private Vector2 pos;
	private final int WIDTH = 90;
	private final int HEIGHT = 170;
	private float time;
	private int direction = 0;
	private double speed;
	private boolean coll;
	private Player player;
	
	public Cactus(Vector2 renderPos, Vector2 pos) {
		cactusImg = new Texture(Gdx.files.internal("cactus.png"));
		this.pos = pos;
		this.renderPos = renderPos;
		time = 0;
		speed = 1;
		coll = false;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
//	public void render(SpriteBatch batch) {
//		batch.draw(cactusImg, renderPos.x, renderPos.y);
//	}
	
	public void update(float delta) {
		time+=delta;
		if(time > 0.3f) {
			move();
			time = 0;
		}
		
	}
	
	public void move() {
		
		if(player.getPos().x > pos.x) {
			renderPos.x -= 32 * speed;
			renderPos.y += 16 * speed;
			pos.x += 1*speed;
		} else {
			renderPos.x += 32 * speed;
			renderPos.y -= 16 * speed;
			pos.x -= 1*speed;
		}
		if(player.getPos().y > pos.y) {
			renderPos.x += 32 * speed;
			renderPos.y += 16 * speed;
			pos.y += 1*speed;
			
		} else {
			renderPos.x -= 32 * speed;
			renderPos.y -= 16 * speed;
			pos.y -= 1*speed;
		}
		
		
	}

	public Vector2 getRenderPos() {
		return renderPos;
	}

	public Vector2 getPos() {
		return pos;
	}

	public void setRenderPos(float x, float y) {
		this.renderPos.x = x;
		this.renderPos.y = y;
	}

	public void setPos(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}
	
	public Texture getTexture() {
		return cactusImg;
	}

	public String name() {
		return "Cactus";
	}

	public void setColl(boolean coll) {
		this.coll = coll;
	}
	public boolean getColl() {
		return coll;
	}
}
