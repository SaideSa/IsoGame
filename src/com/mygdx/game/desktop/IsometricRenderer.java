package com.mygdx.game.desktop;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;

public class IsometricRenderer {

	public static final int TILE_WIDTH = 64;
	public static final int TILE_HEIGHT = 128;
	public static final int MAP_SIZE = 40;
	private Random r = new Random();

	private ArrayList<Ground> ground;
	private ArrayList<EnvObject> envObject;
	private ArrayList<Cactus> cactus;
	private ArrayList<Key> keys;

	public static final int TILE_WIDTH_NEAR = 128;
	public static final int TILE_HEIGHT_NEAR = 256;

	private Texture sand, stone, pyramidFlat, chest, barrel, stairsStone, pyramid, tileSand, block;
//	private Texture tileSand3;;
	private int[][] environment;
	private ArrayList<Item> items;

	private int groundLength;
	private int level;
	private Vector2 playerPos;

	public IsometricRenderer() {
		sand = new Texture(Gdx.files.internal("sand.png"));
		stone = new Texture(Gdx.files.internal("stone.png"));
		pyramid = new Texture(Gdx.files.internal("pyramid.png"));
		block = new Texture(Gdx.files.internal("block.png"));
		pyramidFlat = new Texture(Gdx.files.internal("pyramid_flat.png"));
		items = new ArrayList<Item>();
		chest = new Texture(Gdx.files.internal("chest.png"));
		stairsStone = new Texture(Gdx.files.internal("stairsStone.png"));
		barrel = new Texture(Gdx.files.internal("barrel.png"));

		ground = new ArrayList<Ground>();
		envObject = new ArrayList<EnvObject>();
		cactus = new ArrayList<Cactus>();
		keys = new ArrayList<Key>();
		
		level = 1;

		fillMap();

	}

	public void drawGround(SpriteBatch batch, float delta) {

		for (Ground g : ground) {
			batch.draw(g.getTex(), g.getRenderPos().x, g.getRenderPos().y, TILE_WIDTH, TILE_HEIGHT);
		}

		for (EnvObject env : envObject) {
			batch.draw(env.getTex(), env.getRenderPos().x, env.getRenderPos().y, env.getWidth(), env.getHeight());
		}
		for (Cactus cact : cactus) {
			cact.update(delta);
			batch.draw(cact.getTexture(), cact.getRenderPos().x, cact.getRenderPos().y, cact.getWidth(),
					cact.getHeight());
		}
		for (Key key : keys) {
			batch.draw(key.getTexture(), key.getRenderPos().x, key.getRenderPos().y);
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
//			map = generateMap();
			envObject.clear();
			generateEnvironment();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
			level = 2;
			ground.clear();
			fillMap();
			envObject.clear();
			generateEnvironment();
		}

	}

	public void fillMap() {
		if (level == 1) {
			for (int row = MAP_SIZE; row >= 0; row--) {
				for (int col = MAP_SIZE; col >= 0; col--) {
					float x = (col - row) * (TILE_HEIGHT / 4f);
					float y = (col + row) * (TILE_WIDTH / 4f);

					ground.add(new Ground(new Vector2(x, y), new Vector2(row, col), sand));

				}
			}
			groundLength = ground.size();
		}

		if (level == 2) {
			ground.clear();
			for (int row = MAP_SIZE; row >= 0; row--) {
				for (int col = MAP_SIZE; col >= 0; col--) {
					float x = (col - row) * (TILE_HEIGHT / 5f);
					float y = (col + row) * (TILE_WIDTH / 5f);

					ground.add(new Ground(new Vector2(x, y), new Vector2(row, col), stone));

				}

			}
		}

	}

	public void generateEnvironment() {
		boolean large = false;
		for (int row = MAP_SIZE; row >= 0; row--) {
			for (int col = MAP_SIZE; col >= 0; col--) {
				double num = r.nextDouble() * 10;

				float x = (col - row) * (TILE_HEIGHT / 4f);
				float y = (col + row) * (TILE_WIDTH / 4f);

				Vector2 renderPos = new Vector2(x, y);
				Vector2 mapPos = new Vector2(row, col);

				if (row != getPlayerPos().x && col != getPlayerPos().y) {

					if (level == 1) {
						if (large == false) {
							if (num < 9.87) {

							} else if (num >= 9.87 && num < 9.9 && (row < MAP_SIZE - 2) && col < MAP_SIZE - 2
									&& (row > 2 && col > 2)) {
								envObject.add(new EnvObject(renderPos, mapPos, 128, 256, pyramid, "Pyramide"));
								System.out.println("Row: " + row + "Col: " + col);
								large = true;
							} else if (num >= 9.9 && num < 9.95) {
								envObject.add(new EnvObject(renderPos, mapPos, pyramidFlat, "PyramideFlat"));
							} else {
								envObject.add(new EnvObject(renderPos, mapPos, block, "Block"));
							}
						} else {
							large = false;
						}
					}

					if (level == 2) {
						if (large == false) {
							if (num <= 9.9) {

							} else if (num > 9.9 && num < 9.94 && (row < MAP_SIZE - 4) && col < MAP_SIZE - 4
									&& (row > 4 && col > 4)) {
								envObject.add(new EnvObject(renderPos, mapPos, 128, 256, stairsStone, "SteinTreppen"));
								large = true;
							} else if (num >= 9.94 && num < 9.98 && (row < MAP_SIZE - 4) && col < MAP_SIZE - 4
									&& (row > 4 && col > 4)) {
								envObject.add(new EnvObject(renderPos, mapPos, 128, 256, barrel, "Faﬂ"));
							} else if ((row < MAP_SIZE - 4) && col < MAP_SIZE - 4 && (row > 4 && col > 4)) {
								envObject.add(new EnvObject(renderPos, mapPos, 256, 512, chest, "Truhe"));
							}
						} else {
							large = false;
						}
					}
				}
			}
		}

	}

	public ArrayList<Cactus> createCactus(int amount) {

		for (int i = 0; i < amount; i++) {
			float randomX = r.nextFloat() * MAP_SIZE;
			float randomY = r.nextFloat() * MAP_SIZE;
			float x = (randomY - randomX) * (TILE_HEIGHT / 4f);
			float y = (randomY + randomX) * (TILE_WIDTH / 4f);
			Vector2 renderPos = new Vector2(x, y);
			Vector2 mapPos = new Vector2(randomX, randomY);
			cactus.add(new Cactus(renderPos, mapPos));
		}
		return cactus;
	}

	// hier auch noch pos;
	public ArrayList<Key> createKeys(int amount) {
		for (int i = 0; i < amount; i++) {
			float randomX = r.nextFloat() * MAP_SIZE;
			float randomY = r.nextFloat() * MAP_SIZE;
			float x = (randomY - randomX) * (TILE_HEIGHT / 4f);
			float y = (randomY + randomX) * (TILE_WIDTH / 4f);
			Vector2 renderPos = new Vector2(x, y);
			Vector2 mapPos = new Vector2(randomX, randomY);
			keys.add(new Key(renderPos, mapPos));
		}


		return keys;
	}

	public void setPlayerPos(Vector2 pos) {
		playerPos = pos;
	}

	public Vector2 getPlayerPos() {
		return playerPos;
	}

	public ArrayList<EnvObject> getEnvObject() {
		return envObject;
	}
}
