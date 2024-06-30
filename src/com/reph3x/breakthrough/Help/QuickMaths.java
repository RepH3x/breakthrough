package com.reph3x.breakthrough.Help;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.Bullet.*;
import java.util.*;
import com.reph3x.breakthrough.Entity.*;
import com.reph3x.breakthrough.Particle.*;

public class QuickMaths
{
	
	private static Random r = new Random();
	
	public static float getDistance(Vector2 p1, Vector2 p2) {
		return (float)Math.sqrt(getQuickDistance(p1, p2));
	}
	
	public static float getQuickDistance(Vector2 p1, Vector2 p2) {
		return (float)(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
	}
	
	public static boolean isCloserThan(Vector2 p1, Vector2 p2, float distance) {
		return getQuickDistance(p1, p2) <= Math.pow(distance, 2);
	}
	public static boolean isCloserThan(Vector2 p1, Entity e1, float distance) {
		return isCloserThan(p1, e1.pos, distance + e1.size);
	}
	public static boolean isCloserThan(Vector2 p1, Entity e1, Entity e2) {
		return isCloserThan(p1, e1.pos, getDistance(p1, e2.pos));
	}
	public static boolean isTouching(Entity e1, Entity e2) {
		return isCloserThan(e1.pos, e2.pos, e1.size + e2.size);
	}
	public static boolean isTouching(Bullet b1, Entity e1) {
		return isCloserThan(b1.pos, e1.pos, b1.size + e1.size);
	}
	public static boolean isTouching(Bubble b1, Bubble b2) {
		return isCloserThan(b1.pos, b2.pos, b1.maxSize + b2.maxSize);
	}
	
	
	public static Vector2 getRandomConeAngle(Vector2 origin, Vector2 target, int degrees) {
		Vector2 angle = new Vector2(target).sub(origin).nor();
		if(r.nextBoolean())
			angle.rotate(r.nextInt(degrees/2));
		else 
			angle.rotate(-(r.nextInt(degrees/2)));
		return angle;
	}
	
	public static Vector2 getRandomPointC(Vector2 origin, float size){
		int degrees = r.nextInt(360);
		Vector2 point = new Vector2(0, 1);
		point.rotate(degrees).scl(size);
		return point.add(origin);
	}
	
	public static float getRandomDegen() {
		float degen = r.nextFloat();
		if(degen < 0.8f)
			degen = getRandomDegen();
		return degen;
	}
	
	public static Vector2 getRandomPointInCircle(Vector2 origin, float size) {
		return getRandomPointC(origin, size-(size/r.nextFloat()));
	}
	public static Entity getClosestEntity(Vector2 origin, List<Entity> entityList) {
		Entity closest = null;
		if(!entityList.isEmpty())
			closest = entityList.get(0);
		for(Entity entity: entityList) {
			if(isCloserThan(origin, entity, closest))
				closest = entity;
		}
		return closest;
	}
	//gets the closest entity of opposite isfriendly than given
	public static Entity getClosestEnemy(Vector2 origin, List<Entity> entityList, float range, boolean isFriendly) {
		List<Entity> enemyList = new ArrayList<Entity>();
		for(Entity entity : entityList) {
			if(entity.isFriendly != isFriendly && QuickMaths.isCloserThan(origin, entity, range))
				enemyList.add(entity);
		}
		return getClosestEntity(origin, enemyList);
	}
	
}
