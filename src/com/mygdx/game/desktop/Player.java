package com.mygdx.game.desktop;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {

	public Texture standImg, rightRImg, leftRImg, downRImg, upRImg, rightLImg, leftLImg, downLImg, upLImg;
	private Vector2 renderPos;
	private Vector2 pos;
	private final int WIDTH = 95;
	private final int HEIGHT = 158;
	private float time;
	private int direction = 0;
	private double speed;
	private boolean coll;
	private float xOldPos, yOldPos, xOldRenderPos, yOldRenderPos; //für Invariante
	private float firstXpos = 4;
	private float firstYpos = 5;

	public Player() {
//		standImg = new Texture(Gdx.files.internal(("playerVR2.png"));
		rightLImg = new Texture(Gdx.files.internal("playerRL.png"));
		leftLImg = new Texture(Gdx.files.internal("playerLL.png"));
		downRImg = new Texture(Gdx.files.internal("playerVR.png"));
		standImg = new Texture(Gdx.files.internal("playerVR.png"));
		upLImg = new Texture(Gdx.files.internal("playerHL.png"));
		rightRImg = new Texture(Gdx.files.internal("playerRR.png"));
		leftRImg = new Texture(Gdx.files.internal("playerLR.png"));
		downLImg = new Texture(Gdx.files.internal("playerVL.png"));
		upRImg = new Texture(Gdx.files.internal("playerHR.png"));

		pos = new Vector2();
		renderPos = new Vector2();
		time = 1;
		speed = 1;
		coll = false;
	}

	@Override
	public void render(SpriteBatch batch) {

		if (direction == 0) {
			batch.draw(standImg, renderPos.x, renderPos.y);
		} else if (direction == 1) {
			batch.draw(upRImg, renderPos.x, renderPos.y);
		} else if (direction == 2) {
			batch.draw(upLImg, renderPos.x, renderPos.y);
		} else if (direction == 3) {
			batch.draw(downRImg, renderPos.x, renderPos.y);
		} else if (direction == 4) {
			batch.draw(downLImg, renderPos.x, renderPos.y);
		} else if (direction == 5) {
			batch.draw(leftRImg, renderPos.x, renderPos.y);
		} else if (direction == 6) {
			batch.draw(leftLImg, renderPos.x, renderPos.y);
		} else if (direction == 7) {
			batch.draw(rightRImg, renderPos.x, renderPos.y);
		} else if (direction == 8) {
			batch.draw(rightLImg, renderPos.x, renderPos.y);
		}

//		batch.draw(img, pos.x, pos.y);
	}

	@Override
	public void update(float delta) {
		
		//render und map Pos merken für Invariante
		xOldPos = pos.x;
		yOldPos = pos.y;
		
		xOldRenderPos = renderPos.x;
		yOldRenderPos = renderPos.y;
		
		move();
	
//		System.out.println("x: " + pos.x + "  y: " + pos.y);
//		checkPlayerActions();
	}

//	private void checkPlayerActions() {
//		if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
//			for (Key k : keys) {
//				if (k.getPos().x == pos.x && k.getPos().y == pos.y) {
//
//					keys.remove(k);
//					break;
//				}
//			}
//			System.out.println("RenderPos: " + renderPos + "; Pos: " + pos);
//		}
//	}

	public void move() {
		
	
		if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {

			renderPos.x -= 32 * speed;
			renderPos.y += 16 * speed;
			pos.x += 1*speed;

			if (direction == 1) {
				direction = 2;
			} else {
				direction = 1;
			}

		} else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {

			renderPos.x += 32 * speed; // bleibt
			renderPos.y -= 16 * speed;
			pos.x -= 1*speed;

			if (direction == 3) {
				direction = 4;
			} else {
				direction = 3;

			}

		} else if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {

			renderPos.x -= 32 * speed;
			renderPos.y -= 16 * speed;
			pos.y -= 1*speed;

			if (direction == 5) {
				direction = 6;
			} else {
				direction = 5;
			}

		} else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {

			renderPos.x += 32 * speed;
			renderPos.y += 16 * speed;
			pos.y += 1*speed;

			if (direction == 7) {
				direction = 8;
			} else {
				direction = 7;
			}

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
	
	public void playerSetFirstPos(int MAP_SIZE, int TILE_WIDTH, int TILE_HEIGHT) {
		for (int row = MAP_SIZE; row >= 0; row--) {
			for (int col = MAP_SIZE; col >= 0; col--) {
				if (row == firstXpos && col == firstYpos) {
					float x = (col - row) * (TILE_HEIGHT / 4f);
					float y = (col + row) * (TILE_WIDTH / 4f);

					this.setPos(row, col);
					this.setRenderPos(x, y);
					xOldPos = row;
					yOldPos = col;
				}
			}
		}

	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public Vector2 getOldPos() {
		Vector2 oldPos = new Vector2(xOldPos, yOldPos);
		return oldPos;
	}
	public Vector2 getOldRenderPos() {
		Vector2 oldRenderPos = new Vector2(xOldRenderPos, yOldRenderPos);
		return oldRenderPos;
	}

	@Override
	public String name() {
		return "Player";
	}

	public void setColl(boolean coll) {
		this.coll = coll;
	}
	public boolean getColl() {
		return coll;
	}
}
