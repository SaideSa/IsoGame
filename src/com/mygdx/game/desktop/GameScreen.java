package com.mygdx.game.desktop;

import java.util.ArrayList;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends ScreenAdapter {
	public static final int WIDTH = 320 * 4;
	public static final int HEIGHT = 180 * 4;
	public static final int keyAmount = 3;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private IsometricRenderer renderer;
	private Player player;
	private ArrayList<Key> keys;
	private Texture key;

	private Physics physics;
	private ArrayList<EnvObject> envObjects;
	private ArrayList<Cactus> cactus;


	public GameScreen(SpriteBatch batch) {
		this.batch = batch;
	}

	public void show() {
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.position.set(WIDTH / 2 - 500, HEIGHT / 2, 10);
		renderer = new IsometricRenderer();
		
		player = new Player(keys);
		player.playerSetFirstPos(renderer.MAP_SIZE, renderer.TILE_WIDTH, renderer.TILE_HEIGHT);

		keys = new ArrayList<Key>();
		key = new Texture(Gdx.files.internal("key.png"));

		for (int i = 0; i < keyAmount; i++) {
			keys.add(new Key(renderer.keysPosition(), key));
		}

		cactus = renderer.createCactus(3);
		for(Cactus cact : cactus) {
			cact.setPlayer(player);
		}
		envObjects = renderer.getEnvObject();
		physics = new Physics(envObjects);

	}

	public void render(float delta) {
//		Gdx.gl.glClearColor(180/255f, 174/255f, 210/255f, 1);
		Gdx.gl.glClearColor(190 / 255f, 198 / 255f, 220 / 255f, 1);

//		Gdx.gl.glClearColor(230/255f, 210/255f, 200/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);

		handleInput();

		camera.update();

		batch.begin();

		renderer.drawGround(batch, delta);

		if(physics.getCactusCollision(player, cactus)) {
			//hier z.B. HealthBar reduzieren
			System.out.println("Game Over");
		}
		
		player.update(delta);
		if(physics.getEnvCollision(player) || player.getPos().x < 0.0 || player.getPos().y < 0.0) {
			player.setPos(player.getOldPos().x, player.getOldPos().y);

			player.setRenderPos(player.getOldRenderPos().x, player.getOldRenderPos().y);
		}
		
//		if(physics.getEnvCollision(player)) {
//			Vector2 oldPos = new Vector2(player.getOldPos().x, player.getOldPos().y);
//			player.setPos(oldPos);
//		}

		player.render(batch);

		for (int i = 0; i < keyAmount; i++) {
			keys.get(i).render(batch);
		}

//		renderer.drawEnvironment(batch);
		batch.end();

	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
			camera.zoom -= 0.002f;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.U)) {
			camera.zoom += 0.002f;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.P)) {
			System.out.println(camera.position);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera.position.x -= 1;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			camera.position.x += 1;
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			camera.position.y += 1;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			camera.position.y -= 1;

		}

	}
	

	public void dispose() {

	}

}
