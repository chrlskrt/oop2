package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MyGdxGame extends ApplicationAdapter {
	private boolean DEBUG = false;
	private Box2DDebugRenderer b2dr;
	private World WORLD;
	private Body player;
	private Body[] platform = new Body[10];
	private Label coordinates;
	private Stage stage;
	private OrthographicCamera camera;

	public MyGdxGame() {
	}

	public void create() {
		float w = (float)Gdx.graphics.getWidth();
		float h = (float)Gdx.graphics.getHeight();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false);
		this.WORLD = new World(new Vector2(0.0F, -9.8F), false);
		this.b2dr = new Box2DDebugRenderer();
		this.player = this.createBox(2, 10, 32, 32, false);
		this.platform[0] = this.createBox(0, 100, 64, 300, true);
		this.platform[1] = this.createBox(0, -300, 64, 300, true);
		this.platform[3] = this.createBox(0, -250, 500, 1, true);
	}

	public void render() {
		this.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
		Gdx.gl.glClear(16384);
		this.b2dr.render(this.WORLD, this.camera.combined.scl(32.0F));
	}

	public void resize(int width, int height) {
		this.camera.setToOrtho(false, (float)(width / 2), (float)(height / 2));
	}

	public void dispose() {
		this.WORLD.dispose();
		this.b2dr.dispose();
	}

	public void update(float delta) {
		this.WORLD.step(0.016666668F, 6, 2);
		this.inputUpdate(delta);
		this.cameraUpdate(delta);
	}

	public void inputUpdate(float delta) {
		int horizontalForce = 0;
		if (Gdx.input.isKeyPressed(21)) {
			--horizontalForce;
		}

		if (Gdx.input.isKeyPressed(22)) {
			++horizontalForce;
		}

		if (Gdx.input.isKeyJustPressed(19)) {
			this.player.applyForceToCenter(0.0F, 300.0F, false);
		}

		this.player.setLinearVelocity((float)(horizontalForce * 5), this.player.getLinearVelocity().y);
	}

	public void cameraUpdate(float delta) {
		Vector3 position = this.camera.position;
		position.x = this.player.getPosition().x * 32.0F;
		position.y = this.player.getPosition().y * 32.0F;
		this.camera.position.set(position);
		this.camera.update();
	}

	public Body createBox(int x, int y, int width, int height, boolean isStatic) {
		BodyDef def = new BodyDef();
		def.type = isStatic ? BodyType.StaticBody : BodyType.DynamicBody;
		def.position.set((float)x / 32.0F, (float)y / 32.0F);
		def.fixedRotation = true;
		Body pBody = this.WORLD.createBody(def);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox((float)(width / 2) / 32.0F, (float)(height / 2) / 32.0F);
		pBody.createFixture(shape, 1.0F);
		shape.dispose();
		return pBody;
	}
}
