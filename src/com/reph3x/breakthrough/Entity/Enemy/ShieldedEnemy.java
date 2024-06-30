package com.reph3x.breakthrough.Entity.Enemy;
import com.reph3x.breakthrough.World.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;
import com.reph3x.breakthrough.Help.*;
import com.reph3x.breakthrough.Entity.*;
import java.util.*;
import com.reph3x.breakthrough.Bullet.*;

public class ShieldedEnemy extends Enemy
{
	public ShieldedEnemy(World world, Vector2 pos, float size, float speed, float hp, float shootCooldown, Color color) {
		super(world, pos, size, speed, hp, shootCooldown, color);
	}
	public ShieldedEnemy(World world, Vector2 pos) {
		this(world, pos, 25, 5, 5, 300, Color.BLUE);
	}

	@Override
	public void update()
	{
		super.update();
		List<Entity> otherEntityList = new ArrayList<Entity>(world.entityList);
		otherEntityList.remove(this);
		Entity entity = QuickMaths.getClosestEntity(pos, otherEntityList);
		if(entity != null && isFriendly == entity.isFriendly && isTouching(entity) && !(entity instanceof Pool)) {
			isDead = true;
			gun.emitter.puddle(pos, 35, 25, Color.ORANGE);
		}
	}

	@Override
	public void checkCollision()
	{
		for(Bullet bullet: world.bulletList) {
			if(bullet.isFriendly != isFriendly && bullet.isTouching(this)) {
				bullet.isDead = true;
				gun.emitter.sprayCircle(pos, 25, 5, size*2, 5, 25, Color.BLUE); 
			}
		}
	}

	@Override
	protected void avoidAllies()
	{
		float avoidRange = 100;
		float bounceScalar = 1;
		for (Entity entity : world.entityList) {
			if(entity != this && entity instanceof ShieldedEnemy) {
				float distance = (float)Math.pow(entity.pos.x - pos.x, 2) + (float)Math.pow(entity.pos.y - pos.y, 2);
				if(distance <= Math.pow(entity.size + avoidRange, 2)) {
					Vector2 bounceDir = new Vector2(entity.pos);
					bounceDir.sub(pos).nor().scl(bounceScalar);
					bounceDir.scl(-1);
					bounce.add(bounceDir);
				}
			}
		}
	}  
	
	
}
