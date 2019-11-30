package com.mygdx.game.desktop;

import java.util.ArrayList;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Physics {

	Rectangle envRect;
	Rectangle playerRect;
	Rectangle cactusRect;
	ArrayList<EnvObject> envObjects;

	ArrayList<Rectangle> rectangles;
	boolean checkEnvCollision;
	boolean checkCactusCollision;
	int tileWidth = 64;
	int tileHeight = 128;

	public Physics(ArrayList<EnvObject> envObjects) {
		envRect = new Rectangle();
		playerRect = new Rectangle();
		cactusRect = new Rectangle();
		this.envObjects = envObjects;
		rectangles = new ArrayList<Rectangle>();
		checkEnvCollision = false;
	}


	public boolean getEnvCollision(Player p) {
		checkEnvCollision = false;
		playerRect.x = p.getPos().x;
		playerRect.y = p.getPos().y;
		playerRect.height = p.getHeight()/tileHeight;
		playerRect.width = p.getWidth()/tileWidth;

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
		for(int i = 0; i < cactusList.size(); i++) {
			Cactus cactus = cactusList.get(i);
			cactusRect.x = cactus.getPos().x-0.3f;
			cactusRect.y = cactus.getPos().y-0.3f;
			cactusRect.height = cactus.getHeight()/tileHeight+0.6f;
			cactusRect.width = cactus.getHeight()/tileWidth+0.6f;
			
			if(cactusRect.overlaps(playerRect)) {
				checkCactusCollision = true;
			}
		}
		return checkCactusCollision;
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


}
