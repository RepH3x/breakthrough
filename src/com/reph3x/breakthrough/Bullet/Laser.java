package com.reph3x.breakthrough.Bullet;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Gun.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.Help.*;
import java.util.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.assets.loaders.*;
import com.badlogic.gdx.*;
import com.reph3x.breakthrough.Entity.*;

public class Laser extends Bullet
{
	public int length;
	public List<Entity> hitList;
	public List<Integer> hitTimer;
	public float collisionCooldown;
	public int maxBounces;
	public int bounces;
	
	public Laser(Vector2 pos, Gun gun, float damage, float size, Vector2 target, float speed, boolean isFriendly) {
		super(pos, gun, damage, size, target, speed, isFriendly);
		this.length = 3;
		this.hitList = new ArrayList<Entity>();
		this.hitTimer = new ArrayList<Integer>();
		this.collisionCooldown = 25;
		this.maxBounces = 3;
		this.bounces = 0;
	}
	
	
	public List<Vector2> calculateSegments() {
		List<Vector2> segments = new ArrayList<Vector2>();
		float posDifference = size * 0.75f;
		for(int i=1;i<length;i++) {
			Vector2 newPos = new Vector2(vel).nor().scl(-1);
			newPos.scl(posDifference * i).add(pos);
			segments.add(newPos);
		}
		return segments;
	}

	@Override
	public void update()
	{
		super.update();
		stayOnScreen();
		for(int i=0; i<hitTimer.size(); i++) {
			int timer = hitTimer.get(i) + 1;
			hitTimer.set(i, timer);
			if(timer >= collisionCooldown) {
				hitList.remove(i);
				hitTimer.remove(i);
			}
		}
	}
	
	@Override
	public void render(ShapeRenderer render)
	{
		super.render(render);
		render.begin(ShapeRenderer.ShapeType.Filled);
		for(Vector2 segment : calculateSegments()) {
			render.circle(segment.x, segment.y, size);
		}
		render.end();
		
	}

	@Override
	public boolean isTouching(Entity entity)
	{
		
		if(super.isTouching(entity))
			return true;
		
		for(Vector2 segment : calculateSegments()) {
			if(QuickMaths.isCloserThan(segment, entity, size))
				return true;
		}
		
		return false;
	}

	@Override
	public void onCollision(Entity entity, float damage)
	{
		boolean hasCollided = false;
		for(int i=0; i<hitList.size(); i++) {
			if(hitList.get(i).equals(entity)) {
				hasCollided = true;
			}
		}
		if(!hasCollided) {
			entity.takeDamage(damage);
			drawParticles();
			hitList.add(entity);
			hitTimer.add(0);
			length--;
		}
		if(length == 0)
			destroyBullet();
	}

	@Override
	public void drawParticles()
	{
		Color particleColor = new Color(Color.CYAN).mul(1, 1, 1, 0.3f);
		Vector2 v1 = new Vector2(target).scl(-1);
		//origin, target, numparticles, degrees, size, speed, timer, color
		emitter.sprayCone(pos, v1, 10, 45, 10, 25, 25, particleColor);
		
	}

	public void stayOnScreen() {
		float w = Gdx.graphics.getWidth() / 2;
		float h = Gdx.graphics.getHeight() / 2;
		if(pos.x < -w || pos.x > w) {
			vel.scl(-1, 1);
			bounces++;
		}
		if(pos.y < -h || pos.y > h) {
			vel.scl(1, -1);
			bounces++;
		}
		if(bounces > maxBounces)
			destroyBullet();
	}

	@Override
	public boolean isOffscreen()
	{
		return false;
	}
	
	
	
	
}
