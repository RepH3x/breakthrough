package com.reph3x.breakthrough.Bullet;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.Gun.*;
import java.util.*;
import com.reph3x.breakthrough.Help.*;
import com.reph3x.breakthrough.Particle.*;
import com.badlogic.gdx.graphics.*;
import com.reph3x.breakthrough.UI.*;
import com.reph3x.breakthrough.Entity.*;

public class Bullet
{
	public Vector2 pos;
	public Vector2 vel;
	public Vector2 target;
	public Vector2 targetPos;
	public Gun gun;
	public float damage;
	public float speed;
	public float size;
	public boolean isFriendly;
	public boolean isDead;
	public ParticleEmitter emitter;
	public Color color;
	
	public Bullet(Vector2 pos, Gun gun, float damage, float size, Vector2 target, float speed, boolean isFriendly) {
		this.pos = pos;
		this.target = target;
		this.targetPos = new Vector2(target).add(pos);
		this.vel = new Vector2(target).nor().scl(speed);
		this.damage = damage;
		this.gun = gun;
		this.size = size;
		this.speed = speed;
		this.isFriendly = isFriendly;
		isDead = false;
		this.emitter = new ParticleEmitter(gun.entity.world);
		setDefaultColor();
	}
	
	public Bullet(Vector2 pos, Gun gun, float damage, Vector2 target, float speed, boolean isFriendly) {
		this(pos, gun, damage, 20, target, speed, isFriendly);
	}
	public Bullet(Bullet b) {
		this(b.pos, b.gun, b.damage, b.size, b.target, b.speed, b.isFriendly);
	}
	
	public void setDefaultColor() {
		if(isFriendly)
			setColor(new Color(Color.GREEN));
		else
			setColor(new Color(Color.RED));
	}
	
	public void update() {
		pos.add(vel);
	}
	
	public void setTarget(Vector2 target) {
		this.target = new Vector2(target);
		this.targetPos = new Vector2(target).add(pos);
		this.vel = new Vector2(target).nor().scl(speed);
	}
	
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
		this.vel = new Vector2(target).nor().scl(speed);
	}
	
	public void render(ShapeRenderer render) {
		render.begin(ShapeRenderer.ShapeType.Filled);
		render.setColor(color);
		render.circle(pos.x, pos.y, size);
		//render.circle(targetPos.x, targetPos.y, 20);
		render.end();
	}
	
	public boolean isOffscreen() {
		float w = Gdx.graphics.getWidth() / 2;
		float h = Gdx.graphics.getHeight() / 2;
		return pos.x > w || pos.x < -w || pos.y > h || pos.y < -h;
	}
	
	public boolean isTouching(Entity entity) {
		return QuickMaths.isTouching(this, entity);
	}
	
	public void onCollision(Entity entity, float damage) {
		Vector2 bounce = vel;
		float bScalar = 0.06f;
		entity.bounce.add(bounce.scl(bScalar).scl(size));
		destroyBullet();
		entity.takeDamage(damage);
		drawParticles();
	}
	public void onCollision(Entity entity) {
		onCollision(entity, damage);
	}
	
	public void drawParticles() {
		Color particleColor = new Color(Color.ORANGE).mul(1, 1, 1, 0.5f);
		emitter.sprayCircle(pos, 15, 15, size, 15, 20, particleColor);
	}
	
	public void destroyBullet() {
		isDead = true;
		gun.bulletList.remove(this);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Bullet scale(float scale) {
		this.size *= scale;
		this.speed *= scale;
		this.damage *= scale;
		return this;
	}
	
	public Bullet clone() {
		return new Bullet(pos, gun, damage,  size, target, speed, isFriendly);
	}
	
}
