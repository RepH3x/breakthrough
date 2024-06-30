package com.reph3x.breakthrough.Entity;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Bullet.*;
import com.reph3x.breakthrough.World.*;
import java.util.*;
import com.reph3x.breakthrough.Gun.*;
import com.reph3x.breakthrough.Help.*;
import com.reph3x.breakthrough.Effect.*;
import com.reph3x.breakthrough.UI.*;

public class Entity
{
	public World world;
	public Vector2 pos;
	public Vector2 vel;
	public Vector2 netForce;
	public Vector2 bounce;
	public Gun gun;
	public List<Effect> effectList;
	public float hp;
	public float currentHp;
	public float size;
	public float speed;
	public float currentSpeed;
	public Color color;
	public boolean isDead;
	public boolean isFriendly;
	public float bounceDiminish = 0.95f;

	public Entity(World world, Vector2 pos, float size, float speed, float hp, boolean isFriendly, Color color)
	{
		this.world = world;
		this.pos = new Vector2(pos);
		this.vel = new Vector2();
		this.netForce = new Vector2();
		this.bounce = new Vector2();
		//entity, bullet, damage, size, speed, bulletCount, spread, color
		//this.gun = new ChargeGun(this, Bullets.LASER, 1, 15, 15, Color.YELLOW);
		this.gun = new Shotgun(this, Bullets.WARP, 1, 15, 20, 1, 10, color);
		//this.gun = new MineLayer(this, Bullets.SLOW, 1, 15, color);
		effectList = new ArrayList<Effect>();
		this.size = size;
		this.hp = hp;
		this.currentHp = hp;
		this.isFriendly = isFriendly;
		this.color = color;
		this.speed = speed;
		this.currentSpeed = speed;
		isDead = false;
	}

	public Entity(World world, boolean isFriendly, Color color)
	{
		this(world, new Vector2(0, 0), 50, 25, 10, isFriendly, color);
	}
	public Entity(World world, Vector2 pos, boolean isFriendly, Color color)
	{
		this(world, pos, 25, 0, 1, isFriendly, color);
	}
	public Entity(Entity e) {
		this(e.world, e.pos, e.size, e.speed, e.hp, e.isFriendly, e.color);
	}

	public void render(ShapeRenderer render)
	{
		render.begin(ShapeRenderer.ShapeType.Filled);
		render.setColor(color);
		render.circle(pos.x, pos.y, size);
		render.end();
		drawHealthBar(render);
	}

	public void update()
	{
		checkCollision();
		netForce.setZero();
		netForce.add(vel);
		netForce.add(bounce);
		pos.add(netForce);
		bounce.scl(bounceDiminish);
		stayInBounds();
		if (currentHp <= 0)
			isDead = true;
		cleanEffects();
	}

	public void drawHealthBar(ShapeRenderer render)
	{
		float hpx = pos.x - size;
		float hpy = pos.y + size + 25;
		render.begin(ShapeRenderer.ShapeType.Filled);
		render.setColor(new Color(Color.RED));
		render.rect(hpx, hpy, size * 2, 10);
		render.setColor(new Color(Color.GREEN));
		render.rect(hpx, hpy, (size * 2) * (currentHp / hp), 10);
		render.end();
	}

	public void takeDamage(float damage) {
		world.elementList.add(new DamageIndicator(new Vector2(pos), this, damage, Color.RED, world.batch, world.font));
		mergeIndicators();
		currentHp -= damage;
	}

	public boolean isTouching(Entity entity)
	{
		return QuickMaths.isTouching(this, entity);
	}

	private void stayInBounds() {
		if(pos.x + size > world.camera.viewportWidth / 2) {
			pos.set(world.camera.viewportWidth / 2 - size, pos.y);
			vel.set(0, vel.y);
		}
		if(pos.x - size < -world.camera.viewportWidth / 2) {
			pos.set(-(world.camera.viewportWidth / 2) + size, pos.y);
			vel.set(0, vel.y);
		}
		if(pos.y + size > world.camera.viewportHeight / 2) {
			pos.set(pos.x, world.camera.viewportHeight / 2 - size);
			vel.set(vel.x, 0);
		}
		if(pos.y - size < -world.camera.viewportHeight / 2) {
			pos.set(pos.x, -(world.camera.viewportHeight / 2) + size);
			vel.set(vel.x, 0);
		}
	}

	//requires being overridden to shoot
	//returns true if there was special case
	public boolean specialCaseGuns() {
		if(gun instanceof ChargeGun) {
			ChargeGun cGun = (ChargeGun)gun;
			if(cGun.stageTimer > cGun.stageDur) {
				cGun.currentStage++;
				cGun.stageTimer = 0;
			}
			if(cGun.currentStage > cGun.stages)
				cGun.currentStage = cGun.stages;
			cGun.stageTimer++;
			return true;
		}
		return false;
	}

	public void checkCollision() {
		for(Bullet bullet : world.bulletList) {
			if(bullet.isTouching(this) && isFriendly != bullet.isFriendly) {
				bullet.onCollision(this);
			}
		}
	}

	public void shoot(Vector2 target) {
		gun.shoot(target);
	}

	public void shoot(Entity target) {
		if (target != null) {
			gun.shoot(target);
		}
	}
	
	public void addEffect(Effect effect) {
		Effect effectClone = effect.clone();
		boolean hasEffect = false;
		for(Effect e1 : effectList) {
			if(effectClone.id == e1.id) {
				hasEffect = true;
				if(effectClone.level > e1.level) {
					effectList.remove(e1);
					effectClone.setEntity(this);
					effectList.add(effectClone);
				} else if(effectClone.level == e1.level) {
					if(effectClone.ticks > e1.ticks) {
						effectList.remove(e1);
						effectClone.setEntity(this);
						effectList.add(effectClone);
					}
				}
			}
		}
		if(!hasEffect) {
			effectClone.setEntity(this);
			effectList.add(effectClone);
		}
	}

	public void cleanEffects() {
		List<Effect> deadEffects = new ArrayList<Effect>();
		for(Effect effect : effectList) {
			if(effect.isDead) {
				deadEffects.add(effect);
			} else {
				effect.update();
			}
		}
		effectList.remove(deadEffects);
	}

	public void mergeIndicators() {
		List<DamageIndicator> mergeList = new ArrayList<DamageIndicator>();
		for(Element e : world.elementList) {
			if(e instanceof DamageIndicator) {
				DamageIndicator dI = (DamageIndicator)e;
				if(dI.entity.equals(this)) {
					mergeList.add(dI);
				}
			}
		}
		float totalDamage = 0;
		for(DamageIndicator dI : mergeList) {
			totalDamage += dI.damage;
			dI.isDead = true;
		}
		world.elementList.add(new DamageIndicator(new Vector2(pos), this, totalDamage, Color.RED, world.batch, world.font));
	}


}
