package com.reph3x.breakthrough.World;
import com.reph3x.breakthrough.Entity.Player.*;
import java.util.*;
import com.reph3x.breakthrough.Bullet.*;
import com.reph3x.breakthrough.*;
import com.badlogic.gdx.graphics.*;
import com.reph3x.breakthrough.UI.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Gun.*;
import com.reph3x.breakthrough.Particle.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.*;
import com.reph3x.breakthrough.Entity.*;
import com.reph3x.breakthrough.Entity.Enemy.*;
import com.reph3x.breakthrough.UI.Inventory.*;
import com.reph3x.breakthrough.UI.Button.*;
import com.reph3x.breakthrough.UI.Item.*;

public class World
{
	public OrthographicCamera camera;
	public ShapeRenderer render;
	public SpriteBatch batch;
	public BitmapFont font;
	public Player player;
	public List<Entity> entityList;
	public List<Bullet> bulletList;
	public List<Particle> particleList;
	public List<Element> elementList;
	public List<Object> updateQueue;
	public VJoystick joystick;
	public UpdateBar updateBar;
	public float w;
	public float h;
	
	public World () {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		render = new ShapeRenderer();
		font = new BitmapFont();
		batch = new SpriteBatch();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		camera = new OrthographicCamera(w, h);
		render.setProjectionMatrix(camera.combined);
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		addPlayer(new Vector2(0, 0), 50, 15);
		bulletList = new ArrayList<Bullet>();
		entityList = new ArrayList<Entity>();
		particleList = new ArrayList<Particle>();
		elementList = new ArrayList<Element>();
		updateQueue = new ArrayList<Object>();
		updateBar = new UpdateBar();
		elementList.add(updateBar);
		OpenInventoryButton oiButton = new OpenInventoryButton(new Vector2(), new Color(Color.CYAN), this);
		oiButton.pos.x = camera.viewportWidth/2 - oiButton.width - 10;
		oiButton.pos.y = camera.viewportHeight/2 - 200;
		queueUpdate(oiButton);
		}
	
	public void update() {
		cleanEntities();
		cleanBulletList();
		cleanParticleList();
		cleanIndicatorList();
		render();
	}
	
	public void render() {
		if(player != null) {
			player.render(render);
			player.update();
			joystick.render(render);
		}
		else if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			addPlayer(new Vector2(touchPos.x, touchPos.y), 50, 15);
		}
		for(Entity entity : entityList) {
			entity.render(render);
			entity.update();
		}
		for(Bullet bullet : bulletList) {
			bullet.render(render);
			bullet.update();
		}
		for(Particle particle : particleList) {
			particle.render(render);
			particle.update();
		}
		for(Element element : elementList) {
			element.render(render, batch, font);
			element.update();
		}
		for(Object object : updateQueue) {
			if(object instanceof Entity)
				entityList.add((Entity) object);
			if(object instanceof Bullet)
				bulletList.add((Bullet) object);
			if(object instanceof Particle)
				particleList.add((Particle) object);
			if(object instanceof Element)
				elementList.add((Element)object);
		}
		updateQueue.clear();
	}
	
	public void addEnemy(Vector2 pos, float size) {
		Enemy enemy = new Enemy(this, pos, size);
		queueUpdate(enemy);
	}
	public void addShieldedEnemy(Vector2 pos) {
		ShieldedEnemy shieldedEnemy = new ShieldedEnemy(this, pos);
		queueUpdate(shieldedEnemy);
	}
	
	public void addRandomEnemy(float size) {
		Vector2 enemyPos = getFreePos(size);
		Random r = new Random();
		if(r.nextBoolean())
			addEnemy(enemyPos, size);
		else
			addShieldedEnemy(enemyPos);
	}
	
	
	public void addPlayer(Vector2 pos, float size, float speed) {
		player = new Player(this, pos, size, speed, 10, 500, camera, new Color(Color.GREEN));
		joystick = new VJoystick(-(camera.viewportWidth /2) + 10, -(camera.viewportHeight / 2) + 10, 500, 75, camera, player);
	}
	
	public void addParticle(Vector2 pos, Vector2 vel, float size, float timer, Color color) {
		Particle particle = new Particle(pos, vel, size, timer, color);
		queueUpdate(particle);
	}
	public void addParticle(Vector2 pos, Vector2 vel, float size, float timer, Color color, boolean scaleVel, float velScale) {
		Particle particle = new Particle(pos, vel, size, timer, color, scaleVel, velScale);
		queueUpdate(particle);
	}
	public void addUpdate(Update update) {
		updateBar.addUpdate(update);
	}
	public void queueUpdate(Object object) {
		updateQueue.add(object);
	}
	
	public void removeBullets(ArrayList<Bullet> removeList) {
		bulletList.removeAll(removeList);
	}
	
	public void removeBullet(Bullet bullet) {
		ArrayList<Bullet> removeBullet = new ArrayList<Bullet>();
		removeBullet.add(bullet);
		removeBullets(removeBullet);
	}
	
	public Element getElementAt(Vector2 pos) {
		for(Element element : elementList) {
			if(pos.x > element.pos.x && pos.x < element.pos.x + element.width) {
				if(pos.y > element.pos.y && pos.y < element.pos.y + element.height) {
					return element;
				}
			}
		}
		return null;
	}
	
	public void cleanEntities() {
		List<Entity> cleanList = new ArrayList<Entity>();
		for(Entity entity : entityList) {
			if(entity.isDead) {
				cleanList.add(entity);
			}
		}
		entityList.removeAll(cleanList);
		if(player != null) {
			if(player.isDead)
				player = null;
		}
		
	}
	
	public void cleanBulletList() {
		List<Bullet> clearList = new ArrayList<Bullet>();
		for(Bullet bullet : bulletList) {
			if(bullet.isOffscreen() || bullet.isDead)
				clearList.add(bullet);
		}
		bulletList.removeAll(clearList);
	}
	
	public void cleanParticleList() {
		List<Particle> clearList = new ArrayList<Particle>();
		for(Particle particle : particleList) {
			if(particle.isDead)
				clearList.add(particle);
		}
		particleList.removeAll(clearList);
	}
	
	public void cleanIndicatorList() {
		List<Element> deadElements = new ArrayList<Element>();
		for(Element element : elementList) {
			if(element.isDead) {
				deadElements.add(element);
			} 
		}
		elementList.removeAll(deadElements);
	}
	
	private Vector2 getFreePos(float size) {
		Vector2 pos = getRandomPos();
		if(player != null && pos.x + size >= player.pos.x - player.size && pos.x - size <= player.pos.x + player.size) {
			return getFreePos(size);
		} else if (player != null && pos.y + size >= player.pos.y - player.size && pos.y - size <= player.pos.y + player.size) {
			return getFreePos(size);
		}
		return pos;
	}
	private Vector2 getRandomPos() {
		Random rand = new Random();
		float rX = rand.nextInt((int)camera.viewportWidth/2);
		if(rand.nextBoolean())
			rX *= -1;
		float rY = rand.nextInt((int)camera.viewportHeight/2);
		if(rand.nextBoolean())
			rY *= -1;
		return new Vector2(rX, rY);
	}
}
