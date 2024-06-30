package com.reph3x.breakthrough.Bullet;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Gun.*;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.Help.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;
import com.reph3x.breakthrough.Entity.*;

public class WarpBullet extends TargetedBullet
{
	public float warpRange;
	public float warpDistance;
	public float speedMod;
	public boolean hasWarped;

	public WarpBullet(Vector2 pos, Gun gun, float damage, float size, Vector2 target, float warpRange, float speed, boolean isFriendly) {
		super(pos, gun, damage, size, target, speed, isFriendly);
		this.warpRange = warpRange;
		this.warpDistance = 150;
		this.speedMod = 150;
		hasWarped = false;
	}

	@Override
	public void update() {
		super.update();
		if(!hasWarped && hasEntityInRange()) {
			warp(getClosestEntityInRange());
		}
	}
	
	private boolean hasEntityInRange() {
		for(Entity e : gun.entity.world.entityList) {
			if(isFriendly != e.isFriendly && QuickMaths.isCloserThan(pos, e, warpRange))
				return true;
		}
		return false;
	}
	
	private Entity getClosestEntityInRange() {
		Entity entity = gun.entity.world.entityList.get(0);
		for(Entity e : gun.entity.world.entityList) {
			if(isFriendly != e.isFriendly && QuickMaths.isCloserThan(pos, e, warpRange)) {
				if(QuickMaths.getQuickDistance(pos, e.pos) < QuickMaths.getQuickDistance(pos, entity.pos)) {
					entity = e;
				}
			}
		}
		return entity;
	}
	
	private void warp(Entity entity) {
		Color c = new Color(Color.CYAN).mul(1, 1, 1, 0.1f);
		emitter.puddle(new Vector2(pos), 15, 15, c);
		setPos(QuickMaths.getRandomPointC(new Vector2(entity.pos), warpDistance));
		setTarget(new Vector2(entity.pos).sub(pos));
		setSpeed(speed * (speedMod / 100));
		emitter.puddle(new Vector2(pos), 15, 15, c);
		hasWarped = true;
	}


}
