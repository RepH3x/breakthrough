package com.reph3x.breakthrough.Bullet;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Gun.*;
import com.reph3x.breakthrough.Help.*;
import com.reph3x.breakthrough.Entity.*;
import com.reph3x.breakthrough.UI.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;
import java.nio.file.*;

public class HomingBullet extends Bullet
{
	public float homingRange;
	public float homingSpeed;
	public float originalSpeed;
	public HomingBullet(Vector2 pos, Gun gun, float damage, float size, Vector2 target, float speed, float homingRange, float homingSpeed, boolean isFriendly) {
		super(pos, gun, damage, size, target, speed, isFriendly);
		this.homingRange = homingRange;
		this.homingSpeed = homingSpeed;
	}

	@Override
	public void update()
	{
		super.update();
		Entity closestEnemy = QuickMaths.getClosestEnemy(pos, gun.entity.world.entityList, homingRange, isFriendly);
		if(closestEnemy != null) {
			vel.add(new Vector2(closestEnemy.pos).sub(pos).nor().scl(homingSpeed));
			vel.clamp(0, speed);
		}
	}
}

