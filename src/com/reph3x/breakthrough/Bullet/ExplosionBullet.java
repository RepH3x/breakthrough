package com.reph3x.breakthrough.Bullet;
import com.reph3x.breakthrough.Gun.*;
import com.reph3x.breakthrough.World.*;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.Help.*;
import com.badlogic.gdx.math.*;
import java.util.*;
import com.badlogic.gdx.graphics.*;
import com.reph3x.breakthrough.Entity.*;

public class ExplosionBullet extends TargetedBullet
{
	
	public float explosionSize;
	public float explosionDamage;
	public Color explosionColor;
	
	public ExplosionBullet(Vector2 pos, Gun gun, float damage, float explosionSize, float explosionDamage, float size, Vector2 target, float speed, boolean isFriendly) {
		super(pos, gun, damage, size, target, speed, isFriendly);
		this.explosionSize = explosionSize;
		this.explosionDamage = explosionDamage;
		this.explosionColor = new Color(Color.YELLOW);
		explosionColor.a = 0.4f;
	}
	
	public ExplosionBullet(Vector2 pos, Gun gun, float damage, Vector2 target, float speed, boolean isFriendly) {
		this(pos, gun, damage, 200, 2, 15, target, speed, isFriendly);
	}

	public void onExplosion(Entity entity, float damage)
	{
		Vector2 bounce = new Vector2(pos).sub(entity.pos).nor().scl(-explosionSize*1.5f);
		float bScalar = 0.06f;
		entity.bounce.add(bounce.scl(bScalar));
		destroyBullet();
		entity.takeDamage(damage);
	}
	
	

	@Override
	public void explode()
	{
		super.explode();
		World world = gun.entity.world;
		world.addParticle(pos, new Vector2(), explosionSize, 5, explosionColor);
		if(isFriendly != world.player.isFriendly && QuickMaths.isCloserThan(pos, world.player, explosionSize)) 
			onExplosion(world.player, explosionDamage);
		for(Entity entity : world.entityList) {
			if(isFriendly != entity.isFriendly && QuickMaths.isCloserThan(pos, entity, explosionSize))
				onExplosion(entity, explosionDamage);
		}
	}

	@Override
	public void onCollision(Entity entity, float damage)
	{
		super.onCollision(entity, damage);
		explode();
	}
	
	

	@Override
	public void drawTargetParticles()
	{
		Color particleColor = new Color(Color.ORANGE).mul(1, 1, 1, 0.3f);
		emitter.sprayCircle(pos, 25, 15, explosionSize, 15, 20, particleColor);
	}
	
	
	
}
