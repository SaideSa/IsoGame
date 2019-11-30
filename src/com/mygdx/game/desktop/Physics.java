package com.mygdx.game.desktop;

import java.util.ArrayList;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Physics {

	Rectangle rect;
	Rectangle playerRect;
	ArrayList<EnvObject> envObjects;

	ArrayList<Rectangle> rectangles;
	boolean checkCollision;
	int tileWidth = 64;
	int tileHeight = 128;

	public Physics(ArrayList<EnvObject> envObjects) {
		rect = new Rectangle();
		playerRect = new Rectangle();
		this.envObjects = envObjects;
		rectangles = new ArrayList<Rectangle>();
		checkCollision = false;
	}


	public boolean getEnvCollision(Player p) {
		checkCollision = false;
		playerRect.x = p.getPos().x;
		playerRect.y = p.getPos().y;
		playerRect.height = p.getHeight()/tileHeight;
		playerRect.width = p.getWidth()/tileWidth;

		for (int i = 0; i < envObjects.size(); i++) {
			EnvObject env = envObjects.get(i);
			rect.x = env.getPos().x-0.3f;
			rect.y = env.getPos().y-0.3f;
			rect.height = env.getHeight()/tileHeight+0.6f;
			rect.width = env.getWidth()/tileWidth+0.6f;
			
			
			if(rect.overlaps(playerRect)) {
				checkCollision=true;
				System.out.println("++++++++++++++++++++");
			}
			

		}
		return checkCollision;
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
