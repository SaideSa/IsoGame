package com.mygdx.game.desktop;

import java.util.ArrayList;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Physics {

	private Rectangle envRect;
	private Rectangle playerRect;
	private Rectangle cactusRect;
	private Rectangle keyRect;
	private ArrayList<EnvObject> envObjects;

	private ArrayList<Rectangle> rectangles;
	private boolean checkEnvCollision;
	private boolean checkCactusCollision;
	private boolean checkKeyCollision;
	private int keyIndex;
	private int tileWidth = 64;
	private int tileHeight = 128;

	public Physics(ArrayList<EnvObject> envObjects) {
		envRect = new Rectangle();
		playerRect = new Rectangle();
		cactusRect = new Rectangle();
		keyRect = new Rectangle();
		this.envObjects = envObjects;
		rectangles = new ArrayList<Rectangle>();
		checkEnvCollision = false;
	}

	public void playerRect(Player p) {
		playerRect.x = p.getPos().x;
		playerRect.y = p.getPos().y;
		playerRect.height = p.getHeight()/tileHeight;
		playerRect.width = p.getWidth()/tileWidth;
	}

	public boolean getEnvCollision(Player p) {
		checkEnvCollision = false;
		playerRect(p);

		for (int i = 0; i < envObjects.size(); i++) {
			EnvObject env = envObjects.get(i);
			envRect.x = env.getPos().x-0.3f;
			envRect.y = env.getPos().y-0.3f;
			envRect.height = env.getHeight()/tileHeight+0.6f;
			envRect.width = env.getWidth()/tileWidth+0.6f;
			
			
			if(envRect.overlaps(playerRect)) {
				checkEnvCollision=true;
				System.out.println("++++++++++++++++++++");
			}
			

		}
		return checkEnvCollision;
	}

	public boolean getCactusCollision(Player p, ArrayList<Cactus> cactusList) {
		checkCactusCollision = false;
		playerRect(p);
		for(int i = 0; i < cactusList.size(); i++) {
			Cactus cactus = cactusList.get(i);
			cactusRect.x = cactus.getPos().x-0.3f;
			cactusRect.y = cactus.getPos().y-0.3f;
			cactusRect.height = cactus.getHeight()/tileHeight+0.6f;
			cactusRect.width = cactus.getWidth()/tileWidth+0.6f;
			
			if(cactusRect.overlaps(playerRect)) {
				checkCactusCollision = true;
			}
		}
		return checkCactusCollision;
	}
	
	public boolean getKeyCollision(Player p, ArrayList<Key> keys) {
		checkKeyCollision = false;
		playerRect(p);
		for(int i = 0; i < keys.size(); i++) {
			Key key = keys.get(i);
			keyRect.x = key.getPos().x-0.3f;
			keyRect.y = key.getPos().y-0.3f;
			keyRect.height = key.getHeight()/tileHeight+0.6f;
			keyRect.width = key.getHeight()/tileWidth+0.6f;
			
			if(keyRect.overlaps(playerRect)) {
				checkKeyCollision = true;
				keyIndex = i;
			}
		}
		return checkKeyCollision;
	}

	public ArrayList<Rectangle> getRectangles() {
		return this.rectangles;
	}
	
	public void setTileWidth(int width) {
		tileWidth = width;
	}
	public void setTileHeight(int height) {
		tileHeight = height;
	}

	public int getKeyIndex() {
		return keyIndex;
	}

}
