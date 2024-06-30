package com.reph3x.breakthrough.Gun;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.Bullet.*;
import com.reph3x.breakthrough.Particle.*;
import com.badlogic.gdx.graphics.*;
import java.util.*;
import com.reph3x.breakthrough.Entity.*;
import com.reph3x.breakthrough.Effect.*;

public class Gun
{
	public Bullets defaultBullet;
	public float damage;
	public float size;
	public float speed;
	public Entity entity;
	public ParticleEmitter emitter;
	public Color color;
	public List<Bullet> bulletList;
	
	
	public Gun (Entity entity, Bullets bullet, float damage, float size, float speed, Color color) {
		this.entity = entity;
		this.damage = damage;
		this.size = size;
		this.speed = speed;
		this.emitter = new ParticleEmitter(entity.world);
		this.color = color;
		this.defaultBullet = bullet;
		this.bulletList = new ArrayList<Bullet>();
	}
	
	public void shoot(Vector2 target, float scale) {
		target.sub(entity.pos);
		Bullet bullet = createBullet(target, scale);
		entity.world.queueUpdate(bullet);
		bulletList.add(bullet);
		drawParticles(target);
	}
	
	public void shoot(Entity target) {
		if (target != null) {
			Vector2 targetPos = new Vector2(target.pos);
			shoot(targetPos, 1);
		}
	}
	public void shoot(Vector2 target) {
		shoot(target, 1);
	}
	
	public void setDefaultBullet(Bullets b) {
		defaultBullet = b;
	}
	
	public Bullet createBullet(Vector2 target, Bullets b, float scale) {
		switch(b) {
			case(BULLET): return new Bullet(new Vector2(entity.pos), this, damage, size, target, speed, entity.isFriendly).scale(scale);
			case(TARGETED): return new TargetedBullet(new Vector2(entity.pos), this, damage, size, target, speed, entity.isFriendly).scale(scale);
			case(DOT): return new DoTBullet(new Vector2(entity.pos), this, damage, 1, 0, 2, 50, size, target, speed, entity.isFriendly).scale(scale);
			case(SLOW): return new SlowBullet(new Vector2(entity.pos), this, damage, 1, 50, 3, 20, 15,  target, speed, entity.isFriendly).scale(scale);
			case(EXPLOSION): return new ExplosionBullet(new Vector2(entity.pos), this, damage, target, speed, entity.isFriendly).scale(scale);
			case(LASER): return new Laser(new Vector2(entity.pos), this, damage, size, target, speed, entity.isFriendly).scale(scale);
			case(BOOMERANG): return new BoomerangBullet(new Vector2(entity.pos), this, damage, size, target, speed, entity.isFriendly).scale(scale);
			case(ORBIT): return new OrbitBullet(new Vector2(entity.pos), this, damage, size, target, speed, entity.isFriendly).scale(scale);
			case(WARP): return new WarpBullet(new Vector2(entity.pos), this, damage, size, target, 100, speed, entity.isFriendly).scale(scale);
			case(EFFECTPOOL): return new EffectPoolBullet(new Vector2(entity.pos), this, damage, target, speed, entity.isFriendly, new EffectDoT(null, 0, 2, 50, 1)).scale(scale);
			case(HOMING): return new HomingBullet(new Vector2(entity.pos), this, damage, 25, target, speed, 500, 2, entity.isFriendly).scale(scale);
			case(SPLIT): return new SplitBullet(new Vector2(entity.pos), this, damage, size, target, speed, entity.isFriendly).scale(scale);
		}
		return createBullet(target, defaultBullet, scale);
	}
	
	public Bullet createBullet(Vector2 target, float scale) {
		return createBullet(target, defaultBullet, scale);
	}
	
	public Bullet createBullet(Vector2 target) {
		return createBullet(target, defaultBullet, 1);
	}
	
	public void drawParticles(Vector2 target) {
		Color smokeColor = new Color(Color.GRAY).mul(1, 1, 1, 0.5f);
		emitter.sprayCone(entity.pos, target, 100, 45, 5, 20, 15, smokeColor);
	}
}
