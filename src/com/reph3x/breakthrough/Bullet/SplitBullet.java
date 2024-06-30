package com.reph3x.breakthrough.Bullet;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Gun.*;
import com.badlogic.gdx.graphics.*;

public class SplitBullet extends TargetedBullet
{
	public SplitBullet(Vector2 pos, Gun gun, float damage, float size, Vector2 target, float speed, boolean isFriendly) {
		super(pos, gun, damage, size, target, speed, isFriendly);
	}

	@Override
	public void explode()
	{
		super.explode();
		Vector2 newTarget1 = new Vector2(origin).sub(target).rotate(-45);
		Vector2 newTarget2 = new Vector2(origin).sub(target).rotate(45);
		Bullet newBullet1 = clone();
		Bullet newBullet2 = clone();
		newBullet1.color = Color.PINK;
		newBullet2.color = Color.PURPLE;
		newBullet1.setTarget(newTarget1);
		newBullet2.setTarget(newTarget2);
		gun.entity.world.queueUpdate(newBullet1);
		gun.entity.world.queueUpdate(newBullet2);
	}
	
}
